<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<!DOCTYPE HTML>
<html>
<head>
<title>Video Library Management System : Update Profile</title>
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
		<div id="content" style="height: 110%;">
			<form action="UpdateProfileAction" id="form1" method="post">
				<c:if test="${message ne '' }">
					<h3>
						<c:out value="${message }"></c:out>
					</h3>
				</c:if>
				<table style="width: 100%">
					<tr>
						<td><b>Membership ID</b></td>
						<td>${sessionScope.user.userId}</td>
					</tr>
					<tr>
						<td><b>First Name *</b></td>
						<td><input type="text" name="firstname" required
							value='${sessionScope.user.firstName}' /></td>
					</tr>
					<tr>
						<td><b>Last Name *</b></td>
						<td><input type="text" name="lastname" required
							value='${sessionScope.user.lastName}' /></td>
					</tr>
					<tr>
						<td><b>Old Password *</b></td>
						<td><input type="password" name="pass" required /></td>
					</tr>
					<tr>
						<td><b>New Password</b></td>
						<td><input type="password" name="newpass" /></td>
					</tr>
					<tr>
						<td><b>Address *</b></td>
						<td><input type="text" name="address" required
							value='${sessionScope.user.address}' /></td>
					</tr>
					<tr>
						<td><b>City *</b></td>
						<td><input type="text" name="city" required
							value='${sessionScope.user.city}' /></td>
					</tr>
					<tr>
						<td><b>State *</b></td>
						<td>
							<!--<input type="text" name="state" required
							value='${sessionScope.user.state}' />--> <select name="state"
							class="cjComboBox" required>
								<option value="">Pick your state</option>
								<option value="AL"
									${sessionScope.user.state == 'AL' ? 'selected' : ''}>Alabama</option>
								<option value="AK"
									${sessionScope.user.state == 'AK' ? 'selected' : ''}>Alaska</option>
								<option value="AZ"
									${sessionScope.user.state == 'AZ' ? 'selected' : ''}>Arizona</option>
								<option value="AR"
									${sessionScope.user.state == 'AR' ? 'selected' : ''}>Arkansas</option>
								<option value="CA"
									${sessionScope.user.state == 'CA' ? 'selected' : ''}>California</option>
								<option value="CO"
									${sessionScope.user.state == 'CO' ? 'selected' : ''}>Colorado</option>
								<option value="CT"
									${sessionScope.user.state == 'CT' ? 'selected' : ''}>Connecticut</option>
								<option value="DE"
									${sessionScope.user.state == 'DE' ? 'selected' : ''}>Delaware</option>
								<option value="DC"
									${sessionScope.user.state == 'DC' ? 'selected' : ''}>District
									of Columbia</option>
								<option value="FL"
									${sessionScope.user.state == 'FL' ? 'selected' : ''}>Florida</option>
								<option value="GA"
									${sessionScope.user.state == 'GA' ? 'selected' : ''}>Georgia</option>
								<option value="HI"
									${sessionScope.user.state == 'HI' ? 'selected' : ''}>Hawaii</option>
								<option value="ID"
									${sessionScope.user.state == 'ID' ? 'selected' : ''}>Idaho</option>
								<option value="IL"
									${sessionScope.user.state == 'IL' ? 'selected' : ''}>Illinois</option>
								<option value="IN"
									${sessionScope.user.state == 'IN' ? 'selected' : ''}>Indiana</option>
								<option value="IA"
									${sessionScope.user.state == 'IA' ? 'selected' : ''}>Iowa</option>
								<option value="KS"
									${sessionScope.user.state == 'KS' ? 'selected' : ''}>Kansas</option>
								<option value="KY"
									${sessionScope.user.state == 'KY' ? 'selected' : ''}>Kentucky</option>
								<option value="LA"
									${sessionScope.user.state == 'LA' ? 'selected' : ''}>Louisiana</option>
								<option value="ME"
									${sessionScope.user.state == 'ME' ? 'selected' : ''}>Maine</option>
								<option value="MD"
									${sessionScope.user.state == 'MD' ? 'selected' : ''}>Maryland</option>
								<option value="MA"
									${sessionScope.user.state == 'MA' ? 'selected' : ''}>Massachusetts</option>
								<option value="MI"
									${sessionScope.user.state == 'MI' ? 'selected' : ''}>Michigan</option>
								<option value="MN"
									${sessionScope.user.state == 'MN' ? 'selected' : ''}>Minnesota</option>
								<option value="MS"
									${sessionScope.user.state == 'MS' ? 'selected' : ''}>Mississippi</option>
								<option value="MO"
									${sessionScope.user.state == 'MO' ? 'selected' : ''}>Missouri</option>
								<option value="MT"
									${sessionScope.user.state == 'MY' ? 'selected' : ''}>Montana</option>
								<option value="NE"
									${sessionScope.user.state == 'NE' ? 'selected' : ''}>Nebraska</option>
								<option value="NV"
									${sessionScope.user.state == 'NV' ? 'selected' : ''}>Nevada</option>
								<option value="NH"
									${sessionScope.user.state == 'NH' ? 'selected' : ''}>New
									Hampshire</option>
								<option value="NJ"
									${sessionScope.user.state == 'NJ' ? 'selected' : ''}>New
									Jersey</option>
								<option value="NM"
									${sessionScope.user.state == 'NM' ? 'selected' : ''}>New
									Mexico</option>
								<option value="NY"
									${sessionScope.user.state == 'NY' ? 'selected' : ''}>New
									York</option>
								<option value="NC"
									${sessionScope.user.state == 'NC' ? 'selected' : ''}>North
									Carolina</option>
								<option value="ND"
									${sessionScope.user.state == 'ND' ? 'selected' : ''}>North
									Dakota</option>
								<option value="OH"
									${sessionScope.user.state == 'OH' ? 'selected' : ''}>Ohio</option>
								<option value="OK"
									${sessionScope.user.state == 'OK' ? 'selected' : ''}>Oklahoma</option>
								<option value="OR"
									${sessionScope.user.state == 'OR' ? 'selected' : ''}>Oregon</option>
								<option value="PA"
									${sessionScope.user.state == 'PA' ? 'selected' : ''}>Pennsylvania</option>
								<option value="RI"
									${sessionScope.user.state == 'RI' ? 'selected' : ''}>Rhode
									Island</option>
								<option value="SC"
									${sessionScope.user.state == 'SC' ? 'selected' : ''}>South
									Carolina</option>
								<option value="SD"
									${sessionScope.user.state == 'SD' ? 'selected' : ''}>South
									Dakota</option>
								<option value="TN"
									${sessionScope.user.state == 'TN' ? 'selected' : ''}>Tennessee</option>
								<option value="TX"
									${sessionScope.user.state == 'TX' ? 'selected' : ''}>Texas</option>
								<option value="UT"
									${sessionScope.user.state == 'UT' ? 'selected' : ''}>Utah</option>
								<option value="VT"
									${sessionScope.user.state == 'VT' ? 'selected' : ''}>Vermont</option>
								<option value="VA"
									${sessionScope.user.state == 'VA' ? 'selected' : ''}>Virginia</option>
								<option value="WA"
									${sessionScope.user.state == 'WA' ? 'selected' : ''}>Washington</option>
								<option value="WV"
									${sessionScope.user.state == 'WV' ? 'selected' : ''}>West
									Virginia</option>
								<option value="WI"
									${sessionScope.user.state == 'WI' ? 'selected' : ''}>Wisconsin</option>
								<option value="WY"
									${sessionScope.user.state == 'WY' ? 'selected' : ''}>Wyoming</option>
						</select>
						</td>
					</tr>
					<tr>
						<td><b>Zip Code *</b></td>
						<td><input type="text" name="zip" maxlength="9" required
							value='${sessionScope.user.zipCode}' /></td>
					</tr>
					<tr>
						<c:choose>
							<c:when test="${sessionScope.user.memberType eq 'admin'  }">
								<td><b>Member Type</b></td>
								<td>${sessionScope.user.memberType}</td>
								<input type="hidden" name="membertype" value="${sessionScope.user.memberType}">
							</c:when>
							<c:otherwise>
								<td><b>Member Type *</b></td>
								<td><select class="cjComboBox" name="membertype">
										<option value="Premium"
											${sessionScope.user.memberType == 'Premium' ? 'selected' : ''}>Premium</option>
										<option value="Simple"
											${sessionScope.user.memberType == 'Simple' ? 'selected' : ''}>Simple</option>
								</select></td>
							</c:otherwise>
						</c:choose>
					</tr>
					<tr>
						<td colspan="2"><input type="submit" class="btnLogin"
							value="Update" name="updateBtn" /></td>
					</tr>
				</table>

			</form>
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
