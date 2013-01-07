package com.vlms.sjsu.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.vlms.sjsu.entity.Movie;
import com.vlms.sjsu.entity.User;
import com.vlms.sjsu.service.ServiceProxy;

/**
 * Servlet implementation class for Servlet: UserdetailsAction
 * 
 */
public class UserdetailsAction extends javax.servlet.http.HttpServlet implements
		javax.servlet.Servlet {
	static final long serialVersionUID = 1L;
	ServiceProxy proxy = new ServiceProxy();

	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public UserdetailsAction() {
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
		System.out.println("in servlet of showuser");
		try {
			proxy.setEndpoint("http://localhost:8080/VLMS/services/Service");
			System.out.println("in userdetails action");
			HttpSession session = request.getSession();
			// User user = (User) (request.getSession().getAttribute("user"));
			User user = new User();
			if( request.getParameter("userhidden") == null)
				System.out.println("IS NULL ::::");
			int userid=Integer.parseInt( request.getParameter("userhidden"));
			System.out.println("user hiden valu passed"+userid);
			user = proxy.getUserDetails(userid);
			System.out.println("user details---" + user.getEmail());
			session.setAttribute("userdetailsobject", user);
			Movie movie[];
			movie=proxy.getUserMovieslist(userid);
			//System.out.println("movies got from user--"+movie[0].getMovieName());
			session.setAttribute("usermovielist", movie);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		String nextJSP = "/View/userDetail.jsp";
		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher(nextJSP);
		dispatcher.forward(request, response);
	}

}