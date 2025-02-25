/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Shop;

/**
 *
 * @author GiaKhiem
 */
public class ShopDAO extends DBContext{
    public Shop getCountProductOfShopByShopId(int ShopId) {
        String sql = "SELECT shop.shopId,COUNT(DISTINCT shopProduct.productLineId) AS NumberOfProductLineId\n"
                + "FROM shop\n"
                + "JOIN shopProduct ON shopProduct.shopId = shop.shopId\n"
                + "WHERE shop.shopId = ?\n"
                + "group by shop.shopId";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, ShopId);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    return new Shop(rs.getInt("shopId"), rs.getInt("NumberOfProductLineId"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi cơ sở dữ liệu: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
    public Shop getShopByShopProductId(int ShopProductId) {
        String sql = "select shop.shopId, shop.shopName, shop.image from shop\n"
                + "join shopProduct on shopProduct.shopId=shop.shopId\n"
                + "where shopProduct.id=?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, ShopProductId);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    return new Shop(rs.getInt("shopId"),
                            rs.getString("shopName"),
                            rs.getString("image"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi cơ sở dữ liệu: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
    
    public Shop getShopByShopShopId(int ShopId) {
        String sql = "select shop.shopId, shop.shopName, shop.image from shop\n"
                + "join shopProduct on shopProduct.shopId=shop.shopId\n"
                + "where shop.shopId=?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, ShopId);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    return new Shop(rs.getInt("shopId"),
                            rs.getString("shopName"),
                            rs.getString("image"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi cơ sở dữ liệu: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}
