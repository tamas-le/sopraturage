<%@page import="sopraturage.models.tables.Session"%>
<%@page import="java.util.LinkedList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sopraturage</title>
</head>
<body>
	<%
		LinkedList<Session> sessions = (LinkedList) request
				.getAttribute("list");

		out.print("<p>Connexion number = " + sessions.size() + "</p>");

		for (Session s : sessions) {
			out.print(s);
		}
	%>



</body>
</html>