package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Login extends HttpServlet {
	
	// Now from the loginview.jsp we pass a post request, so if we just run this URL in new tab after the login with post
	// http://localhost:8080/Servlet_7_SessionManagement/login
	// This is just a get request, so the username stored in the session, we just check if it is there and then we redirect
	// to home.jsp without reentering the username or password
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		if (session.getAttribute("data") != null) {;
			request.getRequestDispatcher("/WEB-INF/view/home.jsp").forward(request, response);
			
		} else {
			request.getRequestDispatcher("/WEB-INF/view/loginview.jsp").forward(request, response);
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = (String) request.getParameter("username");
		String password = (String) request.getParameter("password");
		
		// We had hardcoded the values of username and password here
		// So if it matches then we create a HttpSession and stores the username in it.
		// And then redirect to the home.jsp
		
		if (username.equals("raju") && password.equals("123456")) {
			HttpSession session = request.getSession();
			session.setAttribute("data", username);
			
			request.getRequestDispatcher("/WEB-INF/view/home.jsp").forward(request, response);
			
		} 
		
		// If the username or password does not matche, then redirect to the loginview.jsp
		else {
			request.getRequestDispatcher("/WEB-INF/view/loginview.jsp").forward(request, response);
			
		}
	}
}
