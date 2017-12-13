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
		<h3>Welcome to Admin Panel</h3>
	
<style>
.carousel-item img, img.first-img{
	height:400px; 
	width:auto !important; 
	margin: 10px auto;
	text-align: center;
	align-items: center;
}

.wrapper .card{
	margin: 5px 0;
}
.wrapper .card img{
	max-width: 315px;
	margin: 0 auto;
}

.carousel-caption{
	color: black;
}
</style>	
	<div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
  <div class="carousel-inner">
  	
     <div class="carousel-item active">
     	<img class="first-img" src="https://www.itsgotsoul.co.uk/wp-content/uploads/2014/06/shop-online-banner.jpg">
    </div>
    <c:forEach var="a" items="${products}">
    <div class="carousel-item">
      <img class="d-block w-100" src=${a.image} height=400px>
      <div class="carousel-caption d-none d-md-block">
	    <h3>${a.title}</h3>
	    <p>id: ${a.id}</p>
	  </div>
    </div>
   
    	</c:forEach>
  </div>
   <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
    <span class="sr-only">Previous</span>
  </a>
  <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
    <span class="carousel-control-next-icon" aria-hidden="true"></span>
    <span class="sr-only">Next</span>
  </a>
</div>


<script>
$('.carousel').carousel({
	  interval: 3000
	})
</script>

	
	<script>
	
	
	
/* 	$(document).ready(function(){
	 function check(id)
     {  
		console.log(id)
		var urlGet = "http://localhost:8080/OnlineShopProject/api/products/delete/"+ id;
		Jquery.ajax({
		url: urlGet,
		type: "GET",
		
		success: function() {
		  alert("success");
		},
		
		error: function() {
		  alert( "Sorry, there was a problem!" );
		}
		});           
     }  
	} */
	
/* 
	    $(document).ready(function() {                        
	        $('#delete').click(function(event) {  
	            var id=$('#delete').val();
	            console.log(id);
	         $.get('MainServlet',{deleteID:id},function(responseText) { 
	                $('#welcometext').text(responseText);         
	            });
	        }); */
	        
	        function check(id){
	    		console.log('delelelelle')
var urlGet = "http://localhost:8080/OnlineShopProject/api/products/delete/" + id ;
	        
	    		var xhr = new XMLHttpRequest();

	    		
	    		xhr.open('GET', urlGet, false);

	    		// 3. Отсылаем запрос
	    		xhr.send();
	    		
	    		location.reload();

/* 	         $.get(
	        		 urlGet,
	        		 {},
	        		 null); */
	    }

	        $(document).ready(function(){
	    	    $("#delete").click(function(){
	    	    	var form = $('#form1');
	    	    	console.log(form);
	    	 
	    	    	var urlGet = "http://localhost:8080/OnlineShopProject/api/products/delete/" ;
	    	        
	    	        form.submit(function () {

	    	         $.ajax({
	    	         type: "GET",
	    	         url: urlGet,
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
<div class="wrapper row">
	<c:forEach var="a" items="${products}">

		
		<div class="card col-sm-4">
		  <img class="card-img-top" src=${a.image} alt="Card image cap" height=250px width=auto>
		  <div class="card-body">
		    <h4 class="card-title">${a.title}</h4>
		    <h6 class="card-subtitle mb-2 text-muted">${a.price}</h6>
		    <p class="card-text">${a.description}</p>
			<input type="button" class="btn btn-primary" name="delete" value="Delete" id="delete" onclick="check(${a.id})"/> 
		  
		  </div>
		</div>
		
		
	</c:forEach>

	</div>

  </div>  
</body>
</html>