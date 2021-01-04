import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
@WebServlet("/Utilities")

/* 
	Utilities class contains class variables of type HttpServletRequest, PrintWriter,String and HttpSession.

	Utilities class has a constructor with  HttpServletRequest, PrintWriter variables.
	  
*/

public class Utilities extends HttpServlet{
	HttpServletRequest req;
	PrintWriter pw;
	String url;
	HttpSession session; 
	public Utilities(HttpServletRequest req, PrintWriter pw) {
		this.req = req;
		this.pw = pw;
		this.url = this.getFullURL();
		this.session = req.getSession(true);
	}


	/*  Printhtml Function gets the html file name as function Argument, 
		If the html file name is Header.html then It gets Username from session variables.
		Account ,Cart Information ang Logout Options are Displayed*/

	public void printHtml(String file) {
		String result = HtmlToString(file);
		//to print the right navigation in header of username cart and logout etc
		if (file == "Header.html") {
				result=result+"<div id='menu' style='float: right;'><ul>";
			if (session.getAttribute("username")!=null){
				String username = session.getAttribute("username").toString();
				username = Character.toUpperCase(username.charAt(0)) + username.substring(1);
				if(session.getAttribute("usertype").equals("manager"))
				{
					result = result + "<li><a href='ProductModify?button=Addproduct'><span class='glyphicon'>Addproduct</span></a></li>"
						+ "<li><a href='ProductModify?button=Updateproduct'><span class='glyphicon'>Updateproduct</span></a></li>"
						+"<li><a href='ProductModify?button=Deleteproduct'><span class='glyphicon'>Deleteproduct</span></a></li>"
						+"<li><a href='DataVisualization'><span class='glyphicon'>Trending</span></a></li>"
						+"<li><a href='Inventory'><span class='glyphicon'>Inventory</span></a></li>"
						+"<li><a href='SalesReport'><span class='glyphicon'>SalesReport</span></a></li>"
						+"<li><a href='DataAnalytics'><span class='glyphicon'>DataAnalytics</span></a></li>"
						+ "<li><a><span class='glyphicon'>Hello,"+username+"</span></a></li>"
						+ "<li><a href='Logout'><span class='glyphicon'>Logout</span></a></li>";
				}
				
				else if(session.getAttribute("usertype").equals("retailer")){
					result = result + "<li><a href='Registration'><span class='glyphicon'>Create Customer</span></a></li>"
						+ "<li><a href='ViewOrder'><span class='glyphicon'>ViewOrder</span></a></li>"
						+ "<li><a><span class='glyphicon'>Hello,"+username+"</span></a></li>"
						+ "<li><a href='Logout'><span class='glyphicon'>Logout</span></a></li>";
				}									  
		
				else																				 
				{
				result = result + "<li><a href='ViewOrder'><span class='glyphicon'>ViewOrder</span></a></li>"
						+ "<li><a><span class='glyphicon'>Hello,"+username+"</span></a></li>"
						+ "<li><a href='Account'><span class='glyphicon'>Account</span></a></li>"
						+ "<li><a href='Logout'><span class='glyphicon'>Logout</span></a></li>";
				}
			}
			else
				result = result +"<li><a href='ViewOrder'><span class='glyphicon'>View Order</span></a></li>"+ "<li><a href='Login'><span class='glyphicon'>Login</span></a></li>";
				result = result +"<li><a href='Cart'><span class='glyphicon'>Cart("+CartCount()+")</span></a></li></ul></div></div><div id='page'>";
				pw.print(result);
		} else
				pw.print(result);
	}
	
	/*  getFullURL Function - Reconstructs the URL user request  */

	public String getFullURL() {
		String scheme = req.getScheme();
		String serverName = req.getServerName();
		int serverPort = req.getServerPort();
		String contextPath = req.getContextPath();
		StringBuffer url = new StringBuffer();
		url.append(scheme).append("://").append(serverName);

		if ((serverPort != 80) && (serverPort != 443)) {
			url.append(":").append(serverPort);
		}
		url.append(contextPath);
		url.append("/");
		return url.toString();
	}

	/*  HtmlToString - Gets the Html file and Converts into String and returns the String.*/
	public String HtmlToString(String file) {
		String result = null;
		try {
			String webPage = url + file;
			URL url = new URL(webPage);
			URLConnection urlConnection = url.openConnection();
			InputStream is = urlConnection.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);

			int numCharsRead;
			char[] charArray = new char[1024];
			StringBuffer sb = new StringBuffer();
			while ((numCharsRead = isr.read(charArray)) > 0) {
				sb.append(charArray, 0, numCharsRead);
			}
			result = sb.toString();
		} 
		catch (Exception e) {
		}
		return result;
	} 

	/*  logout Function removes the username , usertype attributes from the session variable*/

	public void logout(){
		session.removeAttribute("username");
		session.removeAttribute("usertype");
	}
	
	/*  logout Function checks whether the user is loggedIn or Not*/

	public boolean isLoggedin(){
		if (session.getAttribute("username")==null)
			return false;
		return true;
	}

	/*  username Function returns the username from the session variable.*/
	
	public String username(){
		if (session.getAttribute("username")!=null)
			return session.getAttribute("username").toString();
		return null;
	}
	
	/*  usertype Function returns the usertype from the session variable.*/
	public String usertype(){
		System.out.println("User type: "+session.getAttribute("usertype"));
		if (session.getAttribute("usertype")!=null)
			return session.getAttribute("usertype").toString();
		return null;
	}
	
	/*  getUser Function checks the user is a customer or retailer or manager and returns the user class variable.*/
	public User getUser(){
		String usertype = usertype();
		HashMap<String, User> hm=new HashMap<String, User>();
		try
		{		    
			hm=MySqlDataStoreUtilities.selectUser();
		}
		catch(Exception e)
		{
		}	
		User user = hm.get(username());
		return user;
	}
	
	/*  getCustomerOrders Function gets  the Orders for the user*/
	public ArrayList<OrderItem> getCustomerOrders(){
		ArrayList<OrderItem> order = new ArrayList<OrderItem>(); 
		if(OrdersHashMap.orders.containsKey(username()))
			order= OrdersHashMap.orders.get(username());
		return order;
	}

	/*  getOrdersPaymentSize Function gets  the size of OrderPayment */
	public int getOrderPaymentSize(){
		HashMap<Integer, ArrayList<OrderPayment>> orderPayments = new HashMap<Integer, ArrayList<OrderPayment>>();
		int size = 0;
			try
			{ 
				orderPayments = MySqlDataStoreUtilities.selectOrder();
			}
			catch(Exception e)
			{
			
			}

			for(Map.Entry<Integer, ArrayList<OrderPayment>> entry : orderPayments.entrySet()){
					 size=entry.getKey();
					 
			}
			return size;		
	}

	/*  CartCount Function gets  the size of User Orders*/
	public int CartCount(){
		if(isLoggedin())
		return getCustomerOrders().size();
		return 0;
	}
	
	/* StoreProduct Function stores the Purchased product in Orders HashMap according to the User Names.*/

	public void storeProduct(String name,String type,String maker, String acc){
		if(!OrdersHashMap.orders.containsKey(username())){	
			ArrayList<OrderItem> arr = new ArrayList<OrderItem>();
			OrdersHashMap.orders.put(username(), arr);
		}
		ArrayList<OrderItem> orderItems = OrdersHashMap.orders.get(username());
		HashMap<String,Console> allconsoles = new HashMap<String,Console> ();
		HashMap<String,Accessory> allaccessories=new HashMap<String,Accessory>();	
		HashMap<String,Tablet> alltablets = new HashMap<String,Tablet> ();
		HashMap<String,Game> allgames = new HashMap<String,Game> ();
		HashMap<String,VoiceAssistant> allVoiceAssistants = new HashMap<String,VoiceAssistant> ();
		HashMap<String,Laptop> allLaptops = new HashMap<String,Laptop> ();
		HashMap<String,FitnessWatch> allFitnessWatches = new HashMap<String,FitnessWatch> ();
		HashMap<String,SmartWatch> allSmartWatches = new HashMap<String,SmartWatch> ();
		HashMap<String,HeadPhone> allHeadPhones = new HashMap<String,HeadPhone> ();
		HashMap<String,SoundSystem> allSoundSystems = new HashMap<String,SoundSystem> ();
		HashMap<String,VirtualReality> allVirtualRealities = new HashMap<String,VirtualReality> ();
		HashMap<String,PetTracker> allPetTrackers = new HashMap<String,PetTracker> ();
		
		if(type.equals("consoles")){
			Console console;
			try{										 
				allconsoles = MySqlDataStoreUtilities.getConsoles();
			}
			catch(Exception e){
				
			}
			
			console = allconsoles.get(name);
			System.out.println(console.getName());
			OrderItem orderitem = new OrderItem(console.getName(), console.getPrice(), console.getImage(), console.getRetailer());
			orderItems.add(orderitem);
		}
		System.out.println("\n\nFinished Store Product");
		if(type.equals("tablets")){
			Tablet tablet = null;
			try{
			alltablets = MySqlDataStoreUtilities.getTablets();
			}
			catch(Exception e){
				
			}									 
			tablet = alltablets.get(name);
			System.out.println(tablet.getName());
			OrderItem orderitem = new OrderItem(tablet.getName(), tablet.getPrice(), tablet.getImage(), tablet.getRetailer());
			orderItems.add(orderitem);
		}
		if(type.equals("soundSystems")){	
			SoundSystem soundsystem = null;
			try{
			allSoundSystems = MySqlDataStoreUtilities.getSoundSystems();
			}
			catch(Exception e){
				
			}									 
			soundsystem = allSoundSystems.get(name);
			OrderItem orderitem = new OrderItem(soundsystem.getName(), soundsystem.getPrice(), soundsystem.getImage(), soundsystem.getRetailer());
			orderItems.add(orderitem);
		}
		if(type.equals("voiceAssistants")){
			VoiceAssistant voiceAssistant = null;
			
			try{
			allVoiceAssistants = MySqlDataStoreUtilities.getVoiceAssistants();
			}
			catch(Exception e){
				
			}									 
			voiceAssistant = allVoiceAssistants.get(name);
			OrderItem orderitem = new OrderItem(voiceAssistant.getName(), voiceAssistant.getPrice(), voiceAssistant.getImage(), voiceAssistant.getRetailer());
			orderItems.add(orderitem);
		}
		if(type.equals("smartWatches")){
			SmartWatch smartWatch = null;
			try{
			allSmartWatches = MySqlDataStoreUtilities.getSmartWatches();
			}
			catch(Exception e){
				
			}									 
			smartWatch = allSmartWatches.get(name);
			OrderItem orderitem = new OrderItem(smartWatch.getName(), smartWatch.getPrice(), smartWatch.getImage(), smartWatch.getRetailer());
			orderItems.add(orderitem);
		}
		if(type.equals("fitnessWatches")){
			FitnessWatch fitnessWatch = null;
			
			try{
			allFitnessWatches = MySqlDataStoreUtilities.getFitnessWatches();
			}
			catch(Exception e){
				
			}									 
			fitnessWatch = allFitnessWatches.get(name);
			OrderItem orderitem = new OrderItem(fitnessWatch.getName(), fitnessWatch.getPrice(), fitnessWatch.getImage(), fitnessWatch.getRetailer());
			orderItems.add(orderitem);
		}
		if(type.equals("headPhones")){
			HeadPhone headPhone = null;
			SoundSystem soundSystem = null;
			try{
			allHeadPhones = MySqlDataStoreUtilities.getHeadPhones();
			}
			catch(Exception e){
				
			}
			headPhone = allHeadPhones.get(name);
			OrderItem orderitem = new OrderItem(headPhone.getName(), headPhone.getPrice(), headPhone.getImage(), headPhone.getRetailer());
			orderItems.add(orderitem);
		}
		if(type.equals("virtualRealities")){
			VirtualReality virtualReality = null;
			try{
			allVirtualRealities = MySqlDataStoreUtilities.getVirtualRealities();
			}catch(Exception e){
				
			}									 
			virtualReality = allVirtualRealities.get(name);
			OrderItem orderitem = new OrderItem(virtualReality.getName(), virtualReality.getPrice(), virtualReality.getImage(), virtualReality.getRetailer());
			orderItems.add(orderitem);
		}
		if(type.equals("laptops")){
			Laptop laptop = null;
			try{
			allLaptops = MySqlDataStoreUtilities.getLaptops();
			}
			catch(Exception e){
				
			}
			laptop = allLaptops.get(name);
			OrderItem orderitem = new OrderItem(laptop.getName(), laptop.getPrice(), laptop.getImage(), laptop.getRetailer());
			orderItems.add(orderitem);
		}
		if(type.equals("petTrackers")){
			PetTracker petTracker = null;
			try{
			allPetTrackers = MySqlDataStoreUtilities.getPetTrackers();
			}
			catch(Exception e){
			}									 
			petTracker = allPetTrackers.get(name);
			OrderItem orderitem = new OrderItem(petTracker.getName(), petTracker.getPrice(), petTracker.getImage(), petTracker.getRetailer());
			orderItems.add(orderitem);
		} 
	}
	// store the payment details for orders
	
	public void storePayment(int orderId,
		String orderName,double orderPrice,String userAddress,String creditCardNo){
		HashMap<Integer, ArrayList<OrderPayment>> orderPayments= new HashMap<Integer, ArrayList<OrderPayment>>();
		try
		{
			orderPayments =  MySqlDataStoreUtilities.selectOrder();
		}
		catch(Exception e)
		{
		
		}
		if(orderPayments==null)
		{
			orderPayments = new HashMap<Integer, ArrayList<OrderPayment>>();
		}
		// if there exist order id already add it into same list for order id or create a new record with order id
		
		if(!orderPayments.containsKey(orderId)){	
			ArrayList<OrderPayment> arr = new ArrayList<OrderPayment>();
			orderPayments.put(orderId, arr);
		}
		ArrayList<OrderPayment> listOrderPayment = orderPayments.get(orderId);		
		OrderPayment orderpayment = new OrderPayment(orderId,username(),orderName,orderPrice,userAddress,creditCardNo);
		listOrderPayment.add(orderpayment);	
			
			// add order details into file

		try
		{	
			if(session.getAttribute("usertype").equals("retailer"))
 
			{
				MySqlDataStoreUtilities.insertOrder(orderId,"xyz",orderName,orderPrice,userAddress,creditCardNo);
			}else
			
				{MySqlDataStoreUtilities.insertOrder(orderId,username(),orderName,orderPrice,userAddress,creditCardNo);}
		}
		catch(Exception e)
		{
			System.out.println("inside exception file not written properly");
		}	
	}

	
	/* getConsoles Functions returns the Hashmap with all consoles in the store.*/

	public HashMap<String, Console> getConsoles(){
			HashMap<String, Console> hm = new HashMap<String, Console>();
			hm.putAll(SaxParserDataStore.consoles);
			return hm;
	}
	
	/* getGames Functions returns the  Hashmap with all Games in the store.*/

	public HashMap<String, SoundSystem> getSoundSystems(){
			HashMap<String, SoundSystem> hm = new HashMap<String, SoundSystem>();
			hm.putAll(SaxParserDataStore.soundSystems);
			return hm;
	}
	
	public HashMap<String, SmartWatch> getSmartWatches(){
			HashMap<String, SmartWatch> hm = new HashMap<String, SmartWatch>();
			hm.putAll(SaxParserDataStore.smartWatches);
			return hm;
	}
	
	public HashMap<String, VirtualReality> getVirtualRealities(){
			HashMap<String, VirtualReality> hm = new HashMap<String, VirtualReality>();
			hm.putAll(SaxParserDataStore.virtualRealities);
			return hm;
	}
	
	public HashMap<String, PetTracker> getPetTrackers(){
			HashMap<String, PetTracker> hm = new HashMap<String, PetTracker>();
			hm.putAll(SaxParserDataStore.petTrackers);
			return hm;
	}
	
	public HashMap<String, FitnessWatch> getFitnessWatches(){
			HashMap<String, FitnessWatch> hm = new HashMap<String, FitnessWatch>();
			hm.putAll(SaxParserDataStore.fitnessWatches);
			return hm;
	}
	
	public HashMap<String, HeadPhone> getHeadPhones(){
			HashMap<String, HeadPhone> hm = new HashMap<String, HeadPhone>();
			hm.putAll(SaxParserDataStore.headPhones);
			return hm;
	}
	
	public HashMap<String, Laptop> getLaptops(){
			HashMap<String, Laptop> hm = new HashMap<String, Laptop>();
			hm.putAll(SaxParserDataStore.laptops);
			return hm;
	}
	
	public HashMap<String, VoiceAssistant> getVoiceAssistants(){
			HashMap<String, VoiceAssistant> hm = new HashMap<String, VoiceAssistant>();
			hm.putAll(SaxParserDataStore.voiceAssistants);
			return hm;
	}
	
	
	
	/* getTablets Functions returns the Hashmap with all Tablet in the store.*/

	public HashMap<String, Tablet> getTablets(){
			HashMap<String, Tablet> hm = new HashMap<String, Tablet>();
			hm.putAll(SaxParserDataStore.tablets);
			return hm;
	}
	
	/* getProducts Functions returns the Arraylist of consoles in the store.*/

	public ArrayList<String> getProducts(){
		ArrayList<String> ar = new ArrayList<String>();
		for(Map.Entry<String, Console> entry : getConsoles().entrySet()){			
			ar.add(entry.getValue().getName());
		}
		return ar;
	}
	
	/* getProducts Functions returns the Arraylist of games in the store.*/

	public ArrayList<String> getProductsSoundSystems(){		
		ArrayList<String> ar = new ArrayList<String>();
		for(Map.Entry<String, SoundSystem> entry : getSoundSystems().entrySet()){
			ar.add(entry.getValue().getName());
		}
		return ar;
	}
	
	public ArrayList<String> getProductsLaptops(){		
		ArrayList<String> ar = new ArrayList<String>();
		for(Map.Entry<String, Laptop> entry : getLaptops().entrySet()){
			ar.add(entry.getValue().getName());
		}
		return ar;
	}
	
	public ArrayList<String> getProductsVirtualRealities(){		
		ArrayList<String> ar = new ArrayList<String>();
		for(Map.Entry<String, VirtualReality> entry : getVirtualRealities().entrySet()){
			ar.add(entry.getValue().getName());
		}
		return ar;
	}
	
	public ArrayList<String> getProductsPetTrackers(){		
		ArrayList<String> ar = new ArrayList<String>();
		for(Map.Entry<String, PetTracker> entry : getPetTrackers().entrySet()){
			ar.add(entry.getValue().getName());
		}
		return ar;
	}
	
	public ArrayList<String> getProductsSmartWatches(){		
		ArrayList<String> ar = new ArrayList<String>();
		for(Map.Entry<String, SmartWatch> entry : getSmartWatches().entrySet()){
			ar.add(entry.getValue().getName());
		}
		return ar;
	}
	
	public ArrayList<String> getProductsFitnessWatches(){		
		ArrayList<String> ar = new ArrayList<String>();
		for(Map.Entry<String, FitnessWatch> entry : getFitnessWatches().entrySet()){
			ar.add(entry.getValue().getName());
		}
		return ar;
	}
	
	public ArrayList<String> getProductsHeadPhonees(){		
		ArrayList<String> ar = new ArrayList<String>();
		for(Map.Entry<String, HeadPhone> entry : getHeadPhones().entrySet()){
			ar.add(entry.getValue().getName());
		}
		return ar;
	}
	
	public ArrayList<String> getProductsVoiceAssistants(){		
		ArrayList<String> ar = new ArrayList<String>();
		for(Map.Entry<String, VoiceAssistant> entry : getVoiceAssistants().entrySet()){
			ar.add(entry.getValue().getName());
		}
		return ar;
	}
	
	public String storeReview(String productname,String producttype,String productmaker,String reviewrating,String reviewdate,String  reviewtext,String reatilerpin,String price,String city){
	    String message=MongoDBDataStoreUtilities.insertReview(productname,username(),producttype,productmaker,reviewrating,reviewdate,reviewtext,reatilerpin,price,city); 
	if(!message.equals("Successfull"))
		{ return "UnSuccessfull";
		}
		else
		{
		HashMap<String, ArrayList<Review>> reviews= new HashMap<String, ArrayList<Review>>();
		try
		{
			reviews=MongoDBDataStoreUtilities.selectReview();
		}
		catch(Exception e)
		{
			
		}
		if(reviews==null)
		{
			reviews = new HashMap<String, ArrayList<Review>>();
		}
			// if there exist product review already add it into same list for productname or create a new record with product name
			
		if(!reviews.containsKey(productname)){	
			ArrayList<Review> arr = new ArrayList<Review>();
			reviews.put(productname, arr);
		}
		ArrayList<Review> listReview = reviews.get(productname);		
		Review review = new Review(productname,username(),producttype,productmaker,reviewrating,reviewdate,reviewtext,reatilerpin,price,city);
		listReview.add(review);	
			
			// add Reviews into database
		
		return "Successfull";	
		}
	}
	
	/* getProducts Functions returns the Arraylist of Tablets in the store.*/

	public ArrayList<String> getProductsTablets(){		
		ArrayList<String> ar = new ArrayList<String>();
		for(Map.Entry<String, Tablet> entry : getTablets().entrySet()){
			ar.add(entry.getValue().getName());
		}
		return ar;
	}
}
