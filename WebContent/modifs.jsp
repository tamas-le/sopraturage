<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="java.util.LinkedList"%>
<%@page import="sopraturage.models.tables.Address"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sopraturage</title>
</head>
<body>

	<h2>Modify your information</h2>
	<form action="" method="post">

		<input type="tel" id="phone" name="phoneNumber"
			placeholder="Phone Number" maxlength="10" />

		<p>Address :</p>
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
		<br /> <br />
		<p>Workplace :</p>
		<SELECT name="workplace">
			<%
				LinkedList<Address> attribut = (LinkedList<Address>) request
						.getAttribute("adresse");
				for (Address a : attribut) {
					out.println("<option>"+a.toStringBetter()+"</option>");
				}
			%>

		</SELECT> <br /> <br />
		<p>More information :</p>
		<input type="checkbox" name="driver" value="driver_ok" /><label
			for="driver">I am a driver</label> <br /> <input type="checkbox"
			name="notify" value="notify_ok" /><label for="notify">I want
			to be notify</label><br /> <br /> <input type="submit" id="submit"
			name="connexion" value="Save changes" /><br />

	</form>




</body>
</html>