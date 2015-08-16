# SassionSaveUpdateMergePersistVersion
Sassion - Save Update Merge Persist Version

------------------------------------------------------------------------------------------------------------

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
			session3.close();

	1. SAVE
	emp.seteName("sriram");
	session2.save(emp);
	
	Hibernate: select nextval ('employeeIdGen')
	Hibernate: /* insert com.hibernate.SassionSaveUpdateMergePersistVersion.pojo.Employee */ insert into mydb.employee (eName, EID) values (?, ?)
	Hibernate: /* update com.hibernate.SassionSaveUpdateMergePersistVersion.pojo.Employee */ update mydb.employee set eName=? where EID=?
	Hibernate: select nextval ('employeeIdGen')
	Hibernate: /* insert com.hibernate.SassionSaveUpdateMergePersistVersion.pojo.Employee */ insert into mydb.employee (eName, EID) values (?, ?)
	Hibernate: /* from employee */ select employee0_.EID as EID1_0_, employee0_.eName as eName2_0_ from mydb.employee employee0_
	17-08-2015 01:47:55 2653 [main] INFO  c.h.S.app.App - Emp : Employee [eId=27, eName=rajaram, ver=1] 
	17-08-2015 01:47:55 2654 [main] INFO  c.h.S.app.App - Emp : Employee [eId=28, eName=sriram, ver=1]


	2. MERGE
	emp.seteName("sriram");
	session2.merge(emp);
	
	Hibernate: select nextval ('employeeIdGen')
	Hibernate: /* insert com.hibernate.SassionSaveUpdateMergePersistVersion.pojo.Employee */ insert into mydb.employee (eName, EID) values (?, ?)
	Hibernate: /* update com.hibernate.SassionSaveUpdateMergePersistVersion.pojo.Employee */ update mydb.employee set eName=? where EID=?
	Hibernate: /* load com.hibernate.SassionSaveUpdateMergePersistVersion.pojo.Employee */ select employee0_.EID as EID1_0_0_, employee0_.eName as eName2_0_0_ from mydb.employee employee0_ where employee0_.EID=?
	Hibernate: /* update com.hibernate.SassionSaveUpdateMergePersistVersion.pojo.Employee */ update mydb.employee set eName=? where EID=?
	Hibernate: /* from employee */ select employee0_.EID as EID1_0_, employee0_.eName as eName2_0_ from mydb.employee employee0_
	17-08-2015 01:47:01 2950 [main] INFO  c.h.S.app.App - Emp : Employee [eId=26, eName=sriram, ver=2] 
