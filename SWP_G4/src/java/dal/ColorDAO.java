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
import model.Color;

/**
 *
 * @author GiaKhiem
 */
public class ColorDAO extends DBContext{
    public List<Color> getAllColor() {

        List<Color> list = new ArrayList<>();
        String sql = "SELECT * FROM Color";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
              Color c = new Color(rs.getInt("colorId"), rs.getString("colorValue"));
                list.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return list;
    }
}
