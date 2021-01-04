package bean;

import java.util.List;

public class Doctor {
	private int id;
	private String name;
	private String specilization;
	private String phoneNum;
	private List<Appointment> appointments;
	
	private String address;
	private String state;
	private String city;
	private String zipcode;
	private String longtitude;
	private String latitude;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Doctor() {
		
	}
	public Doctor(int id, String name, String specilization, String phoneNum, String address, String state, String city,
			String zipcode, String longtitude, String latitude) {
		super();
		this.id = id;
		this.name = name;
		this.specilization = specilization;
		this.phoneNum = phoneNum;
		this.address = address;
		this.state = state;
		this.city = city;
		this.zipcode = zipcode;
		this.longtitude = longtitude;
		this.latitude = latitude;
	}
	public Doctor(String name, String specilization, String phoneNum, 
			String address, String state, String city, String zipcode, String longtitude, String latitude) {
		this.name = name;
		this.specilization = specilization;
		this.phoneNum = phoneNum;
		this.address = address;
		this.state = state;
		this.city = city;
		this.zipcode = zipcode;
		this.longtitude = longtitude;
		this.latitude = latitude;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getLongtitude() {
		return longtitude;
	}
	public void setLongtitude(String longtitude) {
		this.longtitude = longtitude;
	}
	public String getlatitude() {
		return latitude;
	}
	public void setlatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getSpecilization() {
		return specilization;
	}
	public void setSpecilization(String specilization) {
		this.specilization = specilization;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public List<Appointment> getAppointments() {
		return appointments;
	}
	public void setAppointments(List<Appointment> appointments) {
		this.appointments = appointments;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}
