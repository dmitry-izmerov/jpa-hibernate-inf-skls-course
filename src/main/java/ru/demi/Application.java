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

        Address address1 = new Address();
        address1.setAddressLine1("Moroseyka, 11");
        address1.setAddressLine2("Petrovka, 22");
        address1.setCity("Moscow");
        address1.setState("Moscow");
        address1.setZipCode("120204");

        Address address2 = new Address();
        address2.setAddressLine1("line 1");
        address2.setAddressLine2("line 2");
        address2.setCity("Tver");
        address2.setState("Tverskaya oblast");
        address2.setZipCode("160303");

		// user
        LocalDate birthDate = LocalDate.of(1990, 4, 5);
        User user = new User("Vasya", "Pupkin", birthDate, "vasya@mail.ru", "admin");
        user.getAddresses().add(address1);
        user.getAddresses().add(address2);
        session.save(user);

        // bank
        /*Bank bank = new Bank();
        bank.setName("VTB");
        bank.setAddress(address1);
        bank.setCreatedBy("Vasya Petrov");
        bank.setCreatedDate(new Date());
        bank.setLastUpdatedBy("Vasya Petrov");
        bank.setLastUpdatedDate(new Date());
        bank.getContacts().put("CLIENT_MANAGER", "Timofei");
        bank.getContacts().put("SELLER", "Alex");
        session.save(bank);*/

        session.getTransaction().commit();
		session.close();
	}
}
