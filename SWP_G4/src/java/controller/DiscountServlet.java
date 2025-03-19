/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.DiscountDAO;
import dal.ProductLineDAO;
import dal.ShopDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import model.Account;
import model.Discount;
import model.ProductLine;
import model.Shop;

/**
 *
 * @author admin
 */
@WebServlet(name = "DiscountServlet", urlPatterns = {"/discount"})
public class DiscountServlet extends HttpServlet {

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
            out.println("<title>Servlet DiscountServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DiscountServlet at " + request.getContextPath() + "</h1>");
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
        // Get current date
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = currentDate.format(formatter);

        if (user == null) {
            request.getRequestDispatcher("logout").forward(request, response);
        } else {
            int productId = Integer.parseInt(request.getParameter("productId"));
            ShopDAO sd = new ShopDAO();
            ProductLineDAO pd = new ProductLineDAO();
            int accountId = user.getAccountId();
            List<Shop> listS = sd.getShop(accountId);
            Shop shop = listS.get(0);
            ProductLine listInfo = pd.getInfoProductById(productId, accountId);

            DiscountDAO dis = new DiscountDAO();
            boolean hasDiscount = dis.isProductOnDiscount(productId);
            if (hasDiscount) {
                request.setAttribute("mess1", "Sản Phẩm Đang Trong Thời Gian Giảm Giá");
            }

            List<Discount> listD = dis.getInfoDiscountById(productId);
            request.setAttribute("currentDate", formattedDate);
            request.setAttribute("listD", listD);
            request.setAttribute("shop", shop);
            request.setAttribute("listInfo", listInfo);
            request.setAttribute("productId", productId);
            request.getRequestDispatcher("discount.jsp").forward(request, response);
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account user = (Account) session.getAttribute("user");
        DiscountDAO dis = new DiscountDAO();
        int productId = Integer.parseInt(request.getParameter("productId"));
        double price = Double.parseDouble(request.getParameter("price"));
        int discountValue = Integer.parseInt(request.getParameter("discountValue"));
        Date startDate = Date.valueOf(request.getParameter("startDate"));
        Date endDate = Date.valueOf(request.getParameter("endDate"));

        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = currentDate.format(formatter);

        //    Date today = new Date(System.currentTimeMillis());
        if (endDate.before(startDate)) {

            ShopDAO sd = new ShopDAO();
            ProductLineDAO pd = new ProductLineDAO();
            int accountId = user.getAccountId();
            List<Shop> listS = sd.getShop(accountId);
            Shop shop = listS.get(0);
            ProductLine listInfo = pd.getInfoProductById(productId, accountId);

            List<Discount> listD = dis.getInfoDiscountById(productId);
            request.setAttribute("listD", listD);

            boolean hasDiscount = dis.isProductOnDiscount(productId);
            if (hasDiscount) {
                request.setAttribute("mess1", "Sản Phẩm Đang Trong Thời Gian Giảm Giá");
            }
            request.setAttribute("currentDate", formattedDate);
            request.setAttribute("shop", shop);
            request.setAttribute("listInfo", listInfo);
            request.setAttribute("productId", productId);
            // Nếu startDate trước ngày hôm nay, hiển thị lỗi
            request.setAttribute("mess", "Ngày Không Hợp Lệ");
            request.getRequestDispatcher("discount.jsp").forward(request, response);
            return;
        }
        if (discountValue >= 100 || discountValue <=0) {

            ShopDAO sd = new ShopDAO();
            ProductLineDAO pd = new ProductLineDAO();
            int accountId = user.getAccountId();
            List<Shop> listS = sd.getShop(accountId);
            Shop shop = listS.get(0);
            ProductLine listInfo = pd.getInfoProductById(productId, accountId);

            List<Discount> listD = dis.getInfoDiscountById(productId);
            request.setAttribute("listD", listD);
            request.setAttribute("currentDate", formattedDate);
            request.setAttribute("shop", shop);
            request.setAttribute("listInfo", listInfo);
            request.setAttribute("productId", productId);
            // Nếu startDate trước ngày hôm nay, hiển thị lỗi
            request.setAttribute("mess", "% giảm giá không hợp lệ");
            request.getRequestDispatcher("discount.jsp").forward(request, response);
            return;
        }

        if (dis.isProductOnDiscount(productId, startDate, endDate) == true) {

            ShopDAO sd = new ShopDAO();
            ProductLineDAO pd = new ProductLineDAO();
            int accountId = user.getAccountId();
            List<Shop> listS = sd.getShop(accountId);
            Shop shop = listS.get(0);
            ProductLine listInfo = pd.getInfoProductById(productId, accountId);

            List<Discount> listD = dis.getInfoDiscountById(productId);
            request.setAttribute("listD", listD);

            boolean hasDiscount = dis.isProductOnDiscount(productId);
            if (hasDiscount) {
                request.setAttribute("mess1", "Sản Phẩm Đang Trong Thời Gian Giảm Giá");
            }
            request.setAttribute("currentDate", formattedDate);
            request.setAttribute("shop", shop);
            request.setAttribute("listInfo", listInfo);
            request.setAttribute("productId", productId);
            // Nếu startDate trước ngày hôm nay, hiển thị lỗi
            request.setAttribute("mess", "Sản Phẩm đang trong quá trình giảm giá");
            request.getRequestDispatcher("discount.jsp").forward(request, response);
            return;
        }

        double promotionalPrice = price - (price * discountValue / 100);
        //add
        dis.addDiscount(productId, discountValue, startDate, endDate, promotionalPrice);
        //goilai page discount.jsp
        ShopDAO sd = new ShopDAO();
        ProductLineDAO pd = new ProductLineDAO();
        int accountId = user.getAccountId();
        List<Shop> listS = sd.getShop(accountId);
        Shop shop = listS.get(0);
        ProductLine listInfo = pd.getInfoProductById(productId, accountId);

        List<Discount> listD = dis.getInfoDiscountById(productId);
        request.setAttribute("listD", listD);

        boolean hasDiscount = dis.isProductOnDiscount(productId);
        if (hasDiscount) {
            request.setAttribute("mess1", "Sản Phẩm Đang Trong Thời Gian Giảm Giá");
        }
        request.setAttribute("currentDate", formattedDate);
        request.setAttribute("shop", shop);
        request.setAttribute("listInfo", listInfo);
        request.setAttribute("productId", productId);

        request.setAttribute("mess", "Đã lên lịch giảm giá sản phẩm");
        request.getRequestDispatcher("discount.jsp").forward(request, response);

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
