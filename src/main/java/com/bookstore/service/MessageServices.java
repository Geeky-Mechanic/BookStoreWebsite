package com.bookstore.service;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.dao.CategoryDao;

public class MessageServices {

	
	public MessageServices() {

	}
	
	
	public static void sendMessage(HttpServletRequest request, HttpServletResponse response ,String message) throws ServletException, IOException {
		request.setAttribute("message", message);

		RequestDispatcher requestDispatcher = request.getRequestDispatcher("message.jsp");
		requestDispatcher.forward(request, response);
	}
	
	public static void populatePage(HttpServletRequest request, HttpServletResponse response ,Object attribute, String attributeName, String page) throws ServletException, IOException {
		request.setAttribute(attributeName, attribute);

		RequestDispatcher requestDispatcher = request.getRequestDispatcher(page);
		requestDispatcher.forward(request, response);
	}
	
	public static void populatePage(HttpServletRequest request, HttpServletResponse response ,Map<String, Object> attributesMap, String page) throws ServletException, IOException {
		Set<Entry<String, Object>> attributes = attributesMap.entrySet();
		
		for (Entry<String, Object> entry : attributes) {
    		request.setAttribute(entry.getKey(), entry.getValue());
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
		
	}
	
}
