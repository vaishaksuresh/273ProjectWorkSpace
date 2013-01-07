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
<script src="http://code.jquery.com/jquery-latest.js"></script>
<style>
#panel {
	height: 10em;
	width: auto;
	padding-left: 10em;
	padding-top: 5em;
	display: none;
	border-top: solid 4px #422410;
}

.slide {
	margin: 0;
	padding: 0;
	border-top: solid 4px #422410;
	background: url(images/btn-slide.gif) no-repeat center top;
}

.btn-slide {
	background: url(images/white-arrow.gif) no-repeat right -50px;
	text-align: center;
	width: 144px;
	height: 31px;
	padding: 10px 10px 0 0;
	margin: 0 auto;
	display: block;
	font: bold 120%/100% Arial, Helvetica, sans-serif;
	color: #fff;
	text-decoration: none;
}

.active {
	background-position: right 12px;
}
</style>
<script>
	$(document)
			.ready(
					function() {

						$(".btn-slide").click(function() {
							$("#panel").slideToggle("slow");
							$(this).toggleClass("active");
						});
						$("#checkout")
								.click(
										function() {
											var CCNumber = $("#ccNum").val();
											if (CCNumber.length != 16) {
												alert("Please Enter Valid 16 - Digit Credit Card Number");
												$("#ccNum").focus();
											} else if (!CCNumber.match(/^\d+$/)) {
												alert("Please Enter Valid 16 - Digit Credit Card Number, Enter Only Numbers");
												$("#ccNum").focus();
											} else {
												document.forms.checkoutform
														.submit();
											}

										});

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
		<div id="content">
			<c:choose>
				<c:when test="${Ads ne null }">
					<h2>My Cart</h2>
					<h3>
						<c:if test="${message ne '' }">
							<c:out value="${message }"></c:out>
						</c:if>
					</h3>
					<table border="1" style="width: 100%; border-color: red;">
						<tr>
							<th>Title</th>
							<th>Price</th>
							<th>Quantity</th>
							<th>Update/Delete</th>
						</tr>

						<c:forEach items="${Ads}" var="ad">

							<tr>

								<td><c:out value="${ad.adTitle }"></c:out></td>
								<td align="center"><c:out value="${ad.price }"></c:out></td>
								<td align="center">
									<form action="ShowUpdateCart" method="post">
										<input type="text" name="qty"
											value="<c:out value="${ad.adQty }"></c:out>"></input>
								</td>
								<td><input type="hidden" name="adID"
									value="<c:out value="${ad.adId }"></c:out>"> <input
									type="submit" name="Update" value="Update"> <input
									type="submit" name="Delete" value="Delete">
									</form></td>

							</tr>

						</c:forEach>
					</table>

					<div id="panel" align="center">
						<form name="checkoutform" action="CheckoutAction" method="post">
							<h3>Enter Credit Card Number (Without Dashes or Spaces)</h3>
							<br> <input id="ccNum" type="text" maxlength="16"> <input
								type="button" id="checkout" name="checkout" value="CheckOut">
						</form>
					</div>
					<p class="slide">
						<a class="btn-slide">Checkout</a>
					</p>

				</c:when>
				<c:otherwise>
					<h2>You don't have any items in your cart.</h2>
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


</body>
</html>
