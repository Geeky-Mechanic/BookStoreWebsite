<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>${book.getTitle()}</title>
	
	<link rel="stylesheet" href="../css/style.css" />
</head>

<body>
	<jsp:directive.include file="header.jsp" />
	
    <div class="center">
    
    	<table class="book">
    		<tr>
    			<td colspan="3" align="left">
    				<h2>${book.getTitle()}</h2>
					<h3>By ${book.getAuthor()}</h3>
					
    			</td>
    		</tr>
    		
    		<tr>
    			<td rowspan="2">
    				<img width="240px" height="300px" alt="${book.getTitle()}" src="data:image/jpg;base64, ${book.base64Image}"/>
    			</td>
    			<td valign="top" align="left">
    				Rating *****
    			</td>
    			<td valign="top" rowspan="2" width="20%">
    				<h2>$ ${book.getPrice() }</h2>
    				<br/>
    				<button>ADD TO CART</button>
    			</td>
    		</tr>
    		
    		<tr>
    			<td valign="top">
    				${book.getDescription()}	
    			</td>
    		</tr>
    		
    		<tr>
    			<td colspan="1">
    				<h2>Customer Reviews</h2>
    			</td>
    			
    			<td colspan="2">
    				Write A Review
    			</td>
    		</tr>
    		
    	</table>
    
		
	</div>
	
	<jsp:directive.include file="footer.jsp" />




</body>
</html>