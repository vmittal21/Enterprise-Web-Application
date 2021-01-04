package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Store;
import util.JDBCUtil;

public class StoreDaoImpl implements StoreDao{
	Connection conn = null;
	ResultSet rs = null;
	public void addAllStore() {
		/*
		 * hard-code 10 store
		 */
		List<Store> stores;
		{
			stores = new ArrayList<Store>();
			stores.add(new Store("1","store1","443 E 34TH St","60616","41.832820", "-87.614550"));
			stores.add(new Store("2","store2","1224 S Wabash Ave","60605", "41.866340", "-87.626020"));
			stores.add(new Store("3","store3","1340 S Canal St","60607", "41.864360", "-87.639860"));
			stores.add(new Store("4","store4","1220 S Ashland Ave","60608", "41.866310", "-87.666510"));
			stores.add(new Store("5","store5","370 N Des Plaines St","60661","41.888760","-87.645040"));
			stores.add(new Store("6","store6","3644 S Archer Ave","60609","41.888760", "-87.645040"));
			stores.add(new Store("7","store7","550 N State St","60654", "41.888760", "-87.645040"));
			stores.add(new Store("8","store8","102 W Division St","60610", "41.888760", "-87.645040"));
			stores.add(new Store("9","store9","424 W Division St","60610", "41.903880", "-87.639630"));
			stores.add(new Store("10","store10","6014 S Cottage","60614", "41.784540", "-87.606550"));
		}
		try {
			conn = JDBCUtil.getConnection();
			String truncatetableprod = "delete from  Stores;";
			PreparedStatement psttprod = conn.prepareStatement(truncatetableprod);
			psttprod.executeUpdate();
			String insertStore = "INSERT INTO  Stores(id, name, address, zipcode, latitude, longtitude)" + "VALUES (?,?,?,?,?,?);";
			psttprod = conn.prepareStatement(insertStore);
			for(Store store : stores) {
				psttprod.setString(1, store.getId());
				psttprod.setString(2, store.getName());
				psttprod.setString(3, store.getAddress());
				psttprod.setString(4, store.getZipcode());
				psttprod.setString(5, store.getLatitude());
				psttprod.setString(6, store.getLongtitude());
				psttprod.execute();
			}
		}catch(Exception e) {
			
		}finally{
			JDBCUtil.close(conn);
		}
	}
	@Override
	public void add(Store t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Store t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Store getById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Store> getAll() {
		List<Store> res = new ArrayList<Store>();
		try {
			conn = JDBCUtil.getConnection();
			PreparedStatement preparedStatement = conn.prepareStatement("select * from Stores");
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				res.add(new Store(resultSet.getString("id"), resultSet.getString("name"), resultSet.getString("address"), resultSet.getString("zipcode"),resultSet.getString("latitude"), resultSet.getString("longtitude")));
			}
		}catch (Exception e) {
			System.out.print(e.getMessage());
		}
		return res;
	}

}
