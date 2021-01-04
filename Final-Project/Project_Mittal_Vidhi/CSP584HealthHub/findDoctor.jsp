<%@page import="java.util.*, bean.Doctor"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@include file="header.jsp"%>
<%@ include file="leftNavBar.jsp"%>
<div class="container my-4" id="custom-border">
	<div class="row">
		<div class="col-3" id="custom-border">
			<div class="locations-filters">
				<div class="wrap">
					<h2 class="filters-form-title">Select a specialty to find a
						doctor near you:</h2>

					<div class="filters-form-wrapper">
						<form id="frm_services_filters" class="filters-form" method="post"
							action="<%=path%>/DoctorServlet">
							<div class="filters-form-filters">
								<%
								    List<String> specialties = (List<String>) request.getAttribute("specialties");
											pageContext.setAttribute("specialties", specialties);
								%>
								<c:forEach items="${specialties}" var="specialty">
									<div class="filters-form-checkbox">
										<input type="checkbox" name="specialty" data-service-id="23"
											value="${specialty}" /> <label
											for="chk_home_health">${specialty}</label>
									</div>
								</c:forEach>
							</div>

							<div class="filters-form-actions">
								<div class="form-filters">
									<div class="js-form-type-text">
										<label for="txt_state">State</label> <input type="text"
											id="txt_state" name="state" class="form-text" />
									</div>
									<div class="js-form-type-text">
										<label for="txt_city">City</label> <input type="text"
											id="txt_city" name="city" class="form-text" />
									</div>
									<div class="js-form-type-text">
										<label for="txt_zipcode">Zipcode</label> <input type="text"
											id="txt_zipcode" name="zipcode" class="form-text" />
									</div>
								</div>
								<div class="actions">
									<input type="submit" class="form-submit button yellow"
										value="Go" id="edit-actions-submit" />
								</div>
								<div class="row" id="custom-border">
									<div class="col" id="custom-border">
										<div class="location-list" id="custom-border">
											<a id="findDoctor" href="javascript:void(0)">Doctor Near
												Me</a>
										</div>
									</div>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
		<div class="col-9" id="mapBorder" style='display: none;'>
			<!--maps-->
			<div id="map"></div>

		</div>
		<div class="col-9" id="doctorArea">
			<%
			    List<Doctor> doctors = (List<Doctor>) request.getAttribute("doctors");
						if (doctors == null) {
							out.print("<li class='doctor-info' id='custom-border'>"
									+ "<p>Select a Doctor to schedule appoinment</p></li>");
						}
			%>
			<ul>
				<c:forEach items="${doctors}" var="doctor">
					<div style="display: 'none'">
						<form id="myForm" action="appointment.jsp" method="post">
							<input type="hidden" name="doctorId" value="${doctor.id}">
						</form>
					</div>

					<li class="doctor-info" id="custom-border"><a
						href="javascript:appointment()"><h5>Dr. ${doctor.name}</h5></a>
						<p>${doctor.specilization}Doctor</p>
						<p>${doctor.phoneNum }</p>
						<p>${doctor.address},${doctor.city}, ${doctor.state},
							${doctor.state}</p></li>
				</c:forEach>

			</ul>
			<script>
				function appointment() {
					document.getElementById("myForm").submit();
				}
			</script>
		</div>
	</div>
</div>
<!-- Google MAPS-->
<script type='text/javascript' defer
	src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBHKJVOYru4oeEbXSmhM430eSEWbYG0ttc&v=weekly&libraries=visualization"></script>
<script type='text/javascript' src="js/findDoctor.js">
	
</script>
<%@include file="footer.jsp"%>
