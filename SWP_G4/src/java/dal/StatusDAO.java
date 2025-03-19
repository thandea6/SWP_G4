/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Status;

/**
 *
 * @author admin
 */
public class StatusDAO extends DBContext {

    public List<Status> getAllStatus(int statusId) {
        String sql = "select * from Status where statusId>=?";
        List<Status> list = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, statusId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                    Status status = new Status(rs.getInt("statusId"), rs.getString("statusValue"));
                list.add(status);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return  list;
        }
     public static void main(String[] args) {
     StatusDAO a = new StatusDAO();
     
     List<Status> list = a.getAllStatus(2);
     for(Status b : list){
         System.out.println(b);
     }
     }
     
    
    
    
    }
