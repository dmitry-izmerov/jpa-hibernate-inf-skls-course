package ru.demi;

import org.hibernate.Session;
import ru.demi.entities.Address;
import ru.demi.entities.Bank;
import ru.demi.entities.User;
import ru.demi.util.HibernateUtil;

import java.time.LocalDate;
import java.util.Date;

public class Application {

	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

        Address address = new Address();
        address.setAddressLine1("Moroseyka, 11");
        address.setAddressLine2("Petrovka, 22");
        address.setCity("Moscow");
        address.setZipCode("120204");

		// user
        /*LocalDate birthDate = LocalDate.of(1990, 4, 5);
        User user = new User("Vasya", "Pupkin", birthDate, "vasya@mail.ru", "admin");
        user.setAddress(address);
        session.save(user);*/

        // bank
        Bank bank = new Bank();
        bank.setName("VTB");
        bank.setAddress(address);
        bank.setCreatedBy("Vasya Petrov");
        bank.setCreatedDate(new Date());
        bank.setLastUpdatedBy("Vasya Petrov");
        bank.setLastUpdatedDate(new Date());
        bank.getContacts().add("Timofei");
        bank.getContacts().add("Alex");
        session.save(bank);

        session.getTransaction().commit();
		session.close();
	}
}
