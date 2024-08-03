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
import model.Accounts;

public class AccountDAO extends DBContext {

    public List<Accounts> getAll() {
        List<Accounts> list = new ArrayList<>();
        String sql = "select * from Accounts";
        //chay lenhj truy van
        try {
            PreparedStatement ur = connection.prepareStatement(sql);
            ResultSet rs = ur.executeQuery();
            while (rs.next()) {
                Accounts account = new Accounts(rs.getString("id"), rs.getString("username"), rs.getString("password"), rs.getString("firstname"), rs.getString("lastname"), rs.getString("image"), rs.getInt("role"),rs.getByte("hehe"));
                list.add(account);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return list;
    }

    public Accounts getAccount(String username) {
        String sql = "select * from Accounts Where username = ?";
        try {
            PreparedStatement ur = connection.prepareStatement(sql);
            ur.setString(1, username);
            ResultSet rs = ur.executeQuery();
            if (rs.next()) {
                Accounts account = new Accounts(rs.getString("id"), rs.getString("username"), rs.getString("password"), rs.getString("firstname"), rs.getString("lastname"), rs.getString("image"), rs.getInt("role"),rs.getByte("hehe"));
                return account;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public void setInsert(Accounts account) {
        String sql = "INSERT INTO Accounts (id,Username,Password,FirstName,LastName,image,role,hehe) VALUES (?,?,?,?,?,?,?,0)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, account.getId());
            st.setString(2, account.getUsername());
            st.setString(3, account.getPassword());
            st.setString(4, account.getFirstname());
            st.setString(5, account.getLastname());
            st.setString(6, account.getImage());
            st.setInt(7, account.getRole());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void updateFromUser(Accounts account) {
        String sql = "UPDATE Accounts SET firstname=?, lastname=? Where username=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, account.getFirstname());
            st.setString(2, account.getLastname());
            st.setString(3, account.getUsername());
            st.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    public void updateP(String password, String username) {
        String sql = "UPDATE Accounts SET password=? Where username=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, password);
            st.setString(2, username);
            st.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    public void hehe(String username,int hehe){
         String sql = "UPDATE Accounts SET hehe=? Where username=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, hehe);
            st.setString(2, username);
            st.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e);
        }
    }
    
    public static void main(String[] args) {
        AccountDAO dao = new AccountDAO();
        for (Accounts a : dao.getAll()) {
            System.out.println(a.getId());
        }
        Accounts a = new Accounts("1","2", "6");
        dao.hehe("1", 3);
        dao.updateFromUser(a);
        dao.updateP("123", "1");
        System.out.println(dao.getAll().size());
        System.out.println(dao.getAccount("user1").getPassword());
    }
}
