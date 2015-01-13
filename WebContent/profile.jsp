<%@page import="sopraturage.ApplicationData"%>
<%@page import="sopraturage.models.tables.User"%>
<%@page import="sopraturage.models.tables.Address"%>
<%@page import="sopraturage.models.tables.Workplace"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Sopraturage</title>
	
	<link href="css/sopraturage.css" rel="stylesheet" type="text/css" />
	<link rel="icon" href="images/auto.ico" />
	<script src="scripts/jquery-2.1.1.min.js"></script>
    <script type="text/javascript"
            src="https://maps.googleapis.com/maps/api/js?key=AIzaSyASDA0eZvH1781uZidsoWCbt_8s4aj1P88"></script>
    <script type="text/javascript" src="scripts/maps.js"></script>
</head>
<body>

<%@ include file="header.jsp" %>

	<div class="page">
		<div class="titre_page">
			
			<% 
				User user = (User) request.getAttribute("user");
				Address address = (Address) request.getAttribute("address"); 
				Workplace wp = (Workplace) request.getAttribute("wp");
				out.println("<h2>Profile of "+ user.getSurname() + " " + user.getName() + "</h2>");
				
			%>
			
		</div>
		<br />
		
		<div class="the_profile">
		
			<br />
			<div class="the_avatar">
				
				<% 
				
				if (user.getImagePath().equals("/images/inconnu.jpg")){
					out.println("<img src='images/avatar/inconnu.jpg' height='250px' width='240px'/>");
				} else {
					out.println("<img src='images/avatar/"+user.getImagePath()+"' height='250px' width='240px'/>");
				}
				
				
				if (user.equals(data.localUser)){
					out.println("<div class='button_more'><a class='more' href='upload'>Change avatar</a></div>");
				}
				
				%>
					
			</div>
		
			<div class="the_information">
			
				<% 
					out.println("<p class='nom'>"+ user.getSurname() +" <span class='surnom'>"+ user.getName() +"</span></p>");
					out.println("<p class='email'>"+ user.getEmail()+"</p>");
					out.println("<p class='email'>"+ user.getPhone()+"</p>");
					
					out.println("<br /><p class='titre' > Address : </p>");
					out.println("<p class='titre'>"+ address.toStringBetter() +"</p>");
					
					
					out.println("<br /><p class='titre'> Workplace : </p>");
					out.println("<p class='titre'>"+ wp.toStringBetter()  +"</p><br />");
					
					if (user.isDriver()){
						out.println("<p class='titre'>This person is a driver</p>");
					}
					else {
						out.println("<p class='titre'>This person is not a driver</p>");
					}
					
				%>
			</div>
			<br />
			<br />
			<br />
		
		</div>
		
		<div id="map-canvas"
					data-home-profile-lat="<%= data.home.getLat() %>"
					data-home-profile-lng="<%= data.home.getLon() %>"
					data-workplace-profile-lat="<%= wp.getLat() %>"
					data-workplace-profile-lng="<%= wp.getLon() %>"></div>
		
	</div>

</body>
</html>