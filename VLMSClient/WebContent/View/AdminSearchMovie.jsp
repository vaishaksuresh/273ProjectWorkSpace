<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<!DOCTYPE HTML>
<html>
<head>
<title>Find Movie</title>
<meta charset="UTF-8" />
<link rel="stylesheet" type="text/css" href="css/reset.css">
<link rel="stylesheet" type="text/css" href="css/structure.css">
</head>
<body>



	<div id="navbar">
		<jsp:include page="navbar.jsp"></jsp:include>
	</div>
	<div id="container">
		<div id="content">
			<h2>Search Movie</h2>
			<h3>
				<c:if test="${message ne '' }">
					<c:out value="${message }"></c:out>
				</c:if>
			</h3>


			<form method="post" action="AdminSearchMovie">
				<select name="selCount">
					<option value="100">100</option>
					<option value="500">500</option>
					<option value="1000">1000</option>
					<option value="all">All</option>
				</select> <br> <br> <input type="submit" name="submitMovie"
					value="Submit">
			</form>
			<c:choose>
				<c:when test="${AdminSearchMovie ne null }">

					<h2>Searched Movies</h2>
					<table style="width: 100%;">
						<tr>
							<th>Movie Name</th>
							<th>Production</th>
							<th>Release Date</th>
							<th>Category</th>
							<c:choose>
								<c:when
									test="${(not empty user.firstName) && (user.memberType ne 'admin')}">
									<th style="width:100px;">Actions</th>
								</c:when>
								<c:otherwise>
									<th style="width:350px;">Actions</th>
								</c:otherwise>
							</c:choose>
						</tr>

						<c:forEach items="${AdminSearchMovie}" var="movies">
							<tr>
								<td align="center"><c:out value="${movies.movieName }"></c:out></td>
								<td align="center"><c:out value="${movies.movieBanner }"></c:out></td>
								<td align="center"><c:out value="${movies.releaseDate }"></c:out></td>
								<td align="center"><c:out value="${movies.category }"></c:out></td>
								<c:if
									test="${(not empty user.firstName) && (user.memberType eq 'admin')}">
									<td align="center" style="width:350px;">
										<div style="float:left;width:100px;">
											<form name="UpdateMovieAction" method="post"
												action="UpdateMovieAction">
												<input type="submit" name="updateMovie" value="Update" /> <input
													type="hidden" name="movieID" value="${movies.movieId}" /> <input
													type="hidden" name="movieName" value="${movies.movieName}" />
												<input type="hidden" name="movieBanner"
													value="${movies.movieBanner}" /> <input type="hidden"
													name="releaseDate" value="${movies.releaseDate}" /> <input
													type="hidden" name="rentAmount" value="${movies.rentAmount}" />
												<input type="hidden" name="category"
													value="${movies.category}" /> <input type="hidden"
													name="availableCopies" value="${movies.availableCopies}" />
											</form>
										</div>
										<div style="float:left;width:100px;">
											<form name="DeleteMovieAction" method="post"
												action="DeleteMovieAction">
												<input type="submit" name="deleteMovie" value="Delete" /> <input
													type="hidden" name="movieID" value="${movies.movieId}" /> <input
													type="hidden" name="movieName" value="${movies.movieName}" />
											</form>
										</div>
										<div style="float:left;width:100px;">
											<form name="SearchMovieAction" method="post"
												action="SearchMovieAction">
												<input type="submit" value="More details"> <input
													type="hidden" name="movieId" value="${movies.movieId }">
											</form>
										</div>
									</td>
								</c:if>
								<c:if
									test="${(not empty user.firstName) && (user.memberType ne 'admin')}">
									<td><c:choose>
											<c:when test="${movies.availableCopies gt 0}">
												<form name="IssueMovieAction" method="post"
													action="IssueMovieAction">
													<input type="submit" name="issueMovie" value="Issue" /> <input
														type="hidden" name="movieID" value="${movies.movieId}" />
													<input type="hidden" name="movieName"
														value="${movies.movieName}" />
												</form>
											</c:when>
											<c:otherwise>
												<input type="button" class="btnLogin" style="width: 150px;"
													value="Out of stock" disabled="disabled" />
											</c:otherwise>
										</c:choose></td>
								</c:if>
								</td>
							</tr>
						</c:forEach>

					</table>
				</c:when>

			</c:choose>

			<br> <br>

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
