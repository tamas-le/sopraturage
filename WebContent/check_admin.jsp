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
		Boolean status = (Boolean) request.getAttribute("status");
		if (status) {
			out.println("<p>Your changes have been saved</p>");
		} else {
			out.println("<p>Something went wrong </p>");
		}
		
	%>
	<a href='admin'>Back to the admin home page</a>

</body>
</html>