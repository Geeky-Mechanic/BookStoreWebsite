<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
	
	<div class="center">
	
		<h2 class="pageHeading">
			<c:if test="${book != null}">
				Edit Book
			</c:if>
		
			<c:if test="${book == null}">
				Create New Book
			</c:if>
		</h2>
	</div>
	
	<div class="center">
		<c:if test="${book != null}">
			<form action="edit_book" enctype="multipart/form-data" method="post" id="bookForm">
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
							<c:if test="${category.categoryId eq book.category.categoryId}">
								<option value="${category.categoryId}" selected>
							</c:if>
							<c:if test="${category.categoryId ne book.category.categoryId}">
								<option value="${category.categoryId}">
							</c:if>
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
				<td align="right">Published Date <br/> (dd/MM/yyyy): </td>
				<td align="left"><input type="text" name="publishDate" id="publishDate" size="20" value="<fmt:formatDate pattern='dd/MM/yyyy' value='${book.publishDate}'/>"/></td>
			</tr>
			
			<tr>
				<td align="right">Book Image: </td>
				<td align="left">
					<input type="file" name="image" id="image" size="20"/><br/>
					<img id="thumbnail" alt="Image Preview" style="width:20%; margin-top:10px;" src="data:image/jpg;base64, ${book.base64Image}" width="84px" height="110px" />
				</td>
			</tr>
			
			<tr>
				<td align="right">Price: </td>
				<td align="left"><input type="text" name="price" id="price" size="20" value="${book.price}" /></td>
			</tr>
			
			<tr>
				<td align="right">Description: </td>
				<td align="left"><textarea rows="20" cols="50" name="description" id="description" >${book.description}</textarea></td>
			</tr>
		
			<tr>
				<td colspan="2" align="center">
					<button type="submit">Save</button>
					<button type="button" id="cancel">Cancel</button>
				</td>
			</tr>
		</table>
		
	</form>
	</div>
	
	<jsp:directive.include file="footer.jsp"/> 

</body>

<script type="text/javascript">

	$(document).ready(function(){
		

	    $("#publishDate").datepicker({
	    	dateFormat:"dd/mm/yy"
	    });
		
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
				
				<c:if test="${book == null}">
				image:"required",
				</c:if>
				
				
				price: "required",
				description:"required"
			},
			messages:{
				bookTitle: "Please enter a book title",
				author:"Please enter an author name",
				isbn:"Please enter an ISBN number",
				publishDate:"Please enter a publication date",
				
				<c:if test="${book == null}">
				image:"Please enter an image",
				</c:if>
				
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