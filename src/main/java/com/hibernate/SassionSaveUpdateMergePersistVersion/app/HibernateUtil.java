package com.hibernate.SassionSaveUpdateMergePersistVersion.app;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.slf4j.LoggerFactory;

public class HibernateUtil {
	static org.slf4j.Logger logger = LoggerFactory.getLogger(HibernateUtil.class);

	private static SessionFactory sessionFactory = null;

	private static SessionFactory buildSessionFactory() {
		if (sessionFactory == null) {
			try {
				// Create the SessionFactory from hibernate.cfg.xml
				Configuration configuration = new Configuration().configure();
				StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
				logger.info("Initial SessionFactory creation successfully!");
				return configuration.configure().buildSessionFactory(serviceRegistry);
			} catch (Throwable ex) {
				// Make sure you log the exception, as it might be swallowed
				logger.error("Initial SessionFactory creation failed :: " + ex);
				throw new ExceptionInInitializerError(ex);
			}
		} else {
			return sessionFactory;
		}
	}

	public static SessionFactory getSessionFactory() {
		return buildSessionFactory();
	}

	public static void shutdown() {
		// Close caches and connection pools
		getSessionFactory().close();
	}

}
