<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Admin</title>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>
<body>
	<%@include file="header.jsp" %>
	<div class="container">
		<h3>Products</h3>
		
			
	<div class="prods">
	<c:forEach var="a" items="${products}">
		<div class="card" style="margin-top:10px;margin-bottom:10px;">
		  <div class="card-header">${a.title}</div>
		  <div class="card-body">
		    <h4 class="card-title">${a.price}</h4>
		    <p class="card-text">${a.description}</p>
		 </div>
		</div>
	</c:forEach>
	</div>
	</div>

	
</body>
</html>