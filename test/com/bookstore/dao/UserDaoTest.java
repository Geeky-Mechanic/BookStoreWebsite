package com.bookstore.dao;

import com.bookstore.entity.Users;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

public class UserDaoTest extends BaseDaoTest {

	private static UserDao userDao;

	@BeforeClass
	public static void beforeClass() throws Exception {
		//create entity manager from entityManagerFactory
		BaseDaoTest.beforeClass();
		userDao = new UserDao(entityManager);
	}

	@Test
	public void testCreateUsers() {
		//create user
		Users user1 = new Users();
		user1.setEmail("john2@gmail.com");
		user1.setFullName("John Smith");
		user1.setPassword("abcdefg");

		user1 = userDao.create(user1);

		assertTrue(user1.getUserId() > 0);
	}

	@Test
	public void testCreateUsersUninitialized() {
		Users user1 = new Users();
		user1 = userDao.create(user1);

		assertTrue(user1.getUserId() > 0);
	}

	@Test
	public void testUpdateUsers() {
		Users user = new Users();
		user.setUserId(1);
		user.setEmail("j@gmail.com");
		user.setFullName("Jeremy Champ");
		user.setPassword("mysecret");
		user = userDao.update(user);
		
		assertEquals(user.getPassword(),"mysecret");
		
	}
	
	@Test
	public void testGetUsersFound() {
		Integer userId = 1;
		Users user = userDao.get(userId);
		if(user != null) {
			System.out.println(user.getEmail());
		}
		assertNotNull(user);
	}
	
	@Test
	public void testGetUsersNotFound() {
		Integer userId = 100;
		Users user = userDao.get(userId);
		if(user != null) {
			System.out.println(user.getEmail());
		}else {
			System.out.println("User not found");
		}
		assertNull(user);
	}
	
	@Test
	public void testDeleteUsers() {
		Integer userId = 4;
		userDao.delete(userId);
		
		Users users = userDao.get(userId);
		assertNull(users);
	}
	@Test(expected = EntityNotFoundException.class)
	public void testDeleteNonExistantUsers() {
		Integer userId = 4;
		userDao.delete(userId);
		}
	
	@Test
	public void testListAll() {
		List<Users> userList = userDao.listAll();
		assertTrue(userList.size() > 0);
	}
	
	@Test
	public void testCountAll() {
		System.out.println(userDao.count());
		assertTrue(userDao.count() > 0);
	}
	
	@Test
	public void testFindByEmail() {
		String email = "newuser@gmail.com";
		Users user = userDao.findByEmail(email);
		assertEquals(user.getEmail(), email);

	}
	
	@Test
	public void testCheckLogin() {
		String email = "jay@gmail.com";
		String pass = "mysecret";
		boolean login = userDao.checkLogin(email, pass);
		assertTrue(login);

	}
	
	@Test
	public void testCheckLoginFailure() {
		String email = "jay@gmail.com";
		String pass = "mysecrets";
		boolean login = userDao.checkLogin(email, pass);
		assertFalse(login);

	}
	

	@AfterClass
	public static void afterClass() throws Exception {
		BaseDaoTest.tearDownAfterClass();
	}
}
