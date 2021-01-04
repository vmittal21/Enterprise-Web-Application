<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
 String basePath = request.getContextPath();
%>
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
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <!-- Custom styles for this template -->
    <link href="css/style.css" rel="stylesheet" />
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
      <h4 class="text-center w-75 mx-auto mb-4">Create an HeathHub account</h4>
      <form action="<%=request.getContextPath()%>/RegisterServlet" method="post">
        <div class="form-group">
          <input
            type="text"
            class="form-control form-control-lg mb-4 w-75 mx-auto"
            id="firstname"
            name="firstName"
            placeholder="First Name"
            required
          />
        </div>
        <div class="form-group">
          <input
            type="text"
            class="form-control form-control-lg mb-4 w-75 mx-auto"
            id="last-name"
            name="lastName"
            placeholder="Last Name"
            required
          />
        </div>
        <div class="form-group">
          <input
            type="email"
            class="form-control form-control-lg mb-4 w-75 mx-auto"
            id="email-address"
            name="emailAddress"
            placeholder="Email Address"
          />
          <label id="warning" style="align:center;"></label>
        </div>
        <script type="text/javascript">
        	function validate(){
        		if (document.getElementById('password').value != document.getElementById('repassword').value) {
                    document.getElementById('repassword').focus();              
                    document.getElementById("alert").innerHTML="<font color='red'>two passwords don't match</font>";
                    document.getElementById("submit").disabled = true;
                }else{
                	document.getElementById("alert").innerHTML="";
                	document.getElementById("submit").disabled = false;
                }
        		
                return  true;
        	}
        	$("#email-address").blur(function(){
        		var curValue=$("#email-address").val();
        		$.ajax({
                    url : "/CSP584HealthHub/RegisterServlet?userName=" + curValue,
                    type : "get",
                    success : function(data) {
                       if($.parseJSON(data) == "false"){
                    	   
                    	   document.getElementById("warning").innerHTML = "<font color='red'>User Name has existed</font>";
                    	   document.getElementById("submit").disabled = true;
                       }else{
                    	   document.getElementById("submit").disabled = false;
                    	   document.getElementById("warning").innerHTML = "";
                       }
                       
                    }, error: function(){
                        console.log("error occurred while making ajax call;")
                    }
              });
        	});
        	
        </script>
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
        <div class="form-group">
          <input
            type="password"
            class="form-control form-control-lg mb-4 w-75 mx-auto"
            id="repassword"
            name="repassword"
            placeholder="Confirm Password"
            required
            onblur="validate()"
            />
          <span id="alert"></span>
        </div>
        <button id="submit" type="submit" class="form-btn btn btn-block w-75 mx-auto mb-5">
          Create account
        </button>
        <p class="text-center w-75 mx-auto"><a  href="login.jsp">Already have an account? Sign in now</a></p>
      </form>
    </div>
    <!-- Footer -->
    <footer class="py-4">
      <div class="container">
        <p class="m-0 text-center text-white">
          Copyright &copy; Health Hub 2020
        </p>
      </div>
      <!-- /.container -->
    </footer>
  </body>
</html>
