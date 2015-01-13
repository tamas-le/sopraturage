<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sopraturage</title>
<link rel="icon" href="images/auto.ico" />
<%
	Integer numberDriver = (Integer) request.getAttribute("driver");
	Integer numbernDriver = (Integer) request.getAttribute("ndriver");
%>
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<script type="text/javascript">
	google.load("visualization", "1", {
		packages : [ "corechart" ]
	});
	google.setOnLoadCallback(drawChart);
	function drawChart() {

		var driver = <%=numberDriver %>;
		var ndriver = <%=numbernDriver %>;

		var data = google.visualization.arrayToDataTable([
				[ 'Status', 'Number' ], [ 'Driver', driver ],
				[ 'Not Driver', ndriver ] ]);

		var options = {
			title : 'User number : Driver / Not driver'
		};

		var chart = new google.visualization.PieChart(document
				.getElementById('piechart'));

		chart.draw(data, options);
	}
</script>

</head>
<body>
	<%
		Integer total = (Integer) request.getAttribute("total");
		out.println("<p>Total number : " + total + "</p>");
	%>
	<div id="piechart" style="width: 900px; height: 500px;"></div>
</body>
</html>