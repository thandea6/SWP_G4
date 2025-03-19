/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.ChatBoxDAO;
import dal.MessageDAO;
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
import model.Account;
import model.ChatBox;
import model.User;

/**
 *
 * @author admin
 */
@WebServlet(name="DeleteChatBoxServlet", urlPatterns={"/deletechatbox"})
public class DeleteChatBoxServlet extends HttpServlet {
   
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
            out.println("<title>Servlet DeleteChatBoxServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DeleteChatBoxServlet at " + request.getContextPath () + "</h1>");
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
        ChatBoxDAO cb = new ChatBoxDAO();
        MessageDAO m = new MessageDAO();
        if (user == null) {
            request.getRequestDispatcher("logout").forward(request, response);
        } else {
            
            int chatId = Integer.parseInt(request.getParameter("chatId"));
            m.deleteMessageBychatId(chatId);
            cb.deleteChatBoxBychatId(chatId);
            User u = ud.getUserByAccountId(user.getAccountId());

            request.setAttribute("userProfile", u);
            response.sendRedirect("userchatwithshop");
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
        HttpSession session = request.getSession();
        Account user = (Account) session.getAttribute("user");
        ChatBoxDAO cb = new ChatBoxDAO();
        MessageDAO m = new MessageDAO();
        if (user == null) {
            request.getRequestDispatcher("logout").forward(request, response);
        } else {
            
            int chatId = Integer.parseInt(request.getParameter("chatId"));
            m.deleteMessageBychatId(chatId);
            cb.deleteChatBoxBychatId(chatId);
            response.sendRedirect("shopchatwithuser");
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
