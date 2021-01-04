package bean;

import java.util.Date;

public class Appointment {
	//patient info
	private int doctorId;
	private int id;
	public Appointment(int doctorId, String fullName, String gender, String birth, String fullAddress, String phone,
			String email, Date date) {
		super();
		this.doctorId = doctorId;
		this.fullName = fullName;
		this.gender = gender;
		this.birth = birth;
		this.fullAddress = fullAddress;
		this.phone = phone;
		this.email = email;
		this.date = date;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	private String fullName;
	private String gender;
	private String birth;
	private String fullAddress;
	private String phone;
	private String email;
	private Date date; // dd-mm-hh
	public Appointment() {
		
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getFullAddress() {
		return fullAddress;
	}
	public void setFullAddress(String fullAddress) {
		this.fullAddress = fullAddress;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Appointment(String fullName, String gender, String birth, String fullAddress, String phone, String email,
			Date date) {
		this.fullName = fullName;
		this.gender = gender;
		this.birth = birth;
		this.fullAddress = fullAddress;
		this.phone = phone;
		this.email = email;
		this.date = date;
	}
	public int getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}
}
