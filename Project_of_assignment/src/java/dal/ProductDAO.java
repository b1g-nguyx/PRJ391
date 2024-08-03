/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

/**
 *
 * @author nguye
 */
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Product;

public class ProductDAO extends DBContext {

    public List<Product> getAll() {
        List<Product> list = new ArrayList<>();
        String sql = "select * from Products order by id";
        //chay lenhj truy van
        try {
            PreparedStatement ur = connection.prepareStatement(sql);
            ResultSet rs = ur.executeQuery();
            while (rs.next()) {
                Product product = new Product(rs.getString("id"), rs.getString("name"), rs.getString("description"), rs.getString("image"), rs.getFloat("price"), rs.getInt("quantity"), rs.getString("category_id"));
                list.add(product);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return list;
    }

    public int getCount(String pc) {
        String sql = "Select count(*) from products Where category_id=?";
        try {
            PreparedStatement ur = connection.prepareStatement(sql);
            ur.setString(1, pc);
            ResultSet rs = ur.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
        }
        return 0;
    }

    public List<Product> getPaging(String pc, int index) {
        List<Product> list = new ArrayList<>();
        String sql = "Select * from Products where category_id=? order by category_id OFFSET ? ROWS FETCH NEXT 12 ROWS ONLY;";
        //chay lenhj truy van
        try {
            PreparedStatement ur = connection.prepareStatement(sql);
            ur.setString(1, pc);
            ur.setInt(2, (index - 1) * 12);
            ResultSet rs = ur.executeQuery();
            while (rs.next()) {
                Product product = new Product(rs.getString("id"), rs.getString("name"), rs.getString("description"), rs.getString("image"), rs.getFloat("price"), rs.getInt("quantity"), rs.getString("category_id"));
                list.add(product);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return list;
    }

    public Product getProduct(String id) {
        String sql = "Select * from Products WHERE id=?";
        try {
            PreparedStatement ur = connection.prepareStatement(sql);
            ur.setString(1, id);
            ResultSet rs = ur.executeQuery();
            while (rs.next()) {
                Product product = new Product(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getFloat(5), rs.getInt(6), rs.getString(7));
                return product;
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        return null;
    }

    public List<Product> getSearch(String search, int index) {
        List<Product> list = new ArrayList<>();
        String sql = "Select * from Products where name like '%?%' order by id OFFSET ? ROWS FETCH NEXT 12 ROWS ONLY;";
        try {
            PreparedStatement ur = connection.prepareStatement(sql);
            ur.setString(1, search);
            ur.setInt(2, (index - 1) * 12);
            ResultSet rs = ur.executeQuery();
            while (rs.next()) {
                Product product = new Product(rs.getString("id"), rs.getString("name"), rs.getString("description"), rs.getString("image"), rs.getFloat("price"), rs.getInt("quantity"), rs.getString("category_id"));
                list.add(product);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return list;
    }

    public List<Product> findPrice(float min, float max) {
        List<Product> list = new ArrayList<>();
        String sql = "Select * from Products where price >= ? and price <=?";
        try {
            PreparedStatement ur = connection.prepareStatement(sql);
            ur.setFloat(1, min);
            ur.setFloat(2, max);
            ResultSet rs = ur.executeQuery();
            while (rs.next()) {
                Product product = new Product(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getFloat(5), rs.getInt(6), rs.getString(7));
                list.add(product);
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        return list;
    }

    public void updateQuantity(String id, int quantity) {
        String sql = "UPDATE [dbo].[Products]\n"
                + "   SET [quantity] = <quantity, int,>\n"
                + " WHERE <id=?>";
        try {
            PreparedStatement ur = connection.prepareStatement(sql);
            ur.setInt(1, quantity);
            ur.setString(2, id);
            ur.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    public static void main(String[] args) {
        ProductDAO dao = new ProductDAO();
        for (Product product : dao.getPaging("PC1", 1)) {
            System.out.println(product.getId());
        }
        for (Product product : dao.getSearch("2", 0)) {
            System.out.println(product.getId());
        }
        System.out.println(dao.getCount("PC1"));
        for (Product p : dao.findPrice(2000000, 3000000)) {
            System.out.println(p.getPrice());
        }
    }
}
