package com.vlms.sjsu.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.vlms.sjsu.service.ServiceProxy;

/**
 * Servlet implementation class DeleteMovieAction
 */
public class DeleteMovieAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ServiceProxy proxy = new ServiceProxy();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteMovieAction() {
        super();
        // TODO Auto-generated constructor stub
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
		response.setContentType("text/html");
		proxy.setEndpoint("http://localhost:8080/VLMS/services/Service");
		HttpSession session = request.getSession(true);
		String nextJSP = "";

		if(null == session.getAttribute("user"))
			nextJSP = "/View/accessControl.jsp";

		else{
			String movieID = request.getParameter("movieID");
			String movieName = request.getParameter("movieName");
			System.out.println("INSIDE 1 :::::");

			String qdone = proxy.deleteMovie(movieID);

			if (qdone.equalsIgnoreCase("true")) {
				System.out.println("INSIDE 2 :::::");
				request.setAttribute("message", "Deleted "+movieName+" from Database!");
			}
			else{
				System.out.println("INSIDE 3 :::::");
				request.setAttribute("message",
						"Unable to Delete Movie! : " + qdone);
			}

			nextJSP = "/View/AdminHome.jsp";
		}
		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher(nextJSP);
		dispatcher.forward(request, response);

	}

}
