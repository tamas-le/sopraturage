<%@page import="sopraturage.models.tables.TinyUser"%>
<%@page import="java.util.LinkedList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sopraturage</title>
<link href="css/account.css" rel="stylesheet" type="text/css" />

</head>
<body>

	<div class="page">

		<h4>Manage accounts</h4>

		<div class="titre_page">
			<h2>List of the users</h2>
		</div>

		<div class="form_create_account" >

			<form method='post'>
				<TABLE BORDER="1">
					<tr>
						<th><p class='important_information'>Name</p></TH>
						<th><p class='important_information'>Email</p></TH>

						<th><p class='important_information'>Modify</p></TH>
						<th><p class='important_information'>Delete</p></TH>
					</tr>
					
					<%
						LinkedList<TinyUser> list = (LinkedList) request.getAttribute("list");
											
					int i=0;
											
					for (TinyUser t : list) {
												
						if (i%2==0){
							out.println("<TR class='paire'>");
						}
						else{
							out.println("<TR class='impaire'>");
						}
						out.println("<td><p>" + t.getName() + " " + t.getSurname() + "</p></td>");
						out.println("<td><p>" + t.getEmail() + "</p></td>");
						out.println("<td><div class='special_input'> <input class='action' type='submit' name='M" + t.getId()+ "' value='Modify'/></div> </td>");
						out.println("<td>"+ "<input class='action' type='submit' name='D"+ t.getId() + "' value='Delete'/></td>");
						out.println("</TR>");
						i++;
													
					}
					%>

				</TABLE>
			</form>
		
		<br />
		
		</div>
	</div>
	
</body>
</html>