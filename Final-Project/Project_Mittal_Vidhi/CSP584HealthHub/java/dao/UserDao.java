package dao;

import bean.User;

public interface UserDao extends BasicCRUD<User, String>{
	public boolean checkLogin(String uername, String password);
	public void resetPassword(String password, String username);
	public boolean checkRegister(String username);
	public boolean checkProfile(String username);
	public void updateProfile(User user);
}
