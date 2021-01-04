package servlet;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.User;
import dao.UserDaoImpl;
import service.MongoDBService;

@WebServlet("/SubmitReview")
public class SubmitReview extends HttpServlet {
	private static MongoDBService mongoDBService = new MongoDBService();
	static UserDaoImpl userDaoImpl = new UserDaoImpl();
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(true);
		Utilities utilities = new Utilities(request, response);
		if(!utilities.isLoggedin()){
			
			session.setAttribute("login_msg", "Please Login to add items to cart");
			try {
				response.sendRedirect("login.jsp");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		}
		String productName = request.getParameter("productName");
		Double productPrice = Double.parseDouble(request.getParameter("productPrice"));
		String rating = request.getParameter("rating");
		String headline = request.getParameter("reviewHeadline");
		String reviewContent = request.getParameter("reviewContent");
		User customer = userDaoImpl.getProfile(utilities.username());
		mongoDBService.insert(utilities.username(), productName, Integer.parseInt(rating), headline, reviewContent, customer.getZipcode(), customer.getCity(), productPrice);
		try {
			response.getWriter().print("Review Submitted, jump to homepage");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.setHeader("refresh", "2;index.jsp");
	}
}
