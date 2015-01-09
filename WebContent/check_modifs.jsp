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
		Boolean create = (Boolean) request.getAttribute("created");
		if (create) {
			out.println("<p  >Your changes have been saved</p>");
		} else {
			out.println("<p >Something went wrong, please go back and check that your information are correct</p>");
		}
	%>

</body>
</html>