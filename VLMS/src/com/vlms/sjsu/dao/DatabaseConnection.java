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

import com.vlms.sjsu.entity.Bill;
import com.vlms.sjsu.entity.Movie;
import com.vlms.sjsu.entity.User;

/**
 * @author vaishaksuresh
 * 
 */
public class DatabaseConnection {
	Connection con = null;
	static ResultSet rs = null;
	Statement stmt = null;

	public DatabaseConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/vlms", "vlmsadmin",
					"vlmspassword");
			stmt = con.createStatement();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String createUser(User objUser) {
		String result = "";
		int rowcount;

		try {
			// Check if a user with the same email already exists.
			PreparedStatement stmt = con
					.prepareStatement("SELECT userid FROM user WHERE email=?");
			stmt.setString(1, objUser.getEmail());
			System.out.println(stmt.toString());
			rs = stmt.executeQuery();
			rs.last();
			rowcount = rs.getRow();
			if (rowcount > 0) {
				result = "The email ID is already registered. Please try another email ID";
			} else {
				// If the user does not exist, create a user.
				String query = "INSERT INTO `vlms`.`user`(`email`,`password`,"
						+ "`firstname`,`lastname`,`address`,`city`,`state`,`zipcode`,"
						+ "`membertype`,`subscriptionfee`,`balance`)"
						+ "VALUES('" + objUser.getEmail() + "', '"
						+ objUser.getPassword() + "', '"
						+ objUser.getFirstName() + "', '"
						+ objUser.getLastName() + "', '" + objUser.getAddress()
						+ "', '" + objUser.getCity() + "', '"
						+ objUser.getState() + "', '" + objUser.getZipCode()
						+ "', '" + objUser.getMemberType() + "', ";
				if ("SIMPLE".equalsIgnoreCase(objUser.getMemberType())) {
					query = query + "'0'," + "'150')";
				} else if ("PREMIUM".equalsIgnoreCase(objUser.getMemberType())) {
					query = query + "'100'," + "'0')";
				}
				rowcount = stmt.executeUpdate(query);

				if (rowcount > 0) {
					result = "true";
				} else {
					result = "false: The data could not be inserted into the database";
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public String updateUser(User objUser) {
		String result = "";
		int rowcount;

		try {
			// Check if a user with the same email already exists.
			PreparedStatement stmt = con
					.prepareStatement("SELECT userid FROM user WHERE email=?");
			stmt.setString(1, objUser.getEmail());
			System.out.println(stmt.toString());
			rs = stmt.executeQuery();
			rs.last();
			rowcount = rs.getRow();
			if (rowcount < 0) {
				result = "There seems to be some problem and we cannot find your account";
			} else {
				StringBuffer strbQuery = new StringBuffer("UPDATE user set ");
				if (null != objUser.getFirstName()) {
					strbQuery.append("firstname = '" + objUser.getFirstName()
							+ "', ");
				}
				if (null != objUser.getLastName()) {
					strbQuery.append("lastname = '" + objUser.getLastName()
							+ "', ");
				}
				if (null != objUser.getPassword()) {
					strbQuery.append("password = '" + objUser.getPassword()
							+ "', ");
				}
				if (null != objUser.getAddress()) {
					strbQuery.append("address = '" + objUser.getAddress()
							+ "', ");
				}
				if (null != objUser.getCity()) {
					strbQuery.append("city = '" + objUser.getCity() + "', ");
				}
				if (null != objUser.getState()) {
					strbQuery.append("state = '" + objUser.getState() + "', ");
				}
				if (null != objUser.getZipCode()) {
					strbQuery.append("zipcode = '" + objUser.getZipCode()
							+ "', ");
				}
				if (null != objUser.getMemberType()) {
					strbQuery.append("membertype = '" + objUser.getMemberType()
							+ "', ");
				}
				strbQuery.append("subscriptionfee = '"
						+ String.valueOf(objUser.getSubscriptionFee()) + "', ");
				strbQuery.append("balance = '"
						+ String.valueOf(objUser.getBalance()) + "' ");
				strbQuery.append("WHERE email='" + objUser.getEmail() + "'");
				System.out.println(strbQuery.toString());
				rowcount = stmt.executeUpdate(strbQuery.toString());

				if (rowcount > 0) {
					result = "true";
				} else {
					result = "false: The data could not be inserted into the database";
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public User signIn(String strUsername, String mdPassword) {
		ResultSet rs;
		User objLoggedInUser = new User();
		try {

			String query = "SELECT * FROM user WHERE email='" + strUsername
					+ "'";
			rs = stmt.executeQuery(query);
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
					PreparedStatement stmt = con
							.prepareStatement("UPDATE user set lastlogin=? where userid=?");
					stmt.setString(1,
							DateFormat.getDateTimeInstance().format(new Date()));
					stmt.setInt(2, id);
					stmt.executeUpdate();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return objLoggedInUser;

	}

	public ArrayList<Movie> listAllMovies(int count) {
		String result = "";

		ArrayList<Movie> movies = new ArrayList<Movie>();
		try {
			// Retrieve all movies in the library
			String query;

			if (count != 0)
				query = "Select * from movie LIMIT " + count;
			else
				query = "Select * from movie";

			ResultSet rs = stmt.executeQuery(query);

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
		}

		return null;
	}

	public ArrayList<User> findUser(User objUser) {

		String result = "";
		ArrayList<User> users = new ArrayList<User>();

		StringBuffer strbQuery = new StringBuffer("select * from user where");

		if (0 != objUser.getUserId()) {
			strbQuery.append(" userid = " + objUser.getUserId() + " and ");
		}

		if (null != objUser.getFirstName()
				&& !"".equalsIgnoreCase(objUser.getFirstName())) {
			strbQuery.append(" firstname LIKE '%" + objUser.getFirstName()
					+ "%' and ");
		}
		if (null != objUser.getLastName()
				&& !"".equalsIgnoreCase(objUser.getLastName())) {
			strbQuery.append(" lastname LIKE '%" + objUser.getLastName()
					+ "%' and ");
		}
		if (null != objUser.getAddress()
				&& !"".equalsIgnoreCase(objUser.getAddress())) {
			strbQuery.append(" address LIKE '%" + objUser.getAddress()
					+ "%' and ");
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

		System.out.println(strbQuery.toString());
		String finalQuery = strbQuery.toString().substring(0,
				strbQuery.toString().lastIndexOf("and"));
		System.out.println(finalQuery);

		ResultSet rs;
		try {
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<Movie> findMovie(Movie objMovie) {
		String result = "";
		ArrayList<Movie> movies = new ArrayList<Movie>();

		StringBuffer strbQuery = new StringBuffer("select * from movie where ");

		if (null != objMovie.getCategory()
				&& !"".equalsIgnoreCase(objMovie.getCategory())) {
			strbQuery.append("  category = '" + objMovie.getCategory()
					+ "' and ");
		}
		if (!"".equalsIgnoreCase(objMovie.getMovieBanner())) {
			strbQuery.append("  production LIKE '%" + objMovie.getMovieBanner()
					+ "%' and ");
		}
		if (!"".equalsIgnoreCase(objMovie.getMovieName())) {
			strbQuery.append("  name LIKE '%" + objMovie.getMovieName()
					+ "%' and ");
		}
		if (!"".equalsIgnoreCase(objMovie.getReleaseDate())) {
			strbQuery.append("  releaseDate ='" + objMovie.getReleaseDate()
					+ "' and ");
		}

		// System.out.println("QUERY : " + strbQuery.toString());
		// String finalQuery =
		// String.copyValueOf(strbQuery.toString().toCharArray(), 0,
		// strbQuery.toString().length()-4);
		String finalQuery = strbQuery.toString().substring(0,
				strbQuery.toString().lastIndexOf("and"));
		System.out.println(" FINAL QUERY : " + finalQuery);

		ResultSet rs;
		try {
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

	/*
	 * SUD Starting my methods
	 */

	public String deleteMovie(String movieId) {
		String result = "";
		int rowcount = 0;
		try {
			// Check if a user has rented the movie.
			PreparedStatement stmt = con
					.prepareStatement("SELECT * FROM transaction WHERE movieID=?");
			stmt.setString(1, movieId);
			rs = stmt.executeQuery();
			rs.last();
			rowcount = rs.getRow();
			if (rowcount > 0) {
				result = "The movie is currently issue to one or more users.";
			} else {
				String query = "DELETE FROM movie WHERE id='" + movieId + "';";
				rowcount = stmt.executeUpdate(query);

				if (rowcount > 0) {
					result = "true";
				} else {
					result = "false: The movie could not be found in database";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public String createMovie(Movie objMovie) {
		String result = "";
		int rowcount;

		try {
			// Check if a movie already exists.
			PreparedStatement stmt = con
					.prepareStatement("SELECT id FROM movie WHERE name=? AND production=? AND releaseDate=?");
			stmt.setString(1, objMovie.getMovieName());
			stmt.setString(2, objMovie.getMovieBanner());
			stmt.setString(3, objMovie.getReleaseDate());
			System.out.println(stmt.toString());
			rs = stmt.executeQuery();
			rs.last();
			rowcount = rs.getRow();
			if (rowcount > 0) {
				result = "The movie already exists under the given banner name and release date";
			} else {
				// If the user does not exist, create a user.
				String query = "INSERT INTO `vlms`.`movie`(name,production,releaseDate,rentAmount,category,"
						+ "availableCopies)"
						+ "VALUES('"
						+ objMovie.getMovieName()
						+ "', '"
						+ objMovie.getMovieBanner()
						+ "', '"
						+ objMovie.getReleaseDate()
						+ "', '"
						+ objMovie.getRentAmount()
						+ "', '"
						+ objMovie.getCategory()
						+ "', '"
						+ objMovie.getAvailableCopies() + "'); ";
				System.out.println("qyery === " + query);
				rowcount = stmt.executeUpdate(query);

				if (rowcount > 0) {
					result = "true";
				} else {
					result = "false: The data could not be inserted into the database";
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public String updateMovie(Movie newMovieObj) {
		String result = "";
		int rowcount;

		try {
			// Check if a user with the same email already exists.
			String query = "SELECT COUNT(name) FROM movie WHERE id='"
					+ newMovieObj.getMovieId() + "'";
			rs = stmt.executeQuery(query);
			rs.last();
			rowcount = rs.getRow();
			if (rowcount < 0) {
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
				rowcount = stmt.executeUpdate(strbQuery.toString());

				if (rowcount > 0) {
					result = "true";
				} else {
					result = "false: The data could not be inserted into the database";
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	public String issueMovie(String movieId, User objUser) {
		String result = "";
		int rowcount = 0;
		int moviesCount = 0;
		boolean skip = false;

		try {
			String query = "SELECT * from transaction where userID='"
					+ objUser.getUserId() + "';";
			rs = stmt.executeQuery(query);
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
				query = "INSERT INTO transaction(userID,movieID,issueDate,returndate) VALUES ('"
						+ objUser.getUserId()
						+ "','"
						+ movieId
						+ "','"
						+ getCurrentTime() + "',0);";
				rowcount = stmt.executeUpdate(query);
				if (rowcount > 0) {
					Statement st = con.createStatement();
					ResultSet rs2 = st
							.executeQuery("SELECT * from movie where id='"
									+ movieId + "';");
					while (rs2.next()) {
						query = "UPDATE movie SET availableCopies='"
								+ (rs2.getInt("availableCopies") - rowcount)
								+ "' WHERE id='" + movieId + "';";
						stmt.executeUpdate(query);
					}
					result = "true";
				} else {
					result = "false: The data could not be inserted into the database";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public String getCurrentTime() {
		final String DATE_FORMAT_NOW = "yyyy-dd-MM HH:mm";
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
		return sdf.format(cal.getTime());
	}

	/*
	 * SUD ends
	 */
	public User getUserDetails(Integer strUserId)// dappa change
	{
		User userobj = new User();
		try {
			String query = "Select * from user where userid='" + strUserId
					+ "';";
			rs = stmt.executeQuery(query);

			while (rs.next()) {
				System.out.println("name == " + rs.getString("firstname"));

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
				System.out.println("Last Login:" + userobj.getLastlogin());

				// return user object enough?
				// session object of sign userbean
				// bean sending enuf?
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userobj;
	}

	public ArrayList<Movie> getUserMovieslist(int strUserId) {
		ResultSet rs;
		ArrayList<Movie> moviebig = new ArrayList<Movie>();
		// Movie movielist = new Movie();
		// try {
		// String query = "Select movieId from transaction where userId='"
		// + strUserId + "'";
		// System.out.println("query in user movies list----" + query);
		// rs = stmt.executeQuery(query);
		// rs.last();
		// movieIdlist = new int[rs.getRow()];
		// rs.beforeFirst();
		//
		// while (rs.next()) {
		// movieIdlist[counter] = rs.getInt("movieId");
		// System.out.println("movieIdlist[counter]---"
		// + movieIdlist[counter]);
		// counter++;
		// }
		// for (int i = 0; i < counter; i++) {
		// String query2 = "select name from movie where id='"
		// + movieIdlist[i] + "'";
		// System.out
		// .println("printing query in geting movie names seen by user"
		// + query2);
		// rs = stmt.executeQuery(query2);
		// while (rs.next()) {
		// movielist.setMovieName(rs.getString(1));
		// moviebig.add(movielist);
		// }
		// }
		//
		// } catch (Exception e) {
		// // TODO: handle exception
		// e.printStackTrace();
		// }
		// System.out.println("returning from movie list details");
		try {
			String query = "SELECT name from movie, transaction WHERE  movie.id=transaction.movieId AND transaction.userId='"
					+ strUserId + "'";
			System.out.println("Query:" + query);
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				Movie movielist = new Movie();
				movielist.setMovieName(rs.getString(1));
				moviebig.add(movielist);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return moviebig;
	}

	public Movie getMovieDetails(int strMovieId) // changed from Movie[]
	{
		ResultSet rs;
		Movie movieset = new Movie();
		System.out.println("in db of getmovie details getting");
		try {
			String query = "Select * from movie where id='" + strMovieId + "'";
			rs = stmt.executeQuery(query);

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
		}
		System.out.println("returning movieset details ---"
				+ movieset.getMovieName());
		return movieset;
	}

public ArrayList<User> movierenteduserlist(int strMovieId) {

		ArrayList<User> users = new ArrayList<User>();
		ResultSet rs;
//		int movieuserlist[] = null;
//		int movieusercounter = 0;

		try {
			String test="Select DISTINCT(user.userid), firstname from user,transaction WHERE user.userid=transaction.userId AND transaction.movieid='"+strMovieId+"'";
			rs=stmt.executeQuery(test);
			while(rs.next())
			{
				User usernamelist = new User();
				System.out.println("in the while loop of moviecounter..username:"+ rs.getString(1));
				usernamelist.setFirstName(rs.getString("firstname"));
				System.out.println("after setting to userobject in db--username"+ usernamelist.getFirstName());
				users.add(usernamelist);
			}
			//String userlist = "Select userId from transaction where movieid='"
				//	+ strMovieId + "'";
			//rs = stmt.executeQuery(userlist);
			//rs.last();
			//movieuserlist = new int[rs.getRow()];
			//rs.beforeFirst();
			//while (rs.next()) {
				//System.out
					//	.println("Select userId from transac table using that movie"
						//		+ rs.getInt(1));

				//movieuserlist[movieusercounter] = rs.getInt(1);
				//movieusercounter++;
			//}

			//for (int i = 0; i < movieusercounter; i++) {
				//String userlistname = "Select firstname from user where userid='"
					//	+ movieuserlist[i] + "'";
				//rs = stmt.executeQuery(userlistname);
				//System.out.println("the username completed query--"
					//	+ userlistname);

				//while (rs.next()) {
					//User usernamelist = new User();
					//System.out
						//	.println("in the while loop of moviecounter..username:"
							//		+ rs.getString(1));
					//usernamelist.setFirstName(rs.getString(1));
					//System.out
						//	.println("after setting to userobject in db--username"
							//		+ usernamelist.getFirstName());
					//users.add(usernamelist);
				//}

			//s}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		System.out.println("in db of userlist getting");
		return users;
	}public String returnMovie(int strMovieId, int strUserId) {
		String result = "";
		ResultSet rs;
		
		try {
			Statement stmt1 = con.createStatement();
			System.out.println("strMovieId==="+strMovieId+"strUserId === "+strUserId);
			String query = "UPDATE transaction SET returndate='"+getCurrentTime()+"' WHERE userID='"
			+ strUserId+"' AND movieID='"+strMovieId+"' AND returndate='0'";
			int rowcount = stmt.executeUpdate(query);
			System.out.println("return movie query :"+query);
			System.out.println("row count number :"+rowcount);
			if(rowcount>0){
			rs = stmt.executeQuery("SELECT * from movie where id='"+ strMovieId + "';");
			while (rs.next()) {
			query = "UPDATE movie SET availableCopies='"
			+ (rs.getInt("availableCopies") + 1)
			+ "' WHERE id='" + strMovieId + "';";
			stmt1.executeUpdate(query);
			}
			result = "success";
			}
			else{
				result="Movie already removed";
			}
			
			
		
		
			} 
		 catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}



	public User[] listAllUSers(String noOfRows) {
		// ResultSet rs;
		User[] eachRow = null;
		try {
			int noOfRows1 = new Integer(noOfRows);
			Statement stmt3 = null;
			stmt3 = con.createStatement();
			String query = ("SELECT * FROM user LIMIT " + noOfRows1);
			ResultSet rs = stmt3.executeQuery(query);
			int rsSize = 0;
			rs.last();
			rsSize = rs.getRow();
			eachRow = new User[rsSize];

			rs.beforeFirst();
			// User eachRow=new User();
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
				// eachRow[i]
				// .setMembershipNumber(rs.getString("membershipnumber"));
				eachRow[i].setSubscriptionFee((rs.getFloat("subscriptionfee")));
				eachRow[i].setBalance((rs.getFloat("balance")));

				i++;
			}
			System.out.println("Each row ==== " + eachRow.length);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return eachRow;

	}

	public String deleteUser(String strUserID) {

		String result = "";
		int rowcount;
		Statement stmt2 = null;
		try {
			System.out.println("user ID is : " + strUserID);
			stmt2 = con.createStatement();
			String query = ("delete from user where userid=" + strUserID);
			rowcount = stmt2.executeUpdate(query);
			if (rowcount > 0) {
				result = "Success";
				// System.out.println("Data Deleted successfully");
			} else {
				result = "Failure";
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	public Bill[] generateBill(String strUserID) {
		Bill[] eachRow = null;
		try {
			// int noOfRows1=new Integer(noOfRows);
			System.out.println("DB Call");
			Statement stmt4 = null;
			stmt4 = con.createStatement();
			String query = ("SELECT transaction.issueDate,transaction.returndate,movie.name as name FROM transaction,movie where (movie.id = transaction.movieID and transaction.userID ='"
					+ strUserID + "')");
			ResultSet rs = stmt4.executeQuery(query);
			int rsSize = 0;
			rs.last();
			rsSize = rs.getRow();
			eachRow = new Bill[rsSize];

			rs.beforeFirst();
			// User eachRow=new User();
			int i = 0;

			while (rs.next()) {
				eachRow[i] = new Bill();
				// int userId = rs.getInt("userID");
				// eachRow[i].setUserId(userId);

				// int movId = rs.getInt("movieID");
				// eachRow[i].setMovieId(movId);
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
		}
		return eachRow;
	}

	public ArrayList<Movie> showMoviesToReturn(String strUserID) {
		ArrayList<Movie> movieList = new ArrayList<Movie>();
		try {
			String query = "SELECT movie.name as name,movie.id as id from movie, transaction WHERE  movie.id=transaction.movieId AND transaction.userId='"
					+ strUserID + "' AND returndate='0'";
			System.out.println("Query:" + query);
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				Movie movie = new Movie();
				movie.setMovieName(rs.getString("name"));
				movie.setMovieId(rs.getInt("id"));
				movieList.add(movie);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return movieList;
	}

}
