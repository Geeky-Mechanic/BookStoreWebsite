package com.bookstore.entity;
// Generated Nov. 24, 2022, 8:37:25 p.m. by Hibernate Tools 4.3.6.Final

import javax.persistence.*;

import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Category generated by hbm2java
 */
@Entity
@Table(name = "category", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
@NamedQueries({
	@NamedQuery(name="Category.findAll", query = "SELECT c FROM Category c ORDER BY c.name"),
	@NamedQuery(name="Category.countAll", query="SELECT COUNT(c) FROM Category c"),
	@NamedQuery(name="Category.findByName", query="SELECT c FROM Category c WHERE c.name = :name")
})
public class Category implements java.io.Serializable {

	private Integer categoryId;
	private String name;
	private Set<Book> books = new HashSet<Book>(0);

	public Category() {
	}
	
	public Category(String name, Integer categoryId) {
		this.name = name;
		this.categoryId = categoryId;
	}

	public Category(String name) {
		this.name = name;
	}

	public Category(String name, Set<Book> books) {
		this.name = name;
		this.books = books;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "category_id", unique = true, nullable = false)
	public Integer getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	@Column(name = "name", unique = true, nullable = false, length = 30)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
	public Set<Book> getBooks() {
		return this.books;
	}

	public void setBooks(Set<Book> books) {
		this.books = books;
	}

}