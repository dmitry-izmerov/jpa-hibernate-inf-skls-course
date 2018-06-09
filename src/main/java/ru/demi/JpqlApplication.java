package ru.demi;

import ru.demi.entities.Transaction;
import ru.demi.entities.TransactionType;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

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

            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<Transaction> criteriaQuery = builder.createQuery(Transaction.class);

            Root<Transaction> root = criteriaQuery.from(Transaction.class);
            Path<BigDecimal> amountPath = root.get("amount");
            Path<TransactionType> typePath = root.get("transactionType");

            criteriaQuery.select(root).where(builder.and(
                builder.le(amountPath, new BigDecimal(20)),
                builder.equal(typePath, TransactionType.Withdrawl)
            ));

            TypedQuery<Transaction> query = em.createQuery(criteriaQuery);
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
