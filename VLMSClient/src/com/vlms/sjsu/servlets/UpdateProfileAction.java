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
 * Servlet implementation class UpdateProfileAction
 */
public class UpdateProfileAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ServiceProxy proxy = new ServiceProxy();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateProfileAction() {
		super();
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
		User objUser = (User) (request.getSession().getAttribute("user"));
		if (null == objUser) {
			String nextJSP = "/View/HomeAction";
			RequestDispatcher dispatcher = getServletContext()
					.getRequestDispatcher(nextJSP);
			dispatcher.forward(request, response);
		} else {
			if (null != request.getParameter("updateBtn")) {
				if (AppUtils.md5(request.getParameter("pass")).equals(
						objUser.getPassword())) {

					HttpSession session = request.getSession();
					User objUpdateUser = new User();

					if (isProfileUpdated(request, objUser)) {

						objUpdateUser.setUserId(objUser.getUserId());
						objUpdateUser.setFirstName(request
								.getParameter("firstname"));
						objUpdateUser.setLastName(request
								.getParameter("lastname"));
						objUpdateUser.setEmail(objUser.getEmail());

						if (AppUtils.md5(request.getParameter("pass")).equals(
								AppUtils.md5(request.getParameter("newpass")))
								|| "".equalsIgnoreCase(request
										.getParameter("newpass")))
							// objUpdateUser.setPassword(null);
							objUpdateUser.setPassword(objUser.getPassword());
						else
							objUpdateUser.setPassword(AppUtils.md5(request
									.getParameter("newpass")));

						objUpdateUser.setAddress(request
								.getParameter("address"));
						objUpdateUser.setCity(request.getParameter("city"));
						objUpdateUser.setState(request.getParameter("state"));
						objUpdateUser.setZipCode(request.getParameter("zip"));
						objUpdateUser.setMemberType(request
								.getParameter("membertype"));
						if (!request.getParameter("membertype")
								.equalsIgnoreCase(objUser.getMemberType())) {
							if ("Simple".equalsIgnoreCase(request
									.getParameter("membertype"))) {

								objUpdateUser.setBalance(150f);
								objUpdateUser.setSubscriptionFee(0f);

							} else {

								objUpdateUser.setBalance(0f);
								objUpdateUser.setSubscriptionFee(100f);

							}
						} else {
							objUpdateUser.setBalance(objUser.getBalance());
							objUpdateUser.setSubscriptionFee(objUser
									.getSubscriptionFee());
						}

						String qdone = proxy.createUpdateUser(objUpdateUser);
						if (qdone.equalsIgnoreCase("true")) {
							session.setAttribute("userSession", session);
							session.setAttribute("user", objUpdateUser);
							request.setAttribute("message", "Profile Updated!");

						} else {
							request.setAttribute("message",
									"Unable to Update Profile! : " + qdone);
						}
					} else {
						request.setAttribute("message",
								"No Change Made to the Profile. Nothing to Update");
					}
				} else {
					request.setAttribute("message",
							"The Current Password is Incorrect!");
				}
			}
			String nextJSP = "/View/UpdateProfile.jsp";
			RequestDispatcher dispatcher = getServletContext()
					.getRequestDispatcher(nextJSP);
			dispatcher.forward(request, response);
		}

	}

	private boolean isProfileUpdated(HttpServletRequest request, User objUser) {
		if (request.getParameter("firstname").equalsIgnoreCase(
				objUser.getFirstName())
				&& request.getParameter("lastname").equalsIgnoreCase(
						objUser.getLastName())
				&& "".equalsIgnoreCase(request.getParameter("newpass"))
				&& request.getParameter("address").equalsIgnoreCase(
						objUser.getAddress())
				&& request.getParameter("city").equalsIgnoreCase(
						objUser.getCity())
				&& request.getParameter("state").equalsIgnoreCase(
						objUser.getState())
				&& request.getParameter("zip").equalsIgnoreCase(
						objUser.getZipCode())
				&& request.getParameter("membertype").equalsIgnoreCase(
						objUser.getMemberType()))
			return false;
		else
			return true;
	}
}
