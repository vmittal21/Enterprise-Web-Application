package servlet;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import bean.Doctor;
import dao.UserDaoImpl;
import service.DoctorService;
@WebServlet("/DoctorServlet")
public class DoctorServlet extends HttpServlet {
	private DoctorService doctorService = new DoctorService();
	private UserDaoImpl userDaoImpl = new UserDaoImpl();
	List<String> specialties = doctorService.getSpecialties();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getParameter("method");
		Utilities utilities = new Utilities(request, response);
		if(method != null && method.equals("nearbyDoctor")) {
//			if(!utilities.isLoggedin())
//			{
//				HttpSession session = request.getSession(true);				
//				session.setAttribute("login_msg", "Please Login to find a doctor nearby");
//				response.sendRedirect("login.jsp");
//				return;
//			}
			String zipcode = userDaoImpl.getZip(utilities.username());
			Map<String,String> filter = new HashMap<>();
			filter.put("zipcode",zipcode);
			List<Doctor> doctors = doctorService.getDoctorByFilters(filter);
			String medicalJson = new Gson().toJson(doctors);
	        response.setContentType("application/JSON");
	        response.setCharacterEncoding("UTF-8");
	        try {
				response.getWriter().write(medicalJson);
			} catch (IOException e) {
				e.printStackTrace();
			}
	        return;
		}
		request.setAttribute("specialties", specialties);
		request.getRequestDispatcher("findDoctor.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String specialty = request.getParameter("specialty");
		String state = request.getParameter("state");
		String city = request.getParameter("city");
		String zipcode = request.getParameter("zipcode");
		Map<String,String> filter = new HashMap<>();
		if(specialty != null && specialty.length() > 0)filter.put("specialty",specialty);
		if(state != null && state.length() > 0)filter.put("state",state);
		if(city != null && city.length() > 0)filter.put("city",city);
		if(zipcode != null && zipcode.length() > 0)filter.put("zipcode",zipcode);
		List<Doctor> doctors = doctorService.getDoctorByFilters(filter);
		request.setAttribute("doctors", doctors);
		request.setAttribute("specialties", specialties);
		request.getRequestDispatcher("findDoctor.jsp").forward(request, response);
	
	}
}
