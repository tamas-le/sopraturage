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
				<input type="text" name="name" placeholder="Name" /><br /> <br />
				<input type="text" name="surname" placeholder="Surname" /><br /> <br />
				<input type="password" name="pass" placeholder="Password" /><br />
				<br /> 
				<p>Once chosen, your email will become your pseudo</p>
				<input type="email" id="email" name="email"
					placeholder="Email" /><br /> <br /> <input type="tel" id="phone"
					name="phoneNumber" placeholder="Phone Number" maxlength="10" /><br />
				<br /> <br />
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
					type="number" id="postCode" name="postCode" placeholder="PostCode"
					required /> <br />
				<p class="error"></p>
				<br /> <input type="text" name="city" placeholder="City" /> <br />
				<br /> <br />
				<p>Workplace :</p>
				<SELECT name="workplace">
					<%
						LinkedList<Address> attribut = (LinkedList<Address>) request.getAttribute("adresse");
												for (Address a:attribut){
													out.println("<option>"+a.toStringBetter()+"</option>");
												}
					%>

				</SELECT> <br /> <br />
				<p>More information :</p>
				<input type="checkbox" name="driver" value="driver_ok" /><label
					for="driver">I am a driver</label> <br /> <input type="checkbox"
					name="notify" value="notify_ok" /><label for="notify">I
					want to be notify</label><br /> <br /> <br /> <input type="submit" id="submit"
					name="connexion" value="&rarr;" /><br />
			</form>
			<p></p>
		</div>

	</div>



</body>



</html>