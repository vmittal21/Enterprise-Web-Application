<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<% 
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8" />
    <meta
      name="viewport"
      content="width=device-width, initial-scale=1, shrink-to-fit=no"
    />
    <meta name="description" content="" />
    <meta name="author" content="" />

    <title>Health Hub Homepage</title>
    <!-- Link to font awesome CDN -->
    <link href="https://use.fontawesome.com/releases/v5.0.4/css/all.css" rel="stylesheet">
    <!-- Link to bootstrap CDN -->
    <link
      rel="stylesheet"
      href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
      integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
      crossorigin="anonymous"
    />
    <!-- Custom styles for this template -->
    <link href="css/style.css" rel="stylesheet" />
    <!-- Import font awesome CDN -->
    <script src="https://use.fontawesome.com/6373855059.js"></script>
    <!-- Link to bootstrap CDN -->
    <script
      src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
      integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
      crossorigin="anonymous"
    ></script>
    <script
      src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
      integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
      crossorigin="anonymous"
    ></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script
      src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
      integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
      crossorigin="anonymous"
    ></script>
    <script type="text/javascript" src="js/autocomplete.js"></script>
    <script type="text/javascript" src="js/cart.js"></script>
    <script type="text/javascript" src="js/news.js"></script>
	<script>
		$(document).ready(function(){
			$.ajax({
		        url: "UserProfile",
		        type: "GET",
		        data: {
		        	method:"check"
		        },
		        success: function (msg) {
		        	msg = $.parseJSON(msg);
		        	console.log(msg);
		            if(msg == "true"){
		            	alert("Your profile is incomplete, please complete your profile");
		            }
		        },
		        error: function(){
		            console.log("error occurred while making ajax call;")
		        }
		    });    
		});
	</script>
  </head>

  <body>
    <!-- Header-->
    <header class="navbar navbar-expand fixed-top justify-content-between">
      <a class="navbar-brand text-light" href="index.jsp">Health Hub</a>
      <form id="search-form" class="form-inline">
       <div class="autocomplete">
	      	<input
	          id="search-input"
	          class="form-control form-control-sm mr-sm-2"
	          type="search"
	          placeholder="Search"
	          aria-label="Search"
	          onkeyup="doCompletion()"
	        />
	        <table id="complete-table" class="autocomplete-items"></table>
      	</div>
        <button id="search-btn" class="btn btn-sm my-sm-0" type="submit"><i class="fas fa-search"></i></button>
      </form>
      <ul class="navbar-nav flex-row">
        <li class="nav-item dropdown mr-3">
          <a
            class="nav-link dropdown-toggle text-light"
            href="#"
            id="navbarDropdownMenuLink"
            data-toggle="dropdown"
            aria-haspopup="true"
            aria-expanded="false"
          >
          <%
          	String username = (String)request.getSession().getAttribute("username");
          	if(username != null){
          		out.println("<i class='far fa-user'>" + username + "</i></a>");
          		out.println("<div class='dropdown-menu' aria-labelledby='navbarDropdownMenuLink'>"
          	            +"<a class='dropdown-item' href='LogoutServlet'>Log out</a>"
          	            +"<a class='dropdown-item' href='viewSchedule.jsp'>View Schedule</a>"
          	            +"<a class='dropdown-item' href='viewOrder.jsp'>View Order</a>"
          	           	+"<a class='dropdown-item' href='UserProfile?method=view'>View Profile</a>"
          	          	+"</div>");
          	}else{
          		out.println("<i class='far fa-user'> Account</i></a>");
          		out.println("<div class='dropdown-menu' aria-labelledby='navbarDropdownMenuLink'>"
          	            +"<a class='dropdown-item' href='login.jsp'>Sign in</a>"
          	            +"<a class='dropdown-item' href='register.jsp'>Create account</a>"
          	            +"<a class='dropdown-item' href='admin.jsp'>Admin</a>"
          	          	+"</div>");
          	}
          %>
        </li>
        <li class="nav-item ml-3">
          <a class="nav-link text-light" href="cart.jsp" id="cart"
            ><i class="fas fa-shopping-cart" ></i
          ></a>
        </li>
      </ul>
    </header>