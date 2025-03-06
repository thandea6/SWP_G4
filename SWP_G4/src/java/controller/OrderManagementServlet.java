
package controller;

import dal.OrderDAO;
import dal.ShopDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import model.Account;
import model.Order;
import model.Shop;

/**
 *
 * @author admin
 */
@WebServlet(name = "OrderManagementServlet", urlPatterns = {"/orderManagement"})
public class OrderManagementServlet extends HttpServlet {

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        OrderDAO od = new OrderDAO();
        ShopDAO sd = new ShopDAO();
        HttpSession session = request.getSession();
        Account user = (Account) session.getAttribute("user");
        if (user == null) {
            request.getRequestDispatcher("logout").forward(request, response);
        } else {
            int accountId = user.getAccountId();
            Shop shop = sd.getShopByAccountId(accountId);
            int shopId = shop.getShopId();
            List<Order> orderList = od.getOrderListByShop(shopId);

//            List<Shop> listS;
//            listS = sd.getShop(accountId);
//            Shop shop1 = listS.get(0);

            request.setAttribute("orderList", orderList);
            request.setAttribute("shopId", shopId);
 //            request.setAttribute("shop", shop1);
            request.getRequestDispatcher("manager/orderManagement.jsp").forward(request, response);
        }
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account user = (Account) session.getAttribute("user");
        String txt = request.getParameter("phone");
        int shopId = Integer.parseInt(request.getParameter("shopId"));

        OrderDAO od = new OrderDAO();
        List<Order> list = od.getOrderListByShop(shopId);
        List<Order> orderList = new ArrayList<>();


        for (Order o : list) {
            String phone = o.getPhone();
            if (phone != null && phone.contains(txt)) {
                orderList.add(o);
            }
        }
        request.setAttribute("txt", txt);
        request.setAttribute("shopId", shopId);
        request.setAttribute("orderList", orderList);
        request.getRequestDispatcher("manager/orderManagement.jsp").forward(request, response);
    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
