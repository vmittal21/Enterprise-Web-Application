package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.UserService;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		String username = request.getParameter("emailAddress");
		String password = request.getParameter("password");
		String usertype = request.getParameter("usertype");
		if(UserService.checkLogin(username, password)) {
			try {
				HttpSession session = request.getSession(true);
				session.setAttribute("username", username);
				session.setAttribute("usertype", usertype);
				if(usertype.equals("customer"))response.sendRedirect("index.jsp");
				else if(usertype.equals("manager"))response.sendRedirect("admin.jsp");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			request.setAttribute("login_msg", "Please check your username, password and user type!");
			try {
				request.getRequestDispatcher("login.jsp").forward(request, response);
			} catch (ServletException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
