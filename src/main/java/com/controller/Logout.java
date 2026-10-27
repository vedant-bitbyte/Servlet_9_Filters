package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

public class Logout extends HttpServlet {
	
	// This is the Logout Servlet, so after we logout, we remove the username form the session object
	// So after again running the login URL
	// http://localhost:8080/Servlet_7_SessionManagement/login
	// We had to again enter the username and password
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("data");
		
		request.getRequestDispatcher("index.html").forward(request, response);
		
	}
}
