package ru.demi;

import java.util.List;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class JpqlApplication {

    public static void main(String[] args) {

        EntityManagerFactory factory = null;
        EntityManager em = null;
        EntityTransaction tx = null;

        try {
            factory = Persistence.createEntityManagerFactory("infinite-finances");
            em = factory.createEntityManager();
            tx = em.getTransaction();
            tx.begin();

            String jsql = "select distinct t.account.name," +
                " concat(concat(t.account.bank.name, ' '), t.account.bank.address.state) from Transaction t" +
                " where lower(t.transactionType) = 'deposit'" +
                " and t.amount >= 500";;
            Query query = em.createQuery(jsql);

            List<Object[]> rows = query.getResultList();

            rows.forEach(row -> {
                System.out.println(row[0]);
                System.out.println(row[1]);
            });

            tx.commit();
        } catch (Exception e) {
            if (Objects.nonNull(tx)) {
                tx.rollback();
            }
            throw new RuntimeException(e);
        } finally {
            if (Objects.nonNull(em)) {
                em.close();
            }
            if (Objects.nonNull(factory)) {
                factory.close();
            }
        }
    }
}
