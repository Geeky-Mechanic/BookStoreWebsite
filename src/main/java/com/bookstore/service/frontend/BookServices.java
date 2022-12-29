package com.bookstore.service.frontend;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.dao.BookDao;
import com.bookstore.dao.CategoryDao;
import com.bookstore.entity.Book;
import com.bookstore.entity.Category;
import com.bookstore.service.MessageServices;

public class BookServices {
	
	private HttpServletResponse response;
	private HttpServletRequest request;
	private CategoryDao categoryDao;
	private BookDao bookDao;

	public BookServices(HttpServletResponse response, HttpServletRequest request) {
		super();
		this.response = response;
		this.request = request;
		this.categoryDao = new CategoryDao();
		this.bookDao = new BookDao();
	}

	public void handlePage() throws ServletException, IOException {
		String homepage = "/frontend/index.jsp";
		
		Map<String, Object> paramsMap = new HashMap<>();
		paramsMap.put("newBooksList", bookDao.listNewBooks());
		
		MessageServices.populatePage(request, response, paramsMap, homepage);
		
	}
	
	
	public void listBooksByCategory() throws ServletException, IOException {
		
		String strCategoryId = request.getParameter("categoryId");
		
		Integer intCategoryId = null;
		String errMessage = "The selected category is not valid";
		if(strCategoryId == null || strCategoryId.isEmpty()) {
			
			response.sendError(HttpServletResponse.SC_NOT_FOUND, errMessage);
		
		}else {
			try {
				intCategoryId = Integer.parseInt(strCategoryId);
			}catch(NumberFormatException e) {
				e.printStackTrace();
				response.sendError(HttpServletResponse.SC_NOT_FOUND, errMessage);
					
			}
			
			List<Book> bookList = bookDao.listBooksByCategory(intCategoryId);
			Category category = categoryDao.get(intCategoryId);
			
			Map<String, Object> paramsMap = new HashMap<>();
			paramsMap.put("category", category);
			paramsMap.put("bookList", bookList);
			
			MessageServices.populatePage(request, response, paramsMap, "frontend/books_list_by_category.jsp");
			
		}
		
	}

	public void viewBookDetails() throws IOException, ServletException {
		String strBookId = request.getParameter("bookId");
		String errMessage = "The selected book is not valid";
		
		Integer intBookId = null;
		
		if(strBookId == null || strBookId.isEmpty()) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND, errMessage);
		}else {
			try {
				intBookId = Integer.parseInt(strBookId);
			}catch(NumberFormatException e) {
				response.sendError(HttpServletResponse.SC_NOT_FOUND, errMessage);
				
			}
			
			Book book = bookDao.get(intBookId);
			
			Map<String, Object> paramsMap = new HashMap<>();
			paramsMap.put("book", book);
			
			MessageServices.populatePage(request, response, paramsMap, "frontend/book_details.jsp");
			
		}
		
	}

	public void search() throws IOException, ServletException {
		String keyword = request.getParameter("keyword");
				
		String page = "frontend/search_results.jsp";

		Map<String, Object> paramsMap = new HashMap<>();
		
		if(keyword == null || keyword.isEmpty()) {
			paramsMap.put("bookList", bookDao.listAll());
			MessageServices.populatePage(request, response, paramsMap, page);
		}else {
			
			List<Book> bookList = bookDao.search(keyword);
			

			paramsMap.put("bookList", bookList);
			paramsMap.put("keyword", keyword);
			
			MessageServices.populatePage(request, response, paramsMap, page);

		}
		
	}
}
