<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>
	<c:if test="${user != null}">
		Edit User
	</c:if>
		
	<c:if test="${user == null}">
		Create New User
	</c:if>
</title>
<link rel="stylesheet" href="../css/style.css">
<script type="text/javascript" src="../js/jquery-3.6.1.min.js"></script>
<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
</head>
<body>

	<jsp:directive.include file="header.jsp" />
	
	<div align="center">
	
		<h2 class="pageHeading">
			<c:if test="${user != null}">
				Edit User
			</c:if>
		
			<c:if test="${user == null}">
				Create New User
			</c:if>
		</h2>
	</div>
	
	<div align="center">
		<c:if test="${user != null}">
			<form action="update_user" method="post" id="userForm">
			<input type="hidden" name="userId" value="${user.userId}"/>
		</c:if>
		<c:if test="${user == null}">	
			<form action="create_user" method="post" id="userForm">
		</c:if>
		<table class="newForm">
			<tr>
				<td align="right">Email: </td>
				<td align="left"><input type="text" name="email" id="email" size="20" value="${user.email}" /></td>
			</tr>
			<tr>
				<td align="right">Full Name: </td>
				<td align="left"><input type="text" name="fullname" id="fullname" size="20" value="${user.fullName}" /></td>
			</tr>
			<tr>
				<td align="right">Password: </td>
				<td align="left"><input type="password" name="password" id="password" size="20" /></td>
			</tr>
		
			<tr>
				<td colspan="2" align="center">
					<button type="submit">Save</button>
					<button id="cancel">Cancel</button>
				</td>
			</tr>
		</table>
		
	</form>
	</div>
	
	<jsp:directive.include file="footer.jsp"/>

<script type="text/javascript">

	$(document).ready(function(){
		$("#userForm").validate({
			rules:{
				email:{
					required: true,
					email: true,
				},
				fullname:"required",
				password:"required",
			},
			messages:{
				email:{
					required: "Please enter email.",
					email: "Please enter a valid email address.",
				},
				fullname:"Please enter Full Name.",
				password:"Please enter a password."
			}
		});
		
		$("#cancel").click(function(){
			history.go(-1);
		});
		
	});
	
</script> 

</body>
</html>