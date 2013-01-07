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
		<c:redirect url="accessControl.jsp" />
	</c:if>
	<div id="navbar">
		<jsp:include page="navbar.jsp"></jsp:include>
	</div>
	<div id="container">
		<div id="content">
			<c:choose>
				<c:when test="${Ads ne null }">
					<h2>My Posted Advertisement</h2>
					<table style="width: 100%;">
						<tr>
							<th>Title</th>
							<th>Description</th>
							<th>Quantity Available</th>
							<th>Price</th>
						</tr>

						<c:forEach items="${Ads}" var="ad">
							<tr>
								<td><c:out value="${ad.adTitle }"></c:out></td>
								<td><c:out value="${ad.adDescription }"></c:out></td>
								<td align="center"><c:out value="${ad.adQty }"></c:out></td>
								<td align="center"><c:out value="${ad.price }"></c:out></td>
							</tr>
						</c:forEach>
					</table>
				</c:when>
				<c:otherwise>
					<h2>You have not Posted any Ads yet.</h2>
				</c:otherwise>
			</c:choose>
			<br> <br>
			<h2>Add New Advertisement</h2>
			<h3>
				<c:if test="${message ne '' }">
					<c:out value="${message }"></c:out>
				</c:if>
			</h3>
			<form method="post" action="PostAdvertisement">
				<table style="width: 100%;">
					<tr>
						<td><b>Title</b></td>
						<td><input type="text" width="30%" maxlength="30"
							name="adTitle"></td>
					</tr>
					<tr>
						<td><b>Description</b></td>
						<td><textarea rows="4" cols="21" name="adDesc"></textarea></td>
					</tr>
					<tr>
						<td><b>Quantity</b></td>
						<td><input type="text" name="adQuantity"></td>
					</tr>
					<tr>
						<td><b>Price per Item</b></td>
						<td><input type="text" name="adPrice"></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td><input type="submit" name="submitAd" value="Post">
							<input type="reset" name="resetAd" value="Reset"
							onclick="form.reset();"></td>
					</tr>
				</table>
			</form>
		</div>
		<div id="footer">
			<p>
				<strong><c:out
						value="You logged in last on ${user.lastLogin }"></c:out></strong>
			</p>
		</div>
	</div>


</body>
</html>
