package dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import bean.Doctor;
import bean.MedicalService;
import util.JDBCUtil;

public class MedicalDao {
	Connection conn;
	PreparedStatement ps;
	ResultSet rs;
	public List<MedicalService> getMedicalByfilters(String[] parameters, String zipcode) {
		// TODO Auto-generated method stub
		conn = JDBCUtil.getConnection();
		String sql = "SELECT * FROM MedicalService";
		List<MedicalService> res = new ArrayList<>();
		if(zipcode != null && zipcode.length() > 0) {
			sql += " WHERE zipcode='" + zipcode + "'";
		}
		if(parameters != null && parameters.length > 0) {
			if(zipcode != null && zipcode.length() > 0) {
				sql += " AND FIND_IN_SET(type,'";
			}
			else sql += " WHERE FIND_IN_SET(type, '";
			for(int i=0; i<parameters.length; i++) {
				sql += parameters[i];
				if(i != parameters.length - 1)sql += ",";
			}
			sql += "')";
		}
		
		conn = JDBCUtil.getConnection();
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			System.out.println(ps);
			while(rs.next()) {
				res.add(new MedicalService(rs.getString("id"), rs.getString("type"), rs.getString("name"), rs.getString("address"), rs.getString("zipcode"), rs.getString("longtitude"), rs.getString("latitude")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	public void addAllMedical() {
String TOMCAT_HOME = System.getProperty("catalina.base");
		
		List<MedicalService> list = readAllMedical(TOMCAT_HOME + "//webapps//CSP584HealthHub//data//medical.txt");
		conn = JDBCUtil.getConnection();
		
		try {
			conn.prepareStatement("delete from MedicalService").execute();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String sql = "INSERT INTO  MedicalService(id, type, name, address, zipcode, latitude, longtitude)" + "VALUES (?,?,?,?,?,?,?);";
		try {
			ps = conn.prepareStatement(sql);
			for(MedicalService m : list) {
				ps.setString(1, m.getId());
				ps.setString(2, m.getType());
				ps.setString(3, m.getName());
				ps.setString(4, m.getAddress());
				ps.setString(5, m.getZipcode());
				ps.setString(6, m.getLatitude());
				ps.setString(7, m.getLongtitude());
				ps.execute();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtil.close(conn);
		}
	}
	private List<MedicalService> readAllMedical(String string) {
		// TODO Auto-generated method stub
		List<MedicalService> list = new ArrayList<>();
		try {
			BufferedReader bf = new BufferedReader(new FileReader(new File(string)));
			int tmp = 0;
			while((tmp = bf.read()) > 0) {
				String line[] = bf.readLine().split(",");
				for(int i=0; i<line.length; i++) {
					line[i] = line[i].replace("\"", "");
				}
				int i = 0;
				list.add(new MedicalService(line[i++],line[i++],line[i++],line[i++],line[i++],line[i++],line[i++]));
			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
}
