package com.vlms.sjsu.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.vlms.sjsu.service.ServiceProxy;

/**
 * Servlet implementation class deleteUser
 */
public class deleteUserAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ServiceProxy proxy = new ServiceProxy();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public deleteUserAction() {
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
		try {
			proxy.setEndpoint("http://localhost:8080/VLMS/services/Service");
			System.out.println("am in delete part 1");
			HttpSession session = request.getSession();
			String userHiddenID = (String) request.getParameter("userHiddenID");
			String result;
			result = proxy.deleteUser(userHiddenID);
			// System.out.println("result" + result[0]);

			System.out.println("userHiddenID is" + userHiddenID);
			System.out.println("am in delete part 2");
			// response.sendRedirect("ListAllUserAction");
			request.getRequestDispatcher("ListAllUserAction").forward(request,
					response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
