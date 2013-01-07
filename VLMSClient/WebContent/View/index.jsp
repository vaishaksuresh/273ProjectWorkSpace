<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<!DOCTYPE HTML>
<html>
<head>
<title>Video Library Management System : Home</title>
<meta charset="UTF-8" />
<link rel="stylesheet" type="text/css" href="css/reset.css">
<link rel="stylesheet" type="text/css" href="css/structure.css">
</head>
<body>
	<c:if test='${sessionScope.user.firstName == null}'>
		<c:redirect url="accessControl.jsp" />
	</c:if>
	<div id="navbar">
		<jsp:include page="navbar.jsp"></jsp:include>
	</div>
	<div id="container">
		<div id="content">
			<h3>
				<c:if test="${message ne '' and moviesToReturn ne null}">
					<c:out value="${message }"></c:out>
					<br/>
				</c:if>
			</h3>
			<c:choose>
				<c:when test="${moviesToReturn ne null }">
					<h2>Currently Rented Movies</h2>
					<table style="width: 100%;">
						<tr>
							<th>Movie Name</th>
							<th>Action</th>
						</tr>

						<c:forEach items="${moviesToReturn}" var="movies">

							<tr>
								<td align="center"><c:out value="${movies.movieName }"></c:out></td>
								<td align="center"><form action="ReturnMovieAction"
										method="post">
										<input type="submit" value="return"> <input
											name="movieID" type="hidden"
											value="<c:out value="${movies.movieId }"></c:out>">
									</form></td>
							</tr>
						</c:forEach>
					</table>
				</c:when>
				<c:otherwise>
					<h2>You do not have any movies issued</h2>
				</c:otherwise>
			</c:choose>
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
