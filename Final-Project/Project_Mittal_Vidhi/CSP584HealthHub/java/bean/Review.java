package bean;

import java.io.*;

/* 
	Review class contains class variables username,productname,reviewtext,reviewdate,reviewrating

	Review class has a constructor with Arguments username,productname,reviewtext,reviewdate,reviewrating
	  
	Review class contains getters and setters for username,productname,reviewtext,reviewdate,reviewrating
*/

public class Review implements Serializable{
	private String productName;
	private String productType;
	private String price;
	private String onSale;
	private String rebate;
	private String productMaker;
	
	
	private String retailerName;
	private String retailerpin;
	private String retailercity;
	private String retailerState;
	
	private String userName;
	private String reviewRating;
	private String headline;
	private String reviewDate;
	private String reviewText;
	private int count;
	
	
	public Review(String userName, String productName, String rating, String headline, String reviewContent) {
		this.setUserName(userName);
		this.productName = productName;
		this.reviewRating = rating;
		this.setHeadline(headline);
		this.reviewText = reviewContent;
	}

	public String getOnSale() {
		return onSale;
	}

	public void setOnSale(String onSale) {
		this.onSale = onSale;
	}

	public String getRebate() {
		return rebate;
	}

	public void setRebate(String rebate) {
		this.rebate = rebate;
	}

	public String getRetailerName() {
		return retailerName;
	}

	public void setRetailerName(String retailerName) {
		this.retailerName = retailerName;
	}

	public String getRetailerpin() {
		return retailerpin;
	}

	public void setRetailerpin(String retailerpin) {
		this.retailerpin = retailerpin;
	}

	public String getRetailercity() {
		return retailercity;
	}

	public void setRetailercity(String retailercity) {
		this.retailercity = retailercity;
	}

	public String getRetailerState() {
		return retailerState;
	}

	public void setRetailerState(String retailerState) {
		this.retailerState = retailerState;
	}



	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getProductMaker() {
		return productMaker;
	}

	public void setProductMaker(String productMaker) {
		this.productMaker = productMaker;
	}

	public String getReviewRating() {
		return reviewRating;
	}

	public String getReviewText() {
		return reviewText;
	}
	public void setReviewText(String reviewText) {
		this.reviewText = reviewText;
	}

	public void setReviewRating(String reviewRating) {
		this.reviewRating = reviewRating;
	}
	public String getReviewDate() {
		return reviewDate;
	}

	public void setReviewDate(String reviewDate) {
		this.reviewDate = reviewDate;
	}
    
		public String getRetailerPin() {
		return retailerpin;
	}

	public void setRetailerPin(String retailerpin) {
		this.retailerpin = retailerpin;
	}
			public String getRetailerCity() {
		return retailercity;
	}

	public void setRetailerCity(String retailercity) {
		this.retailercity = retailercity;
	}
	
			public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getHeadline() {
		return headline;
	}

	public void setHeadline(String headline) {
		this.headline = headline;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
