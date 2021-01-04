<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@include file="header.jsp" %>
<!-- Page Content -->
    <div class="appointment-form-container">
      <h4>Schedule an Appointment</h4>
      <p>
        After you've sent this request, we'll send you a confirmation email.
      </p>
      <form action="AppointmentServlet" method="post">
        <h5 class="border-bottom pb-2">Patient Information</h5>
        <div class="form-row">
          <div class="form-group col-md-4">
            <!-- auto-complete feature if user has already created an account -->
            <!-- name input -->
            <input
              type="text"
              class="form-control form-control-lg my-2"
              name="firstName"
              placeholder="* First Name"
              required
            />
          </div>
          <div class="form-group col-md-4">
            <input
              type="text"
              class="form-control form-control-lg my-2"
              name="middleName"
              placeholder="Middle Initial"
            />
          </div>
          <div class="form-group col-md-4">
            <input
              type="text"
              class="form-control form-control-lg my-2"
              name="lastName"
              placeholder="* Last Name"
              required
            />
          </div>
        </div>
        <div class="form-row">
          <div class="form-group col-md-6">
            <!-- gender selection-->
            <select class="form-control text-secondary my-2" required name="gender">
              <option disabled selected>* Gender</option>
              <option value="female">Female</option>
              <option value="male">Male</option>
              <option value="other">Other</option>
            </select>
          </div>
          <div class="form-group col-md-6 input-group">
            <!-- date of birth selection -->
            <input
              type="date"
              class="form-control text-secondary my-2"
              placeholder="* Date of Birth"
              name="birth"
              required
            />
          </div>
        </div>
        <div class="form-row">
          <div class="form-group col-md-6">
            <!-- address input-->
            <input
              placeholder="* Address"
              type="text"
              class="form-control form-control-lg my-2"
              name="address"
              required
            />
          </div>
          <div class="form-group col-md-2">
            <input
              placeholder="* City"
              type="text"
              class="form-control form-control-lg my-2"
              name="city"
              required
            />
          </div>
          <div class="form-group col-md-2">
            <select
              name="state"
              class="form-control text-secondary my-2"
              required
            >
              	<option selected>--Please Select A State</option>
                    <option value="AL">AL</option>
					<option value="AK">AK</option>
					<option value="AR">AR</option>	
					<option value="AZ">AZ</option>
					<option value="CA">CA</option>
					<option value="CO">CO</option>
					<option value="CT">CT</option>
					<option value="DC">DC</option>
					<option value="DE">DE</option>
					<option value="FL">FL</option>
					<option value="GA">GA</option>
					<option value="HI">HI</option>
					<option value="IA">IA</option>	
					<option value="ID">ID</option>
					<option value="IL">IL</option>
					<option value="IN">IN</option>
					<option value="KS">KS</option>
					<option value="KY">KY</option>
					<option value="LA">LA</option>
					<option value="MA">MA</option>
					<option value="MD">MD</option>
					<option value="ME">ME</option>
					<option value="MI">MI</option>
					<option value="MN">MN</option>
					<option value="MO">MO</option>	
					<option value="MS">MS</option>
					<option value="MT">MT</option>
					<option value="NC">NC</option>	
					<option value="NE">NE</option>
					<option value="NH">NH</option>
					<option value="NJ">NJ</option>
					<option value="NM">NM</option>			
					<option value="NV">NV</option>
					<option value="NY">NY</option>
					<option value="ND">ND</option>
					<option value="OH">OH</option>
					<option value="OK">OK</option>
					<option value="OR">OR</option>
					<option value="PA">PA</option>
					<option value="RI">RI</option>
					<option value="SC">SC</option>
					<option value="SD">SD</option>
					<option value="TN">TN</option>
					<option value="TX">TX</option>
					<option value="UT">UT</option>
					<option value="VT">VT</option>
					<option value="VA">VA</option>
					<option value="WA">WA</option>
					<option value="WI">WI</option>	
					<option value="WV">WV</option>
					<option value="WY">WY</option>
            </select>
          </div>
          <div class="form-group col-md-2">
            <input
              placeholder="* Zip Code"
              type="text"
              class="form-control form-control-lg my-2"
              name="zipCode"
              required
            />
          </div>
        </div>
        <div class="form-row">
          <div class="form-group col-md-6">
            <input
              type="tel"
              class="form-control form-control-lg my-2"
              placeholder="* Phone"
              name="phone"
              required
            />
          </div>
          <div class="form-group col-md-6">
            <!-- email input -->
            <input
              type="email"
              class="form-control form-control-lg my-2"
              placeholder="* Email"
              name="email"
              required
            />
          </div>
        </div>
        <h5 class="border-bottom pb-2">Appointment Information</h5>
        <div class="form-row">
          <div class="form-group col-md-6 input-group">
         <script>
			$(function(){
				var date_now = new Date();
				var year = date_now.getFullYear();
				var month = date_now.getMonth()+1 < 10 ? "0"+(date_now.getMonth()+1) : (date_now.getMonth()+1);
				var date = date_now.getDate() < 10 ? "0"+date_now.getDate() : date_now.getDate();
				$("#appointmentDate").attr("min",year+"-"+month+"-"+date);
			})
		</script>
            <input
              type="date"
              id="appointmentDate"
              class="form-control form-control-lg text-secondary my-2"
              placeholder="* Appointment Date"
              id="appointmentDate"
              name="appointmentDate"
              required
            />
          </div>
        </div>
        <div class="form-row">
          <div class="col-md-6">
            <select
              name="appointmentTime"
              class="form-control text-secondary my-2"
              required
            >
              <option disabled selected>* Please select a time</option>
              <option value="09:00">09:00</option>
              <option value="10:00">10:00</option>
              <option value="11:00">11:00</option>
              <option value="13:00">13:00</option>
              <option value="14:00">14:00</option>
              <option value="15:00">15:00</option>
            </select>
          </div>
        </div>
        <div class="form-row">
          <div class="col-md-10">
            <textarea
              name="reason"
              class="form-control form-control-lg my-4"
              rows="4"
              placeholder="* Reason for Appointment"
              required
            ></textarea>
          </div>
        </div>
		<input type="hidden" name="doctorId" value="<%=request.getParameter("doctorId")%>">
        <button type="submit" class="form-btn btn btn-block mb-5">
          Make an Appointment
        </button>
      </form>
    </div>
 <%@include file="footer.jsp" %>