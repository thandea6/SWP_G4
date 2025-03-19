/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.lang.reflect.Array;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import model.Order;
import model.OrderDetail;
import model.ProductItem;
import model.Voucher;

/**
 *
 * @author VIET HOANG
 */
public class OrderDetailDAO extends DBContext {

    public List<OrderDetail> listAllOrderDetailByUserId(int userId, int orderId) {
        List<OrderDetail> list = new ArrayList<>();
        String sql = "select [order].orderDate, orderDetail.orderDetaiId, orderDetail.quantity, [status].statusValue, color.colorValue, \n"
                + "size.sizeValue, shopProduct.title, orderDetail.price, shop.shopId, shop.shopName, shopProduct.[image], productItem.shopProductId,"
                + " productItem.productItemId, [status].statusId,rating.starRating, voucher.code, voucher.reducedAmount  from [order] "
                + "                                       join orderDetail on [order].orderId = orderDetail.orderId \n"
                + "					  join productItem on productItem.productItemId = orderDetail.productItemId \n"
                + "					  join shopProduct on shopProduct.id = productItem.shopProductId\n"
                + "					  join color on productItem.colorId = color.colorId\n"
                + "					  join size on productItem.sizeId = size.sizeId\n"
                + "					  join [status] on orderDetail.statusId = [status].statusId\n"
                + "					  join shop on shopProduct.shopId = shop.shopId\n"
                + "                                        left join rating on rating.orderDetailId= [orderDetail].orderDetaiId\n"
                + "                                       left join voucher on orderDetail.voucherId = voucher.voucherId\n"
                + "					  where [order].userId = ? and [order].orderId = ? order by [order].orderDate desc";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, userId);
            st.setInt(2, orderId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int orderDetaiId = rs.getInt("orderDetaiId");
                int quantity = rs.getInt("quantity");
                String statusValue = rs.getString("statusValue");
                String colorValue = rs.getString("colorValue");
                String sizeValue = rs.getString("sizeValue");
                String title = rs.getString("title");
                double price = rs.getDouble("price");
                Date orderDate = rs.getDate("orderDate");
                int shopId = rs.getInt("shopId");
                String shopName = rs.getString("shopName");
                String image = rs.getString("image");
                int statusId = rs.getInt("statusId");
                int shopProductId = rs.getInt("shopProductId");
                int productItemId = rs.getInt("productItemId");
                double reducedAmount = rs.getDouble("reducedAmount");
                String code = rs.getString("code");
                String starRating = rs.getString("starRating");
                OrderDetail order = new OrderDetail(orderDetaiId, quantity, statusValue, colorValue, sizeValue, title, orderDate, price, shopId, shopName, image, starRating);
                order.setStatusId(statusId);
                order.setShopProductId(shopProductId);
                order.setProductItemId(productItemId);
                order.setOrderId(orderId);
                order.setReducedAmount(reducedAmount);
                order.setCode(code);
                list.add(order);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<OrderDetail> listAllOrderDetailByOrderIdAndKey(int orderId, String key) {
        List<OrderDetail> list = new ArrayList<>();
        String sql = "select [order].orderDate, orderDetail.orderDetaiId, orderDetail.quantity, [status].statusValue, color.colorValue, \n"
                + "size.sizeValue, shopProduct.title, orderDetail.price, shop.shopId, shop.shopName, shopProduct.[image], productItem.shopProductId,"
                + " productItem.productItemId, [status].statusId, voucher.code, voucher.reducedAmount from [order] "
                + "                                       join orderDetail on [order].orderId = orderDetail.orderId \n"
                + "					  join productItem on productItem.productItemId = orderDetail.productItemId \n"
                + "					  join shopProduct on shopProduct.id = productItem.shopProductId\n"
                + "					  join color on productItem.colorId = color.colorId\n"
                + "					  join size on productItem.sizeId = size.sizeId\n"
                + "					  join [status] on orderDetail.statusId = [status].statusId\n"
                + "					  join shop on shopProduct.shopId = shop.shopId\n"
                + "                                       left join voucher on orderDetail.voucherId = voucher.voucherId\n"
                + "					  where [order].orderId = ? and shopProduct.title like '%" + key + "%' order by [order].orderDate desc";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, orderId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int orderDetaiId = rs.getInt("orderDetaiId");
                int quantity = rs.getInt("quantity");
                String statusValue = rs.getString("statusValue");
                String colorValue = rs.getString("colorValue");
                String sizeValue = rs.getString("sizeValue");
                String title = rs.getString("title");
                double price = rs.getDouble("price");
                Date orderDate = rs.getDate("orderDate");
                int shopId = rs.getInt("shopId");
                double reducedAmount = rs.getDouble("reducedAmount");
                String shopName = rs.getString("shopName");
                String image = rs.getString("image");
                int statusId = rs.getInt("statusId");
                int shopProductId = rs.getInt("shopProductId");
                String code = rs.getString("code");
                int productItemId = rs.getInt("productItemId");
                OrderDetail order = new OrderDetail(orderDetaiId, quantity, statusValue, colorValue, sizeValue, title, orderDate, price, shopId, shopName, image);
                order.setStatusId(statusId);
                order.setCode(code);
                order.setReducedAmount(reducedAmount);
                order.setShopProductId(shopProductId);
                order.setProductItemId(productItemId);
                list.add(order);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public void cancelOrder(int orderDetailId, String reason) {
        OrderDAO od = new OrderDAO();
        String sql = "UPDATE [dbo].[orderDetail]\n"
                + "   SET [statusId] = 4,\n"
                + "       [description] = '" + reason + "'\n"
                + " WHERE orderDetaiId = ?";

        String sql2 = "UPDATE [dbo].[order]\n"
                + "   SET [totalMoney] = ?\n"
                + " WHERE orderId = ?";
        
        String sql3 = "UPDATE [dbo].[productItem]\n"
                + "   SET [quantity] = ?\n"
                + " WHERE productItemId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, orderDetailId);
            st.executeUpdate();
            PreparedStatement st2 = connection.prepareStatement(sql2);
            Order o = od.getOrderByOrderDetailId(orderDetailId);
            OrderDetail odd = getOrderDetailById(orderDetailId);
            double newPrice = 0;
            
            newPrice = o.getTotalMoney() - odd.getPrice();
            st2.setDouble(1, newPrice);
            st2.setInt(2, o.getOrderId());
            st2.executeUpdate();
            PreparedStatement st3 = connection.prepareStatement(sql3);
            ProductItemDAO pid = new ProductItemDAO();
            ProductItem p = pid.getProductItemByProductItemID(odd.getProductItemId());
            st3.setInt(1, p.getQuantity()+odd.getQuantity());
            st3.setInt(2, p.getProductItemId());
            st3.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public OrderDetail getOrderDetailById(int orderDetailId) {
        String sql = "select orderDetail.*, voucher.code from orderDetail"
                + " left join voucher on orderDetail.voucherId = voucher.voucherId where orderDetaiId =  ?";
        OrderDetail od = new OrderDetail();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, orderDetailId);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                String code = rs.getString("code");
                if (code != null) {
                    VoucherDAO vd = new VoucherDAO();
                    Voucher v = vd.getVoucherByCode(code);
                    od.setCode(code);                   
                    od.setReducedAmount(v.getReducedAmount());
                }
                od.setProductItemId(rs.getInt("productItemId"));
                od.setPrice(rs.getDouble("price"));
                od.setOrderId(rs.getInt("orderId"));
                od.setQuantity(rs.getInt("quantity"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return od;
    }

    public List<OrderDetail> getOrderDetailByOrderId(int orderId) {
        List<OrderDetail> list = new ArrayList<>();
        String sql = "select * from [orderDetail]\n"
                + "join [order] on orderDetail.orderId = [order].orderId \n"
                + "join productItem on productItem.productItemId = orderDetail.productItemId \n"
                + "join shopProduct on shopProduct.id = productItem.shopProductId\n"
                + "join color on productItem.colorId = color.colorId\n"
                + "join size on productItem.sizeId = size.sizeId\n"
                + "join [status] on orderDetail.statusId = [status].statusId\n"
                + "join shop on shopProduct.shopId = shop.shopId\n"
                + "where [order].orderId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, orderId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int orderDetaiId = rs.getInt("orderDetaiId");
                int quantity = rs.getInt(3);
                String colorValue = rs.getString("colorValue");
                String sizeValue = rs.getString("sizeValue");
                String title = rs.getString("title");
                double price = rs.getDouble(4);
                Date orderDate = rs.getDate("orderDate");
                int shopId = rs.getInt("shopId");
                String shopName = rs.getString("shopName");
                String image = rs.getString("image");
                int statusId = rs.getInt("statusId");
                int shopProductId = rs.getInt("shopProductId");
                int productItemId = rs.getInt("productItemId");
                OrderDetail order = new OrderDetail(orderDetaiId, quantity, colorValue, sizeValue, title, orderDate, price, shopId, shopName, image, rs.getDouble(9), productItemId);
                list.add(order);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public static void main(String[] args) {
        OrderDetailDAO ord = new OrderDetailDAO();
//        System.out.println(ord.listAllOrderDetailByUserId(4, 18));
        List<OrderDetail> list = new ArrayList<OrderDetail>();
        list = ord.getOrderDetailByOrderId(18);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
}
