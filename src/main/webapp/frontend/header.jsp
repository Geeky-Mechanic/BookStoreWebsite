     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div align="center">
	<div>
		<img alt="Bookstore Logo" src="images/BookstoreLogo.png" />
	</div>
	<div>
		<input type="text" name="keyword" size="50" placeholder="Search"/>
		<input type="button" value="Search"/>
		
		<a href="login">Sign in</a> |
		<a href="register">Register</a> |
		<a href="view_cart">Cart</a>
	</div>
	<div>
	
	<c:forEach var="category" items="${listCategory}" varStatus="status">
	<a href="view_category?id=${category.categoryId}">
		<b><c:out value="${category.name}" /></b>
	</a>
	<c:if test="${not status.last}"> | </c:if>
	</c:forEach>
	
	</div>
</div>