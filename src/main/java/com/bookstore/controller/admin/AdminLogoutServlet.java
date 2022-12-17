package com.bookstore.controller.admin;

import com.bookstore.controller.BaseServlet;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/admin/logout")
public class AdminLogoutServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	
	public AdminLogoutServlet() {

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		session.removeAttribute("email");
		
		String message = "Logout successful";
		request.setAttribute("logout", message);
		RequestDispatcher dispatcher = request.getRequestDispatcher("login");
		dispatcher.forward(request, response);
		
	}
	

}
