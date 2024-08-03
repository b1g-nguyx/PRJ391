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
import model.Payment;

public class PaymentDAO extends DBContext {

    public List<Payment> getAll() {
        List<Payment> list = new ArrayList<>();
        String sql = "select * from Payment";
        //chay lenhj truy van
        try {
            PreparedStatement ur = connection.prepareStatement(sql);
            ResultSet rs = ur.executeQuery();
            while (rs.next()) {
                Payment payment = new Payment(rs.getString("id"), rs.getString("shopping_cart_id"), rs.getString("account_id"));
                list.add(payment);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return list;
    }

    public float totalofTotal() {
        String sql = "select sum(total_price) from Payment";
        try {
            PreparedStatement ur = connection.prepareStatement(sql);
            ResultSet st = ur.executeQuery();
            if (st.next()) {
                return st.getFloat("total_price");
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        return 0;
       
    }

    public static void main(String[] args) {
        PaymentDAO dao = new PaymentDAO();
        for (Payment a : dao.getAll()) {
            System.out.println(a.getId());
        }
    }
}
