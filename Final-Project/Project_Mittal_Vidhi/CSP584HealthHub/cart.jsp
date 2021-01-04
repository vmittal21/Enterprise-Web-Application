<%@page
	import="servlet.Utilities, util.IOUtil, service.ProductCrudService"%>
<%@page import="java.util.*, bean.OrderItem, bean.Product"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!--header-->
<%@include file="header.jsp"%>>

<!-- Navigation -->
<%@ include file="leftNavBar.jsp"%>

<!-- Page Content -->
<div class="container mt-4">
	<div class="row">
		<form action="checkout.jsp">
			<table class="table table-hover col-9 mx-auto">
				<thead>
					<tr>
						<th style="border-top: 1px solid #fff"><h4>Shopping Cart</h4></th>
					</tr>
					<tr>
						<th></th>
						<th>Quantity</th>
						<th></th>
						<th class="text-center">Price</th>
						<th class="text-center">Total</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${sessionScope.orderMap.values()}"
						var="orderItem">
						<tr>
							<td class="col-sm-8 col-md-6">
								<div class="media">
									<a class="thumbnail pull-left" href="#"> <img
										class="media-object" src="image/products/${orderItem.image}"
										style="width: 72px; height: 72px" alt="" />
									</a>
									<div class="media-body ml-2">
										<p class="media-heading font-weight-bold">
											<a href="<%=path%>/viewProductDetail">${orderItem.name}</a>
										</p>
										<p class="media-heading font-weight-bold">
											by <a href="#">${orderItem.brand}</a>
										</p>
										<span>Status: </span>
										<c:if test="${orderItem.inventory>0}">
											<strong>In Stock</strong>
										</c:if>
										<c:if test="${orderItem.inventory==0}">
											<strong>Re-Stock in two weeks</strong>
										</c:if>
									</div>
								</div>
							</td>
							<!-- Quantity -->

							<td class="col-sm-1 col-md-1" style="text-align: center;"><input
								type="text" class="form-control" id="quantity" name="number"
								placeholder="1" value="${orderItem.quantity}" /></td>
							<!-- Delete button -->
							<td class="col-sm-1 col-md-1">
								<button type="button" class="btn btn-link" name="del">Delete</button>
							</td>
							<!-- Price -->
							<td class="col-sm-1 col-md-1 text-center">
								<p class="btn font-weight-bold">$${orderItem.price}</p>
							</td>
							<!-- Total price -->
							<td class="col-sm-1 col-md-1 text-center">
								<p class="btn font-weight-bold">${orderItem.totalPrice}</p>
							</td>
						</tr>
					</c:forEach>

					<tr>
						<td> </td>
						<td> </td>
						<td> </td>
						<td class="col-sm-1 col-md-1 text-center">
							<h4>Subtotal</h4>
						</td>
						<!-- Subtotal -->
						<td class="col-sm-1 col-md-1 text-center">
							<h4><%=session.getAttribute("totalPrice")%></h4>
						</td>
					</tr>
					<tr>
						<td> </td>
						<td> </td>
						<td> </td>
						<td>
							<button type="button" class="btn btn-default"
								onclick="javascript:history.back(1);">Continue Shopping
							</button>

						</td>
						<td>
							<button type="submit" class="btn btn-checkout">Checkout</button>
						</td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>

	<div class="row justify-content-center my-4">
		<div class="col-9 h4">Recommended products for you</div>
	</div>
	<div class="row justify-content-center">
		<%
		    // Recommender system logic
		    IOUtil ioutilities = new IOUtil();
		    Utilities utilities = new Utilities(request, response);
		    HashMap<String, String> prodRecmMap = new HashMap<String, String>();
		    prodRecmMap = ioutilities.readOutputFile();
		    ArrayList<String> productsList = new ArrayList<String>();
		    for (String user : prodRecmMap.keySet()) {
		        if (user.equals(utilities.username())) {
		            String products = prodRecmMap.get(user);
		            products = products.replace("[", "");
		            products = products.replace("]", "");
		            products = products.replace("\"", " ");
		            productsList = new ArrayList<String>(Arrays.asList(products.split(",")));
		            pageContext.setAttribute("productsList", productsList);
		        }
		    }
		%>
		<c:forEach items="${productsList}" var="prod">
			<%
			    String prod = (String) pageContext.getAttribute("prod");
			        prod = prod.replace("'", "");
			        Product prodObj = new Product();
			        prodObj = new ProductCrudService().getProductObjById(prod.trim());
			        System.out.println(prod.trim());
			        System.out.println(prodObj.getName());
			%>
			<div class="col-md-3 mb-4">
				<div class="card">
					<img class="card-img-top p-2"
						src="image/products/<%=prodObj.getImage()%>" style="height: 200px"
						alt="" />
					<div class="ml-4 my-1">
						<!-- product name -->
						<p class="font-weight-bold"><%=prodObj.getName()%></p>
						<!-- product price -->
						<p>
							$<%=prodObj.getPrice()%></p>
					</div>
					<form action="<%=path%>/Cart" method="post">
						<input type="hidden" name="productName"
							value="<%=prodObj.getName()%>"> <input type="hidden"
							name="productId" value="<%=prodObj.getId()%>"> <input
							type="hidden" name="productImage" value="<%=prodObj.getImage()%>">
						<input type="hidden" name="productPrice"
							value="<%=prodObj.getPrice()%>"> <input type="hidden"
							name="productBrand" value="<%=prodObj.getManufacturer()%>">
						<input type="hidden" name="productInventory"
							value="<%=prodObj.getInventory()%>">
						<button type="submit" class="btn btn-block w-75 mb-2 mx-auto">Add
							to Cart</button>
					</form>
					<form action="writeReview.jsp">
						<input type="hidden" name="productName"
							value="<%=prodObj.getName()%>" /> <input type="hidden"
							name="productId" value="<%=prodObj.getId()%>"> <input
							type="hidden" name="productImage" value="<%=prodObj.getImage()%>">
						<button type="submit" class="btn btn-block w-75 mb-2 mx-auto">Write
							Review</button>
					</form>
					<form action="viewReview.jsp">
						<input type="hidden" name="productName"
							value="<%=prodObj.getName()%>" />
						<button type="submit" class="btn btn-block w-75 mb-2 mx-auto">View
							Review</button>
					</form>
				</div>
			</div>
		</c:forEach>
	</div>
</div>
<!-- /.container -->
<!-- /.footer -->
<%@include file="footer.jsp"%>
