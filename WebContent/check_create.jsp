<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sopraturage</title>
<link href="css/sopraturage.css" rel="stylesheet" type="text/css" />

</head>



<body>

	<%@ include file="header.jsp" %>

	<%
		Boolean create = (Boolean) request.getAttribute("created");
		if (create) {
			out.println("<p>Your account has been created successfully</p>");
		} else {
			out.println("<p>Something went wrong, please go back and check that your information are correct</p>");
		}
	%>

	<a href="index.html">Back to the connexion page</a>
	<br/>

</body>
</html>