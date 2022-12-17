package com.bookstore.entity;
import com.bookstore.entity.Users;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class UsersTest {

	public static void main(String[] args) {
		//create user
		Users user1 = new Users();
		user1.setEmail("j@emai.com");
		user1.setFullName("Mister Champ");
		user1.setPassword("abc123");
		//create entity manager from entityManagerFactory
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("BookStoreWebsite");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		//create transaction
		entityManager.getTransaction().begin();
		//persist user
		entityManager.persist(user1);
		//commit transaction
		entityManager.getTransaction().commit();
		//close entityManager
		entityManager.close();
		//close entityManagerFactory
		entityManagerFactory.close();

		System.out.println("Persisted user");


	}

}
