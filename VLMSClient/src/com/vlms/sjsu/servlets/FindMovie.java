package com.vlms.sjsu.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vlms.sjsu.entity.Movie;
import com.vlms.sjsu.service.ServiceProxy;

/**
 * Servlet implementation class FindMovie
 */
public class FindMovie extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ServiceProxy proxy = new ServiceProxy();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FindMovie() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		proxy.setEndpoint("http://localhost:8080/VLMS/services/Service");
		Movie objMovie = new Movie();
		Movie[] searchedMovies;

		if (	!"".equalsIgnoreCase(request.getParameter("mvcategory"))
				|| !"".equalsIgnoreCase(request.getParameter("mvproduction"))
				|| !"".equalsIgnoreCase(request.getParameter("mvname"))
				|| !"".equalsIgnoreCase(request.getParameter("mvdate"))) 
		{
			objMovie.setCategory(request.getParameter("mvcategory"));
			request.setAttribute("savedCategory", request.getParameter("mvcategory"));
			
			objMovie.setMovieBanner(request.getParameter("mvproduction"));
			request.setAttribute("savedBanner", request.getParameter("mvproduction"));
			
			objMovie.setMovieName(request.getParameter("mvname"));
			request.setAttribute("savedName", request.getParameter("mvname"));
			
			objMovie.setReleaseDate(request.getParameter("mvdate"));
			request.setAttribute("savedDate", request.getParameter("mvdate"));
			

			System.out.println("call to create update user web method ");
			proxy.setEndpoint("http://localhost:8080/VLMS/services/Service");

			searchedMovies = proxy.findMovie(objMovie);
			request.setAttribute("SearchMovie", searchedMovies);
		
			if(searchedMovies==null)
			{
					request.setAttribute("message","Sorry, no results found. Please try again.");	
			}
			else
			{
					StringBuffer searchString = new StringBuffer();
					if(!"".equalsIgnoreCase(request.getParameter("mvcategory"))){
						searchString.append(request.getParameter("mvcategory") + ",");
					}
					if(!"".equalsIgnoreCase(request.getParameter("mvproduction"))){
						searchString.append(request.getParameter("mvproduction") + ",");
					}
					if(!"".equalsIgnoreCase(request.getParameter("mvname"))){
						searchString.append(request.getParameter("mvname") + ",");
					}
					if(!"".equalsIgnoreCase(request.getParameter("mvdate"))){
						searchString.append(request.getParameter("mvdate") + ",");
					}
					
					String finalSearchString = searchString.toString().substring(0,searchString.toString().lastIndexOf(","));
					request.setAttribute("message1","Search parameters : " + finalSearchString.toString());
			}
		
		
		} 
		else {
			request.setAttribute("message","Please enter atleast one search field.");
			System.out.println("No values, NO call to web method");
			searchedMovies = null;
			request.setAttribute("SearchMovie", searchedMovies);
		}

		String nextJSP = "/View/FindMovie.jsp";
		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher(nextJSP);
		dispatcher.forward(request, response);

	}
}
