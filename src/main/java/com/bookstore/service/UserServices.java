package com.bookstore.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.dao.UserDao;
import com.bookstore.entity.Users;
import com.bookstore.security.PasswordHashing;

public class UserServices {
	private UserDao userDao;
	private EntityManager entityManager;
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	
	public UserServices(EntityManager entityManager, HttpServletRequest request, HttpServletResponse response) {
		this.entityManager = entityManager;
		userDao = new UserDao(entityManager);
		this.request = request;
		this.response = response;
	}

	
	public void listUsers() throws ServletException, IOException {
		listUsers(null);
	}


	public void listUsers(String message) throws ServletException, IOException {
		List<Users> userList = userDao.listAll();

		//set the attribute to send to the JSP file
		request.setAttribute("listUsers", userList);
		if(message != null) {
			request.setAttribute("message",message );
		}
		//define the JSP file
		String listPage = "user_list.jsp";
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(listPage);
		requestDispatcher.forward(request, response);

	}
	
	public void createNewUser() throws ServletException, IOException {
		String email = request.getParameter("email");
		String fullName = request.getParameter("fullname");
		String password = request.getParameter("password");
		
		
		if(email == null || email.equals("") || fullName == null || fullName.equals("") || password == null || password.equals("")) {
			String message = "Could not update the user: Some fields were left blank";
			sendMessage(message, "message.jsp", "message");
		}else {
			
			Users existingUser = userDao.findByEmail(email);
			
			if(existingUser != null) {
				//create an error message
				String message = "Could not create user. A user with email " + email + " already exists";
				
				sendMessage(message, "message.jsp", "message");
				
			}else {
				try {
					String hashedPassword = PasswordHashing.generateHashString(password);
					Users newUser = new Users(email, hashedPassword, fullName);
					userDao.create(newUser);
					listUsers("New User created succesfully");
				}catch(Exception e) {
					e.printStackTrace();
					String message = "Could not create the user: An error occured, please contact the support if error persists";
					sendMessage(message, "message.jsp", "message");

				}
			}
		}
		
	}


	public void editUser() throws ServletException, IOException {
		int userId = Integer.parseInt(request.getParameter("id"));
		Users user = userDao.get(userId);
		String destPage = "user_form.jsp";
		
		if(user == null) {
			destPage = "message.jsp";
			String message = "Could not update the user: the user with userId '<i>" + userId + "</i>' could not be found";
			request.setAttribute("message", message);
			
		}else {
			Users userNoPassword = new Users(user.getEmail(), user.getFullName(), user.getUserId());
			request.setAttribute("user", userNoPassword);
		}
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(destPage);
		requestDispatcher.forward(request, response);
		
	}


	public void updateUser() throws ServletException, IOException {
		String email = request.getParameter("email");
		String fullName = request.getParameter("fullname");
		String password = request.getParameter("password");
		int userId = Integer.parseInt(request.getParameter("userId"));
		String messageAttribute = "message";
		String page = "message.jsp";
		
		Users userById = userDao.get(userId);
		
		Users userByEmail = userDao.findByEmail(email);
		
		if(email == null || fullName == null || password == null) {
			String message = "Could not update the user: Some fields were left blank";
			sendMessage(message, page, messageAttribute);
			
		}else if(userByEmail != null && userByEmail.getUserId() != userById.getUserId()) {
			String message = "Could not update the user: The email '<i>" + email +  "</i>' is already used by another user";
			sendMessage(message, page, messageAttribute);
			
		}else {
			try {
				String hashedPassword = PasswordHashing.generateHashString(password);
				
				Users user = new Users(email, hashedPassword, fullName, userId);
				
				userDao.update(user);
				
				String message = "User successfully updated";
				
				listUsers(message);
			}catch(Exception e) {
				e.printStackTrace();
				String message = "Could not update the user: An error occured, please contact the support if error persists";
				sendMessage(message, page, messageAttribute);
			}
			
		}
		
	}


	public void deleteUser() throws ServletException, IOException {
		int userId = Integer.parseInt(request.getParameter("userId"));
		String message = "User has been deleted successfully";
		String destPage = "message.jsp";
		if(userDao.get(userId) != null) {
			userDao.delete(userId);
			listUsers(message);
		}else if(userId == 1) {
			message = "The default admin account cannot be deleted";
			sendMessage(message, destPage, "message");
		}else {
			message = "Could not find user with ID "+ userId + ", or it might have been deleted by another admin.";
			sendMessage(message, destPage, "message");
		}
	}
	
	public void login() throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		if(email == null || password == null) {
			String message = "Please enter an email address and/or a password";
			sendMessage(message, "message.jsp", "message");
		}else {
			try {
				String hashedPassword = PasswordHashing.generateHashString(password);
				boolean isLoggedIn = userDao.checkLogin(email, hashedPassword);
				if(isLoggedIn == true) {
					request.getSession().setAttribute("email", email);
								
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("/admin/");
					requestDispatcher.forward(request, response);

				}else {
					String message = "Login failed, try again.";
					request.setAttribute("message", message);
					RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
					dispatcher.forward(request, response);
				}
			}catch(Exception ex) {
				String message = "Could not Login: An error occured, please contact the support if error persists";
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
				dispatcher.forward(request, response);
			
			}
		}
	}
	
	public void sendMessage(String message, String page, String attributeName) throws ServletException, IOException {
		request.setAttribute(attributeName, message);

		RequestDispatcher requestDispatcher = request.getRequestDispatcher(page);
		requestDispatcher.forward(request, response);
	}
	
}
