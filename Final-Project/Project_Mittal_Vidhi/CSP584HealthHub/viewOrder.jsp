<%@page import="servlet.Utilities"%>
<%@page import="service.OrderService, java.util.*, bean.Order"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="header.jsp"%>
    
    <!-- Navigation -->
    <%@ include file="leftNavBar.jsp" %>

    <!-- Page Content -->
	<div class="container mt-4">
	<h4 class="mb-4">My Orders</h4>
      <div class="table-responsive">
        <table class="table table-striped table-sm">
          <thead>
            <tr>
              <th>Order #</th>
              <th>Ordered placed</th>
              <th>Total</th>
              <th>Status</th>
              <th></th>
              <th></th>
            </tr>
          </thead>
          <%
          	Utilities utilities = new Utilities(request, response);
          	List<Order> orders = new OrderService().getOrderByUser(utilities.username());
         	pageContext.setAttribute("orders", orders);
          %>
          <tbody>
          	<c:forEach items="${orders}" var="order">
	            <tr>
	              <td class="align-middle">${order.orderId}</td>
	              <td class="align-middle">${order.purchaseDate}</td>
	              <td class="align-middle">$${order.totalPrice}</td>
	              <td class="align-middle">${order.status}</td>
	              <td class="align-middle"><a href="OrderProcess?method=delete&id=${order.orderId}" class="text-danger">Cancel Order</a></td>
	              <td class="align-middle"><a href="OrderProcess?method=detail&id=${order.orderId}" class="text-danger">View Detail</a></td>
	            </tr>
           	</c:forEach>
          </tbody>
        </table>
      </div>
    </div>
    <!-- /.container -->
 <%@include file="footer.jsp" %>