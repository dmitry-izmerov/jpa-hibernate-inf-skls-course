package ru.demi;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import ru.demi.entities.Transaction;
import ru.demi.entities.TransactionType;
import ru.demi.util.HibernateUtil;

public class HqlApplication {

    public static void main(String[] args) {

        SessionFactory factory = null;
        Session session = null;
        org.hibernate.Transaction tx = null;

        try {
            factory = HibernateUtil.getSessionFactory();
            session = factory.openSession();
            tx = session.beginTransaction();

            Query query = session.createQuery("select t from Transaction t" +
                " where t.transactionType = ?" +
                " and t.amount >= ?");
            query.setParameter(0, TransactionType.Withdrawl);
            query.setParameter(1, new BigDecimal(100));
            List<Transaction> transactions = query.list();

            transactions.forEach(item -> System.out.println(item.getTitle()));

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

