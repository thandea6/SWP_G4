/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.ProductItemDAO;
import dal.UserDAO;
import dal.VoucherDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Account;
import model.Cart;
import model.ProductItem;
import model.User;
import model.Voucher;

/**
 *
 * @author VIET HOANG
 */
@WebServlet(name = "UpdatePriceCartServlet", urlPatterns = {"/updatePriceCart"})
public class UpdatePriceCartServlet extends HttpServlet {

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
            out.println("<title>Servlet UpdatePriceCartServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdatePriceCartServlet at " + request.getContextPath() + "</h1>");
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
        String productId = request.getParameter("productId");
        String voucherCode = request.getParameter("code");
        ProductItemDAO d = new ProductItemDAO();
        HttpSession session = request.getSession();
        Account a = (Account) session.getAttribute("user");
        List<ProductItem> list = d.getAllProductItem();
        String cartData = "";
        Cookie[] cookies = request.getCookies();
        UserDAO ud = new UserDAO();
        if (voucherCode == null || voucherCode.length() == 0) {
            voucherCode = "0";
        }
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("cart1")) {
                    cartData += cookie.getValue();
                }
            }
        }
        Cart cart1 = new Cart(cartData, list);
        System.out.println("Before update: " + cartData + ", productId: " + productId + ", voucherCode: " + voucherCode + ", total: " + cart1.getTotalMoney());

        // Update cart1 based on productId and voucherCode
        if (productId != null && voucherCode != null) {
            String[] cartItems = cartData.split("/");
            StringBuilder updatedCart = new StringBuilder();

            for (String item : cartItems) {
                String[] itemDetails = item.split(":");
                if (itemDetails[0].equals(productId)) {
                    itemDetails[2] = voucherCode; // Update voucher code
                }
                updatedCart.append(String.join(":", itemDetails)).append("/");
            }

            // Remove the trailing slash
            if (updatedCart.length() > 0) {
                updatedCart.setLength(updatedCart.length() - 1);
            }

            cartData = updatedCart.toString();
        }

        // Update the cart cookie
        Cookie updatedCartCookie = new Cookie("cart1", cartData);
        updatedCartCookie.setMaxAge(60 * 60 * 24 * 2); // Set the expiration time as needed
        response.addCookie(updatedCartCookie);
        Cart cart = new Cart(cartData, list);
        System.out.println("After update: " + cartData + " ,total:" + cart.getTotalMoney());

        VoucherDAO vd = new VoucherDAO();
        User u = ud.getUserByAccountId(a.getAccountId());
        List<Voucher> listV = vd.getAllVoucherByUserId(u.getUserId());
        // Update total money in the cart
        request.setAttribute("test", "test");
        request.setAttribute("cart1", cart);
        request.setAttribute("name", u.getFullName());
        request.setAttribute("address", u.getAddress());
        request.setAttribute("phone", a.getPhone());
        request.setAttribute("listVoucher", listV);
        request.getRequestDispatcher("checkout.jsp").forward(request, response);
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
