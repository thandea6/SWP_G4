/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.ChatBoxDAO;
import dal.MessageDAO;
import dal.ShopDAO;
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
import model.ChatBox;
import model.Message;
import model.Shop;
import model.User;

/**
 *
 * @author admin
 */
@WebServlet(name="ShopChatWithUserServlet", urlPatterns={"/shopchatwithuser"})
public class ShopChatWithUserServlet extends HttpServlet {
   
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
            out.println("<title>Servlet ShopChatWithUserServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ShopChatWithUserServlet at " + request.getContextPath () + "</h1>");
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
        HttpSession session = request.getSession();
        Account user = (Account) session.getAttribute("user");
        ChatBoxDAO cb = new ChatBoxDAO();
        MessageDAO m = new MessageDAO();
        ShopDAO s = new ShopDAO();
        UserDAO u = new UserDAO();
          String cId = request.getParameter("chatId");
            String uId =request.getParameter("uid");
        if (user == null) {
            request.getRequestDispatcher("logout").forward(request, response);
        } else {
           if (uId == null) {
            List<ChatBox> listChat = cb.getAllChatOfUserAndShop(user.getAccountId(), user.getAccountId());

                request.setAttribute("listChat", listChat);
                request.getRequestDispatcher("shopChatWithUser.jsp").forward(request, response);
        } else {
                
            int userId = Integer.parseInt(uId);
            
            List<ChatBox> listChat = cb.getAllChatOfUserAndShop(user.getAccountId(), user.getAccountId());
            List<Message> listm = m.getListMessageByUserIdAndAccountId(userId, user.getAccountId());
            User getUser = u.getUserByUserId(userId);

            
            
            request.setAttribute("getUser", getUser);
            request.setAttribute("listMessage", listm);
            request.setAttribute("listChat", listChat);
            request.getRequestDispatcher("shopChatWithUser.jsp").forward(request, response);
           }
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
        ShopDAO s = new ShopDAO();
        UserDAO u = new UserDAO();
        if (user == null) {
            request.getRequestDispatcher("logout").forward(request, response);
        } else {
            //int sid = Integer.parseInt(request.getParameter("sid"));
            int chatId = Integer.parseInt(request.getParameter("chatId"));
            int userId = Integer.parseInt(request.getParameter("userId"));

            List<ChatBox> listChat = cb.getAllChatOfUserAndShop(user.getAccountId(), user.getAccountId());
            List<Message> listm = m.getAllMessageByChatId(chatId);
            User getUser = u.getUserByUserId(userId);

            
            
            request.setAttribute("getUser", getUser);
            request.setAttribute("listMessage", listm);
            request.setAttribute("listChat", listChat);
            request.getRequestDispatcher("shopChatWithUser.jsp").forward(request, response);

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
