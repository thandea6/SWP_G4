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
import model.ProductLine;
import model.Shop;

/**
 *
 * @author VIET HOANG
 */
public class ShopDAO extends DBContext {

    public List<Shop> getShop(int accountId) {
        List<Shop> shopInfo = new ArrayList<>();
        String sql = "select * from shop\n"
                + "where shop.accountId =?";

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, accountId);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    Shop shop = new Shop(rs.getInt("shopId"),
                            rs.getString("shopName"),
                            rs.getString("address"),
                            rs.getString("image"),
                            rs.getDouble("accountBalance"),
                            rs.getInt("accountId"));
                    shopInfo.add(shop);
                    break;
                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi cơ sở dữ liệu: " + e.getMessage());
            e.printStackTrace();
        }

        return shopInfo;
    }

    //lay ra thong tin chi tiet cua shop by accountId
    public Shop getShopByAccountId(int accountId) {
        String sql = "SELECT s.shopId,s.shopName, s.address, s.image, s.accountId, a.phone, a.email FROM shop s "
                + "JOIN account a ON s.accountId = a.accountId "
                + "WHERE s.accountId = ?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, accountId);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    return new Shop(
                            rs.getInt("shopId"),
                            rs.getString("shopName"),
                            rs.getString("address"),
                            rs.getString("image"),
                            rs.getInt("accountId"),
                            rs.getString("phone"),
                            rs.getString("email"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi cơ sở dữ liệu: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public void update(int accountId, String address, String image) {
        String sql = "UPDATE [dbo].[shop] " // 
                + "SET [address] = ?, "
                + " [image] = ? "
                + "WHERE accountId = ?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, address);
            st.setString(2, image);
            st.setInt(3, accountId);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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

    //Duc
    public int addShop(Shop p) {
        String sql = "Insert into shop (shopName, accountId) values (?, ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, p.getShopName());
            st.setInt(2, p.getAccountId());
            int result = st.executeUpdate();
            return result;
        } catch (Exception e) {
            System.out.println("Add shop: " + e);
        }
        return 0;
    }

    public List<Shop> getAllShop() {
        List<Shop> shopInfo = new ArrayList<>();
        String sql = "select * from shop";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    Shop shop = new Shop(rs.getInt("shopId"),
                            rs.getString("shopName"),
                            rs.getString("address"),
                            rs.getString("image"),
                            rs.getDouble("accountBalance"),
                            rs.getInt("accountId"));
                    shopInfo.add(shop);
                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi cơ sở dữ liệu: " + e.getMessage());
            e.printStackTrace();
        }
        return shopInfo;
    }

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

    public List<Shop> getShopByShopName(String shopName) {
        List<Shop> shopInfo = new ArrayList<>();
        String sql = """
                 SELECT * 
                 FROM account
                 JOIN shop ON shop.accountId = account.accountId
                 WHERE shop.shopName LIKE ?
                 """;
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            // Set the parameter with wildcard characters
            st.setString(1, "%" + shopName + "%");

            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    Shop shop = new Shop(
                            rs.getInt("shopId"),
                            rs.getString("shopName"),
                            rs.getString("address"),
                            rs.getString("image"),
                            rs.getDouble("accountBalance"),
                            rs.getInt("accountId")
                    );
                    shopInfo.add(shop);
                }
            }
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
            e.printStackTrace();
        }
        return shopInfo;
    }

    public static void main(String[] args) {
        ShopDAO sd = new ShopDAO();
        Shop a = sd.getShopByAccountId(3);
        System.out.println(a);
        System.out.println("hihihi");
    }
}
