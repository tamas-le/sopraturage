<%@page import="sopraturage.ApplicationData"%>
<div class="page">

	<div class="head">
		<div class="titre_sop">
			<h1>Sopraturage</h1>
		</div>
		<div class="pseudo">
			<span class="img_rouages"><img src="images/gear.png"
				width="20px" height="20px"></span>
			<%
				ApplicationData data = (ApplicationData) session
						.getAttribute("data");
				boolean LOCAL=true;

				String email = data.localUser.getEmail();
				out.println("<a href='profile?id=" + data.localUser.getUserId()
						+ "' title='My profile'>" + email + "</a>");
			%>

		</div>
	</div>


	<ul class="menu">

		<a href="search"><li>Search</li></a>
		<%
			out.println("<a href='profile?id=" + data.localUser.getUserId()
					+ "'><li>My profile</li></a>");
		%>



		<a href="modify"><li>Modify account</li></a>

		<%
			Boolean admin = data.admin;
			if (admin) {
				out.println("<a href='admin'><li>Administration</li></a>");
			}
		%>

		<a href="disconnect"><li>Log out</li></a>

	</ul>


</div>

<br />