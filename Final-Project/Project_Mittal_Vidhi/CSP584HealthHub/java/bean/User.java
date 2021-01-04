package bean;

import java.io.Serializable;

import javax.print.DocFlavor.STRING;

public class User implements Serializable{
	private String userName;
	private String firstName;
	private String lastName;
	private String password;
	private String usertype;
	public User(String userName, String firstName, String lastName, String password, String usertype, String gender,
			String birth, String age, String address, String city, String state, String zipcode) {
		super();
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.usertype = usertype;
		this.gender = gender;
		this.birth = birth;
		this.age = age;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zipcode = zipcode;
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
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}


	private String gender;
	private String birth;
	private String age;
	private String address;
	private String city;
	private String state;
	private String zipcode;
	private String job;
	private String email;
	public User() {}
	public User(String firstName, String lastName, String password, String usertype, String userName) {
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.password=password;
		this.usertype=usertype;
		this.setUserName(userName);
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsertype() {
		return usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		
		this.firstName = firstName;
	}

	public User(String firstName, String lastName, String gender, String birth, String age, String address, String city,
			String state, String zipcode, String job, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.birth = birth;
		this.age = age;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zipcode = zipcode;
		this.job = job;
		this.email = email;
	}
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
