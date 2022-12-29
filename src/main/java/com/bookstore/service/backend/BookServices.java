package com.bookstore.service.backend;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.bookstore.dao.BookDao;
import com.bookstore.dao.CategoryDao;
import com.bookstore.entity.Book;
import com.bookstore.entity.Category;
import com.bookstore.service.MessageServices;

public class BookServices {

	private BookDao bookDao;
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	private CategoryDao categoryDao;

	
	public BookServices(HttpServletRequest request,
			HttpServletResponse response) {
		super();
		this.bookDao = new BookDao();
		this.request = request;
		this.response = response;
		
		this.categoryDao = new CategoryDao();
	}

	public void listBooks(String message) throws ServletException, IOException {
		
		List<Book> bookList = bookDao.listAll();
		
		String listPage = "book_list.jsp";
		
		Map<String, Object> params = new HashMap<>();
		params.put("message", message);
		params.put("bookList", bookList);
		
		MessageServices.populatePage(request, response, params, listPage);

	}

	public void listBooks() throws ServletException, IOException {
		listBooks(null);
	}
	
	public void showNewBookForm() throws ServletException, IOException {
		List<Category> categoryList = categoryDao.listAll();
		
		String page = "book_form.jsp";
		MessageServices.populatePage(request, response, categoryList, "categoryList", page);
		
	}
	
	public void createNewBook() throws ServletException, IOException {

		Book newBook = new Book();
		boolean isParseable = readBook(newBook);
		
		if(newBook != null && isParseable) {
			Book createdBook = bookDao.create(newBook);
			
			if(createdBook != null && createdBook.getBookId() > 0) {
				String message = "Book created successfully";
				listBooks(message);
			}
			
		}		
	}



	public void editBook() throws ServletException, IOException {

		String strId = request.getParameter("bookId");

		Integer intId = null;

		if(strId == null || strId.isEmpty()) {
			String message = "Could not find the book: The id is missing or invalid";
			MessageServices.sendMessage(request, response, message);
		}else {

			try {
				intId = Integer.parseInt(strId);
			}catch(NumberFormatException e) {
				e.printStackTrace();
				String message = "Could not find the book: The id is missing or invalid";
				MessageServices.sendMessage(request, response, message);
				return;
			}

			Book book = bookDao.get(intId);

			if(book == null) {
				String message = "Could not find the book: The id is missing or invalid";
				MessageServices.sendMessage(request, response, message);
			}else {
				
				List<Category> categoryList = categoryDao.listAll();

				for (int i = 0; i < categoryList.size(); i++) {
					if(categoryList.get(i).getCategoryId() == book.getCategory().getCategoryId()) {
						categoryList.remove(book.getCategory());
						categoryList.add(0, book.getCategory());
					}
				}

				Map<String, Object> params =new HashMap<>();
				params.put("book", book);
				params.put("categoryList", categoryList);
				String page = "book_form.jsp";
				MessageServices.populatePage(request, response, params, page);


			}
		}


	}

	
	public void showEditBookForm() throws ServletException, IOException {
		List<Category> categoryList = categoryDao.listAll();
		
		String page = "book_form.jsp";
		
		MessageServices.populatePage(request, response, categoryList, "categoryList", page);
		
	}


	public boolean readBook(Book newBook) throws ServletException, IOException {

		Part partImage = request.getPart("image");
		String strCategoryId = request.getParameter("category");
		String title = request.getParameter("bookTitle");
		String author = request.getParameter("author");
		String isbn = request.getParameter("isbn");
		String strPublishDate = request.getParameter("publishDate");
		String strPrice = request.getParameter("price");
		String description = request.getParameter("description");
		String strBookId = request.getParameter("bookId");



		Integer categoryId = null;
		float price;
		Date publishDate = null;
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		if(strCategoryId == null || strCategoryId.isEmpty()
				|| title == null || title.isEmpty()
				|| author == null || author.isEmpty()
				|| isbn == null || isbn.isEmpty()
				|| strPublishDate == null || strPublishDate.isEmpty()
				|| strPrice == null || strPrice.isEmpty()
				|| description == null || description.isEmpty()) {

			String message = "Could not create the book: Some required fields were left blank";

			MessageServices.sendMessage(request, response, message);
			return false;
		}else {
			
			Book bookFoundByTitle = bookDao.findByTitle(title);
			
			if(bookFoundByTitle != null && !bookFoundByTitle.getBookId().toString().equals(strBookId)) {
				String message = "Could not create the book: A book with the title <i>" + title + "</i> already exists";

				MessageServices.sendMessage(request, response, message);
				return false;
			}else {
				
				try {
					categoryId = Integer.parseInt(strCategoryId);
					price = Float.parseFloat(strPrice);
				}catch(NumberFormatException e) {
					e.printStackTrace();
					MessageServices.sendMessage(request, response, "Error parsing numbers, the category ID (" + strCategoryId + ") or the Price (" + strPrice + ") do not represent real numbers");
					return false;
				}
				long size = partImage.getSize();
				
				if(size > 0) {
					byte[] imageBytes = new byte[(int)size];

					InputStream inputStream = partImage.getInputStream();
					inputStream.read(imageBytes);
					inputStream.close();
					newBook.setImage(imageBytes);
				}
				
				try {
					publishDate = dateFormat.parse(strPublishDate);
				}catch(ParseException ex) {
					ex.printStackTrace();
					MessageServices.sendMessage(request, response, "Error parsing the publication date, the format is MM/dd/yyyy");
					return false;
				}

				newBook.setAuthor(author);
				newBook.setDescription(description);
				newBook.setIsbn(isbn);
				newBook.setLastUpdateTime(new Date());
				newBook.setPublishDate(publishDate);
				newBook.setPrice(price);
				newBook.setTitle(title);

				Category category = categoryDao.get(categoryId);
				if(category != null) {
					newBook.setCategory(categoryDao.get(categoryId));
				}else {
					String message = "Could not create the book: The category which has an ID of <i>" + categoryId + "</i> does not exist";

					MessageServices.sendMessage(request, response, message);
					return false;
				}
				return true;

			}
		}


	}
	

	public void updateBook() throws ServletException, IOException {
		
		String strBookId = request.getParameter("bookId");
		
		Integer intId = null;
		
		if(strBookId == null || strBookId.isEmpty()) {
			String message = "Could not find the book: The id is missing or invalid";
			MessageServices.sendMessage(request, response, message);
		}else {
			try {
				intId = Integer.parseInt(strBookId);
			}catch(NumberFormatException e) {
				e.printStackTrace();
				String message = "Could not find the book: The id is missing or invalid";
				MessageServices.sendMessage(request, response, message);
				return;
			}
			
			Book existingBook = bookDao.get(intId);
			
			boolean isParseable = readBook(existingBook);
			
			if(existingBook != null && isParseable) {
				bookDao.update(existingBook);
				
				String message = "Book updated successfully";
				
				listBooks(message);
			}
			
		}
		
		

		
	}
	

	public void deleteBook() throws ServletException, IOException{
		
		String strBookId = request.getParameter("bookId");
		
		Integer intId = null;
		
		String missingMessage = "Could not delete the book: The ID is missing or invalid";
		
		if(strBookId == null || strBookId.isEmpty()) {
			
			MessageServices.sendMessage(request, response, missingMessage);
		}else {
			try {
				intId = Integer.parseInt(strBookId);
			}catch(NumberFormatException e) {
				e.printStackTrace();
				MessageServices.sendMessage(request, response, missingMessage);
				return;				
			}
			
			if(bookDao.get(intId) == null) {
				MessageServices.sendMessage(request, response, missingMessage);
			}else {
				bookDao.delete(intId);
				String message = "Book successfully deleted";
				listBooks(message);
				
			}
			
		}
		
	}


}
