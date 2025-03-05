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
import model.Account;
import model.Comment;
import model.User;

/**
 *
 * @author GiaKhiem
 */
public class CommentDAO extends DBContext{
    public List<Comment> getAllCommentByShopProductId(int id) {
        List<Comment> list = new ArrayList<>();
        String sql = "select * from comment\n"
                + "join orderDetail on comment.orderDetailId=orderDetail.orderDetaiId\n"
                + "join productItem on productItem.productItemId= orderDetail.productItemId\n"
                + " JOIN account ON account.accountId = comment.accountId\n"
                + "JOIN [user] ON [user].accountId = account.accountId\n"
                + "JOIN rating on rating.ratingId = comment.ratingId\n"
                + "JOIN size on size.sizeId = productItem.sizeId\n"
                + "JOIN color on color.colorId = productItem.colorId\n"
                + "where comment.shopProductId=?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int commentId = rs.getInt("commentId");
                int accountId = rs.getInt("accountId");
                int shopProductId = rs.getInt("shopProductId");
                String content = rs.getString("content");
                String username = rs.getString("username");
                Date created_at = rs.getDate("created_at");
                Date updated_at = rs.getDate("updated_at");
                String fullName = rs.getString("fullName");
                String image = rs.getString("image");
                int starRating = rs.getInt("starRating");
                String colorValue = rs.getString("colorValue");
                String sizeValue = rs.getString("sizeValue");

                User user = new User(id, fullName, image, accountId);
                Account account = new Account(accountId, username);
                Comment comment = new Comment(commentId, accountId, shopProductId, created_at, updated_at, content, account, user, starRating, colorValue, sizeValue);
                list.add(comment);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return list;
    }
    
}
