package bean;

import java.util.ArrayList;
import java.util.List;

public class Order {
	private String orderId;
	private String purchaseDate;
	private double totalPrice;
	private List<OrderItem> items;
	private String status;
	public Order(String orderId) {
		this.orderId = orderId;
		items = new ArrayList<OrderItem>();
	}
	public Order(String orderId, String purchaseDate, double totalPrice, List<OrderItem> items, String status) {
		super();
		this.orderId = orderId;
		this.purchaseDate = purchaseDate;
		this.totalPrice = totalPrice;
		this.items = items;
		this.status = status;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(String purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public List<OrderItem> getItems() {
		return items;
	}
	public void setItems(List<OrderItem> items) {
		this.items = items;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
