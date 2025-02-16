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
import model.Brand;

/**
 *
 * @author GiaKhiem
 */
public class BrandDAO extends DBContext{
    public List<Brand> getBrandAll() {
        List<Brand> list = new ArrayList<>();
        String sql = "SELECT * from brand";

        try (PreparedStatement st = connection.prepareStatement(sql); 
            ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                Brand c = new Brand();
                c.setBrandId(rs.getInt("brandId"));
                c.setBrandName(rs.getString("brandName"));
                c.setImage(rs.getString("image"));
                list.add(c);
            }
        } catch (SQLException e) {

        }
        return list;
    }
}
