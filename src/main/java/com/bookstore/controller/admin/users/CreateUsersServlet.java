package com.bookstore.controller.admin.users;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.service.backend.UserServices;

@WebServlet("/admin/create_user")
public class CreateUsersServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
    
    public CreateUsersServlet() {
        super();
    }
    
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	RequestDispatcher dispatcher = req.getRequestDispatcher("user_form.jsp");
    	dispatcher.forward(req, resp);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserServices userServices = new UserServices(request, response);
		userServices.createNewUser();
		
		}

}
