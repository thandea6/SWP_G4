/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.AccountDAO;
import dal.ShopDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Account;
import model.Shop;

/**
 *
 * @author admin
 */
@WebServlet(name = "SignUpShopController", urlPatterns = {"/sign-up/shop"})
public class SignUpShopController extends HttpServlet {

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
            out.println("<title>Servlet SignUpShopController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SignUpShopController at " + request.getContextPath() + "</h1>");
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
        String status = request.getParameter("status");
        if (status != null) {
            request.setAttribute("message", "Sign up for shop success");
        }
        request.getRequestDispatcher("../SignUpIn.jsp").forward(request, response);
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
        String btnSingUp = request.getParameter("btn-signup");
        if (btnSingUp != null) {
            AccountDAO accountDao = new AccountDAO();
            String username = request.getParameter("user");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            Account isExist = accountDao.isExistAccount(username, email, phone);
            if (isExist != null) {
                request.setAttribute("message", "Username, email or phone is exist");
                request.getRequestDispatcher("../SignUpIn.jsp").forward(request, response);
                return;
            }
            String password = request.getParameter("password");
            String nameShop = request.getParameter("nameShop");
            String rePassword = request.getParameter("rePassword");
            if (password.equals(rePassword)) {
                Account a = new Account();
                a.setUsername(username);
                a.setPassword(Mahoa.toSHA1(password));
                a.setRoleId(3);
                a.setPhone(phone);
                a.setEmail(email);
                int result = accountDao.add(a);
                if (result >= 1) {
                    ShopDAO shopDao = new ShopDAO();
                    Shop s = new Shop();
                    s.setShopName(nameShop);
                    s.setAccountId(result);
                    int addShop = shopDao.addShop(s);
                    if (addShop >= 1) {
                        response.sendRedirect("/SWP/sign-up/shop?status=1");
                    } else {
                        request.setAttribute("message", "Sign up account fail");
                    }
                } else {
                    request.setAttribute("message", "Sign up account fail");
                }
            } else {
                request.setAttribute("message", "Password confirm is incorrect");
                request.getRequestDispatcher("../SignUpIn.jsp").forward(request, response);
            }
        }
    }
    // bạn test thử xem

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
