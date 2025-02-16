/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.User;

/**
 *
 * @author GiaKhiem
 */
public class UserDAO extends DBContext{
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
}
