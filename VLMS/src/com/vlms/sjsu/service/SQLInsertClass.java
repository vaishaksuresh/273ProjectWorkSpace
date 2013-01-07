package com.vlms.sjsu.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.vlms.sjsu.util.AppUtils;

public class SQLInsertClass {
	static Connection con = null;
	static ResultSet rs = null;
	static Statement stmt = null;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Long startTime = System.currentTimeMillis();

		/*try {
			String email = "simple@email.com";
			String firstname = "John";
			String lastname = "Doe";
			String password = "password";
			String mdPassword = AppUtils.md5(password);
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/vlms", "vlmsadmin",
					"vlmspassword");
			stmt = con.createStatement();

			for (int i = 0; i <= 10000; i++) {
				String query = "INSERT INTO `vlms`.`user`(`email`,`password`,"
						+ "`firstname`,`lastname`,`address`,`city`,`state`,`zipcode`,"
						+ "`membertype`,`subscriptionfee`,`balance`)"
						+ "VALUES('"
						+ email
						+ i
						+ "', '"
						+ mdPassword
						+ "', '"
						+ firstname
						+ i
						+ "', '"
						+ lastname
						+ "', 'Address', 'San Jose', 'CA', '95134', 'Premium', '100',"
						+ "'0')";
				System.out.println(query.toString());

				Class.forName("com.mysql.jdbc.Driver").newInstance();

				stmt.executeUpdate(query);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("Average Time : "
				+ ((System.currentTimeMillis() - startTime) / 1000f));

		try {
			String email = "premium@email.com";
			String firstname = "Jane";
			String lastname = "Smith";
			String password = "password";
			String mdPassword = AppUtils.md5(password);
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/vlms", "vlmsadmin",
					"vlmspassword");
			stmt = con.createStatement();

			for (int i = 0; i <= 10000; i++) {
				String query = "INSERT INTO `vlms`.`user`(`email`,`password`,"
						+ "`firstname`,`lastname`,`address`,`city`,`state`,`zipcode`,"
						+ "`membertype`,`subscriptionfee`,`balance`)"
						+ "VALUES('"
						+ email
						+ i
						+ "', '"
						+ mdPassword
						+ "', '"
						+ firstname
						+ i
						+ "', '"
						+ lastname
						+ "', 'Address', 'San Jose', 'CA', '95134', 'Premium', '100',"
						+ "'0')";
				System.out.println(query.toString());

				Class.forName("com.mysql.jdbc.Driver").newInstance();

				stmt.executeUpdate(query);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("Average Time : "
				+ ((System.currentTimeMillis() - startTime) / 1000f));*/

		try {

			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/vlms", "vlmsadmin",
					"vlmspassword");
			PreparedStatement stmt = con
					.prepareStatement("INSERT INTO `movie` "
							+ "( `name`, `production`, `releaseDate`, `rentAmount`, `category`, `availableCopies`)"
							+ "VALUES(?,?,?,10.0,?,10 )");

			for (int i = 0; i < 1000; i++) {

				stmt.setString(1, "ActionMovie" + i);
				stmt.setString(2, "Production1");
				stmt.setString(3, getCurrentTime());
				stmt.setString(4, "Action");

				stmt.executeUpdate();
			}
			for (int i = 1000; i < 2000; i++) {

				stmt.setString(1, "AdventureMovie" + i);
				stmt.setString(2, "Production2");
				stmt.setString(3, getCurrentTime());
				stmt.setString(4, "Adventure");
				stmt.executeUpdate();
			}
			for (int i = 2000; i < 3000; i++) {
				stmt.setString(1, "ComedyMovie" + i);
				stmt.setString(2, "Production3");
				stmt.setString(3, getCurrentTime());
				stmt.setString(4, "Comedy");
				stmt.executeUpdate();
			}
			for (int i = 3000; i < 4000; i++) {
				stmt.setString(1, "DramaMovie" + i);
				stmt.setString(2, "Production4");
				stmt.setString(3, getCurrentTime());
				stmt.setString(4, "Drama");
				stmt.executeUpdate();
			}
			for (int i = 4000; i < 5000; i++) {
				stmt.setString(1, "HorrorMovie" + i);
				stmt.setString(2, "Production5");
				stmt.setString(3, getCurrentTime());
				stmt.setString(4, "Horror");
				stmt.executeUpdate();
			}
			for (int i = 5000; i < 6000; i++) {
				stmt.setString(1, "SciFiMovie" + i);
				stmt.setString(2, "Production6");
				stmt.setString(3, getCurrentTime());
				stmt.setString(4, "SciFi");
				stmt.executeUpdate();
			}
			for (int i = 6000; i < 7000; i++) {
				stmt.setString(1, "ThrillerMovie" + i);
				stmt.setString(2, "Production7");
				stmt.setString(3, getCurrentTime());
				stmt.setString(4, "Thriller");
				stmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		System.out.println("Average Time : "
				+ ((System.currentTimeMillis() - startTime) / 1000f));
	}

	public static String getCurrentTime() {
		final String DATE_FORMAT_NOW = "yyyy-dd-MM HH:mm";
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
		return sdf.format(cal.getTime());
	}
}
