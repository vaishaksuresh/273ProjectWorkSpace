/**
 * 
 */
package com.vlms.sjsu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.vlms.sjsu.entity.Bill;
import com.vlms.sjsu.entity.Movie;
import com.vlms.sjsu.entity.User;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

// TODO: Auto-generated Javadoc
/**
 * The Class DatabaseConnection_PS.
 * 
 * @author vaishaksuresh
 */
public class DatabaseConnection_PS {

	private DataSource dataSource;

	/**
	 * Instantiates a new database connection_ ps.
	 */
	public DatabaseConnection_PS() {
		Context initContext;
		try {
			initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			dataSource = (DataSource) envContext.lookup("jdbc/vlms");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Creates the user.
	 * 
	 * @param objUser
	 *            the obj user
	 * @return the string
	 */
	public String createUser(User objUser) {
		String result = "";
		int rowcount;

		try {

			Connection con = dataSource.getConnection();
			PreparedStatement stmt = con
					.prepareStatement("INSERT INTO `vlms`.`user`(`email`,`password`,`firstname`,`lastname`,`address`,`city`,`state`,`zipcode`,`membertype`,`subscriptionfee`,`balance`)"
							+ "VALUES(?,?,?,?,?,?,?,?,?,?,?)");

			stmt.setString(1, objUser.getEmail());
			stmt.setString(2, objUser.getPassword());
			stmt.setString(3, objUser.getFirstName());
			stmt.setString(4, objUser.getLastName());
			stmt.setString(5, objUser.getAddress());
			stmt.setString(6, objUser.getCity());
			stmt.setString(7, objUser.getState());
			stmt.setString(8, objUser.getZipCode());
			stmt.setString(9, objUser.getMemberType());
			stmt.setString(10, "0");
			stmt.setString(11, "150");
			System.out.println(stmt.toString());
			rowcount = stmt.executeUpdate();
			if (rowcount > 0) {
				result = "true";
			} else {
				result = "false: The data could not be inserted into the database";
			}
		} catch (MySQLIntegrityConstraintViolationException e) {
			result = "User Exists with the email, please use another email ID";
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * Update user.
	 * 
	 * @param objUser
	 *            the obj user
	 * @return the string
	 */
	public String updateUser(User objUser) {
		String result = "";
		int rowcount;
		Statement stmt = null;
		Connection con = null;
		try {
			con = dataSource.getConnection();
			stmt = con.createStatement();
			StringBuffer strbQuery = new StringBuffer("UPDATE user set ");
			if (null != objUser.getFirstName()) {
				strbQuery.append("firstname = '" + objUser.getFirstName()
						+ "', ");
			}
			if (null != objUser.getLastName()) {
				strbQuery
						.append("lastname = '" + objUser.getLastName() + "', ");
			}
			if (null != objUser.getPassword()) {
				strbQuery
						.append("password = '" + objUser.getPassword() + "', ");
			}
			if (null != objUser.getAddress()) {
				strbQuery.append("address = '" + objUser.getAddress() + "', ");
			}
			if (null != objUser.getCity()) {
				strbQuery.append("city = '" + objUser.getCity() + "', ");
			}
			if (null != objUser.getState()) {
				strbQuery.append("state = '" + objUser.getState() + "', ");
			}
			if (null != objUser.getZipCode()) {
				strbQuery.append("zipcode = '" + objUser.getZipCode() + "', ");
			}
			if (null != objUser.getMemberType()) {
				strbQuery.append("membertype = '" + objUser.getMemberType()
						+ "', ");
			}
			if (null != objUser.getSubscriptionFee()) {
				strbQuery.append("subscriptionfee = '"
						+ String.valueOf(objUser.getSubscriptionFee()) + "', ");
			}
			strbQuery.append("balance = '"
					+ String.valueOf(objUser.getBalance()) + "' ");
			strbQuery.append("WHERE email='" + objUser.getEmail()
					+ "' AND userid=" + objUser.getUserId());
			System.out.println(strbQuery.toString());
			rowcount = stmt.executeUpdate(strbQuery.toString());

			if (rowcount > 0) {
				result = "true";
			} else {
				result = "false: The data could not be inserted into the database";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (stmt != null) {
					stmt.close();
				}

				if (con != null) {
					con.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

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
	 * @return the user
	 */
	public User signIn(String strUsername, String mdPassword) {
		ResultSet rs = null;
		User objLoggedInUser = new User();
		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement stmt = null;
		try {
			con = dataSource.getConnection();

			pstmt = con
					.prepareStatement("SELECT userid,password,firstname,lastname,address,city,state,zipcode,membertype,subscriptionfee,balance,lastlogin "
							+ "FROM user WHERE email=?");
			pstmt.setString(1, strUsername);
			System.out.println(pstmt.toString());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String strPass = rs.getString("password");

				if (strPass.equals(mdPassword)) {
					int id = rs.getInt("userid");
					objLoggedInUser.setUserId(id);
					objLoggedInUser.setFirstName(rs.getString("firstname"));
					objLoggedInUser.setLastName(rs.getString("lastname"));
					objLoggedInUser.setEmail(strUsername);
					objLoggedInUser.setPassword(mdPassword);
					objLoggedInUser.setAddress(rs.getString("address"));
					objLoggedInUser.setCity(rs.getString("city"));
					objLoggedInUser.setState(rs.getString("state"));
					objLoggedInUser.setZipCode(rs.getString("zipcode"));
					objLoggedInUser.setMemberType(rs.getString("membertype"));
					objLoggedInUser.setSubscriptionFee(Float.valueOf(rs
							.getString("subscriptionfee")));
					objLoggedInUser.setBalance(Float.valueOf(rs
							.getString("balance")));
					objLoggedInUser.setLastlogin(rs.getString("lastlogin"));
					System.out.println("Last Login:"
							+ objLoggedInUser.getLastlogin());
					stmt = con
							.prepareStatement("UPDATE user set lastlogin=? where userid=?");
					stmt.setString(1,
							DateFormat.getDateTimeInstance().format(new Date()));
					stmt.setInt(2, id);
					stmt.executeUpdate();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				/*
				 * if (con != null) { con.close(); }
				 */
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return objLoggedInUser;

	}

	/**
	 * List all movies.
	 * 
	 * @param count
	 *            the count
	 * @return the array list
	 */
	public ArrayList<Movie> listAllMovies(int count) {

		String result = "";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Movie> movies = new ArrayList<Movie>();

		try {
			con = dataSource.getConnection();

			if (count != 0) {
				pstmt = con
						.prepareStatement("Select id,name,releaseDate,availableCopies,category,production,rentAmount from movie LIMIT ?");
				pstmt.setInt(1, count);
			} else {
				pstmt = con
						.prepareStatement("Select id,name,releaseDate,availableCopies,category,production,rentAmount from movie");
			}

			rs = pstmt.executeQuery();

			if (rs != null) {
				while (rs.next()) {
					Movie m = new Movie();
					m.setAvailableCopies(rs.getInt("availableCopies"));
					m.setCategory(rs.getString("category"));
					m.setMovieBanner(rs.getString("production"));
					m.setMovieId(rs.getInt("id"));
					m.setMovieName(rs.getString("name"));
					m.setReleaseDate(rs.getString("releaseDate"));
					m.setRentAmount(rs.getFloat("rentAmount"));

					movies.add(m);
				}
				return movies;
			} else {
				result = "There seems to be some problem and we cannot find any movies";
				System.out.println(result);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}

				if (con != null) {
					con.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * Find user.
	 * 
	 * @param objUser
	 *            the obj user
	 * @return the array list
	 */
	public ArrayList<User> findUser(User objUser) {

		String result = "";
		ArrayList<User> users = new ArrayList<User>();

		StringBuffer strbQuery = new StringBuffer(
				"select address,balance,city,email,firstname,lastname,membertype,state,subscriptionfee,userid,zipcode,lastlogin from user where");

		if (0 != objUser.getUserId()) {
			strbQuery.append(" userid = " + objUser.getUserId() + " and ");
		}

		if (null != objUser.getFirstName()
				&& !"".equalsIgnoreCase(objUser.getFirstName())) {
			strbQuery.append(" firstname = '" + objUser.getFirstName()
					+ "' and ");
		}
		if (null != objUser.getLastName()
				&& !"".equalsIgnoreCase(objUser.getLastName())) {
			strbQuery
					.append(" lastname = '" + objUser.getLastName() + "' and ");
		}
		if (null != objUser.getAddress()
				&& !"".equalsIgnoreCase(objUser.getAddress())) {
			strbQuery.append(" address = '" + objUser.getAddress() + "' and ");
		}
		if (null != objUser.getCity()
				&& !"".equalsIgnoreCase(objUser.getCity())) {
			strbQuery.append(" city = '" + objUser.getCity() + "' and ");
		}
		if (null != objUser.getState()
				&& !"".equalsIgnoreCase(objUser.getState())) {
			strbQuery.append(" state = '" + objUser.getState() + "' and ");
		}
		if (null != objUser.getZipCode()
				&& !"".equalsIgnoreCase(objUser.getZipCode())) {
			strbQuery.append(" zipcode = '" + objUser.getZipCode() + "' and ");
		}
		if (null != objUser.getMemberType()
				&& !"".equalsIgnoreCase(objUser.getMemberType())) {
			strbQuery.append(" membertype = '" + objUser.getMemberType()
					+ "' and ");
		}
		if (null != objUser.getEmail()
				&& !"".equalsIgnoreCase(objUser.getEmail())) {
			strbQuery.append(" email = '" + objUser.getEmail() + "' and ");
		}

		String finalQuery = strbQuery.toString().substring(0,
				strbQuery.toString().lastIndexOf("and"));
		System.out.println(finalQuery);
		ResultSet rs = null;
		Connection con = null;
		Statement stmt = null;
		try {
			con = dataSource.getConnection();
			stmt = con.createStatement();

			rs = stmt.executeQuery(finalQuery);

			if (rs != null) {
				while (rs.next()) {
					User u = new User();
					// 15 password balance subscriptionfee lastlogin
					u.setAddress(rs.getString("address"));
					u.setBalance(rs.getFloat("balance"));
					u.setCity(rs.getString("city"));

					u.setEmail(rs.getString("email"));
					u.setFirstName(rs.getString("firstname"));
					u.setLastName(rs.getString("lastname"));

					// u.setMembershipNumber(rs.getString("membershipnumber"));
					u.setMemberType(rs.getString("membertype"));
					u.setState(rs.getString("state"));
					u.setSubscriptionFee(rs.getFloat("subscriptionfee"));

					u.setUserId(rs.getInt("userid"));
					u.setZipCode(rs.getString("zipcode"));
					u.setLastlogin(rs.getString("lastlogin"));

					users.add(u);
				}
				return users;
			} else {
				result = "No Match Found";
				System.out.println(result);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * Find movie.
	 * 
	 * @param objMovie
	 *            the obj movie
	 * @return the array list
	 */
	public ArrayList<Movie> findMovie(Movie objMovie) {
		String result = "";
		ArrayList<Movie> movies = new ArrayList<Movie>();

		StringBuffer strbQuery = new StringBuffer(
				"select id,name,production,category,availableCopies,releaseDate,rentAmount from movie where ");

		if (null != objMovie.getCategory()
				&& !"".equalsIgnoreCase(objMovie.getCategory())) {
			strbQuery.append("  category = '" + objMovie.getCategory()
					+ "' and ");
		}
		if (!"".equalsIgnoreCase(objMovie.getMovieBanner())) {
			strbQuery.append("  production = '" + objMovie.getMovieBanner()
					+ "' and ");
		}
		if (!"".equalsIgnoreCase(objMovie.getMovieName())) {
			strbQuery
					.append("  name = '" + objMovie.getMovieName() + "' and ");
		}
		if (!"".equalsIgnoreCase(objMovie.getReleaseDate())) {
			strbQuery.append("  releaseDate ='" + objMovie.getReleaseDate()
					+ "' and ");
		}

		String finalQuery = strbQuery.toString().substring(0,
				strbQuery.toString().lastIndexOf("and"));
		System.out.println(" FINAL QUERY : " + finalQuery);

		ResultSet rs = null;
		Statement stmt = null;
		Connection con = null;
		try {
			con = dataSource.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(finalQuery);

			if (rs != null) {
				while (rs.next()) {
					Movie m = new Movie();

					m.setAvailableCopies(rs.getInt("availableCopies"));
					m.setCategory(rs.getString("category"));
					m.setMovieBanner(rs.getString("production"));
					m.setMovieId(rs.getInt("id"));
					m.setMovieName(rs.getString("name"));
					m.setReleaseDate(rs.getString("releaseDate"));
					m.setRentAmount(rs.getFloat("rentAmount"));
					movies.add(m);
				}

				return movies;
			} else {
				result = "No Match Found";
				System.out.println(result);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return null;

	}

	/*
	 * SUD Starting my methods
	 */

	/**
	 * Delete movie.
	 * 
	 * @param movieId
	 *            the movie id
	 * @return the string
	 */
	public String deleteMovie(String movieId) {
		String result = "";
		PreparedStatement pstmt = null;
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement stmt = null;
		int rowcount = 0;
		try {
			// Check if a user has rented the movie.
			con = dataSource.getConnection();

			stmt = con
					.prepareStatement("SELECT COUNT(movieID) as issuedcount FROM transaction WHERE movieID=? AND returndate='0'");
			stmt.setString(1, movieId);
			rs = stmt.executeQuery();
			while (rs.next()) {
				rowcount = rs.getInt("issuedcount");
			}
			if (rowcount > 0) {
				result = "The movie is currently issue to one or more users.";
			} else {
				pstmt = con.prepareStatement("DELETE FROM movie WHERE id=?");
				pstmt.setString(1, movieId);
				rowcount = pstmt.executeUpdate();
				if (rowcount > 0) {
					result = "true";
				} else {
					result = "false: The movie could not be found in database";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}

				if (con != null) {
					con.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	/**
	 * Creates the movie.
	 * 
	 * @param objMovie
	 *            the obj movie
	 * @return the string
	 */
	public String createMovie(Movie objMovie) {
		String result = "";
		int rowcount = 0;
		PreparedStatement pstmt = null;
		PreparedStatement stmt = null;
		Connection con = null;
		ResultSet rs = null;

		try {
			// Check if a movie already exists.
			con = dataSource.getConnection();

			stmt = con
					.prepareStatement("SELECT count(id) as id FROM movie WHERE name=? AND production=? AND releaseDate=?");
			stmt.setString(1, objMovie.getMovieName());
			stmt.setString(2, objMovie.getMovieBanner());
			stmt.setString(3, objMovie.getReleaseDate());
			System.out.println(stmt.toString());
			rs = stmt.executeQuery();
			while (rs.next()) {
				rowcount = rs.getInt("id");
			}
			if (rowcount > 0) {
				result = "The movie already exists under the given banner name and release date";
			} else {
				pstmt = con
						.prepareStatement("INSERT INTO `vlms`.`movie`(name,production,releaseDate,rentAmount,category,availableCopies)VALUES(?,?,?,?,?,?)");

				pstmt.setString(1, objMovie.getMovieName());
				pstmt.setString(2, objMovie.getMovieBanner());
				pstmt.setString(3, objMovie.getReleaseDate());
				pstmt.setFloat(4, objMovie.getRentAmount());
				pstmt.setString(5, objMovie.getCategory());
				pstmt.setInt(6, objMovie.getAvailableCopies());

				rowcount = pstmt.executeUpdate();

				if (rowcount > 0) {
					result = "true";
				} else {
					result = "false: The data could not be inserted into the database";
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}

				if (con != null) {
					con.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * Update movie.
	 * 
	 * @param newMovieObj
	 *            the new movie obj
	 * @return the string
	 */
	public String updateMovie(Movie newMovieObj) {
		String result = "";
		int rowcount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		Statement stmt = null;

		try {
			con = dataSource.getConnection();

			pstmt = con
					.prepareStatement("SELECT COUNT(id) FROM movie WHERE id=?");
			pstmt.setInt(1, newMovieObj.getMovieId());
			// String query = "SELECT COUNT(name) FROM movie WHERE id='"
			// + newMovieObj.getMovieId() + "'";
			rs = pstmt.executeQuery();
			while (rs.next()) {
				rowcount = rs.getRow();
			}
			if (rowcount <= 0) {
				result = "The movie does not exist in the DB";
			} else {
				StringBuffer strbQuery = new StringBuffer("UPDATE movie SET ");
				if (newMovieObj.getAvailableCopies() != 0) {
					strbQuery.append("availableCopies = '"
							+ newMovieObj.getAvailableCopies() + "', ");
				}
				if (null != newMovieObj.getCategory()) {
					strbQuery.append("category = '" + newMovieObj.getCategory()
							+ "', ");
				}
				if (null != newMovieObj.getMovieBanner()) {
					strbQuery.append("production = '"
							+ newMovieObj.getMovieBanner() + "', ");
				}
				if (null != newMovieObj.getMovieName()) {
					strbQuery.append("name = '" + newMovieObj.getMovieName()
							+ "', ");
				}
				if (null != newMovieObj.getReleaseDate()) {
					strbQuery.append("releaseDate = '"
							+ newMovieObj.getReleaseDate() + "', ");
				}
				if (null != newMovieObj.getRentAmount()) {
					strbQuery.append("rentAmount = '"
							+ newMovieObj.getRentAmount() + "' ");
				}
				strbQuery
						.append("WHERE id='" + newMovieObj.getMovieId() + "';");
				System.out.println(strbQuery.toString());
				stmt = con.createStatement();
				rowcount = stmt.executeUpdate(strbQuery.toString());

				if (rowcount > 0) {
					result = "true";
				} else {
					result = "The movie Cannot Be found In teh Database. It may have been Deleted";
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}

				if (con != null) {
					con.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	/**
	 * Issue movie.
	 * 
	 * @param movieId
	 *            the movie id
	 * @param objUser
	 *            the obj user
	 * @return the string
	 */
	public String issueMovie(String movieId, User objUser) {
		String result = "";
		int rowcount = 0;
		int moviesCount = 0;
		boolean skip = false;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		ResultSet rs = null;
		Connection con = null;

		try {
			con = dataSource.getConnection();

			pstmt = con
					.prepareStatement("SELECT movieID,returndate from transaction where userID=?");
			pstmt.setInt(1, objUser.getUserId());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				if (rs.getInt("returndate") == 0)
					moviesCount++;
				if ("Simple".equalsIgnoreCase(objUser.getMemberType())
						&& moviesCount == 2) {
					result = "As a simple customer, you can only rent 2 movies at a time.";
					skip = true;
					break;
				} else if ("Premium".equalsIgnoreCase(objUser.getMemberType())
						&& moviesCount == 10) {
					result = "As a premium customer, you can only rent 10 movies at a time.";
					skip = true;
					break;
				} else if (movieId.equalsIgnoreCase(rs.getString("movieID"))
						&& rs.getInt("returndate") == 0) {
					result = "You have already rented a copy of this movie. You cannot add more copies";
					skip = true;
				}
			}
			if (!skip) {

				pstmt2 = con
						.prepareStatement("INSERT INTO transaction(userID,movieID,issueDate,returndate) VALUES (?,?,?,?)");

				pstmt2.setInt(1, objUser.getUserId());
				pstmt2.setString(2, movieId);
				pstmt2.setString(3, getCurrentTime());
				pstmt2.setString(4, "0");
				rowcount = pstmt2.executeUpdate();
				if (rowcount > 0) {
					pstmt3 = con
							.prepareStatement("UPDATE movie SET availableCopies=availableCopies-1 where id=?");
					pstmt3.setInt(1, Integer.parseInt(movieId));
					pstmt3.executeUpdate();
					result = "true";
				} else {
					result = "The data could not be inserted into the database";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (pstmt2 != null) {
					pstmt2.close();
				}
				if (pstmt3 != null) {
					pstmt3.close();
				}

				if (con != null) {
					con.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * Gets the current time.
	 * 
	 * @return the current time
	 */
	public String getCurrentTime() {
		final String DATE_FORMAT_NOW = "yyyy-dd-MM HH:mm";
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
		return sdf.format(cal.getTime());
	}

	/*
	 * SUD ends
	 */
	/**
	 * Gets the user details.
	 * 
	 * @param strUserId
	 *            the str user id
	 * @return the user details
	 */
	public User getUserDetails(Integer strUserId)// dappa change
	{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		User userobj = new User();
		Connection con = null;
		try {
			con = dataSource.getConnection();

			pstmt = con
					.prepareStatement("Select firstname,lastname,email,password,address,city,state,zipcode,membertype,subscriptionfee,balance,lastlogin from user where userid=?");
			pstmt.setInt(1, strUserId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				userobj.setUserId(strUserId);
				userobj.setFirstName(rs.getString("firstname"));
				userobj.setLastName(rs.getString("lastname"));
				userobj.setEmail(rs.getString("email"));
				userobj.setPassword(rs.getString("password"));
				userobj.setAddress(rs.getString("address"));
				userobj.setCity(rs.getString("city"));
				userobj.setState(rs.getString("state"));
				userobj.setZipCode(rs.getString("zipcode"));
				userobj.setMemberType(rs.getString("membertype"));
				userobj.setSubscriptionFee(Float.valueOf(rs
						.getString("subscriptionfee")));
				userobj.setBalance(Float.valueOf(rs.getString("balance")));
				userobj.setLastlogin(rs.getString("lastlogin"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}

				if (con != null) {
					con.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return userobj;
	}

	/**
	 * Gets the user movieslist.
	 * 
	 * @param strUserId
	 *            the str user id
	 * @return the user movieslist
	 */
	public ArrayList<Movie> getUserMovieslist(int strUserId) {
		ResultSet rs = null;
		ArrayList<Movie> moviebig = new ArrayList<Movie>();
		PreparedStatement pstmt = null;
		Connection con = null;

		try {
			con = dataSource.getConnection();

			pstmt = con
					.prepareStatement("SELECT name from movie, transaction WHERE  movie.id=transaction.movieId AND transaction.userId=?");
			pstmt.setInt(1, strUserId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Movie movielist = new Movie();
				movielist.setMovieName(rs.getString("name"));
				moviebig.add(movielist);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}

				if (con != null) {
					con.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return moviebig;
	}

	/**
	 * Gets the movie details.
	 * 
	 * @param strMovieId
	 *            the str movie id
	 * @return the movie details
	 */
	public Movie getMovieDetails(int strMovieId) // changed from Movie[]
	{
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		Movie movieset = new Movie();
		Connection con = null;
		try {
			con = dataSource.getConnection();

			pstmt = con
					.prepareStatement("select name,releaseDate,rentAmount,availableCopies,category,production from movie WHERE id=?");
			pstmt.setInt(1, strMovieId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				movieset.setMovieId(strMovieId);
				movieset.setMovieName(rs.getString("name"));
				movieset.setReleaseDate(rs.getString("releaseDate"));
				movieset.setRentAmount(rs.getFloat("rentAmount"));
				movieset.setAvailableCopies(rs.getInt("availableCopies"));
				movieset.setCategory(rs.getString("category"));
				movieset.setMovieBanner(rs.getString("production"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}

				if (con != null) {
					con.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return movieset;
	}

	/**
	 * Movierenteduserlist.
	 * 
	 * @param strMovieId
	 *            the str movie id
	 * @return the array list
	 */
	public ArrayList<User> movierenteduserlist(int strMovieId) {

		ArrayList<User> users = new ArrayList<User>();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		Connection con = null;

		try {
			con = dataSource.getConnection();

			pstmt = con
					.prepareStatement("Select DISTINCT(user.userid), firstname from user,transaction WHERE user.userid=transaction.userId AND transaction.movieid=?");
			pstmt.setInt(1, strMovieId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				User usernamelist = new User();
				usernamelist.setFirstName(rs.getString("firstname"));
				users.add(usernamelist);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}

				if (con != null) {
					con.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return users;
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
	public String returnMovie(int strMovieId, int strUserId) {
		String result = "";
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		Connection con = null;

		try {
			con = dataSource.getConnection();

			pstmt = con
					.prepareStatement("UPDATE transaction SET returndate=? WHERE userID=? AND movieID=? AND returndate='0'");
			pstmt.setString(1, getCurrentTime());
			pstmt.setInt(2, strUserId);
			pstmt.setInt(3, strMovieId);
			int rowcount = pstmt.executeUpdate();
			if (rowcount > 0) {
				pstmt2 = con
						.prepareStatement("UPDATE movie SET availableCopies=availableCopies+1 where id=?");
				pstmt2.setInt(1, strMovieId);
				pstmt2.executeUpdate();
				result = "success";
			} else {
				result = "Movie already removed";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (pstmt2 != null) {
					pstmt2.close();
				}

				if (con != null) {
					con.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * List all u sers.
	 * 
	 * @param noOfRows
	 *            the no of rows
	 * @return the user[]
	 */
	public User[] listAllUSers(String noOfRows) {
		User[] eachRow = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = dataSource.getConnection();

			pstmt = con
					.prepareStatement("Select userid, firstname,lastname,email,password,address,city,state,zipcode,membertype,subscriptionfee,balance,lastlogin "
							+ "from user LIMIT ?");
			pstmt.setInt(1, Integer.parseInt(noOfRows));
			rs = pstmt.executeQuery();

			int rsSize = 0;
			rs.last();
			rsSize = rs.getRow();
			eachRow = new User[rsSize];

			rs.beforeFirst();
			int i = 0;
			while (rs.next()) {
				eachRow[i] = new User();
				int id = rs.getInt("userid");
				eachRow[i].setUserId(id);
				eachRow[i].setEmail(rs.getString("email"));
				eachRow[i].setPassword(rs.getString("password"));
				eachRow[i].setFirstName(rs.getString("firstname"));
				eachRow[i].setLastName(rs.getString("lastname"));
				eachRow[i].setAddress(rs.getString("address"));
				eachRow[i].setCity(rs.getString("city"));
				eachRow[i].setState(rs.getString("state"));
				eachRow[i].setZipCode(rs.getString("zipcode"));
				eachRow[i].setMemberType(rs.getString("membertype"));
				eachRow[i].setSubscriptionFee((rs.getFloat("subscriptionfee")));
				eachRow[i].setBalance((rs.getFloat("balance")));
				i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}

				if (con != null) {
					con.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
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

		String result = "";
		int rowcount;
		PreparedStatement pstmt = null;
		Connection con = null;
		try {
			con = dataSource.getConnection();

			pstmt = con.prepareStatement("delete from user where userid=?");
			pstmt.setInt(1, Integer.parseInt(strUserID));
			rowcount = pstmt.executeUpdate();
			if (rowcount > 0) {
				result = "Success";
			} else {
				result = "Failure";
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}

				if (con != null) {
					con.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * Generate bill.
	 * 
	 * @param strUserID
	 *            the str user id
	 * @return the bill[]
	 */
	public Bill[] generateBill(String strUserID) {
		Bill[] eachRow = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = dataSource.getConnection();

			pstmt = con
					.prepareStatement("SELECT transaction.issueDate,transaction.returndate,movie.name as name FROM transaction,movie where movie.id = transaction.movieID and transaction.userID =?");
			pstmt.setInt(1, Integer.parseInt(strUserID));

			rs = pstmt.executeQuery();

			int rsSize = 0;
			rs.last();
			rsSize = rs.getRow();
			eachRow = new Bill[rsSize];

			rs.beforeFirst();
			int i = 0;

			while (rs.next()) {
				eachRow[i] = new Bill();
				eachRow[i].setMovieName(rs.getString("name"));

				eachRow[i].setIssuedDate(rs.getString("issueDate"));
				if (rs.getString("returndate").equalsIgnoreCase("0"))
					eachRow[i].setReturndate("Not Returned");
				else
					eachRow[i].setReturndate(rs.getString("returndate"));
				float billAmount = 5;
				eachRow[i].setBillAmount(billAmount);
				i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}

				if (con != null) {
					con.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return eachRow;
	}

	/**
	 * Show movies to return.
	 * 
	 * @param strUserID
	 *            the str user id
	 * @return the array list
	 */
	public ArrayList<Movie> showMoviesToReturn(String strUserID) {
		ArrayList<Movie> movieList = new ArrayList<Movie>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = dataSource.getConnection();

			pstmt = con
					.prepareStatement("SELECT movie.name as name,movie.id as id from movie, transaction "
							+ "WHERE  movie.id=transaction.movieId AND transaction.userId=? AND returndate='0'");
			pstmt.setInt(1, Integer.parseInt(strUserID));
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Movie movie = new Movie();
				movie.setMovieName(rs.getString("name"));
				movie.setMovieId(rs.getInt("id"));
				movieList.add(movie);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}

				if (con != null) {
					con.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return movieList;
	}

}
