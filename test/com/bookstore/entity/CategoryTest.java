package com.bookstore.entity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CategoryTest {

	public static void main(String[] args) {
		//create category
		Category category = new Category("Advanced Java");
		//create entity manager from entityManagerFactory
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("BookStoreWebsite");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		//create transaction
		entityManager.getTransaction().begin();
		//persist user
		entityManager.persist(category);
		//commit transaction
		entityManager.getTransaction().commit();
		//close entityManager
		entityManager.close();
		//close entityManagerFactory
		entityManagerFactory.close();

		System.out.println("Persisted category");


	}

}
