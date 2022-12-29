package com.bookstore.controller.admin.categories;

import com.bookstore.service.backend.CategoryServices;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/admin/create_category")
public class CreateCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public CreateCategoryServlet() {

    	    	
    }
    
    //Redirect to creation form
    
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	
    	RequestDispatcher dispatcher = req.getRequestDispatcher("category_form.jsp");
    	dispatcher.forward(req, resp);
    
    }
    
    //handle the creation of the category
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		CategoryServices categoryServices = new CategoryServices(request, response);
		categoryServices.createCategory();
	
	
	}

}
