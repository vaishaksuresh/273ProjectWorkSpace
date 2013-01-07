<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<!DOCTYPE HTML>
<html>
<head>
<title>Simple Market Place</title>
<meta charset="UTF-8" />
<link rel="stylesheet" type="text/css" href="css/reset.css">
<link rel="stylesheet" type="text/css" href="css/structure.css">
</head>
<body>
	<c:if test='${sessionScope.user.firstName == null}'>
		<c:redirect url="/View/index.jsp" />
	</c:if>
	<header>
		<h3>
			<c:out
				value="Welcome ${user.firstName} ${user.lastName}, You logged in last on ${user.lastLogin }"></c:out>
		</h3>
	</header>
	
	<footer id="main" draggable="true">Logout</footer>
</body>
</html>
