package dao;

import java.util.List;
import java.util.Map;

import bean.Review;

public interface ReviewDao {
	public List<Review> selectReviewByProduct(String productName);
	public void insertReview(String username, String productName, int rating, String headline, String reviewContent,
			String zipcode, String city, Double productPrice);
	
}
