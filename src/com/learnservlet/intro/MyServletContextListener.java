package com.learnservlet.intro;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyServletContextListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent event) {
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		System.out.println("Context is initialized");
		ServletContext sc = event.getServletContext();
		String contextClass = sc.getInitParameter("dbConnection");
		System.out.println(""+contextClass);
		event.getServletContext().setAttribute("db",contextClass);
		
	}

}
