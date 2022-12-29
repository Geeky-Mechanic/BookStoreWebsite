<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="center">
	<img alt="BookStoreLogo" src="../images/BookstoreAdminLogo.png"/>

	<div>
		Welcome <c:out value="${sessionScope.email}"/> | <a href="logout">Logout</a>
	</div>
	<div id="headerMenu">
		<div class="menuItem">
			<a href="list_users"><img alt="Users" src="../images/users.png" /><br/>Users</a>
		</div>
		<div class="menuItem">
			<a href="list_category"><img alt="Categories" src="../images/category.png"/><br/>Categories</a>
		</div>
		<div class="menuItem">
			<a href="list_books"><img alt="Books" src="../images/bookstack.png"/><br/>Books</a>
		</div>
		<div class="menuItem">
			<a href="customers"><img alt="Customers" src="../images/customer.png"/><br/>Customers</a>
		</div>
		<div class="menuItem">
			<a href="reviews"><img alt="Reviews" src="../images/review.png"/><br/>Reviews</a>
		</div>
		<div class="menuItem">
			<a href="orders"><img alt="Orders" src="../images/order.png"/><br/>Orders</a>
		</div>
		
	</div>

</div>
