<%@page import='org.apache.jasper.tagplugins.jstl.core.ForEach'%>
<%@page import='java.util.LinkedList'%>
<%@page import='sopraturage.models.tables.Address'%>
<%@page import='sopraturage.models.tables.User'%>
<%@ page language='java' contentType='text/html; charset=ISO-8859-1'
	pageEncoding='ISO-8859-1'%>
<!DOCTYPE html PUBLIC '-//W3C//DTD HTML 4.01 Transitional//EN' 'http://www.w3.org/TR/html4/loose.dtd'>
<html>
<head>
<meta http-equiv='Content-Type' content='text/html; charset=ISO-8859-1'>
<title>Sopraturage</title>
<link href="css/sopraturage.css" rel="stylesheet" type="text/css" />
</head>
<body>

<%@ include file="header.jsp" %>

<div class="page">
	
	<div class="content">
	
	<div class="titre_page">
		<h2>Modify user information</h2>
	</div>
	
	

	<div class="form_create_account">
	<form action='' method='post'>
	
		<%
	
	User u = (User) request.getAttribute("user");
	out.println("<p class='important_information'>Your pseudo is "+u.getEmail()+"</p>");
	%>
		
		<br/>
		<p>Phone number :</p>

		<%
			
			out.println("<input type='tel' id='phone' name='phoneNumber' placeholder='Phone Number' maxlength='10' value='"
					+ u.getPhone() + "' />");
		%>

		<p>Password</p>

		<%
			out.println("<input type='text' name='pass' placeholder='Password' value='"
					+ u.getPassword() + "'/>");
		%>



		<p>Address :</p>

		<%
			Address ad = (Address) request.getAttribute("adress");
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

		</SELECT><br /> <br />

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


		<br /> <br />
		<p>Workplace :</p>
		<SELECT name='workplace'>
			<%
				LinkedList<Address> attribut = (LinkedList<Address>) request
						.getAttribute("adresses");
				for (Address a : attribut) {
					out.println("<option>" + a.toStringBetter() + "</option>");
				}
			%>

		</SELECT> <br /> <br />

		<%
			out.println(u.isDriver() ? "<input type='checkbox' name='driver' value='driver_ok' checked/>"
					: "<input type='checkbox' name='driver' value='driver_ok' />");
		%>


		<label for='driver'>I am a driver</label> <br />

		<%
			out.println(u.isNotification() ? "<input type='checkbox' name='notify' value='notify_ok' checked/>"
					: "<input type='checkbox' name='notify' value='notify_ok' />");
		%>

		<label for='notify'>I want to be notify</label><br /> <br /> 
		
		
		<input
			type='submit' id='submit' name='connexion' value='Save changes' /><br />

	</form>
	</div>

</div>
	
</div>


</body>
</html>