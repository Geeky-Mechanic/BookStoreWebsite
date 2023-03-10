package com.bookstore.dao;

import java.util.Date;
import java.util.List;

import com.bookstore.entity.Book;

public class BookDao extends JpaDao<Book> implements GenericDao<Book>{

	public BookDao() {
		super();
	}

	@Override
	public Book create(Book book) {
		book.setLastUpdateTime(new Date());
		return super.create(book);
	}

	@Override
	public Book update(Book book) {
		book.setLastUpdateTime(new Date());
		return super.update(book);
	}

	@Override
	public Book get(Object id) {
		
		return super.find(Book.class, id);
	}
	
	public Book findByTitle(String title) {
		List<Book> bookList = super.findWithNamedQuery("Book.findByTitle", "title", title);
		if(!bookList.isEmpty()) {
			return bookList.get(0);
		}
		return null;
	}

	@Override
	public void delete(Object id) {
		super.delete(Book.class, id);
		
	}

	@Override
	public List<Book> listAll() {
		return super.findWithNamedQuery("Book.findAll");
	}
	
	public List<Book> listNewBooks() {
		return super.findWithNamedQuery("Book.listNewBooks");
	}

	@Override
	public long count() {
		return super.countWithNamedQuery("Book.countAll");
	}
	
	public List<Book> listBooksByCategory(Integer categoryId){
		return super.findWithNamedQuery("Book.findByCategory", "categoryId", categoryId);
	}
	
	public List<Book> search(String keyword){
		return super.findWithNamedQuery("Book.search", "keyword", keyword);
	}

}
