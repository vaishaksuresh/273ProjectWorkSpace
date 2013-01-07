<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="com.vlms.sjsu.entity.*"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Video Library Management System : List All Users</title>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<link rel="stylesheet" type="text/css" href="css/reset.css">
<link rel="stylesheet" type="text/css" href="css/structure.css">
<style>
table {
	margin-left: auto;
	margin-right: auto;
}
</style>
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

			<form method="post" action="ListAllUserAction">

				<h2>Choose The Number Of Users</h2>
				<select name="value">
					<option value="100" ${value1 == '100' ? 'selected' : ''}>100</option>
					<option value="200" ${value1 == '200' ? 'selected' : ''}>200</option>
					<option value="1000" ${value1 == '1000' ? 'selected' : ''}>1000</option>
					<option value="10000" ${value1 == '10000' ? 'selected' : ''}>All</option>
				</select> <br> <br> <input type="submit" name="submitMovie"
					value="Submit">
			</form>
			<%
				User[] result = (User[]) request.getAttribute("resultArray");
				if (result != null) {
			%>


			<br/>
			<h2>Searched Users</h2>
			<table width="100%">

				<tr>
					<th>User ID</th>
					<th>E-Mail</th>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Street</th>
					<th>City</th>
					<th>State</th>
					<th>Zip-Code</th>
					<th>Member-Type</th>
					<th>Action</th>

				</tr>


				<%
					for (int i = 0; i < result.length; i++) {
							//User a = result[i];

							// System.out.println("INSIDE JSP 2");
				%>



				<tr>
					<td align="center">
						<%
							out.println(result[i].getUserId());
						%>
					</td>
					<td align="center">
						<%
							out.println(result[i].getEmail());
						%>
					</td>
					<td align="center">
						<%
							out.println(result[i].getFirstName());
						%>
					</td>
					<td align="center">
						<%
							out.println(result[i].getLastName());
						%>
					</td>
					<td align="center">
						<%
							out.println(result[i].getAddress());
						%>
					</td>
					<td align="center">
						<%
							out.println(result[i].getCity());
						%>
					</td>
					<td align="center">
						<%
							out.println(result[i].getState());
						%>
					</td>
					<td align="center">
						<%
							out.println(result[i].getZipCode());
						%>
					</td>
					<td align="center">
						<%
							out.println(result[i].getMemberType());
						%>
					</td>
					<%
						if (!(result[i].getMemberType()).equalsIgnoreCase("admin")) {
					%>

					<td>
						<form id="form1" action="deleteUserAction" method="post">
							<input type="submit" value="Delete"> <input type="hidden"
								name="userHiddenID" value="<%=result[i].getUserId()%>">
						</form>
					</td>
					<%
						}
					%>
				</tr>
				<%
					}
					}
				%>
			</table>
			<br /> <br />
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