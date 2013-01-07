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
 * Servlet implementation class UpdateMovieAction
 */
public class UpdateMovieAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ServiceProxy proxy = new ServiceProxy();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateMovieAction() {
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
	
	  protected void doPost(HttpServletRequest request, HttpServletResponse
			  response) throws ServletException, IOException {
		  response.setContentType("text/html");
		  proxy.setEndpoint("http://localhost:8080/VLMS/services/Service");
		  HttpSession session = request.getSession(true);

		  String nextJSP = "";

		  if(null == session.getAttribute("user")){
			  nextJSP = "/View/accessControl.jsp";
		  }
		  else if (null != request.getParameter("updateMovieBtn")) {
			  Movie objMovie = (Movie) session.getAttribute("movieDetails");
			  session.removeAttribute("movieDetails");
			  Movie objUpdateMovie = setMovieObject(request);  			  
			  nextJSP = "/View/updateMovie.jsp";
			  request.setAttribute("movieDetails", objUpdateMovie);	

			  if(!(objUpdateMovie.getMovieName().equalsIgnoreCase(objMovie.getMovieName())) || !(objUpdateMovie.getMovieBanner().equalsIgnoreCase(objMovie.getMovieBanner())) || !(objUpdateMovie.getReleaseDate().equalsIgnoreCase(objMovie.getReleaseDate())) || !(objUpdateMovie.getRentAmount().equals(objMovie.getRentAmount())) || objUpdateMovie.getAvailableCopies()!=objMovie.getAvailableCopies() || !(objUpdateMovie.getCategory().equalsIgnoreCase(objMovie.getCategory()))){
				  String qdone = proxy.updateMovie(objUpdateMovie); 
				  if(qdone.equalsIgnoreCase("true")){
					  request.setAttribute("message","Movie Updated!");
				  }
				  else{
					  request.setAttribute("message","Unable to Update Movie! : " + qdone);
				  }
			  }
			  else{
				  request.setAttribute("message","You have not updated any details... ");
			  }
		  }
		  else{
			  Movie objOldMovie = setMovieObject(request);
			  request.setAttribute("movieDetails", objOldMovie);
			  request.setAttribute("message", "Edit movie details");			  
			  nextJSP = "/View/updateMovie.jsp";
		  } 
		  
		  RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
		  dispatcher.forward(request, response);
	  }
	 

/*	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		proxy.setEndpoint("http://localhost:8080/VLMS/services/Service");

		Movie objMovie = new Movie();
		objMovie.setMovieId(1);
		objMovie.setAvailableCopies(3);
		objMovie.setCategory("sci-fi");
		objMovie.setMovieBanner("myBanner");
		objMovie.setMovieName("myMovie");
		objMovie.setReleaseDate("11/19/2012");
		objMovie.setRentAmount((float) 10);


		if (null != request.getParameter("updateMovieBtn")) {

			Movie objUpdateMovie = new Movie();
			objUpdateMovie.setMovieId(objMovie.getMovieId());
			objUpdateMovie.setMovieName(request.getParameter("moviename"));
			objUpdateMovie.setMovieBanner(request
					.getParameter("productionname"));
			objUpdateMovie.setReleaseDate(request.getParameter("releasedate"));
			objUpdateMovie.setRentAmount(Float.parseFloat(request
					.getParameter("rentamount")));
			objUpdateMovie.setCategory(request.getParameter("category"));
			objUpdateMovie.setAvailableCopies(Integer.parseInt(request
					.getParameter("availablecopies")));

			String qdone = proxy.updateMovie(objMovie, objUpdateMovie);
			if (qdone.equalsIgnoreCase("true"))
				request.setAttribute("message", "Movie Updated!");
			else
				request.setAttribute("message", "Unable to Update Movie! : "
						+ qdone);
		} else
			request.setAttribute("message", "Edit movie details");

		String nextJSP = "/View/updateMovie.jsp";
		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher(nextJSP);
		dispatcher.forward(request, response);

	}*/

	  public Movie setMovieObject(HttpServletRequest request){
		  
		  Movie objUpdateMovie = new Movie();
		  objUpdateMovie.setMovieId(Integer.parseInt(request.getParameter("movieID")));
		  objUpdateMovie.setMovieName(request.getParameter("movieName"));
		  objUpdateMovie.setMovieBanner(request.getParameter("movieBanner"));
		  objUpdateMovie.setReleaseDate(request.getParameter("releaseDate"));
		  objUpdateMovie.setRentAmount(Float.parseFloat(request.getParameter("rentAmount")));
		  objUpdateMovie.setCategory(request.getParameter("category"));
		  objUpdateMovie.setAvailableCopies(Integer.parseInt(request.getParameter("availableCopies")));
		  return objUpdateMovie;		  
	  }
}
