/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Shopping_Cart_Item;

/**
 *
 * @author nguye
 */
public class Shopping_Cart_ItemDAO extends DBContext{
    
    public List<Shopping_Cart_Item> getAll() {
        List<Shopping_Cart_Item> list = new ArrayList<>();
        String sql = "select * from Shopping_Cart_Item";
        //chay lenhj truy van
        try {
            PreparedStatement ur = connection.prepareStatement(sql);
            ResultSet rs = ur.executeQuery();
            while (rs.next()) {
                Shopping_Cart_Item shopping_cart_item = new Shopping_Cart_Item(rs.getString("id"), rs.getInt("quantity"), rs.getString("product_id"), rs.getString("shopping_cart_id"));
                list.add(shopping_cart_item);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return list;
    }

    public List<Shopping_Cart_Item> getTop(){
        List<Shopping_Cart_Item> list = new ArrayList<>();
        String sql = "select * from Shopping_Cart_Item ORDER BY quantity DESC";
        //chay lenhj truy van
        try {
            PreparedStatement ur = connection.prepareStatement(sql);
            ResultSet rs = ur.executeQuery();
            while (rs.next()) {
                Shopping_Cart_Item shopping_cart_item = new Shopping_Cart_Item(rs.getString("id"), rs.getInt("quantity"), rs.getString("product_id"), rs.getString("shopping_cart_id"));
                list.add(shopping_cart_item);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return list;
    }
    public static void main(String[] args) {
        Shopping_Cart_ItemDAO dao = new Shopping_Cart_ItemDAO();
        for (Shopping_Cart_Item a : dao.getTop()) {
            System.out.println(a.getQuantity());
        }
    }
}
