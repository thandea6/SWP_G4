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

/**
 *
 * @author GiaKhiem
 */
public class CategoryDAO extends DBContext{
    public List<Category> getCategoryAll() {
        List<Category> list = new ArrayList<>();
        String sql = "SELECT * from category";

        try (PreparedStatement st = connection.prepareStatement(sql); 
            ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                Category c = new Category();
                c.setCategoryId(rs.getInt("categoryId"));
                c.setCategoryName(rs.getString("categoryName"));
                list.add(c);
            }
        } catch (SQLException e) {

        }
        return list;
    }
}
