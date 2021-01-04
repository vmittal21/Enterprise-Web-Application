package service;

import java.util.List;

import bean.Store;
import dao.StoreDao;
import dao.StoreDaoImpl;

public class StoreService {
	static StoreDao storeDao = new StoreDaoImpl();
	public static List<Store> getAllStores() {
		return storeDao.getAll();
	}
}
