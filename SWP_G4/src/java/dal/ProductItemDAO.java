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
import model.Color;
import model.ProductItem;
import model.ShopProduct;
import model.Size;

/**
 *
 * @author GiaKhiem
 */
public class ProductItemDAO extends DBContext{
    public ProductItem getProductByID(int id) {
        ProductItem product = null;
        String sql = "select productItemId,shopProductId,shopId,productItem.sizeId,productItem.colorId,sizeValue,colorValue,quantity,price,description,image,title\n"
                + "from productItem\n"
                + "join color on productItem.colorId=color.colorId\n"
                + "join size on productItem.sizeId=size.sizeId\n"
                + "join shopProduct on shopProduct.id= productItem.shopProductId\n"
                + "where shopProductId= ? ";
        //chay lenhj truy van
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int productItemId = rs.getInt("productItemId");
                int shopProductId = rs.getInt("shopProductId");
                int sizeId = rs.getInt("sizeId");
                int colorId = rs.getInt("colorId");
                int quantity = rs.getInt("quantity");

                int sizeValue = rs.getInt("sizeValue");
                String colorValue = rs.getString("colorValue");
                int price = rs.getInt("price");
                String description = rs.getString("description");
                String image = rs.getString("image");
                String title = rs.getString("title");
                int shopId = rs.getInt("shopId");
                ShopProduct shopProduct = new ShopProduct(price, shopId, price, description, title, image);

                product = new ProductItem(productItemId, shopProductId, sizeId, colorId, quantity, sizeValue, colorValue, shopProduct);

            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return product;
    }
    public List<Color> getAllProductColor(int id) {
        List<Color> list = new ArrayList<>();
        String sql = "select distinct color.colorId, color.colorValue from shopProduct join productItem on shopProduct.id = productItem.shopProductId join color on color.colorId = productItem.colorId\n"
                + "where shopProduct.id= ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int colorId = rs.getInt("colorId");
                String colorValue = rs.getString("colorValue");
                Color color = new Color(colorId, colorValue);

                list.add(color);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return list;
    }
    public List<Size> getAllProductSize(int id) {
        List<Size> list = new ArrayList<>();
        String sql = " select distinct size.sizeId, size.sizeValue\n"
                + "from shopProduct \n"
                + "join productItem on shopProduct.id = productItem.shopProductId \n"
                + "join size on size.sizeId = productItem.sizeId \n"
                + "where shopProduct.id= ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int sizeId = rs.getInt("sizeId");
                int sizeValue = rs.getInt("sizeValue");
                Size size = new Size(sizeId, sizeValue);

                list.add(size);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return list;
    }
    public ProductItem getSumQuantityByShopProductId(int shopProductId) {
        String sql = "SELECT productItem.shopProductId, SUM(productItem.quantity) AS sumquantity \n"
                + "FROM productItem \n"
                + "WHERE productItem.shopProductId = ?\n"
                + "GROUP BY productItem.shopProductId";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, shopProductId);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new ProductItem(rs.getInt("shopProductId"), rs.getInt("sumquantity"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return null;
    }
    public ProductItem getQuantity(int shopProductId, int sizeId, int colorId) {
        String sql = "select * from productItem where productItem.shopProductId = ? and productItem.colorId = ? and productItem.sizeId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, shopProductId);
            st.setInt(2, colorId);
            st.setInt(3, sizeId);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new ProductItem(rs.getInt("ProductItemId"), rs.getInt("shopProductId"), rs.getInt("sizeId"), rs.getInt("colorId"), rs.getInt("quantity"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return null;
    }
    
    public List<Size> getAvailableSizesByColor(int shopProductId, int colorId) {
        List<Size> availableSizes = new ArrayList<>();
        String sql = "SELECT DISTINCT s.sizeId, s.sizeValue " +
                     "FROM productItem p " +
                     "JOIN size s ON p.sizeId = s.sizeId " +
                     "WHERE p.shopProductId = ? AND p.colorId = ? AND p.quantity > 0";

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, shopProductId);
            st.setInt(2, colorId);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                int sizeId = rs.getInt("sizeId");
                int sizeValue = rs.getInt("sizeValue");
                availableSizes.add(new Size(sizeId, sizeValue));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return availableSizes;
    }

    
}
