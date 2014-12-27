<%@page import="sopraturage.ApplicationData"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Sopraturage</title>
</head>
<body>
	<%
		out.println("<h1>Bienvenue "
				+ ApplicationData.localUser.getSurname() + "</h1>");
	%>
	
	<h2>What do you want to do ?</h2>

	<a href="modify">Modify Account</a>
	<a href="#">Search</a>
	<%
		if (ApplicationData.admin) {
			out.println("<a href='#'>Admin Page</a>");
		}
	%>

	<a href="disconnect">Disconnect</a>



</body>
</html>