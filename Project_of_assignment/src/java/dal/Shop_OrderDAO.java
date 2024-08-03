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
import model.Shop_Order;
public class Shop_OrderDAO extends DBContext{
     
    public List<Shop_Order> getAll() {
        List<Shop_Order> list = new ArrayList<>();
        String sql = "select * from Shop_Order";
        //chay lenhj truy van
        try {
            PreparedStatement ur = connection.prepareStatement(sql);
            ResultSet rs = ur.executeQuery();
            while (rs.next()) {
                Shop_Order shop_order = new Shop_Order(rs.getString("id"), rs.getString("date_order"), rs.getFloat("total_price"), rs.getByte("status_order"), rs.getString("address_shipping"), rs.getString("shipping_id"), rs.getString("account_id"));
                list.add(shop_order);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return list;
    }

    public static void main(String[] args) {
        Shop_OrderDAO dao = new Shop_OrderDAO();
        for (Shop_Order a : dao.getAll()) {
            System.out.println(a.getId());
        }
    }
}
