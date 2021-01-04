<%@page import="bean.Store"%>
<%@page import="service.StoreService"%>
<%@page import="dao.StoreDaoImpl"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*, java.sql.Timestamp, java.text.SimpleDateFormat" %>
<%@include file="header.jsp"%>
<!-- Page Content -->
    <div class="container mt-4">
      <h4 class="px-4 py-2">Checkout</h4>
      <form action="OrderProcess?method=checkout" method="post">
      <div class="row">
        <div class="col-lg-8 mb-4">
          <div class="card wish-list pb-1">
            <!-- Shipping address Info -->
            <div class="card-body pb-0">
              <h5 class="mb-2">1. Shipping address</h5>
              <div class="form-row">
                <div class="form-group col-md-4">
                  <input
                    type="text"
                    class="form-control form-control-lg"
                    name="firstName"
                    placeholder="* First Name"
                    required
                  />
                </div>
                <div class="form-group col-md-4">
                  <input
                    type="text"
                    class="form-control form-control-lg"
                    name="middleName"
                    placeholder="Middle Initial"
                  />
                </div>
                <div class="form-group col-md-4">
                  <input
                    type="text"
                    class="form-control form-control-lg"
                    name="lastName"
                    placeholder="* Last Name"
                    required
                  />
                </div>
              </div>
              <div class="form-row">
                <div class="form-group col-md-6">
                  <!-- address input-->
                  <input
                    placeholder="* Address"
                    type="text"
                    class="form-control form-control-lg"
                    name="address"
                    required
                  />
                </div>
                <div class="form-group col-md-2">
                  <input
                    placeholder="* City"
                    type="text"
                    class="form-control form-control-lg"
                    name="city"
                    required
                  />
                </div>
                <div class="form-group col-md-2">
                  <select
                    name="state"
                    class="form-control text-secondary"
                    required
                  >
                    <option selected>--Please Select A State</option>
                    <option value="AL">AL</option>
					<option value="AK">AK</option>
					<option value="AR">AR</option>	
					<option value="AZ">AZ</option>
					<option value="CA">CA</option>
					<option value="CO">CO</option>
					<option value="CT">CT</option>
					<option value="DC">DC</option>
					<option value="DE">DE</option>
					<option value="FL">FL</option>
					<option value="GA">GA</option>
					<option value="HI">HI</option>
					<option value="IA">IA</option>	
					<option value="ID">ID</option>
					<option value="IL">IL</option>
					<option value="IN">IN</option>
					<option value="KS">KS</option>
					<option value="KY">KY</option>
					<option value="LA">LA</option>
					<option value="MA">MA</option>
					<option value="MD">MD</option>
					<option value="ME">ME</option>
					<option value="MI">MI</option>
					<option value="MN">MN</option>
					<option value="MO">MO</option>	
					<option value="MS">MS</option>
					<option value="MT">MT</option>
					<option value="NC">NC</option>	
					<option value="NE">NE</option>
					<option value="NH">NH</option>
					<option value="NJ">NJ</option>
					<option value="NM">NM</option>			
					<option value="NV">NV</option>
					<option value="NY">NY</option>
					<option value="ND">ND</option>
					<option value="OH">OH</option>
					<option value="OK">OK</option>
					<option value="OR">OR</option>
					<option value="PA">PA</option>
					<option value="RI">RI</option>
					<option value="SC">SC</option>
					<option value="SD">SD</option>
					<option value="TN">TN</option>
					<option value="TX">TX</option>
					<option value="UT">UT</option>
					<option value="VT">VT</option>
					<option value="VA">VA</option>
					<option value="WA">WA</option>
					<option value="WI">WI</option>	
					<option value="WV">WV</option>
					<option value="WY">WY</option>
                  </select>
                </div>
                <div class="form-group col-md-2">
                  <input
                    placeholder="* Zip Code"
                    type="text"
                    class="form-control form-control-lg"
                    name="zipCode"
                    required
                  />
                </div>
              </div>
            </div>
            <!-- Payment Method Info -->
            <div class="card-body py-0">
              <h5 class="mb-2">2. Payment method</h5>
              <div class="d-block my-3">
                <div class="form-check">
                  <input
                    id="credit"
                    name="paymentMethod"
                    type="radio"
                    checked
                    required
                  />
                  <label class="form-check-label" for="credit"
                    >Credit card</label
                  >
                </div>
                <div class="form-check">
                  <input
                    id="debit"
                    name="paymentMethod"
                    type="radio"
                    required
                  />
                  <label class="form-check-label" for="debit">Debit card</label>
                </div>
                <div class="form-check">
                  <input
                    id="paypal"
                    name="paymentMethod"
                    type="radio"
                    required
                  />
                  <label class="form-check-label" for="paypal">Paypal</label>
                </div>
              </div>
              <div class="row">
                <div class="col-md-6 mb-3">
                  <input
                    type="text"
                    class="form-control form-control-lg"
                    id="cc-name"
                    name="cc-name"
                    placeholder="* Name on card"
                    required
                  />
                  <small class="text-muted"
                    >Full name as displayed on card</small
                  >
                  <div class="invalid-feedback">Name on card is required</div>
                </div>
                <div class="col-md-6 mb-3">
                  <input
                    type="text"
                    class="form-control form-control-lg"
                    id="cc-number"
                    name="cc-number"
                    placeholder="* Credit card number"
                    required
                  />
                  <div class="invalid-feedback">
                    Credit card number is required
                  </div>
                </div>
              </div>
              <div class="row">
                <div class="col-md-3 mb-3">
                  <input
                    type="text"
                    class="form-control form-control-lg"
                    id="cc-expiration"
                    name="cc-expiration"
                    placeholder="* Expiration"
                    required
                  />
                  <div class="invalid-feedback">Expiration date required</div>
                </div>
                <div class="col-md-3 mb-3">
                  <input
                    type="text"
                    class="form-control form-control-lg"
                    id="cc-cvv"
                    name="cc-cvv"
                    placeholder="* CVV"
                    required
                  />
                  <div class="invalid-feedback">Security code required</div>
                </div>
              </div>
            </div>
            <!-- Review items and shipping Info -->
            <div class="card-body pt-0">
              <h5 class="mb-2">3. Review items and shipping</h5>
              <table class="table table-hover col-9 mx-auto">
                <tbody>-
                	<c:forEach items="${sessionScope.orderMap.values()}" var="orderItem">
	                  <!-- Order Item 1 -->
	                  <tr class="row">
	                    <td class="col-sm-3 col-md-3">
	                      <small class="text-success font-weight-bold pr-0">
	                        Expected Delivery Date:
	                      </small>
	                      <!-- Delivery Date -->
	                      <%
	                      	Calendar date = Calendar.getInstance();
		  					date.add(Calendar.DAY_OF_MONTH, 14);
		  					SimpleDateFormat sd = new SimpleDateFormat("MMM.dd",Locale.US);
		  					String deliveryDate = sd.format(date.getTime());
	                      %>
	                      <p class="text-success font-weight-bold"><%=deliveryDate %></p>
	                      <input type="hidden" name="deliveryDate" value="<%=deliveryDate %>">
	                    </td>
	                    <td class="col-sm-5 col-md-5">
	                      <div class="media">
	                        <a class="thumbnail pull-left" href="#">
	                          <img
	                            class="media-object"
	                            src="image/products/${orderItem.image}"
	                            style="width: 72px; height: 72px"
	                          />
	                        </a>
	                        <div class="media-body ml-2">
	                          <!-- Product name -->
	                          <p class="media-heading font-weight-bold m-0">
	                            <a href="#">${orderItem.name}</a>
	                          </p>
	                          <!-- Brand name -->
	                          <small class="media-heading font-weight-bold">
	                            by <a href="#">${orderItem.brand}</a>
	                          </small>
	                          <!-- Price -->
	                          <p class="font-weight-bold text-danger mb-1">$${orderItem.price}</p>
	                          <div class="row mx-auto">
	                            <!-- Quantity -->
	                            <input
	                              type="text"
	                              class="form-control form-control-sm w-25 mr-2"
	                              id="quantity"
	                              name="quantity"
	                              value="${orderItem.quantity}"
	                            />
	                            <!-- Delete button -->
	                            <button
	                              type="button"
	                              class="btn btn-sm btn-link"
	                              style="background-color: #fff"
	                              name="delete"
	                            >
	                              Delete
	                            </button>
	                          </div>
	                        </div>
	                      </div>
	                    </td>
	                    <td class="col-sm-4 col-md-4">
	                      <small class="font-weight-bold pt-0 mt-0">
	                        Choose your delivery option:
	                      </small>
	                      <!-- Delivery Option; Home delivery, Store pickup -->
	                      <div class="form-check">
	                        <input
	                          class="form-check-input"
	                          type="radio"
	                          name="deliveryOption"
	                          id="homedelivery"
	                          value="Home Delivery"
	                          checked
	                        />
	                        <label class="form-check-label" for="homedelivery">
	                          Home Delivery
	                        </label>
	                      </div>
	                      <div class="form-check mb-2">
	                        <input
	                          class="form-check-input"
	                          type="radio"
	                          name="deliveryOption"
	                          id="storepickup"
	                          value="Store Pickup"
	                        />
	                        <label class="form-check-label" for="storepickup">
	                          Store Pickup
	                        </label>
	                      </div>
	                      <!-- Select a store to pick up -->
	                      <!-- Might disable the select dropdown if user choose home delivery -->
	                      <%
	                      	List<Store> stores = StoreService.getAllStores();
	                       	pageContext.setAttribute("stores", stores);
	                      %>
	                      <select
	                        name="storeName"
	                        class="form-control form-control-sm text-secondary w-75"
	                      >
	                        <option selected>Select a store</option>
	                        <c:forEach items="${stores}" var="store">
	                        <option value="${store.id}">${store.toString()}</option>
	                        </c:forEach>
	                      </select>
                    </td>
                  </tr>
                 </c:forEach>
                </tbody>
              </table>
            </div>
          </div>
        </div>

        <div class="col-lg-4">
          <!-- Order Summary -->
          <div class="card mb-4">
            <div class="card-body">
              <h5 class="mb-3">Order Summary</h5>

              <ul class="list-group list-group-flush">
                <!-- Total item amount -->
                <li
                  class="list-group-item d-flex justify-content-between align-items-center border-0 px-0 pb-0"
                >
                  Items
                  <span>$<%=session.getAttribute("totalPrice") %></span>
                </li>
                <!-- Shipping amount -->
                <li
                  class="list-group-item d-flex justify-content-between align-items-center px-0"
                >
                <%
                  	double total = Double.parseDouble((String)session.getAttribute("totalPrice"));
                  	double discount = total*0.2;
                  	total -= discount;
                  	double tax = total*0.1;
                  	double shippingFee = 9.99;
                  %>
                  Shipping & handling
                  <span>$<%=String.format("%.2f", shippingFee) %></span>
                </li>
                <!-- Discount -->
                <li
                  class="list-group-item d-flex justify-content-between align-items-center px-0"
                >
                  Discount Applied
                  <span>$<%=String.format("%.2f", discount) %></span>
                </li>
                <!-- Total amount before tax -->
                <li
                  class="list-group-item d-flex justify-content-between align-items-center px-0"
                >
                  Total before tax
                  <span>$<%=String.format("%.2f", total) %></span>
                  <input type="hidden" name="subtotal" value="<%=String.format("%.2f", total) %>">
                </li>
                <!-- Tax amount -->
                <li
                  class="list-group-item d-flex justify-content-between align-items-center px-0"
                >
                  Estimated tax
                  <span>$<%=String.format("%.2f", tax) %></span>
                  <input type="hidden" name="tax" value="<%=String.format("%.2f", tax) %>">
                </li>
                <!-- Total amount -->
                <li
                  class="list-group-item d-flex justify-content-between align-items-center border-0 px-0 mb-3"
                >
                  <div>
                    <strong class="text-danger">Order Total</strong>
                  </div>
                  <span><strong class="text-danger">$<%=String.format("%.2f", total + shippingFee) %></strong></span>
                  <input type="hidden" name="total" value="<%=String.format("%.2f", total + shippingFee) %>">
                </li>
              </ul>

              <button type="submit" class="btn btn-block form-btn">
                Place your order
              </button>
              <small class="text-muted"
                >After placing your order, we'll send you a confirmation
                email.</small
              >
            </div>
          </div>
        </div>
      </div>
      </form>
    </div>
<%@include file="footer.jsp"%>