/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Category;
import model.ProductLine;
import model.Shop;
import model.ShopProduct;

/**
 *
 * @author GiaKhiem
 */
public class ShopProductDAO extends DBContext{
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
    public List<ShopProduct> getListByPage(List<ShopProduct> list, int start, int end) {
        ArrayList<ShopProduct> arr = new ArrayList<>();
        for (int i = start; i < end; i++) {
            arr.add(list.get(i));
        }
        return arr;
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
}
