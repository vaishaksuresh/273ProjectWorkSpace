<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/reset.css">
<link rel="stylesheet" type="text/css" href="css/structure.css">
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<title>Video Library Management System : Sign Up</title>

</head>
<body>
	<script>
		$(document)
				.ready(
						function() {
							$("#signUpBtn")
									.click(
											function() {
												var CCNumber = $("#ccNum")
														.val();
												if (CCNumber.length != 16) {
													alert("Please Enter Valid 16 - Digit Credit Card Number");
													$("#ccNum").focus();
												} else if (!CCNumber
														.match(/^\d+$/)) {
													alert("Please Enter Valid 16 - Digit Credit Card Number, Enter Only Numbers");
													$("#ccNum").focus();
												} else {
													//document.forms.signupForm.submit();
													return true;
												}

											});

						});
	</script>

	<form class="box login" style="height: auto; top: 20%;"
		action="SignUpAction" id="form1" method="post" name="signupForm">
		<h3>
			<c:if test="${message ne '' }">
				<c:out value="${message }"></c:out>
			</c:if>
		</h3>
		<fieldset>
			<div>
				<span>First Name *</span><input type="text" name="firstname"
					required />
			</div>
			<div>
				<span class="label">Last Name *<input type="text"
					name="lastname" required />
			</div>
			<div>
				<span class="label">Email*</span><input type="text" name="email"
					required />
			</div>
			<div>
				<span class="label">Password *</span><input type="password"
					name="pass" required />
			</div>
			<div>
				<span class="label">Address *</span><input type="text"
					name="address" required />
			</div>

			<div>
				<span class="label">City *</span><input type="text" name="city"
					required />
			</div>
			<div>
				<span class="label">State *</span>
				<!-- <input type="text" name="state"
					required /> -->
				<select name="state" class="cjComboBox" required>
					<option value="">Pick your state</option>
					<option value="AL">Alabama</option>
					<option value="AK">Alaska</option>
					<option value="AZ">Arizona</option>
					<option value="AR">Arkansas</option>
					<option value="CA">California</option>
					<option value="CO">Colorado</option>
					<option value="CT">Connecticut</option>
					<option value="DE">Delaware</option>
					<option value="DC">District of Columbia</option>
					<option value="FL">Florida</option>
					<option value="GA">Georgia</option>
					<option value="HI">Hawaii</option>
					<option value="ID">Idaho</option>
					<option value="IL">Illinois</option>
					<option value="IN">Indiana</option>
					<option value="IA">Iowa</option>
					<option value="KS">Kansas</option>
					<option value="KY">Kentucky</option>
					<option value="LA">Louisiana</option>
					<option value="ME">Maine</option>
					<option value="MD">Maryland</option>
					<option value="MA">Massachusetts</option>
					<option value="MI">Michigan</option>
					<option value="MN">Minnesota</option>
					<option value="MS">Mississippi</option>
					<option value="MO">Missouri</option>
					<option value="MT">Montana</option>
					<option value="NE">Nebraska</option>
					<option value="NV">Nevada</option>
					<option value="NH">New Hampshire</option>
					<option value="NJ">New Jersey</option>
					<option value="NM">New Mexico</option>
					<option value="NY">New York</option>
					<option value="NC">North Carolina</option>
					<option value="ND">North Dakota</option>
					<option value="OH">Ohio</option>
					<option value="OK">Oklahoma</option>
					<option value="OR">Oregon</option>
					<option value="PA">Pennsylvania</option>
					<option value="RI">Rhode Island</option>
					<option value="SC">South Carolina</option>
					<option value="SD">South Dakota</option>
					<option value="TN">Tennessee</option>
					<option value="TX">Texas</option>
					<option value="UT">Utah</option>
					<option value="VT">Vermont</option>
					<option value="VA">Virginia</option>
					<option value="WA">Washington</option>
					<option value="WV">West Virginia</option>
					<option value="WI">Wisconsin</option>
					<option value="WY">Wyoming</option>
				</select>


			</div>
			<div>
				<span class="label">Zip Code *</span><input type="text" maxlength="10" name="zip"
					required />
			</div>
			<div>
				<span class="label">Member Type *</span> <select class="cjComboBox"
					name="membertype">
					<option value="Simple">Simple</option>
					<option value="Premium">Premium</option>
				</select>
			</div>
			<div>
				<span class="label">Credit Card *</span><input type="text"
					name="creditcard" maxlength="16" id="ccNum" required  />
			</div>
			<div>
				<input type="submit" id="signUpBtn" name="signUpBtn"
					class="btnLogin" value="SignUp" />
			</div>
		</fieldset>
	</form>
</body>
</html>