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
import model.Discount;

/**
 *
 * @author GiaKhiem
 */
public class DiscountDAO extends DBContext{
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
}
