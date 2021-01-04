<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@include file="header.jsp"%>
<%@include file="leftNavBar.jsp"%>
<!-- content-->
<div class="container my-4" id="custom-border">
	<div class="row">

		<div class="col-3" id="custom-border">
			<div class="locations-filters">
				<div class="wrap">

					<h2 class="filters-form-title">Select a service to find a
						provider near you:</h2>

					<div class="filters-form-wrapper">


						<div class="filters-form-filters">
							<div class="filters-form-checkbox">
								<input type="checkbox" id="chk_home_health" name="parameters"
									data-service-id="23" value="homeHealth"> <label
									for="chk_home_health">Home Health</label>
							</div>
							<div class="filters-form-checkbox">
								<input type="checkbox" id="chk_hospice" name="parameters"
									data-service-id="26" value="hospiceCare"> <label
									for="chk_hospice">Hospice Care</label>
							</div>
							<div class="filters-form-checkbox">
								<input type="checkbox" id="chk_medical_equipment"
									name="parameters" data-service-id="25" value="homeMedical">
								<label for="chk_medical_equipment">Home Medical
									Equipment</label>
							</div>
						</div>


						<div class="filters-form-actions">
							<div class="form-filters">
								<div class="js-form-type-text">
									<label for="txt_zipcode">Zipcode</label> <input type="text"
										id="txt_zipcode" name="zipcode" class="form-text">
								</div>
								<div class="js-form-type-select">
									<label for="sel_radius">Distance</label> <select
										id="sel_radius" name="rad" class="form-select">
										<option value="5">5 miles</option>
										<option value="10">10 miles</option>
										<option value="25" selected="selected">25 miles</option>
										<option value="50">50 miles</option>
									</select>
								</div>
							</div>
							<div class="actions">
								<input type="submit" class="form-submit button yellow"
									value="Go" id="edit-actions-submit">
							</div>
						</div>


					</div>
				</div>
			</div>
		</div>

		<div class="col-9 mb-4" id="custom-border">
			<!--maps-->
			<div id="map"></div>

		</div>
	</div>
</div>
<script type='text/javascript' defer
	src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBHKJVOYru4oeEbXSmhM430eSEWbYG0ttc&v=weekly&libraries=visualization"></script>
<script type="text/javascript" src="js/medical.js"></script>

<!-- Google MAPS-->
<%@include file="footer.jsp"%>