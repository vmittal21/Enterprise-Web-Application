package util;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoDBUtil {
	static MongoClient mongo;
	static MongoCollection<Document> myReviews;
	public static MongoCollection<Document> getConnection()
	{
		mongo = new MongoClient("localhost", 27017);
		
		MongoDatabase db = mongo.getDatabase("CustomerReviews");
		myReviews= db.getCollection("healthhubReviews");	
		return myReviews; 
	}
	
	public static void closeConnection() {
		mongo.close();
	}
}
