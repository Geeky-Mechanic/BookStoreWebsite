<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Manage Books - Evergreen Bookstore Administration</title>
<link rel="stylesheet" href="../css/style.css">
<script type="text/javascript" src="../js/jquery-3.6.1.min.js"></script>
<script type="text/javascript" src="../js/jquery.validate.min.js"></script>

</head>
<body>

	<jsp:directive.include file="header.jsp" />

	<div class="center">
		<h2 class="pageHeading">Book Management</h2>
		<a href="create_book">Create New Book</a>
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
				<th>Image</th>
				<th>Title</th>
				<th>Author</th>
				<th>Category</th>
				<th>Price</th>
				<th>Last Updated</th>
				<th>Actions</th>
			</tr>
			<c:forEach var="book" items="${bookList}" varStatus="status">
				<tr>
					<td>${status.index + 1}</td>
					<td>${book.getBookId()}</td>
					<td><img width="84px" height="110px" alt="${book.title}" src="data:image/jpg;base64, ${book.base64Image}"/></td>
					<td>${book.getTitle()}</td>
					<td>${book.getAuthor()}</td>
					<td>${book.getCategory().getName()}</td>
					<td>$ ${book.getPrice()}</td>
					<td><fmt:formatDate pattern="dd/MM/yyyy" value="${book.getLastUpdateTime()}"/></td>
					<td>
						<a href="edit_book?bookId=${book.bookId}">Edit</a>
						<a href="javascript:void(0);" class="deleteLink" id="${book.getBookId()}">Delete</a>
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
				var bookId = $(this).attr("id");
				if(confirm("Do you really want to delete the book with ID " + bookId + " ?")){
					window.location = "delete_book?bookId=" + bookId;	
					}
			});
		});
	});

	
</script>

</html>