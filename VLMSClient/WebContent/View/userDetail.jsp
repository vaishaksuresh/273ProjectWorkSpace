<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%><%@page import="com.vlms.sjsu.entity.User"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Detail Lookup</title>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<meta charset="UTF-8" />
<link rel="stylesheet" type="text/css" href="css/reset.css">
<link rel="stylesheet" type="text/css" href="css/structure.css">
</head>
<body>
	<div id="navbar"><jsp:include page="navbar.jsp"></jsp:include></div>

	<%
		User tempo = (User) session.getAttribute("userdetailsobject");
	%>
	<div id="container">
		<div id="content" style="height: 110%;">

			<table style="width: 100%">
				<tr>
					<th>User Id</th>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Street</th>
					<th>City</th>
					<th>State</th>
					<th>Zip Code</th>
					<th>Member Type</th>
					<th>Subscription fee</th>
					<th>Balance</th>
				</tr>
				<tr>
					<td align="center"><c:out value="${userdetailsobject.userId }"></c:out></td>
					<td align="center"><c:out
							value="${userdetailsobject.firstName }"></c:out></td>
					<td align="center"><c:out
							value="${userdetailsobject.lastName }"></c:out></td>
					<td align="center"><c:out
							value="${userdetailsobject.address }"></c:out></td>
					<td align="center"><c:out value="${userdetailsobject.city }"></c:out></td>
					<td align="center"><c:out value="${userdetailsobject.state }"></c:out></td>
					<td align="center"><c:out
							value="${userdetailsobject.zipCode }"></c:out></td>
					<td align="center"><c:out
							value="${userdetailsobject.memberType }"></c:out></td>
					<td align="center"><c:out
							value="${userdetailsobject.subscriptionFee }"></c:out></td>
					<td align="center"><c:out
							value="${userdetailsobject.balance }"></c:out></td>

				</tr>
			</table>
			<br/>
			<br/>
			<c:if test="${usermovielist ne null }">
				<div align="center">
					<table width="100%">
						<tr>
							<th>Movies Rented</th>
						</tr>
							<c:forEach items="${usermovielist}" var="user">
							<tr>
								<td align="center"><c:out value="${user.movieName }"></c:out></td>
							</tr>
							</c:forEach>
					</table>
				</div>
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