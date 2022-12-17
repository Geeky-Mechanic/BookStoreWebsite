package com.bookstore.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class BaseDaoTest {
	protected static EntityManagerFactory entityManagerFactory;
	protected static EntityManager entityManager;
	
	public static void beforeClass() throws Exception {
		//create entity manager from entityManagerFactory
		entityManagerFactory = Persistence.createEntityManagerFactory("BookStoreWebsite");
		entityManager = entityManagerFactory.createEntityManager();
	}
	
	public static void tearDownAfterClass() throws Exception {
		entityManager.close();
		entityManagerFactory.close();
	}
}
