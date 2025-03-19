/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.ShopDAO;
import dal.VoucherDAO;
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
import model.Shop;
import model.Voucher;

/**
 *
 * @author VIET HOANG
 */
@WebServlet(name="AddVoucherUser", urlPatterns={"/addVoucherUser"})
public class AddVoucherUser extends HttpServlet {
   
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
            out.println("<title>Servlet AddVoucherUser</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddVoucherUser at " + request.getContextPath () + "</h1>");
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
        processRequest(request, response);
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
         ShopDAO sd = new ShopDAO();
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("user");
        Shop s = sd.getShopByAccountId(account.getAccountId());
        VoucherDAO vd = new VoucherDAO();
        List<Voucher> listV = vd.getAllVoucherShop(s.getShopId());
        String[] userId_raw = request.getParameterValues("userId");
        String[] quantity_raw = request.getParameterValues("quantity");
        String code = request.getParameter("code");
        System.out.println("test here");
        for(int i = 0; i < userId_raw.length; i++){
            if(Integer.parseInt(userId_raw[i]) == 0){
                System.out.println("out");
                continue;
            }
            int userId = Integer.parseInt(userId_raw[i]);
            int quantity = Integer.parseInt(quantity_raw[i]);
            vd.createVoucherForUser(code, userId, quantity);
        }
        request.setAttribute("giveDone", "Bạn đã tặng voucher cho user thành công!");
        request.setAttribute("shop", s);
        request.setAttribute("listVoucher", listV);
        request.getRequestDispatcher("ShopVoucher.jsp").forward(request, response);
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
