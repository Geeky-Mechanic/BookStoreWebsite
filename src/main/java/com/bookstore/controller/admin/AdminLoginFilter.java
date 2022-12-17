 package com.bookstore.controller.admin;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@WebFilter("/admin/*")
public class AdminLoginFilter implements Filter {

	
	public AdminLoginFilter() {
    
	}

	public void destroy() {
	
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
	
		HttpServletRequest req = (HttpServletRequest) request;
		//if false, don't create a new session
		HttpSession session = req.getSession(false);
		
		boolean loggedIn = session != null && session.getAttribute("email") != null;
		
		String loginUrl = req.getContextPath() + "/admin/login";
		boolean isLogInReq = req.getRequestURI().equals(loginUrl);
		if(loggedIn && isLogInReq) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/");
			dispatcher.forward(request, response);
		}else if (loggedIn || isLogInReq) {
			chain.doFilter(request, response);
		}else {
			request.setAttribute("message", "You must be logged in to proceed");
			RequestDispatcher dispatcher = request.getRequestDispatcher("login");
			dispatcher.forward(request, response);
		}
		
	}

	public void init(FilterConfig fConfig) throws ServletException {
	
		
	
	}

}
