<%@page import="sopraturage.models.tables.Address"%>
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
	
	
	<h4>Modify</h4>
	
	<div class="form_create_account">
	<form method='post'>

		<%
			Address ad = (Address) request.getAttribute("address");
			request.setAttribute("adresse", ad);

			out.println("<input type='text' name='number' placeholder='Number' value='"
					+ ad.getNum() + "' /> <br /> <br />");
		%>

		<SELECT name='typeWay'>
			<%
				out.println(ad.getWaytype().equals("rue") ? "<option selected>rue</option>"
						: "<option>rue</option>");
				out.println(ad.getWaytype().equals("avenue") ? "<option selected>avenue</option>"
						: "<option>avenue</option>");
				out.println(ad.getWaytype().equals("chemin") ? "<option selected>chemin</option>"
						: "<option>chemin</option>");
				out.println(ad.getWaytype().equals("allee") ? "<option selected>allee</option>"
						: "<option>allee</option>");
				out.println(ad.getWaytype().equals("boulevard") ? "<option selected>boulevard</option>"
						: "<option>boulevard</option>");
				out.println(ad.getWaytype().equals("route") ? "<option selected>route</option>"
						: "<option>route</option>");
				out.println(ad.getWaytype().equals("ruelle") ? "<option selected>ruelle</option>"
						: "<option>ruelle</option>");
			%>

		</SELECT> <br /> <br />

		<%
			out.println("<input type='text' name='way' placeholder='Name of the way' value='"
					+ ad.getWayName() + "' /> ");
		%>
		<br /> <br />

		<%
			out.println("<input type='number' id='postCode' name='postCode' placeholder='PostCode' value='"
					+ ad.getPostCode().getPostcode() + "' />");
		%>

		<br /> <br />


		<%
			out.println("<input type='text' name='city' placeholder='City' value='"
					+ ad.getPostCode().getCity() + "' />");
		%>
		<br />
		<br />
		<input type='submit' name='Modification' value='Modify'>
	</form>
	</div>
	</div>

</body>
</html>