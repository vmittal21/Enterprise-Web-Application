package servlet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.OrderItem;

public class Utilities extends HttpServlet {
	private static Map<String, List<OrderItem>> orderMap = new HashMap<>();
	HttpServletRequest request;
	HttpServletResponse response;
	HttpSession session;
	public Utilities(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.session = request.getSession(true);
		this.response = response;
	}
	public void logout(){
		session.removeAttribute("username");
		session.removeAttribute("usertype");
	}
	
	/*  logout Function checks whether the user is loggedIn or Not*/

	public boolean isLoggedin(){
		if (session.getAttribute("username")==null)
			return false;
		return true;
	}

	/*  username Function returns the username from the session variable.*/
	
	public String username(){
		if (session.getAttribute("username")!=null)
			return session.getAttribute("username").toString();
		return null;
	}
	
	/*  usertype Function returns the usertype from the session variable.*/
	public String usertype(){
		if (session.getAttribute("usertype")!=null)
			return session.getAttribute("usertype").toString();
		return null;
	}
	public boolean cartIsEmpty() {
		Map<String, OrderItem> orderMap = (Map<String, OrderItem>) session.getAttribute("orderMap");
		return orderMap == null || orderMap.size() == 0;
	}
	public void addToCart(OrderItem orderItem) {
		// TODO Auto-generated method stub
		Map<String, OrderItem> orderMap = (Map<String, OrderItem>) session.getAttribute("orderMap");
		String tmp = (String) session.getAttribute("totalPrice");
		tmp = tmp==null?"0":tmp;
		double totalPrice = Double.parseDouble(tmp);
		if(orderMap == null) {
			orderMap = new HashMap<>();
		}
		orderMap.put(orderItem.getName(), orderItem);
		totalPrice += orderItem.getTotalPrice();
		session.setAttribute("orderMap", orderMap);
		session.setAttribute("totalPrice", String.format("%.2f", totalPrice));
	}
	public void updateCartQuantity(String productName, int quantity) {
		Map<String, OrderItem> orderMap = (Map<String, OrderItem>) session.getAttribute("orderMap");
		String tmp = (String) session.getAttribute("totalPrice");
		tmp = tmp==null?"0":tmp;
		double totalPrice = Double.parseDouble(tmp);
		if(orderMap == null) {
			orderMap = new HashMap<>();
		}
		OrderItem orderItem = orderMap.get(productName);
		double diff = (quantity - orderItem.getQuantity()) * orderItem.getPrice();
		totalPrice += diff;
		orderItem.setQuantity(quantity);
		orderItem.setTotalPrice(quantity * orderItem.getPrice());
		orderMap.put(productName, orderItem);
		session.setAttribute("orderMap", orderMap);
		session.setAttribute("totalPrice", String.format("%.2f", totalPrice));
	}
	public void deleteProductFromCart(String productName) {
		Map<String, OrderItem> orderMap = (Map<String, OrderItem>) session.getAttribute("orderMap");
		String tmp = (String) session.getAttribute("totalPrice");
		tmp = tmp==null?"0":tmp;
		double totalPrice = Double.parseDouble(tmp);		if(orderMap == null) {
			orderMap = new HashMap<>();
		}
		OrderItem orderItem = orderMap.get(productName);
		totalPrice -= orderItem.getTotalPrice();
		orderMap.remove(productName);
		if(orderMap.size() == 0)totalPrice = 0;
		session.setAttribute("orderMap", orderMap);
		session.setAttribute("totalPrice", String.format("%.2f", totalPrice));
	}
}
