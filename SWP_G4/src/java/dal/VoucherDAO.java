/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import model.Voucher;

/**
 *
 * @author VIET HOANG
 */
public class VoucherDAO extends DBContext {

    public List<Voucher> getAllVoucherByUserId(int userId) {
        List<Voucher> list = new ArrayList<>();
        String sql = "SELECT\n"
                + "    v.voucherId,\n"
                + "    v.code,\n"
                + "    v.reducedAmount,\n"
                + "    v.endDate,\n"
                + "    v.isGlobal,\n"
                + "    v.description,\n"
                + "    vu.quantity,\n"
                + "    vu.isUsed,\n"
                + "	v.shopId,\n"
                + "	s.shopName\n"
                + "FROM\n"
                + "    [dbo].[voucher] v\n"
                + "JOIN\n"
                + "    [dbo].[voucherUser] vu ON v.voucherId = vu.voucherId\n"
                + "	left join shop s on v.shopId = s.shopId\n"
                + "WHERE\n"
                + "    vu.userId = ?\n"
                + "    AND (v.isGlobal = 1 OR v.shopId IS NOT NULL) and vu.isUsed = 'False'"
                + "    and v.endDate >= GETDATE()";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, userId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Voucher v = new Voucher(rs.getInt("voucherId"), rs.getString("code"), rs.getDouble("reducedAmount"),
                        rs.getDate("endDate"), rs.getString("description"), rs.getBoolean("isGlobal"));
                v.setQuantity(rs.getInt("quantity"));
                v.setShopId(rs.getInt("shopId"));
                v.setShopName(rs.getString("shopName"));
                list.add(v);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    //hàm này chỉ dùng để lấy ra voucher để đưa vào cart, không dùng cho mục đích khác.
    public Voucher getVoucherByCode(String code) {
        String sql = "select * from voucher  where code like '" + code + "'";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Voucher v = new Voucher(rs.getInt("voucherId"), rs.getString("code"), rs.getDouble("reducedAmount"),
                        rs.getDate("endDate"), rs.getString("description"), rs.getBoolean("isGlobal"));
                return v;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public Voucher getVoucherByCodeAndUserId(String code, int userId) {
        String sql = "select voucher.*, voucherUser.quantity from voucher join voucherUser on voucherUser.voucherId = voucher.voucherId where code like '" + code + "' "
                + " and voucherUser.userId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, userId);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Voucher v = new Voucher(rs.getInt("voucherId"), rs.getString("code"), rs.getDouble("reducedAmount"),
                        rs.getDate("endDate"), rs.getString("description"), rs.getBoolean("isGlobal"));
                v.setQuantity(rs.getInt("quantity"));
                return v;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public Voucher getVoucherByCodeShop(String code) {
        String sql = "select * from voucher where code = '" + code + "'";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Voucher v = new Voucher(rs.getInt("voucherId"), rs.getString("code"), rs.getDouble("reducedAmount"),
                        rs.getDate("endDate"), rs.getString("description"), rs.getBoolean("isGlobal"));
                v.setShopId(rs.getInt("shopId"));
                return v;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public List<Voucher> getAllVoucherShop(int shopId) {
        List<Voucher> list = new ArrayList<>();
        String sql = "SELECT\n"
                + "    v.voucherId,\n"
                + "    v.code,\n"
                + "    v.reducedAmount,\n"
                + "    v.endDate,\n"
                + "    v.isGlobal,\n"
                + "    v.description,\n"
                + "	v.shopId,\n"
                + "	s.shopName\n"
                + "FROM\n"
                + "    [dbo].[voucher] v\n"
                + "	left join shop s on v.shopId = s.shopId\n"
                + "WHERE\n"
                + "    v.shopId = ?\n";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, shopId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Voucher v = new Voucher(rs.getInt("voucherId"), rs.getString("code"), rs.getDouble("reducedAmount"),
                        rs.getDate("endDate"), rs.getString("description"), rs.getBoolean("isGlobal"));
                v.setShopId(rs.getInt("shopId"));
                v.setShopName(rs.getString("shopName"));
                list.add(v);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public void updateVoucher(String code, double reducedAmount, Date endDate, String description) {
        String sql = "UPDATE [dbo].[voucher]\n"
                + "   SET [reducedAmount] = ?\n"
                + "      ,[endDate] = ?\n"
                + "      ,[description] = ?\n"
                + " WHERE code like '" + code + "'";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setDouble(1, reducedAmount);
            st.setDate(2, endDate);
            st.setString(3, description);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public List<Voucher> SearchVoucherByCode(String code, int userId) {
        List<Voucher> list = new ArrayList<>();
        String sql = "SELECT\n"
                + "    v.voucherId,\n"
                + "    v.code,\n"
                + "    v.reducedAmount,\n"
                + "    v.endDate,\n"
                + "    v.isGlobal,\n"
                + "    v.description,\n"
                + "    vu.quantity,\n"
                + "    vu.isUsed,\n"
                + "	v.shopId,\n"
                + "	s.shopName\n"
                + "FROM\n"
                + "    [dbo].[voucher] v\n"
                + "JOIN\n"
                + "    [dbo].[voucherUser] vu ON v.voucherId = vu.voucherId\n"
                + "	left join shop s on v.shopId = s.shopId\n"
                + "WHERE\n"
                + "    vu.userId = ?\n"
                + "    AND (v.isGlobal = 1 OR v.shopId IS NOT NULL) and v.code like '%" + code + "%' and vu.isUsed = 'False'"
                + "    and v.endDate >= GETDATE()";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, userId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Voucher v = new Voucher(rs.getInt("voucherId"), rs.getString("code"), rs.getDouble("reducedAmount"),
                        rs.getDate("endDate"), rs.getString("description"), rs.getBoolean("isGlobal"));
                v.setQuantity(rs.getInt("quantity"));
                v.setShopId(rs.getInt("shopId"));
                v.setShopName(rs.getString("shopName"));
                list.add(v);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Voucher> SearchVoucherByCodeShop(String code, int shopId) {
        List<Voucher> list = new ArrayList<>();
        String sql = "SELECT\n"
                + "    v.voucherId,\n"
                + "    v.code,\n"
                + "    v.reducedAmount,\n"
                + "    v.endDate,\n"
                + "    v.isGlobal,\n"
                + "    v.description,\n"
                + "	v.shopId,\n"
                + "	s.shopName\n"
                + "FROM\n"
                + "    [dbo].[voucher] v\n"
                + "	left join shop s on v.shopId = s.shopId\n"
                + "WHERE\n"
                + "    v.shopId = ? and\n"
                + "    v.code like '%" + code + "%'";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, shopId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Voucher v = new Voucher(rs.getInt("voucherId"), rs.getString("code"), rs.getDouble("reducedAmount"),
                        rs.getDate("endDate"), rs.getString("description"), rs.getBoolean("isGlobal"));
                v.setShopId(rs.getInt("shopId"));
                v.setShopName(rs.getString("shopName"));
                list.add(v);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public void createVoucherShop(String code, double reducedAmount, Date endDate, String description, int shopId) {
        String sql = "INSERT INTO [dbo].[voucher]\n"
                + "           ([code]\n"
                + "           ,[reducedAmount]\n"
                + "           ,[endDate]\n"
                + "           ,[isGlobal]\n"
                + "           ,[description]\n"
                + "           ,[shopId])\n"
                + "     VALUES (?,?,?,'0',?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, code);
            st.setDouble(2, reducedAmount);
            st.setDate(3, endDate);
            st.setString(4, description);
            st.setInt(5, shopId);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

   public void createVoucherForUser(String code, int userId, int quantity) {
        String sql1 = "select * from voucher left join voucherUser on voucher.voucherId = voucherUser.voucherId"
                + " where code = '" + code + "' and voucherUser.userId = ?";
        try {
            PreparedStatement st1 = connection.prepareStatement(sql1);
            st1.setInt(1, userId);
            ResultSet rs = st1.executeQuery();
            if (rs.next()) {
                String sql2 = "UPDATE [dbo].[voucherUser]\n"
                        + "   SET [quantity] = ?\n"
                        + "   SET [isUsed] = 'false'\n"
                        + " WHERE userId = ? and voucherId = ?";
                PreparedStatement st2 = connection.prepareStatement(sql2);
                Voucher v = getVoucherByCodeAndUserId(code, userId);
                st2.setInt(1, v.getQuantity() + quantity);
                st2.setInt(2, userId);
                st2.setInt(3, v.getVoucherId());
                st2.executeUpdate();
            } else {
                String sql3 = "INSERT INTO [dbo].[voucherUser]\n"
                        + "           ([voucherId]\n"
                        + "           ,[userId]\n"
                        + "           ,[quantity]\n"
                        + "           ,[isUsed])\n"
                        + "     VALUES (?,?,?,'false')";
                PreparedStatement st3 = connection.prepareStatement(sql3);
                Voucher v = getVoucherByCode(code);
                st3.setInt(1, v.getVoucherId());
                st3.setInt(2, userId);
                st3.setInt(3, quantity);
                st3.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        VoucherDAO v = new VoucherDAO();
        List<Voucher> list = v.getAllVoucherShop(5);
        System.out.println(list.get(0));
    }
}
