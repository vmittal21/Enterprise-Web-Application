<%@page import="bean.Bestseller"%>
<%@ page import="service.ProductCrudService, java.util.*, bean.Product"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="header.jsp"%>

<!-- Navigation -->
<!-->
<nav
	class="navbar justify-content-center navbar-light fixed-top fixed-top-2"
	style="box-shadow: none">

	<ul class="nav ml-auto mr-5">
		<li class="nav-item px-3"><a class="btn btn-outline-success mt-2"
			href="ProductCrudProcess?action=showNewForm"><i
				class="fas fa-list-ul pr-2" style="color: green"></i>Add Product</a>
		</li>
		<li class="nav-item px-3"><a class="nav-link dropdown-toggle"
            href="#"
            id="navbarDropdownMenuLink"
            data-toggle="dropdown"
            aria-haspopup="true"
            aria-expanded="false"><i
				class="fas fa-list-ul pr-2" style="color: green"></i>Trending</a>
			<div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
            <a class="dropdown-item" href="DataAnalysis?method=trending&action=bestseller">Best Seller</a>
            <a class="dropdown-item" href="DataAnalysis?method=trending&action=bestrating">Best Rating</a>
          </div>
		</li>		
		<li class="nav-item px-3"><a class="btn btn-outline-success mt-2"
			href="DataAnalysis?action=analytics"><i
				class="fas fa-list-ul pr-2" style="color: green"></i>Data Analytics</a>
		</li>
	</ul>
</nav>
<!-->
<script type='text/javascript' src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript" src="js/inventory.js"></script>
<nav
      class="navbar justify-content-center navbar-light fixed-top fixed-top-2"
    >
      <ul class="nav">
        <li class="nav-item px-3">
          <a class="nav-link active" href="ProductCrudProcess?action=showNewForm"
            ><i class="fas fa-list-ul pr-2" style="color: green"></i> Add Product</a
          >
        </li>
        <li class="nav-item px-3">
          <a class="nav-link active" href="#" id="viewInventory" onclick="chart()"
            ><i class="fas fa-list-ul pr-2" style="color: green"></i> Inventory</a
          >
        </li>
        <li class="nav-item px-3">
          <a class="nav-link active" href="analytics.jsp"
            ><i class="fas fa-list-ul pr-2" style="color: green"></i>Data Analytics</a
          >
        </li>
        <li class="nav-item dropdown px-3">
          <a
            class="nav-link dropdown-toggle"
            href="#"
            id="navbarDropdownMenuLink"
            data-toggle="dropdown"
            aria-haspopup="true"
            aria-expanded="false"
            ><i class="fas fa-list-ul pr-2" style="color: green"></i>
            Trending
          </a>
          <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
          	<a class="dropdown-item" href="DataAnalysis?method=trending&action=bestseller">Best Seller</a>
            <a class="dropdown-item" href="DataAnalysis?method=trending&action=bestrating">Best Rating</a>
          </div>
        </li>
      </ul>
    </nav>

<!-- Page Content -->
<div class="container mt-4">
	<div id="chart_div" style="display:none">
	</div>
	<div class="table-responsive" id="table">
		<%
			    List<Product> products = new ProductCrudService().getAllProducts();
			    pageContext.setAttribute("products", products);
			    List<Bestseller> bestsellers = (List<Bestseller>)request.getAttribute("bestsellers");
			    List<Bestseller> bestratings = (List<Bestseller>)request.getAttribute("bestratings");
			%>
		<table class="table table-striped table-sm">
			<thead>
				<tr>
					<c:if test="${bestsellers != null}" >
						<th>Num of Sold</th>
					</c:if>
					<c:if test="${bestratings != null}" >
						<th>Rating</th>
					</c:if>
					<th>Id</th>
					<th>Name</th>
					<th>Image</th>
					<th>Price</th>
					<th>Manufacturer</th>
					<th>Condition</th>
					<th>Discount</th>
					<th>Category</th>
					<th>Inventory</th>
					<th>Action</th>
				</tr>
			</thead>
			
			<tbody>
				<c:if test="${bestsellers != null}" >
					<c:forEach items="${bestsellers}" var="bestseller">
						<tr>
							<td class="align-middle">${bestseller.numSold}</td>
							<td class="align-middle">${bestseller.product.id}</td>
							<td class="align-middle">${bestseller.product.name}</td>
							<td class="align-middle"><img
								src="image/products/${bestseller.product.image}" style="width: 60px" /></td>
							<td class="align-middle">${bestseller.product.price}</td>
							<td class="align-middle">${bestseller.product.manufacturer}</td>
							<td class="align-middle">${bestseller.product.condition}</td>
							<td class="align-middle">${bestseller.product.discount}</td>
							<td class="align-middle">${bestseller.product.catagory}</td>
							<td class="align-middle">${bestseller.product.inventory}</td>
							<td class="align-middle"><a class="mr-3"
								href="ProductCrudProcess?action=showEditForm&id=${product.id}"><i
									class="fas fa-edit" style="color: green"></i></a> <a
								href="ProductCrudProcess?action=deleteProduct&id=${product.id}"><i
									class="fas fa-trash-alt" style="color: red"></i></a></td>
						</tr>
					</c:forEach>
				</c:if>
				<c:if test="${bestratings != null}" >
					<c:forEach items="${bestratings}" var="bestseller">
						<tr>
								<td class="align-middle">${bestseller.rating}</td>
								<td class="align-middle">${bestseller.product.id}</td>
								<td class="align-middle">${bestseller.product.name}</td>
								<td class="align-middle"><img
									src="image/products/${bestseller.product.image}" style="width: 60px" /></td>
								<td class="align-middle">${bestseller.product.price}</td>
								<td class="align-middle">${bestseller.product.manufacturer}</td>
								<td class="align-middle">${bestseller.product.condition}</td>
								<td class="align-middle">${bestseller.product.discount}</td>
								<td class="align-middle">${bestseller.product.catagory}</td>
								<td class="align-middle">${bestseller.product.inventory}</td>
								<td class="align-middle"><a class="mr-3"
									href="ProductCrudProcess?action=showEditForm&id=${product.id}"><i
										class="fas fa-edit" style="color: green"></i></a> <a
									href="ProductCrudProcess?action=deleteProduct&id=${product.id}"><i
										class="fas fa-trash-alt" style="color: red"></i></a></td>
							</tr>
						</c:forEach>
				</c:if>
				<c:if test="${bestsellers == null && bestratings == null}">
					<c:forEach items="${products}" var="product">
						<tr>
							<td class="align-middle">${product.id}</td>
							<td class="align-middle">${product.name}</td>
							<td class="align-middle"><img
								src="image/products/${product.image}" style="width: 60px" /></td>
							<td class="align-middle">${product.price}</td>
							<td class="align-middle">${product.manufacturer}</td>
							<td class="align-middle">${product.condition}</td>
							<td class="align-middle">${product.discount}</td>
							<td class="align-middle">${product.catagory}</td>
							<td class="align-middle">${product.inventory}</td>
							<td class="align-middle"><a class="mr-3"
								href="ProductCrudProcess?action=showEditForm&id=${product.id}"><i
									class="fas fa-edit" style="color: green"></i></a> <a
								href="ProductCrudProcess?action=deleteProduct&id=${product.id}"><i
									class="fas fa-trash-alt" style="color: red"></i></a></td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>
		</table>
	</div>
</div>
<!-- /.container -->
<%@include file="footer.jsp"%>
