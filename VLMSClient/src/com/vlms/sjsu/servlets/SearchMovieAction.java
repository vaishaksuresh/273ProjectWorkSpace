//Venkat's work
package com.vlms.sjsu.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.vlms.sjsu.entity.User;
import com.vlms.sjsu.entity.Movie;
import com.vlms.sjsu.service.ServiceProxy;

/**
 * Servlet implementation class for Servlet: SearchMovieAction
 * 
 */
public class SearchMovieAction extends javax.servlet.http.HttpServlet implements
		javax.servlet.Servlet {
	static final long serialVersionUID = 1L;
	ServiceProxy proxy = new ServiceProxy();

	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public SearchMovieAction() {
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
		System.out.println("in serach movieaction");
		try {
			proxy.setEndpoint("http://localhost:8080/VLMS/services/Service");
			HttpSession session = request.getSession();
			Movie movie = new Movie();
			System.out.println("SD Before :::::::");
			int movid=Integer.parseInt((request.getParameter("movieId")));
			movie = proxy.getMovieDetails(movid);
			System.out.println("SUD after :::::::::::");
			System.out.println("succesfully got movie in searchmovieaction");
			session.setAttribute("moviedetail", movie);
			System.out.println("movie name list---"+movie.getMovieName());
			User user[];
			System.out.println("going to get username list");
			user=proxy.movierenteduserlist(movid);
			//System.out.println("username list size:---"+user.length);
			//session.setAttribute("usernamelength", user.length);
			System.out.println("succesfully got user details in searchmovieaction");
//			System.out.println("username list:--"+user);
			session.setAttribute("movierentuser", user);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		String nextJSP = "/View/showMovieDetail.jsp";
		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher(nextJSP);
		dispatcher.forward(request, response);
	}
}