<%@page
	import="util.IOUtil, java.util.*, bean.Product, service.ProductCrudService"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="header.jsp"%>

<!-- Navigation -->
<%@ include file="leftNavBar.jsp"%>

<!-- Page Content -->
<div class="container mt-4">
	<!-- /.col-lg-3 -->
	<div class="col-lg-9 mx-auto">
		<h2>Top Health News</h2>
		<div id="carouselExampleIndicators" class="carousel slide my-4"
			data-ride="carousel">
			<ol class="carousel-indicators">
				<li data-target="#carouselExampleIndicators" data-slide-to="0"
					class="active"></li>
				<li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
				<li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
				<li data-target="#carouselExampleIndicators" data-slide-to="3"></li>
				<li data-target="#carouselExampleIndicators" data-slide-to="4"></li>
			</ol>
			<div class="carousel-inner" role="listbox">
				<div class="carousel-item active">
					<a class="h4" href=""></a> <img class="d-block img-fluid"
						src="http://placehold.it/900x350" alt="First slide"
						style="width: 900px; height: 350px" />
				</div>
				<div class="carousel-item">
					<a class="h4" href=""></a> <img class="d-block img-fluid"
						src="http://placehold.it/900x350" alt="Second slide"
						style="width: 900px; height: 350px" />
				</div>
				<div class="carousel-item">
					<a class="h4" href=""></a> <img class="d-block img-fluid"
						src="http://placehold.it/900x350" alt="Third slide"
						style="width: 900px; height: 350px" />
				</div>
				<div class="carousel-item">
					<a class="h4" href=""></a> <img class="d-block img-fluid"
						src="http://placehold.it/900x350" alt="Third slide"
						style="width: 900px; height: 350px" />
				</div>
				<div class="carousel-item">
					<a class="h4" href=""></a> <img class="d-block img-fluid"
						src="http://placehold.it/900x350" alt="Third slide"
						style="width: 900px; height: 350px" />
				</div>
			</div>
			<a class="carousel-control-prev" href="#carouselExampleIndicators"
				role="button" data-slide="prev"> <span
				class="carousel-control-prev-icon" aria-hidden="true"></span> <span
				class="sr-only">Previous</span>
			</a> <a class="carousel-control-next" href="#carouselExampleIndicators"
				role="button" data-slide="next"> <span
				class="carousel-control-next-icon" aria-hidden="true"></span> <span
				class="sr-only">Next</span>
			</a>
		</div>

		<h4 class="my-4">Deal Matches</h4>
		<%
		    IOUtil ioutilities = new IOUtil();
		    ArrayList<String> lineArray = new ArrayList<String>();
		    ArrayList<Product> selectedproducts = new ArrayList<Product>();
		    List<Product> products = new ProductCrudService().getAllProducts();
		    lineArray = ioutilities.readDealMatchesFile();
		    pageContext.setAttribute("lineArray", lineArray);
		    for (Product product : products) {
		        for (String line : lineArray) {
		            if (line.contains(product.getId())) {
		                selectedproducts.add(new ProductCrudService().getProductById(product.getId()));
		            }
		        }

		    }
		    pageContext.setAttribute("selectedproducts", selectedproducts);
		%>
		<c:forEach items="${lineArray}" var="line">
			<h5 class="my-4">${line}</h5>
		</c:forEach>

		<div class="row">
			<c:forEach items="${selectedproducts}" var="product">
				<div class="col-lg-4 col-md-6 mb-4">
					<div class="card">
						<img class="card-img-top p-2"
							src="image/products/${product.image}" style="height: 200px"
							alt="" />
						<div class="ml-4 my-1">
							<!-- product name -->
							<p class="font-weight-bold">${product.name}</p>
							<!-- product price -->
							<p>$${product.price}</p>
						</div>
						<form action="<%=path%>/Cart" method="post">
							<input type="hidden" name="method" value="addToCart"> <input
								type="hidden" name="productName" value="${product.name}">
							<input type="hidden" name="productId" value="${product.id}">
							<input type="hidden" name="productImage" value="${product.image}">
							<input type="hidden" name="productPrice" value="${product.price}">
							<input type="hidden" name="productBrand"
								value="${product.manufacturer}"> <input type="hidden"
								name="productInventory" value="${product.inventory}">
							<button type="submit" class="btn btn-block w-75 mb-2 mx-auto">
								Add to Cart</button>
						</form>
						<form action="writeReview.jsp">
							<input type="hidden" name="productName" value="${product.name}" />
							<input type="hidden" name="productId" value="${product.id}">
							<input type="hidden" name="productImage" value="${product.image}">
							<button type="submit" class="btn btn-block w-75 mb-2 mx-auto">
								Write Review</button>
						</form>
						<form action="viewReview.jsp">
							<input type="hidden" name="productName" value="${product.name}" />
							<button type="submit" class="btn btn-block w-75 mb-2 mx-auto">
								View Review</button>
						</form>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
	<!-- /.col-lg-9 -->
</div>
<!-- /.row -->
</div>
<!-- /.container -->
<%@include file="footer.jsp"%>
