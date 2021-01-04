package bean;


public class Store {
	private String id;
	private String name;
	private String address;
	private String zipcode;
	private String latitude;
	private String longtitude;
	private int reviewCount;
	private int transactionCount;
	private int likedCount;
	private int dislikedCount;
	private int pickupCount;
	public int getReviewCount() {
		return reviewCount;
	}

	public void setReviewCount(int reviewCount) {
		this.reviewCount = reviewCount;
	}

	public int getTransactionCount() {
		return transactionCount;
	}

	public void setTransactionCount(int transactionCount) {
		this.transactionCount = transactionCount;
	}

	public int getLikedCount() {
		return likedCount;
	}

	public void setLikedCount(int likedCount) {
		this.likedCount = likedCount;
	}

	public int getDislikedCount() {
		return dislikedCount;
	}

	public void setDislikedCount(int dislikedCount) {
		this.dislikedCount = dislikedCount;
	}

	public int getPickupCount() {
		return pickupCount;
	}

	public void setPickupCount(int pickupCount) {
		this.pickupCount = pickupCount;
	}

	public Store(String id, String name, String address, String zipcode, String latitude, String longtitude) {
		this.id=id;
		this.name=name;
		this.address=address;
		this.zipcode=zipcode;
		this.setLatitude(latitude);
		this.setLongtitude(longtitude);
	}
	
	public Store(String id, String name, String address, String zipcode) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.zipcode = zipcode;
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
	@Override
	public String toString() {
		return this.name + "\n" + this.address + "\n" + this.zipcode;
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
}

