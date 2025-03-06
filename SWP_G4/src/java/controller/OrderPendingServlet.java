
package controller;

import dal.OrderDAO;
import dal.ShopDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import model.Account;
import model.Order;
import model.Shop;


public class OrderPendingServlet extends HttpServlet {
   
    
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
            List<Order> orderList = od.getOrderPendingByShop(shopId);

            List<Shop> listS;
            listS = sd.getShop(accountId);
            Shop shop1 = listS.get(0);

            request.setAttribute("orderList", orderList);
            request.setAttribute("shopId", shopId);
            request.setAttribute("shop", shop1);
            request.getRequestDispatcher("manager/orderPending.jsp").forward(request, response);
        }
    } 

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
       HttpSession session = request.getSession();
        Account user = (Account) session.getAttribute("user");
        int accountId = user.getAccountId();
        String txt = request.getParameter("phone");
        int shopId = Integer.parseInt(request.getParameter("shopId"));

        OrderDAO od = new OrderDAO();
        List<Order> list = od.getOrderPendingByShop(shopId);
        List<Order> orderList = new ArrayList<>();
        ShopDAO sd = new ShopDAO();
        List<Shop> listS;
        listS = sd.getShop(accountId);
        Shop shop = listS.get(0);

        for (Order o : list) {
            String phone = o.getPhone();
            if (phone != null && phone.contains(txt)) {
                orderList.add(o);
            }
        }
        request.setAttribute("shop", shop);
        request.setAttribute("txt", txt);
        request.setAttribute("shopId", shopId);
        request.setAttribute("orderList", orderList);
        request.getRequestDispatcher("manager/orderPending.jsp").forward(request, response);
    }

  
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
