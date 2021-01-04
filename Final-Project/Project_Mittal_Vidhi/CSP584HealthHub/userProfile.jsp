<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*, bean.User"%>
<%@ include file="header.jsp"%>

<!-- Navigation -->
<%@ include file="leftNavBar.jsp"%>
<%
	User info = (User)(request.getAttribute("profile"));
%>
<!-- Page Content -->
<script>
	function edit(){
		$("input[type='text']").removeAttr("readonly");
		$("#submit").removeAttr("style");
	}
</script>
<div class="container mt-4">
	<div class="h3 text-center mb-2">Profile Information</div>
	<div class="text-center">
		<a href="#" class="btn btn-link mx-auto" onclick="edit()">Edit Profile</a>
	</div>
	<form action="UserProfile" method="post">
		<div class="p-3 mb-4">
			<div class="row">
				<input
	              type="text"
	              class="form-control form-control-lg my-2"
	              name="name"
	              value="<%=info.getFirstName() + " " + info.getLastName() %>"
	              placeholder="Name"
	              readonly
	              style="text-align:center;"
	            />
			</div>
			<div class="row">
				<input
	              type="text"
	              class="form-control form-control-lg my-2"
	              name="gender"
	              value="<%=info.getGender()==null?"":info.getAddress() %>"
	              placeholder="Gender"
	              readonly
	              style="text-align:center;"
	            />
			</div>
			<div class="row">
				<input
	              type="text"
	              class="form-control form-control-lg my-2"
	              name="birth"
	              value="<%=info.getBirth()==null?"":info.getBirth() %>"
	              placeholder="Date of Birth"
	              readonly
	              style="text-align:center;"
	            />
			</div>
			<div class="row">
				<input
	              type="text"
	              class="form-control form-control-lg my-2"
	              name="age"
	              value="<%=info.getAge()==null?"":info.getAge() %>"
	              placeholder="Age"
	              readonly
	              style="text-align:center;"
	            />
			</div>
			<div class="row">
				<input
	              type="text"
	              class="form-control form-control-lg my-2"
	              name="job"
	              value="<%=info.getJob()==null?"":info.getJob() %>"
	              placeholder="Occupation"
	              readonly
	              style="text-align:center;"
	            />
			</div>
			<div class="row">
				<input
	              type="text"
	              class="form-control form-control-lg my-2"
	              name="address"
	              value="<%=info.getAddress()==null?"":info.getAddress() %>"
	              placeholder="Address"
	              readonly
	              style="text-align:center;"
	            />
			</div>
			<div class="row">
				<input
	              type="text"
	              class="form-control form-control-lg my-2"
	              name="city"
	              value="<%=info.getCity()==null?"":info.getCity() %>"
	              placeholder="City"
	              readonly
	              style="text-align:center;"
	            />		
	       	</div>
			<div class="row">
				<input
	              type="text"
	              class="form-control form-control-lg my-2"
	              name="state"
	              value="<%=info.getState()==null?"":info.getState() %>"
	              placeholder="State"
	              readonly
	              style="text-align:center;"
	            />
			</div>
			<div class="row">
				<input
	              type="text"
	              class="form-control form-control-lg my-2"
	              name="zipcode"
	              value="<%=info.getZipcode()==null?"":info.getZipcode() %>"
	              readonly
	              style="text-align:center;"
	              placeholder="Zipcode"
	            />
			</div>
			<div class="row">
				<input
	              type="text"
	              class="form-control form-control-lg my-2"
	              name="email"
	              value="<%=info.getEmail()==null?"":info.getEmail()%>"
	              readonly
	              style="text-align:center;"
	              placeholder="Email"
	            />
			</div>
			<button type="submit" id="submit" class="form-btn btn btn-block mb-5" style="display:none">
	          Update Profile
	        </button>
	       	
		</div>
		
      </form>
</div>

<!-- /.container -->
<%@include file="footer.jsp"%>