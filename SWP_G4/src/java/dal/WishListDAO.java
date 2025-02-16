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
import model.WishList;

/**
 *
 * @author GiaKhiem
 */
public class WishListDAO extends DBContext{
    public List<WishList> getAllWishList(int userId) {
        PreparedStatement stm;
        ResultSet rs;
        List<WishList> list = new ArrayList<>();
        String sql = "SELECT [wishListId]\n"
                + "      ,[userId]\n"
                + "      ,[shopProductId]\n"
                + "      ,[createdAt]\n"
                + "  FROM [dbo].[wishlist] \n"
                + "  where userId = ?";
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, userId);
            rs = stm.executeQuery();
            while (rs.next()) {
                WishList w = new WishList(rs.getInt("wishListId"), rs.getInt("userId"), rs.getInt("shopProductId"), rs.getDate("createdAt"));
                list.add(w);
            }
            return list;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
    public WishList checkWishList(int shopProductId, int userId) {
        PreparedStatement stm;
        ResultSet rs;
        String sql = "SELECT [wishListId]\n"
                + "      ,[userId]\n"
                + "      ,[shopProductId]\n"
                + "      ,[createdAt]\n"
                + "  FROM [dbo].[wishlist] where userId = ? and shopProductId = ?";
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, userId);
            stm.setInt(2, shopProductId);
            rs = stm.executeQuery();
            if (rs.next()) {
                return new WishList(rs.getInt("wishListId"), rs.getInt("userId"), rs.getInt("shopProductId"), rs.getDate("createdAt"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
}
