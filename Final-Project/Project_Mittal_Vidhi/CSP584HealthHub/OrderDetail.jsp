<%@page import="servlet.Utilities"%>
<%@page import="service.OrderService, java.util.*, bean.OrderItem"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="header.jsp"%>
    <!-- Navigation -->
    <%@ include file="leftNavBar.jsp" %>

    <!-- Page Content -->
	<div class="container mt-4">
	<h4 class="mb-4">Order Details</h4>
      <div class="table-responsive">
        <table class="table table-striped table-sm">
          <thead>
            <tr>
              <th>Item</th>
              <th>Brand</th>
              <th>Total Price</th>
              <th>Quantity</th>
              <th>Purchase Date</th>
            </tr>
          </thead>
          <%
          	List<OrderItem> orderItems = (List<OrderItem>)request.getSession().getAttribute("orderItems");
         	pageContext.setAttribute("orderItems", orderItems);
          %>
          <tbody>
          	<c:forEach items="${orderItems}" var="order">
	            <tr>
	              <td class="align-middle">${order.name}</td>
	              <td class="align-middle">${order.brand}</td>
	              <td class="align-middle">$${order.totalPrice}</td>
	              <td class="align-middle">${order.quantity}</td>
	              <td class="align-middle">${order.purchaseDate}</td>
	            </tr>
           	</c:forEach>
          </tbody>
        </table>
      </div>
    </div>
    <!-- /.container -->
 <%@include file="footer.jsp" %>