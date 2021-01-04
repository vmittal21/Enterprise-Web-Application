package servlet;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			HttpSession session = request.getSession(true);
			session.removeAttribute("username");
			session.removeAttribute("usertype");
			response.sendRedirect("index.jsp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
