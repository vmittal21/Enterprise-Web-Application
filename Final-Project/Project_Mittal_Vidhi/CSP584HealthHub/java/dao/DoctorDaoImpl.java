package dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sound.sampled.Line;

import bean.Appointment;
import bean.Doctor;
import bean.MedicalService;
import util.JDBCUtil;
import util.SaxParserDataStore;

public class DoctorDaoImpl implements DoctorDao{
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	@Override
	public List<Doctor> getDoctorBySpecialty(String specialty) {
		conn = JDBCUtil.getConnection();
		String sql = "SELECT doctor FROM user WHERE specialty='" + specialty + "';";
		List<Doctor> list = new ArrayList<Doctor>();
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				list.add(new Doctor());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtil.close(conn);
		}
		return list;
	}

	@Override
	public List<Doctor> getDoctorByFilters(Map<String,String> filter) {
		List<Doctor> res = new ArrayList<>();
		String sql = "SELECT * FROM doctor";
		List<Map.Entry<String, String>> list = new ArrayList<>(filter.entrySet());
		if(list.size() >= 1) {
			sql += " WHERE " + list.get(0).getKey() + "='" + list.get(0).getValue()+"'";
			for(int i=1; i<list.size(); i++) {
				sql += " AND " + list.get(i).getKey() + "='" + list.get(i).getValue() + "'";
			}

		}
		conn = JDBCUtil.getConnection();
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				res.add(new Doctor(rs.getInt("id"), rs.getString("doctorName"), rs.getString("specialty"), rs.getString("phoneNum"), rs.getString("address"), rs.getString("state"), rs.getString("city"), rs.getString("zipcode"), rs.getString("longtitude"), rs.getString("latitude")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public List<String> getSpecialties() {
		Set<String> set = new HashSet<>();
		conn = JDBCUtil.getConnection();
		String sql = "SELECT * FROM Doctor;";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				set.add(rs.getString("specialty"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtil.close(conn);
		}
		return new ArrayList<>(set);
	}


	@Override
	public void add(Doctor doctor) {
		// TODO Auto-generated method stub
		conn = JDBCUtil.getConnection();
		String sql = "INSERT INTO doctor('doctorName', 'specialty', 'address', 'state', 'city', 'phoneNum', 'zipcode', 'longtitude', 'latitude') VALUES(?,?,?,?,?,?,?,?,?);";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, doctor.getName());
			ps.setString(2, doctor.getSpecilization());
			ps.setString(3, doctor.getAddress());
			ps.setString(4, doctor.getState());
			ps.setString(5, doctor.getCity());
			ps.setString(6, doctor.getPhoneNum());
			ps.setString(7, doctor.getZipcode());
			ps.setString(8, doctor.getLongtitude());
			ps.setString(9, doctor.getlatitude());
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtil.close(conn);
		}
	}

	public List<Doctor> readAllDoctors(String fileName){
		List<Doctor> list = new ArrayList<>();
		try {
			BufferedReader bf = new BufferedReader(new FileReader(new File(fileName)));
			int tmp = 0;
			while((tmp = bf.read()) > 0) {
				String line[] = bf.readLine().split(",");
				for(int i=0; i<line.length; i++) {
					line[i] = line[i].replace("\"", "");
				}
				int i = 0;
				list.add(new Doctor(line[i++],line[i++],line[i++],line[i++],line[i++],line[i++],line[i++],line[i++],line[i++]));
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
	@Override
	public void addAllDoctors() {
		// TODO Auto-generated method stub
		String TOMCAT_HOME = System.getProperty("catalina.base");
		List<Doctor> doctors = readAllDoctors(TOMCAT_HOME + "//webapps//CSP584HealthHub//data//doctors.txt");
		conn = JDBCUtil.getConnection();
		String sql = "INSERT INTO doctor(doctorName, specialty, address, state, city, phoneNum, zipcode, longtitude, latitude) VALUES(?,?,?,?,?,?,?,?,?);";
		try {
			ps = conn.prepareStatement(sql);
			for(Doctor doctor : doctors) {
				ps.setString(1, doctor.getName());
				ps.setString(2, doctor.getSpecilization());
				ps.setString(3, doctor.getAddress());
				ps.setString(4, doctor.getState());
				ps.setString(5, doctor.getCity());
				ps.setString(6, doctor.getPhoneNum());
				ps.setString(7, doctor.getZipcode());
				ps.setString(8, doctor.getLongtitude());
				ps.setString(9, doctor.getlatitude());
				ps.execute();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtil.close(conn);
		}
	}

	@Override
	public void addAppointment(Appointment appointment, String username) {
		// TODO Auto-generated method stub
		conn = JDBCUtil.getConnection();
		String sql = "INSERT INTO appointment(doctorId, fullName, gender, birth, address, phone, email, date, userName) VALUES(?,?,?,?,?,?,?,?,?);";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, appointment.getDoctorId());
			ps.setString(2, appointment.getFullName());
			ps.setString(3, appointment.getGender());
			ps.setString(4, appointment.getBirth());
			ps.setString(5, appointment.getFullAddress());
			ps.setString(6, appointment.getPhone());
			ps.setString(7, appointment.getEmail());
			ps.setTimestamp(8, new Timestamp(appointment.getDate().getTime()));
			ps.setString(9, username);
			System.out.println(ps);
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtil.close(conn);
		}
	}
	public Map<Appointment, Doctor> getAppointmentByUser(String username){
		conn = JDBCUtil.getConnection();
		Map<Appointment, Doctor> res = new HashMap<Appointment, Doctor>();
		String sql = "SELECT * FROM appointment a left join doctor d on a.doctorId = d.id where userName ='" + username + "';";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()) {
				Appointment appointment = new Appointment();
				appointment.setId(rs.getInt("id"));
				appointment.setDate(rs.getDate("date"));
				appointment.setFullName(rs.getString("fullName"));
				appointment.setFullAddress(rs.getString("a.address"));
				appointment.setEmail(rs.getString("email"));
				appointment.setPhone(rs.getString("phone"));
				Doctor doctor = new Doctor();
				doctor.setAddress(rs.getString("d.address"));
				doctor.setName(rs.getString("doctorName"));
				doctor.setPhoneNum(rs.getString("phoneNum"));
				res.put(appointment, doctor);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtil.close(conn);
		}
		return res;
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Doctor t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Doctor getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Doctor> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public void removeAppointment(String id) {
		// TODO Auto-generated method stub
		conn = JDBCUtil.getConnection();
		String sql = "DELETE FROM appointment WHERE doctorId='" + id + "'";
		try {
			ps = conn.prepareStatement(sql);
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtil.close(conn);
		}
	}

}
