import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/VirtualRealityList")

public class VirtualRealityList extends HttpServlet {

	/* Console Page Displays all the Consoles and their Information in Game Speed */

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		String name = null;
		String CategoryName = request.getParameter("maker");


		/* Checks the Tablets type whether it is microsft or sony or nintendo */

		HashMap<String, VirtualReality> hm = new HashMap<String, VirtualReality>();
		if(CategoryName==null){
			hm.putAll(SaxParserDataStore.virtualRealities);
			name = "";
		}
		else
		{
		   if(CategoryName.equals("oculus"))
		   {
			 for(Map.Entry<String,VirtualReality> entry : SaxParserDataStore.virtualRealities.entrySet())
			 {
				if(entry.getValue().getRetailer().equals("Oculus"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
			 }
				name = "Oculus";
		   }
		   else if(CategoryName.equals("bnext"))
		    {
			for(Map.Entry<String,VirtualReality> entry : SaxParserDataStore.virtualRealities.entrySet())
				{
				 if(entry.getValue().getRetailer().equals("BNext"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
				 name = "BNext";
			}
			else if(CategoryName.equals("destek"))
			{
				for(Map.Entry<String,VirtualReality> entry : SaxParserDataStore.virtualRealities.entrySet())
				{
				 if(entry.getValue().getRetailer().equals("Destek"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
			   	 name = "Destek";
			}
			else if(CategoryName.equals("optoslon"))
			{
				for(Map.Entry<String,VirtualReality> entry : SaxParserDataStore.virtualRealities.entrySet())
				{
				 if(entry.getValue().getRetailer().equals("Optoslon"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
			   	 name = "Optoslon";
			}
			else if(CategoryName.equals("google"))
			{
				for(Map.Entry<String,VirtualReality> entry : SaxParserDataStore.virtualRealities.entrySet())
				{
				 if(entry.getValue().getRetailer().equals("Google"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
			   	 name = "Google";
			}
		}


		/* Header, Left Navigation Bar are Printed.

		All the Console and Console information are displayed in the Content Section

		and then Footer is Printed*/

		Utilities utility = new Utilities(request,pw);
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>"+name+" Virtual Reality</a>");
		pw.print("</h2><div class='entry'><table id='bestseller'>");
		int i = 1; int size= hm.size();
		for(Map.Entry<String, VirtualReality> entry : hm.entrySet())
		{
			VirtualReality virtualReality = entry.getValue();
			if(i%3==1) pw.print("<tr>");
			pw.print("<td><div id='shop_item'>");
			pw.print("<h3>"+virtualReality.getName()+"</h3>");
			System.out.println("+++++++++++++++++++++++++++++++++++"+virtualReality.getName());
			pw.print("<strong>$"+virtualReality.getPrice()+"</strong><ul>");
			pw.print("<li id='item'><img src='images/virtualRealities/"+virtualReality.getImage()+"' alt='' /></li>");

			pw.print("<li><form method='post' action='Cart'>" +
					"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='virtualRealities'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value=''>"+
					"<input type='submit' class='btnbuy' value='Buy Now'></form></li>");
			pw.print("<li><form method='post' action='WriteReview'>"+"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='virtualRealities'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value=''>"+
				    "<input type='submit' value='WriteReview' class='btnreview'></form></li>");
			pw.print("<li><form method='post' action='ViewReview'>"+"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='virtualRealities'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value=''>"+
				    "<input type='submit' value='ViewReview' class='btnreview'></form></li>");
			pw.print("</ul></div></td>");
			if(i%3==0 || i == size) pw.print("</tr>");
			i++;
		}
		pw.print("</table></div></div></div>");

		utility.printHtml("Footer.html");

	}
}
