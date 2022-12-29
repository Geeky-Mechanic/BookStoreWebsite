package com.bookstore.dao;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Book;
import com.bookstore.entity.Category;

public class BookDaoTests {

	private static BookDao bookDao;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		bookDao = new BookDao();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		bookDao.close();
	}

	@Test
	public void testCreateBook() throws ParseException, IOException {
		Book book = new Book();
		Category category = new Category("JavaEE");
		category.setCategoryId(4);
		book.setCategory(category);
		
		book.setAuthor("Joshua Bloch");
		book.setTitle("Effective Java (2nd Edition)");
		book.setDescription("Are you looking for a deeper understanding of the Java programming language so that you can write code that is clearer, more correct, more robust, and more reusable? Look no further! Effective Java, Second Edition, brings together seventy-eight indispensable programmers rules of thumb: working, best-practice solutions for the programming challenges you encounter every day.\n"
				+ " \n"
				+ "This highly anticipated new edition of the classic, Jolt Award-winning work has been thoroughly updated to cover Java SE 5 and Java SE 6 features introduced since the first edition. Bloch explores new design patterns and language idioms, showing you how to make the most of features ranging from generics to enums, annotations to autoboxing.\n"
				+ " \n"
				+ "Each chapter in the book consists of several items presented in the form of a short, standalone essay that provides specific advice, insight into Java platform subtleties, and outstanding code examples. The comprehensive descriptions and explanations for each item illuminate what to do, what not to do, and why.\n"
				+ " \n"
				+ "Highlights include:\n"
				+ "New coverage of generics, enums, annotations, autoboxing, the for-each loop, varargs, concurrency utilities, and much more\n"
				+ "Updated techniques and best practices on classic topics, including objects, classes, libraries, methods, and serialization\n"
				+ "How to avoid the traps and pitfalls of commonly misunderstood subtleties of the language\n"
				+ "Focus on the language and its most fundamental libraries: java.lang, java.util, and, to a lesser extent, java.util.concurrent and java.io\n"
				+ "Simply put, Effective Java, Second Edition, presents the most practical, authoritative guidelines available for writing efficient, well-designed programs.\n"
				+ "");
		book.setIsbn("0321356683");
		book.setPrice(38.87f);
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date publishDate = dateFormat.parse("05/28/2008");
		book.setPublishDate(publishDate);
		
		String imagePath = "/home/jeremy/Desktop/Udemy-Java-Projects/books/Effective Java.jpg";
		byte[] imageBytes = Files.readAllBytes(Paths.get(imagePath));
		book.setImage(imageBytes);
		
		Book createdBook = bookDao.create(book);
		assertTrue(createdBook.getBookId() > 0);
	}

	@Test
	public void testCreateSecondBook() throws ParseException, IOException {
		Book book = new Book();
		Category category = new Category("JavaEE");
		category.setCategoryId(4);
		book.setCategory(category);
		
		book.setAuthor("Raoul-Gabriel Urma, Mario Fusco, Alan Mycroft");
		book.setTitle("Java 8 in Action: Lambdas, Streams, and functional-style programming");
		book.setDescription("Java 8 in Action is a clearly written guide to the new features of Java 8. The book covers lambdas, streams, and functional-style programming. With Java 8's functional features you can now write more concise code in less time, and also automatically benefit from multicore architectures. It's time to dig in!\n"
				+ "");
		book.setIsbn("1617291994");
		book.setPrice(36.72f);
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date publishDate = dateFormat.parse("08/28/2014");
		book.setPublishDate(publishDate);
		
		String imagePath = "/home/jeremy/Desktop/Udemy-Java-Projects/books/Java 8 in Action.jpg";
		byte[] imageBytes = Files.readAllBytes(Paths.get(imagePath));
		book.setImage(imageBytes);
		
		Book createdBook = bookDao.create(book);
		assertTrue(createdBook.getBookId() > 0);
	}
	
	@Test
	public void testUpdateBook() throws ParseException, IOException {
		Book book = new Book();
		Category category = new Category("Advanced Java");
		book.setBookId(1);
		category.setCategoryId(14);
		book.setCategory(category);
		
		book.setAuthor("Joshua Bloch");
		book.setTitle("Effective Java (3rd Edition)");
		book.setDescription("Are you looking for a deeper understanding of the Java programming language so that you can write code that is clearer, more correct, more robust, and more reusable? Look no further! Effective Java, Second Edition, brings together seventy-eight indispensable programmers rules of thumb: working, best-practice solutions for the programming challenges you encounter every day.\n"
				+ " \n"
				+ "This highly anticipated new edition of the classic, Jolt Award-winning work has been thoroughly updated to cover Java SE 5 and Java SE 6 features introduced since the first edition. Bloch explores new design patterns and language idioms, showing you how to make the most of features ranging from generics to enums, annotations to autoboxing.\n"
				+ " \n"
				+ "Each chapter in the book consists of several items presented in the form of a short, standalone essay that provides specific advice, insight into Java platform subtleties, and outstanding code examples. The comprehensive descriptions and explanations for each item illuminate what to do, what not to do, and why.\n"
				+ " \n"
				+ "Highlights include:\n"
				+ "New coverage of generics, enums, annotations, autoboxing, the for-each loop, varargs, concurrency utilities, and much more\n"
				+ "Updated techniques and best practices on classic topics, including objects, classes, libraries, methods, and serialization\n"
				+ "How to avoid the traps and pitfalls of commonly misunderstood subtleties of the language\n"
				+ "Focus on the language and its most fundamental libraries: java.lang, java.util, and, to a lesser extent, java.util.concurrent and java.io\n"
				+ "Simply put, Effective Java, Second Edition, presents the most practical, authoritative guidelines available for writing efficient, well-designed programs.\n"
				+ "");
		book.setIsbn("0321356683");
		book.setPrice(40f);
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date publishDate = dateFormat.parse("05/28/2008");
		book.setPublishDate(publishDate);
		
		String imagePath = "/home/jeremy/Desktop/Udemy-Java-Projects/books/Effective Java.jpg";
		byte[] imageBytes = Files.readAllBytes(Paths.get(imagePath));
		book.setImage(imageBytes);
		
		Book updatedBook = bookDao.update(book);
		Integer expected = 1;
		System.out.println(updatedBook.getTitle());
		assertEquals(expected, updatedBook.getBookId());
	
	}

	@Test
	public void testGet() {
		Book book = bookDao.get(Integer.valueOf(2));
		assertNotNull(book);
	}
	
	@Test
	public void testGetFail() {
		Integer bookId = 99;
		Book book = bookDao.get(bookId);
		assertNull(book);
	}
	
	@Test
	public void testFindByTitle() {
		Book book = bookDao.findByTitle("Java 8 in Action: Lambdas, Streams, and functional-style programming");
		assertNotNull(book);
	}
	
	@Test
	public void testFindByTitleFail() {
		Book book = bookDao.findByTitle("Java");
		assertNull(book);
	}

	@Test(expected = EntityNotFoundException.class)
	public void testDeleteObjectFail() {
		Integer bookId = 100;
		bookDao.delete(bookId);
	}
	@Test
	public void testDeleteObjectSuccess() {
		Integer bookId = 1;
		bookDao.delete(bookId);
		assertTrue(true);
	}

	@Test
	public void testListAll() {
		bookDao.listAll().stream().forEach(book -> {
			System.out.println(book.getTitle());
		});
		assertTrue(bookDao.listAll().size() > 0);
	}

	@Test
	public void testCount() {
		System.out.println(bookDao.count());
		assertEquals(2l,bookDao.count());
	}
	
	@Test
	public void testListByCategory() {
		List<Book> catList = bookDao.listBooksByCategory(14);
		for (Book book : catList) {
			System.out.println(book.toString());
		}
		assertTrue(catList.size() > 0);
	}
	
	@Test
	public void testListNewBooks() {
		List<Book> bookList = bookDao.listNewBooks();
		
		for(Book book : bookList) {
			System.out.println(book.toString());
		}
		
		assertTrue(bookList.size() == 4);
	}
	
	@Test
	public void testSearch() {
		List<Book> bookList = bookDao.search("The Big Picture uniquely explores the entire Java EE");
		
		for(Book book : bookList) {
			System.out.println(book.toString());
		}
		
		assertTrue(bookList.size() > 0);
		
	}

}
