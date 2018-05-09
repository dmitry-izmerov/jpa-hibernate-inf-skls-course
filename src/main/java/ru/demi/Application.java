package ru.demi;

import org.hibernate.Session;
import ru.demi.entities.User;
import ru.demi.util.HibernateUtil;

import java.time.LocalDate;

public class Application {

	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
        LocalDate birthDate = LocalDate.of(1990, 4, 5);
        User user = new User("Vasya", "Pupkin", birthDate, "vasya@mail.ru", "admin");
        session.save(user);
		session.getTransaction().commit();
		session.close();
	}
}
