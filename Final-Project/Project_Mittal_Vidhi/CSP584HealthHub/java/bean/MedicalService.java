package bean;

public class MedicalService {
	private String id;
	private String type;
	private String name;
	public MedicalService(String id, String type, String name, String address, String zipcode,String longtitude,String latitude
			) {
		super();
		this.id = id;
		this.type = type;
		this.name = name;
		this.address = address;
		this.zipcode = zipcode;
		this.latitude = latitude;
		this.longtitude = longtitude;
	}
	private String address;
	private String zipcode;
	private String latitude;
	private String longtitude;
	public MedicalService() {
		
	}
	public MedicalService(String id, String name, String address, String zipcode, String longtitude, String latitude) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.zipcode = zipcode;
		this.latitude = latitude;
		this.longtitude = longtitude;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongtitude() {
		return longtitude;
	}
	public void setLongtitude(String longtitude) {
		this.longtitude = longtitude;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
