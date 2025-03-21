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
import model.OrderDetail;
import model.ProductLine;

/**
 *
 * @author admin
 */
public class OrderDAO extends DBContext {

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

//    public void updateStatus(int orderId, int shopId, int statusId) {
//        try {
//            String Sql = "UPDATE od SET od.statusId = ?\n"
//                    + "                    FROM orderDetail od\n"
//                    + "                    JOIN productItem p ON p.productItemId = od.productItemId\n"
//                    + "                    JOIN shopProduct sp ON p.shopProductId = sp.id\n"
//                    + "                    WHERE od.orderId = ?\n"
//                    + "                    AND sp.shopId = ?\n"
//                    + "					AND od.statusId !=4";
//            PreparedStatement st1 = connection.prepareStatement(Sql);
//            st1.setInt(1, statusId);
//            st1.setInt(2, orderId);
//            st1.setInt(3, shopId);
//            st1.executeUpdate();
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//    }
    public void cancelStatus(int orderId, int shopId) {
        try {
            String Sql = "UPDATE od SET od.statusId = 4\n"
                    + "                    FROM orderDetail od\n"
                    + "                    JOIN productItem p ON p.productItemId = od.productItemId\n"
                    + "                    JOIN shopProduct sp ON p.shopProductId = sp.id\n"
                    + "                    WHERE od.orderId = ?\n"
                    + "                    AND sp.shopId = ?\n";
            PreparedStatement st1 = connection.prepareStatement(Sql);
            st1.setInt(1, orderId);
            st1.setInt(2, shopId);
            st1.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void nextStatus(int orderId, int shopId) {
        try {
            // Tìm `StatusID` hiện tại của đơn hàng
            String getStatusSql = "select od.statusId\n"
                    + "FROM orderDetail od\n"
                    + "JOIN productItem p ON p.productItemId = od.productItemId\n"
                    + "JOIN shopProduct sp ON p.shopProductId = sp.id\n"
                    + "WHERE od.orderId = ?\n"
                    + "AND sp.shopId = ?;";
            PreparedStatement st = connection.prepareStatement(getStatusSql);

            st.setInt(1, orderId);
            st.setInt(2, shopId);
            ResultSet rs = st.executeQuery();

            int currentStatus = 0;
            if (rs.next()) {
                currentStatus = rs.getInt("statusId");
            } else {
                // Không tìm thấy đơn hàng với ID tương ứng
                // Xử lý lỗi hoặc thông báo
                return;
            }

            if (currentStatus < 3) {
                // Chỉ tăng `StatusID` nếu nó nhỏ hơn 3
                int newStatus = currentStatus + 1;

                // Cập nhật `StatusID`
                String updateStatusSql = """
                                         UPDATE od
                                         SET od.statusId = ?
                                         FROM orderDetail od
                                         JOIN productItem p ON p.productItemId = od.productItemId
                                         JOIN shopProduct sp ON p.shopProductId = sp.id
                                         WHERE od.orderId = ?
                                         AND sp.shopId = ?;""";
                PreparedStatement st1 = connection.prepareStatement(updateStatusSql);
                st1.setInt(1, newStatus);
                st1.setInt(2, orderId);
                st1.setInt(3, shopId);
                st1.executeUpdate();
            } else {

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getStatusByOrder(int orderId, int shopId) {
        String sql = """
                 SELECT od.statusId
                 FROM orderDetail od
                 JOIN productItem p ON p.productItemId = od.productItemId
                 JOIN shopProduct sp ON p.shopProductId = sp.id
                 WHERE od.orderId = ?
                 AND sp.shopId = ?;""";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, orderId);
            st.setInt(2, shopId);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt("statusId");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }

    public Order getOrderByOrderDetailId(int orderDetailId) {
        Order o = new Order();
        OrderDetailDAO odd = new OrderDetailDAO();
        OrderDetail od = odd.getOrderDetailById(orderDetailId);
        String sql = "select * from [order] where orderId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, od.getOrderId());
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                o.setTotalMoney(rs.getDouble("totalMoney"));
                o.setOrderId(rs.getInt("orderId"));
            }
        } catch (SQLException e) {

        }
        return o;
    }

    public List<Order> getAllOrderByUserId(int userId) {
        List<Order> orderList = new ArrayList<>();
        String sql = "select * from [order] where [order].userId = ? order by orderDate desc";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, userId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Order o = new Order(rs.getInt("OrderId"),
                        rs.getInt("userId"),
                        rs.getDate("orderDate"),
                        rs.getDouble("totalMoney"),
                        rs.getString("name"),
                        rs.getString("address"),
                        rs.getString("phone")
                );
                orderList.add(o);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return orderList;
    }

    public int getLatestOrderIdByUserId(int userId) {

        String sql = "SELECT top 1 * FROM [order] WHERE [order].userId = ? ORDER BY OrderDate DESC";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, userId);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Order o = new Order(rs.getInt("OrderId"),
                        rs.getInt("userId"),
                        rs.getDate("orderDate"),
                        rs.getDouble("totalMoney"),
                        rs.getString("name"),
                        rs.getString("address"),
                        rs.getString("phone")
                );
                return rs.getInt("OrderId");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }

    public int getTotalQuantitySoldByAccountIdAndMonth(int year,int accountId, int month) {
        String sql = "SELECT \n"
                + "    SUM(orderDetail.quantity) AS totalQuantitySold\n"
                + "FROM [order]\n"
                + "JOIN orderDetail ON orderDetail.orderId = [order].orderId\n"
                + "JOIN productItem ON productItem.productItemId = orderDetail.productItemId\n"
                + "JOIN shopProduct ON shopProduct.id = productItem.shopProductId\n"
                + "JOIN shop ON shop.shopId = shopProduct.shopId\n"
                + "WHERE YEAR([order].orderDate) = ?\n"
                + "  AND shop.accountId = ?\n"
                + "  AND MONTH([order].orderDate) = ?\n"
                + "GROUP BY MONTH([order].orderDate)\n"
                + "ORDER BY MONTH([order].orderDate);";
        int totalQuantitySold = 0;
        try (PreparedStatement st = connection.prepareStatement(sql)) {
             st.setInt(1, year);
            st.setInt(2, accountId);
            st.setInt(3, month);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    totalQuantitySold = rs.getInt("totalQuantitySold");
                }
            }
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }
        return totalQuantitySold;
    }

    public int getTotalQuantitySoldByDay(int accountId, int year, int month, int day) {
    int totalQuantitySold = 0;
    String sql = "SELECT " +
            "    SUM(orderDetail.quantity) AS totalQuantitySold " +
            "FROM [order] " +
            "JOIN orderDetail ON orderDetail.orderId = [order].orderId " +
            "JOIN productItem ON productItem.productItemId = orderDetail.productItemId " +
            "JOIN shopProduct ON shopProduct.id = productItem.shopProductId " +
            "JOIN shop ON shop.shopId = shopProduct.shopId " +
            "WHERE YEAR([order].orderDate) = ? " +
            "    AND MONTH([order].orderDate) = ? " +
            "    AND DAY([order].orderDate) = ? " +
            "    AND shop.accountId = ? " +
            "GROUP BY DAY([order].orderDate);";
    try {
        PreparedStatement st = connection.prepareStatement(sql);
        st.setInt(1, year);
        st.setInt(2, month);
        st.setInt(3, day);
        st.setInt(4, accountId);
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            totalQuantitySold = rs.getInt("totalQuantitySold");
        }
        rs.close();
        st.close();
    } catch (SQLException e) {
        System.out.println("Error fetching total quantities by day: " + e.getMessage());
    }
    return totalQuantitySold;
}


    public static void main(String[] args) {
        OrderDAO o = new OrderDAO();
        int a = o.getStatusByOrder(31, 1);
        System.out.println(a);
//        List<Order> x = o.getAllOrderByUserId(4);
//        System.out.println(x.get(0));
//        o.updateStatus(28, 1, 2);
    }

}
