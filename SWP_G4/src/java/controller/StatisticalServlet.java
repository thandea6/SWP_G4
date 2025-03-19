/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
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

/**
 *
 * @author admin
 */
@WebServlet(name = "StatisticalServlet", urlPatterns = {"/statistical"})
public class StatisticalServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet StatisticalServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet StatisticalServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
            List<ProductLine> list1 = sp.getQuantityAndPriceSoldByAccountId(accountId);
            int totalQuantitySold = sp.getTotalQuantitySoldByAccountId(accountId);
            int totalPriceSold = sp.getTotalPriceSoldByAccountId(accountId);
            int userCount = sp.getTotalUserBuyProductByAccountId(accountId);

            request.setAttribute("userCount", userCount);
            request.setAttribute("totalQuantitySold", totalQuantitySold);
            request.setAttribute("totalPriceSold", totalPriceSold);
            request.setAttribute("listSold", list1);
            request.setAttribute("shop", shop);
            request.getRequestDispatcher("statistical.jsp").forward(request, response);
        }

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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

            int t1 = o.getTotalQuantitySoldByAccountIdAndMonth(year, accountId, 1);
            int t2 = o.getTotalQuantitySoldByAccountIdAndMonth(year, accountId, 2);
            int t3 = o.getTotalQuantitySoldByAccountIdAndMonth(year, accountId, 3);
            int t4 = o.getTotalQuantitySoldByAccountIdAndMonth(year, accountId, 4);
            int t5 = o.getTotalQuantitySoldByAccountIdAndMonth(year, accountId, 5);
            int t6 = o.getTotalQuantitySoldByAccountIdAndMonth(year, accountId, 6);
            int t7 = o.getTotalQuantitySoldByAccountIdAndMonth(year, accountId, 7);
            int t8 = o.getTotalQuantitySoldByAccountIdAndMonth(year, accountId, 8);
            int t9 = o.getTotalQuantitySoldByAccountIdAndMonth(year, accountId, 9);
            int t10 = o.getTotalQuantitySoldByAccountIdAndMonth(year, accountId, 10);
            int t11 = o.getTotalQuantitySoldByAccountIdAndMonth(year, accountId, 11);
            int t12 = o.getTotalQuantitySoldByAccountIdAndMonth(year, accountId, 12);

            List<Integer> dataListMonth = Arrays.asList(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12);

            int d1 = o.getTotalQuantitySoldByDay(accountId, year, month, 1);
            int d2 = o.getTotalQuantitySoldByDay(accountId, year, month, 2);
            int d3 = o.getTotalQuantitySoldByDay(accountId, year, month, 3);
            int d4 = o.getTotalQuantitySoldByDay(accountId, year, month, 4);
            int d5 = o.getTotalQuantitySoldByDay(accountId, year, month, 5);
            int d6 = o.getTotalQuantitySoldByDay(accountId, year, month, 6);
            int d7 = o.getTotalQuantitySoldByDay(accountId, year, month, 7);
            int d8 = o.getTotalQuantitySoldByDay(accountId, year, month, 8);
            int d9 = o.getTotalQuantitySoldByDay(accountId, year, month, 9);
            int d10 = o.getTotalQuantitySoldByDay(accountId, year, month, 10);
            int d11 = o.getTotalQuantitySoldByDay(accountId, year, month, 11);
            int d12 = o.getTotalQuantitySoldByDay(accountId, year, month, 12);
            int d13 = o.getTotalQuantitySoldByDay(accountId, year, month, 13);
            int d14 = o.getTotalQuantitySoldByDay(accountId, year, month, 14);
            int d15 = o.getTotalQuantitySoldByDay(accountId, year, month, 15);
            int d16 = o.getTotalQuantitySoldByDay(accountId, year, month, 16);
            int d17 = o.getTotalQuantitySoldByDay(accountId, year, month, 17);
            int d18 = o.getTotalQuantitySoldByDay(accountId, year, month, 18);
            int d19 = o.getTotalQuantitySoldByDay(accountId, year, month, 19);
            int d20 = o.getTotalQuantitySoldByDay(accountId, year, month, 20);
            int d21 = o.getTotalQuantitySoldByDay(accountId, year, month, 21);
            int d22 = o.getTotalQuantitySoldByDay(accountId, year, month, 22);
            int d23 = o.getTotalQuantitySoldByDay(accountId, year, month, 23);
            int d24 = o.getTotalQuantitySoldByDay(accountId, year, month, 24);
            int d25 = o.getTotalQuantitySoldByDay(accountId, year, month, 25);
            int d26 = o.getTotalQuantitySoldByDay(accountId, year, month, 26);
            int d27 = o.getTotalQuantitySoldByDay(accountId, year, month, 27);
            int d28 = o.getTotalQuantitySoldByDay(accountId, year, month, 28);
            int d29 = o.getTotalQuantitySoldByDay(accountId, year, month, 29);
            int d30 = o.getTotalQuantitySoldByDay(accountId, year, month, 30);

            List<Integer> dataListDay = Arrays.asList(d1, d2, d3, d4, d5, d6, d7, d8, d9, d10, d11, d12, d13, d14, d15, d16, d17, d18, d19, d20, d21, d22, d23, d24, d25, d26, d27, d28, d29, d30);

            List<ProductLine> list1 = sp.getQuantityAndPriceSoldByAccountId(accountId);
            int totalQuantitySold = sp.getTotalQuantitySoldByAccountId(accountId);
            int totalPriceSold = sp.getTotalPriceSoldByAccountId(accountId);
            int userCount = sp.getTotalUserBuyProductByAccountId(accountId);

            request.setAttribute("userCount", userCount);
            request.setAttribute("totalQuantitySold", totalQuantitySold);
            request.setAttribute("totalPriceSold", totalPriceSold);
            request.setAttribute("listSold", list1);
            request.setAttribute("dataListDay", dataListDay);
            request.setAttribute("dataListMonth", dataListMonth);
            request.setAttribute("shop", shop);
            request.getRequestDispatcher("statistical.jsp").forward(request, response);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
