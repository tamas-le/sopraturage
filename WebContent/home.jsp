<%@page import="sopraturage.ApplicationData"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Sopraturage</title>
<link href="css/sopraturage.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div class="page">
	
	
	<div class="head">
			<h1>Sopraturage</h1>
			
	</div>
	
	
		
		
	
	<%
	
	String name=(String)request.getAttribute("name");
		out.println("<h1>Welcome "
				+ name + "</h1>");
	%>

	<ul class="menu">

	<a href="#"><li>Search</li></a>

	<a href="modify"><li>My Account</li></a>
	
	<%
	Boolean admin=(Boolean)request.getAttribute("admin");
		if (admin) {
			out.println("<a href='admin'><li>Administration</li></a>");
		}
	%>

	<a href="disconnect"><li>Disconnection</li></a>

	</ul>
		

	</div>

</body>
</html>