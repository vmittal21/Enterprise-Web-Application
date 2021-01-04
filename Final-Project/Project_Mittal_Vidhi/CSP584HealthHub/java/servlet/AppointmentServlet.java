package servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Appointment;
import service.DoctorService;

@WebServlet("/AppointmentServlet")
public class AppointmentServlet extends HttpServlet{
	private static DoctorService doctorService = new DoctorService();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		doctorService.removeAppointment(id);
		try {
			request.getRequestDispatcher("viewSchedule.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		int doctorId = Integer.parseInt(request.getParameter("doctorId"));
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String gender = request.getParameter("gender");
		String birth = request.getParameter("birth");
		String address = request.getParameter("address");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		String zipcode = request.getParameter("zipCode");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String date = request.getParameter("appointmentDate");
		String time = request.getParameter("appointmentTime");
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		Date date1 = null;
		try {
			System.out.println(date + " " + time);
			date1 = sf.parse(date + " " + time);
			System.out.println(date1);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Appointment appointment = new Appointment(doctorId,firstName+lastName, gender, birth, address+city+state+zipcode, phone, email, date1);
		doctorService.addAppointment(appointment, new Utilities(request, response).username());
		try {
			response.getWriter().print("Appointment Scheduled, jump to homepage");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.setHeader("refresh", "2;index.jsp");
	}
}
