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
import model.Category;
import model.ShopProduct;
import dal.CategoryDAO;
import java.lang.reflect.Array;
import java.sql.Date;
import model.Images;
import model.ProductLine;
import model.Shop;

/**
 *
 * @author VIET HOANG
 */
public class ShopProductDAO extends DBContext {

    public List<ShopProduct> getShopProductsAll() {
        List<ShopProduct> list = new ArrayList<>();
        String sql = """
                     SELECT 
                                 sp.*,
                                 pl.*,
                                 c.*,
                                 b.*,
                                 s.*,
                                 COALESCE(dis.promotionalPrice, 0) AS discountPrice,
                                 COALESCE(dis.discountValue, 0) AS discountValue,
                                 COALESCE(r.averageRating, 0) AS averageRating,
                                 COALESCE(od.totalSold, 0) AS totalSold
                             FROM 
                                 shopProduct sp
                             JOIN 
                                 productLine pl ON sp.productLineId = pl.productLineId
                             JOIN 
                                 category c ON pl.categoryId = c.categoryId
                             JOIN 
                                 brand b ON pl.brandId = b.brandId
                             JOIN 
                                 shop s ON sp.shopId = s.shopId
                             LEFT JOIN 
                                 discount dis ON dis.shopProductId = sp.id 
                                 AND GETDATE() >= dis.startDate AND GETDATE() <= dis.endDate
                             LEFT JOIN 
                                 (SELECT shopProductId, AVG(starRating) AS averageRating 
                                  FROM rating 
                                  GROUP BY shopProductId) r ON r.shopProductId = sp.id
                             LEFT JOIN 
                                 (SELECT 
                                     sp.id AS shopProductId,
                                     SUM(od.quantity) AS totalSold
                                  FROM 
                                     orderDetail od
                                  JOIN 
                                     productItem pi ON od.productItemId = pi.productItemId
                                  JOIN 
                                     shopProduct sp ON pi.shopProductId = sp.id
                                  WHERE 
                                     od.statusId = 3
                                  GROUP BY 
                                     sp.id
                                 ) od ON od.shopProductId = sp.id;""";

        try (PreparedStatement st = connection.prepareStatement(sql); ResultSet rs = st.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                int shopId = rs.getInt("shopId");
                int productLineId = rs.getInt("productLineId");
                double price = rs.getDouble("price");
                String description = rs.getString("description");
                Date createdAt = rs.getDate("created_at");
                Date updatedAt = rs.getDate("updated_at");
                boolean isDeleted = rs.getBoolean("is_deleted");
                int averageRating = rs.getInt("averageRating");
                int discount = rs.getInt("discountValue");
                double discountprice = rs.getDouble("discountPrice");
                // ProductLine fields
                String productLineName = rs.getString("productLineName");
                int categoryId = rs.getInt("categoryId");
                int brandId = rs.getInt("brandId");
                // Category fields
                String categoryName = rs.getString("categoryName");
                String shopName = rs.getString("shopName");

                // Optionally create ProductLine and Category objects if needed
                ProductLine productLine = new ProductLine(productLineId, rs.getString("ProductLineName"), categoryId, brandId);
                Category category = new Category(categoryId, categoryName);
                String image = rs.getString("image");
                String title = rs.getString("title");
                Shop shop = new Shop(shopId, shopName, rs.getString("address"), image, rs.getDouble("accountBalance"), rs.getInt("accountID"));

//                    private int shopId;
//    private String shopName;
//    private String address;
//    private String image;
//    private double accountBalance;
//    private int accountId;
                // Create ShopProduct object
                ShopProduct shopProduct = new ShopProduct(id, shopId, productLine, price, description, createdAt, updatedAt, isDeleted, title, averageRating, shop, image, rs.getInt("totalSold"), discount, discountprice);

                list.add(shopProduct);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return list;
    }

    public List<ShopProduct> getShopProductsAllbyWishList(int userId) {
        List<ShopProduct> list = new ArrayList<>();
        String sql = """
                     SELECT 
                                              sp.*,
                                              pl.*,
                                              c.*,
                                              b.*,
                                              s.*,
                                            COALESCE(dis.promotionalPrice, 0) AS discountPrice,
                                             COALESCE(dis.discountValue, 0) AS discountValue,
                                              COALESCE(r.averageRating, 0) AS averageRating,
                                              COALESCE(od.totalSold, 0) AS totalSold
                                          FROM
                                              shopProduct sp
                                          JOIN
                                              productLine pl ON sp.productLineId = pl.productLineId
                                          JOIN
                                              category c ON pl.categoryId = c.categoryId
                                          JOIN 
                                              brand b ON pl.brandId = b.brandId
                                          JOIN
                                              shop s ON sp.shopId = s.shopId
                                          LEFT JOIN
                                              (SELECT shopProductId, AVG(starRating) AS averageRating 
                                               FROM rating 
                                               GROUP BY shopProductId) r 
                                          ON r.shopProductId = sp.id
                                          LEFT JOIN 
                                         discount dis ON dis.shopProductId = sp.id
                      AND GETDATE() >= dis.startDate AND GETDATE() <= dis.endDate
                                          LEFT JOIN
                                              (SELECT
                                                   sp.id AS shopProductId,
                                                   SUM(od.quantity) AS totalSold
                                               FROM
                                                   orderDetail od
                                               JOIN
                                                   productItem pi ON od.productItemId = pi.productItemId
                                               JOIN
                                                   shopProduct sp ON pi.shopProductId = sp.id
                                               WHERE
                                                   od.statusId = 3
                                               GROUP BY
                                                   sp.id) od 
                                          ON od.shopProductId = sp.id
                     LEFT JOIN
                         wishlist w ON sp.id = w.shopProductId AND w.userId = """ + userId + " \n"
                + "ORDER BY\n"
                + "    CASE \n"
                + "        WHEN w.userId = " + userId + " THEN 0 \n"
                + "        ELSE 1 \n"
                + "    END,\n"
                + "    sp.id; ";

        try (PreparedStatement st = connection.prepareStatement(sql); ResultSet rs = st.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                int shopId = rs.getInt("shopId");
                int productLineId = rs.getInt("productLineId");
                double price = rs.getDouble("price");
                String description = rs.getString("description");
                Date createdAt = rs.getDate("created_at");
                Date updatedAt = rs.getDate("updated_at");
                boolean isDeleted = rs.getBoolean("is_deleted");
                int averageRating = rs.getInt("averageRating");
                int discount = rs.getInt("discountValue");
                double discountPrice = rs.getDouble("discountPrice");
                // ProductLine fields
                String productLineName = rs.getString("productLineName");
                int categoryId = rs.getInt("categoryId");
                int brandId = rs.getInt("brandId");
                // Category fields
                String categoryName = rs.getString("categoryName");
                String shopName = rs.getString("shopName");

                // Optionally create ProductLine and Category objects if needed
                ProductLine productLine = new ProductLine(productLineId, rs.getString("ProductLineName"), categoryId, brandId);
                Category category = new Category(categoryId, categoryName);
                String image = rs.getString("image");
                String title = rs.getString("title");
                Shop shop = new Shop(shopId, shopName, rs.getString("address"), image, rs.getDouble("accountBalance"), rs.getInt("accountID"));

                ShopProduct shopProduct = new ShopProduct(id, shopId, productLine, price, description, createdAt, updatedAt, isDeleted, title, averageRating, shop, image, rs.getInt("totalSold"), discount, discountPrice);

                list.add(shopProduct);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return list;
    }

    public List<ShopProduct> getShopProductsByShop(int sid) {
        List<ShopProduct> list = new ArrayList<>();
        String sql = """
                     SELECT 
                         sp.*,
                         pl.*,
                         c.*,
                         b.*,
                         s.*,
                         COALESCE(dis.promotionalPrice, 0) AS discountPrice,
                         COALESCE(dis.discountValue, 0) AS discountValue,
                         COALESCE(r.averageRating, 0) AS averageRating,
                         COALESCE(od.totalSold, 0) AS totalSold
                     FROM 
                         shopProduct sp
                     JOIN 
                         productLine pl ON sp.productLineId = pl.productLineId
                     JOIN 
                         category c ON pl.categoryId = c.categoryId
                     JOIN 
                         brand b ON pl.brandId = b.brandId
                     JOIN 
                         shop s ON sp.shopId = s.shopId
                     LEFT JOIN 
                         discount dis ON dis.shopProductId = sp.id 
                         AND GETDATE() >= dis.startDate AND GETDATE() <= dis.endDate
                     LEFT JOIN 
                         (SELECT shopProductId, AVG(starRating) AS averageRating 
                          FROM rating 
                          GROUP BY shopProductId) r ON r.shopProductId = sp.id
                     LEFT JOIN 
                         (SELECT 
                             sp.id AS shopProductId,
                             SUM(od.quantity) AS totalSold
                          FROM 
                             orderDetail od
                          JOIN 
                             productItem pi ON od.productItemId = pi.productItemId
                          JOIN 
                             shopProduct sp ON pi.shopProductId = sp.id
                          WHERE 
                             od.statusId = 3
                          GROUP BY 
                             sp.id
                         ) od ON od.shopProductId = sp.id
                     WHERE 
                         s.shopId = ? and sp.is_deleted = 0
                     """;

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, sid);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int shopId = rs.getInt("shopId");
                int productLineId = rs.getInt("productLineId");
                double price = rs.getDouble("price");
                String description = rs.getString("description");
                Date createdAt = rs.getDate("created_at");
                Date updatedAt = rs.getDate("updated_at");
                boolean isDeleted = rs.getBoolean("is_deleted");
                int averageRating = rs.getInt("averageRating");
                int discount = rs.getInt("discountValue");
                double discountprice = rs.getDouble("discountPrice");
                // ProductLine fields
                String productLineName = rs.getString("productLineName");
                int categoryId = rs.getInt("categoryId");
                int brandId = rs.getInt("brandId");
                // Category fields
                String categoryName = rs.getString("categoryName");
                String shopName = rs.getString("shopName");

                // Optionally create ProductLine and Category objects if needed
                ProductLine productLine = new ProductLine(productLineId, rs.getString("ProductLineName"), categoryId, brandId);
                Category category = new Category(categoryId, categoryName);
                String image = rs.getString("image");
                String title = rs.getString("title");
                Shop shop = new Shop(shopId, shopName, rs.getString("address"), image, rs.getDouble("accountBalance"), rs.getInt("accountID"));

//                    private int shopId;
//    private String shopName;
//    private String address;
//    private String image;
//    private double accountBalance;
//    private int accountId;
                // Create ShopProduct object
                ShopProduct shopProduct = new ShopProduct(id, shopId, productLine, price, description, createdAt, updatedAt, isDeleted, title, averageRating, shop, image, rs.getInt("totalSold"), discount, discountprice);

                list.add(shopProduct);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return list;
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

    public ShopProduct getShopProductById(int id) {
        String sql = "SELECT * from shopProduct where shopProduct.id=?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, id);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    return new ShopProduct(rs.getInt("id"), rs.getInt("shopId"), rs.getDouble("price"), rs.getString("description"), rs.getString("title"), rs.getString("image"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi cơ sở dữ liệu: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public List<ShopProduct> searchProduct(String key, int[] cid, int[] bid, double minValue, double maxValue) {
        List<ShopProduct> list = new ArrayList<>();
        try {
            String sql = "SELECT \n"
                    + "                         sp.*,\n"
                    + "                         pl.*,\n"
                    + "                         c.*,\n"
                    + "                         b.*,\n"
                    + "                         s.*,\n"
                    + "				COALESCE(dis.promotionalPrice, 0) AS discountPrice,\n"
                    + "                        COALESCE(dis.discountValue, 0) AS discountValue,\n"
                    + "                         COALESCE(r.averageRating, 0) AS averageRating,\n"
                    + "                         COALESCE(od.totalSold, 0) AS totalSold\n"
                    + "                     FROM\n"
                    + "                         shopProduct sp\n"
                    + "                     JOIN\n"
                    + "                         productLine pl ON sp.productLineId = pl.productLineId\n"
                    + "                     JOIN\n"
                    + "                         category c ON pl.categoryId = c.categoryId\n"
                    + "                     JOIN \n"
                    + "                         brand b ON pl.brandId = b.brandId\n"
                    + "                     JOIN\n"
                    + "                         shop s ON sp.shopId = s.shopId\n"
                    + "                     LEFT JOIN\n"
                    + "                         (SELECT shopProductId, AVG(starRating) AS averageRating \n"
                    + "                          FROM rating \n"
                    + "                          GROUP BY shopProductId) r \n"
                    + "                     ON r.shopProductId = sp.id\n"
                    + "                     LEFT JOIN \n"
                    + "                    discount dis ON dis.shopProductId = sp.id\n"
                    + "                    and GETDATE() >= dis.startDate AND GETDATE() <= dis.endDate\n"
                    + "                     LEFT JOIN\n"
                    + "                         (SELECT\n"
                    + "                              sp.id AS shopProductId,\n"
                    + "                              SUM(od.quantity) AS totalSold\n"
                    + "                          FROM\n"
                    + "                              orderDetail od\n"
                    + "                          JOIN\n"
                    + "                              productItem pi ON od.productItemId = pi.productItemId\n"
                    + "                          JOIN\n"
                    + "                              shopProduct sp ON pi.shopProductId = sp.id\n"
                    + "                          WHERE\n"
                    + "                              od.statusId = 3\n"
                    + "                          GROUP BY\n"
                    + "                              sp.id) od \n"
                    + "                     ON od.shopProductId = sp.id"
                    + "  where 1 = 1 and is_deleted = 'false'";

            if (key != null && key.length() > 0) {
                sql += "and sp.title like '%" + key + "%'";
            }
            //nếu chỉ bid được tryền vào.
            if (bid != null && cid[0] == 0 && bid[0] != 0) {
                sql += "and pl.brandId in (";
                for (int i = 0; i < bid.length; i++) {
                    sql += bid[i] + ",";
                }
                if (sql.endsWith(",")) {
                    sql = sql.substring(0, sql.length() - 1);
                }
                sql += ")";

            }
            //nếu chỉ cid được truyền vào
            if ((cid != null && bid[0] == 0 && cid[0] != 0)) {
                sql += "and pl.categoryId in(";
                for (int i = 0; i < cid.length; i++) {
                    sql += cid[i] + ",";
                }
                if (sql.endsWith(",")) {
                    sql = sql.substring(0, sql.length() - 1);
                }
                sql += ")";

            }
            //nếu truyền vào cả bid và cid.
            if ((cid != null && bid != null) && (cid[0] != 0 && bid[0] != 0)) {
                sql += " and pl.categoryId in (";
                for (int i = 0; i < cid.length; i++) {
                    sql += cid[i] + ",";
                }
                if (sql.endsWith(",")) {
                    sql = sql.substring(0, sql.length() - 1);
                }
                sql += ") and pl.brandId in (";
                for (int i = 0; i < bid.length; i++) {
                    sql += bid[i] + ",";
                }
                if (sql.endsWith(",")) {
                    sql = sql.substring(0, sql.length() - 1);
                }
                sql += ")";
            }
            if (minValue >= 0 && maxValue > 0) {
                sql += "and sp.price between " + minValue + " and " + maxValue;
            }
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int shopId = rs.getInt("shopId");
                int productLineId = rs.getInt("productLineId");
                double price = rs.getDouble("price");
                String description = rs.getString("description");
                Date createdAt = rs.getDate("created_at");
                Date updatedAt = rs.getDate("updated_at");
                boolean isDeleted = rs.getBoolean("is_deleted");

                int averageRating = rs.getInt("averageRating");
                int discount = rs.getInt("discountValue");
                double discountPrice = rs.getDouble("discountPrice");
                // ProductLine fields
                String productLineName = rs.getString("productLineName");
                int categoryId = rs.getInt("categoryId");
                int brandId = rs.getInt("brandId");
                // Category fields
                String categoryName = rs.getString("categoryName");
                String shopName = rs.getString("shopName");

                // Optionally create ProductLine and Category objects if needed
                ProductLine productLine = new ProductLine(productLineId, rs.getString("ProductLineName"), categoryId, brandId);
                Category category = new Category(categoryId, categoryName);
                String image = rs.getString("image");
                String title = rs.getString("title");
                Shop shop = new Shop(shopId, shopName, rs.getString("address"), image, rs.getDouble("accountBalance"), rs.getInt("accountID"));

                ShopProduct shopProduct = new ShopProduct(id, shopId, productLine, price, description, createdAt, updatedAt, isDeleted, title, averageRating, shop, image, rs.getInt("totalSold"), discount, discountPrice);

                list.add(shopProduct);
            }
        } catch (SQLException e) {

        }

        return list;
    }

    public List<ShopProduct> searchProductbyWishList(String key, int[] cid, int[] bid, double minValue, double maxValue, int userId) {
        List<ShopProduct> list = new ArrayList<>();
        try {
            String sql = "SELECT\n"
                    + "    sp.*,\n"
                    + "    pl.*,\n"
                    + "    c.*,\n"
                    + "    b.*,\n"
                    + "    s.*,\n"
                    + "    COALESCE(dis.promotionalPrice, 0) AS discountPrice,\n"
                    + "    COALESCE(dis.discountValue, 0) AS discountValue,\n"
                    + "    COALESCE(r.averageRating, 0) AS averageRating,\n"
                    + "    COALESCE(od.totalSold, 0) AS totalSold\n"
                    + "FROM\n"
                    + "    shopProduct sp\n"
                    + "JOIN\n"
                    + "    productLine pl ON sp.productLineId = pl.productLineId\n"
                    + "JOIN\n"
                    + "    category c ON pl.categoryId = c.categoryId\n"
                    + "JOIN \n"
                    + "    brand b ON pl.brandId = b.brandId\n"
                    + "JOIN\n"
                    + "    shop s ON sp.shopId = s.shopId\n"
                    + "LEFT JOIN\n"
                    + "    (SELECT shopProductId, AVG(starRating) AS averageRating \n"
                    + "     FROM rating \n"
                    + "     GROUP BY shopProductId) r\n"
                    + "ON r.shopProductId = sp.id\n"
                    + "LEFT JOIN \n"
                    + "    discount dis ON dis.shopProductId = sp.id\n"
                    + "                    and GETDATE() >= dis.startDate AND GETDATE() <= dis.endDate\n"
                    + "LEFT JOIN\n"
                    + "    (SELECT\n"
                    + "         sp.id AS shopProductId,\n"
                    + "         SUM(od.quantity) AS totalSold\n"
                    + "     FROM\n"
                    + "         orderDetail od\n"
                    + "     JOIN\n"
                    + "         productItem pi ON od.productItemId = pi.productItemId\n"
                    + "     JOIN\n"
                    + "         shopProduct sp ON pi.shopProductId = sp.id\n"
                    + "     WHERE\n"
                    + "         od.statusId = 3\n"
                    + "     GROUP BY\n"
                    + "         sp.id) od \n"
                    + "ON od.shopProductId = sp.id\n"
                    + "LEFT JOIN\n"
                    + "    wishlist w ON sp.id = w.shopProductId AND w.userId =" + userId + "\n"
                    + "WHERE\n"
                    + "    sp.is_deleted = 'false' ";

            if (key != null && key.length() > 0) {
                sql += " AND sp.title LIKE '%" + key + "%'";
            }
            //nếu chỉ bid được tryền vào.
            if (bid != null && cid[0] == 0 && bid[0] != 0) {
                sql += " AND pl.brandId IN (";
                for (int i = 0; i < bid.length; i++) {
                    sql += bid[i] + ",";
                }
                if (sql.endsWith(",")) {
                    sql = sql.substring(0, sql.length() - 1);
                }
                sql += ")";
            }
            //nếu chỉ cid được truyền vào
            if ((cid != null && bid[0] == 0 && cid[0] != 0)) {
                sql += " AND pl.categoryId IN (";
                for (int i = 0; i < cid.length; i++) {
                    sql += cid[i] + ",";
                }
                if (sql.endsWith(",")) {
                    sql = sql.substring(0, sql.length() - 1);
                }
                sql += ")";

            }
            //nếu truyền vào cả bid và cid.
            if ((cid != null && bid != null) && (cid[0] != 0 && bid[0] != 0)) {
                sql += " AND pl.categoryId IN (";
                for (int i = 0; i < cid.length; i++) {
                    sql += cid[i] + ",";
                }
                if (sql.endsWith(",")) {
                    sql = sql.substring(0, sql.length() - 1);
                }
                sql += ") AND pl.brandId IN (";
                for (int i = 0; i < bid.length; i++) {
                    sql += bid[i] + ",";
                }
                if (sql.endsWith(",")) {
                    sql = sql.substring(0, sql.length() - 1);
                }
                sql += ")";
            }
            if (minValue >= 0 && maxValue > 0) {
                sql += " AND sp.price BETWEEN " + minValue + " and " + maxValue;
            }

            sql += " ORDER BY\n"
                    + "    CASE \n"
                    + "        WHEN w.userId = " + userId + " THEN 0\n"
                    + "        ELSE 1\n"
                    + "    END,\n"
                    + "    sp.id;";

            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int shopId = rs.getInt("shopId");
                int productLineId = rs.getInt("productLineId");
                double price = rs.getDouble("price");
                String description = rs.getString("description");
                Date createdAt = rs.getDate("created_at");
                Date updatedAt = rs.getDate("updated_at");
                boolean isDeleted = rs.getBoolean("is_deleted");

                int averageRating = rs.getInt("averageRating");
                int discount = rs.getInt("discountValue");
                double discountPrice = rs.getDouble("discountPrice");
                // ProductLine fields
                String productLineName = rs.getString("productLineName");
                int categoryId = rs.getInt("categoryId");
                int brandId = rs.getInt("brandId");
                // Category fields
                String categoryName = rs.getString("categoryName");
                String shopName = rs.getString("shopName");

                // Optionally create ProductLine and Category objects if needed
                ProductLine productLine = new ProductLine(productLineId, rs.getString("ProductLineName"), categoryId, brandId);
                Category category = new Category(categoryId, categoryName);
                String image = rs.getString("image");
                String title = rs.getString("title");
                Shop shop = new Shop(shopId, shopName, rs.getString("address"), image, rs.getDouble("accountBalance"), rs.getInt("accountID"));

                ShopProduct shopProduct = new ShopProduct(id, shopId, productLine, price, description, createdAt, updatedAt, isDeleted, title, averageRating, shop, image, rs.getInt("totalSold"), discount, discountPrice);
                list.add(shopProduct);
            }
        } catch (SQLException e) {

        }

        return list;
    }

    public void lockProduct(int accountId, int productLineId) {
        String sql = "UPDATE sp "
                + "SET sp.is_deleted = 'TRUE' "
                + "FROM shopProduct sp "
                + "INNER JOIN shop s ON sp.shopId = s.shopId "
                + "WHERE sp.id = ? AND s.accountId = ?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {

            st.setInt(1, productLineId);
            st.setInt(2, accountId);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void unlockProduct(int accountId, int productLineId) {
        String sql = "UPDATE sp "
                + "SET sp.is_deleted = 'FALSE' "
                + "FROM shopProduct sp "
                + "INNER JOIN shop s ON sp.shopId = s.shopId "
                + "WHERE sp.id = ? AND s.accountId = ?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {

            st.setInt(1, productLineId);
            st.setInt(2, accountId);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<ShopProduct> searchProductAndSort(String key, int[] cid, int[] bid, double minValue, double maxValue, String sort) {
        List<ShopProduct> list = new ArrayList<>();
        try {
            String sql = "SELECT \n"
                    + "                         sp.*,\n"
                    + "                         pl.*,\n"
                    + "                         c.*,\n"
                    + "                         b.*,\n"
                    + "                         s.*,\n"
                    + "				COALESCE(dis.promotionalPrice, 0) AS discountPrice,\n"
                    + "                        COALESCE(dis.discountValue, 0) AS discountValue,\n"
                    + "                         COALESCE(r.averageRating, 0) AS averageRating,\n"
                    + "                         COALESCE(od.totalSold, 0) AS totalSold\n"
                    + "                     FROM\n"
                    + "                         shopProduct sp\n"
                    + "                     JOIN\n"
                    + "                         productLine pl ON sp.productLineId = pl.productLineId\n"
                    + "                     JOIN\n"
                    + "                         category c ON pl.categoryId = c.categoryId\n"
                    + "                     JOIN \n"
                    + "                         brand b ON pl.brandId = b.brandId\n"
                    + "                     JOIN\n"
                    + "                         shop s ON sp.shopId = s.shopId\n"
                    + "                     LEFT JOIN\n"
                    + "                         (SELECT shopProductId, AVG(starRating) AS averageRating \n"
                    + "                          FROM rating \n"
                    + "                          GROUP BY shopProductId) r \n"
                    + "                     ON r.shopProductId = sp.id\n"
                    + "                     LEFT JOIN \n"
                    + "                    discount dis ON dis.shopProductId = sp.id\n"
                    + "                    and GETDATE() >= dis.startDate AND GETDATE() <= dis.endDate\n"
                    + "                     LEFT JOIN\n"
                    + "                         (SELECT\n"
                    + "                              sp.id AS shopProductId,\n"
                    + "                              SUM(od.quantity) AS totalSold\n"
                    + "                          FROM\n"
                    + "                              orderDetail od\n"
                    + "                          JOIN\n"
                    + "                              productItem pi ON od.productItemId = pi.productItemId\n"
                    + "                          JOIN\n"
                    + "                              shopProduct sp ON pi.shopProductId = sp.id\n"
                    + "                          WHERE\n"
                    + "                              od.statusId = 3\n"
                    + "                          GROUP BY\n"
                    + "                              sp.id) od \n"
                    + "                     ON od.shopProductId = sp.id"
                    + "  where 1 = 1 and is_deleted = 'false'";
            if (key != null && key.length() > 0) {
                sql += "and sp.title like '%" + key + "%'";
            }
            //nếu chỉ bid được tryền vào.
            if (bid != null && cid[0] == 0 && bid[0] != 0) {
                sql += "and pl.brandId in (";
                for (int i = 0; i < bid.length; i++) {
                    sql += bid[i] + ",";
                }
                if (sql.endsWith(",")) {
                    sql = sql.substring(0, sql.length() - 1);
                }
                sql += ")";

            }
            //nếu chỉ cid được truyền vào
            if ((cid != null && bid[0] == 0 && cid[0] != 0)) {
                sql += "and pl.categoryId in(";
                for (int i = 0; i < cid.length; i++) {
                    sql += cid[i] + ",";
                }
                if (sql.endsWith(",")) {
                    sql = sql.substring(0, sql.length() - 1);
                }
                sql += ")";

            }
            //nếu truyền vào cả bid và cid.
            if ((cid != null && bid != null) && (cid[0] != 0 && bid[0] != 0)) {
                sql += " and pl.categoryId in (";
                for (int i = 0; i < cid.length; i++) {
                    sql += cid[i] + ",";
                }
                if (sql.endsWith(",")) {
                    sql = sql.substring(0, sql.length() - 1);
                }
                sql += ") and pl.brandId in (";
                for (int i = 0; i < bid.length; i++) {
                    sql += bid[i] + ",";
                }
                if (sql.endsWith(",")) {
                    sql = sql.substring(0, sql.length() - 1);
                }
                sql += ")";
            }

            if (minValue >= 0 && maxValue > 0) {
                sql += "and sp.price between " + minValue + " and " + maxValue;
            }
            if (sort != null) {
                sql += "order by sp.price " + sort;
            }

            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int shopId = rs.getInt("shopId");
                int productLineId = rs.getInt("productLineId");
                double price = rs.getDouble("price");
                String description = rs.getString("description");
                Date createdAt = rs.getDate("created_at");
                Date updatedAt = rs.getDate("updated_at");
                boolean isDeleted = rs.getBoolean("is_deleted");
                int averageRating = rs.getInt("averageRating");
                int discount = rs.getInt("discountValue");
                double discountPrice = rs.getDouble("discountPrice");
                // ProductLine fields
                String productLineName = rs.getString("productLineName");
                int categoryId = rs.getInt("categoryId");
                int brandId = rs.getInt("brandId");
                // Category fields
                String categoryName = rs.getString("categoryName");
                String shopName = rs.getString("shopName");

                // Optionally create ProductLine and Category objects if needed
                ProductLine productLine = new ProductLine(productLineId, rs.getString("ProductLineName"), categoryId, brandId);
                Category category = new Category(categoryId, categoryName);
                String image = rs.getString("image");
                String title = rs.getString("title");
                Shop shop = new Shop(shopId, shopName, rs.getString("address"), image, rs.getDouble("accountBalance"), rs.getInt("accountID"));

                ShopProduct shopProduct = new ShopProduct(id, shopId, productLine, price, description, createdAt, updatedAt, isDeleted, title, averageRating, shop, image, rs.getInt("totalSold"), discount, discountPrice);

                list.add(shopProduct);
            }
        } catch (SQLException e) {

        }

        return list;
    }

    public List<ShopProduct> searchProductAndSortbyWishList(String key, int[] cid, int[] bid, double minValue, double maxValue, String sort, int userId) {
        List<ShopProduct> list = new ArrayList<>();
        try {
            String sql = "SELECT\n"
                    + "    sp.*,\n"
                    + "    pl.*,\n"
                    + "    c.*,\n"
                    + "    b.*,\n"
                    + "    s.*,\n"
                    + "    COALESCE(dis.promotionalPrice, 0) AS discountPrice,\n"
                    + "    COALESCE(dis.discountValue, 0) AS discountValue,\n"
                    + "    COALESCE(r.averageRating, 0) AS averageRating,\n"
                    + "    COALESCE(od.totalSold, 0) AS totalSold\n"
                    + "FROM\n"
                    + "    shopProduct sp\n"
                    + "JOIN\n"
                    + "    productLine pl ON sp.productLineId = pl.productLineId\n"
                    + "JOIN\n"
                    + "    category c ON pl.categoryId = c.categoryId\n"
                    + "JOIN \n"
                    + "    brand b ON pl.brandId = b.brandId\n"
                    + "JOIN\n"
                    + "    shop s ON sp.shopId = s.shopId\n"
                    + "LEFT JOIN\n"
                    + "    (SELECT shopProductId, AVG(starRating) AS averageRating \n"
                    + "     FROM rating \n"
                    + "     GROUP BY shopProductId) r\n"
                    + "ON r.shopProductId = sp.id\n"
                    + "LEFT JOIN \n"
                    + "    discount dis ON dis.shopProductId = sp.id\n"
                    + "     and GETDATE() >= dis.startDate AND GETDATE() <= dis.endDate\n"
                    + "LEFT JOIN\n"
                    + "    (SELECT\n"
                    + "         sp.id AS shopProductId,\n"
                    + "         SUM(od.quantity) AS totalSold\n"
                    + "     FROM\n"
                    + "         orderDetail od\n"
                    + "     JOIN\n"
                    + "         productItem pi ON od.productItemId = pi.productItemId\n"
                    + "     JOIN\n"
                    + "         shopProduct sp ON pi.shopProductId = sp.id\n"
                    + "     WHERE\n"
                    + "         od.statusId = 3\n"
                    + "     GROUP BY\n"
                    + "         sp.id) od \n"
                    + "ON od.shopProductId = sp.id\n"
                    + "LEFT JOIN\n"
                    + "    wishlist w ON sp.id = w.shopProductId AND w.userId =" + userId + "\n"
                    + "WHERE\n"
                    + "    sp.is_deleted = 'false' ";

            if (key != null && key.length() > 0) {
                sql += "and sp.title like '%" + key + "%'";
            }
            //nếu chỉ bid được tryền vào.
            if (bid != null && cid[0] == 0 && bid[0] != 0) {
                sql += "and pl.brandId in (";
                for (int i = 0; i < bid.length; i++) {
                    sql += bid[i] + ",";
                }
                if (sql.endsWith(",")) {
                    sql = sql.substring(0, sql.length() - 1);
                }
                sql += ")";

            }
            //nếu chỉ cid được truyền vào
            if ((cid != null && bid[0] == 0 && cid[0] != 0)) {
                sql += "and pl.categoryId in(";
                for (int i = 0; i < cid.length; i++) {
                    sql += cid[i] + ",";
                }
                if (sql.endsWith(",")) {
                    sql = sql.substring(0, sql.length() - 1);
                }
                sql += ")";

            }
            //nếu truyền vào cả bid và cid.
            if ((cid != null && bid != null) && (cid[0] != 0 && bid[0] != 0)) {
                sql += " and pl.categoryId in (";
                for (int i = 0; i < cid.length; i++) {
                    sql += cid[i] + ",";
                }
                if (sql.endsWith(",")) {
                    sql = sql.substring(0, sql.length() - 1);
                }
                sql += ") and pl.brandId in (";
                for (int i = 0; i < bid.length; i++) {
                    sql += bid[i] + ",";
                }
                if (sql.endsWith(",")) {
                    sql = sql.substring(0, sql.length() - 1);
                }
                sql += ")";
            }
            if (minValue >= 0 && maxValue > 0) {
                sql += "and sp.price between " + minValue + " and " + maxValue;
            }
            if (sort != null) {
                sql += "ORDER BY\n"
                        + "    CASE \n"
                        + "        WHEN w.userId = " + userId + " THEN 0\n"
                        + "        ELSE 1\n"
                        + "    END,\n"
                        + "	sp.price " + sort;
            }

            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int shopId = rs.getInt("shopId");
                int productLineId = rs.getInt("productLineId");
                double price = rs.getDouble("price");
                String description = rs.getString("description");
                Date createdAt = rs.getDate("created_at");
                Date updatedAt = rs.getDate("updated_at");
                boolean isDeleted = rs.getBoolean("is_deleted");
                int averageRating = rs.getInt("averageRating");
                int discount = rs.getInt("discountValue");
                double discountPrice = rs.getDouble("discountPrice");
                // ProductLine fields
                String productLineName = rs.getString("productLineName");
                int categoryId = rs.getInt("categoryId");
                int brandId = rs.getInt("brandId");
                // Category fields
                String categoryName = rs.getString("categoryName");
                String shopName = rs.getString("shopName");

                // Optionally create ProductLine and Category objects if needed
                ProductLine productLine = new ProductLine(productLineId, rs.getString("ProductLineName"), categoryId, brandId);
                Category category = new Category(categoryId, categoryName);
                String image = rs.getString("image");
                String title = rs.getString("title");
                Shop shop = new Shop(shopId, shopName, rs.getString("address"), image, rs.getDouble("accountBalance"), rs.getInt("accountID"));
                ShopProduct shopProduct = new ShopProduct(id, shopId, productLine, price, description, createdAt, updatedAt, isDeleted, title, averageRating, shop, image, rs.getInt("totalSold"), discount, discountPrice);

                list.add(shopProduct);
            }
        } catch (SQLException e) {

        }

        return list;
    }

//    public boolean shopHasProductLine(int accountId, int productLineId) {
//        boolean hasProductLine = false;
//        String sql = "SELECT COUNT(*) AS count FROM shopProduct sp "
//                + "JOIN shop s ON sp.shopId = s.shopId "
//                + "WHERE s.accountId = ? AND sp.productLineId = ?";
//        try (PreparedStatement st = connection.prepareStatement(sql)) {
//            st.setInt(1, accountId);
//            st.setInt(2, productLineId);
//            try (ResultSet rs = st.executeQuery()) {
//                if (rs.next()) {
//                    int count = rs.getInt("count");
//                    hasProductLine = count > 0;
//                }
//            }
//        } catch (SQLException e) {
//            System.err.println("Lỗi cơ sở dữ liệu: " + e.getMessage());
//            e.printStackTrace();
//        }
//        return hasProductLine;
//    }
    public boolean shopHasProductLine(int accountId, int productLineId, int currentProductId) {
        boolean hasProductLine = false;
        String sql = "SELECT COUNT(*) AS count FROM shopProduct sp "
                + "JOIN shop s ON sp.shopId = s.shopId "
                + "WHERE s.accountId = ? AND sp.productLineId = ? AND sp.id != ?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, accountId);
            st.setInt(2, productLineId);
            st.setInt(3, currentProductId);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt("count");
                    hasProductLine = count > 0;
                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi cơ sở dữ liệu: " + e.getMessage());
            e.printStackTrace();
        }
        return hasProductLine;
    }
//    public boolean shopHasProductLine(int accountId, int productLineId, int currentProductId) {
//        boolean hasProductLine = false;
//        String sql = "SELECT COUNT(*) AS count FROM shopProduct sp "
//                + "JOIN shop s ON sp.shopId = s.shopId "
//                + "WHERE s.accountId = ? AND sp.productLineId = ? AND sp.id != ?";
//        try (PreparedStatement st = connection.prepareStatement(sql)) {
//            st.setInt(1, accountId);
//            st.setInt(2, productLineId);
//            st.setInt(3, currentProductId);
//            try (ResultSet rs = st.executeQuery()) {
//                if (rs.next()) {
//                    int count = rs.getInt("count");
//                    hasProductLine = count > 0;
//                }
//            }
//        } catch (SQLException e) {
//            System.err.println("Lỗi cơ sở dữ liệu: " + e.getMessage());
//            e.printStackTrace();
//        }
//        return hasProductLine;
//    }
    //cap nhat thong tin san pham

    public void updateShopProduct(int productId, double price, String des, String image, String title) {
        String sql = """
                     UPDATE [dbo].[shopProduct]
                     SET [price] = ?,
                     description = ?,
                     image = ?,
                     title = ?
                     WHERE id = ?""";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setDouble(1, price);
            st.setString(2, des);
            st.setString(3, image);
            st.setString(4, title);
            st.setInt(5, productId);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addProductToShop(int shopId, int productLineId, String productName, double price, String image, String des, boolean isDeleted) {
        String sql = """
                     INSERT INTO shopProduct (shopId, productLineId, price, image, description, is_deleted,title)
                     VALUES (?, ?, ?, ?, ?, ?, ?)""";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, shopId);
            st.setInt(2, productLineId);
            st.setDouble(3, price);
            st.setString(4, image);
            st.setString(5, des);
            st.setBoolean(6, isDeleted);
            st.setString(7, productName);
            st.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public List<ShopProduct> getListByPage(List<ShopProduct> list, int start, int end) {
        ArrayList<ShopProduct> arr = new ArrayList<>();
        for (int i = start; i < end; i++) {
            arr.add(list.get(i));
        }
        return arr;
    }

    public List<ProductLine> getBillDetail(int shopId, int orderId) {
        List<ProductLine> listBill = new ArrayList<>();
        String sql = """
                     SELECT 
                                                             shopProduct.title,
                                                         shopProduct.image,
                                                          color.colorValue,
                                                          size.sizeValue,
                                                              shopProduct.price,
                                                              orderDetail.quantity,
                                          status.statusValue,
                     \t\t\t\t\t voucher.reducedAmount,
                                                              SUM(orderDetail.quantity * orderDetail.price) AS Total
                                                         FROM 
                                                             [order] 
                                                        JOIN
                                                           orderDetail ON [order].orderId = orderDetail.orderId 
                                                          JOIN 
                                                            productItem ON productItem.productItemId = orderDetail.productItemId
                                                          JOIN 
                                                             shopProduct ON shopProduct.id = productItem.shopProductId
                                                          JOIN 
                                                              shop ON shop.shopId = shopProduct.shopId
                                                          JOIN 
                                                              color ON color.colorId = productItem.colorId
                                                          JOIN 
                                                             size ON size.sizeId = productItem.sizeId
                     \t\t\t\t\t\t\t\t\tLEFT JOIN 
                     \t\t\t\t\t\t\t\t\tvoucher ON voucher.voucherId = orderDetail.voucherId
                                          JOIN 
                                          status ON orderDetail.statusId = status.statusId
                                                          WHERE 
                                                             shop.shopId = ? AND orderDetail.orderId = ? AND orderDetail.statusId !=4
                                                          GROUP BY 
                                                              shopProduct.title,
                                                          shopProduct.image,
                                                          color.colorValue,
                                                          size.sizeValue,
                                                            shopProduct.price,
                                          status.statusValue,
                     \t\t\t\t\t voucher.reducedAmount,
                                              orderDetail.quantity;""";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, shopId);
            st.setInt(2, orderId);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    ProductLine a = new ProductLine(rs.getInt("price"),
                            rs.getString("image"),
                            rs.getString("colorValue"),
                            rs.getString("sizeValue"),
                            rs.getInt("quantity"),
                            rs.getString("title"),
                            rs.getString("statusValue"),
                            rs.getInt("Total"),
                            rs.getDouble("reducedAmount"));

                    listBill.add(a);
                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi cơ sở dữ liệu: " + e.getMessage());
            e.printStackTrace();
        }
        return listBill;
    }

    public List<ProductLine> getOrderCancelDetail(int shopId, int orderId) {
        List<ProductLine> listBill = new ArrayList<>();
        String sql = """
                     SELECT 
                                        shopProduct.title,
                                    \tshopProduct.image,
                                     \tcolor.colorValue,
                                     \tsize.sizeValue,
                                         shopProduct.price,
                                         orderDetail.quantity,
                                         orderDetail.description,
                     \t\t\t\t\tstatus.statusValue,
                                         SUM(orderDetail.quantity * orderDetail.price) AS Total
                                    FROM 
                                        [order] 
                                   JOIN
                                      orderDetail ON [order].orderId = orderDetail.orderId 
                                     JOIN 
                                       productItem ON productItem.productItemId = orderDetail.productItemId
                                     JOIN 
                                        shopProduct ON shopProduct.id = productItem.shopProductId
                                     JOIN 
                                         shop ON shop.shopId = shopProduct.shopId
                                     JOIN 
                                         color ON color.colorId = productItem.colorId
                                     JOIN 
                                        size ON size.sizeId = productItem.sizeId
                     \t\t\t\tJOIN 
                     \t\t\t\t\tstatus ON orderDetail.statusId = status.statusId
                                     WHERE 
                                        shop.shopId = ? AND orderDetail.orderId = ? AND orderDetail.statusId =4
                                     GROUP BY 
                                         shopProduct.title,
                                     shopProduct.image,
                                     \tcolor.colorValue,
                                     \tsize.sizeValue,
                                       shopProduct.price,
                     \t\t\t\t  status.statusValue,
                         orderDetail.quantity,
                     orderDetail.description;""";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, shopId);
            st.setInt(2, orderId);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    ProductLine a = new ProductLine(rs.getInt("price"),
                            rs.getString("image"),
                            rs.getString("colorValue"),
                            rs.getString("sizeValue"),
                            rs.getInt("quantity"),
                            rs.getString("description"),
                            rs.getString("title"),
                            rs.getInt("Total"),
                            rs.getString("statusValue")
                    );

                    listBill.add(a);
                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi cơ sở dữ liệu: " + e.getMessage());
            e.printStackTrace();
        }
        return listBill;
    }

    public List<ShopProduct> getRandomProductOfShop(int sid) {
        List<ShopProduct> list = new ArrayList<>();
        String sql = """
                     SELECT TOP 4 * 
                     FROM shopProduct 
                     JOIN productLine ON shopProduct.productLineId = productLine.productLineId 
                     JOIN category ON productLine.categoryId = category.categoryId 
                     JOIN brand ON productLine.brandId = brand.brandId 
                     JOIN shop ON shopProduct.shopId = shop.shopId
                     LEFT JOIN discount dis ON dis.shopProductId = shopProduct.id
                     WHERE shop.shopId = ?
                     ORDER BY NEWID();""";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, sid);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int shopId = rs.getInt("shopId");
                int productLineId = rs.getInt("productLineId");
                double price = rs.getDouble("price");
                String description = rs.getString("description");
                Date createdAt = rs.getDate("created_at");
                Date updatedAt = rs.getDate("updated_at");
                boolean isDeleted = rs.getBoolean("is_deleted");
                int discount = rs.getInt("discountValue");

                // ProductLine fields
                String productLineName = rs.getString("productLineName");
                int categoryId = rs.getInt("categoryId");
                int brandId = rs.getInt("brandId");
                // Category fields
                String categoryName = rs.getString("categoryName");
                String shopName = rs.getString("shopName");

                // Optionally create ProductLine and Category objects if needed
                ProductLine productLine = new ProductLine(productLineId, rs.getString("ProductLineName"), categoryId, brandId);
                Category category = new Category(categoryId, categoryName);
                String image = rs.getString("image");
                String title = rs.getString("title");
                Shop shop = new Shop(shopId, shopName, rs.getString("address"), image, rs.getDouble("accountBalance"), rs.getInt("accountID"));

                ShopProduct shopProduct = new ShopProduct(id, shopId, productLine, price, description, createdAt, updatedAt, isDeleted, shop, image, title, discount);

                list.add(shopProduct);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return list;
    }

//    public List<ShopProduct> getProductByCIDAndBID(int[] cateID, int[] bid, String priceorder) {
//        List<ShopProduct> list = new ArrayList<>();
//        String sql = "    SELECT * from shopProduct join productLine on shopProduct.productLineId = productLine.productLineId \n"
//                + "join category on productLine.categoryId = category.categoryId join brand on productLine.brandId = brand.brandId \n"
//                + "join shop on shopProduct.shopId = shop.shopId where Category.categoryId = ? and brand.brandId = ? order by price" + priceorder;
//
//        try (PreparedStatement statement = connection.prepareStatement(sql)) {
//            statement.setInt(1, cateID);
//            statement.setInt(2, bid);
//
//            try (ResultSet rs = statement.executeQuery()) {
//                while (rs.next()) {
//                    int id = rs.getInt("id");
//                    int shopId = rs.getInt("shopId");
//                    int productLineId = rs.getInt("productLineId");
//                    double price = rs.getDouble("price");
//                    String description = rs.getString("description");
//                    Date createdAt = rs.getDate("created_at");
//                    Date updatedAt = rs.getDate("updated_at");
//                    boolean isDeleted = rs.getBoolean("is_deleted");
//
//                    // ProductLine fields
//                    String productLineName = rs.getString("productLineName");
//                    int categoryId = rs.getInt("categoryId");
//                    int brandId = rs.getInt("brandId");
//                    // Category fields
//                    String categoryName = rs.getString("categoryName");
//                    String shopName = rs.getString("shopName");
//
//                    // Optionally create ProductLine and Category objects if needed
//                    ProductLine productLine = new ProductLine(productLineId, rs.getString("ProductLineName"), categoryId, brandId);
//                    Category category = new Category(categoryId, categoryName);
//                    String image = rs.getString("image");
//                    String title = rs.getString("title");
//                    Shop shop = new Shop(shopId, shopName, rs.getString("address"), image, rs.getDouble("accountBalance"), rs.getInt("accountID"));
//
//                    ShopProduct shopProduct = new ShopProduct(id, shopId, productLine, price, description, createdAt, updatedAt, isDeleted, shop, image, title);
//                    list.add(shopProduct);
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            // Handle exceptions
//        }
//
//        return list;
//    }
    public List<ShopProduct> getProductByCIDAndBID(int[] cateIDs, int[] bids, String priceorder) {
        List<ShopProduct> list = new ArrayList<>();
        if (cateIDs.length == 0 || bids.length == 0) {
            return list; // Return empty list if no category IDs or brand IDs are provided
        }

        // Dynamically create the SQL query
        StringBuilder sql = new StringBuilder("SELECT * FROM shopProduct ")
                .append("JOIN productLine ON shopProduct.productLineId = productLine.productLineId ")
                .append("JOIN category ON productLine.categoryId = category.categoryId ")
                .append("JOIN brand ON productLine.brandId = brand.brandId ")
                .append("LEFT JOIN discount dis ON dis.shopProductId = shopProduct.id")
                .append("JOIN shop ON shopProduct.shopId = shop.shopId ")
                .append("WHERE category.categoryId IN (");

        // Append placeholders for category IDs
        for (int i = 0; i < cateIDs.length; i++) {
            sql.append("?");
            if (i < cateIDs.length - 1) {
                sql.append(",");
            }
        }

        sql.append(") AND brand.brandId IN (");

        // Append placeholders for brand IDs
        for (int i = 0; i < bids.length; i++) {
            sql.append("?");
            if (i < bids.length - 1) {
                sql.append(",");
            }
        }

        sql.append(") ORDER BY price ").append(priceorder);

        try (PreparedStatement statement = connection.prepareStatement(sql.toString())) {
            int paramIndex = 1;

            // Set category IDs in the prepared statement
            for (int cateID : cateIDs) {
                statement.setInt(paramIndex++, cateID);
            }

            // Set brand IDs in the prepared statement
            for (int bid : bids) {
                statement.setInt(paramIndex++, bid);
            }

            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    int shopId = rs.getInt("shopId");
                    int productLineId = rs.getInt("productLineId");
                    double price = rs.getDouble("price");
                    String description = rs.getString("description");
                    Date createdAt = rs.getDate("created_at");
                    Date updatedAt = rs.getDate("updated_at");
                    boolean isDeleted = rs.getBoolean("is_deleted");
                    int discount = rs.getInt("discountValue");

                    // ProductLine fields
                    String productLineName = rs.getString("productLineName");
                    int categoryId = rs.getInt("categoryId");
                    int brandId = rs.getInt("brandId");

                    // Category fields
                    String categoryName = rs.getString("categoryName");

                    // Shop fields
                    String shopName = rs.getString("shopName");

                    // Optionally create ProductLine and Category objects if needed
                    ProductLine productLine = new ProductLine(productLineId, productLineName, categoryId, brandId);
                    Category category = new Category(categoryId, categoryName);
                    String image = rs.getString("image");
                    String title = rs.getString("title");
                    Shop shop = new Shop(shopId, shopName, rs.getString("address"), image, rs.getDouble("accountBalance"), rs.getInt("accountID"));

                    ShopProduct shopProduct = new ShopProduct(id, shopId, productLine, price, description, createdAt, updatedAt, isDeleted, shop, image, title, discount);
                    list.add(shopProduct);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions
        }

        return list;
    }

    public List<ProductLine> getQuantityAndPriceSoldByAccountId() {
        List<ProductLine> list = new ArrayList<>();
        String sql = """
                    SELECT 
                         sp.id,
                         p.productLineId,
                         p.productLineName,
                         sp.title,
                         sp.image,
                         sp.price,
                         b.brandName,
                         c.categoryName,
                         SUM(od.quantity) AS total_quantity,
                         SUM(od.price) AS total_price
                     FROM shop s
                     JOIN shopProduct sp ON s.shopId = sp.shopId
                     JOIN productLine p ON sp.productLineId = p.productLineId
                     JOIN brand b ON b.brandId = p.brandId
                     JOIN category c ON c.categoryId = p.categoryId
                     JOIN productItem pi ON pi.shopProductId = sp.id
                     JOIN orderDetail od ON od.productItemId = pi.productItemId
                     WHERE  sp.is_deleted = 'FALSE' AND od.statusId=3
                     GROUP BY 
                         sp.id,
                         p.productLineId,
                         p.productLineName,
                         sp.title,
                         sp.image,
                         sp.price,
                         b.brandName,
                         c.categoryName
                     ORDER BY total_price DESC;""";
        try (PreparedStatement st = connection.prepareStatement(sql)) {

            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    ProductLine a = new ProductLine(rs.getInt("id"), rs.getInt("productLineId"),
                            rs.getString("productLineName"), rs.getString("brandName"), rs.getInt("price"), rs.getString("image"),
                            rs.getString("categoryName"), rs.getInt("total_price"), rs.getInt("total_quantity")
                    );

                    list.add(a);
                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi cơ sở dữ liệu: " + e.getMessage());
            e.printStackTrace();
        }
        return list;
    }

    public int getTotalQuantitySoldByAccountId() {
        int totalQuantity = 0;
        String sql = """
                SELECT 
                    SUM(od.quantity) AS total_quantity
                FROM shop s
                JOIN shopProduct sp ON s.shopId = sp.shopId
                JOIN productLine p ON sp.productLineId = p.productLineId
                JOIN brand b ON b.brandId = p.brandId
                JOIN category c ON c.categoryId = p.categoryId
                JOIN productItem pi ON pi.shopProductId = sp.id
                JOIN orderDetail od ON od.productItemId = pi.productItemId
                WHERE  sp.is_deleted = 'FALSE' AND od.statusId=3;""";
        try (PreparedStatement st = connection.prepareStatement(sql)) {

            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    totalQuantity = rs.getInt("total_quantity");
                }
            }
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
            e.printStackTrace();
        }
        return totalQuantity;
    }

    public int getTotalPriceSoldByAccountId() {
        int totalPrice = 0;
        String sql = """
                SELECT 
                    SUM(od.price) AS total_price
                FROM shop s
                JOIN shopProduct sp ON s.shopId = sp.shopId
                JOIN productLine p ON sp.productLineId = p.productLineId
                JOIN brand b ON b.brandId = p.brandId
                JOIN category c ON c.categoryId = p.categoryId
                JOIN productItem pi ON pi.shopProductId = sp.id
                JOIN orderDetail od ON od.productItemId = pi.productItemId
                WHERE sp.is_deleted = 'FALSE' AND od.statusId=3;""";
        try (PreparedStatement st = connection.prepareStatement(sql)) {

            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    totalPrice = rs.getInt("total_price");
                }
            }
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
            e.printStackTrace();
        }
        return totalPrice;
    }

    public int getTotalUserBuyProductByAccountId() {
        int totalUser = 0;
        String sql = """
               SELECT COUNT(DISTINCT o.userId) AS UserCount
                FROM shop s 
                JOIN shopProduct sp ON s.shopId = sp.shopId 
                JOIN productLine p ON sp.productLineId = p.productLineId 
                JOIN brand b ON b.brandId = p.brandId 
                JOIN category c ON c.categoryId = p.categoryId
                JOIN productItem pi ON pi.shopProductId = sp.id
                JOIN orderDetail od ON od.productItemId = pi.productItemId
                JOIN [order] o ON o.orderId = od.orderId
                WHERE 
                   sp.is_deleted = 'FALSE' 
                  AND od.statusId = 3;""";
        try (PreparedStatement st = connection.prepareStatement(sql)) {

            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    totalUser = rs.getInt("UserCount");
                }
            }
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
            e.printStackTrace();
        }
        return totalUser;
    }
    
    public static void main(String[] args) {
      
        //       s.addProductToShop(6, 2, "hihi", 123456, "null", "hidi", false);
//        List<ProductLine> list = s.getBillDetail(1, 28);
//        for (ProductLine a : list) {
//            System.out.println(a);
//        }
//        List<ShopProduct> sp = s.getShopProductsAllbyWishList(5);
//        for (int i = 0; i < sp.size(); i++) {
//            System.out.println(sp.get(i));
//        }
//        int[] bIdList = {0};
//        int[] cIdList = {0};
//        List<ShopProduct> sp1 = s.searchProductbyWishList("nike", cIdList, bIdList, 10000, 100000000, 1);
//        for (int i = 0; i < sp1.size(); i++) {
//            System.out.println(sp1.get(i));
//        }
  ShopProductDAO dao = new ShopProductDAO();
       // Thay đổi accountId tùy vào dữ liệu của bạn
        
        List<ProductLine> productList = dao.getQuantityAndPriceSoldByAccountId();
        
        if (productList.isEmpty()) {
            System.out.println("Không có sản phẩm nào được bán bởi tài khoản có ID: " );
        } else {
            for (ProductLine product : productList) {
                System.out.println(product);
            }
        }
    }
}
