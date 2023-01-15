package com.melihcanozturk.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.melihcanozturk.entity.Admin;
import com.melihcanozturk.entity.Category;
import com.melihcanozturk.entity.Customer;
import com.melihcanozturk.entity.Product;
import com.melihcanozturk.entity.ProductEvaluate;

public class HibernateUtils {

	private static final SessionFactory SESSION_FACTORY = sessionFactoryHibernate();

	private static SessionFactory sessionFactoryHibernate() {
		
		try {
			Configuration configuration = new Configuration();
			
			
			configuration.addAnnotatedClass(Admin.class);
			configuration.addAnnotatedClass(Customer.class);
			configuration.addAnnotatedClass(Product.class);
			configuration.addAnnotatedClass(Category.class);
			configuration.addAnnotatedClass(ProductEvaluate.class);
			
			
			SessionFactory factory = configuration.configure("hibernate.cfg.xml").buildSessionFactory();
			
			return factory;
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		return null;
	}

	public static SessionFactory getSessionFactory() {
		return SESSION_FACTORY;
	}
	
	
	
}
