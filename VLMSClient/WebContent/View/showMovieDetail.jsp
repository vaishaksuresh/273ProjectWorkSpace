<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%><%@page import="com.vlms.sjsu.entity.Movie"%><%@page
	import="com.vlms.sjsu.entity.User"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Detail</title>
<meta charset="UTF-8" />
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<link rel="stylesheet" type="text/css" href="css/reset.css">
<link rel="stylesheet" type="text/css" href="css/structure.css">
</head>
<body>
	<%
		System.out.println("in showmovie and username detail.jsp");
		Movie movie = (Movie) session.getAttribute("moviedetail");
	%>
	<div id="navbar">
		<jsp:include page="navbar.jsp"></jsp:include>
	</div>
	<div id="container">
		<div id="content" style="height: 110%;">
			<table style="width: 100%;">
				<tr>
					<th>Movie Id</th>
					<th>Movie Name</th>
					<th>Movie Banner</th>
					<th>Rent Amount</th>
					<th>Release Date</th>
					<th>Category</th>
					<th>Copies</th>
				</tr>

				<tr>
					<td align="center"><c:out value="${moviedetail.movieId }"></c:out></td>
					<td align="center"><c:out value="${moviedetail.movieName }"></c:out></td>
					<td align="center"><c:out value="${moviedetail.movieBanner }"></c:out></td>
					<td align="center"><c:out value="${moviedetail.rentAmount }"></c:out></td>
					<td align="center"><c:out value="${moviedetail.releaseDate }"></c:out></td>
					<td align="center"><c:out value="${moviedetail.category }"></c:out></td>
					<td align="center"><c:out
							value="${moviedetail.availableCopies}"></c:out></td>

				</tr>

			</table>
			<br>

			<c:if test="${movierentuser ne null}">
				<table>
					<tr>
						<th>Users Who Rented This Movie</th>
					</tr>
					<c:forEach items="${movierentuser}" var="user">
						<tr>
							<td align="center"><c:out value="${user.firstName}"></c:out></td>
						</tr>
					</c:forEach>
				</table>
			</c:if>
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