<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 
<%@ include file="header.jsp"%>
  <%@ include file="leftNavBar.jsp" %>
    <!-- Page Content -->
    <div class="container mt-4">
      <form action="<%=path%>/SubmitReview" method="post">
	     <div class="row">
	       <table class="table table-hover col-9 mx-auto">
	         <thead>
	           <tr>
	             <th class="border-0">
	               <h4>Create a review</h4>
	             </th>
	           </tr>
	         </thead>
	         <tbody>
	           <tr class="align-items-center">
	             <!-- Selected product image -->
	             <td class="border-top-0">
	               <img src="image/products/<%=request.getParameter("productImage")%>" width="72px" />
	             </td>
	             <!-- Selected product name -->
	             <td class="border-top-0 align-middle">
	             	<input type="hidden" name="productName" value="<%=request.getParameter("productName")%>">
	             	<input type="hidden" name="productPrice" value="<%=request.getParameter("productPrice")%>">
	               <%=request.getParameter("productName")%>
	             </td>
	           </tr>
	           <tr>
	             <td>
	               <p class="font-weight-bold">Overall Rating</p>
	             </td>
	             <td>
	               <select id="condition" name="rating" class="form-control">
	                 <option value="5" selected>5</option>
	                 <option value="4">4</option>
	                 <option value="3">3</option>
	                 <option value="2">2</option>
	                 <option value="1">1</option>
	               </select>
	             </td>
	           </tr>
	           <tr>
	             <td>
	               <p class="font-weight-bold">Add a headline</p>
	             </td>
	             <td>
	               <textarea
	                 name="reviewHeadline"
	                 class="form-control"
	                 rows="1"
	                 placeholder="What the most important for us to know?"
	                 required
	               ></textarea>
	             </td>
	           </tr>
	           <tr>
	             <td>
	               <p class="font-weight-bold">Add a written review</p>
	             </td>
	             <td>
	               <textarea
	                 name="reviewContent"
	                 class="form-control"
	                 rows="4"
	                 placeholder="What did you like or dislike?"
	                 required
	               ></textarea>
	             </td>
	           </tr>
	           <tr>
	             <td></td>
	             <td class="text-right">
	               <button type="submit" class="btn form-btn">Submit</button>
	             </td>
	           </tr>
	         </tbody>
	       </table>
	     </div>
	   </form>
    </div>
    <!-- /.container -->
  </body>
</html>
    