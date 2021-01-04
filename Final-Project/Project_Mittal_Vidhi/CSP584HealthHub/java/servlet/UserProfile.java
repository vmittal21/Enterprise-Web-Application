package servlet;

import java.io.IOException;
import java.util.List;
import java.util.jar.Attributes.Name;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import bean.User;
import dao.UserDaoImpl;
@WebServlet("/UserProfile")
public class UserProfile extends HttpServlet{
	static UserDaoImpl userDaoImpl = new UserDaoImpl();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		String method = request.getParameter("method");
		String username = new Utilities(request, response).username();
		if(method.equals("view")) {
			viewProfile(request, response,username);
		}else if(method.equals("check")) {
			checkProfile(request, response,username);
		}
	}
	private void viewProfile(HttpServletRequest request, HttpServletResponse response, String username) {
		// TODO Auto-generated method stub
		User info = userDaoImpl.getProfile(username);
		request.setAttribute("profile", info);
		try {
			request.getRequestDispatcher("userProfile.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void checkProfile(HttpServletRequest request, HttpServletResponse response, String username) {
		// TODO Auto-generated method stub
		
		String msg = "";
		if(userDaoImpl.checkProfile(username)) {
			msg = new Gson().toJson("true");
		}else {
			msg = new Gson().toJson("false");
		}
		response.setContentType("application/JSON");
        response.setCharacterEncoding("UTF-8");
        try {
			response.getWriter().write(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		String userAddress=request.getParameter("address");
		String zipcode = request.getParameter("zipcode");
		String city = request.getParameter("city");
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String birth = request.getParameter("birth");
		String age = request.getParameter("age");
		String job = request.getParameter("job");
		String email = request.getParameter("email");
		String state = request.getParameter("state");
		String ss[] = name.split(" ");
		String userName = new Utilities(request, response).username();
		User user = new User(ss[0], ss[1], gender, birth, age, userAddress, city, state, zipcode, job, email);
		user.setUserName(userName);
		userDaoImpl.updateProfile(user);
		
		this.viewProfile(request, response, userName);
	}
}
