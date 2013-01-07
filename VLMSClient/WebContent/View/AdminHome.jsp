<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<!DOCTYPE HTML>
<html>
<head>
<title>Video Library Management System : Update Profile</title>
<meta charset="UTF-8" />
<link rel="stylesheet" type="text/css" href="css/reset.css">
<link rel="stylesheet" type="text/css" href="css/structure.css">
</head>
<body>
	<c:if
		test='${sessionScope.user.firstName == null || sessionScope.user.memberType ne "admin"}'>
		<c:redirect url="accessControl.jsp" />
	</c:if>
	<div id="navbar">
		<jsp:include page="navbar.jsp"></jsp:include>
	</div>
	<div id="container">
		<div id="content" style="height: 110%;">
			<c:if test="${message ne '' }">
				<h3>
					<c:out value="${message }"></c:out>
				</h3>
				<br/>
			</c:if>
			<table style="width: 100%;text-align: center;">
				<tr>
					<td><b><a href="ListAllUser.jsp">List All Users</a></b></td>
				</tr>
				<tr>
					<td><b><a href="AdminSearchMovie.jsp">List All Movies</a></b></td>
				</tr>
				<tr>
					<td><b><a href="FindUser?ssn=&UserMemberType=premium">List All Premium Users</a></b></td>
				</tr>
				<tr>
					<td><b><a href="FindUser?ssn=&UserMemberType=simple">List All Simple Users</a></b></td>
				<tr>
					<td><b><a href="CreateMovieAction">Create Movie</a></b></td>
				</tr>
			</table>
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
