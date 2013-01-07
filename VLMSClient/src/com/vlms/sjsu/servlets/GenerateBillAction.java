package com.vlms.sjsu.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.vlms.sjsu.entity.Bill;
import com.vlms.sjsu.entity.User;
import com.vlms.sjsu.service.ServiceProxy;

/**
 * Servlet implementation class GenerateBill
 */
// @WebServlet("/GenerateBill")
public class GenerateBillAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ServiceProxy proxy = new ServiceProxy();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GenerateBillAction() {
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

			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("user");

			String strUserId = Integer.toString(user.getUserId());
			System.out.println("user id " + strUserId);
			Bill[] result = proxy.generateBill(strUserId);
			
			request.setAttribute("resultArray", result);
			request.getRequestDispatcher("/View/GenerateBill.jsp").forward(
					request, response);

		}

		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
