package com.vlms.sjsu.service;

import java.util.ArrayList;
import java.util.regex.Pattern;

import com.vlms.sjsu.dao.DatabaseConnection;
import com.vlms.sjsu.entity.Bill;
import com.vlms.sjsu.entity.Movie;
import com.vlms.sjsu.entity.User;

// TODO: Auto-generated Javadoc
/**
 * The Class Service.
 */
public class Service {
	DatabaseConnection db = new DatabaseConnection();

	/**
	 * Creates the update user.
	 * 
	 * @param objUser
	 *            the obj user
	 * @return the string
	 */
	private static final Pattern rfc2822 = Pattern
			.compile("^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$");
	private static final Pattern zip = Pattern.compile("^\\d{5}(-\\d{4})?$");

	public String createUpdateUser(User objUser) {
		String result = "";
		if (!rfc2822.matcher(objUser.getEmail()).matches()) {
			result = "Invalid Email, Please Try Again!";
			return result;
		}
		if (!zip.matcher(objUser.getZipCode()).matches()) {
			result = "Invalid Zip Code. Please correct and retry";
			return result;
		}
		if (null == objUser || 0 == objUser.getUserId()) {

			result = db.createUser(objUser);
		} else {
			result = db.updateUser(objUser);
		}
		return result;
	}

	/**
	 * Sign in.
	 * 
	 * @param strUsername
	 *            the str username
	 * @param mdPassword
	 *            the md password
	 * @return the user[]
	 */
	public User[] signIn(String strUsername, String mdPassword) {
		User[] arrUser = new User[1];
		arrUser[0] = db.signIn(strUsername, mdPassword);
		return arrUser;
	}

	/**
	 * List all movies.
	 * 
	 * @return the movie[]
	 */
	public Movie[] listAllMovies(int count) {
		ArrayList<Movie> movies = new ArrayList<Movie>();
		movies = db.listAllMovies(count);

		Movie[] arrMovies = new Movie[movies.size()];

		for (int i = 0; i < movies.size(); i++) {
			// arrMovies[i] = new Movie();
			arrMovies[i] = movies.get(i);
		}

		return arrMovies;
	}

	/**
	 * Find user.
	 * 
	 * @param objUser
	 *            the obj user
	 * @return the user[]
	 */
	public User[] findUser(User objUser) {
		if (objUser.getUserId() != 0
				|| !"".equalsIgnoreCase(objUser.getAddress())
				|| !"".equalsIgnoreCase(objUser.getCity())
				|| !"".equalsIgnoreCase(objUser.getEmail())
				|| !"".equalsIgnoreCase(objUser.getFirstName())
				|| !"".equalsIgnoreCase(objUser.getLastName())
				|| !"".equalsIgnoreCase(objUser.getMemberType())
				|| !"".equalsIgnoreCase(objUser.getState())
				|| !"".equalsIgnoreCase(objUser.getZipCode())) {

			if ((null != objUser.getAddress() && validate(objUser.getAddress()) == true)
					|| (null != objUser.getCity() && validate(objUser.getCity()) == true)
					|| (null != objUser.getEmail() && validate(objUser
							.getEmail()) == true)
					|| (null != objUser.getFirstName() && validate(objUser
							.getFirstName()) == true)
					|| (null != objUser.getLastName() && validate(objUser
							.getLastName()) == true)
					|| (null != objUser.getZipCode() && validate(objUser
							.getZipCode()) == true)) {
				System.out
						.println("The Find User field/s has a special character/s, no call to DB");
				return null;
			}

			else {
				ArrayList<User> users = new ArrayList<User>();
				users = db.findUser(objUser);
				User[] arrUsers = new User[users.size()];
				for (int i = 0; i < users.size(); i++) {
					arrUsers[i] = new User();
					arrUsers[i] = users.get(i);
				}
				return arrUsers;
			}
		}

		else {
			if (objUser.getUserId() > 0) {
				System.out
						.println("Correct format of membership id is xxx-xx-xxxx");
			}
			return null;
		}
	}

	private Boolean validate(String str) {
		boolean hasSpecialChar = false;

		char[] specialCh = { '!', '%', ']', '#', '$', '^', '&', '*', '\'', '(',
				')', '~', '<', '>', '/', '\\' };
		char[] ch = str.toCharArray();

		for (int i = 0; i < str.length(); i++) {
			for (int j = 0; j < specialCh.length; j++) {
				if (specialCh[j] == ch[i]) {
					System.out.println("String has a special character "
							+ specialCh[j]);
					hasSpecialChar = true;
				}
			}
		}

		return hasSpecialChar;
	}

	/**
	 * Find movie.
	 * 
	 * @param objMovie
	 *            the obj movie
	 * @return the movie[]
	 */
	public Movie[] findMovie(Movie objMovie) {
		System.out.println("inside find movie - Service");
		if (!"".equalsIgnoreCase(objMovie.getCategory())
				|| !"".equalsIgnoreCase(objMovie.getMovieBanner())
				|| !"".equalsIgnoreCase(objMovie.getMovieName())
				|| !"".equalsIgnoreCase(objMovie.getReleaseDate())) {

			System.out.println("at least one value has been provided");

			if ((null != objMovie.getMovieBanner() && validate(objMovie
					.getMovieBanner()) == true)
					|| (null != objMovie.getMovieName() && validate(objMovie
							.getMovieName()) == true)
					|| (null != objMovie.getReleaseDate() && validate(objMovie
							.getReleaseDate()) == true)) {
				System.out
						.println("The Find Movie field/s has a special character/s, no call to DB");
				return null;
			} else {
				ArrayList<Movie> movies = new ArrayList<Movie>();
				movies = db.findMovie(objMovie);
				Movie[] arrMovies = new Movie[movies.size()];
				for (int i = 0; i < movies.size(); i++) {
					arrMovies[i] = new Movie();
					arrMovies[i] = movies.get(i);
				}
				return arrMovies;
			}
		}

		else
			return null;
	}

	/**
	 * Generate bill.
	 * 
	 * @param strUserId
	 *            the str user id
	 * @return the bill[]
	 */
	public Bill[] generateBill(String strUserId) {
		Bill[] eachRow = db.generateBill(strUserId);
		return eachRow;
	}

	/*
	 * SUD Begin
	 */

	/**
	 * Creates the update movie.
	 * 
	 * @param objMovie
	 *            the obj movie
	 * @return the string
	 */
	public String createMovie(Movie objMovie) {
		String result = "";
		result = db.createMovie(objMovie);
		return result;
	}

	public String updateMovie(Movie newMovieObj) {
		String result = "";
		result = db.updateMovie(newMovieObj);
		return result;
	}

	/**
	 * Delete movie.
	 * 
	 * @param strMovieId
	 *            the str movie id
	 * @return the string
	 */
	public String deleteMovie(String strMovieId) {
		String result = db.deleteMovie(strMovieId);
		return result;
	}

	/**
	 * Issue movie.
	 * 
	 * @param strMovieId
	 *            the str movie id
	 * @param strUserId
	 *            the str user id
	 * @return the string
	 */
	public String issueMovie(String strMovieId, User objUser) {
		// Have to limit no of movies to 10
		// Have to do credit card checking
		String result = db.issueMovie(strMovieId, objUser);
		return result;
	}

	/*
	 * SUD Ends
	 */
	/**
	 * Gets the user details.
	 * 
	 * @param strUserId
	 *            the str user id
	 * @return the user details
	 */
	public User getUserDetails(Integer strUserId) {
		User arrUser = new User();

		// String result = "";
		// if (0 == strUserId) {
		// result = "false";
		// return result;
		// } else {
		arrUser = db.getUserDetails(strUserId);
		System.out.println("hope this works");
		return arrUser;

	}

	public Movie[] getUserMovieslist(int strUserId) {
		System.out.println("in usermovie list getting service");
		ArrayList<Movie> movies = new ArrayList<Movie>();
		movies = db.getUserMovieslist(strUserId);
		Movie movie[] = new Movie[movies.size()];
		for (int i = 0; i < movies.size(); i++) {
			// movie[i] = new Movie();
			movie[i] = movies.get(i);
			System.out.println("returning username in service" + movie[i]);
		}

		return movie;
	}

	/**
	 * Gets the movie details.
	 * 
	 * @param strMovieId
	 *            the str movie id
	 * @return the movie details
	 */
	public Movie getMovieDetails(Integer strMovieId) {
		System.out.println("in getmovie details getting service");
		Movie result = new Movie();

		if (0 == strMovieId) {

			return result;
		} else {
			result = db.getMovieDetails(strMovieId);
			System.out.println("returning moviedetails in service---"
					+ result.getMovieName());
			return result;
		}

	}

	public User[] movierenteduserlist(int strMovieId) {
		System.out.println("in rented username getting service");

		ArrayList<User> users = new ArrayList<User>();
		users = db.movierenteduserlist(strMovieId);
		User user[] = new User[users.size()];
		System.out.println("users.size() == " + users.size());
		for (int i = 0; i < users.size(); i++) {
			System.out.println("i === " + i);
			// user[i] = new User();
			user[i] = users.get(i);
			System.out.println("users array list check--" + users.get(i));
			System.out.println("returning username in service"
					+ user[i].getFirstName());
		}

		return user;
	}

	/**
	 * Return movie.
	 * 
	 * @param strMovieId
	 *            the str movie id
	 * @param strUserId
	 *            the str user id
	 * @return the string
	 */
	public String returnMovie(Integer strMovieId, Integer strUserId) {
		//
		String rm = db.returnMovie(strMovieId, strUserId);
		System.out.println("in service of return movie" + rm);
		return rm;

	}

	/**
	 * List all users.
	 * 
	 * @param strUserType
	 *            the str user type
	 * @return the user[]
	 */
	public User[] listAllUsers(String noOfRows) {
		// System.out.println("SERVICE ::::::::::::::::::::::::: 1");
		User[] eachRow = db.listAllUSers(noOfRows);

		// System.out.println("SERVICE ::::::::::::::::::::::::: 2");

		return eachRow;
	}

	/**
	 * Delete user.
	 * 
	 * @param strUserID
	 *            the str user id
	 * @return the string
	 */
	public String deleteUser(String strUserID) {
		String result = db.deleteUser(strUserID);
		return result;
	}

	public Movie[] showMoviesToReturn(String strUserID) {
		ArrayList<Movie> movieList = new ArrayList<Movie>();
		movieList = db.showMoviesToReturn(strUserID);
		Movie[] movies = new Movie[movieList.size()];
		for (int i = 0; i < movieList.size(); i++) {
			movies[i] = movieList.get(i);
		}
		return movies;
	}

}
