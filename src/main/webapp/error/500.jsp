<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Internal Server Error</title>
</head>
<body>
	
	<div align="center">
		<img alt="Logo" src="${pageContext.request.contextPath}/images/BookstoreLogo.png"/>
	</div>

	<div align="center">
		<h2>Sorry the server has encountered an error while fulfilling your request</h2>
		<h3>Please check back later or contact support</h3>
	</div>
	
	<div align="center">
		<a href="javascript:history.go(-1);">Go Back</a>
	</div>
	
</body>
</html>