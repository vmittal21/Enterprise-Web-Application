package dao;

import java.util.List;

import bean.Product;

public interface ProductDao extends BasicCRUD<Product, String>{
	public List<Product> getProductByCatagory(String catagory);
}
