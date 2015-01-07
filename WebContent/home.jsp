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
		<div class="titre_sop">
			<h1>Sopraturage</h1>
		</div>
		<div class="pseudo">
			<span class="img_rouages"><img src="images/gear.png" width="20px" height="20px"></span>
			<%
	
			String email=(String)request.getAttribute("email");
			out.println("<a href='admin'>"+ email +"</a>");
			%>
	
		</div>
	</div>
	

	<ul class="menu">

	<a href="#"><li>Search</li></a>

	<a href="modify"><li>My Account</li></a>
	
	<%
	Boolean admin=(Boolean)request.getAttribute("admin");
		if (admin) {
			out.println("<a href='admin'><li>Administration</li></a>");
		}
	%>

	<a href="disconnect"><li>Log out</li></a>

	</ul>
		

</div>

<br />
<div class="page">
	
	<div class="content">
	</div>
	
</div>

</body>
</html>