package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import bean.MedicalService;
import dao.MedicalDao;

@WebServlet("/MedicalServlet")
public class MedicalServlet extends HttpServlet{
	static MedicalDao medicalDao = new MedicalDao();
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		String[] parameters = request.getParameterValues("parameters");
		System.out.println(parameters[0]);
		String zipcode = request.getParameter("zipcode");
		List<MedicalService> medicalServices = medicalDao.getMedicalByfilters(parameters, zipcode);
		String medicalJson = new Gson().toJson(medicalServices);
        response.setContentType("application/JSON");
        response.setCharacterEncoding("UTF-8");
        try {
			response.getWriter().write(medicalJson);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
