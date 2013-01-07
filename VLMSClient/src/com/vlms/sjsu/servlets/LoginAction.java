package com.vlms.sjsu.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.vlms.sjsu.entity.User;
import com.vlms.sjsu.service.ServiceProxy;
import com.vlms.sjsu.util.AppUtils;

/**
 * Servlet implementation class LoginAction
 */
public class LoginAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ServiceProxy proxy = new ServiceProxy();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginAction() {
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
		try {

			String email = request.getParameter("email");
			String password = AppUtils.md5(request.getParameter("password"));

			User objUser = new User();

			proxy.setEndpoint("http://localhost:8080/VLMS/services/Service");
			User[] arrUser = proxy.signIn(email, password);
			objUser = arrUser[0];
			HttpSession session = request.getSession();

			if (objUser.getUserId() != 0) {
				session.setAttribute("userSession", session);
				session.setAttribute("user", objUser);
				String nextJSP = "/View/HomeAction";
				RequestDispatcher dispatcher = getServletContext()
						.getRequestDispatcher(nextJSP);
				dispatcher.forward(request, response);
			} else {
				request.setAttribute("message",
						"Invalid Login Details, Please Try Again");
				String nextJSP = "/View/accessControl.jsp";
				RequestDispatcher dispatcher = getServletContext()
						.getRequestDispatcher(nextJSP);
				dispatcher.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
