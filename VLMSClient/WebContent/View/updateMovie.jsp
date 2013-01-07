<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/reset.css">
<link rel="stylesheet" type="text/css" href="css/structure.css">
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.9.2/themes/base/jquery-ui.css" />
<script src="http://code.jquery.com/jquery-1.8.3.js"></script>
<script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>
<title>Video Library Management System : Sign Up</title>
</head>
<body>
	<script>
	    $(function() {
	        $( "#releasedate" ).datepicker();
	    });
		$(document).ready(
				function() {
							
				 $('#form1').submit(function(event){
					   var rent=$('#rentamount').val();
					   var copies=$('#availablecopies').val();
					   var intRegex = /^\d+$/;
					   var floatRegex = /^((\d+(\.\d *)?)|((\d*\.)?\d+))$/;
					   
					   if(!rent.match(floatRegex)){
							alert("Enter proper rent amount");
							$("#rentamount").focus();
							event.preventDefault();
					   }
						else if(!copies.match(intRegex)){
							alert("Enter proper copies");
							$("#availablecopies").focus();
							event.preventDefault();
					   }
						else{
							return true;
						}
				}); // end submit

		});//end ready
	</script>
	<c:if test='${sessionScope.user.firstName == null}'>
		<c:redirect url="accessControl.jsp" />
	</c:if>
	<div id="navbar">
		<jsp:include page="navbar.jsp"></jsp:include>
	</div>
	<div id="container">
		<div id="content" style="height: 110%;">
			<form action="UpdateMovieAction" id="form1" method="post">
				<h3>
					<c:if test="${message ne '' }">
						<c:out value="${message }"></c:out>
					</c:if>
					<br/>
				</h3>
				<table>
					<tr>
						<td>Movie Name *</td>
						<td><input type="text" name="movieName" required
							value="${movieDetails.movieName}" /> <input type="hidden"
							name="movieID" value="${movieDetails.movieId}" /> <%
 	session.setAttribute("movieDetails",
 			request.getAttribute("movieDetails"));
 %></td>
					</tr>
					<tr>
						<td>Production Name *</td>
						<td><input type="text" name="movieBanner" required
							value="${movieDetails.movieBanner}" /></td>
					</tr>
					<tr>
						<td>Release Date *</td>
						<td>
							<input type="text" name="releasedate" id="releasedate" readonly="readonly" required />
						</td>
					</tr>
					<tr>
						<td>Cost *</td>
						<td><input type="text" id="rentAmount" name="rentAmount" required
							value="${movieDetails.rentAmount}" /></td>
					</tr>
					<tr>
						<td>Category *</td>
						<td><select class="cjComboBox" name="category">
								<option value="Action"
									${movieDetails.category == 'Action' ? 'selected' : ''}>Action</option>
								<option value="Adventure"
									${movieDetails.category == 'Adventure' ? 'selected' : ''}>Adventure</option>
								<option value="Comedy"
									${movieDetails.category == 'Comedy' ? 'selected' : ''}>Comedy</option>
								<option value="Drama"
									${movieDetails.category == 'Drama' ? 'selected' : ''}>Drama</option>
								<option value="Horror"
									${movieDetails.category == 'Horror' ? 'selected' : ''}>Horror</option>
								<option value="Sci-fi"
									${movieDetails.category == 'Sci-fi' ? 'selected' : ''}>Sci-fi</option>
								<option value="Thriller"
									${movieDetails.category == 'Thriller' ? 'selected' : ''}>Thriller</option>
						</select></td>
					</tr>
					<tr>
						<td>Copies Available *</td>
						<td><input type="text" id="availableCopies" name="availableCopies" required
							value="${movieDetails.availableCopies}" /></td>
					</tr>
					<tr>
						<td colspan="2"><input type="submit" class="btnLogin"
							value="Update Movie" name="updateMovieBtn" /></td>
					</tr>
				</table>
			</form>

		</div>
	</div>
</body>
</html>