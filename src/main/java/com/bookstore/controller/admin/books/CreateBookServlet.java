package com.bookstore.controller.admin.books;

import com.bookstore.controller.BaseServlet;
import com.bookstore.service.BookServices;

import jakarta.servlet.annotation.MultipartConfig;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/admin/create_book")
@MultipartConfig(
		fileSizeThreshold = 1024 * 1024,//10KB
		maxFileSize = 1024 * 1024 * 5, //300KB
		maxRequestSize = 1024 * 1024 * 5 * 5 //1MB
		)
public class CreateBookServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BookServices bookServices = new BookServices(entityManager, request, response);
		System.out.println("");
		bookServices.showNewBookForm();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BookServices bookServices = new BookServices(entityManager, request, response);
		System.out.println(request.getContentLength());
		bookServices.createNewBook();
	}

}
