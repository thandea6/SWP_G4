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
import model.Images;

/**
 *
 * @author GiaKhiem
 */
public class ImagesDAO extends DBContext{
    public List<Images> getAllImagesByShopProductId(int spid) {
        List<Images> list = new ArrayList<>();
        String sql = "select * from images\n"
                + "where shopProductId=?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, spid);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int imagesId = rs.getInt("imagesId");
                int shopProductId = rs.getInt("shopProductId");
                String imageLink = rs.getString("imageLink");

                Images images = new Images(imagesId, shopProductId, imageLink);

                list.add(images);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return list;
    }
}
