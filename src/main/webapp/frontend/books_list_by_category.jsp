<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Books in - ${category.getName()}</title>
	<link rel="stylesheet" href="../css/style.css" />
</head>
<body>
	
	<jsp:directive.include file="header.jsp" />
    	<div class="center">
			<h2>${category.getName()}</h2>
		</div>
		
		
		<div class="center">
			<c:forEach items="${bookList}" var="book" varStatus="status">
				<div style="display: inline-block; margin: 30px;">
					<div>
						<a href="view_book?bookId=${book.getBookId()}">
							<img width="128px" height="164px" alt="${book.getTitle()}" src="data:image/jpg;base64, ${book.base64Image}"/>
						</a>
					</div>
					<div>
						<a href="view_book?bookId=${book.getBookId()}">
							<b>${book.getTitle() }</b>
						</a>
					</div>
					<div>Rating *****</div>
					<div><i>By: ${book.getAuthor() }</i></div>
					<div><b>$ ${book.getPrice() }</b></div>
				</div>
			</c:forEach>
		</div>
		
	<jsp:directive.include file="footer.jsp" />
	
</body>
</html>