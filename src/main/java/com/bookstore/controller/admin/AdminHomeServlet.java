package com.bookstore.controller.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.controller.BaseServlet;

@WebServlet("/admin/")
public class AdminHomeServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminHomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String adminHomePage = "index.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(adminHomePage);
		requestDispatcher.forward(request, response);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
