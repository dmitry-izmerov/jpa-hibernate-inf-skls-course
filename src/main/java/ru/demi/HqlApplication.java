package ru.demi;

import java.util.List;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import ru.demi.entities.Account;
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

            Query query = session.createQuery("select distinct t.account from Transaction t" +
                " where t.transactionType = 'Deposit'" +
                " and t.amount >= 500");

            List<Account> accounts = query.list();

            accounts.forEach(item -> System.out.println(item.getName()));

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

