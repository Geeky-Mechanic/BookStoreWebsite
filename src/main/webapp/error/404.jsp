<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Page Not Found</title>
</head>
<body>
	
	<div align="center">
		<img alt="Logo" src="${pageContext.request.contextPath}/images/BookstoreLogo.png"/>
	</div>

	<div align="center">
		<h2>Sorry the requested page at URL : "${requestScope['javax.servlet.forward.request_uri']}" could not be found</h2>
	</div>
	
	<div align="center">
		<a href="javascript:history.go(-1);">Go Back</a>
	</div>
	
</body>
</html>