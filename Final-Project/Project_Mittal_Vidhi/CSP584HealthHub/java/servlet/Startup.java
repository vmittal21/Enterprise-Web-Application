package servlet;

import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import dao.DoctorDaoImpl;
import dao.MedicalDao;
import dao.ProductDaoImpl;
import dao.StoreDaoImpl;
import util.SaxParserDataStore;

@WebServlet("/Startup")

/*  
startup servlet Intializes HashMap in SaxParserDataStore
*/

public class Startup extends HttpServlet
{

	public void init() throws ServletException
   {
		System.out.println("System is booting");
		SaxParserDataStore.addHashmap();
		new ProductDaoImpl().addAllProductsFromXML(new ArrayList<>(SaxParserDataStore.products.values()));
		new StoreDaoImpl().addAllStore();
		new DoctorDaoImpl().addAllDoctors();
		new MedicalDao().addAllMedical();
   }
}
