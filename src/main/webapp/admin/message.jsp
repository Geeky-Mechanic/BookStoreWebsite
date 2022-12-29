<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Evergreen Bookstore Administration</title>
<link rel="stylesheet" href="../css/style.css">
</head>
<body>

	<jsp:directive.include file="header.jsp" />
	
	<div class="center">
		<h3 class="message errorMessage">${message}</h3>
	</div>
	
	<div class="center">
		<a href="javascript:history.go(-1);"><b>Go Back</b></a>
	</div>
	
	<jsp:directive.include file="footer.jsp"/>

</body>
</html>