<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sopraturage</title>
<link href="css/sopraturage.css" rel="stylesheet" type="text/css" />
<link href="css/admin.css" rel="stylesheet" type="text/css" />
<link rel="icon" href="images/auto.ico" />

</head>

<body>
	
	<a href="admin" style="font-size: 15px;" class="back_website">Back to the admin page</a>
	<br />
	<br />
	<br />
	<br />
	
<div class="page">
	
	<%
		Boolean status = (Boolean) request.getAttribute("status");
		if (status) {
			out.println("<p class='notification_information' >Your changes have been saved</p>");
		} else {
			out.println("<p class='notification_information'>Something went wrong </p>");
		}
		
	%>
	
	
	
</div>



</body>
</html>