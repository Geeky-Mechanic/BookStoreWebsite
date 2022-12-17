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

	<div align="center">
		<h2 class="pageHeading">Admin Dashboard</h2>
	</div>
	
	<hr width="60%"/>
	
	<!-- ------------------------ QUICK ACTIONS SECTION ------------------ -->
	
	<div align="center">
		<h2 class="pageHeading">Quick Actions :</h2>
		<a href="create_book">New Book</a>
		<a href="create_user">New User</a>
		<a href="create_category">New Category</a>
		<a href="create_customer">New Customer</a>
	</div>
	
	<hr width="60%"/>
	
	<!-- ------------------------ RECENT SALES SECTION ------------------ -->
	
	
	<div align="center">
		<h2 class="pageHeading">Recent Sales :</h2>
	</div>
	
	<!-- ------------------------ RECENT REVIEWS SECTION ------------------ -->
	
	
	<div align="center">
		<h2 class="pageHeading">Recent Reviews :</h2>
	</div>
	
	<!-- ------------------------ STATISTICS SECTION ------------------ -->
	
	
	<div align="center">
		<h2 class="pageHeading">Statistics :</h2>
	</div>

	<jsp:directive.include file="footer.jsp"/>
</body>
</html>