package dao;

import java.util.List;
import java.util.Map;

import bean.Appointment;
import bean.Doctor;

public interface DoctorDao extends BasicCRUD<Doctor, Integer>{
	public List<Doctor> getDoctorBySpecialty(String specialty);
	public List<String> getSpecialties();
	List<Doctor> getDoctorByFilters(Map<String, String> filter);
	void addAllDoctors();
	void addAppointment(Appointment appointment, String username);
}
