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

			<br>
			<h2>Search Movie</h2>
			<h4>
				<c:if test="${message ne '' }">
					<c:out value="${message }"></c:out>
				</c:if>
			</h4>
			<form method="post" action="FindMovie">
				<table style="width: 100%;">
				
					<tr>
						<td><b>Movie Name</b></td>
						<td><input type="text" name="mvname" value="${savedName }"></td>
					</tr>
					<tr>
						<td><b>Production</b></td>
						<td><input type="text" name="mvproduction"
							value="${savedBanner }"></td>
					</tr>
					<tr>
						<td><b>Release Date</b></td>
						<td><input type="text" name="mvdate" value="${savedDate }"></td>
					</tr>
					<tr>
					<td><b>Category *</b></td>
						<td>
							<select class="cjComboBox" name="mvcategory" >
								<option value="">Select</option>
								<option value="Action" >Action</option>
								<option value="Adventure" >Adventure</option>
								<option value="Comedy" >Comedy</option>
								<option value="Drama" >Drama</option>
								<option value="Horror">Horror</option>
								<option value="Sci-fi" >Sci-fi</option>
								<option value="Thriller" >Thriller</option>
							</select>
						</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td><input type="submit" name="submitMovie" value="Submit"></td>
					</tr>
				</table>
			</form>
			<c:choose>
				<c:when test="${SearchMovie ne null }">
					<br>
					<h2>Searched Movies</h2>
					<h4>
						<c:if test="${message1 ne '' }">
							<c:out value="${message1 }"></c:out>
						</c:if>
					</h4>
					<table style="width: 100%;">
						<tr>
							<th>Movie ID</th>
							<th>Movie Name</th>
							<th >Available Copies</th>
							<c:choose>
								<c:when
									test="${(not empty user.firstName) && (user.memberType ne 'admin')}">
									<th style="width:100px;">Action</th>
								</c:when>
								<c:otherwise>
									<th style="width:350px;">Actions</th>
								</c:otherwise>
							</c:choose>
						</tr>

						<c:forEach items="${SearchMovie}" var="movies">
							<tr>
								<td align="center"><c:out value="${movies.movieId }"></c:out></td>
								<td align="center"><a href="SearchMovieAction?movieId=${movies.movieId}">${movies.movieName }</a></td>
								<td align="center"><c:out value="${movies.availableCopies }"></c:out></td>
								<c:if
									test="${(not empty user.firstName) && (user.memberType eq 'admin')}">
									<td align="center" style="width:350px;">
										<div style="float:left;width:100px;">
											<form name="UpdateMovieAction" method="post"
												action="UpdateMovieAction">
												<input type="submit"  style="width: 100px;"
													name="updateMovie" value="Update" /> <input type="hidden"
													name="movieID" value="${movies.movieId}" /> <input
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
												<input type="submit" style="width: 100px;"
													name="deleteMovie" value="Delete" /> <input type="hidden"
													name="movieID" value="${movies.movieId}" /> <input
													type="hidden" name="movieName" value="${movies.movieName}" />
											</form>
										</div>
										<div style="float:left;width:100px;">
											<form name="SearchMovieAction"  method="post"
												action="SearchMovieAction">
												<input type="submit" value="More details" style="width: 100px;"> <input
													type="hidden" name="movieId" value="${movies.movieId }">
											</form>
										</div>
									</td>
								</c:if>
								<c:if
									test="${(not empty user.firstName) && (user.memberType ne 'admin')}">
									<td style="width:75px;" align="center">
										<c:choose>
											<c:when test="${movies.availableCopies gt 0}">
												<form name="IssueMovieAction" method="post"
													action="IssueMovieAction">
													<input type="submit" style="width: 100px;"
														name="issueMovie" value="Issue" />
													<input type="hidden"
														name="movieID" value="${movies.movieId}" />
													<input
														type="hidden" name="movieName" value="${movies.movieName}" />
												</form>
											</c:when>
											<c:otherwise>
												<input type="button" style="width: 100px;"
													value="Out of stock" disabled="disabled" />
											</c:otherwise>
										</c:choose>
									</td>
								</c:if>
							</tr>
						</c:forEach>
					</table>
				</c:when>

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
