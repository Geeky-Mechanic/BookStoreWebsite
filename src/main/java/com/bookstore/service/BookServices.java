package com.bookstore.service;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.bookstore.dao.BookDao;
import com.bookstore.dao.CategoryDao;
import com.bookstore.entity.Book;
import com.bookstore.entity.Category;

public class BookServices {

	private EntityManager entityManager;
	private BookDao bookDao;
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	private CategoryDao categoryDao;

	
	
	public BookServices(EntityManager entityManager, HttpServletRequest request,
			HttpServletResponse response) {
		super();
		this.entityManager = entityManager;
		this.bookDao = new BookDao(entityManager);
		this.request = request;
		this.response = response;
		
		this.categoryDao = new CategoryDao(entityManager);
	}



	public void listBooks() throws ServletException, IOException {
		List<Book> bookList = bookDao.listAll();
		
		request.setAttribute("bookList", bookList);
		String listPage = "book_list.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(listPage);
		dispatcher.forward(request, response);
	}
	
	public void showNewBookForm() throws ServletException, IOException {
		List<Category> categoryList = categoryDao.listAll();
		
		request.setAttribute("categoryList", categoryList);
		String page = "book_form.jsp";
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
		
	}
	
	public void createNewBook() throws ServletException, IOException {
		String strCategoryId = request.getParameter("category");
		String title = request.getParameter("bookTitle");
		String author = request.getParameter("author");
		String isbn = request.getParameter("isbn");
		String strPublishDate = request.getParameter("publishDate");
		String strPrice = request.getParameter("price");
		String description = request.getParameter("description");
		Part partImage = request.getPart("image");
		
		
		Integer categoryId;
		float price;
		Date publishDate;
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		
		if(strCategoryId == null || strCategoryId.isEmpty()
				|| title == null || title.isEmpty()
				|| author == null || author.isEmpty()
				|| isbn == null || isbn.isEmpty()
				|| strPublishDate == null || strPublishDate.isEmpty()
				|| strPrice == null || strPrice.isEmpty()
				|| description == null || description.isEmpty()
				|| partImage == null || partImage.getSize() == 0) {
			System.out.println(strCategoryId + title + author + isbn + strPublishDate + strPrice + description);
			String message = "Could not create the book: Some required fields were left blank";
			String page = "message.jsp";
			request.setAttribute("message", message);
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			dispatcher.forward(request, response);
		}else {
			try {
			categoryId = Integer.parseInt(strCategoryId);
			price = Float.parseFloat(strPrice);
			}catch(NumberFormatException e) {
				throw new ServletException("Error parsing numbers, the category ID (" + strCategoryId + ") or the Price (" + strPrice + ") do not represent real numbers");
			}
			long size = partImage.getSize();
			byte[] imageBytes = new byte[(int)size];
			
			InputStream inputStream = partImage.getInputStream();
			inputStream.read(imageBytes);
			inputStream.close();
			
			try {
				publishDate = dateFormat.parse(strPublishDate);
			}catch(ParseException ex) {
				ex.printStackTrace();
				throw new ServletException("Error parsing the publication date, the format is MM/dd/yyyy");
			}
			
			Book newBook = new Book();
			newBook.setAuthor(author);
			newBook.setDescription(description);
			newBook.setIsbn(isbn);
			newBook.setLastUpdateTime(new Date());
			newBook.setPublishDate(publishDate);
			newBook.setPrice(price);
			newBook.setTitle(title);
			newBook.setImage(imageBytes);
			
			Category category = categoryDao.get(categoryId);
			if(category != null) {
				newBook.setCategory(categoryDao.get(categoryId));
			}else {
				String message = "Could not create the book: The category which has an ID of <i>" + categoryId + "</i> does not exist";
				String page = "message.jsp";
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher(page);
				dispatcher.forward(request, response);
			}
			
			Book createdBook = bookDao.create(newBook);
			
			if(createdBook != null && createdBook.getBookId() > 0) {
				String message = "Book created successfully";
				request.setAttribute("message", message);
				listBooks();
			}
			
		}
	}
	
}
