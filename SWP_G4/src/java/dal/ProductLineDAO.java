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

/**
 *
 * @author GiaKhiem
 */
public class ProductLineDAO extends DBContext{
    public List<ProductLine> getProductLine() {
        List<ProductLine> productList = new ArrayList<>();
        String sql = """
                     select  pro.*,c.categoryName,b.brandName from productLine pro
                     JOIN category c ON pro.categoryId =c.categoryId
                     JOIN brand b ON pro.brandId =b.brandId""";

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    ProductLine productLine = new ProductLine(rs.getInt("productLineId"),
                            rs.getString("productLineName"),
                            rs.getInt("categoryId"),
                            rs.getInt("brandId"),
                            rs.getString("categoryName"),
                            rs.getString("brandName"));
                    productList.add(productLine);
                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi cơ sở dữ liệu: " + e.getMessage());
        }

        return productList;
    }
    
    public ProductLine getProductLineById(int id) {
        String sql = """
                     select  pro.*,c.categoryName,b.brandName from productLine pro
                                          JOIN category c ON pro.categoryId =c.categoryId
                                          JOIN brand b ON pro.brandId =b.brandId
                     					 where pro.productLineId =?""";

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, id);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    return new ProductLine(rs.getInt("productLineId"),
                            rs.getString("productLineName"),
                            rs.getInt("categoryId"),
                            rs.getInt("brandId"),
                            rs.getString("categoryName"),
                            rs.getString("brandName"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi cơ sở dữ liệu: " + e.getMessage());
        }

        return null;
    }
    
    public void addProductLine(String name, int categoryId, int brandId) {
        try {
            String sql = """
                         INSERT INTO [dbo].[productLine]
                                    ([productLineName]
                                    ,[categoryId]
                                    ,[brandId])
                              VALUES
                                    (?,?,?)""";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, name);
            st.setInt(2, categoryId);
            st.setInt(3, brandId);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void updateProductLine(int id, String name, int categoryId, int brandId) {
        try {
            String sql = """
                         UPDATE [dbo].[productLine]
                                       SET [productLineName] = ?
                                          ,[categoryId] = ?
                                          ,[brandId] = ?
                         where productLineId =?""";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, name);
            st.setInt(2, categoryId);
            st.setInt(3, brandId);
            st.setInt(4, id);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
