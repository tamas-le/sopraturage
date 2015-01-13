<%@page import="sopraturage.util.Time"%>
<%@page import="sopraturage.models.tables.UserTime"%>
<%@page import="sopraturage.Recherche"%>
<%@page import="sopraturage.models.tables.Workplace"%>
<%@page import="sopraturage.models.tables.Address"%>
<%@page import="java.util.LinkedList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link href="css/search.css" rel="stylesheet" type="text/css" />
	<script src="jquery-2.1.1.min.js"></script>
    <script type="text/javascript"
            src="https://maps.googleapis.com/maps/api/js?key=AIzaSyASDA0eZvH1781uZidsoWCbt_8s4aj1P88"></script>
    <script type="text/javascript" src="./scripts/maps.js"></script>
	<script type="text/javascript">
		function switchInfoPerso() {
			divInfo = document.getElementById('divacacher');
			if (divInfo.style.display == 'none')
				divInfo.style.display = 'block';
			else
				divInfo.style.display = 'none';
		}
	
		function displayHome() {
			divHome = document.getElementById('home_input');
			if (document.getElementById('home').checked)
				divHome.style.display = 'block';
			else
				divHome.style.display = 'none';
		}
	
		function displayWorkplace() {
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

	<%@ include file="header.jsp"%>

	<div class="page">

		<div class="content">
		
			<div id="map-canvas"></div>
				
		</div>

		<div class="titre_page">
			<%
				Recherche recherche = (Recherche) request.getAttribute("recherche");
				out.print("<h2>" + recherche.displayRecherche() + "</h2>");
			%>
		</div>

		<br /> <br /> <br />

		<div class="button_adv">
			<a href="javascript: switchInfoPerso();">Change Parameter</a>
		</div>
		<br /> <br /> <br />



		<div id="divacacher" class="form_create_account">

			<div class="titre_page">
				<h2>Search by</h2>
			</div>

			<form method="post" action="">

				<p>
					<input type="checkbox" id="home" onClick="displayHome();"
						name="home" value="home_ok" /> <label for="home">Home</label>
				</p>

				<div id="home_input">
					<p>
						<label for="town"> Town :</label> <input type="text" name="town"
							id="town" />
					</p>

					<p>
						<label for="PostCode"> Post code : </label> <input type="text"
							name="PostCode" id="PostCode" />
					</p>
				</div>

				<p>
					<input type="checkbox" id="workplace" onClick="displayWorkplace();"
						name="workplace_box" value="workplace_ok" /> <label
						for="workplace">Workplace</label>
				</p>

				<div id="workplaces_input">
					<select name="workplace">
						<%
							LinkedList<Workplace> workplaces = data.workplaces;

							for (Address a : workplaces) {
								out.println("<option>" + a.toStringBetter() + "</option>");
							}
						%>


					</select> <br />
				</div>
				<br />

				<p class="important_information">Are you looking for a driver ?</p>

				<p>
					<input type="radio" name="driver" value="driver" /> <label
						for="driver">Yes</label> <br /> <input type="radio" name="driver"
						value="not_driver" /> <label for="not_driver">No</label> <br />
					<input type="radio" name="driver" value="dont_care" checked /> <label
						for="not_driver">I do not mind</label>
				</p>


				<input type="submit" id="submit" name="connexion" value="&rarr;" /><br />
			</form>
		</div>

		<div class="titre_page">
			<h2>Results</h2>
		</div>
	
	
		<%
			LinkedList<UserTime> listResults = (LinkedList) request
					.getAttribute("resultats");
			int j=0;
			for (UserTime ut : listResults) {
				if (j%2==0){
	 				out.println("<div class='pair'>");
				}
				else{
					out.println("<div class='impair'>");
				}
				out.println("<div class='avatar'><img src='images/avatar/10424266_10152444948906555_9189562097428624070_n.jpg' height='90px' width='80px'/></div>");
				out.println("<div class='infos'>");
				out.println("<p class='nom'>"+ut.getUser().getName()+" "+ut.getUser().getSurname()+"</p>");
				out.println("<p class='email'>"+ut.getUser().getEmail()+"</p>");
				out.println("<p class='location'>Location :"+Time.convertToTime(ut.getTime())+" from you</p>");
				out.println("</div>");
				out.println("<div class='more_profil'>");
				out.println("<a class='more' href='profile?id="+ut.getUser().getUserId()+"'>View profil</a>");
				out.println("</div>");
				out.println("</div>");
				j++;
			}
		%>


	</div>

</body>
</html>