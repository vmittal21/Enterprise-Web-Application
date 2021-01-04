package service;


import java.util.List;
import java.util.Map;

import bean.Review;
import dao.ReviewDao;
import dao.ReviewDaoImpl;

public class MongoDBService {
	private static ReviewDao reviewDao = new ReviewDaoImpl();
	public List<Review> selectReviewByProduct(String productName){
		return reviewDao.selectReviewByProduct(productName);
	}
	public void insert(String username, String productName, int rating, String headline, String reviewContent,
			String zipcode, String city, Double productPrice) {
		reviewDao.insertReview(username, productName, rating, headline, reviewContent, zipcode, city, productPrice);
	}
}
