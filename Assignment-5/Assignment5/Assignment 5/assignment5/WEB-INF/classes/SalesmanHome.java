import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/SalesmanHome")
public class SalesmanHome extends HttpServlet {
    private String error_msg;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter pw = response.getWriter();
        displaySalesmanHome(request, response, pw, "");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        Utilities utility = new Utilities(request, pw);

      
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String repassword = request.getParameter("repassword");

     
        String customerName = request.getParameter("customerName");
        String itemName = request.getParameter("itemName");
        String itemCatalog = request.getParameter("itemCatalog");
        String creditCardNo = request.getParameter("creditCardNo");
        String customerAddress = request.getParameter("customerAddress");


        HashMap<String, User> hm = new HashMap<String, User>();
        String TOMCAT_HOME = System.getProperty("catalina.home");

        //get the user details from file
        try {
            hm = MySqlDataStoreUtilities.selectUser();
        } catch (Exception e) {

        }

        if (request.getParameter("customer") != null && request.getParameter("customer").equals("Create Customer")) {
            
            if (!password.equals(repassword)) {
                error_msg = "Passwords doesn't match!";  
                displaySalesmanHome(request, response, pw, "customer");
            } else {

                // if the user already exist show error that already exist
                if (hm.containsKey(username)) {
                    error_msg = "Username already exist.";
                    displaySalesmanHome(request, response, pw, "customer");
                } else {
                    MySqlDataStoreUtilities.insertUser(username, password,repassword,"customer");
                    HttpSession session = request.getSession(true);
                    session.setAttribute("login_msg", "The customer account created successfully.");

                    error_msg = "The customer account created successfully.";
                    displaySalesmanHome(request, response, pw, "customer");  
                }

            }
        } else if (request.getParameter("order") != null && request.getParameter("order").equals("Create Order")) {
            
            if (!hm.containsKey(customerName)) { 
                error_msg = "Cannot found this customer.";
                displaySalesmanHome(request, response, pw, "order");
            } else {
                double totalPrice;
                if (utility.isContainsStr(request.getParameter("totalPrice"))) {
                 
                    error_msg = "Please type in number!"; 
                    displaySalesmanHome(request, response, pw, "order");
                    return;
                } else {
                    totalPrice = Double.parseDouble(request.getParameter("totalPrice"));
                }
                if (utility.isItemExist(itemCatalog, itemName)) { 
                    SimpleDateFormat df = new SimpleDateFormat("HHmmss");
                    int orderId = Integer.parseInt(df.format(new Date())); 
                    utility.storeNewOrder(orderId, itemName, customerName, totalPrice, customerAddress, creditCardNo);
                  
                    error_msg = "The order created successfully.";
                    displaySalesmanHome(request, response, pw, "order");
                } else {
                    error_msg = "Cannot found this item.";
                    displaySalesmanHome(request, response, pw, "order");
                }
            }
        }
    }

    protected void displaySalesmanHome(HttpServletRequest request,
                                       HttpServletResponse response, PrintWriter pw, String flag)  
            throws ServletException, IOException {

        Utilities utility = new Utilities(request, pw);
        utility.printHtml("Header.html");
        utility.printHtml("LeftNavigationBar.html");

        pw.print("<div id='content'>");
        pw.print("<div class='post'>");
        pw.print("<h3 class='title'>");
        pw.print("Create New Customer");
        pw.print("</h3>");
        pw.print("<div class='entry'>");

        if (flag.equals("customer"))
            pw.print("<h4 style='color:red'>" + error_msg + "</h4>");

        pw.print("<form action='SalesmanHome' method='post'>");
        pw.print("<table style='width:100%'><tr><td>");
        pw.print("<h4>Username</h4></td><td><input type='text' name='username' value='' class='input' required></input>");
        pw.print("</td></tr><tr><td>");
        pw.print("<h4>Password</h4></td><td><input type='password' name='password' value='' class='input' required></input>");
        pw.print("</td></tr><tr><td>");
        pw.print("<h4>Re-Password</h4></td><td><input type='password' name='repassword' value='' class='input' required></input>");
        pw.print("</td></tr><tr><td>");
        pw.print("<input type='submit' class='btnbuy' value='Create Customer' name='customer' style='float: right;height: 20px margin: 20px; margin-right: 10px;'></input>");
        pw.print("</td></tr><tr><td></td><td>");
        pw.print("</td></tr></table>");
        pw.print("</form></div></div>");


        pw.print("<div class='post'>");
        pw.print("<h3 class='title'>");
        pw.print("Create New Order");
        pw.print("</h3>");
        pw.print("<div class='entry'>");
        if (flag.equals("order"))
            pw.print("<h4 style='color:red'>" + error_msg + "</h4>");
        pw.print("<form action='SalesmanHome' method='post'>");
        pw.print("<table style='width:100%'><tr><td>");
        pw.print("<h4>Customer name</h4></td><td><input type='text' name='customerName' value='' class='input' required></input>");
        pw.print("</td></tr><tr><td>");
        pw.print("<h4>Item name</h4></td><td><input type='text' name='itemName' value='' class='input' required></input>");
        pw.print("</td></tr><tr><td>");

        pw.print("<h4>Item catalog</h4><td><select name='itemCatalog' class='input'>" +
                "<option value='Fitnesswatch' selected>Fitness watch</option>" +
                "<option value='Smartwatch'>Smart watch</option>" +
                "<option value='Headphone'>Headphone</option>" +
                "<option value='Television'>Television</option>" +
                "<option value='Soundsystem'>Sound System</option>" +
                "<option value='Game'>Phone</option>" +
                "<option value='Tablet'>Laptop</option>" +
                "<option value='VoiceAssistant'>Voice assistant</option>" +
                "<option value='Accessory'>Accessory</option></select>");
        pw.print("</td></tr></td><tr><td>");
        pw.print("<h4>Total price</h4></td><td><input type='text' name='totalPrice' value='' class='input' required></input>");
        //   pw.print("<h4>Total price</h4></td><td><input type=\"text\" name=\"totalPrice\" onkeyup=\"this.value=this.value.replace(/[^\d]/g,\'\')\" onafterpaste=\"this.value=this.value.replace(/[^\d]/g,\'\')\"  value=\'\'</input>");
        pw.print("</td></tr><tr><td>");
        pw.print("<h4>Credit/accountNo</h4></td><td><input type='text' name='creditCardNo' value='' class='input' required></input>");
        pw.print("</td></tr><tr><td>");
        pw.print("<h4>Customer Address</h3></td><td><input type='text' name='customerAddress' value='' class='input' required></input>");
        pw.print("</td></tr><tr><td>");
        pw.print("<input type='submit' class='btnbuy' value='Create Order' name='order' style='float: right;height: 20px margin: 20px; margin-right: 10px;'></input>");
        pw.print("</td></tr><tr><td></td><td>");
        pw.print("</td></tr></table>");
        pw.print("</form></div></div>");

        HashMap<Integer, ArrayList<OrderPayment>> orderPayments = new HashMap<Integer, ArrayList<OrderPayment>>();
        try {
           orderPayments = MySqlDataStoreUtilities.selectOrder();
        } catch (Exception ignored) {

        }

        pw.print("<div class='post'>");
        pw.print("<h2 class='title meta'>");
        pw.print("<a style='font-size: 24px;'>View Orders</a>");
        pw.print("</h2><div class='entry'>");

        pw.print("<table class='gridtable'>");
        pw.print("<tr>");
        pw.print("<td>Order Id:</td>");
        pw.print("<td>Username:</td>");
        pw.print("<td>Product Name:</td>");
        pw.print("<td>Price:</td></td>");
        pw.print("<td>Address:</td>");
        pw.print("<td>Credit Card:</td>");
        pw.print("</tr>");
        for (Map.Entry<Integer, ArrayList<OrderPayment>> entry : orderPayments.entrySet()) {
            for (OrderPayment od : entry.getValue()) {


                pw.print("<form method='post' action='RemoveUpdateOrder'>");
                pw.print("<tr>");
                pw.print("<td>" + od.getOrderId() + "</td>" +
                        "<td>" + od.getUserName() + "</td>" +
                        "<td>" + od.getOrderName() + "</td>" +
                        "<td>" + od.getOrderPrice() + "</td>" +
                        "<td>" + od.getUserAddress() + "</td>" +
                        "<td>" + od.getCreditCardNo() + "</td>");

                pw.print("<input type='hidden' name='orderName' value='" + od.getOrderName() + "'>");
                pw.print("<input type='hidden' name='orderId' value='" + od.getOrderId() + "'>");
                pw.print("<input type='hidden' name='username' value='" + od.getUserName() + "'>");
                pw.print("<input type='hidden' name='productName' value='" + od.getOrderName() + "'>");
                pw.print("<input type='hidden' name='price' value='" + od.getOrderPrice() + "'>");
                pw.print("<input type='hidden' name='address' value='" + od.getUserAddress() + "'>");
                pw.print("<input type='hidden' name='creditCard' value='" + od.getCreditCardNo() + "'>");
                pw.print("<input type='hidden' name='userType' value='Salesman'>");
                pw.print("<td><input type='submit' name='Order' value='Cancel' class='btnbuy'></td>");
                pw.print("<td><input type='submit' name='Order' value='Update' class='btnbuy'></td>");
                pw.print("</tr>");
                pw.print("</form>");
            }
        }
        pw.print("</table>");
        pw.print("</h2></div></div></div>");
    }
}
