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
import model.Rating;
import model.User;

/**
 *
 * @author GiaKhiem
 */
public class RatingDAO extends DBContext{
    public List<Rating> getAllRatingByShopProductId(int id) {
        List<Rating> list = new ArrayList<>();
        String sql = "select * from rating\n"
                + "                join orderDetail on rating.orderDetailId=orderDetail.orderDetaiId\n"
                + "                join productItem on productItem.productItemId= orderDetail.productItemId  \n"
                + "               JOIN [user] ON [user].userId = rating.userId\n"
                + "              JOIN comment on rating.commentId = comment.commentId\n"
                + "              JOIN size on size.sizeId = productItem.sizeId\n"
                + "               JOIN color on color.colorId = productItem.colorId\n"
                + "			   where rating.shopProductId=?\n"
                + "               ";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int ratingId = rs.getInt("ratingId");
                int accountId = rs.getInt("accountId");
                 int userId = rs.getInt("userId");
                int shopProductId = rs.getInt("shopProductId");
                String content = rs.getString("content");
                
                Date created_at = rs.getDate("created_at");
                Date updated_at = rs.getDate("updated_at");
                String fullName = rs.getString("fullName");
                String image = rs.getString("image");
                int starRating = rs.getInt("starRating");
                String colorValue = rs.getString("colorValue");
                String sizeValue = rs.getString("sizeValue");

                User user = new User(userId, fullName, image, accountId);
                
                Rating rating = new Rating(ratingId, userId, shopProductId, created_at, updated_at, starRating, starRating, user, content, colorValue, sizeValue);
                list.add(rating);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return list;
    }
    public Rating getAvgRatingByShopProductId(int shopProductid) {
        String sql = "SELECT  AVG(starRating) AS average_starrating\n"
                + "FROM rating\n"
                + "where shopProductId=?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, shopProductid);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    return new Rating(rs.getInt("average_starrating"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi cơ sở dữ liệu: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
    public Rating countRatingByShop(int shopId) {
        String sql = "SELECT COUNT(r.starRating) AS starRatingCount\n"
                + "FROM rating r\n"
                + "JOIN shopProduct sp ON sp.id = r.shopProductId\n"
                + "WHERE sp.shopId = ?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, shopId);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    return new Rating(shopId, rs.getInt("starRatingCount"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi cơ sở dữ liệu: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}
