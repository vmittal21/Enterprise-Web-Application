package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import service.UserService;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet{
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String emailAddress = request.getParameter("emailAddress");
		String password = request.getParameter("password");
		String usertype = request.getParameter("usertype");
		if(usertype == null) {
			usertype = "customer";
		}
		UserService.addUser(emailAddress, firstName, lastName, password, usertype);
		try {
			request.setAttribute("register_msg", "Successfully registered, please login");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//ajax check username is already existing
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		String userName = request.getParameter("userName");
		String ret = "false";
		if(UserService.checkRegister(userName)) {
			ret = "true";
		}
		String retJson = new Gson().toJson(ret);
        response.setContentType("application/JSON");
        response.setCharacterEncoding("UTF-8");
        try {
			response.getWriter().write(retJson);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
