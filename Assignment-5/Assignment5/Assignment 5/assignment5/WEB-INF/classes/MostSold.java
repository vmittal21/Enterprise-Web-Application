import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

@WebServlet("/MostSold")

public class MostSold extends HttpServlet {

	/* Trending Page Displays all the Consoles and their Information in Game Speed*/

	protected void doGet(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
		System.out.println("In MostSold");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request, pw);
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		ArrayList<String> products = new ArrayList<String>(5);
		products = MySqlDataStoreUtilities.getMostSoldProducts();
		pw.print("<p style='color:#fff;font-size:44px;font-weight:lighter;text-align:center;font-family:sans-serif;'>Top 5 Most Sold Products</p>");
		pw.print("<hr>");
		pw.print("<table align='center'>");
		for(int i=0; i<5; i++){
			System.out.println(products.get(i));
			pw.print("<tr><td><p style='color:#fff;font-size:22px;font-weight:lighter;text-align:center;font-family:sans-serif;'>"+products.get(i)+"</p></tr></td>");
		}
		pw.print("</table>");
	
	}



}
