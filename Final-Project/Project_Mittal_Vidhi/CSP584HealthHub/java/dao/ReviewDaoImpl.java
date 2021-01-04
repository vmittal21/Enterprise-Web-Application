package dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

import bean.Bestseller;
import bean.Review;
import util.MongoDBUtil;

public class ReviewDaoImpl implements ReviewDao{
	private MongoCollection<Document> myReviews;
	static ProductDaoImpl productDaoImpl = new ProductDaoImpl();
	@Override
	public List<Review> selectReviewByProduct(String productName) {
		List<Review> reviews= new ArrayList<>();
		try
			{
				myReviews = MongoDBUtil.getConnection();
				FindIterable<Document> cursor = myReviews.find();
				for (Document obj:cursor)
				{
				
					if(obj.getString("productName").equals(productName))
					{	
						Review review =new Review(obj.getString("userName"),obj.getString("productName"), obj.getInteger("rating").toString(), obj.getString("headline"), obj.getString("text"));
						//add to review hashmap
						reviews.add(review);
					}
				}
			 	return reviews;
			}catch(Exception e){
				System.out.print(e.getMessage());
				reviews=null;
				return reviews;	
			}finally {
				MongoDBUtil.closeConnection();
			}
	}

	@Override
	public void insertReview(String userName, String name, int rating, String headline, String content,
			String zipcode, String city, Double productPrice) {
		// TODO Auto-generated method stub
		myReviews = MongoDBUtil.getConnection();
		Document document = new Document("title", "healthhubReviews").
		append("userName", userName).
		append("productName", name).
		append("headline", headline).
		append("rating", rating).
		append("text", content).
		append("zipcode", zipcode).
		append("city", city).
		append("productPrice", productPrice);
		myReviews.insertOne(document);
		MongoDBUtil.closeConnection();
	}
	public List<Bestseller> bestRating(){
		  List <Bestseller> Bestrate = new ArrayList <Bestseller> ();
		  try{
			  
			  myReviews = MongoDBUtil.getConnection();
			  int retlimit =5;
			  Document sort = new Document();
			  sort.put("rating",-1);
			  FindIterable<Document> cursor = myReviews.find().limit(retlimit).sort(sort);
			  for(Document obj : cursor) {
				  		  		   
				  String prodcutnm = obj.get("productName").toString();
				  String rating = obj.get("rating").toString();
			      Bestseller best = new Bestseller();
			      best.setProduct(productDaoImpl.getProductByName(prodcutnm));
			      best.setRating(Integer.parseInt(rating));
				  Bestrate.add(best);
			  }
		
		}catch (Exception e){ 
			System.out.println(e.getMessage());
		}finally {
			MongoDBUtil.closeConnection();
		}
		 return Bestrate;
}

	public Map<Integer,MongoCursor<Document>> findData(Map<String, String> params) {
		myReviews = MongoDBUtil.getConnection();
		Document query = new Document();
		MongoCursor<Document> res = null;
		Map<Integer, MongoCursor<Document>> map = new HashMap<>();
		if(params.containsKey("productPrice")) {
			double price = Double.parseDouble(params.get("productPrice"));
			if(params.get("priceCompare") != null) {
				String compare = params.get("priceCompare");
				if(compare.equals("GREAT_THAN")) {
					query.put("productPrice", new Document("$gt", price));
				}else {
					query.put("productPrice", new Document("$lt", price));
				}
			}else {
				query.put("productPrice", price);
			}
		}
		if(params.get("rating") != null) {
			int rating = Integer.parseInt(params.get("rating"));
			if(params.get("ratingCompare") != null) {
				String compare = params.get("ratingCompare");
				if(compare.equals("GREAT_THAN")) {
					query.put("rating", new Document("$gt", rating));
				}else {
					query.put("rating", new Document("$lt", rating));
				}
			}else {
				query.put("rating", rating);
			}
		}
		if(params.containsKey("productName")) {
			String productName = params.get("productName");
			if(!productName.equals("ALL_PRODUCTS")){
				query.put("productName", productName);
			}	
		}
		if(params.containsKey("city")) {
			String city = params.get("city");
			query.put("city", city);
		}
		if(params.containsKey("zipcode")) {
			String zipcode = params.get("zipcode");
			query.put("zipcode", zipcode);
		}
		
		if(params.get("groupBy") != null && !params.get("groupBy").equals("default")) {
			Document groupFields =  new Document("_id", 0);
//			groupfield="RetailerCity";
			if(params.get("groupBy").equals("city")) {
				groupFields.put("_id", "$city");
			}else {
				groupFields.put("_id", "$productName");
				
			}
			groupFields.put("count", new Document("$sum", 1));
			groupFields.put("productName", new Document("$push", "$productName"));
			groupFields.put("review", new Document("$push", "$text"));
			groupFields.put("rating", new Document("$push", "$rating"));
			groupFields.put("price", new Document("$push", "$productPrice"));
			groupFields.put("city", new Document("$push", "$city"));
			groupFields.put("zipcode", new Document("$push", "$zipcode"));

			Document group = new Document("$group", groupFields);
			
			Document projectFields = new Document("_id", 0);
			projectFields.put("value", "$_id");
			projectFields.put("count", "$count");
//			projectFields.put("Product", "$productName");
//			projectFields.put("User", "$userName");
//			projectFields.put("Reviews", "$review");
//			projectFields.put("Rating", "$rating");
//		    projectFields.put("price", "$price");
//		    projectFields.put("city", "$city");
//		    projectFields.put("zipCode", "$zipcode");

			Document project = new Document("$project", projectFields);
			Document match = new Document("$match", query);
			res =  myReviews.aggregate(Arrays.asList(match, group, project)).iterator();
			
			map.put(1, res);
			
		}else {
			res = myReviews.find(query).cursor();
//			System.out.println(query);
			map.put(2, res);
		}
		return map;
	
	}
}
