package dao;

import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.User;
import util.JDBCUtil;

public class UserDaoImpl implements UserDao{
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;
	@Override
	public void add(User user) {
		// TODO Auto-generated method stub
		conn = JDBCUtil.getConnection();
		String sql = "INSERT INTO user(username, userpassword, firstname, lastname, usertype) VALUES(?,?,?,?,?);";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUserName());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getFirstName());
			ps.setString(4, user.getLastName());
			ps.setString(5, user.getUsertype());
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(conn);
		}
		
	}

	@Override
	public void delete(String username) {
		// TODO Auto-generated method stub
		conn = JDBCUtil.getConnection();
		String sql = "DELETE FROM user WHERE username = ?;";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(conn);
		}
	}

	@Override
	public void update(User t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User getById(String userName) {
		// TODO Auto-generated method stub
		return null;
	}
	public String getZip(String username)  {
		String zipcode = "";
		conn = JDBCUtil.getConnection();
		String sql = "SELECT zipcode FROM user WHERE username='" + username + "';";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()) {
				zipcode = rs.getString("zipcode");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return zipcode;
	}
	@Override
	public List<User> getAll() {
		// TODO Auto-generated method stub
		List<User> list = new ArrayList<User>();
		conn = JDBCUtil.getConnection();
		String sql = "SELECT * FROM user WHERE usertype='customer';";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				list.add(new User(rs.getString("firstname"), rs.getString("lastname"), rs.getString("password"), rs.getString("usertype"), rs.getString("username")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			
		}finally {
			JDBCUtil.close(conn);
		}
		return list;
	}

	@Override
	public boolean checkLogin(String username, String password) {
		// TODO Auto-generated method stub
		conn = JDBCUtil.getConnection();
		String sql = "SELECT userpassword FROM user WHERE username='" + username + "';";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next())return rs.getString("userpassword").equals(password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtil.close(conn);
		}
		return false;
	}
	
	@Override
	public void resetPassword(String password, String username) {
		// TODO Auto-generated method stub
		conn = JDBCUtil.getConnection();
		String sql = "UPDATE user SET userpassword=? WHERE username=?;";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, password);
			ps.setString(2, username);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(conn);
		}
	}
	/*
	 * check register username is already existing
	 * return true we can use this username
	 * false means username already existed 
	 * */
	@Override
	public boolean checkRegister(String username) {
		// TODO Auto-generated method stub
		conn = JDBCUtil.getConnection();
		String sql = "SELECT * FROM user WHERE username='" + username + "';";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next())return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtil.close(conn);
		}
		return true;
	}

	@Override
	public boolean checkProfile(String username) {
		if(username == null)return false;
		// TODO Auto-generated method stub
		conn = JDBCUtil.getConnection();
		String sql = "SELECT * FROM user WHERE username='" + username + "';";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			int i = 6;
			while(rs.next()) {
				if(rs.getString(i++) == null)return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtil.close(conn);
		}
		return false;
	}

	@Override
	public void updateProfile(User user) {
		// TODO Auto-generated method stub
		conn = JDBCUtil.getConnection();
		String sql = "UPDATE user SET firstname=?, lastname=?, gender=?, birth=?, address=?, city=?, state=?, zipcode=?, job=?, email=?, age=? WHERE username='" + user.getUserName() + "';";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getFirstName());
			ps.setString(2, user.getLastName());
			ps.setString(3, user.getGender());
			ps.setString(4,  user.getBirth());
			ps.setString(5, user.getAddress());
			ps.setString(6, user.getCity());
			ps.setString(7, user.getState());
			ps.setString(8, user.getZipcode());
			ps.setString(9, user.getJob());
			ps.setString(10, user.getEmail());
			ps.setString(11, user.getAge());
//			System.out.println(ps);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtil.close(conn);
		}
	}

	public User getProfile(String username) {
		// TODO Auto-generated method stub
		conn = JDBCUtil.getConnection();
		User user = new User();
		String sql = "SELECT firstname, lastname, gender, birth, age, job, address, city, state, zipcode, email FROM user WHERE username='" + username + "';";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			int i = 1;
			if(rs.next()) {
				if(rs.getString(i) != null)user.setFirstName(rs.getString(i++));
				if(rs.getString(i) != null)user.setLastName(rs.getString(i++));
				if(rs.getString(i) != null)user.setGender(rs.getString(i++));
				if(rs.getString(i) != null)user.setBirth(rs.getString(i++));
				if(rs.getString(i) != null)user.setAge(rs.getString(i++));
				if(rs.getString(i) != null)user.setJob(rs.getString(i++));
				if(rs.getString(i) != null)user.setAddress(rs.getString(i++));
				if(rs.getString(i) != null)user.setCity(rs.getString(i++));
				if(rs.getString(i) != null)user.setState(rs.getString(i++));
				
				if(rs.getString(i) != null)user.setZipcode(rs.getString(i++));
				if(rs.getString(i) != null)user.setEmail(rs.getString(i++));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtil.close(conn);
		}
		return user;
	}

}
