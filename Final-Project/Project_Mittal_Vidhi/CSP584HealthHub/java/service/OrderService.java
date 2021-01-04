package service;

import java.util.List;
import java.util.Map;

import org.apache.catalina.LifecycleState;

import bean.Order;
import bean.OrderItem;
import dao.OrderDao;

public class OrderService {
	static OrderDao orderDao = new OrderDao();
	public void generateOrder(String userId, String fullName, String address, String city, String state,
			String zipcode, String paymentInfo, String deliveryOption, String store, String orderId, OrderItem item) {
		// TODO Auto-generated method stub
		orderDao.insertTransaction(userId, fullName, address, city, state, zipcode, paymentInfo, deliveryOption, store, orderId, item);
	}
	public List<Order> getOrderByUser(String userName) {
		return orderDao.getOrderByUser(userName);
	}
	public void deleteOrder(String orderId) {
		// TODO Auto-generated method stub
		orderDao.deleteOrder(orderId);
	}
	public List<OrderItem> getOrderById(String orderId) {
		return orderDao.getOrderById(orderId);
	}
}
