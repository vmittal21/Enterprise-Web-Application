<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ include file="header.jsp"%>
           
<%@ include file="leftNavBar.jsp" %>

    <!-- Page Content -->
    <div class="container mt-5">
      <div class="row">
        <div class="col-lg-3">
          <div class="list-group">
            <a href="ListProduct?type=vitamin" class="list-group-item">Vitamins</a>
            <a href="ListProduct?type=medicine" class="list-group-item">Medicine</a>
            <a href="ListProduct?type=personalcare" class="list-group-item">Personal Care</a>
            <a href="ListProduct?type=homecare" class="list-group-item">Home Care</a>
            <a href="ListProduct?type=nutrition" class="list-group-item">Nutrition</a>
          </div>
        </div>
        <!-- List of products -->
        <div class="col-lg-9">
          <div class="row">
          
          <!-- loop through products -->
           <c:forEach items="${products}" var="product">
	            <!-- product image -->
	            <div class="col-lg-4 col-md-6 mb-4">
	              <div class="card">
	                <img
	                  class="card-img-top p-2"
	                  src="image/products/${product.image}" 
	                  style="height: 200px"
	                  alt=""
	                />
	                <div class="ml-4 my-1">
	                  <!-- product name -->
	                  <p class="font-weight-bold">
	                    ${product.name}
	                  </p>
	                  <!-- product price -->
	                  <p>$${product.price}</p>
	                </div>
	                <form action="<%=path %>/Cart" method="post">
	                	<input type="hidden" name="method" value="addToCart">
	                	<input type="hidden" name="productName" value="${product.name}">
	                	<input type="hidden" name="productId" value="${product.id}">
	               		<input type="hidden" name="productImage" value="${product.image}">
	               		<input type="hidden" name="productPrice" value="${product.price}">
	               		<input type="hidden" name="productBrand" value="${product.manufacturer}">
	               		<input type="hidden" name="productInventory" value="${product.inventory}">
		            	<button type="submit" class="btn btn-block w-75 mb-2 mx-auto">
		                  Add to Cart
		            	</button>
		            </form>
	                <form action="writeReview.jsp">
	               		<input type="hidden" name="productName" value="${product.name}"/>
	               		<input type="hidden" name="productId" value="${product.id}">
	               		<input type="hidden" name="productImage" value="${product.image}">
	               		<input type="hidden" name="productPrice" value="${product.price}">
		                <button type="submit" class="btn btn-block w-75 mb-2 mx-auto">
		                  Write Review
		                </button>
		            </form>
		            <form action="viewReview.jsp">
		            	<input type="hidden" name="productName" value="${product.name}"/>
		                <button type="submit" class="btn btn-block w-75 mb-2 mx-auto">
		                  View Review
		                </button>
		            </form>
	              </div>
	            </div>
			</c:forEach>
           
          <!-- /.row -->
        </div>
        <!-- /.col-lg-9 -->
      </div>
      <!-- /.row -->
    </div>
    <!-- /.container -->
    </div>
   <%@include file="footer.jsp" %>
