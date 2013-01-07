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
 * Servlet implementation class AdminSearchMovie
 */
public class AdminSearchMovie extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ServiceProxy proxy = new ServiceProxy();  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminSearchMovie() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(null==request.getAttribute("AdminSearchMovie"))
		{
			proxy.setEndpoint("http://localhost:8080/VLMS/services/Service");
			Movie[] searchedMovies;
							
			//System.out.println("call to create update user web method ");
			proxy.setEndpoint("http://localhost:8080/VLMS/services/Service");
			int count=0;
			
			if(request.getParameter("selCount").compareToIgnoreCase("all") !=0)
			count= Integer.parseInt(request.getParameter("selCount"));
			else
			count = 0;
			
			searchedMovies = proxy.listAllMovies(count);		
			request.setAttribute("AdminSearchMovie", searchedMovies);
		
		}
	
			String nextJSP = "/View/AdminSearchMovie.jsp";
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
			dispatcher.forward(request, response);
		
	}

}
