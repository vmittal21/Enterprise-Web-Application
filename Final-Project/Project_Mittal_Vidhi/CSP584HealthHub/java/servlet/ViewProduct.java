package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Product;
import service.ProductCrudService;

@WebServlet("/ViewProduct")
public class ViewProduct extends HttpServlet {
	ProductCrudService productCrudService = new ProductCrudService();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		String productId = request.getParameter("product");
		Product p = productCrudService.getProductById(productId);
		request.setAttribute("products", p);
		try {
			request.getRequestDispatcher("listProduct.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
