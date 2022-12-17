package com.bookstore.service;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder.In;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.dao.CategoryDao;
import com.bookstore.entity.Category;

public class CategoryServices {
	private EntityManager entityManager;
	private CategoryDao categoryDao;
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	
	public CategoryServices(EntityManager entityManager, HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.entityManager = entityManager;
		categoryDao = new CategoryDao(entityManager);
	}
	
	public void listCategory(String message) throws ServletException, IOException {
		List<Category> catList = categoryDao.listAll();
		request.setAttribute("listCategories", catList);
		
		if(message != null) {
			request.setAttribute("message", message);
		}
		
		//define the JSP file
		String listPage = "category_list.jsp";
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(listPage);
		requestDispatcher.forward(request, response);
	}
	
	public void listCategory() throws ServletException, IOException {
		listCategory(null);

	}
	
	
	
	public void createCategory() throws ServletException, IOException {
		
		String name = request.getParameter("name");
		Category found = categoryDao.findByName(name);
		
		if(found != null) {
			String message = "A category with the name : " + name + " already exists.";
			request.setAttribute("message", message);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("message.jsp");
			requestDispatcher.forward(request, response);
			
		}else if(name.length() == 0) {
			String message = "Could not create the category: The name cannot be empty.";
			request.setAttribute("message", message);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("message.jsp");
			requestDispatcher.forward(request, response);
			
		}
		else {
			
			Category newCategory = new Category(name);
			categoryDao.create(newCategory);
			listCategory("Category succesfully created!");
			
		}
		
	}

	public void editCategory() throws ServletException, IOException {
		Integer id = Integer.parseInt(request.getParameter("id"));
		
		Category category = categoryDao.get(id);
		String destPage = "category_form.jsp";
		
		if(category == null) {
			destPage = "message.jsp";
			String message = "Could not update the category : The category with an ID of <i>" + id + "</i> could not be found";
			request.setAttribute("message", message);
		}else {
			request.setAttribute("category", category);
		}
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(destPage);
		requestDispatcher.forward(request, response);
	}
	
	

	public void updateCategory() throws ServletException, IOException {
		Integer id = Integer.parseInt(request.getParameter("categoryId"));
		String name = request.getParameter("name");
		Category categoryById = categoryDao.get(id);
		Category categoryByName = categoryDao.findByName(name);
		
		if (categoryByName != null && categoryById.getCategoryId() != categoryByName.getCategoryId()) {
			String destPage = "message.jsp";
			String message = "Could not update the category : There is already a category with a name of <i>" + name + "</i>.";;
			request.setAttribute("message", message);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(destPage);
			requestDispatcher.forward(request, response);
		}else if(name.length() == 0) {
			String message = "Could not update the category: The name cannot be empty.";
			request.setAttribute("message", message);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("message.jsp");
			requestDispatcher.forward(request, response);
			
		}else {
			Category newCat = new Category(name, id);
			
			categoryDao.update(newCat);
			
			String message = "Category successfully updated";
			listCategory(message);
		}
		
	}

	public void deleteCategory() throws ServletException, IOException {
		Integer categoryId = Integer.parseInt(request.getParameter("categoryId"));
		
		if (categoryDao.get(categoryId) != null) {
			categoryDao.delete(categoryId);
			listCategory("Category deleted successfully");
		}else {
			String message = "Could not delete the category: The category with an id of <i>"+ categoryId + "</id> could not be found";
			String destPage = "message.jsp";
			request.setAttribute("message", message);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(destPage);
			requestDispatcher.forward(request, response);
		}
		
	}
	
	
}
