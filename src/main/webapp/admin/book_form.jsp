<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>
	<c:if test="${book != null}">
		Edit Book
	</c:if>
		
	<c:if test="${book == null}">
		Create New Book
	</c:if>
</title>
<link rel="stylesheet" href="../css/style.css">
<link rel="stylesheet" href="../css/jquery-ui.min.css">
<link rel="stylesheet" href="../css/jquery-ui.structure.min.css">
<link rel="stylesheet" href="../css/jquery-ui.theme.min.css">
<script type="text/javascript" src="../js/jquery-3.6.1.min.js"></script>
<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
<script type="text/javascript" src="../js/jquery-ui.min.js"></script>
</head>
<body>

	<jsp:directive.include file="header.jsp" />
	
	<div align="center">
	
		<h2 class="pageHeading">
			<c:if test="${book != null}">
				Edit Book
			</c:if>
		
			<c:if test="${book == null}">
				Create New Book
			</c:if>
		</h2>
	</div>
	
	<div align="center">
		<c:if test="${book != null}">
			<form action="update_book" enctype="multipart/form-data" method="post" id="bookForm">
			<input type="hidden" name="bookId" value="${book.bookId}"/>
		</c:if>
		<c:if test="${book == null}">	
			<form action="create_book" enctype="multipart/form-data" method="post" id="bookForm">
		</c:if>
		<table class="newForm">
			
			<tr>
				<td align="right">Category:</td>
				<td>
					<select name="category" id="category">
						<c:forEach items="${categoryList}" var="category">
							<option value="${category.categoryId}" >
								${category.name}
							</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			
			<tr>
				<td align="right">Title: </td>
				<td align="left"><input type="text" name="bookTitle" id="bookTitle" size="20" value="${book.title}" /></td>
			</tr>
			<tr>
				<td align="right">Author: </td>
				<td align="left"><input type="text" name="author" id="author" size="20" value="${book.author}" /></td>
			</tr>
			<tr>
				<td align="right">ISBN: </td>
				<td align="left"><input type="text" name="isbn" id="isbn" size="20" value="${book.isbn}" /></td>
			</tr>
			
			<tr>
				<td align="right">Published Date: </td>
				<td align="left"><input type="text" name="publishDate" id="publishDate" size="20" value="${book.publishDate}"/></td>
			</tr>
			
			<tr>
				<td align="right">Book Image: </td>
				<td align="left">
					<input type="file" name="image" id="image" size="20"/><br/>
					<img id="thumbnail" alt="Image Preview" style="width:20%; margin-top:10px;"/>
				</td>
			</tr>
			
			<tr>
				<td align="right">Price: </td>
				<td align="left"><input type="text" name="price" id="price" size="20" value="${book.price}" /></td>
			</tr>
			
			<tr>
				<td align="right">Description: </td>
				<td align="left"><textarea rows="5" cols="50" name="description" id="description" ></textarea></td>
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

</body>

<script type="text/javascript">

	$(document).ready(function(){
		

	    $("#publishDate").datepicker();
		
	    $("#image").change(function(){
	    	showImageThumbnail(this); 
	    });
	    
		$("#bookForm").validate({
			rules:{
				category: "required",
				bookTitle: "required",
				author:"required",
				isbn:"required",
				publishDate:"required",
				image:"required",
				price: "required",
				description:"required"
			},
			messages:{
				bookTitle: "Please enter a book title",
				author:"Please enter an author name",
				isbn:"Please enter an ISBN number",
				publishDate:"Please enter a publication date",
				image:"Please enter an image",
				price: "Please enter a price",
				description:"Please enter a description",
				category: "Please choose a category"
				
			}
		});
		
		$("#cancel").click(function(){
			history.go(-1);
		});
			
	});
	
	function showImageThumbnail(fileInput){
		var file = fileInput.files[0];
		
		var reader = new FileReader();
		
		reader.onload = function(e){
			$("#thumbnail").attr("src", e.target.result);
		};
		
		reader.readAsDataURL(file);
	}
	
</script>

</html>