package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import model.Discount;

public class DiscountDAO extends DBContext {

    public void addDiscount(int productId, int discountValue, Date startDate, Date endDate, double promotionalPrice) {

        String sql = "INSERT INTO discount (shopProductId, discountValue,startDate, endDate, promotionalPrice) VALUES (?,?,?,?,?)";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, productId);
            st.setInt(2, discountValue);
            st.setDate(3, startDate);
            st.setDate(4, endDate);
            st.setDouble(5, promotionalPrice);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean isDiscountConflict(int productId) {
        String sql = "SELECT COUNT(*) FROM discount WHERE shopProductId = ? "
                + "AND endDate >= GETDATE()";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, productId);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            System.out.println("Lỗi kiểm tra discount conflict: " + e.getMessage());
        }
        return false;
    }

//    public void updatePrice(int productId, double price) {
//        String sql = "UPDATE shopProduct SET price = ? WHERE id = ?";
//        try (PreparedStatement st = connection.prepareStatement(sql)) {
//            st.setDouble(1, price);
//            st.setInt(2, productId);
//            st.executeUpdate();
//        } catch (SQLException e) {
//            System.out.println("Lỗi cập nhật giá sản phẩm: " + e.getMessage());
//        }
//    }
    public List<Discount> getInfoDiscountById(int productId) {
        List<Discount> list = new ArrayList<>();
        String sql = "SELECT * \n"
                + "FROM discount \n"
                + "WHERE shopProductId = ? \n"
                + "    AND GETDATE() <= endDate\n"
                + "ORDER BY startDate;";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, productId);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    Discount a = new Discount(
                            rs.getInt("discountId"),
                            rs.getInt("shopProductId"),
                            rs.getInt("discountValue"),
                            rs.getDate("startDate"),
                            rs.getDate("endDate"),
                            rs.getDouble("promotionalPrice")
                    );
                    list.add(a);
                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi truy vấn cơ sở dữ liệu: " + e.getMessage());
        }
        return list;
    }

    public Discount getDisInfoDiscountById(int productId) {
        List<Discount> list = new ArrayList<>();
        String sql = "SELECT * FROM discount WHERE shopProductId = ?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, productId);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    Discount a = new Discount(
                            rs.getInt("discountId"),
                            rs.getInt("shopProductId"),
                            rs.getInt("discountValue"),
                            rs.getDate("startDate"),
                            rs.getDate("endDate"),
                            rs.getDouble("promotionalPrice")
                    );
                    return a;
                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi truy vấn cơ sở dữ liệu: " + e.getMessage());
        }
        return null;
    }

    public void deleteDiscount(int id) {
        String sql = "DELETE FROM [dbo].[discount] WHERE discountId =?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

//    public void deleteDiscounts() {
//        String sql = "DELETE FROM discount WHERE endDate < GETDATE()";
//        try (PreparedStatement st = connection.prepareStatement(sql)) {
//            int rowsDeleted = st.executeUpdate();
//            System.out.println("Đã xoá " + rowsDeleted + " bản ghi discount đã hết hạn.");
//        } catch (SQLException e) {
//            System.out.println("Lỗi xoá discount đã hết hạn: " + e.getMessage());
//        }
//    }
//    public void updateAndDeleteExpiredDiscounts() {
//        String selectExpiredDiscounts = "SELECT shopProductId, promotionalPrice, discountValue FROM discount WHERE endDate < GETDATE()";
//        List<Discount> expiredDiscounts = new ArrayList<>();
//
//        try (PreparedStatement stSelect = connection.prepareStatement(selectExpiredDiscounts);
//             ResultSet rs = stSelect.executeQuery()) {
//
//            // Collect expired discounts into a list
//            while (rs.next()) {
//                int productId = rs.getInt("shopProductId");
//                double promotionalPrice = rs.getDouble("promotionalPrice");
//                int discountValue = rs.getInt("discountValue");             
//                expiredDiscounts.add(new Discount(productId, discountValue, promotionalPrice));
//            }
//
//            // Update prices for products in the expired discounts list
//            for (Discount discount : expiredDiscounts) {
//                double originalPrice = calculateOriginalPrice(discount.getPromotionalPrice(), discount.getDiscountValue());
//                updatePrice(discount.getShopProductId(), originalPrice);
//            }
//
//            // Delete expired discounts after updating prices
//            deleteExpiredDiscounts();
//
//        } catch (SQLException e) {
//            System.err.println("Lỗi trong quá trình cập nhật và xoá discount đã hết hạn: " + e.getMessage());
//        }
//    }
//    private double calculateOriginalPrice(double promotionalPrice, int discountValue) {
//        return promotionalPrice / (1 - (discountValue / 100.0));
//    }
//    
//    public boolean isProductDiscounted(int productId) {
//    String sql = "SELECT COUNT(*) AS count FROM discount WHERE shopProductId = ?";
//    try (PreparedStatement st = connection.prepareStatement(sql)) {
//        st.setInt(1, productId);
//        try (ResultSet rs = st.executeQuery()) {
//            if (rs.next()) {
//                int count = rs.getInt("count");
//                return count > 0;
//            }
//        }
//    } catch (SQLException e) {
//        System.err.println("Lỗi truy vấn cơ sở dữ liệu: " + e.getMessage());
//    }
//    return false;
//}
    public boolean isProductOnDiscount(int productId, Date startDate, Date endDate) {
        String sql = """
            SELECT COUNT(*) FROM discount 
            WHERE shopProductId = ? 
            AND startDate >= GETDATE()
            AND (
                (startDate <= ? AND endDate >= ?)  
                OR 
                (startDate <= ? AND endDate >= ?) 
                OR 
                (startDate >= ? AND endDate <= ?)
            )
            """;
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, productId);
            st.setDate(2, endDate); // Compare new start with existing range
            st.setDate(3, startDate); // Compare new start with existing range
            st.setDate(4, endDate); // Compare new end with existing range
            st.setDate(5, startDate); // Compare new end with existing range
            st.setDate(6, startDate); // New range completely encloses existing
            st.setDate(7, endDate); // New range completely encloses existing
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            System.out.println("Lỗi kiểm tra sản phẩm đang giảm giá: " + e.getMessage());
        }
        return false;
    }

    public boolean isProductOnDiscount(int productId) {
        String sql = "SELECT COUNT(*) FROM discount WHERE shopProductId = ?\n"
                + "               AND endDate >= GETDATE() AND startDate<= GETDATE()";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, productId);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            System.out.println("Lỗi kiểm tra sản phẩm đang giảm giá: " + e.getMessage());
        }
        return false;
    }

    //SELECT *
    //FROM [dbo].[discount]
    //where shopProductId = 9
    public Discount getActiveDiscountByProductId(int productId) {
        String sql = "SELECT * \n"
                + "FROM discount \n"
                + "join productItem on productItem.shopProductId = discount.shopProductId\n"
                + "where productItem.productItemId = ?\n"
                + "AND GETDATE() <= endDate\n"
                + "ORDER BY startDate";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, productId);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    return new Discount(
                            rs.getInt("discountId"),
                            rs.getInt("shopProductId"),
                            rs.getInt("discountValue"),
                            rs.getDate("startDate"),
                            rs.getDate("endDate"),
                            rs.getDouble("promotionalPrice")
                    );
                }
            }
        } catch (SQLException e) {
            // Replace with your logging framework's error logging
            System.err.println("Database query error: " + e.getMessage());
        }
        return null;
    }

    public static void main(String[] args) {
        DiscountDAO dis = new DiscountDAO();
        // Định dạng ngày theo định dạng "yyyy-MM-dd"
//        Date startDate = Date.valueOf("2024-07-17");
//        Date endDate = Date.valueOf("2024-07-22");
//
//        boolean a = dis.isProductOnDiscount(9, startDate, endDate);
//        System.out.println(a);
//        dis.deleteDiscount(15);
        Discount d = dis.getActiveDiscountByProductId(43);
        System.out.println(d);
    }
}
