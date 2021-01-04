package bean;


public class Product {
	public Product(String id, String name, double price, String image, String manufacturer, String condition,
			double discount, String catagory, int inventory) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.image = image;
		this.manufacturer = manufacturer;
		this.condition = condition;
		this.discount = discount;
		this.catagory = catagory;
		this.inventory = inventory;
	}
	private String id;
	private String name;
	private double price;
	private String image;
	private String manufacturer;
	private String condition;
	private double discount;
	private String catagory;
	private int inventory;
	public Product() {
		
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	public String getCatagory() {
		return catagory;
	}
	public void setCatagory(String catagory) {
		this.catagory = catagory;
	}
	public int getInventory() {
		return inventory;
	}
	public void setInventory(int inventory) {
		this.inventory = inventory;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
}
