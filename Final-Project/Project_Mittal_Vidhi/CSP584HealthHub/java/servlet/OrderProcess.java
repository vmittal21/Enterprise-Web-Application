package servlet;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.cj.Session;

import bean.Order;
import bean.OrderItem;
import service.OrderService;
import util.MyUUID;

@WebServlet("/OrderProcess")
public class OrderProcess extends HttpServlet {
	static OrderService orderService = new OrderService();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getParameter("method");
		if(method.equals("delete")) {
			deleteOrder(request, response);
		}else if(method.equals("detail")) {
			orderDetails(request, response);
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		generateOrder(request, response);
		
	}
	private void orderDetails(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String orderId = request.getParameter("id");
		Utilities utilities = new Utilities(request, response);
      	List<OrderItem> orders = new OrderService().getOrderById(orderId);
      	HttpSession session = request.getSession(true);
      	session.setAttribute("orderItems", orders);
      	try {
			response.sendRedirect("OrderDetail.jsp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void deleteOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String orderId = request.getParameter("id");
		orderService.deleteOrder(orderId);
		request.getRequestDispatcher("viewOrder.jsp").forward(request, response);
	}
	public void generateOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession(true);		
		Utilities utility = new Utilities(request, response);
		if(!utility.isLoggedin())
		{
			session = request.getSession(true);				
			session.setAttribute("login_msg", "Please Login to Pay");
			response.sendRedirect("login.jsp");
			return;
		}
		String firstName = request.getParameter("firstName");
		String middleName = request.getParameter("middleName");
		String lastName = request.getParameter("lastName");
		String fullName = firstName + (middleName == null?"":middleName) + lastName;
		String address = request.getParameter("address");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		String zipcode = request.getParameter("zipCode");
		String paymentMethod = request.getParameter("paymentMethod");
		String nameOnCard = request.getParameter("cc-name");
		String cardNum = request.getParameter("cc-number");
		String expiration = request.getParameter("cc-expiration");
		String cvv = request.getParameter("cc-cvv");
		String paymentInfo = paymentMethod + "," + nameOnCard + "," + cardNum + "," + expiration + "," + cvv;
		String deliveryOption = request.getParameter("deliveryOption");
		String store = "N/A";
		
		if(deliveryOption.equals("Store Pickup")) {
			store = request.getParameter("storeName");
		}
		String orderId = MyUUID.getUUIDInOrderId().toString();
		request.setAttribute("subtotal", request.getParameter("subtotal"));
		request.setAttribute("tax", request.getParameter("tax"));
		request.setAttribute("total", request.getParameter("total"));
		request.setAttribute("deliveryDate", request.getParameter("deliveryDate"));
		request.setAttribute("orderId", orderId);
		request.setAttribute("purchaseDate", new Date().toString());
		System.out.println(request.getParameter("total"));
		for(OrderItem item: ((Map<String, OrderItem>) session.getAttribute("orderMap")).values()) {
			orderService.generateOrder(utility.username(), fullName, address, city, state, zipcode, paymentInfo, deliveryOption, store, orderId, item);
		}
		try {
			request.getRequestDispatcher("confirmation.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
