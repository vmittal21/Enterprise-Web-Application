import java.sql.*;
import java.util.*;
import java.util.Date;
import java.text.SimpleDateFormat;

                	
public class MySqlDataStoreUtilities
{
static Connection conn = null;
static String message;
public static String getConnection()
{

	try
	{
	Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/exampledatabase","root","pass");							
	message="Successfull";
	return message;
	}
	catch(SQLException e)
	{ 
		System.out.println(e);
		message="unsuccessful";
		     return message;
	}
	catch(Exception e)
	{
		message=e.getMessage();
		return message;
	}
}

public static void Insertproducts()
{
	try
	{		
		getConnection();
			
		String truncatetableacc = "delete from Product_accessories;";
		PreparedStatement pstt = conn.prepareStatement(truncatetableacc);
		pstt.executeUpdate();
		
		String truncatetableprod = "delete from  Productdetails;";
		PreparedStatement psttprod = conn.prepareStatement(truncatetableprod);
		psttprod.executeUpdate();
				
		String iPQ = "INSERT INTO  Productdetails(ProductType,Id,productName,productPrice,productImage,productManufacturer,productCondition,productDiscount,Inventory)" +
		"VALUES (?,?,?,?,?,?,?,?,?);";
		for(Map.Entry<String,Accessory> entry : SaxParserDataStore.accessories.entrySet())
		{   
			String name = "accessories";
	        Accessory acc = entry.getValue();
			
			PreparedStatement pst = conn.prepareStatement(iPQ);
			pst.setString(1,name);
			pst.setString(2,acc.getId());
			pst.setString(3,acc.getName());
			pst.setDouble(4,acc.getPrice());
			pst.setString(5,acc.getImage());
			pst.setString(6,acc.getRetailer());
			pst.setString(7,acc.getCondition());
			pst.setDouble(8,acc.getDiscount());
			pst.setInt(9,acc.getInventory());
			
			pst.executeUpdate();
			
		}
		for(Map.Entry<String,Console> entry : SaxParserDataStore.consoles.entrySet())
		{   
	        Console con = entry.getValue();
			String name = "consoles";
					
			PreparedStatement pst = conn.prepareStatement(iPQ);
			pst.setString(1,name);
			pst.setString(2,con.getId());
			pst.setString(3,con.getName());
			pst.setDouble(4,con.getPrice());
			pst.setString(5,con.getImage());
			pst.setString(6,con.getRetailer());
			pst.setString(7,con.getCondition());
			pst.setDouble(8,con.getDiscount());
			pst.setInt(9,con.getInventory());
			
			pst.executeUpdate();
		
		}
		for(Map.Entry<String,Tablet> entry : SaxParserDataStore.tablets.entrySet())
		{   
			String name = "tablets";
	        Tablet tablet = entry.getValue();
			
			PreparedStatement pst = conn.prepareStatement(iPQ);
			pst.setString(1,name);
			pst.setString(2,tablet.getId());
			pst.setString(3,tablet.getName());
			pst.setDouble(4,tablet.getPrice());
			pst.setString(5,tablet.getImage());
			pst.setString(6,tablet.getRetailer());
			pst.setString(7,tablet.getCondition());
			pst.setDouble(8,tablet.getDiscount());
			pst.setInt(9,tablet.getInventory());
			
			pst.executeUpdate();
		}
		for(Map.Entry<String,VoiceAssistant> entry : SaxParserDataStore.voiceAssistants.entrySet())
		{   
			String name = "voiceAssistants";
	        VoiceAssistant voiceAssistant = entry.getValue();
			
			PreparedStatement pst = conn.prepareStatement(iPQ);
			pst.setString(1,name);
			pst.setString(2,voiceAssistant.getId());
			pst.setString(3,voiceAssistant.getName());
			pst.setDouble(4,voiceAssistant.getPrice());
			pst.setString(5,voiceAssistant.getImage());
			pst.setString(6,voiceAssistant.getRetailer());
			pst.setString(7,voiceAssistant.getCondition());
			pst.setDouble(8,voiceAssistant.getDiscount());
			pst.setInt(9,voiceAssistant.getInventory());
			
			pst.executeUpdate();
			
			
		}
		for(Map.Entry<String,Laptop> entry : SaxParserDataStore.laptops.entrySet())
		{   
			String name = "laptops";
	        Laptop laptop = entry.getValue();
			
			PreparedStatement pst = conn.prepareStatement(iPQ);
			pst.setString(1,name);
			pst.setString(2,laptop.getId());
			pst.setString(3,laptop.getName());
			pst.setDouble(4,laptop.getPrice());
			pst.setString(5,laptop.getImage());
			pst.setString(6,laptop.getRetailer());
			pst.setString(7,laptop.getCondition());
			pst.setDouble(8,laptop.getDiscount());
			pst.setInt(9,laptop.getInventory());
			
			pst.executeUpdate();
			
			
		}
		for(Map.Entry<String,HeadPhone> entry : SaxParserDataStore.headPhones.entrySet())
		{   
			String name = "headPhones";
	        HeadPhone headPhone = entry.getValue();
			
			PreparedStatement pst = conn.prepareStatement(iPQ);
			pst.setString(1,name);
			pst.setString(2,headPhone.getId());
			pst.setString(3,headPhone.getName());
			pst.setDouble(4,headPhone.getPrice());
			pst.setString(5,headPhone.getImage());
			pst.setString(6,headPhone.getRetailer());
			pst.setString(7,headPhone.getCondition());
			pst.setDouble(8,headPhone.getDiscount());
			pst.setInt(9,headPhone.getInventory());
			
			pst.executeUpdate();
		}
		for(Map.Entry<String,PetTracker> entry : SaxParserDataStore.petTrackers.entrySet())
		{   
			String name = "petTrackers";
	        PetTracker petTracker = entry.getValue();
			
			PreparedStatement pst = conn.prepareStatement(iPQ);
			pst.setString(1,name);
			pst.setString(2,petTracker.getId());
			pst.setString(3,petTracker.getName());
			pst.setDouble(4,petTracker.getPrice());
			pst.setString(5,petTracker.getImage());
			pst.setString(6,petTracker.getRetailer());
			pst.setString(7,petTracker.getCondition());
			pst.setDouble(8,petTracker.getDiscount());
			pst.setInt(9,petTracker.getInventory());
			
			pst.executeUpdate();
			
			
		}
		for(Map.Entry<String,VirtualReality> entry : SaxParserDataStore.virtualRealities.entrySet())
		{   
			String name = "virtualRealities";
	        VirtualReality virtualReality = entry.getValue();
			
			PreparedStatement pst = conn.prepareStatement(iPQ);
			pst.setString(1,name);
			pst.setString(2,virtualReality.getId());
			pst.setString(3,virtualReality.getName());
			pst.setDouble(4,virtualReality.getPrice());
			pst.setString(5,virtualReality.getImage());
			pst.setString(6,virtualReality.getRetailer());
			pst.setString(7,virtualReality.getCondition());
			pst.setDouble(8,virtualReality.getDiscount());
			pst.setInt(9,virtualReality.getInventory());
			
			pst.executeUpdate();
			
			
		}
		for(Map.Entry<String,SmartWatch> entry : SaxParserDataStore.smartWatches.entrySet())
		{   
			String name = "smartWatches";
	        SmartWatch smartWatch = entry.getValue();
			
			PreparedStatement pst = conn.prepareStatement(iPQ);
			pst.setString(1,name);
			pst.setString(2,smartWatch.getId());
			pst.setString(3,smartWatch.getName());
			pst.setDouble(4,smartWatch.getPrice());
			pst.setString(5,smartWatch.getImage());
			pst.setString(6,smartWatch.getRetailer());
			pst.setString(7,smartWatch.getCondition());
			pst.setDouble(8,smartWatch.getDiscount());
			pst.setInt(9,smartWatch.getInventory());
			
			pst.executeUpdate();
			
			
		}
		for(Map.Entry<String,FitnessWatch> entry : SaxParserDataStore.fitnessWatches.entrySet())
		{   
			String name = "fitnessWatches";
	        FitnessWatch fitnessWatches = entry.getValue();
			
			PreparedStatement pst = conn.prepareStatement(iPQ);
			pst.setString(1,name);
			pst.setString(2,fitnessWatches.getId());
			pst.setString(3,fitnessWatches.getName());
			pst.setDouble(4,fitnessWatches.getPrice());
			pst.setString(5,fitnessWatches.getImage());
			pst.setString(6,fitnessWatches.getRetailer());
			pst.setString(7,fitnessWatches.getCondition());
			pst.setDouble(8,fitnessWatches.getDiscount());
			pst.setInt(9,fitnessWatches.getInventory());
			
			pst.executeUpdate();
		}
			
			
	}catch(Exception e)
	{
		e.printStackTrace();
	}
}

public static HashMap<String,Console> getConsoles()
{	
	HashMap<String,Console> hm=new HashMap<String,Console>();
	try 
	{
		getConnection();
		
		String selectConsole="select * from  productdetails where ProductType=?";
		PreparedStatement pst = conn.prepareStatement(selectConsole);
		pst.setString(1,"consoles");
		ResultSet rs = pst.executeQuery();
	
		while(rs.next())
		{		
				System.out.println("Query results"+rs.getString("productName")+rs.getDouble("productPrice")+rs.getString("productImage")+rs.getString("productManufacturer")+rs.getString("productCondition")+rs.getDouble("productDiscount"));
				Console con = new Console(rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productManufacturer"),rs.getString("productCondition"),rs.getDouble("productDiscount"));
				System.out.println("ID: "+rs.getString("Id"));
				hm.put(rs.getString("Id"), con);
				
				con.setId(rs.getString("Id"));
				
				try
				{
					String selectaccessory = "Select * from product_accessories where productName=?";
					PreparedStatement pstacc = conn.prepareStatement(selectaccessory);
					pstacc.setString(1,rs.getString("Id"));
					ResultSet rsacc = pstacc.executeQuery();
					
					HashMap<String,String> acchashmap = new HashMap<String,String>();
					while(rsacc.next())
					{    
						if (rsacc.getString("accessoriesName") != null){
						
						 acchashmap.put(rsacc.getString("accessoriesName"),rsacc.getString("accessoriesName"));
						 con.setAccessories(acchashmap);
						}
							 
					}				
				}catch(Exception e)
				{
						e.printStackTrace();
				}
		}
	}
	catch(Exception e)
	{
	}
	return hm;			
}

public static HashMap<String,VoiceAssistant> getVoiceAssistants()
{	
	HashMap<String,VoiceAssistant> hm=new HashMap<String,VoiceAssistant>();
	try 
	{
		getConnection();
		
		String selectVoiceAssistant="select * from  productdetails where ProductType=?";
		PreparedStatement pst = conn.prepareStatement(selectVoiceAssistant);
		pst.setString(1,"voiceAssistants");
		ResultSet rs = pst.executeQuery();
	
		while(rs.next())
		{	VoiceAssistant voice = new VoiceAssistant(rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productManufacturer"),rs.getString("productCondition"),rs.getDouble("productDiscount"));
				hm.put(rs.getString("Id"), voice);
				voice.setId(rs.getString("Id"));

		}
	}
	catch(Exception e)
	{
	}
	return hm;			
}

public static HashMap<String,Laptop> getLaptops()
{	
	HashMap<String,Laptop> hm=new HashMap<String,Laptop>();
	try 
	{
		getConnection();
		
		String selectLaptop="select * from  productdetails where ProductType=?";
		PreparedStatement pst = conn.prepareStatement(selectLaptop);
		pst.setString(1,"laptops");
		ResultSet rs = pst.executeQuery();
	
		while(rs.next())
		{	Laptop lap = new Laptop(rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productManufacturer"),rs.getString("productCondition"),rs.getDouble("productDiscount"));
				hm.put(rs.getString("Id"), lap);
				lap.setId(rs.getString("Id"));

		}
	}
	catch(Exception e)
	{
	}
	return hm;			
}

public static HashMap<String,FitnessWatch> getFitnessWatches()
{	
	HashMap<String,FitnessWatch> hm=new HashMap<String,FitnessWatch>();
	try 
	{
		getConnection();
		
		String selectFitnessWatch="select * from  productdetails where ProductType=?";
		PreparedStatement pst = conn.prepareStatement(selectFitnessWatch);
		pst.setString(1,"fitnessWatches");
		ResultSet rs = pst.executeQuery();
	
		while(rs.next())
		{	FitnessWatch fw = new FitnessWatch(rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productManufacturer"),rs.getString("productCondition"),rs.getDouble("productDiscount"));
				hm.put(rs.getString("Id"), fw);
				fw.setId(rs.getString("Id"));

		}
	}
	catch(Exception e)
	{
	}
	return hm;			
}

public static HashMap<String,SmartWatch> getSmartWatches()
{	
	HashMap<String,SmartWatch> hm=new HashMap<String,SmartWatch>();
	try 
	{
		getConnection();
		
		String selectSmartWatch="select * from  productdetails where ProductType=?";
		PreparedStatement pst = conn.prepareStatement(selectSmartWatch);
		pst.setString(1,"smartWatches");
		ResultSet rs = pst.executeQuery();
	
		while(rs.next())
		{	SmartWatch sw = new SmartWatch(rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productManufacturer"),rs.getString("productCondition"),rs.getDouble("productDiscount"));
				hm.put(rs.getString("Id"), sw);
				sw.setId(rs.getString("Id"));

		}
	}
	catch(Exception e)
	{
	}
	return hm;			
}

public static HashMap<String,HeadPhone> getHeadPhones()
{	
	HashMap<String,HeadPhone> hm=new HashMap<String,HeadPhone>();
	try 
	{
		getConnection();
		
		String selectHeadPhone="select * from  productdetails where ProductType=?";
		PreparedStatement pst = conn.prepareStatement(selectHeadPhone);
		pst.setString(1,"headPhones");
		ResultSet rs = pst.executeQuery();
	
		while(rs.next())
		{	HeadPhone hp = new HeadPhone(rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productManufacturer"),rs.getString("productCondition"),rs.getDouble("productDiscount"));
				hm.put(rs.getString("Id"), hp);
				hp.setId(rs.getString("Id"));

		}
	}
	catch(Exception e)
	{
	}
	return hm;			
}

public static HashMap<String,Tablet> getTablets()
{	
	HashMap<String,Tablet> hm=new HashMap<String,Tablet>();
	try 
	{
		getConnection();
		
		String selectTablet="select * from  productdetails where ProductType=?";
		PreparedStatement pst = conn.prepareStatement(selectTablet);
		pst.setString(1,"tablets");
		ResultSet rs = pst.executeQuery();
	
		while(rs.next())
		{	Tablet tab = new Tablet(rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productManufacturer"),rs.getString("productCondition"),rs.getDouble("productDiscount"));
				hm.put(rs.getString("Id"), tab);
				tab.setId(rs.getString("Id"));

		}
	}
	catch(Exception e)
	{
	}
	return hm;			
}

public static HashMap<String,Game> getGames()
{	
	HashMap<String,Game> hm=new HashMap<String,Game>();
	try 
	{
		getConnection();
		
		String selectGame="select * from  Productdetails where ProductType=?";
		PreparedStatement pst = conn.prepareStatement(selectGame);
		pst.setString(1,"games");
		ResultSet rs = pst.executeQuery();
	
		while(rs.next())
		{	Game game = new Game(rs.getString("Id"),rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productManufacturer"),rs.getString("productCondition"),rs.getDouble("productDiscount"));
				hm.put(rs.getString("Id"), game);
				game.setId(rs.getString("Id"));
		}
	}
	catch(Exception e)
	{
	}
	return hm;			
}

public static HashMap<String,Accessory> getAccessories()
{	
	HashMap<String,Accessory> hm=new HashMap<String,Accessory>();
	try 
	{
		getConnection();
		
		String selectAcc="select * from  Productdetails where ProductType=?";
		PreparedStatement pst = conn.prepareStatement(selectAcc);
		pst.setString(1,"accessories");
		ResultSet rs = pst.executeQuery();
	
		while(rs.next())
		{	Accessory acc = new Accessory(rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productManufacturer"),rs.getString("productCondition"),rs.getDouble("productDiscount"));
				hm.put(rs.getString("Id"), acc);
				acc.setId(rs.getString("Id"));

		}
	}
	catch(Exception e)
	{
	}
	return hm;			
}
public static HashMap<String,PetTracker> getPetTrackers()
{	
	HashMap<String,PetTracker> hm=new HashMap<String,PetTracker>();
	try 
	{
		getConnection();
		
		String selectPetTracker="select * from  Productdetails where ProductType=?";
		PreparedStatement pst = conn.prepareStatement(selectPetTracker);
		pst.setString(1,"petTrackers");
		ResultSet rs = pst.executeQuery();
	
		while(rs.next())
		{	PetTracker pt = new PetTracker(rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productManufacturer"),rs.getString("productCondition"),rs.getDouble("productDiscount"));
				hm.put(rs.getString("Id"), pt);
				pt.setId(rs.getString("Id"));
		}
		}catch(Exception e){
			
		}
		return hm;			

}

public static HashMap<String,SoundSystem> getSoundSystems()
{	
	HashMap<String,SoundSystem> hm=new HashMap<String,SoundSystem>();
	try 
	{
		getConnection();
		
		String selectSoundSystem="select * from  Productdetails where ProductType=?";
		PreparedStatement pst = conn.prepareStatement(selectSoundSystem);
		pst.setString(1,"soundSystems");
		ResultSet rs = pst.executeQuery();

		while(rs.next())
		{	SoundSystem pt = new SoundSystem(rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productManufacturer"),rs.getString("productCondition"),rs.getDouble("productDiscount"));
				System.out.println(rs.getString("productName"));
				hm.put(rs.getString("Id"), pt);
				pt.setId(rs.getString("Id"));
		}
		}catch(Exception e){
			
		}
		return hm;			

}


public static HashMap<String,VirtualReality> getVirtualRealities()
{	
	HashMap<String,VirtualReality> hm=new HashMap<String,VirtualReality>();
	try 
	{
		getConnection();
		
		String selectVirtualReality="select * from  Productdetails where ProductType=?";
		PreparedStatement pst = conn.prepareStatement(selectVirtualReality);
		pst.setString(1,"virtualreality");
		ResultSet rs = pst.executeQuery();
	
		while(rs.next())
		{	VirtualReality pt = new VirtualReality(rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productManufacturer"),rs.getString("productCondition"),rs.getDouble("productDiscount"));
				hm.put(rs.getString("Id"), pt);
				pt.setId(rs.getString("Id"));
		}
		}catch(Exception e){
			
		}
		return hm;			

}

public static ArrayList<String> getMostSoldProducts()
{	
	ArrayList<String> products = new ArrayList<String>(5);

	try 
	{
		getConnection();
		
		//String selectVirtualReality="select * from  Productdetails where ProductType=?";
		String getMostSoldProducts = "SELECT orderName from exampledatabase.customerorders GROUP by orderName order by Count(*) DESC LIMIT 5;";
		PreparedStatement pst = conn.prepareStatement(getMostSoldProducts);
		//pst.setString(1,"virtualreality");
		ResultSet rs = pst.executeQuery();
		while(rs.next())
		{		//System.out.println(rs.getString("orderName"));
				products.add(rs.getString("orderName"));
				//VirtualReality pt = new VirtualReality(rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productManufacturer"),rs.getString("productCondition"),rs.getDouble("productDiscount"));
				//hm.put("dummy", "dummy");
				//pt.setId(rs.getString("Id"));
		}
	}catch(Exception e){
			
	}
	return products;
}


public static void deleteOrder(int orderId,String orderName)
{
	try
	{
		
		getConnection();
		String deleteOrderQuery ="Delete from customerorders where OrderId=? and orderName=?";
		PreparedStatement pst = conn.prepareStatement(deleteOrderQuery);
		pst.setInt(1,orderId);
		pst.setString(2,orderName);
		pst.executeUpdate();
	}
	catch(Exception e)
	{
			
	}
}

public static void insertOrder(int orderId,String userName,String orderName,double orderPrice,String userAddress,String creditCardNo)
{
	try
	{
		/*
			Select table "transactions" for updated insertOrder query
		*/
		Date current_date = new Date();
  		SimpleDateFormat SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		getConnection();
		String insertIntoCustomerOrderQuery = "INSERT INTO customerorders(OrderId,userName,orderName,orderPrice,userAddress,creditCardNo,purchasedate) "
		+ "VALUES (?,?,?,?,?,?,?);";	
			
		PreparedStatement pst = conn.prepareStatement(insertIntoCustomerOrderQuery);
		//set the parameter for each column and execute the prepared statement
		pst.setInt(1,orderId);
		pst.setString(2,userName);
		pst.setString(3,orderName);
		pst.setDouble(4,orderPrice);
		pst.setString(5,userAddress);
		pst.setString(6,creditCardNo);
		pst.setString(7,SimpleDateFormat.format(current_date.getTime()));
		pst.execute();
	}
	catch(Exception e)
	{
	
	}		
}

public static HashMap<Integer, ArrayList<OrderPayment>> selectOrder()
{	

	HashMap<Integer, ArrayList<OrderPayment>> orderPayments=new HashMap<Integer, ArrayList<OrderPayment>>();
		
	try
	{					

		getConnection();
        //select the table 
		String selectOrderQuery ="select * from customerorders";			
		PreparedStatement pst = conn.prepareStatement(selectOrderQuery);
		ResultSet rs = pst.executeQuery();	
		ArrayList<OrderPayment> orderList=new ArrayList<OrderPayment>();
		while(rs.next())
		{
			if(!orderPayments.containsKey(rs.getInt("OrderId")))
			{	
				ArrayList<OrderPayment> arr = new ArrayList<OrderPayment>();
				orderPayments.put(rs.getInt("orderId"), arr);
			}
			ArrayList<OrderPayment> listOrderPayment = orderPayments.get(rs.getInt("OrderId"));		
			System.out.println("data is"+rs.getInt("OrderId")+orderPayments.get(rs.getInt("OrderId")));

			//add to orderpayment hashmap
			OrderPayment order= new OrderPayment(rs.getInt("OrderId"),rs.getString("userName"),rs.getString("orderName"),rs.getDouble("orderPrice"),rs.getString("userAddress"),rs.getString("creditCardNo"));
			listOrderPayment.add(order);
					
		}
				
					
	}
	catch(Exception e)
	{
		
	}
	return orderPayments;
}


public static void insertUser(String username,String password,String repassword,String usertype)
{
	try
	{	

		getConnection();
		String insertIntoCustomerRegisterQuery = "INSERT INTO Registration(username,password,repassword,usertype) "
		+ "VALUES (?,?,?,?);";	
				
		PreparedStatement pst = conn.prepareStatement(insertIntoCustomerRegisterQuery);
		pst.setString(1,username);
		pst.setString(2,password);
		pst.setString(3,repassword);
		pst.setString(4,usertype);
		pst.execute();
	}
	catch(Exception e)
	{
	
	}	
}


public static HashMap<String, Product> selectInventory() {
	HashMap<String, Product> hm = new HashMap<String, Product>();
	try {
		getConnection();

		String selectAcc = "select * from productdetails";
		PreparedStatement pst = conn.prepareStatement(selectAcc);
		ResultSet rs = pst.executeQuery();

		while (rs.next()) {
			Product product = new Product(rs.getString("productName"), rs.getDouble("productPrice"), Integer.parseInt(rs.getString("Inventory")));
			hm.put(rs.getString("Id"), product);
			product.setId(rs.getString("Id"));
		}
	} catch (Exception e) {
					System.out.println(e);
					e.printStackTrace();
	}
	return hm;
}

public static HashMap<String, Product> selectRebate() {
	HashMap<String, Product> hm = new HashMap<String, Product>();
	try {
		getConnection();

		String selectAcc = "select * from productdetails where productDiscount > 0";
		PreparedStatement pst = conn.prepareStatement(selectAcc);
		ResultSet rs = pst.executeQuery();

		while (rs.next()) {
			Product product = new Product(rs.getString("productName"), rs.getDouble("productPrice"), Double.parseDouble(rs.getString("productDiscount")));
			hm.put(rs.getString("Id"), product);
			product.setId(rs.getString("Id"));
		}
	} catch (Exception e) {
	}
	return hm;
}
public static HashMap<String, Product> selectOnSale() {
	HashMap<String, Product> hm = new HashMap<String, Product>();
	try {
		getConnection();

		String selectAcc = "select * from productdetails where productonsale=?";
		PreparedStatement pst = conn.prepareStatement(selectAcc);
		pst.setString(1, "yes");
		ResultSet rs = pst.executeQuery();

		while (rs.next()) {
			Product product = new Product(rs.getString("productName"), rs.getDouble("productPrice"), Integer.parseInt(rs.getString("Inventory")));
			hm.put(rs.getString("Id"), product);
			product.setId(rs.getString("Id"));
		}
	} catch (Exception e) {
	}
	return hm;
}



public static HashMap<String, OrderPayment> selectSaleAmount() {
	HashMap<String, OrderPayment> hm = new HashMap<String, OrderPayment>();
	try {
		getConnection();

		String selectAcc = "select DISTINCT(temp.orderName),temp.saleAmount,customerorders.orderPrice from customerorders, (select orderName, count(orderName) as saleAmount from customerorders group by orderName) as temp where customerorders.orderName = temp.orderName";
		PreparedStatement pst = conn.prepareStatement(selectAcc);
		ResultSet rs = pst.executeQuery();
		int i = 0;
		while (rs.next()) {
			OrderPayment orderPayment = new OrderPayment(rs.getString("orderName"), rs.getDouble("orderPrice"), rs.getInt("saleAmount"));
			i++;
			hm.put(String.valueOf(i), orderPayment);
			//orderPayment.setOrderId(Integer.parseInt(rs.getString("Id")));
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return hm;
}

public static HashMap<String,User> selectUser()
{	
	HashMap<String,User> hm=new HashMap<String,User>();
	try 
	{
		getConnection();
		Statement stmt=conn.createStatement();
		String selectCustomerQuery="select * from  registration";
		ResultSet rs = stmt.executeQuery(selectCustomerQuery);
		while(rs.next())
		{	User user = new User(rs.getString("username"),rs.getString("password"),rs.getString("usertype"));
				hm.put(rs.getString("username"), user);
		}
	}
	catch(Exception e)
	{
	}
	return hm;			
}

public static HashMap<String, OrderPayment> selectDailyTransaction() {
	HashMap<String, OrderPayment> hm = new HashMap<String, OrderPayment>();
	try {
		getConnection();

		String selectAcc = "SELECT count(purchasedate) as soldamount, purchasedate from customerorders group by purchasedate";
		PreparedStatement pst = conn.prepareStatement(selectAcc);
		ResultSet rs = pst.executeQuery();

		int i = 0;
		while (rs.next()) {
			OrderPayment orderPayment = new OrderPayment(rs.getInt("soldamount"), rs.getDate("purchasedate"));
			i++;
			hm.put(String.valueOf(i), orderPayment);
			//orderPayment.setId(rs.getString("Id"));
		}
	} catch (Exception e) {
	}
	return hm;


}
public static ArrayList<OrderPayment> selectDailyTransactionForChart() {
	ArrayList<OrderPayment> orderPaymentArrayList = new ArrayList<OrderPayment>();
	try {
		getConnection();

		String selectAcc = "SELECT count(purchasedate) as soldamount, purchasedate from customerorders group by purchasedate";
		PreparedStatement pst = conn.prepareStatement(selectAcc);
		ResultSet rs = pst.executeQuery();

		while (rs.next()) {
			OrderPayment orderPayment = new OrderPayment(rs.getInt("soldamount"), rs.getDate("purchasedate"));
			orderPaymentArrayList.add(orderPayment);
		}
	} catch (Exception e) {
	}
	return orderPaymentArrayList;
}

public static String deleteproducts(String productId)
{   String msg = "Product is deleted successfully";
	try
	{
		
		getConnection();
		String deleteproductsQuery ="Delete from productdetails where Id=?";
		PreparedStatement pst = conn.prepareStatement(deleteproductsQuery);
		pst.setString(1,productId);
		
		pst.executeUpdate();
	}
	catch(Exception e)
	{
			msg = "Product cannot be deleted";
	}
	return msg;
}

public static String updateproducts(String producttype,String productId,String productName,double productPrice,String productImage,String productManufacturer,String productCondition,double productDiscount,int inventory)
{ 
    String msg = "Product is updated successfully";
	try{
		
		getConnection();
		String updateProductQurey = "UPDATE productdetails SET productName=?,productPrice=?,productImage=?,productManufacturer=?,productCondition=?,productDiscount=?,Inventory=? where Id =?;" ;
		
		   
				        			
			PreparedStatement pst = conn.prepareStatement(updateProductQurey);
			
			pst.setString(1,productName);
			pst.setDouble(2,productPrice);
			pst.setString(3,productImage);
			pst.setString(4,productManufacturer);
			pst.setString(5,productCondition);
			pst.setDouble(6,productDiscount);
			pst.setString(7,productId);
			pst.setInt(8,inventory);
			pst.executeUpdate();
			
			
		
	}
	catch(Exception e)
	{
		msg = "Product cannot be updated";
		e.printStackTrace();
		
	}
 return msg;	
}
public static String addproducts(String producttype,String productId,String productName,double productPrice,String productImage,String productManufacturer,String productCondition,double productDiscount,String prod, int inventory)
{
	String msg = "Product is added successfully";
	try{
		
		getConnection();
		String addProductQurey = "INSERT INTO  productdetails(ProductType,Id,productName,productPrice,productImage,productManufacturer,productCondition,productDiscount,Inventory)" +
		"VALUES (?,?,?,?,?,?,?,?,?);";
		   
			String name = producttype;
	        			
			PreparedStatement pst = conn.prepareStatement(addProductQurey);
			pst.setString(1,name);
			pst.setString(2,productId);
			pst.setString(3,productName);
			pst.setDouble(4,productPrice);
			pst.setString(5,productImage);
			pst.setString(6,productManufacturer);
			pst.setString(7,productCondition);
			pst.setDouble(8,productDiscount);
			pst.setInt(9,inventory);

			pst.executeUpdate();
			try{
				if (!prod.isEmpty())
				{
					String addaprodacc =  "INSERT INTO  product_accessories(productName,accessoriesName)" +	"VALUES (?,?);";
					PreparedStatement pst1 = conn.prepareStatement(addaprodacc);
					pst1.setString(1,prod);
					pst1.setString(2,productId);
					pst1.executeUpdate();
					
				}
			}catch(Exception e)
			{
				msg = "Erro while adding the product";
				e.printStackTrace();
		
			}
			
			
		
	}
	catch(Exception e)
	{
		msg = "Erro while adding the product";
		e.printStackTrace();
		
	}
	return msg;
}

}	