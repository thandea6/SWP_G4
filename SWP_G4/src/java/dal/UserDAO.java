/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import model.Account;
import model.User;

/**
 *
 * @author VIET HOANG
 */
public class UserDAO extends DBContext {

    public User getUserByAccountId(int accountId) {
        PreparedStatement stm;
        ResultSet rs;
        String sql = "SELECT *"
                + "  FROM [dbo].[user]"
                + "where [accountId]= ?\n";

        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, accountId);
            rs = stm.executeQuery();
            if (rs.next()) {
                return new User(rs.getInt(1), rs.getNString(2), rs.getNString(3), rs.getString(4), rs.getDouble(5), rs.getDate(6), rs.getBoolean(7), rs.getInt(8));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public User getUserByUserId(int userId) {
        PreparedStatement stm;
        ResultSet rs;
        String sql = "SELECT *"
                + "  FROM [dbo].[user]"
                + "where [userId]= ?\n";

        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, userId);
            rs = stm.executeQuery();
            if (rs.next()) {
                return new User(rs.getInt(1), rs.getNString(2), rs.getNString(3), rs.getString(4), rs.getDouble(5), rs.getDate(6), rs.getBoolean(7), rs.getInt(8));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public void update(int accountId, String fullName, String address, String image, Date dob, boolean gender) {
        PreparedStatement stm;
        ResultSet rs;
        String sql = "UPDATE [dbo].[user]\n"
                + "   SET [fullName] = ?\n"
                + "      ,[address] = ?\n"
                + "      ,[image] = ?\n"
                + "      ,[dob] = ?\n"
                + "      ,[gender] = ?\n"
                + " WHERE accountId = ?";
        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, fullName);
            stm.setString(2, address);
            stm.setString(3, image);
            stm.setDate(4, dob);
            stm.setBoolean(5, gender);
            stm.setInt(6, accountId);
            stm.executeUpdate();
        } catch (SQLException e) {

        }
    }

    public void update(int id, User u) {
        PreparedStatement stm;
        ResultSet rs;
        String sql = "UPDATE [dbo].[user]\n"
                + "   SET [fullName] = ?\n"
                + "      ,[address] = ?\n"
                + "      ,[image] = ?\n"
                + "      ,[dob] = ?\n"
                + "      ,[gender] = ?\n"
                + " WHERE accountId = ?";
        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, u.getFullName());
            stm.setString(2, u.getAddress());
            stm.setString(3, u.getImage());
            stm.setDate(4, u.getDob());
            stm.setBoolean(5, u.isGender());
            stm.setInt(6, id);
            stm.executeUpdate();
        } catch (SQLException e) {

        }
    }

    // Duc
    public int addUser(User p) {
        String sql = "INSERT INTO [dbo].[user]\n"
                + "           ([fullName]\n"
                + "           ,[image]\n"
                + "           ,[accountId])\n"
                + "     VALUES (?,?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, p.getFullName());
            st.setString(2, p.getImage());
            st.setInt(3, p.getAccountId());
            int result = st.executeUpdate();
            return result;
        } catch (Exception e) {
            System.out.println("Add user: " + e);
        }
        return 0;
    }

    public List<User> getAllUser() {
        PreparedStatement stm;
        ResultSet rs;
        String sql = "SELECT *"
                + "  FROM [dbo].[user]";
        List<User> users = new ArrayList<>();
        try {
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                users.add(new User(rs.getInt(1), rs.getNString(2), rs.getNString(3), rs.getString(4), rs.getDouble(5), rs.getDate(6), rs.getBoolean(7), rs.getInt(8)));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return users;
    }

    public List<User> getAllUserAndUsername(int shopId) {
        PreparedStatement stm;
        ResultSet rs;
        String sql = "select distinct [user].*, account.username from [user] join account on [user].accountId = account.accountId join [order] on [order].userId = [user].userId\n"
                + "													   join orderDetail on orderDetail.orderId = [order].orderId \n"
                + "													   join productItem on orderDetail.productItemId = productItem.productItemId\n"
                + "													   join shopProduct on shopProduct.id = productItem.shopProductId\n"
                + "													   where shopProduct.shopId = ?";
        List<User> users = new ArrayList<>();
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, shopId);
            rs = stm.executeQuery();
            while (rs.next()) {
                User u = new User(rs.getInt(1), rs.getNString(2), rs.getNString(3), rs.getString(4), rs.getDouble(5), rs.getDate(6), rs.getBoolean(7), rs.getInt(8));
                u.setUsername(rs.getString("username"));
                users.add(u);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return users;
    }

    public List<User> getUserByFullName(String fullName) {
        List<User> userInfo = new ArrayList<>();
        String sql = """
                SELECT * 
                 FROM account
                 JOIN [user] ON [user].accountId = account.accountId
                 WHERE [user].fullName LIKE ?
                 """;
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            // Set the parameter with wildcard characters
            st.setString(1, "%" + fullName + "%");

            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    User user = new User(
                            rs.getInt("userId"), rs.getString("fullname"), rs.getString("image")
                    );
                    userInfo.add(user);
                }
            }
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
            e.printStackTrace();
        }
        return userInfo;
    }

}
