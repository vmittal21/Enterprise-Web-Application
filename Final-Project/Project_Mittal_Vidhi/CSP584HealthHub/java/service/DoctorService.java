package service;

import java.util.List;
import java.util.Map;

import bean.Appointment;
import bean.Doctor;
import dao.DoctorDao;
import dao.DoctorDaoImpl;

public class DoctorService {
	private static DoctorDaoImpl doctorDao = new DoctorDaoImpl();
	private static List<String> speciaty;
	public List<String> getSpecialties(){
		if(speciaty != null)return speciaty;
		speciaty = doctorDao.getSpecialties();
		return speciaty;
	}
	
	public List<Doctor> getDoctorBySpecialty(String specialty){
		return doctorDao.getDoctorBySpecialty(specialty);
	}
	
	public List<Doctor> getDoctorByFilters(Map<String,String> filter){
		return doctorDao.getDoctorByFilters(filter);
	}


	public void addAppointment(Appointment appointment, String string) {
		// TODO Auto-generated method stub
		doctorDao.addAppointment(appointment,string);
	}

	public void removeAppointment(String id) {
		// TODO Auto-generated method stub
		doctorDao.removeAppointment(id);
	}
}
