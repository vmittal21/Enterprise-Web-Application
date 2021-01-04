package servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bson.Document;

import com.google.gson.Gson;
import com.mongodb.client.MongoCursor;

import bean.Bestseller;
import bean.Product;
import bean.Review;
import dao.ProductDaoImpl;
import dao.ReviewDaoImpl;

@WebServlet("/DataAnalysis")
public class DataAnalysis extends HttpServlet {
	static ProductDaoImpl productDaoImpl = new ProductDaoImpl();
	static ReviewDaoImpl reviewDaoImpl = new ReviewDaoImpl();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		analytics(request, response);
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		String method = request.getParameter("method");
		if(method.equals("trending")) {
			trending(request, response);
		}else if(method.equals("analytics")) {
			analytics(request, response);
		}else if(method.equals("inventory")) {
			inventory(request, response);
		}
	}

	private void inventory(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		List<Product> products = productDaoImpl.getAll();
		String reviewJson = new Gson().toJson(products);
        response.setContentType("application/JSON");
        response.setCharacterEncoding("UTF-8");
        try {
			response.getWriter().write(reviewJson);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void analytics(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String priceCompare = request.getParameter("priceCompare");
		String price = request.getParameter("price");
		String ratingCompare = request.getParameter("ratingCompare");
		String rating = request.getParameter("rating");
		String productName = request.getParameter("productName");
		String city = request.getParameter("city");
		String zipcode = request.getParameter("zipcode");
		String groupBy = request.getParameter("groupBy");
		Map<String, String> params = new HashMap<String, String>();
		if(price != null && price.length() > 0) {
			params.put("productPrice", price);
		}
		if(priceCompare != null && priceCompare.length() > 0) {
			params.put("priceCompare", priceCompare);
		}
		if(ratingCompare != null && ratingCompare.length() > 0) {
			params.put("ratingCompare", ratingCompare);
		}
		if(rating != null && rating.length() > 0) {
			params.put("rating", rating);
		}
		if(productName != null && productName.length() > 0) {
			params.put("productName", productName);
		}
		if(city != null && city.length() > 0) {
			params.put("city", city);
		}
		if(zipcode != null && zipcode.length() > 0) {
			params.put("zipcode", zipcode);
		}
		if(groupBy != null && groupBy.length() > 0) {
			params.put("groupBy", groupBy);
		}
		Map<Integer, MongoCursor<Document>> res = reviewDaoImpl.findData(params);
		request.setAttribute("res", res);
		try {
			request.getRequestDispatcher("analytics.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void trending(HttpServletRequest request, HttpServletResponse response) {
		String action = request.getParameter("action");
		if(action.equals("bestseller")) {
			List<Bestseller> list = productDaoImpl.getBestSeller();
			request.setAttribute("bestsellers", list);
		}else if(action.equals("bestrating")) {
			List<Bestseller> list = reviewDaoImpl.bestRating();
			request.setAttribute("bestratings", list);
		}
		try {
			request.getRequestDispatcher("admin.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
