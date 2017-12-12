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
		<h3>Orders</h3>
			<div class="wrapper">
	<c:forEach var="a" items="${orders}">
		<div class="card" style="margin-top:10px;margin-bottom:10px;">
		  <div class="card-header">Order: ${a.id}</div>
		  <div class="card-body">
		    <h4 class="card-title">ProductID: ${a.product_id}</h4>
		    <p class="card-text">UserID: ${a.user_id} </p>
		    <p class="card-text">Address: ${a.address} </p>
		 </div>
		</div>
	</c:forEach>
	</div>
	</div>
</body>
</html>