<%@page import="dao.ProductDaoImpl"%>
<%@page
	import="bean.Bestseller, com.mongodb.client.MongoCursor, org.bson.Document"%>
<%@ page import="service.ProductCrudService, java.util.*, bean.Product"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="header.jsp"%>
<nav
	class="navbar justify-content-center navbar-light fixed-top fixed-top-2">
	<ul class="nav">
		<li class="nav-item px-3"><a class="nav-link active"
			href="admin.jsp"><i class="fas fa-list-ul pr-2"
				style="color: green"></i>Home</a></li>
		<li class="nav-item px-3"><a class="nav-link active"
			href="analytics.jsp"><i class="fas fa-list-ul pr-2"
				style="color: green"></i>Data Analytics</a></li>
	</ul>
</nav>
<div class="container mt-4" id="custom-border">
	<div class="row">
		<div class="col-3" id="custom-border">
			<div class="locations-filters">
				<div class="wrap">
					<h2 class="filters-form-title">Data Analytics on Review</h2>

					<div class="filters-form-wrapper">
						<form id="frm_services_filters" class="filters-form" method="post"
							action="DataAnalysis">
							<div class="filters-form-filters">
								<div class="filters-form-checkbox">
									<div>
										<input type="radio" name="priceCompare" data-service-id="23"
											value="GREAT_THAN" /> <label
											for="chk_home_health">Price Greater Than</label>
									</div>
									<div>
										<input type="radio" name="priceCompare" data-service-id="23"
											value="LESS_THAN" /> <label
											for="chk_home_health">Price Less Than</label>
									</div>

								</div>
								<div class="js-form-type-text">
									<label for="txt_zipcode">Price</label> <input type="text"
										id="txt_zipcode" name="price" class="form-text" />
								</div>
							</div>
							<div class="filters-form-filters">
								<div class="filters-form-checkbox">
									<div>
										<input type="radio" name="ratingCompare" data-service-id="23"
											value="GREAT_THAN" /> <label
											for="chk_home_health">Rating Greater Than</label>
									</div>
									<div>
										<input type="radio" name="ratingCompare" data-service-id="23"
											value="LESS_THAN" /> <label
											for="chk_home_health">Rating Less Than</label>
									</div>
								</div>
							</div>
							<div class="js-form-type-text">
								<label for="txt_zipcode">Rating</label> <input type="text"
									id="txt_zipcode" name="rating" class="form-text" />
							</div>
							<div class="filters-form-actions">
								<div class="js-form-type-text">
									<label for="txt_zipcode">Product Name</label>
								</div>
								<%
								    List<Product> products = new ProductDaoImpl().getAll();
								    pageContext.setAttribute("products", products);
								%>

								<select style="width: 100%" name="productName">
									<option value="ALL_PRODUCTS">Select a product</option>
									<c:forEach items="${products}" var="product">
										<option value="${product.name }">${product.name}</option>
									</c:forEach>
								</select>

							</div>
							<div class="filters-form-actions">
								<div class="js-form-type-text">
									<label for="txt_zipcode">Group By</label>
								</div>
								<select style="width: 100%" name="groupBy">
									<option value="default">Default</option>
									<option value="city">City</option>
									<option value="productName">productName</option>
								</select>

							</div>
							<div class="filters-form-actions">
								<div class="js-form-type-text">
									<label for="txt_city">City</label> <input type="text"
										id="txt_city" name="city" class="form-text" />
								</div>
								<div class="js-form-type-text">
									<label for="txt_zipcode">Zipcode</label> <input type="text"
										id="txt_zipcode" name="zipcode" class="form-text" />
								</div>
							</div>
							<div class="actions">
								<input type="submit" class="form-submit button yellow"
									value="Find Data" id="edit-actions-submit" />
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
		<!--display data  -->
		<div class="col-9" id="custom-border">
			<div class="table-responsive" id="table">

				<table class="table table-striped table-sm">
					<%
					    Map<Integer, MongoCursor<Document>> data = (Map<Integer, MongoCursor<Document>>) request
					            .getAttribute("res");
					    if (data == null)
					        return;
					    if (data.containsKey(1)) {
					        out.print("<thead><tr><th>Name</th><th>Count</th></tr></thead><tbody>");
					        MongoCursor<Document> cursor = data.get(1);
					        while (cursor.hasNext()) {
					            Document document = cursor.next();
					            out.print("<tr><td>" + document.getString("value") + "</td><td>" + document.getInteger("count")
					                    + "<td></tr>");
					        }
					        out.print("</tbody>");
					    } else {
					        out.print(
					                "<thead><tr><th>Product Name</th><th>Price</th><th>Rating</th><th>Review</th><th>User</th><th>City</th><th>Zipcode</th></tr></thead><tbody>");
					        MongoCursor<Document> cursor = data.get(2);
					        while (cursor.hasNext()) {
					            Document document = cursor.next();
					            out.print("<tr><td>" + document.getString("productName") + "</td><td>"
					                    + document.getDouble("productPrice") + "</td><td>" + document.getInteger("rating")
					                    + "</td><td>" + document.getString("text") + "</td><td>" + document.getString("userName")
					                    + "</td><td>" + document.getString("city") + "</td><td>" + document.getString("zipcode")
					                    + "</td></tr>");
					        }
					        out.print("</tbody>");
					    }
					%>
				</table>
			</div>
		</div>
	</div>
</div>