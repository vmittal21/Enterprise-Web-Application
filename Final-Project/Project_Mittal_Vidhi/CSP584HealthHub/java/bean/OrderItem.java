package bean;

import java.io.Serializable;

public class OrderItem implements Serializable{
	private String productId;
	private String name;
	private double price;
	private String image;
	private double discount;
	private String brand;
	private String purchaseDate;
	private String shippingDate;
	private String pickupStore;
	private int quantity;
	private String type;
	private double totalPrice;
	private int inventory;
	public OrderItem(String productId, String name, double price, String image, String brand, int inventory){
		this.productId = productId;
		this.name=name;
		this.price=price;
		this.image=image;
		this.setBrand(brand);
		this.totalPrice = price;
		this.setInventory(inventory);
		quantity = 1;
	}
	public OrderItem(String name, String brand, double totalPrice, int quantity, String purchaseDate) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.brand = brand;
		this.totalPrice = totalPrice;
		this.quantity = quantity;
		this.purchaseDate = purchaseDate;
	}
	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
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

	public String getPickupStore() {
		return pickupStore;
	}
	public void setPickupStore(String pickupStore) {
		this.pickupStore = pickupStore;
	}
	public String getShippingDate() {
		return shippingDate;
	}
	public void setShippingDate(String shippingDate) {
		this.shippingDate = shippingDate;
	}
	public String getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(String purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getDiscount() {
		return this.discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public int getInventory() {
		return inventory;
	}
	public void setInventory(int inventory) {
		this.inventory = inventory;
	}
}
