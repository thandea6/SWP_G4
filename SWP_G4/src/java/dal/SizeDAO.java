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
import model.Size;

/**
 *
 * @author GiaKhiem
 */
public class SizeDAO extends DBContext{
    public List<Size> getAllSize() {

        List<Size> list = new ArrayList<>();
        String sql = "SELECT * FROM size";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
              Size z = new Size(rs.getInt("sizeId"), rs.getInt("sizeValue"));
              list.add(z);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return list;
    }
}
