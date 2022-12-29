package com.bookstore.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Category;

public class CategoryDaoTest {

	private static CategoryDao categoryDao;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		categoryDao = new CategoryDao();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		categoryDao.close();
	}

	@Test
	public void testCreateCategory() {
		Category newCat = new Category("Javascript");
		Category persistedCat = categoryDao.create(newCat);
		
		assertTrue(persistedCat != null && persistedCat.getCategoryId() > 0);
	}

	@Test
	public void testUpdateCategory() {
		Category newCat = new Category("Java Advanced Examples");
		newCat.setCategoryId(1);
		Category upd = categoryDao.update(newCat);
		
		assertEquals(newCat.getName(), upd.getName() );
	}

	@Test
	public void testGet() {
		Integer catId = 1;
		Category cat = categoryDao.get(catId);
		assertEquals(catId, cat.getCategoryId());
	}

	@Test
	public void testDeleteObject() {
		Integer catId = 1;
		categoryDao.delete(catId);
		assertNull(categoryDao.get(catId));
	}

	@Test
	public void testListAll() {
		List<Category> cat = categoryDao.listAll();
		assertTrue(cat.size() > 0);
	}

	@Test
	public void testCount() {
		Long totalCat = categoryDao.count();
		assertTrue(totalCat == 4);
	}
	
	@Test
	public void testfindByName() {
		String name = "Java";
		Category found = categoryDao.findByName(name);
		assertTrue(found != null);
	}
	@Test
	public void testfindByNameNotFound() {
		String name = "Javas";
		Category found = categoryDao.findByName(name);
		assertTrue(found == null);
	}

}
