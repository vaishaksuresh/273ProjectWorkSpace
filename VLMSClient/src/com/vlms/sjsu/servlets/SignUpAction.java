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
 * Servlet implementation class SignUpAction
 */
public class SignUpAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ServiceProxy proxy = new ServiceProxy();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SignUpAction() {
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
		String qdone;
		try {
			User objUser = new User();
			objUser.setFirstName(request.getParameter("firstname"));
			objUser.setLastName(request.getParameter("lastname"));
			objUser.setEmail(request.getParameter("email"));
			objUser.setPassword(AppUtils.md5(request.getParameter("pass")));
			objUser.setAddress(request.getParameter("address"));
			objUser.setCity(request.getParameter("city"));
			objUser.setState(request.getParameter("state"));
			objUser.setZipCode(request.getParameter("zip"));
			objUser.setMemberType(request.getParameter("membertype"));


			if (!checkMandatorySignUpFields(objUser)) {
				request.setAttribute("message", "Required Field Missing!");
				String nextJSP = "/View/SignUp.jsp";
				RequestDispatcher dispatcher = getServletContext()
						.getRequestDispatcher(nextJSP);
				dispatcher.forward(request, response);
			} else {

				proxy.setEndpoint("http://localhost:8080/VLMS/services/Service");
				qdone = proxy.createUpdateUser(objUser);
				HttpSession session = request.getSession();
				if (qdone.equalsIgnoreCase("true")) {
					session.setAttribute("userSession", session);
					request.setAttribute("message",
							"SignUp Successful, Please Login.");
					String nextJSP = "/View/accessControl.jsp";
					RequestDispatcher dispatcher = getServletContext()
							.getRequestDispatcher(nextJSP);
					dispatcher.forward(request, response);

				} else {
					request.setAttribute("message", "Unable to Signup!" + qdone);
					String nextJSP = "/View/SignUp.jsp";
					RequestDispatcher dispatcher = getServletContext()
							.getRequestDispatcher(nextJSP);
					dispatcher.forward(request, response);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private boolean checkMandatorySignUpFields(User objUser) {

		return true;
	}
}
