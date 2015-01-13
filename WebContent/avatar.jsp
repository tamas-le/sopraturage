<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Sopraturage - File Uploading Form</title>
<link rel="icon" href="images/auto.ico" />
<link href="css/sopraturage.css" rel="stylesheet" type="text/css" />
</head>
<body>

<%@ include file="header.jsp"%>

	<div class="page">

		<div class="titre_page">
			<h2>Changing avatar</h2>
		</div>


		<div class="form_create_account">
			<p class="important_information">Select a file to upload :</p>
			<br />

			<form action="upload" method="post" enctype="multipart/form-data">
				<input type="file" name="file" size="50" /> <br /><br /><br /> <input
					type="submit" value="Upload File" />
			</form>
		</div>
	</div>

</body>
</html>

