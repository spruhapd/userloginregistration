<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Registration</title>
</head>
<body>
	<form:form id="regForm" modelAttribute="user" action="registerProcess"
		method="post">
		
		<table id ="error" align="center">
			<tr style="color:red;font:italic;"><td><form:errors path="userName" /></td></tr>
			<tr style="color:red;font:italic;"><td><form:errors path="password" /></td></tr>
			<tr style="color:red;font:italic;"><td><form:errors path="firstName" /></td></tr>
			<tr style="color:red;font:italic;"><td><form:errors path="lastName" /></td></tr>
			<tr></tr>
		</table>
		
		<table align="center">
			<tr>
				<td><form:label path="userName">User Name</form:label></td>
				<td><form:input path="userName" name="userName" id="userName" />
			</tr>
			<tr>
				<td><form:label path="password">Password</form:label></td>
				<td><form:password path="password" name="password" id="password" /></td>						
			</tr>
			<tr>
				<td><form:label path="firstName">First Name</form:label></td>
				<td><form:input path="firstName" name="firstName" id="firstName" /></td>
			</tr>
			<tr>
				<td><form:label path="lastName">Last Name</form:label></td>
				<td><form:input path="lastName" name="lastName" id="lastName" />
			</tr>
			<tr>
				<td></td>
				<td><form:button id="register" name="register">Register</form:button>
				</td>
			</tr>
			<tr></tr>
			<tr>
				<td></td>
				<td><a href="index.jsp">Home</a></td>
			</tr>
		</table>
	</form:form>
</body>
</html>