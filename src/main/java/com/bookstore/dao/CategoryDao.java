package com.bookstore.dao;

import java.util.List;

import com.bookstore.entity.Category;

public class CategoryDao extends JpaDao<Category> implements GenericDao<Category> {

	public CategoryDao() {
		super();
		
	}

	@Override
	public Category create(Category category) {
		
		return super.create(category);
	}

	@Override
	public Category update(Category category) {
		return super.update(category);
		
	}

	@Override
	public Category get(Object id) {
		return super.find(Category.class, id);
	}

	@Override
	public void delete(Object id) {
		super.delete(Category.class, id);		
	}

	@Override
	public List<Category> listAll() {
		return super.findWithNamedQuery("Category.findAll");
	}

	@Override
	public long count() {
		
		return super.countWithNamedQuery("Category.countAll");
	}
	
	public Category findByName(String name) {
		
		List<Category> catList = super.findWithNamedQuery("Category.findByName", "name", name);
		
		if(catList != null && catList.size() > 0) {
			return catList.get(0);
		}
		
		return null;
	}

	
	
	
}
