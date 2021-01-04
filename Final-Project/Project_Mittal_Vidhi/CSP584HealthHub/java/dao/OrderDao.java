package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import bean.Order;
import bean.OrderItem;
import util.JDBCUtil;

public class OrderDao {
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;
	public void insertTransaction(String userId, String fullName, String address, String city, String state,
			String zipcode, String paymentInfo, String deliveryOption, String store, String orderId, OrderItem item) {
		// TODO Auto-generated method stub
		String[] job = {"student", "banker", "accountant", "lawyer", "singer", "driver", "teacher"};
		String sql =  "INSERT INTO  Transactions(login_ID,Customer_Name,Customer_Age,Customer_Occupation,Credit_Card_Info,Order_ID,Order_Date,Expected_Delivery_Date,Actual_Delivery_Date,Product_ID,Product_Name,Category,Manufacturer,Review_Rating,Delivery_Tracking_ID,Delivery_Type,Delivery_Zip_Code,Transaction_Status,Order_Returned,Order_Delivered_on_Time, Total_Price, Quantity)" +
				"VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
		Random random = new Random();
		try {
			conn = JDBCUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, userId);
			ps.setString(2, fullName);
			ps.setString(3, (random.nextInt(10) + 20) + "");
			ps.setString(4, job[random.nextInt(job.length)]);
			ps.setString(5,paymentInfo);
			ps.setString(6,orderId);
			Calendar date = Calendar.getInstance();
			ps.setTimestamp(7, new Timestamp(date.getTime().getTime()));
			date.add(Calendar.DAY_OF_MONTH, random.nextInt(14) + 1);
			Calendar expected = date;
			Calendar date2 = Calendar.getInstance();
			date2.add(Calendar.DAY_OF_MONTH, random.nextInt(14) + 1);
			Calendar actual = date2;
			
			
			ps.setTimestamp(8, new Timestamp(expected.getTime().getTime()));
			ps.setTimestamp(9, new Timestamp(actual.getTime().getTime()));
			ps.setString(10, item.getProductId());
			ps.setString(11, item.getName());
			ps.setString(12, item.getType());
			ps.setString(13, item.getBrand());
			ps.setInt(14, random.nextInt(5));
			ps.setString(15, Math.abs(UUID.randomUUID().toString().hashCode()) + "");
			ps.setString(16, deliveryOption);
			ps.setString(17, zipcode);
			ps.setString(18, random.nextInt(2) == 0?"Disputed" : "Approved");
			ps.setString(19, random.nextInt(2) == 0?"Yes" : "No");
			ps.setString(20, actual.after(expected)?"No" : "Yes");
			ps.setDouble(21, item.getTotalPrice());
			ps.setInt(22, item.getQuantity());
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public List<Order> getOrderByUser(String userName) {
		// TODO Auto-generated method stub
		conn = JDBCUtil.getConnection();
		String sql = "SELECT * FROM TRANSACTIONS WHERE login_ID='" + userName + "';";
		Map<String, Order> orderMap =  new HashMap<>();
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				String orderId = rs.getString("Order_ID");
				orderMap.putIfAbsent(orderId, new Order(orderId));
				OrderItem item = new OrderItem( rs.getString("Product_Name"), rs.getString("Manufacturer"), rs.getDouble("Total_Price"),rs.getInt("Quantity"), rs.getTimestamp("Order_Date").toLocaleString());
				Order order = orderMap.get(orderId);
				order.getItems().add(item);
				order.setTotalPrice(item.getTotalPrice() + order.getTotalPrice());
				order.setPurchaseDate(item.getPurchaseDate());
				order.setStatus(rs.getString("Transaction_Status"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ArrayList<>(orderMap.values());
	}
	public void deleteOrder(String orderId) {
		// TODO Auto-generated method stub
		conn = JDBCUtil.getConnection();
		String sql = "DELETE FROM TRANSACTIONS WHERE Order_ID='" + orderId + "';";
		try {
			ps = conn.prepareStatement(sql);
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public List<OrderItem> getOrderById(String orderId){
		conn = JDBCUtil.getConnection();
		List<OrderItem> res = new ArrayList<>();
		String sql = "SELECT * FROM TRANSACTIONS WHERE Order_ID='" + orderId + "';";
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			SimpleDateFormat sf = new SimpleDateFormat("MM/dd/yyyy");
			while(rs.next()) {
				res.add(new OrderItem( rs.getString("Product_Name"), rs.getString("Manufacturer"), rs.getDouble("Total_Price"),rs.getInt("Quantity"), sf.format(rs.getTimestamp("Order_Date"))));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
}
