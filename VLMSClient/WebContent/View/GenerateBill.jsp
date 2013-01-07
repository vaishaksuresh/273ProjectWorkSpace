<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.vlms.sjsu.entity.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Video Library Management System : Generate Bill</title>
<link rel="stylesheet" type="text/css" href="css/reset.css">
<link rel="stylesheet" type="text/css" href="css/structure.css">
<style>
table {
	margin-left: auto;
	margin-right: auto;
}
</style>

</head>
<body>

	<div id="navbar">
		<jsp:include page="navbar.jsp"></jsp:include>
	</div>

	<div id="container">
		<div id="content">

			<%
				String amount="0";
				User user = (User) session.getAttribute("user");
				if (user == null)
					System.out.println("user is null ");
				String memberType = user.getMemberType();
				if (memberType == null)
					System.out.println("memberType is null ");

				if (memberType.equalsIgnoreCase("Premium"))

				{
					String fees = "$ 100";
			%>
			<p align="center">
				<%
					out.println("<h2 align=\"center\">Monthly Subscription Fees : " + fees+"</h2");
				%>
			</p>
			<br>
			<%
				} else {
					if (request.getAttribute("resultArray") == null) {
			%>
			<p align="center">
				<%
					out.println("<h2 align=\"center\"> You Havent Rented Any Movies</h2>");
				%>
			</p>
			<br>
			<%
				} else {
						Bill[] result1 = (Bill[]) request
								.getAttribute("resultArray");
						int length = result1.length * 10;
						amount = Integer.toString(length);
			%>
			<br>
			<%
				}
				}
			%>

			<%
				if (request.getAttribute("resultArray") != null) {
			%>
			<p align="center">
				<%
					out.println("<h2 align=\"center\">Monthly Transaction Details</h2>");
				%>
			</p>
			<br/>
			<table width="100%">
				<tr>
					<th>Movie Name</th>
					<th>Issue Date</th>
					<th>Return Date</th>

				</tr>

				<%
					Bill[] result = (Bill[]) request.getAttribute("resultArray");
						for (int i = 0; i < result.length; i++) {
				%>
				<tr>
					<td align="center">
						<%
							out.println(result[i].getMovieName());
						%>
					</td>
					<td align="center">
						<%
							out.println(result[i].getIssuedDate());
						%>
					</td>
					<td align="center">
						<%
							out.println(result[i].getReturndate());
						%>
					</td>
				</tr>
				<%
					}
					}
				%>
			</table>
			<% if (!memberType.equalsIgnoreCase("Premium")){%>
			
			<br/>
			<p align="center">
				<%
					out.println("<h2 align=\"center\">You owe : $" + amount + "</h2>");
				%>
			</p>
			<%} %>
			
		</div>
		<div id="footer">
			<p>
				<strong><c:out
						value="You logged in last on ${user.lastlogin }"></c:out></strong>
			</p>
		</div>
	</div>
</body>
</html>
