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
 * Servlet implementation class HomeAction
 */
public class HomeAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ServiceProxy proxy = new ServiceProxy();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HomeAction() {
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
		HttpSession session = request.getSession();
		try {

			proxy.setEndpoint("http://localhost:8080/VLMS/services/Service");
			User objUser = (User) (session.getAttribute("user"));
			if (null != objUser) {
				Movie[] movieList = proxy.showMoviesToReturn(String
						.valueOf(objUser.getUserId()));
				request.setAttribute("moviesToReturn", movieList);
			}
			if(request.getAttribute("message")!=null)
				request.setAttribute("message", request.getAttribute("message"));
			
			String nextJSP = "/View/index.jsp";
			RequestDispatcher dispatcher = getServletContext()
					.getRequestDispatcher(nextJSP);
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
