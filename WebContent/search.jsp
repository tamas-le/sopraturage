<%@page import="sopraturage.models.tables.Address"%>
<%@page import="java.util.LinkedList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="css/search.css" rel="stylesheet" type="text/css" />

<script type="text/javascript">
function switchInfoPerso()
{
divInfo = document.getElementById('divacacher');
if (divInfo.style.display == 'none')
divInfo.style.display = 'block';
else
divInfo.style.display = 'none';
}

function displayHome()
{
divHome = document.getElementById('home_input');
if (document.getElementById('home').checked)
divHome.style.display = 'block';
else
divHome.style.display = 'none';
}

function displayWorkplace()
{
divWorkplace = document.getElementById('workplaces_input');
if (document.getElementById('workplace').checked)
divWorkplace.style.display = 'block';
else
divWorkplace.style.display = 'none';
}


</script>



<title>Sopraturage</title>


</head>
<body>

<%@ include file="header.jsp" %>

<div class="page">

	<!--  <div class="content">
		
		<iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d92456.97749250506!2d1.4328409999999998!3d43.600699950000006!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x12aebb6fec7552ff%3A0x406f69c2f411030!2sToulouse!5e0!3m2!1sfr!2sfr!4v1420795718576" width="100%" height="400" frameborder="0" style="border:0"></iframe>
				
	</div>-->
 
 
 	
	<br /><br /><br />
		
	<div class="button_adv">
		<a href="javascript: switchInfoPerso();">Advanced research</a> 	
	</div>
	<br /><br /><br />	
		
	
		
 	<div id="divacacher" class="form_create_account" >
 	
 	<div class="titre_page">
		<h2>Search by</h2>
	</div>
 	
	<form method="post" action="">   
  
  		<p><input type="checkbox" id="home"	onClick="displayHome();" name="home" value="home_ok" />
  		<label for="home">Home</label></p>
		
		<div id="home_input">
        <p><label for="town">  Town :</label>
	    <input type="text" name="town" id="town" /></p> 
	    
	    <p><label for="PostCode"> Post code : </label>    
	    <input type="text" name="PostCode" id="PostCode" /></p> 
	   </div>
	  	
	  	<p><input type="checkbox" id="workplace" onClick="displayWorkplace();" name="workplace_box" value="workplace_ok" />
	    <label for="workplace">Workplace</label></p>
       	
       	<div id="workplaces_input">
		<select name="workplace">
					 <%
// 						LinkedList<Address> workplaces = (LinkedList<Address>) request
// 						.getAttribute("adresses");
			   
// 							for (Address a : workplaces) {
// 								out.println("<option>" + a.toStringBetter() + "</option>");
// 							}
						%>
						

		</select>
		<br />
		</div>
		<br />
		
		<p class="important_information">Are you a driver budd ?</p>
		
		<p><input type="radio"	name="driver" value="driver_ok" />
		<label for="driver">Yes, BRO ! Come with me ;)</label>
        <br />
		<input type="radio"	name="driver" value="not_driver_ok" />
		<label for="not_driver">No, pity huh ?</label>
		<br />
		<input type="radio"	name="driver" value="not_driver_ok" />
		<label for="not_driver">I don't give a fuck !</label>
		</p>
		
  
	  <input type="submit" id="submit" name="connexion" value="&rarr;" /><br />
	  </form>
	</div>

</div>

</body>
</html>