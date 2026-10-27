package com.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyFilter extends HttpFilter implements Filter {
	// The filters are used to filter the request and the response
	// At very first the request goes through filter and same the response also goes through filter before reaching client
	// The mapping of this filter is done in web.xml
	// '/*' means every request goes through this filter, and '/login-view' means before calling LoginView servlet this 
	// filter is called.

	public void destroy() {
		// This is used to de-allocate the resources
		// This runs right before the response is send to the client
		
		System.out.println("Response is filtered...");
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		String header = req.getHeader("User-Agent").toLowerCase();
		System.out.println(header);
		
		// This condition tells that only Chrome browser will be able to run this application
		if(!header.contains("chrome")) {
			res.getWriter().print("Invalid Browser");
			return;
		}
		
		// Time at which the filter handed the request to the next filter or first Servlet
		long t1 = System.nanoTime();
		
		// This below line hands the request and response obj to the next filter or first Servlet
		chain.doFilter(request, response);
		
		// The request to the client again goes through the filter
		// Time at which the request is processed and response is send to the client
		
		long t2 = System.nanoTime();
		
		System.out.println("Total time: " + (t2 - t1) + " ns");
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// This is to initialize the Filter
		// This runs only once
		// This is used to allocate any resource like connection, etc.
		
		System.out.println("Filter Inititalized...");
	}

}
