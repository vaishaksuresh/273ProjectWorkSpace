package com.vlms.sjsu.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.vlms.sjsu.entity.User;
import com.vlms.sjsu.service.ServiceProxy;

/**
 * Servlet implementation class FindUser
 */
public class FindUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ServiceProxy proxy = new ServiceProxy();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FindUser() {
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


		proxy.setEndpoint("http://localhost:8080/VLMS/services/Service");
		User objUser = new User();
		User[] searchedUsers;


		System.out.println("Value of ssn is : " + request.getParameter("ssn"));
		
		if (	null != request.getParameter("ssn")	&& !"".equalsIgnoreCase(request.getParameter("ssn"))
				|| !"".equalsIgnoreCase(request.getParameter("UserFirstName"))
				|| !"".equalsIgnoreCase(request.getParameter("UserLastName"))
				|| !"".equalsIgnoreCase(request.getParameter("UserEmail"))
				|| !"".equalsIgnoreCase(request.getParameter("UserAddress"))
				|| !"".equalsIgnoreCase(request.getParameter("UserCity"))
				|| !"".equalsIgnoreCase(request.getParameter("UserState"))
				|| !"".equalsIgnoreCase(request.getParameter("UserZipCode"))
				|| !"".equalsIgnoreCase(request.getParameter("UserMemberType"))) {
			
			String id = request.getParameter("ssn");
			id = id.replaceAll("-", "");
			System.out.println(id);
			int UserID =0;
			
			if(!"".equalsIgnoreCase(id))
			{
			 UserID = Integer.parseInt(id);
			}
			
			objUser.setUserId(UserID);
			request.setAttribute("usersID", request.getParameter("ssn"));
			
			objUser.setAddress(request.getParameter("UserAddress"));
			request.setAttribute("usersStreet", request.getParameter("UserAddress"));
			
			objUser.setCity(request.getParameter("UserCity"));
			request.setAttribute("usersCity", request.getParameter("UserCity"));
			
			objUser.setEmail(request.getParameter("UserEmail"));
			request.setAttribute("usersEmail", request.getParameter("UserEmail"));
			
			objUser.setFirstName(request.getParameter("UserFirstName"));
			request.setAttribute("usersFname", request.getParameter("UserFirstName"));
			
			objUser.setLastName(request.getParameter("UserLastName"));
			request.setAttribute("usersLname", request.getParameter("UserLastName"));
			
			if(!"select".equalsIgnoreCase(request.getParameter("UserMemberType"))){
			objUser.setMemberType(request.getParameter("UserMemberType"));
			request.setAttribute("userMemberType", request.getParameter("UserMemberType"));
			}
			
			if( !"select".equalsIgnoreCase(request.getParameter("UserState"))){
			objUser.setState(request.getParameter("UserState"));
			request.setAttribute("usersState", request.getParameter("UserState"));
			}
			
			objUser.setZipCode(request.getParameter("UserZipCode"));
			request.setAttribute("usersZipcode", request.getParameter("UserZipCode"));
			
			System.out.println("call to find user web method ");
			
			searchedUsers = proxy.findUser(objUser);
			if(searchedUsers==null){
			request.setAttribute("message","Sorry, no results found. Please try again.");	
			}
			else
			{
				StringBuffer searchString = new StringBuffer();
				if(!"".equalsIgnoreCase(request.getParameter("ssn")) && null != request.getParameter("ssn")){
					searchString.append(request.getParameter("ssn") + " ");
				}
				if(!"".equalsIgnoreCase(request.getParameter("UserAddress")) && null != request.getParameter("UserAddress")){
					searchString.append(request.getParameter("UserAddress") + " ");
				}
				if(!"".equalsIgnoreCase(request.getParameter("UserCity")) && null != request.getParameter("UserCity")){
					searchString.append(request.getParameter("UserCity") + " ");
				}
				if(!"".equalsIgnoreCase(request.getParameter("UserEmail")) && null != request.getParameter("UserEmail")){
					searchString.append(request.getParameter("UserEmail") + " ");
				}
				if(!"".equalsIgnoreCase(request.getParameter("UserFirstName")) && null != request.getParameter("UserFirstName")){
					searchString.append(request.getParameter("UserFirstName") + " ");
				}
				if(!"".equalsIgnoreCase(request.getParameter("UserLastName")) && null != request.getParameter("UserLastName")){
					searchString.append(request.getParameter("UserLastName") + " ");
				}
				if(!"select".equalsIgnoreCase(request.getParameter("UserMemberType")) && null != request.getParameter("UserMemberType")){
					searchString.append(request.getParameter("UserMemberType") + " ");
				}
				if(!"".equalsIgnoreCase(request.getParameter("UserZipCode")) && null != request.getParameter("UserZipCode")){
					searchString.append(request.getParameter("UserZipCode") + " ");
				}
				if( !"select".equalsIgnoreCase(request.getParameter("UserState")) && null != request.getParameter("UserState")){
					searchString.append(request.getParameter("UserState") + " ");
				}
				
				System.out.println(searchString.toString());
				//String finalSearchString = searchString.toString().substring(0,searchString.toString().lastIndexOf(",,,"));
				String finalSearchString = searchString.toString();
				System.out.println(finalSearchString);
				request.setAttribute("message1","Search parameters : " + finalSearchString.toString());	
			}
			
			request.setAttribute("SearchUser", searchedUsers);
		} 
		else 
		{
			request.setAttribute("message",	"Please enter a value.");
			searchedUsers = null;
		}
	

	String nextJSP = "/View/FindUser.jsp";
	RequestDispatcher dispatcher = getServletContext()
			.getRequestDispatcher(nextJSP);
	dispatcher.forward(request, response);
	}
}
