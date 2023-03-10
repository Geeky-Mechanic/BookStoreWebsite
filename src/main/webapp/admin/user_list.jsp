<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Manage Users - Evergreen Bookstore Administration</title>
<link rel="stylesheet" href="../css/style.css">
<script type="text/javascript" src="../js/jquery-3.6.1.min.js"></script>
<script type="text/javascript" src="../js/jquery.validate.min.js"></script>

</head>
<body>

	<jsp:directive.include file="header.jsp" />

	<div class="center">
		<h2 class="pageHeading">User Management</h2>
		<a href="create_user">Create New User</a>
	</div>
	
 	<c:if test="${message != null}">
		<div class="center">
			<h4 class="message">${message}</h4>
		</div>
	</c:if>
	<div class="center">
		<table border="1" cellPadding="5">
			<tr>
				<th>Index</th>
				<th>ID</th>
				<th>Email</th>
				<th>Full Name</th>
				<th>Actions</th>
			</tr>
			<c:forEach var="user" items="${listUsers}" varStatus="status">
				<tr>
					<td>${status.index + 1}</td>
					<td>${user.userId}</td>
					<td>${user.email}</td>
					<td>${user.fullName}</td>
					<td>
						<a href="edit_user?id=${user.userId}">Edit</a>
						<a href="javascript:void(0);" class="deleteLink" id="${user.userId}">Delete</a>
					</td>
				</tr>
			</c:forEach>	
		</table>
	</div>
	
	<jsp:directive.include file="footer.jsp"/>

</body>



<script type="text/javascript">

	$(document).ready(function(){
		$(".deleteLink").each(function(){
			$(this).on("click", function(){
				var userId = $(this).attr("id");
				if(confirm("Do you really want to delete the user with ID " + userId + " ?")){
					window.location = "delete_user?userId=" + userId;	
					}
			});
		});
	});

	
</script>

</html>