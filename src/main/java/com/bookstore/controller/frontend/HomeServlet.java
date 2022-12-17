package com.bookstore.controller.frontend;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.controller.BaseServlet;
import com.bookstore.dao.CategoryDao;
import com.bookstore.entity.Category;


@WebServlet("")
public class HomeServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
    public HomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String homepage = "/frontend/index.jsp";
		CategoryDao categoryDao = new CategoryDao(entityManager);
		List<Category> catList = categoryDao.listAll();
		request.setAttribute("listCategory", catList);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(homepage);
		requestDispatcher.forward(request, response);
	}

}
