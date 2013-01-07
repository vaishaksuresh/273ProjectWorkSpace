<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<!DOCTYPE HTML>
<html>
<head>
<title>Find User</title>
<meta charset="UTF-8" />
<link rel="stylesheet" type="text/css" href="css/reset.css">
<link rel="stylesheet" type="text/css" href="css/structure.css">
<script src="http://code.jquery.com/jquery-latest.js"></script>
</head>
<body>	
	<script>
		$(document)
			.ready(
					function() 	{
						$("#submitBtn")
							.click(
									function(){
												var value = $("#ssn").val();
												var re = /^([0-6]\d{2}|7[0-6]\d|77[0-2])([ \-]?)(\d{2})\2(\d{4})$/;
												var temp = value;
												
												
											    if(value.length >0)
											    	{
											    	
														if (!re.test(value)) 
														{
													    	alert("Invalid Membership ID");
													        return false;
													    }
														
														  if (value.length < 11) {
															    alert("Invalid Membership ID");
															    	return false;
															    }
														  
														 if (value.indexOf("-") != -1) {
														        temp = (value.split("-")).join("");
														    }
														    if (value.indexOf(" ") != -1) {
														        temp = (value.split(" ")).join("");
														    }
														    if (temp.substring(0, 3) == "000") {
														    alert("Invalid Membership ID");
														    	return false;
														    }
														    if (temp.substring(3, 5) == "00") {
														    	alert("Invalid Membership ID");
														        return false;
														    }
														    if (temp.substring(5, 9) == "0000") {
														    	alert("Invalid Membership ID");
														        return false;
														    }
											    	}	   
											  										    
											    return true;
											});
					});​
	</script>
	
	
	
	<div id="navbar">
		<jsp:include page="navbar.jsp"></jsp:include>
	</div>
	<div id="container">
		<div id="content">
			
			<br> 
			<h2>Search User</h2>
			<c:if test="${message ne '' }">
				<h4>
					<c:out value="${message }"></c:out>
				</h4>
				<br/>
			</c:if>
			<form method="post" action="FindUser">
				<table style="width: 100%;">
					<tr>
						<td><b>Membership ID</b></td>
						<td><input name="ssn" id="ssn" type="text" maxlength="11" value="${usersID}"></td>
					</tr>
					<tr>
						<td><b>First Name</b></td>
						<td><input type="text" name="UserFirstName" value="${usersFname}" maxlength="40"></td>
					</tr>
					<tr>
						<td><b>Last Name</b></td>
						<td><input type="text" name="UserLastName" value="${usersLname}" maxlength="40"></td>
					</tr>
					<tr>
						<td><b>Email</b></td>
						<td><input type="text" name="UserEmail" value="${usersEmail }" maxlength="40"></td>
					</tr>
					<tr>
						<td><b>Street</b></td>
						<td><input type="text" name="UserAddress" value="${usersStreet }" maxlength="40"></td>
					</tr>
					<tr>
						<td><b>City</b></td>
						<td><input type="text" name="UserCity" value="${usersCity }" maxlength="40"></td>
					</tr>
					<tr>
						<td><b>State</b></td>
						<td>
					<select name="UserState" class="cjComboBox">
					<option value="">Select</option>
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
						</td>
					</tr>
					<tr>
						<td><b>ZipCode</b></td>
						<td><input type="text" name="UserZipCode" value="${usersZipcode }" maxlength="5"></td>
					</tr>
					<tr>
						<td><b>Member Type</b></td>
						<td>
						<select name="UserMemberType" class="cjComboBox" >
						<option value="">Select</option>
						<option value="simple">Simple Customer</option>
						<option value="premium">Premium Member</option>
					
						</select>
						</td>
					</tr>
					
					<tr>
						<td>&nbsp;</td>
						<td><input type="submit"  id="submitBtn" name="SubmitUser" value="Submit"></td>
					</tr>
				</table>
			</form>
			
				<c:choose>
				<c:when test="${SearchUser ne null }">
				<br>
					<h2>Searched Users</h2>
					<h4>
					<c:if test="${message1 ne '' }">
					<c:out value="${message1 }"></c:out>
					</c:if>
					</h4>
					<table style="width: 100%;">
						<tr>
							<th>Name</th>
							
							<th>Email Address</th>						
							
						</tr>
						<form name="userdetail" method="post" action="UserdetailsAction" onsubmit="">
						<c:forEach items="${SearchUser}" var="users">
							<tr>
								<td align="center">
									<u><a href="UserdetailsAction?userhidden=${users.userId }">${users.firstName } ${users.lastName }</a></u>
								</td>
								<td align="center"><c:out value="${users.email }"></c:out></td>
								
							</tr>
						</c:forEach></form>
					</table>
				</c:when>
				<c:otherwise>
				<br>
					<h2>No Results</h2>
					<h4>
					<c:if test="${message1 ne '' }">
					<c:out value="${message1 }"></c:out>
					</c:if>
					</h4>
				</c:otherwise>
			</c:choose>
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
