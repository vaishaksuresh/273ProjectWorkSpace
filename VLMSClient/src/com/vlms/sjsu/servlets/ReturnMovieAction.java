package com.vlms.sjsu.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.vlms.sjsu.entity.User;
import com.vlms.sjsu.service.ServiceProxy;

/**
 * Servlet implementation class for Servlet: GenerateBillAction
 * 
 */
public class ReturnMovieAction extends javax.servlet.http.HttpServlet implements
		javax.servlet.Servlet {
	static final long serialVersionUID = 1L;
	ServiceProxy proxy = new ServiceProxy();

	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public ReturnMovieAction() {
		super();
	}

	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request,
	 * HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request,
	 * HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		try {
			HttpSession session = request.getSession();
			User objUser = (User) session.getAttribute("user");
			String movieID = (String) request.getParameter("movieID");
			System.out.println(movieID);
			proxy.setEndpoint("http://localhost:8080/VLMS/services/Service");
			String movie = proxy.returnMovie(Integer.parseInt(movieID),objUser.getUserId());
			String message = "";
			if (movie.equalsIgnoreCase("Movie already removed")) {
				message = "Movie already removed";
			} else {
				message = "Movie removed";
			}
			request.setAttribute("message", message);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String nextJSP = "/View/HomeAction";
		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher(nextJSP);
		dispatcher.forward(request, response);
	}
}