package com.bookstore.controller.frontend;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import com.bookstore.dao.CategoryDao;

@WebFilter("/*")
public class CommonFilter implements Filter {
	
	private CategoryDao categoryDao;

    public CommonFilter() {
    	categoryDao = new CategoryDao();
    }

	public void destroy() {
	
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String path = httpRequest.getRequestURI();
		if(!path.contains("/admin/")) {
			request.setAttribute("listCategory", categoryDao.listAll());
		}
		
		
		chain.doFilter(request, response);
	}
	
	public void init(FilterConfig fConfig) throws ServletException {

	}

}
