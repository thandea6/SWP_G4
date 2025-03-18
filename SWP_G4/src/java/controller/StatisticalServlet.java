package controller;

import dal.OrderDAO;
import dal.ShopDAO;
import dal.ShopProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.Account;
import model.Order;
import model.ProductLine;
import model.Shop;
import model.ShopProduct;

@WebServlet(name = "StatisticalServlet", urlPatterns = {"/statistical"})
public class StatisticalServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account user = (Account) session.getAttribute("user");
        if (user == null) {
            request.getRequestDispatcher("logout").forward(request, response);
        } else {
            int accountId = user.getAccountId();
            ShopDAO sd = new ShopDAO();
            OrderDAO o = new OrderDAO();
            ShopProductDAO sp = new ShopProductDAO();
            List<Shop> listS;
            listS = sd.getShop(accountId);
            Shop shop = listS.get(0);
            List<ProductLine> list1 = sp.getQuantityAndPriceSoldByAccountId();
            int totalQuantitySold = sp.getTotalQuantitySoldByAccountId();
            int totalPriceSold = sp.getTotalPriceSoldByAccountId();
            int userCount = sp.getTotalUserBuyProductByAccountId();

            request.setAttribute("userCount", userCount);
            request.setAttribute("totalQuantitySold", totalQuantitySold);
            request.setAttribute("totalPriceSold", totalPriceSold);
            request.setAttribute("listSold", list1);
            request.setAttribute("shop", shop);
            request.getRequestDispatcher("manager/statistical.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account user = (Account) session.getAttribute("user");
        if (user == null) {
            // Handle case where user is not logged in
            request.getRequestDispatcher("logout").forward(request, response);
        } else {
            int accountId = user.getAccountId();
            ShopDAO sd = new ShopDAO();
            OrderDAO o = new OrderDAO();
            ShopProductDAO sp = new ShopProductDAO();
            List<Shop> listS;
            listS = sd.getShop(accountId);
            Shop shop = listS.get(0);
            int month = Integer.parseInt(request.getParameter("month"));
            int year = Integer.parseInt(request.getParameter("year"));

            List<Integer> dataListMonth = new ArrayList<>();
            for (int i = 1; i <= 12; i++) {
                dataListMonth.add(o.getTotalQuantitySoldByAccountIdAndMonth(year, accountId, i));
            }
            List<Integer> dataListDay = new ArrayList<>();
            for (int i = 1; i <= 30; i++) {
                dataListDay.add(o.getTotalQuantitySoldByDay(accountId, year, month, i));
            }

            List<ProductLine> list1 = sp.getQuantityAndPriceSoldByAccountId();
            int totalQuantitySold = sp.getTotalQuantitySoldByAccountId();
            int totalPriceSold = sp.getTotalPriceSoldByAccountId();
            int userCount = sp.getTotalUserBuyProductByAccountId();

            request.setAttribute("userCount", userCount);
            request.setAttribute("totalQuantitySold", totalQuantitySold);
            request.setAttribute("totalPriceSold", totalPriceSold);
            request.setAttribute("listSold", list1);
            request.setAttribute("dataListDay", dataListDay);
            request.setAttribute("dataListMonth", dataListMonth);
            request.setAttribute("shop", shop);
            request.getRequestDispatcher("manager/statistical.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
