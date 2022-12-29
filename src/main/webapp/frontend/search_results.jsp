<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Evergreen Books - Online Book Store</title>
	
	<link rel="stylesheet" href="../css/style.css" />
</head>

<body>
	<jsp:directive.include file="header.jsp" />
	
    <div class="center">
		<h2>Search results</h2>
		
		<c:if test="${bookList.size() == 0 }">
			
			<h3>
				The search returned no results for <i>${keyword}</i>, try a broader search or use different terms
			</h3>
		
		</c:if>
		
		<c:forEach items="${bookList}" var="book">
		<div>
				<div style="display: inline-block; margin: 30px;">
					<div>
						<a href="view_book?bookId=${book.getBookId()}">
							<img width="128px" height="164px" alt="${book.getTitle()}" src="data:image/jpg;base64, ${book.base64Image}"/>
						</a>
					</div>
				</div>
				<div style="display: inline-block; margin: 30px;">
					<div>
						<a href="view_book?bookId=${book.getBookId()}">
							<b>${book.getTitle() }</b>
						</a>
					</div>
					<div>Rating *****</div>
					<div><i>By: ${book.getAuthor() }</i></div>
					<div>
						<p>
							${fn:substring(book.getDescription(), 0, 100) }...
						</p>
					</div>
					<div style="display: inline-block; margin: 30px;">
						<b>$ ${book.getPrice() }</b>
						<button>ADD TO CART</button>
					</div>
			
				</div>
		</div>
		</c:forEach>
		
	</div>
	
	<jsp:directive.include file="footer.jsp" />




</body>
</html>