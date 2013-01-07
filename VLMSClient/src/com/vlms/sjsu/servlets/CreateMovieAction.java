package com.vlms.sjsu.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.vlms.sjsu.entity.Movie;
import com.vlms.sjsu.service.ServiceProxy;

/**
 * Servlet implementation class CreateMovieAction
 */
public class CreateMovieAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ServiceProxy proxy = new ServiceProxy();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateMovieAction() {
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
		String qdone;
		HttpSession session = request.getSession(true);
		try {
			String nextJSP = "";
			if(null == session.getAttribute("user"))
				nextJSP = "/View/accessControl.jsp";

			else if(null!=request.getParameter("createMovieBtn")){
				proxy.setEndpoint("http://localhost:8080/VLMS/services/Service");
				Movie objMovie = new Movie();
				objMovie.setMovieName(request.getParameter("moviename"));
				objMovie.setMovieBanner(request.getParameter("productionname"));
				objMovie.setReleaseDate(request.getParameter("releasedate"));
				objMovie.setRentAmount(Float.parseFloat(request.getParameter("rentamount")));
				objMovie.setCategory(request.getParameter("category"));
				objMovie.setAvailableCopies(Integer.parseInt(request.getParameter("availablecopies")));
				qdone = proxy.createMovie(objMovie);
				if (qdone.equalsIgnoreCase("true")) {
					request.setAttribute("message",
							"Successfully added movie to database.");
					nextJSP = "/View/AdminHome.jsp";

				} else {
					request.setAttribute("message", "Unable to create movie!" + qdone);
					nextJSP = "/View/createMovie.jsp";
				}
			}
			else{
				nextJSP = "/View/createMovie.jsp";
			}

			RequestDispatcher dispatcher = getServletContext()
					.getRequestDispatcher(nextJSP);
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public boolean checkMandatorySignUpFields(Movie objMovie){
		if(null != objMovie.getMovieName()){
			return true;
		}
		return false;
	}

}
