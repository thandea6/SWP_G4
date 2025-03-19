/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.ShopProductDAO;
import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Account;
import model.ShopProduct;
import model.User;

/**
 *
 * @author VIET HOANG
 */
@WebServlet(name = "SearchNameServlet", urlPatterns = {"/searchName"})
public class SearchNameServlet extends HttpServlet {

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
            out.println("<title>Servlet SearchNameServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SearchNameServlet at " + request.getContextPath() + "</h1>");
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
        UserDAO ud = new UserDAO();
        String key = request.getParameter("key").trim();
        ShopProductDAO spd = new ShopProductDAO();
        List<ShopProduct> list = null;
        int[] bIdList = {0};
        int[] cIdList = {0};
        HttpSession session = request.getSession();
        Account a = (Account) session.getAttribute("user");
        if (a == null) {
            list = spd.searchProduct(key, cIdList, bIdList, 0, 0);
            System.out.println(list.size());
            request.setAttribute("ListP", list);
            request.setAttribute("key", key);
            request.getRequestDispatcher("home").forward(request, response);
        } else {
            User u = ud.getUserByAccountId(a.getAccountId());
            list = spd.searchProductbyWishList(key, cIdList, bIdList, 0, 0, u.getUserId());
            System.out.println(list.size());
            request.setAttribute("ListP", list);
            request.setAttribute("key", key);
            session.setAttribute("user", a);
            request.getRequestDispatcher("home").forward(request, response);
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
        processRequest(request, response);
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
