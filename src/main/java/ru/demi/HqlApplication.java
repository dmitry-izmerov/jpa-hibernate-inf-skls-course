package ru.demi;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.hibernate.query.Query;
import ru.demi.entities.Transaction;
import ru.demi.util.HibernateUtil;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class HqlApplication {

    public static void main(String[] args) {

        SessionFactory factory = null;
        Session session = null;
        org.hibernate.Transaction tx = null;

        try {
            factory = HibernateUtil.getSessionFactory();
            session = factory.openSession();
            tx = session.beginTransaction();

            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Transaction> criteriaQuery = builder.createQuery(Transaction.class);
            Root<Transaction> root = criteriaQuery.from(Transaction.class);
            criteriaQuery.select(root).orderBy(builder.desc(root.get("title")));
            Query<Transaction> query = session.createQuery(criteriaQuery);
            List<Transaction> accounts = query.getResultList();

            accounts.forEach(item -> {
                System.out.println(item.getTitle());
            });

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw new RuntimeException(e);
        } finally {
            session.close();
            factory.close();
        }
    }
}

