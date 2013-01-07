<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%><%@ page import="com.vlms.sjsu.entity.User"%>
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
					<h2>My Transactions</h2>
					<table border="1" style="width: 100%; border-color: red;">
						<tr>
							<th>Title</th>
							<th>Buy/Sell</th>
							<th>Quantity</th>
							<th>Price</th>
							<th>Purchase Date</th>
						</tr>

						<c:forEach items="${Ads}" var="ad">
							<tr>
								<td align="center"><c:out value="${ad.adTitle }"></c:out></td>
								<td align="center"><c:out value="${ad.buyOrSell }"></c:out></td>
								<td align="center"><c:out value="${ad.adQty }"></c:out></td>
								<td align="center"><c:out value="${ad.price }"></c:out></td>
								<td align="center"><c:out value="${ad.purchaseDate }"></c:out></td>

							</tr>
						</c:forEach>

					</table>
				</c:when>
				<c:otherwise>
				<h2>You don't have any transactions.</h2>
				</c:otherwise>
			</c:choose>
		</div>
		<div id="footer">
			<p>
				<strong><c:out
						value="You logged in last on ${user.lastLogin }"></c:out></strong>
			</p>
		</div>
	</div>
<%String put=(String)session.getAttribute("output"); 
out.println(put);%>

</body>
</html>
