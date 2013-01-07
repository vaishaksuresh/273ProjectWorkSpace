<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/navbar.css">
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
</head>


<body>
	<ul class="navigation">

		<li><c:if test="${not empty user.firstName}">
				<h3>
					<c:out value="Welcome, ${user.firstName}"></c:out>
				</h3>
			</c:if>
			<p>
				<a href="HomeAction">Home</a>
			</p></li>
		<li>
			<h3>Search Movie</h3>
			<p>
				<a href="FindMovie.jsp">Look for a Movie</a>
			</p>
		</li>
		<li>
			<h3>Update Profile</h3>
			<p>
				<a href="UpdateProfileAction">Change My Details</a>
			</p>
		</li>
		<!-- SUD Start -->
		<c:if
			test="${(user.memberType eq 'admin')}">
			<li>
				<h3>Admin Home</h3>
				<p>
					<a href="AdminHome.jsp">Manage Movies/Users</a>
				</p>
			</li>
		</c:if>
		<!-- SUD Ends -->
		<li>
			<h3>Search People</h3>
			<p>
				<a href="FindUser.jsp">Look for Users</a>
			</p>
		</li>
		<c:if
			test="${(user.memberType ne 'admin')}">
			<li>
				<h3>Generate Bill</h3>
				<p>
					<a href="GenerateBillAction">See My Transactions</a>
				</p>
			</li>
		</c:if>
		<li>
			<h3>Logout</h3>
			<p>
				<a href="logout.jsp">Sign Out</a>
			</p>
		</li>

	</ul>
</body>
</html>