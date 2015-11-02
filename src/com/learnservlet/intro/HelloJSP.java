package com.learnservlet.intro;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class HelloJSP extends HttpServlet{

	/**
	 * Generated serial key
	 */
	private static final long serialVersionUID = 8169170080182425285L;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HelloJSP() {


		super();
		//System.out.println("Hei");
		// TODO Auto-generated constructor stub
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		System.out.println("I am Get");
		RequestDispatcher dispatcher = request.getRequestDispatcher("/hellojsp.jsp");
		if(request.getCookies() == null){
		System.out.println(" Cookies is not present");
			response.sendRedirect("http://localhost:8080/LearnServlet/intro");
		}else{
			dispatcher.forward(request, response);	
		}

	}
	/**
	 * Logout the session
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		System.out.println("I am post Jsp");
		// Dont Forget to make the response as html because while handling this 
		response.setContentType("text/html");
		Cookie[] cookies = request.getCookies();
		if(cookies != null){
			for(Cookie cookie :cookies){
				System.out.println(""+cookie.getValue());
				cookie.setMaxAge(0);
				cookie.setValue(null);
				cookie.setPath("/");
				response.addCookie(cookie);
			}
		}
		request.getSession().invalidate();
	//	request.getRequestDispatcher("/index.html").forward(request, response);
	}


}
