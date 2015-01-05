<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sopraturage</title>
</head>
<body>
	<h2>What kind of report do you want ?</h2>
	<form method='POST'>

		<input type='radio' name='report' value='Connexion' >
		Connexion number
		<br /> <input type='radio' name='report'
			value='Driver' checked>
		User number : Driver/Not Driver
		<br /> <input type='radio' name='report'
			value='Location' >
		User number per location
		<br /><br /><br /> <input type="submit" name="go" value="Generate Report" >
	</form>

	<a href='admin'>Back to the admin home page</a>

</body>
</html>