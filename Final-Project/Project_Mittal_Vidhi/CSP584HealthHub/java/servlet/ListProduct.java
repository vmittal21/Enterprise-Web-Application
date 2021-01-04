package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Product;
import service.ProductCrudService;

@WebServlet("/ListProduct")
public class ListProduct extends HttpServlet {
	static ProductCrudService productCrudService = new ProductCrudService();
	protected void doGet(HttpServletRequest request, HttpServletResponse response){
		String type = request.getParameter("type");
		List<Product> products = productCrudService.getProductByCatagory(type);
		request.setAttribute("products", products);
		try {
			request.getRequestDispatcher("listProduct.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
