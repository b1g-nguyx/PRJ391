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
import model.Shipping;
public class ShippingDAO extends DBContext{
    
    public List<Shipping> getAll() {
        List<Shipping> list = new ArrayList<>();
        String sql = "select * from Shipping";
        //chay lenhj truy van
        try {
            PreparedStatement ur = connection.prepareStatement(sql);
            ResultSet rs = ur.executeQuery();
            while (rs.next()) {
Shipping shipping = new Shipping(rs.getString("id"), rs.getString("name"), rs.getFloat("shipping_fee"));
                list.add(shipping);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return list;
    }

    public static void main(String[] args) {
        ShippingDAO dao = new ShippingDAO();
        for (Shipping a : dao.getAll()) {
            System.out.println(a.getId());
        }
    }
}
