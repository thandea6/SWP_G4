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
    public List<ProductLine> getAllProductByShop(int accountId) {
        List<ProductLine> productList = new ArrayList<>();
        String sql = "SELECT sp.id,p.productLineId,p.productLineName, sp.title, sp.image, sp.price , b.brandName, c.categoryName\n"
                + "                FROM shop s \n"
                + "                JOIN shopProduct sp ON s.shopId = sp.shopId \n"
                + "                JOIN productLine p ON sp.productLineId = p.productLineId \n"
                + "                JOIN brand b ON b.brandId = p.brandId \n"
                + "				JOIN category c ON c.categoryId = p.categoryId\n"
                + "                WHERE s.accountId = ? AND sp.is_deleted = 'FALSE'\n"
                + "                order by sp.id DESC";

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, accountId);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    ProductLine productLine = new ProductLine(
                            rs.getInt("id"),
                            rs.getInt("productLineId"),
                            rs.getString("productLineName"),
                            rs.getInt("price"),
                            rs.getString("image"),
                            rs.getString("title"),
                            rs.getString("categoryName"),
                            rs.getString("brandName")
                    );
                    productList.add(productLine);
                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi cơ sở dữ liệu: " + e.getMessage());
            e.printStackTrace();
        }

        return productList;
    }
    
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
    
    public ProductLine getInfoProductById(int productId, int accountId) {
        List<ProductLine> productList = new ArrayList<>();
        String sql = "select sp.id,p.productLineId,p.productLineName,sp.image,sp.description,sp.price,b.brandId,brandName,c.categoryId,categoryName,sp.title from productLine p\n"
                + "JOIN shopProduct sp on p.productLineId = sp.productLineId\n"
                + "JOIN brand b on b.brandId = p.brandId\n"
                + "JOIN category c on c.categoryId =p.categoryId\n"
                + "JOIN shop s on sp.shopId =s.shopId\n"
                + "where sp.id =? and s.accountId=?";

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, productId);
            st.setInt(2, accountId);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    return new ProductLine(rs.getInt("id"),
                            rs.getInt("productLineId"),
                            rs.getString("productLineName"),
                            rs.getInt("categoryId"),
                            rs.getInt("brandId"),
                            rs.getInt("price"),
                            rs.getString("image"),
                            rs.getString("categoryName"),
                            rs.getString("brandName"),
                            rs.getString("description"),
                            rs.getString("title"));

                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi cơ sở dữ liệu: " + e.getMessage());
        }

        return null;
    }
    
    public List<ProductLine> getAllProductLine() {
        List<ProductLine> productList = new ArrayList<>();
        String sql = "Select * from productLine";

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    ProductLine productLine = new ProductLine(rs.getInt("productLineId"), rs.getString("productLineName"));
                    productList.add(productLine);
                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi cơ sở dữ liệu: " + e.getMessage());
            e.printStackTrace();
        }

        return productList;
    }
    
    public void updateProductLineName(int productId, int productLineId) {
        String sql = """
                     UPDATE shopProduct
                     SET productLineId = ?
                     WHERE id = ?""";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, productLineId);
            st.setInt(2, productId);
            st.executeUpdate();
        } catch (SQLException e) {

        }
    }
    
    public List<ProductLine> getAllProductShopBySearch(int accountId, String text) {
        List<ProductLine> productList = new ArrayList<>();
        String sql = "SELECT sp.id,p.productLineId,p.productLineName, sp.title, sp.image, sp.price, b.brandName, c.categoryName\n"
                + "                FROM shop s\n"
                + "                JOIN shopProduct sp ON s.shopId = sp.shopId \n"
                + "                JOIN productLine p ON sp.productLineId = p.productLineId \n"
                + "                JOIN brand b ON b.brandId = p.brandId\n"
                + "				JOIN category c ON c.categoryId = p.categoryId\n"
                + "                WHERE s.accountId = ? AND p.productLineName like ? AND sp.is_deleted = 'FALSE'\n"
                + "                order by sp.id DESC";

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, accountId);
            st.setString(2, "%" + text + "%");
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    ProductLine productLine = new ProductLine(
                            rs.getInt("id"),
                            rs.getInt("productLineId"),
                            rs.getString("productLineName"),
                            rs.getInt("price"),
                            rs.getString("image"),
                            rs.getString("title"),
                            rs.getString("categoryName"),
                            rs.getString("brandName")
                    );
                    productList.add(productLine);
                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi cơ sở dữ liệu: " + e.getMessage());
            e.printStackTrace();
        }

        return productList;
    }
    
    public List<ProductLine> getQuantityProductById(int productId, int accountId) {
        List<ProductLine> productList = new ArrayList<>();
        String sql = "SELECT \n"
                + "    sp.productLineId,\n"
                + "	c.colorId,\n"
                + "   c.colorValue,\n"
                + "   sz.sizeId,\n"
                + "   sz.sizeValue,\n"
                + "      proi.quantity\n"
                + "FROM \n"
                + "                    productItem proi\n"
                + "        JOIN \n"
                + "                  shopProduct sp ON proi.shopProductId = sp.id\n"
                + "             JOIN \n"
                + "                productLine p ON sp.productLineId = p.productLineId\n"
                + "              JOIN \n"
                + "                 color c ON proi.colorId = c.colorId\n"
                + "                JOIN \n"
                + "                    size sz ON proi.sizeId = sz.sizeId\n"
                + "                JOIN \n"
                + "                	shop s ON s.shopId = sp.shopId\n"
                + "                WHERE \n"
                + "                   sp.id = ? and s.accountId =? and proi.quantity>0";

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, productId);
            st.setInt(2, accountId);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    ProductLine productLine = new ProductLine(rs.getInt("productLineId"),
                            rs.getInt("colorId"),
                            rs.getString("colorValue"),
                            rs.getInt("sizeId"),
                            rs.getInt("sizeValue"),
                            rs.getInt("quantity"));
                    productList.add(productLine);
                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi cơ sở dữ liệu: " + e.getMessage());
            e.printStackTrace();
        }

        return productList;
    }
    
    public List<ProductLine> getQuantityProductBySize(int productId, int accountId, int sizeId) {
        List<ProductLine> productList = new ArrayList<>();
        String sql = "SELECT \n"
                + "    sp.productLineId,\n"
                + "	c.colorId,\n"
                + "   c.colorValue,\n"
                + "   sz.sizeId,\n"
                + "   sz.sizeValue,\n"
                + "      proi.quantity\n"
                + "FROM \n"
                + "    productItem proi\n"
                + "JOIN \n"
                + "    shopProduct sp ON proi.shopProductId = sp.id\n"
                + "JOIN \n"
                + "    productLine p ON sp.productLineId = p.productLineId\n"
                + "JOIN \n"
                + "    color c ON proi.colorId = c.colorId\n"
                + "JOIN \n"
                + "    size sz ON proi.sizeId = sz.sizeId\n"
                + "JOIN \n"
                + "	shop s ON s.shopId = sp.shopId\n"
                + "WHERE \n"
                + "    sp.id = ? and s.accountId =? and proi.quantity>0 and sz.sizeId =?";

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, productId);
            st.setInt(2, accountId);
            st.setInt(3, sizeId);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    ProductLine productLine = new ProductLine(rs.getInt("productLineId"),
                            rs.getInt("colorId"),
                            rs.getString("colorValue"),
                            rs.getInt("sizeId"),
                            rs.getInt("sizeValue"),
                            rs.getInt("quantity"));
                    productList.add(productLine);
                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi cơ sở dữ liệu: " + e.getMessage());
            e.printStackTrace();
        }

        return productList;
    }
    public List<ProductLine> getQuantityProductByColor(int productId, int accountId, int colorId) {
        List<ProductLine> productList = new ArrayList<>();
        String sql = "SELECT \n"
                + "    sp.productLineId,\n"
                + "	c.colorId,\n"
                + "   c.colorValue,\n"
                + "   sz.sizeId,\n"
                + "   sz.sizeValue,\n"
                + "      proi.quantity\n"
                + "FROM \n"
                + "    productItem proi\n"
                + "JOIN \n"
                + "    shopProduct sp ON proi.shopProductId = sp.id\n"
                + "JOIN \n"
                + "    productLine p ON sp.productLineId = p.productLineId\n"
                + "JOIN \n"
                + "    color c ON proi.colorId = c.colorId\n"
                + "JOIN \n"
                + "    size sz ON proi.sizeId = sz.sizeId\n"
                + "JOIN \n"
                + "	shop s ON s.shopId = sp.shopId\n"
                + "WHERE \n"
                + "    sp.id = ? and s.accountId =? and proi.quantity>0 and c.colorId =?";

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, productId);
            st.setInt(2, accountId);
            st.setInt(3, colorId);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    ProductLine productLine = new ProductLine(rs.getInt("productLineId"),
                            rs.getInt("colorId"),
                            rs.getString("colorValue"),
                            rs.getInt("sizeId"),
                            rs.getInt("sizeValue"),
                            rs.getInt("quantity"));
                    productList.add(productLine);
                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi cơ sở dữ liệu: " + e.getMessage());
            e.printStackTrace();
        }

        return productList;
    }
    
    public List<ProductLine> getQuantityProductByColorAndSize(int productId, int accountId, int colorId, int sizeId) {
        List<ProductLine> productList = new ArrayList<>();
        String sql = "SELECT \n"
                + "    sp.productLineId,\n"
                + "	c.colorId,\n"
                + "   c.colorValue,\n"
                + "   sz.sizeId,\n"
                + "   sz.sizeValue,\n"
                + "      proi.quantity\n"
                + "FROM \n"
                + "    productItem proi\n"
                + "JOIN \n"
                + "    shopProduct sp ON proi.shopProductId = sp.id\n"
                + "JOIN \n"
                + "    productLine p ON sp.productLineId = p.productLineId\n"
                + "JOIN \n"
                + "    color c ON proi.colorId = c.colorId\n"
                + "JOIN \n"
                + "    size sz ON proi.sizeId = sz.sizeId\n"
                + "JOIN \n"
                + "	shop s ON s.shopId = sp.shopId\n"
                + "WHERE \n"
                + "    sp.id = ? and s.accountId =? and proi.quantity>0 and c.colorId=? and sz.sizeId =?";

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, productId);
            st.setInt(2, accountId);
            st.setInt(3, colorId);
            st.setInt(4, sizeId);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    ProductLine productLine = new ProductLine(rs.getInt("productLineId"),
                            rs.getInt("colorId"),
                            rs.getString("colorValue"),
                            rs.getInt("sizeId"),
                            rs.getInt("sizeValue"),
                            rs.getInt("quantity"));
                    productList.add(productLine);
                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi cơ sở dữ liệu: " + e.getMessage());
            e.printStackTrace();
        }

        return productList;
    }
}
