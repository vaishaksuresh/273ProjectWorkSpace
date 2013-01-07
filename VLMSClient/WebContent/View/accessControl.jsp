<!DOCTYPE HTML>
<html>
<head>
<title>Simple Market Place</title>
<meta charset="UTF-8" />
<link rel="stylesheet" type="text/css" href="css/reset.css">
<link rel="stylesheet" type="text/css" href="css/structure.css">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
</head>

<body>


	<form class="box login" name="login" method="post" action="LoginAction">
		<h3>
			<c:if test="${message ne '' }">
				<c:out value="${message }"></c:out>
			</c:if>
		</h3>
		<fieldset class="boxBody">
			<label>Username</label> <input type="text" tabindex="1"
				placeholder="Enter Email" required name="email">
			<!-- <label><a href="#" class="rLink" tabindex="5">Forget your password?</a>Password</label> -->
			<input type="password" tabindex="2" required name="password">
		</fieldset>
		<footer>
			<!-- <label><input type="checkbox" tabindex="3">Keep me logged in</label>-->
			<input type="submit" class="btnLogin" value="Login" tabindex="3">
			<input type="button" class="btnLogin" value="Sign Up" tabindex="3"
				onclick="window.location='SignUp.jsp'">
		</footer>
	</form>
	<footer id="main"> </footer>
</body>
</html>
