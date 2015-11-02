package com.learnservlet.intro;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Hello
 */

public class Hello extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Hello() {


		super();
		System.out.println("Hei");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init(){
		System.out.println("init is in action");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie[] ck = request.getCookies();
		if(ck!= null){
			System.out.println("Cookie is present");
			System.out.println(" "+request.getContextPath());
			request.getRequestDispatcher("/intro/hellojsp").forward(request, response);
		}else{
			System.out.println("No Cookie is present");
			request.getRequestDispatcher("/index.html").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String name = request.getParameter("user");
		//String pass = request.getParameter("passswrd");

		Cookie[] ck = request.getCookies();
		if(ck == null){
			System.out.println("No Cookie POST");
			/*	PrintWriter out = response.getWriter();
			out.write("Hello\n");
			out.println("content-type:"+request.getContentType());
			out.println("content-length:"+request.getContentLength());*/
			Cookie reqck = new Cookie("JSESSIONID",name);
			reqck.setMaxAge(10);
			
			response.addCookie(reqck);
			response.sendRedirect("http://localhost:8080/LearnServlet/intro/hellojsp");
		}else{
			System.out.println("Cookie is present");

			PrintWriter out = response.getWriter();
			// Setting reponse type
			response.setContentType("text/html");

			out.println("<h3> Request Headers </h3>");


			//Get all headers 
			Enumeration<String> headersName = request.getHeaderNames(); 
			while(headersName.hasMoreElements()){
				String headerName = headersName.nextElement();
				out.println("<br/>"+headerName+":"+request.getHeader(headerName));
			}	

			out.println("<h3>Hello "+name+"<h3>");

			Enumeration<String> names =getServletConfig().getInitParameterNames();
			for(;names.hasMoreElements();){
				String paramName = names.nextElement();
				out.println("param name : "+ paramName+"<br/>");
				out.println("param value : "+ getServletConfig().getInitParameter(paramName)+"<br/>");

			}
			
			out.println(""+getServletContext().getAttribute("db"));
		}
	}

}
