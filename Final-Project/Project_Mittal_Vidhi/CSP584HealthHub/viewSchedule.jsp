<%@page import="dao.DoctorDaoImpl"%>
<%@page import="servlet.Utilities"%>
<%@page import="service.OrderService, java.util.*, bean.Order, bean.Appointment, bean.Doctor"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="header.jsp"%>
    
    <!-- Navigation -->
    <%@ include file="leftNavBar.jsp" %>

    <!-- Page Content -->
	<div class="container mt-4">
	<h4 class="mb-4">My Appointment</h4>
      <div class="table-responsive">
      	<%
	          	Utilities utilities = new Utilities(request, response);
	          	Map<Appointment, Doctor> info = new DoctorDaoImpl().getAppointmentByUser(utilities.username());
	         	pageContext.setAttribute("infoSet", info.entrySet());
	          %>
	        <c:if test="${infoSet.size() == 0}">
	        		you don't have any appointments
	        	
	        </c:if>
        <table class="table table-striped table-sm">
          <thead>
            <tr>
              <th>Date</th>
              <th>Name</th>
              <th>Email</th>
              <th>address</th>
              <th>Phone</th>
              <th>Doctor Name</th>
              <th>Doctor Address</th>
              <th>Doctor Phone</th>
            </tr>
          </thead>
          
          <tbody>
	          
          	<c:forEach items="${infoSet}" var="info">
	            <tr>
	              <td class="align-middle">${info.getKey().date}</td>
	              <td class="align-middle">${info.getKey().fullName}</td>
	              <td class="align-middle">${info.getKey().email}</td>
	              <td class="align-middle">${info.getKey().fullAddress}</td>
	              <td class="align-middle">${info.getKey().phone}</td>
	              <td class="align-middle">${info.getValue().name}</td>
	              <td class="align-middle">${info.getValue().address}</td>
	              <td class="align-middle">${info.getValue().phoneNum}</td>
	              <td class="align-middle"><a href="AppointmentServlet?method=delete&id=${info.getKey().id}" class="text-danger">Cancel Appointment</a></td>
	            </tr>
           	</c:forEach>
          </tbody>
        </table>
      </div>
    </div>
    <!-- /.container -->
 <%@include file="footer.jsp" %>