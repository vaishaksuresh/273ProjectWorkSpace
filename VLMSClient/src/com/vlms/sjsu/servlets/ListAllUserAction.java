package com.vlms.sjsu.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.vlms.sjsu.entity.User;
import com.vlms.sjsu.service.ServiceProxy;

/**
 * Servlet implementation class ListAllUser
 */
public class ListAllUserAction extends HttpServlet {
    private static final long serialVersionUID = 1L;
    ServiceProxy proxy = new ServiceProxy();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListAllUserAction() {
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
        // TODO Auto-generated method stub
        // System.out.println("CALLED::::::::::::::");
        try {
            proxy.setEndpoint("http://localhost:8080/VLMS/services/Service");

            HttpSession session = request.getSession();
            // System.out.println("CALLED:::::::::::::: ----------------------- 1");
            String noOfRows = request.getParameter("value");
            if (null != noOfRows){
                session.setAttribute("noOfRowsSession", noOfRows);
            System.out.println("noOfRows ==== "+noOfRows);
            }
            else    
                noOfRows = (String) session.getAttribute("noOfRowsSession");

            User[] result;
            result = proxy.listAllUsers(noOfRows);
            // System.out.println("result" + result[0]);
            request.setAttribute("value1", noOfRows);
            request.setAttribute("resultArray", result);
            request.getRequestDispatcher("/View/ListAllUser.jsp").forward(
                    request, response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}