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
import model.Order;

/**
 *
 * @author ADMIN
 */
public class OrderDAO extends DBContext {

    public List<Order> getOrderCancelByShop(int shopId) {
        List<Order> orderList = new ArrayList<>();
        String sql = "SELECT DISTINCT "
                + "    [order].orderId, "
                + "    [order].orderDate, "
                + "    [order].userId, "
                + "    [order].name, "
                + "    [order].address, "
                + "    [order].phone, "
                + "    [orderDetail].statusId, "
                + "    [status].statusValue, "
                + "    SUM(orderDetail.price * orderDetail.quantity) AS totalPrice "
                + "FROM "
                + "    [order] "
                + "JOIN "
                + "    orderDetail ON [order].orderId = orderDetail.orderId "
                + "JOIN "
                + "    productItem ON productItem.productItemId = orderDetail.productItemId "
                + "JOIN "
                + "    shopProduct ON shopProduct.id = productItem.shopProductId "
                + "JOIN "
                + "    shop ON shop.shopId = shopProduct.shopId "
                + "JOIN "
                + "    status ON orderDetail.statusId = status.statusId "
                + "WHERE "
                + "    shop.shopId = ? AND orderDetail.statusId =4 "
                + "GROUP BY "
                + "    [order].orderId, "
                + "    [order].orderDate, "
                + "    [order].userId, "
                + "    [order].name, "
                + "    [order].address, "
                + "    [order].phone, "
                + "    [orderDetail].statusId, "
                + "    [status].statusValue "
                + "ORDER BY "
                + "    [order].orderId DESC";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, shopId);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    Order o = new Order(rs.getInt("OrderId"),
                            rs.getInt("userId"),
                            rs.getDate("OrderDate"),
                            rs.getDouble("totalPrice"),
                            rs.getString("name"),
                            rs.getString("address"),
                            rs.getString("phone"),
                            rs.getInt("statusId"),
                            rs.getString("statusValue")
                    );
                    orderList.add(o);
                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi cơ sở dữ liệu: " + e.getMessage());
        }

        return orderList;
    }

    public List<Order> getOrderListByShop(int shopId) {
        List<Order> orderList = new ArrayList<>();
        String sql = "SELECT DISTINCT "
                + "    [order].orderId, "
                + "    [order].orderDate, "
                + "    [order].userId, "
                + "    [order].name, "
                + "    [order].address, "
                + "    [order].phone, "
                + "    [orderDetail].statusId, "
                + "    [status].statusValue, "
                + "    SUM(orderDetail.price * orderDetail.quantity) AS totalPrice "
                + "FROM "
                + "    [order] "
                + "JOIN "
                + "    orderDetail ON [order].orderId = orderDetail.orderId "
                + "JOIN "
                + "    productItem ON productItem.productItemId = orderDetail.productItemId "
                + "JOIN "
                + "    shopProduct ON shopProduct.id = productItem.shopProductId "
                + "JOIN "
                + "    shop ON shop.shopId = shopProduct.shopId "
                + "JOIN "
                + "    status ON orderDetail.statusId = status.statusId "
                + "WHERE "
                + "    shop.shopId = ? AND orderDetail.statusId !=4 "
                + "GROUP BY "
                + "    [order].orderId, "
                + "    [order].orderDate, "
                + "    [order].userId, "
                + "    [order].name, "
                + "    [order].address, "
                + "    [order].phone, "
                + "    [orderDetail].statusId, "
                + "    [status].statusValue "
                + "ORDER BY "
                + "    [order].orderId DESC";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, shopId);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    Order o = new Order(rs.getInt("OrderId"),
                            rs.getInt("userId"),
                            rs.getDate("OrderDate"),
                            rs.getDouble("totalPrice"),
                            rs.getString("name"),
                            rs.getString("address"),
                            rs.getString("phone"),
                            rs.getInt("statusId"),
                            rs.getString("statusValue")
                    );
                    orderList.add(o);
                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi cơ sở dữ liệu: " + e.getMessage());
        }

        return orderList;
    }

    public List<Order> getOrderPendingByShop(int shopId) {
        List<Order> orderList = new ArrayList<>();
        String sql = "SELECT DISTINCT "
                + "    [order].orderId, "
                + "    [order].orderDate, "
                + "    [order].userId, "
                + "    [order].name, "
                + "    [order].address, "
                + "    [order].phone, "
                + "    [orderDetail].statusId, "
                + "    [status].statusValue, "
                + "    SUM(orderDetail.price * orderDetail.quantity) AS totalPrice "
                + "FROM "
                + "    [order] "
                + "JOIN "
                + "    orderDetail ON [order].orderId = orderDetail.orderId "
                + "JOIN "
                + "    productItem ON productItem.productItemId = orderDetail.productItemId "
                + "JOIN "
                + "    shopProduct ON shopProduct.id = productItem.shopProductId "
                + "JOIN "
                + "    shop ON shop.shopId = shopProduct.shopId "
                + "JOIN "
                + "    status ON orderDetail.statusId = status.statusId "
                + "WHERE "
                + "    shop.shopId = ? AND orderDetail.statusId =1 "
                + "GROUP BY "
                + "    [order].orderId, "
                + "    [order].orderDate, "
                + "    [order].userId, "
                + "    [order].name, "
                + "    [order].address, "
                + "    [order].phone, "
                + "    [orderDetail].statusId, "
                + "    [status].statusValue "
                + "ORDER BY "
                + "    [order].orderId DESC";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, shopId);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    Order o = new Order(rs.getInt("OrderId"),
                            rs.getInt("userId"),
                            rs.getDate("OrderDate"),
                            rs.getDouble("totalPrice"),
                            rs.getString("name"),
                            rs.getString("address"),
                            rs.getString("phone"),
                            rs.getInt("statusId"),
                            rs.getString("statusValue")
                    );
                    orderList.add(o);
                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi cơ sở dữ liệu: " + e.getMessage());
        }

        return orderList;
    }
}
