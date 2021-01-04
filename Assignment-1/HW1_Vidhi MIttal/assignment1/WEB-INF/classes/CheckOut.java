import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.*;

@WebServlet("/CheckOut")

//once the user clicks buy now button page is redirected to checkout page where user has to give checkout information

public class CheckOut extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
	        Utilities Utility = new Utilities(request, pw);
		storeOrders(request, response);
	}
	
	protected void storeOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    try
        {
        response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
        Utilities utility = new Utilities(request,pw);
		if(!utility.isLoggedin())
		{
			HttpSession session = request.getSession(true);				
			session.setAttribute("login_msg", "Please Login to add items to cart");
			response.sendRedirect("Login");
			return;
		}
        HttpSession session=request.getSession(); 

		//get the order product details	on clicking submit the form will be passed to submitorder page	
		
	    String userName = session.getAttribute("username").toString();
        String orderTotal = request.getParameter("orderTotal");
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		pw.print("<form name ='CheckOut' action='Payment' method='post'>");
        pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>Order</a>");
		pw.print("</h2><div class='entry'>");
		pw.print("<table  class='gridtable'><tr><td>Customer Name:</td><td>");
		pw.print(userName);
		pw.print("</td></tr>");
		// for each order iterate and display the order name price
		for (OrderItem oi : utility.getCustomerOrders()) 
		{
			pw.print("<tr><td> Product Purchased:</td><td>");
			pw.print(oi.getName()+"</td></tr><tr><td>");
			pw.print("<input type='hidden' name='orderPrice' value='"+oi.getPrice()+"'>");
			pw.print("<input type='hidden' name='orderName' value='"+oi.getName()+"'>");
			pw.print("Product Price:</td><td>"+ oi.getPrice());
			pw.print("</td></tr>");
		}
		pw.print("<tr><td>");
        pw.print("Total Order Cost</td><td>"+orderTotal);
		pw.print("<input type='hidden' name='orderTotal' value='"+orderTotal+"'>");
		pw.print("</td></tr></table><table><tr></tr><tr></tr>");	
		pw.print("<tr><td>");
     	pw.print("Credit/accountNo</td>");
		pw.print("<td><input type='text' name='creditCardNo'>");
		pw.print("</td></tr>");
		pw.print("<tr><td>");
	    pw.print("Customer Address</td>");
		pw.print("<td><input type='text' name='userAddress'>");
        pw.print("</td></tr>");
		pw.print("<tr><td>");
		pw.print("Delivery selected:</td>");
		pw.print("<td><select size=1>");
        
        pw.print("<option value='storepickup'>Store Pickup</option>");
        pw.print("</select>");
		pw.print("</td></tr>");
		pw.print("<tr><td>");
		pw.print("Select the location of store pickup</td>");
		pw.print("<td><select size=1>");
        pw.print("<option value='Chicago60616'>Douglas, Chicago, Illinois 60616</option>");
        pw.print("<option value='Chicago60622'>West Park, Chicago, Illinois 60622 </option>");
		pw.print("<option value='Chicago60603'>Loop, Chicago, Illinois 60603 </option>");
		pw.print("<option value='Chicago60607'>University Village, Chicago, Illinois 60607</option>");
		pw.print("<option value='Chicago60621'>Englewood, Chicago, Illinois 60621</option>");
		pw.print("<option value='Chicago60626'>Rogers Park, Chicago, Illinois 60626</option>");
		pw.print("<option value='Chicago60638'>Clearing, Chicago, Illinois 60638</option>");
		pw.print("<option value='HarwoodHeights60706'>Harwood Heights, Illinois 60706</option>");
		pw.print("<option value='Alsip60803'>Alsip, Illinois 60803</option>");
		pw.print("<option value='Chicago60615'>Kenwood, Chicago, Illinois 60615</option>");
        pw.print("</select>");
		pw.print("</td></tr>");
		pw.print("<tr><td>");
		pw.print("Would you like to purchase warranty for just $5.00 more?</td>");
		pw.print("<td><select size=1>");
        pw.print("<option value='yes'>Yes, add warranty</option>");
        pw.print("<option value='no'>No, thanks!</option>");
        pw.print("</select>");
		pw.print("</td></tr>");
		pw.print("<tr><td colspan='2'>");
		pw.print("<input type='submit' name='submit' class='btnbuy'>");
        pw.print("</td></tr></table></form>");
		pw.print("</div></div></div>");		
		utility.printHtml("Footer.html");
	    }
        catch(Exception e)
		{
         System.out.println(e.getMessage());
		}  			
		}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	    {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
	    }
}
