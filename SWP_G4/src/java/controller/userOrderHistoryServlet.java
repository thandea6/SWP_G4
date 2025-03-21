/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.OrderDetailDAO;
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
import model.OrderDetail;
import model.User;

/**
 *
 * @author VIET HOANG
 */
@WebServlet(name="userOrderHistoryServlet", urlPatterns={"/userOrderHistory"})
public class userOrderHistoryServlet extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
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
            out.println("<title>Servlet userOrderHistoryServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet userOrderHistoryServlet at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        UserDAO ud = new UserDAO();
        HttpSession session = request.getSession();
        Account user = (Account) session.getAttribute("user");
        String oid = request.getParameter("orderId");
        int orderId = Integer.parseInt(oid);
        OrderDetailDAO ordd = new OrderDetailDAO();
        if (user == null) {
            request.getRequestDispatcher("login").forward(request, response);
        } else {
            User u = ud.getUserByAccountId(user.getAccountId());
            List<OrderDetail> listOrder = ordd.listAllOrderDetailByUserId(u.getUserId(), orderId);
            System.out.println(listOrder.size());
            request.setAttribute("userProfile", u);
            request.setAttribute("oid", oid);
            request.setAttribute("list", listOrder);
            request.getRequestDispatcher("userOrderHistory.jsp").forward(request, response);
        }
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        UserDAO ud = new UserDAO();
        HttpSession session = request.getSession();
        Account user = (Account) session.getAttribute("user");
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        OrderDetailDAO ordd = new OrderDetailDAO();
        String key = request.getParameter("key").trim();
        if (user == null) {
            request.getRequestDispatcher("login").forward(request, response);
        } else {
            User u = ud.getUserByAccountId(user.getAccountId());
            List<OrderDetail> listOrder = ordd.listAllOrderDetailByOrderIdAndKey(orderId, key);
            request.setAttribute("oid", orderId);
            request.setAttribute("userProfile", u);
            request.setAttribute("list", listOrder);
            request.getRequestDispatcher("userOrderHistory.jsp").forward(request, response);
        }
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
