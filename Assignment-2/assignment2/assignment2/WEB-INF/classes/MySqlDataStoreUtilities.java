import java.sql.*;
import java.util.*;
                	
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
	try{
		
		
		getConnection();
		
		
		String truncatetableacc = "delete from Product_accessories;";
		PreparedStatement pstt = conn.prepareStatement(truncatetableacc);
		pstt.executeUpdate();
		
		String truncatetableprod = "delete from  Productdetails;";
		PreparedStatement psttprod = conn.prepareStatement(truncatetableprod);
		psttprod.executeUpdate();
		
		
		String insertProductQurey = "INSERT INTO  Productdetails(ProductType,Id,productName,productPrice,productImage,productManufacturer,productCondition,productDiscount)" +
		"VALUES (?,?,?,?,?,?,?,?);";
		for(Map.Entry<String,Accessory> entry : SaxParserDataStore.accessories.entrySet())
		{   
			String name = "accessories";
	        Accessory acc = entry.getValue();
			
			PreparedStatement pst = conn.prepareStatement(insertProductQurey);
			pst.setString(1,name);
			pst.setString(2,acc.getId());
			pst.setString(3,acc.getName());
			pst.setDouble(4,acc.getPrice());
			pst.setString(5,acc.getImage());
			pst.setString(6,acc.getRetailer());
			pst.setString(7,acc.getCondition());
			pst.setDouble(8,acc.getDiscount());
			
			pst.executeUpdate();
			
		}
		
	}
	catch(Exception e)
	{
	
	}
}

public static HashMap<String,Console> getConsoles()
{	
	HashMap<String,Console> hm=new HashMap<String,Console>();
	try 
	{
		getConnection();
		
		String selectConsole="select * from  Productdetails where ProductType=?";
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
					String selectaccessory = "Select * from Product_accessories where productName=?";
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
		
		String selectVoiceAssistant="select * from  Productdetails where ProductType=?";
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
		
		String selectLaptop="select * from  Productdetails where ProductType=?";
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
		
		String selectFitnessWatch="select * from  Productdetails where ProductType=?";
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
		
		String selectSmartWatch="select * from  Productdetails where ProductType=?";
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
		
		String selectHeadPhone="select * from  Productdetails where ProductType=?";
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
		
		String selectTablet="select * from  Productdetails where ProductType=?";
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
		getConnection();
		String insertIntoCustomerOrderQuery = "INSERT INTO customerorders(OrderId,userName,orderName,orderPrice,userAddress,creditCardNo) "
		+ "VALUES (?,?,?,?,?,?);";	
			
		PreparedStatement pst = conn.prepareStatement(insertIntoCustomerOrderQuery);
		//set the parameter for each column and execute the prepared statement
		pst.setInt(1,orderId);
		pst.setString(2,userName);
		pst.setString(3,orderName);
		pst.setDouble(4,orderPrice);
		pst.setString(5,userAddress);
		pst.setString(6,creditCardNo);
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

public static HashMap<String,User> selectUser()
{	
	HashMap<String,User> hm=new HashMap<String,User>();
	try 
	{
		getConnection();
		Statement stmt=conn.createStatement();
		String selectCustomerQuery="select * from  Registration";
		ResultSet rs = stmt.executeQuery(selectCustomerQuery);
		while(rs.next())
		{	User user = new User(rs.getString("username"),rs.getString("password"),rs.getString("usertype"));
				System.out.println(rs.getString("username")+" "+rs.getString("password")+" "+rs.getString("usertype"));
				hm.put(rs.getString("username"), user);
		}
	}
	catch(Exception e)
	{
	}
	return hm;			
}

	
}	