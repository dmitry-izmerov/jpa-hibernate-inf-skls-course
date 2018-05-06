package ru.demi.data;

import java.util.Date;
import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import org.hibernate.cfg.ImprovedNamingStrategy;
import ru.demi.data.entities.AccountType;

public class Application {

	public static void main(String[] args) {
		
		Configuration configuration = new Configuration();

		configuration.addAnnotatedClass(AccountType.class);

		configuration.setNamingStrategy(new ImprovedNamingStrategy());

		configuration.setProperties(new Properties() {
			{
				put("hibernate.connection.username", "ifinances_admin");
				put("hibernate.connection.password", "pass");
				put("hibernate.connection.driver_class",
						"com.mysql.jdbc.Driver");
				put("hibernate.connection.url",
						"jdbc:mysql://localhost:3306/ifinances");
			}
		});

		SessionFactory sessionFactory = configuration
				.buildSessionFactory(
				    new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build()
                );
	
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		AccountType type = new AccountType();

		type.setName("check");
		type.setCreatedDate(new Date());
		type.setLastUpdatedDate(new Date());
		type.setCreatedBy("vasya");
		type.setLastUpdatedBy("vasya");
		
		session.save(type);
		session.getTransaction().commit();
		session.close();
	}
}
