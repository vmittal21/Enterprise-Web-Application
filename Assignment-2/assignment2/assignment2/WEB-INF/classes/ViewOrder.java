import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;
import java.io.*;

@WebServlet("/ViewOrder")

public class ViewOrder extends HttpServlet {
	
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

		Utilities utility = new Utilities(request, pw);
		//check if the user is logged in
		if(!utility.isLoggedin()){
			HttpSession session = request.getSession(true);				
			session.setAttribute("login_msg", "Please Login to View your Orders");
			response.sendRedirect("Login");
			return;
		}
		String username=utility.username();
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		pw.print("<form name ='ViewOrder' action='ViewOrder' method='get'>");
		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>Order</a>");
		pw.print("</h2><div class='entry'>");


		if(request.getParameter("Order")==null)
		{
			pw.print("<table align='center'><tr><td>Enter OrderNo &nbsp&nbsp<input name='orderId' type='text'></td>");
			pw.print("<td><input type='submit' name='Order' value='ViewOrder' class='btnbuy'></td></tr></table>");
		}

		//hashmap gets all the order details from file 

		HashMap<Integer, ArrayList<OrderPayment>> orderPayments = new HashMap<Integer, ArrayList<OrderPayment>>();
		String TOMCAT_HOME = System.getProperty("catalina.home");

		
		if(request.getParameter("Order")!=null && request.getParameter("Order").equals("ViewOrder"))
		{
			HttpSession session = request.getSession(true);;

			if (request.getParameter("orderId") != null && request.getParameter("orderId") != "" )
			{	
				int orderId=Integer.parseInt(request.getParameter("orderId"));
				pw.print("<input type='hidden' name='orderId' value='"+orderId+"'>");
				//get the order details from file
				try
				{
				
					orderPayments=MySqlDataStoreUtilities.selectOrder();
				}
				catch(Exception e)
				{
			        //e.printStackTrace();
				}
				int size=0;
			
				if(orderPayments.get(orderId)!=null)
				{
				for(OrderPayment od:orderPayments.get(orderId))	
				if (session.getAttribute("usertype").equals("retailer"))
				{	
					size= orderPayments.get(orderId).size();
				}
				else
				{				
				if(od.getUserName().equals(username))
				size= orderPayments.get(orderId).size();
				}
				// display the orders if there exist order with order id
				if(size>0)
				{	
					pw.print("<table  class='gridtable'>");
					pw.print("<tr><td></td>");
					pw.print("<td>OrderId:</td>");
					pw.print("<td>UserName:</td>");
					pw.print("<td>productOrdered:</td>");
					pw.print("<td>productPrice:</td></tr>");
					for (OrderPayment oi : orderPayments.get(orderId)) 
					{
						pw.print("<tr>");			
						pw.print("<td><input type='radio' name='orderName' value='"+oi.getOrderName()+"'></td>");			
						pw.print("<td>"+oi.getOrderId()+".</td><td>"+oi.getUserName()+"</td><td>"+oi.getOrderName()+"</td><td>Price: "+oi.getOrderPrice()+"</td>");
						pw.print("<td><input type='submit' name='Order' value='CancelOrder' class='btnbuy'></td>");
						pw.print("</tr>");
					
					}
					pw.print("</table>");
				}
				else
				{
					pw.print("<h4 style='color:red'>You have not placed any order with this order id</h4>");
				}
			}else
				
			{
				pw.print("<h4 style='color:red'>Please enter the valid order number</h4>");	
			}
			}
		}
		//if the user presses cancel order from order details shown then process to cancel the order
		if(request.getParameter("Order")!=null && request.getParameter("Order").equals("CancelOrder"))
		{
			String orderName=request.getParameter("orderName");

			if(request.getParameter("orderName") != null)
			{
				//String orderName=request.getParameter("orderName");
				int orderId=0;
				orderId=Integer.parseInt(request.getParameter("orderId"));
				ArrayList<OrderPayment> ListOrderPayment =new ArrayList<OrderPayment>();
				//get the order from file
				try
				{
		
					orderPayments=MySqlDataStoreUtilities.selectOrder();
				}
				catch(Exception e)
				{
			
				}
				//get the exact order with same ordername and add it into cancel list to remove it later
				for (OrderPayment oi : orderPayments.get(orderId)) 
					{
							if(oi.getOrderName().equals(orderName) && oi.getUserName().equals(username))
							{
								MySqlDataStoreUtilities.deleteOrder(orderId,orderName);							   
								ListOrderPayment.add(oi);
								pw.print("<h4 style='color:red'>Your Order is Cancelled</h4>");								
							}
					}
				//remove all the orders from hashmap that exist in cancel list
				orderPayments.get(orderId).removeAll(ListOrderPayment);
				if(orderPayments.get(orderId).size()==0)
					{
							orderPayments.remove(orderId);
					}
			
			}else
			{
				pw.print("<h4 style='color:red'>Please select any product</h4>");
			}
		}
		pw.print("</form></div></div></div>");		
		utility.printHtml("Footer.html");
	}

}


