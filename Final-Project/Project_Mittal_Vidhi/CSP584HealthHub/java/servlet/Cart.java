package servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.OrderItem;
@WebServlet("/Cart")
public class Cart extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String method = request.getParameter("method");
		Utilities utilities = new Utilities(request, response);
		if(method.equals("updateQuantity")) {
			updateQuantity(request, response, utilities);
		}else if(method.equals("deleteProduct")){
			deleteProduct(request, response, utilities);
		}
		else if(method.equals("addToCart")) {
			addToCart(request, response, utilities);
		}else if(method.equals("checkCart")) {
			checkCart(request, response, utilities);
		}
		
	}
	private void checkCart(HttpServletRequest request, HttpServletResponse response, Utilities utilities) {
		boolean isEmpty = utilities.cartIsEmpty();
		String msg = isEmpty?"your cart is empty":"okay";
        response.setContentType("application/JSON");
        response.setCharacterEncoding("UTF-8");
        try {
			response.getWriter().write(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void addToCart(HttpServletRequest request, HttpServletResponse response, Utilities utilities) throws IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("productId");
		String image = request.getParameter("productImage").trim();
		int inventory = Integer.parseInt(request.getParameter("productInventory"));
		double price = Double.parseDouble(request.getParameter("productPrice"));
		String brand = request.getParameter("productBrand");
		String productName = request.getParameter("productName");
		OrderItem orderItem = new OrderItem(id, productName , price, image, brand, inventory);
		utilities.addToCart(orderItem);
		response.sendRedirect("cart.jsp");
	}
	public void deleteProduct(HttpServletRequest request, HttpServletResponse response, Utilities utilities) {
		String productName = request.getParameter("productName");
		utilities.deleteProductFromCart(productName);
	}
	public void updateQuantity(HttpServletRequest request, HttpServletResponse response, Utilities utilities) {
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		String productName = request.getParameter("productName");
		utilities.updateCartQuantity(productName, quantity);
        response.setContentType("application/JSON");
        response.setCharacterEncoding("UTF-8");
        try {
			response.getWriter().write("success");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
