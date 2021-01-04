package service;


import bean.User;
import dao.UserDaoImpl;

public class UserService {
	static UserDaoImpl userDaoImpl = new UserDaoImpl();
	public static boolean checkLogin(String username, String password) {
		return userDaoImpl.checkLogin(username, password);
	}
	public static void addUser(String userName, String firstName, String lastName, String password, String userType){
		User user = new User(firstName, lastName, password, userType, userName);
		userDaoImpl.add(user);
	}
	public static boolean checkRegister(String username) {
		return userDaoImpl.checkRegister(username);
	}
}
