package com.vlms.sjsu.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.vlms.sjsu.entity.Movie;
import com.vlms.sjsu.entity.User;
import com.vlms.sjsu.service.ServiceProxy;

/**
 * Servlet implementation class IssueMovieAction
 */
public class IssueMovieAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ServiceProxy proxy = new ServiceProxy();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public IssueMovieAction() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		proxy.setEndpoint("http://localhost:8080/VLMS/services/Service");
		String nextJSP = "/View/FindMovie.jsp";
		HttpSession session = request.getSession(true);

		try {
			if(null == session.getAttribute("user"))
				nextJSP = "/View/accessControl.jsp";

			else{
				if (null != request.getParameter("issueMovie")) {

					User objUser = (User) (request.getSession().getAttribute("user"));
					String movieID = request.getParameter("movieID");
					String movieName = request.getParameter("movieName");
					String qdone = proxy.issueMovie(movieID, objUser);

					if (qdone.equalsIgnoreCase("true")){
						request.setAttribute("message", movieName+" has been issued!");
					}
					else{
						request.setAttribute("message", "Unable to issue Movie! "
								+ qdone);
					}
				} 
			}
			RequestDispatcher dispatcher = getServletContext()
					.getRequestDispatcher(nextJSP);
			dispatcher.forward(request, response);

		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}