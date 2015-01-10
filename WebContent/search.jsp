<%@page import="sopraturage.models.tables.Address"%>
<%@page import="java.util.LinkedList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="css/sopraturage.css" rel="stylesheet" type="text/css" />
<title>Sopraturage</title>
</head>
<body>

<%@ include file="header.jsp" %>

<div class="page">

	<div class="content">
		
		<iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d92456.97749250506!2d1.4328409999999998!3d43.600699950000006!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x12aebb6fec7552ff%3A0x406f69c2f411030!2sToulouse!5e0!3m2!1sfr!2sfr!4v1420795718576" width="100%" height="400" frameborder="0" style="border:0"></iframe>
				
	</div>
 
 	<div class="form_create_account" >
	<form method="post" action="">   
  
  		<p><label for="home">Home</label>
		<input type="checkbox"	name="home" value="home_ok" /></p>
		
        <p><label for="town">  Town :</label>
	    <input type="text" name="town" id="town" /></p> 
	    
	    <p><label for="PostCode"> Post code : </label>    
	    <input type="text" name="PostCode" id="PostCode" /></p> 
	  
	    <p><label for="workplace">Workplace</label></p>
       
		<select name="workplace">
					 <%
// 						LinkedList<Address> workplaces = (LinkedList<Address>) request
// 						.getAttribute("adresses");
			   
// 							for (Address a : workplaces) {
// 								out.println("<option>" + a.toStringBetter() + "</option>");
// 							}
						%>
						

		</select>
		<br /><br/>
		
		<p><label for="driver">Driver</label>
        <input type="checkbox"	name="driver" value="driver_ok" /></p>
		
		<p><label for="not_driver">Not Driver</label>
		<input type="checkbox"	name="not_driver" value="not_driver_ok" /></p>
		
  
	  <input type="submit" id="submit" name="connexion" value="&rarr;" /><br />
	  </form>
	</div>

</div>

</body>
</html>