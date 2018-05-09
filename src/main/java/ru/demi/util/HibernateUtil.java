package ru.demi.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.demi.hibernate.ImplicitNamingStrategyImpl;
import ru.demi.hibernate.PhysicalNamingStrategyImpl;

public class HibernateUtil {

	private static final SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
		try {
            Configuration config = new Configuration().configure();
            config.setImplicitNamingStrategy(ImplicitNamingStrategyImpl.INSTANCE);
            config.setPhysicalNamingStrategy(PhysicalNamingStrategyImpl.INSTANCE);
            return config.buildSessionFactory();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("There was an error building the factory");
		}
	}
	
	public static SessionFactory getSessionFactory(){
		return sessionFactory;
	}
}
