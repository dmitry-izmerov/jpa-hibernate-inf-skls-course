package ru.demi;

import ru.demi.entities.Transaction;

import java.util.List;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

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

            String jsql = "from Transaction t" +
                " where (t.amount between 30 and 100)" +
                " and title like '%s'" +
                " order by t.title desc";
            TypedQuery<Transaction> query = em.createQuery(jsql, Transaction.class);
            List<Transaction> transactions = query.getResultList();

            transactions.forEach(item -> System.out.println(item.getTitle()));

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
