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
import model.Product_Category;
public class Product_CategoryDAO extends DBContext{
    
    public List<Product_Category> getAll() {
        List<Product_Category> list = new ArrayList<>();
        String sql = "select * from Product_Category ORDER BY id";
        //chay lenhj truy van
        try {
            PreparedStatement ur = connection.prepareStatement(sql);
            ResultSet rs = ur.executeQuery();
            while (rs.next()) {
                Product_Category category = new Product_Category(rs.getString("id"), rs.getString("name"));
                list.add(category);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return list;
    }
    
     public Product_Category getCategory(String id) {
        String sql = "select * from Product_Category Where id = ?";
        try {
            PreparedStatement ur = connection.prepareStatement(sql);
            ur.setString(1, id);
            ResultSet rs = ur.executeQuery();
            if (rs.next()) {
                Product_Category pc = new Product_Category(rs.getString(1), rs.getString(2));
                return pc;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }


    public static void main(String[] args) {
        Product_CategoryDAO dao = new Product_CategoryDAO();
        for (Product_Category a : dao.getAll()) {
            System.out.println(a.getId());
        }
    }
}
