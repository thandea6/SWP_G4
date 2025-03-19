/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.CommentDAO;
import dal.RatingDAO;
import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;
import model.User;

/**
 *
 * @author admin
 */
@WebServlet(name = "CommentControlServlet", urlPatterns = {"/comment"})
public class CommentControlServlet extends HttpServlet {

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
            out.println("<title>Servlet CommentControlServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CommentControlServlet at " + request.getContextPath() + "</h1>");
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
            request.getRequestDispatcher("login").forward(request, response);
        } else {
        String comment = request.getParameter("comment");
        String ratingParam = request.getParameter("rating");
        int accountId = user.getAccountId();
        int shopProductId = Integer.parseInt(request.getParameter("pId"));
        int odDetailId = Integer.parseInt(request.getParameter("odDetailId"));
        int shopId = Integer.parseInt(request.getParameter("sId"));
        System.out.println(shopProductId);
        int odId = Integer.parseInt(request.getParameter("odId"));
        System.out.println(odId);

// Check if rating is null or not a valid integer
        if (ratingParam == null || ratingParam.isEmpty()) {
            request.setAttribute("errorMessage", "Rating cannot be null. Please provide a rating.");
            request.getRequestDispatcher("userOrderHistory?orderId=" +odId ).forward(request, response); // Replace "feedbackPage" with your actual feedback page
            return; // Exit the method to prevent further processing
        }

        try {
            int rating = Integer.parseInt(ratingParam);
            UserDAO u = new UserDAO();
            CommentDAO cmt = new CommentDAO();
            RatingDAO r = new RatingDAO();
            User us = u.getUserByAccountId(accountId);
            cmt.addCommentOfUser(us.getUserId(), rating, accountId, shopProductId, comment, odDetailId);
            request.getRequestDispatcher("detail?pid=" + shopProductId + "&sid=" +shopId).forward(request, response);
        } catch (NumberFormatException e) {
            // Handle the case where rating is not a valid integer
            request.setAttribute("errorMessage", "Invalid rating value. Please provide a valid rating.");
            request.getRequestDispatcher("userOrderHistory?orderId=" +odId).forward(request, response); // Replace "feedbackPage" with your actual feedback page
        }
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
