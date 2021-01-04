<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Login</title>
    <link
      rel="stylesheet"
      href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
      integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
      crossorigin="anonymous"
    />
    <!-- Custom styles for this template -->
    <link href="css/style.css" rel="stylesheet" />
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
    <script
      src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
      integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
      crossorigin="anonymous"
    ></script>
  </head>
  <body>
    <!-- Header-->
    <header class="navbar navbar-light fixed-top">
      <a class="navbar-brand text-white" href="index.jsp">Health Hub</a>
    </header>
    <div class="login-form-container">
      <h4 class="text-center w-75 mx-auto mb-4">Sign in to HealthHub</h4>
      <% 
        	String login_msg = (String)request.getAttribute("login_msg");
      		String register_msg = (String)request.getAttribute("register_msg");
        	if(login_msg != null){
        		out.print("<h4 class='text-center w-75 mx-auto mb-4' style='color:red'>"+login_msg+"</h4>");
        	}
        	if(register_msg != null){
        		out.print("<h4 class='text-center w-75 mx-auto mb-4' style='color:red'>" + register_msg +  "</h4>");
        	}
        %>
      <form action="<%=request.getContextPath() %>/LoginServlet" method="post">
        <div class="form-group">
          <input type="hidden" name="usertype" value="customer"/>
          <input
            type="email"
            class="form-control form-control-lg mb-4 w-75 mx-auto"
            id="email-address"
            name="emailAddress"
            placeholder="Email Address"
            required
          />
        </div>
        
        <div class="form-group">
          <input
            type="password"
            class="form-control form-control-lg mb-4 w-75 mx-auto"
            id="password"
            name="password"
            placeholder="Password"
            required
          />
        </div>
        <button type="submit" class="form-btn btn btn-block w-75 mx-auto mb-5">
          Sign in
        </button>
      </form>
    </div>
    <!-- Footer -->
    <footer class="py-4 fixed-bottom">
      <div class="container">
        <p class="m-0 text-center text-white">
          Copyright &copy; Health Hub 2020
        </p>
      </div>
      <!-- /.container -->
    </footer>
  </body>
</html>
