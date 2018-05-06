package ru.demi.data;

import org.hibernate.Session;
import ru.demi.data.util.HibernateUtil;

public class Application {

	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.close();
	}
}
