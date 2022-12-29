package com.bookstore.controller.admin.books;

import com.bookstore.service.backend.BookServices;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/admin/edit_book")
@MultipartConfig(
		fileSizeThreshold = 1024 * 1024,//10KB
		maxFileSize = 1024 * 1024 * 5, //300KB
		maxRequestSize = 1024 * 1024 * 5 * 5 //1MB
		)
public class EditBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public EditBookServlet() {

    }

    //TODO CHANGE URL TO THIS URL
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BookServices bookServices = new BookServices(request, response);
		bookServices.editBook();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BookServices bookServices = new BookServices(request, response);
		bookServices.updateBook();
	}

}
