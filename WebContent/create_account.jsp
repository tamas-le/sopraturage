<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="java.util.LinkedList"%>
<%@page import="sopraturage.models.tables.Address"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>

<!doctype html>
<html lang="fr">
<head>
<title>Sopraturage</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="description"
	content="Sopraturage, organiser le covoiturage des employés" />
<meta name="keywords" content="" />

<link href="css/sopraturage.css" rel="stylesheet" type="text/css" />


</head>

<body>

	<div class="page">
	
		<div class="head">
			<h1>Sopraturage</h1>
		</div>
		
		<div class="titre_page">
			<h2>Create an account</h2>
		</div>



		<div class="form_create_account">

			<form id="form_create_account" class="formulaire"
				action="inscriptionservlet.do" method="post" novalidate>


				<p>Personal information :</p>
				<input type="text" name="name" placeholder="Name" required/><br /> <br />
				<input type="text" name="surname" placeholder="Surname" required/><br /> <br />
				<input type="password" name="pass" placeholder="Password" required/><br />
				<br /> 
				<input type="email" id="email" name="email" placeholder="Email" required/><br /> <br /> 
				<input type="tel" id="phone" name="phoneNumber" placeholder="Phone Number" maxlength="10" required/><br />
				<br /> <br />
				<p>Address :</p>
				<input type="text" name="number" placeholder="Number" required/> <br /> <br />
				<SELECT name="typeWay" required>
					<option>rue</option>
					<option>avenue</option>
					<option>chemin</option>
					<option>allee</option>
					<option>boulevard</option>
					<option>route</option>
					<option>ruelle</option>
				</SELECT><br /> <br /> 
				<input type="text" name="way" placeholder="Name of the way" required/> <br /> <br /> 
				<input type="number" id="postCode" name="postCode" placeholder="PostCode" required /> <br />
				
				
				<br /> <input type="text" name="city" placeholder="City" required /> <br />
				<br /> <br />
				<p>Workplace :</p>
				<SELECT name="workplace" required>
					<%
						LinkedList<Address> attribut = (LinkedList<Address>) request.getAttribute("adresse");
												for (Address a:attribut){
													out.println("<option>"+a.toStringBetter()+"</option>");
												}
					%>

				</SELECT> <br /> <br />
				<p>More information :</p>
				<input type="checkbox" name="driver" value="driver_ok" /><label
					for="driver">I am a driver</label> <br /> 
				<input type="checkbox" name="notify" value="notify_ok" /><label for="notify">I want to be notify</label><br /> <br /> 
				
				<p>Days of work :</p>
				<input type="checkbox" name="lundi" value="lundi_ok" /><label for="lundi">Monday</label> 
				<input type="checkbox" name="mardi" value="mardi_ok" /><label for="mardi">Thusday</label> 
				<input type="checkbox" name="mercredi" value="mercredi_ok" /><label for="mercredi">Wednesday</label> 
				<input type="checkbox" name="jeudi" value="jeudi_ok" /><label for="jeudi">Thirsday</label> 
				<input type="checkbox" name="vendredi" value="vendredi_ok" /><label for="vendredi">Friday</label> 
				<input type="checkbox" name="samedi" value="samedi_ok" /><label for="samedi">Saturday</label> 
				
				<br/>
				<br/>
				
				<p>Hours of work : </p>
				
				<p>Beginning : </p>
				<SELECT class="time" name="Heures" required>
				<%
					for (Integer i=0; i<=23 ; i++){
						if (i.toString().length()==1){
							out.println("<option>0" + i + "</option>");
						}
						else {
							out.println("<option>" + i + "</option>");
						}
					}
				
				%>
				</SELECT>:
				<SELECT class="time" name="Minutes" required>
				<%
					for (Integer i=0; i<=59 ; i++){
						if (i.toString().length()==1){
							out.println("<option>0" + i + "</option>");
						}
						else {
							out.println("<option>" + i + "</option>");
						}
					}
				
				%>
				</SELECT>
				
				<p>Ending : </p>
				<SELECT class="time" name="Heures" required>
				<%
					for (Integer i=0; i<=23 ; i++){
						if (i.toString().length()==1){
							out.println("<option>0" + i + "</option>");
						}
						else {
							out.println("<option>" + i + "</option>");
						}
					}
				
				%>
				</SELECT>:
				<SELECT class="time" name="Minutes" required>
				<%
					for (Integer i=0; i<=59 ; i++){
						if (i.toString().length()==1){
							out.println("<option>0" + i + "</option>");
						}
						else {
							out.println("<option>" + i + "</option>");
						}
					}
				
				%>
				</SELECT>
				<br />
				<br />
				<br />
				
				<input type="submit" id="submit" name="connexion" value="&rarr;" /> <br /> <br />
				
			</form>
			<p></p>
		</div>

	</div>



</body>



</html>