<%@page import="sopraturage.models.tables.Address"%>
<%@page import="java.util.LinkedList"%>
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
	
	<div class="page">

	<h1 style="color:black;">Manage workplaces</h1>
	
	<div class="titre_page">
		<h2>List of the workplaces</h2>
	</div>

	
		<%
			LinkedList<Address> workplaces = (LinkedList<Address>) request
					.getAttribute("adresses");

			for (Address a : workplaces) {
				out.println("<p class='address'>" + a.toStringBetter() + "<p>");

			}
		%>
	
	
	
	<br />
	<br />
	<br />

	<div class="titre_page">
		<h2>Add a workplace</h2>
	</div>
	
	<div class="form_create_account">
	
	<form action="" method="post">

		<input type="text" name="number" placeholder="Number" /> <br /> <br />
		<SELECT name="typeWay">
			<option>rue</option>
			<option>avenue</option>
			<option>chemin</option>
			<option>allee</option>
			<option>boulevard</option>
			<option>route</option>
			<option>ruelle</option>
		</SELECT><br /> <br /> <input type="text" name="way"
			placeholder="Name of the way" /> <br /> <br /> <input
			type="number" id="postCode" name="postCode" placeholder="PostCode" />
		<br />
		<p class="error"></p>
		<br /> <input type="text" name="city" placeholder="City" /> <br />
		<br /> <br /> <input type="submit" name="Add" value="Add" />
	
	</form>
	
	</div>
		
		<br/>
		<br/>
		

	<div class="titre_page">
		<h2>Modify a workplace</h2>
	</div>

	<div class="form_create_account">
	<form action="" method="post">
	
		<select name="workplace">
			<%
				for (Address a : workplaces) {
					out.println("<option>" + a.toStringBetter() + "</option>");
				}
			%>
			
		
		</select> 
		<br />
		<br />
		<br />
		<input type="submit" name="Modify" value="Modify" />


	</form>
	
	</div>
	
	</div>
</body>
</html>