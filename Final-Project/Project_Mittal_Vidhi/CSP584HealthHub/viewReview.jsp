<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="service.MongoDBService, java.util.*, bean.Review"%>
<%@ include file="header.jsp"%>
  <%@ include file="leftNavBar.jsp" %>
  	<%
		MongoDBService mongoDBService = new MongoDBService();
		List<Review> reviews =  mongoDBService.selectReviewByProduct(request.getParameter("productName"));
		pageContext.setAttribute("reviews", reviews);
	%>
    <!-- Page Content -->
    <div class="container mt-4">
      <h4 class="mb-4">View Reviews</h4>
      <c:forEach items="${reviews}" var="review">
      <!-- review 01 -->
	      <div class="rounded p-3 mb-4" style="border: 1px solid #b8c1ec">
	        <div class="row">
	          <!-- customer name -->
	          <div class="col-4">${review.userName}</div>
	        </div>
	        <div class="row">
	          <div class="col-4">
	            <span>Overall Rating: </span>
	            <!-- rating point -->
	            <span>${review.reviewRating}</span>
	          </div>
	        </div>
	        <div class="row">
	          <!-- review headline -->
	          <div class="col-8 font-weight-bold">${review.headline}</div>
	        </div>
	        <div class="row">
	          <!-- review content -->
	          <div class="col-8">
	            ${review.reviewText}
	          </div>
	        </div>
	      </div>
	   </c:forEach>
    </div>
    <!-- /.container -->
 <%@include file="footer.jsp" %>
    