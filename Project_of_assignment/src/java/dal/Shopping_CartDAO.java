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
import model.Shopping_Cart;
public class Shopping_CartDAO extends DBContext{
       
    public List<Shopping_Cart> getAll() {
        List<Shopping_Cart> list = new ArrayList<>();
        String sql = "select * from Shopping_Cart";
        //chay lenhj truy van
        try {
            PreparedStatement ur = connection.prepareStatement(sql);
            ResultSet rs = ur.executeQuery();
            while (rs.next()) {
                Shopping_Cart shopping_cart = new Shopping_Cart(rs.getString("id"), rs.getString("shop_order_id"), rs.getString("account_id"));
                list.add(shopping_cart);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return list;
    }

    public static void main(String[] args) {
        Shopping_CartDAO dao = new Shopping_CartDAO();
        for (Shopping_Cart a : dao.getAll()) {
            System.out.println(a.getId());
        }
    }
}
