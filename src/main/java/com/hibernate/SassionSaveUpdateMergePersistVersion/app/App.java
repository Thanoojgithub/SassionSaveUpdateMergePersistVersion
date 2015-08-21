package com.hibernate.SassionSaveUpdateMergePersistVersion.app;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.LoggerFactory;

import com.hibernate.SassionSaveUpdateMergePersistVersion.pojo.Employee;


public class App {
	
	static org.slf4j.Logger logger = LoggerFactory.getLogger(App.class);

	public static void main(String[] args) {
		logger.info(" ****** Sassion Save Update Merge Persist Version **** ");
		Session session1 = null;
		Session session2 = null;
		Session session3 = null;
		Transaction tx1 = null;
		Transaction tx2 = null;
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		try {
			session1 = sessionFactory.openSession();
			tx1 = session1.beginTransaction();
			SQLQuery createSQLQuery = session1.createSQLQuery("delete from mydb.employee");
			createSQLQuery.executeUpdate();
			Employee employee = new Employee("ram");
			Serializable eId = session1.save(employee);
			/*
			 * here, name modification is in same session context, so it will call update operation on same entity instance.
			 */
			employee.seteName("rajaram");
			//Serializable eId = session1.save(employee);
			Employee emp = (Employee)session1.get(Employee.class, eId);
			tx1.commit();
			session1.close();
			session2 = sessionFactory.openSession();
			tx2 = session2.beginTransaction();
			employee.seteName("sriram");
			/*
			 * save() will call a insert operation to insert same instance as new record in DB.
			 */
			//session2.save(emp);
			/*
			 * merge() will call a update operation to update same instance in DB.
			 */
			Object merge = session2.merge(emp);
			tx2.commit();
			session2.close();
			session3 = sessionFactory.openSession();
			Query query = session3.createQuery("from employee");
			@SuppressWarnings("unchecked")
			List<Employee> employees = (List<Employee>)query.list();
			for (Employee empTemp : employees) {
				logger.info("Emp : "+empTemp);
			}
			/**
			 * RESART - SEQUENCE
			 */
			session3.createSQLQuery("ALTER SEQUENCE employeeIdGen RESTART").executeUpdate();
			session3.close();
		} catch (RuntimeException e) {
			try {
				if (tx1 != null) {
					tx1.rollback();
				}
				if (tx2 != null) {
					tx2.rollback();
				}
			} catch (RuntimeException rbe) {
				logger.error("Couldn’t roll back transaction", rbe);
			}
			throw e;
		} finally {
			if (session1 != null && session1.isOpen()) {
				session1.close();
			}
			if (session2 != null && session2.isOpen()) {
				session2.close();
			}
		}
		logger.info(" ****** Sassion Save Update Merge Persist Version **** ");
	}

}
