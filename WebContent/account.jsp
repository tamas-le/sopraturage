<%@page import="sopraturage.models.tables.TinyUser"%>
<%@page import="java.util.LinkedList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sopraturage</title>
</head>
<body>

	<form method='post'>
		<TABLE BORDER="1">
			<TR>
				<TH>Name</TH>
				<TH>Email</TH>

				<TH>Actions</TH>
			</TR>

			<%
				LinkedList<TinyUser> list = (LinkedList) request.getAttribute("list");

				for (TinyUser t : list) {
					out.println("<TR>");
					out.println("<td>" + t.getName() + " " + t.getSurname()
							+ "</td>");
					out.println("<td>" + t.getEmail() + "</td>");
					out.println("<td> <input type='submit' name='M" + t.getId()
							+ "' value='Modify'/> <input type='submit' name='D"
							+ t.getId() + "' value='Delete'/></td>");
					out.println("</TR>");
				}
			%>

		</TABLE>
	</form>
	<a href='admin'>Back to the admin home page</a>


</body>
</html>