<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
        <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
        <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
            <title>Login</title>
        </head>
        <body>
            <form:form id="loginForm" modelAttribute="login" action="loginProcess" method="post">
            	<table id ="error" align="center">
					<tr style="color:red;"><td><form:errors path="userName" /></td></tr>
					<tr style="color:red;"><td><form:errors path="password" /></td></tr>
					<tr></tr>
				</table>
                <table align="center">
                    <tr>
                        <td><form:label path="userName">User Name: </form:label></td>
                        <td><form:input path="userName" name="userName" id="userName" /></td>
                    </tr>
                    <tr>
                        <td><form:label path="password">Password:</form:label></td>
                        <td><form:password path="password" name="password" id="password" /></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td align="left">
                            <form:button id="login" name="login">Login</form:button>
                        </td>
                    </tr>
                    <tr></tr>
                    <tr>
                        <td></td>
                        <td><a href="index.jsp">Home</a>
                        </td>
                        <td></td>
                        <td><a href="register">Register</a>
               			</td>
                    </tr>
                </table>
            </form:form>
            <table align="center">
                <tr>
                    <td style="font-style: italic; color: red;">${message}</td>
                </tr>
            </table>
        </body>
        </html>