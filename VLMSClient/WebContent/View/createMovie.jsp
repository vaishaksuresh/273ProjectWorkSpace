<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/reset.css">
<link rel="stylesheet" type="text/css" href="css/structure.css">
<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.9.2/themes/base/jquery-ui.css" />
<script src="http://code.jquery.com/jquery-1.8.3.js"></script>
<script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>

<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<title>Video Library Management System : Sign Up</title>

<script>
    $(function() {
        $( "#releasedate" ).datepicker();
    });
    </script>

</head>
<body>
	<c:if test='${sessionScope.user.firstName == null}'>
		<c:redirect url="accessControl.jsp" />
	</c:if>
	<div id="navbar">
		<jsp:include page="navbar.jsp"></jsp:include>
	</div>
	<div id="container">
		<div id="content" style="height: 110%;">
			<form action="CreateMovieAction" id="form1" method="post">
				<c:if test="${message ne '' }">
					<h3>
						<c:out value="${message }"></c:out>
					</h3>
				</c:if>
				<table style="width: 100%">
					<tr>
						<td><b>Movie Name *</b></td>
						<td><input type="text" name="moviename" required /></td>
					</tr>
					<tr>
						<td><b>Production Name *</b></td>
						<td><input type="text" name="productionname" required /></td>
					</tr>
					<tr>
						<td><b>Release Date *</b></td>
						<td><input type="text" name="releasedate" id="releasedate" readonly="readonly" required /></td>
					</tr>
					<tr>
						<td><b>Cost *</b></td>
						<td><input type="text" name="rentamount" required /></td>
					</tr>
					<tr>
						<td><b>Category *</b></td>
						<td><select class="cjComboBox" name="category">
								<option value="Action">Action</option>
								<option value="Adventure">Adventure</option>
								<option value="Comedy">Comedy</option>
								<option value="Drama">Drama</option>
								<option value="Horror">Horror</option>
								<option value="Sci-fi">Sci-fi</option>
								<option value="Thriller">Thriller</option>
						</select></td>
					</tr>
					<tr>
						<td><b>Copies Available *</b></td>
						<td><input type="text" name="availablecopies" required /></td>
					</tr>
					<tr>
						<td colspan="2"><input type="submit" class="btnLogin"
							value="Create Movie" name="createMovieBtn" /></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>