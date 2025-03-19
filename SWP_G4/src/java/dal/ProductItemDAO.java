/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import model.Account;
import model.Cart;
import model.Color;
import model.Item;
import model.ProductItem;
import model.Shop;
import model.ShopProduct;
import model.Size;
import model.User;
import java.sql.Types;
import model.Voucher;

/**
 *
 * @author admin
 */
public class ProductItemDAO extends DBContext {

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

    public int getQuantitybyProductItemId(int ProductItemId) {
        String sql = "SELECT productItemId, [quantity]\n"
                + "  FROM [dbo].[productItem] where productItem.productItemId =" + ProductItemId;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                int quantity = rs.getInt("quantity");
                return quantity;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
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

    public ProductItem getQuantityByColorId(int colorId) {
        String sql = "select * from productItem\n"
                + "where colorId= ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, colorId);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new ProductItem(rs.getInt("shopProductId"), rs.getInt("sizeId"), rs.getInt("colorId"), rs.getInt("quantity"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return null;
    }

    public List<ProductItem> getAllProductItem() {
        List<ProductItem> list = new ArrayList<>();
        String sql = "SELECT * FROM [dbo].[productItem] join shopProduct on productItem.shopProductId = shopProduct.id join color on productItem.colorId = color.colorId join size on productItem.sizeId = size.sizeId join shop on shopProduct.shopId = shop.shopId";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int productItemId = rs.getInt("productItemId");
                int shopProductId = rs.getInt("shopProductId");
                int sizeId = rs.getInt("sizeId");
                int colorId = rs.getInt("colorId");
                int quantity = rs.getInt("quantity");
                int sizeValue = rs.getInt("sizeValue");
                String colorValue = rs.getString("colorValue");
                double price = rs.getDouble("price");
                String description = rs.getString("description");
                String image = rs.getString("image");
                String title = rs.getString("title");
                int shopId = rs.getInt("shopId");
                String shopName = rs.getString("shopName");

                Shop shop = new Shop(shopId, shopName, image);
                ShopProduct shopProduct = new ShopProduct(shopProductId, price, image, title);
                ProductItem product = new ProductItem(productItemId, shopProductId, sizeId, colorId, quantity, sizeValue, colorValue, shopProduct, shop);
                list.add(product);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return list;
    }

    public ProductItem getProductItemByProductItemID(int id) {
        ProductItem product = null;
        String sql = "SELECT * FROM [dbo].[productItem] join shopProduct on productItem.shopProductId = shopProduct.id join color on productItem.colorId = color.colorId join size on productItem.sizeId = size.sizeId join shop on shopProduct.shopId = shop.shopId where productItem.productItemId = ?";
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
                String shopName = rs.getString("shopName");
                int shopId = rs.getInt("shopId");
                Shop shop = new Shop(shopId, shopName, image);
                ProductItem productItem = new ProductItem(rs.getInt("productItemId"), rs.getInt("quantity"));
                ShopProduct shopProduct = new ShopProduct(shopId, price, rs.getString("image"), title);

                product = new ProductItem(productItemId, shopProductId, sizeId, colorId, quantity, sizeValue, colorValue, shopProduct);

            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return product;
    }

    public void updateProductItem(int shopProductId, int quantity, int accountId, int sizeId, int colorId) {
        String updateSql = "UPDATE pi\n"
                + "SET pi.quantity = ?\n"
                + "FROM productItem pi\n"
                + "JOIN shopProduct sp ON pi.shopProductId = sp.id\n"
                + "JOIN shop s ON sp.shopId = s.shopId\n"
                + "WHERE sp.id = ?\n"
                + "  AND s.accountId = ?\n"
                + "  AND pi.sizeId = ?\n"
                + "  AND pi.colorId = ?";

        String insertSql = "INSERT INTO productItem (shopProductId, sizeId, colorId, quantity) "
                + "VALUES (?, ?, ?, ?)";

        try (PreparedStatement updateStmt = connection.prepareStatement(updateSql); PreparedStatement insertStmt = connection.prepareStatement(insertSql)) {

            // Try to update the product item first
            updateStmt.setInt(1, quantity);
            updateStmt.setInt(2, shopProductId);
            updateStmt.setInt(3, accountId);
            updateStmt.setInt(4, sizeId);
            updateStmt.setInt(5, colorId);
            int rowsUpdated = updateStmt.executeUpdate();

            // If no rows were updated, insert a new product item
            if (rowsUpdated == 0) {
                insertStmt.setInt(1, shopProductId);
                insertStmt.setInt(2, sizeId);
                insertStmt.setInt(3, colorId);
                insertStmt.setInt(4, quantity);
                insertStmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

public void addOrder(User u, Cart cart, String name, String address, String phone) {
        LocalDateTime curDate = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String date = curDate.format(formatter);
        try {
            String sql = "INSERT INTO [Order] (userId, orderDate, totalMoney, name, address, phone) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, u.getUserId());
            st.setString(2, date);
            st.setDouble(3, cart.getTotalMoney());
            st.setString(4, name);
            st.setString(5, address);
            st.setString(6, phone);
            st.executeUpdate();
            System.out.println("add order");
            String sql1 = "SELECT TOP 1 orderId FROM [order] ORDER BY orderId DESC";
            PreparedStatement st1 = connection.prepareStatement(sql1);
            ResultSet rs = st1.executeQuery();
            if (rs.next()) {
                int oid = rs.getInt("orderId");
                for (Item i : cart.getItems()) {
                    String sql2 = "INSERT INTO [dbo].[orderDetail]\n"
                            + "           ([orderId]\n"
                            + "           ,[quantity]\n"
                            + "           ,[price]\n"
                            + "           ,[productItemId]\n"
                            + "           ,[statusId]\n"
                            + "           ,[voucherId])\n"
                            + "     VALUES\n"
                            + "           (?,?,?,?,?,?)";
                    PreparedStatement st2 = connection.prepareStatement(sql2);
                    VoucherDAO vd = new VoucherDAO();
                    Voucher v = vd.getVoucherByCodeAndUserId(i.getCode(), u.getUserId());
                    st2.setInt(1, oid);
                    st2.setInt(2, i.getQuantity());
                    double finalPrice = (i.getPromotionalPrice() > 0) ? i.getPromotionalPrice() : i.getPrice();
                    if (v != null) {
                        double money = finalPrice * i.getQuantity() - v.getReducedAmount();
                        if(money <=0){
                            money = 0;
                        }
                        st2.setDouble(3, money);
                    } else {
                        st2.setDouble(3, finalPrice * i.getQuantity());
                    }
                    st2.setInt(4, i.getProductItem().getProductItemId());
                    st2.setInt(5, 1);
                    if (v != null) {
                        st2.setInt(6, v.getVoucherId());
                        if (v.getQuantity() > 1) {
                            String sql3 = "UPDATE [dbo].[voucherUser]\n"
                                    + "   SET [quantity] = ?\n"
                                    + " WHERE voucherId = ? and userId = ?";
                            PreparedStatement st3 = connection.prepareStatement(sql3);

                            st3.setInt(1, v.getQuantity() - 1);
                            st3.setInt(2, v.getVoucherId());
                            st3.setInt(3, u.getUserId());
                            st3.executeUpdate();
                        }
                        if (v.getQuantity() <= 1) {
                            String sql4 = "UPDATE [dbo].[voucherUser]\n"
                                    + "   SET [isUsed] = 'True'\n"
                                    + "   ,[quantity] = 0\n"
                                    + " WHERE voucherId = ? and userId = ?";
                            PreparedStatement st4 = connection.prepareStatement(sql4);
                            st4.setInt(1, v.getVoucherId());
                            st4.setInt(2, u.getUserId());
                            st4.executeUpdate();
                        }
                    } else {
                        st2.setNull(6, Types.INTEGER);
                    }
                    st2.executeUpdate();
                }

            }

            // Cập nhật số lượng sản phẩm
            String sql3 = "UPDATE productItem SET quantity = quantity - ? WHERE productItemId = ?";
            PreparedStatement st3 = connection.prepareStatement(sql3);
            for (Item i : cart.getItems()) {
                st3.setInt(1, i.getQuantity());
                st3.setInt(2, i.getProductItem().getProductItemId());
                st3.executeUpdate();
            }
            System.out.println("add done");
        } catch (SQLException e) {
            e.printStackTrace(); // In ra lỗi để xác định và sửa chúng sau này
        }
    }

    public List<ProductItem> getProductItemByCheckBoxId(int[] pid) {
        List<ProductItem> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM [dbo].[productItem] join shopProduct on productItem.shopProductId = shopProduct.id join color on productItem.colorId = color.colorId join size on productItem.sizeId = size.sizeId join shop on shopProduct.shopId = shop.shopId where ";

            if (pid != null && pid[0] != 0) {
                sql += "productItem.productItemId in (";
                for (int i = 0; i < pid.length; i++) {
                    sql += pid[i] + ",";
                }
                if (sql.endsWith(",")) {
                    sql = sql.substring(0, sql.length() - 1);
                }
                sql += ")";

            }
            PreparedStatement st = connection.prepareStatement(sql);
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
                String shopName = rs.getString("shopName");
                int shopId = rs.getInt("shopId");
                Shop shop = new Shop(shopId, shopName, image);
                ProductItem productItem = new ProductItem(rs.getInt("productItemId"), rs.getInt("quantity"));
                ShopProduct shopProduct = new ShopProduct(shopId, price, rs.getString("image"), title);

                ProductItem product = new ProductItem(productItemId, shopProductId, sizeId, colorId, quantity, sizeValue, colorValue, shopProduct, shop);
                list.add(product);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return list;
    }

    public static void main(String[] args) {
        ProductItemDAO dpdao = new ProductItemDAO();
        int quan = dpdao.getQuantitybyProductItemId(1);
        LocalDateTime curDate = LocalDateTime.now();
        System.out.println(quan);
        System.out.println(curDate);
    }

}
