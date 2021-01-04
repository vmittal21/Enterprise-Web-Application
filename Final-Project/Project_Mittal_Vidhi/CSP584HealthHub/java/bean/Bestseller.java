package bean;

public class Bestseller {
	private Product product;
	private int numSold;
	private double rating;
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	
	public int getNumSold() {
		return numSold;
	}
	public void setNumSold(int numSold) {
		this.numSold = numSold;
	}
	
}
