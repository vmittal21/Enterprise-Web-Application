<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="header.jsp"%>

<!-- Navigation -->
<%@ include file="leftNavBar.jsp"%>

<!-- Page Content -->
<div class="appointment-form-container">
	<h3 class="pb-2">Update Profile</h3>
	<form>
		<div class="form-row">
			<div class="form-group col-md-4">
				<!-- name input -->
				<input type="text" class="form-control form-control-lg my-2"
					name="firstName" placeholder="* First Name" required />
			</div>
			<div class="form-group col-md-4">
				<input type="text" class="form-control form-control-lg my-2"
					name="middleName" placeholder="Middle Initial" />
			</div>
			<div class="form-group col-md-4">
				<input type="text" class="form-control form-control-lg my-2"
					name="lastName" placeholder="* Last Name" required />
			</div>
		</div>
		<div class="form-row">
			<div class="form-group col-md-6">
				<!-- gender selection-->
				<select class="form-control form-control-lg text-secondary my-2"
					required>
					<option disabled selected>* Gender</option>
					<option value="female">Female</option>
					<option value="male">Male</option>
					<option value="other">Other</option>
				</select>
			</div>
			<div class="form-group col-md-6 input-group">
				<!-- date of birth selection -->
				<input type="date"
					class="form-control form-control-lg text-secondary my-2"
					placeholder="* Date of Birth" required />
			</div>
		</div>
		<div class="form-row">
			<div class="form-group col-md-6">
				<!-- age -->
				<input type="text" class="form-control form-control-lg my-2"
					placeholder="* Age" name="age" required />
			</div>
			<div class="form-group col-md-6">
				<!-- Occupation-->
				<input type="text" class="form-control form-control-lg my-2"
					placeholder="* Occupation" name="occupation" required />
			</div>
		</div>
		<div class="form-row">
			<div class="form-group col-md-6">
				<!-- address input-->
				<input placeholder="* Address" type="text"
					class="form-control form-control-lg my-2" name="address" required />
			</div>
			<div class="form-group col-md-2">
				<input placeholder="* City" type="text"
					class="form-control form-control-lg my-2" name="city" required />
			</div>
			<div class="form-group col-md-2">
				<select name="state"
					class="form-control form-control-lg text-secondary my-2" required>
					<option selected>* State</option>
					<option>...</option>
				</select>
			</div>
			<div class="form-group col-md-2">
				<input placeholder="* Zip Code" type="text"
					class="form-control form-control-lg my-2" name="zipCode" required />
			</div>
		</div>
		<div class="form-row">
			<div class="form-group col-md-6">
				<!-- phone -->
				<input type="tel" class="form-control form-control-lg my-2"
					placeholder="* Phone" name="phone" required />
			</div>
			<div class="form-group col-md-6">
				<!-- email input -->
				<input type="email" class="form-control form-control-lg my-2"
					placeholder="* Email" name="email" required />
			</div>
		</div>
		<button type="submit" class="form-btn btn btn-block mb-5">
			Submit changes</button>
	</form>
</div>
<!-- /.container -->
<%@include file="footer.jsp"%>