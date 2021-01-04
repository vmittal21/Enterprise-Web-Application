package dao;

import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Appointment;
import bean.Bestseller;
import bean.Product;
import util.JDBCUtil;

public class ProductDaoImpl implements ProductDao {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    @Override
    public void add(Product p) {
        conn = JDBCUtil.getConnection();
        String sql = "INSERT INTO product VALUES(?,?,?,?,?,?,?,?,?)";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, p.getId());
            ps.setString(2, p.getName());
            ps.setDouble(3, p.getPrice());
            ps.setString(4, p.getImage());
            ps.setString(5, p.getManufacturer());
            ps.setString(6, p.getCondition());
            ps.setDouble(7, p.getDiscount());
            ps.setString(8, p.getCatagory());
            ps.setInt(9, p.getInventory());
            ps.execute();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn);
        }

    }

    @Override
    public void delete(String id) {
        // TODO Auto-generated method stub
        conn = JDBCUtil.getConnection();
        String sql = "DELETE FROM product WHERE id = ?;";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn);
        }
    }

    @Override
    public void update(Product p) {
        // TODO Auto-generated method stub
        String sql = "UPDATE product SET productName=?, price=?, image=?, manufacturer=?, productCondition=?, discount=?, catagory=?, inventory=? WHERE id=?;";
        conn = JDBCUtil.getConnection();
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(9, p.getId());
            ps.setString(1, p.getName());
            ps.setDouble(2, p.getPrice());
            ps.setString(3, p.getImage());
            ps.setString(4, p.getManufacturer());
            ps.setString(5, p.getCondition());
            ps.setDouble(6, p.getDiscount());
            ps.setString(7, p.getCatagory());
            ps.setInt(8, p.getInventory());
            ps.execute();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public Product getById(String id) {
        // TODO Auto-generated method stub
        String sql = "SELECT * FROM product WHERE id='" + id + "';";
        conn = JDBCUtil.getConnection();
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next())
                return new Product(rs.getString("id"), rs.getString("productName"), rs.getDouble("price"),
                        rs.getString("image"), rs.getString("manufacturer"), rs.getString("productCondition"),
                        rs.getDouble("discount"), rs.getString("catagory"), rs.getInt("inventory"));
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Product> getAll() {
        List<Product> list = new ArrayList<>();
        conn = JDBCUtil.getConnection();
        String sql = "SELECT * FROM product;";
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getString("id"), rs.getString("productName"), rs.getDouble("price"),
                        rs.getString("image"), rs.getString("manufacturer"), rs.getString("productCondition"),
                        rs.getDouble("discount"), rs.getString("catagory"), rs.getInt("inventory")));
            }
        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            JDBCUtil.close(conn);
        }
        return list;
    }

    @Override
    public List<Product> getProductByCatagory(String catagory) {
        // TODO Auto-generated method stub
        String sql = "SELECT * FROM product WHERE catagory='" + catagory + "'";
        List<Product> list = new ArrayList<>();
        conn = JDBCUtil.getConnection();
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Product p = new Product(rs.getString("id"), rs.getString("productName"), rs.getDouble("price"),
                        rs.getString("image"), rs.getString("manufacturer"), rs.getString("ProductCondition"),
                        rs.getDouble("discount"), rs.getString("catagory"), rs.getInt("inventory"));
                list.add(p);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn);
        }
        return list;
    }

    public void addAllProductsFromXML(List<Product> list) {
        conn = JDBCUtil.getConnection();
        String truncatetableacc = "delete from product;";
        PreparedStatement pstt;
        try {
            pstt = conn.prepareStatement(truncatetableacc);
            pstt.executeUpdate();
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        try {
            for (Product p : list) {
                String sql = "INSERT INTO product VALUES(?,?,?,?,?,?,?,?,?)";
                ps = conn.prepareStatement(sql);
                ps.setString(1, p.getId());
                ps.setString(2, p.getName());
                ps.setDouble(3, p.getPrice());
                ps.setString(4, p.getImage());
                ps.setString(5, p.getManufacturer());
                ps.setString(6, p.getCondition());
                ps.setDouble(7, p.getDiscount());
                ps.setString(8, p.getCatagory());
                ps.setInt(9, p.getInventory());
                ps.execute();
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn);
        }
    }

    public Product getProductObjById(String id) {
        // TODO Auto-generated method stub
        String sql = "SELECT * FROM product WHERE id='" + id + "'";
        Product prodObj = new Product();
        conn = JDBCUtil.getConnection();
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                prodObj = new Product(rs.getString("id"), rs.getString("productName"), rs.getDouble("price"),
                        rs.getString("image"), rs.getString("manufacturer"), rs.getString("ProductCondition"),
                        rs.getDouble("discount"), rs.getString("catagory"), rs.getInt("inventory"));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn);
        }
        return prodObj;
    }

	public List<Bestseller> getBestSeller() {
		// TODO Auto-generated method stub
		List<Bestseller> res = new ArrayList<>();
		String sql = "SELECT * FROM\r\n" + 
				"(SELECT Product_ID, count(*) AS NumOfSold FROM transactions group by Product_ID) b LEFT join product p ON b.Product_ID=p.id ORDER BY NumOfSold DESC\r\n" + 
				"";
        Product prodObj = new Product();
        conn = JDBCUtil.getConnection();
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                prodObj = new Product(rs.getString("id"), rs.getString("productName"), rs.getDouble("price"),
                        rs.getString("image"), rs.getString("manufacturer"), rs.getString("ProductCondition"),
                        rs.getDouble("discount"), rs.getString("catagory"), rs.getInt("inventory"));
                int numOfSold = rs.getInt("NumOfSold");
                Bestseller bs = new Bestseller();
                bs.setNumSold(numOfSold);
                bs.setProduct(prodObj);
                res.add(bs);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn);
        }
		return res;
	}

	public Product getProductByName(String prodcutnm) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM product WHERE productName='" + prodcutnm + "'";
        Product prodObj = new Product();
        conn = JDBCUtil.getConnection();
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                prodObj = new Product(rs.getString("id"), rs.getString("productName"), rs.getDouble("price"),
                        rs.getString("image"), rs.getString("manufacturer"), rs.getString("ProductCondition"),
                        rs.getDouble("discount"), rs.getString("catagory"), rs.getInt("inventory"));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn);
        }
        return prodObj;
	}

}
