<%@page import="servlet.Utilities"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 
<%@ include file="header.jsp"%>

<%@ include file="leftNavBar.jsp" %>
    <!-- Page Content -->
    <div class="container mt-4">
      <div class="text-center mb-2"><i class="fas fa-shopping-bag"></i></div>
      <div class="h3 text-center mb-4">Thanks for your order!</div>
      <p class="text-center pb-4" style="border-bottom: 1px solid #b8c1ec">
        We're processing your order now, here are the details
      </p>
      <div class="p-3 mb-4">
        <div class="row">
          <p class="col-6 font-weight-bold text-right">
            Confirmation email will be sent to
          </p>
	          <% 
	          	Utilities utilities = new Utilities(request, response);
	          	String email = utilities.username();
	          	String subtotal = (String)request.getAttribute("subtotal");
	    		String tax = (String)request.getAttribute("tax");
	    		String total = (String)request.getAttribute("total");
	    		String orderId = (String)request.getAttribute("orderId");
	    		String purchaseDate = (String)request.getAttribute("purchaseDate");
	    		String deliveryDate = (String)request.getAttribute("deliveryDate");
	    		session.removeAttribute("orderMap");
	    		session.removeAttribute("totalPrice");
	          %>
          	  <%=email %>
          <p class="col-6"></p>
        </div>
        <div class="row">
          <p class="col-6 font-weight-bold text-right">Order number</p>
          <p class="col-6"><%=orderId %></p>
        </div>
        <div class="row">
          <p class="col-6 font-weight-bold text-right">Order date</p>
          <p class="col-6"><%=purchaseDate %></p>
        </div>
        <div class="row">
          <p class="col-6 font-weight-bold text-right">
            Estimated delivery date
          </p>
          <p class="col-6"><%=deliveryDate %></p>
        </div>
        <div class="row">
          <p class="col-6 font-weight-bold text-right">Order details</p>
          <div class="col-6">
            <div class="row">
              <p class="col-4">Subtotal before tax</p>
              <p class="col-2 text-right"><%=subtotal %></p>
            </div>
            <div class="row">
              <p class="col-4">Tax</p>
              <p class="col-2 text-right"><%=tax %></p>
            </div>
            <div class="row">
              <p class="col-4">Total</p>
              <p class="col-2 text-right"><%=total %></p>
            </div>
          </div>
        </div>
      </div>
    </div>
    <!-- /.container -->
 <%@include file="footer.jsp" %>
    