<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Admin</title>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js" integrity="sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ" crossorigin="anonymous"></script>

</head>
<body>
	<%@include file="header.jsp" %>
	<div class="container">
		<h3>Products</h3>
	<div class="row row-form">
		<div class="col-sm-7">
		<form name="form1">
		  <div class="form-group">
		    <label for="title">Title</label>
		    <input type="text" name="title" class="form-control" id="title" placeholder="Enter product title">
		  </div>
		  
		  <div class="form-group">
		    <label for="exampleInputPassword1">Price</label>
		    <input type="number" name="price" class="form-control" id="exampleInputPassword1" placeholder="Price">
		  </div>
		  
		  <div class="form-group">
		    <label for="description">Description</label>
		    <input type="text" name="description" class="form-control" id="description" placeholder="Enter description">
		  </div>
		  
		  <div class="form-group">
		    <label for="image">Image</label>
		    <input type="text" name="image" class="form-control" id="image" placeholder="Link to image">
		  </div>
		  
		  <div class="form-group">
		    <label for="category">Category</label>
		    <input type="number" name="category" class="form-control" id="category" placeholder="Enter category">
		  </div>
		  
		  <button class="btn btn-primary">Submit</button>
		</form>
		</div>
	
	<style>
	.row-form{
	margin: 20px auto;
	}
	.container{
	margin-top: 30px;
	
	}
	div.col-sm-5{
	padding: 10px;
	
	}
	
	</style>
	<script>
	$(document).ready(function(){
	    $("button").click(function(){
	 
	        
	        var form = $('#form1');
	        form.submit(function () {

	         $.ajax({
	         type: form.attr('method'),
	         url: form.attr('action'),
	         data: value,
	         success: function (data) {
	         var result=data;
	         $('#result').attr("value",result);
	         }
	         });
	         return false;
	         });
	    });
	});
	</script>
		<div col-sm-5 >
			<p>Men's Clothing and Accessories - 1</p> 
			<p>Women's Clothing and Accessories - 2</p> 
			<p>Beauty and Health - 3</p> 
			<p>Sports and Entertainment - 4</p> 
			<p>Toys and Hobbies - 5</p> 
			<p>Computer and Electronics - 6</p> 
			<p>Home and Garden - 7</p> 
			<p>Other - 8</p>
		</div>
	</div>
	</div>
			
	
	
</body>
</html>