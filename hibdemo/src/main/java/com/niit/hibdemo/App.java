package com.niit.hibdemo;

import org.hibernate.Session;

/**
 * Hello world!
 *
 */


import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class App 
{
	static Session sessionObj;
	static SessionFactory sessionFactoryObj;
	
	private static SessionFactory buildSessionFactory() {
		// Creating Configuration Instance & Passing Hibernate Configuration File
		Configuration configObj = new Configuration();
		
		//configObj.configure("hibernate.cfg.xml");
		configObj.configure("hibernate.cfg.xml").addAnnotatedClass(Employee.class);

		// Since Hibernate Version 4.x, ServiceRegistry Is Being Used
		ServiceRegistry serviceRegistryObj = new StandardServiceRegistryBuilder().applySettings(configObj.getProperties()).build(); 

		// Creating Hibernate SessionFactory Instance
		sessionFactoryObj = configObj.buildSessionFactory(serviceRegistryObj);
		//sessionFactoryObj = configObj.buildSessionFactory(); is deprecated
		return sessionFactoryObj;
	}
    public static void main( String[] args )
    {
    	sessionObj = buildSessionFactory().openSession();
		sessionObj.beginTransaction();
	    /* Student s=new Student();
	     s.setSid(11);
	     s.setName("rahul");
	     sessionObj.persist(s);
		*/
		Employee e=new Employee();
		e.setEmpId(29);
		e.setEname("girish");
		try
		{
			 Employee no=  (Employee) sessionObj.save(e);
			 
			  sessionObj.getTransaction().commit();
			  System.out.println(no);
			  
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
			sessionObj.getTransaction().rollback();
			
		}
	    

	     //;
    }
}
