/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.ShopDAO;
import dal.UserDAO;
import dal.VoucherDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Date;
import java.util.List;
import model.Account;
import model.Shop;
import model.User;
import model.Voucher;

/**
 *
 * @author VIET HOANG
 */
@WebServlet(name="UpdateVoucherServlet", urlPatterns={"/updateVoucher"})
public class UpdateVoucherServlet extends HttpServlet {
   
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
            out.println("<title>Servlet UpdateVoucherServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateVoucherServlet at " + request.getContextPath () + "</h1>");
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
        String code = request.getParameter("code");
        VoucherDAO vd = new VoucherDAO();
        ShopDAO sd = new ShopDAO();
        UserDAO ud = new UserDAO();
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("user");
        Shop s = sd.getShopByAccountId(account.getAccountId());
        String description = request.getParameter("description").trim();
        String reduced_raw = request.getParameter("reducedAmount");
        String date_raw = request.getParameter("endDate");
        double reducedAmount;
        Date endDate;
        if (description.length() == 0) {
            request.setAttribute("err", "Mô tả không được để trắng!");
        } else {
            try {

                reducedAmount = Double.parseDouble(reduced_raw);
                endDate = Date.valueOf(date_raw);
                vd.updateVoucher(code, reducedAmount, endDate, description);
                request.setAttribute("done", "Cập nhật dữ liệu thành công!");
            } catch (NumberFormatException e) {
                System.out.println(e);
            }

        }
        List<User> listU = ud.getAllUserAndUsername(s.getShopId());
        Voucher v = vd.getVoucherByCodeShop(code);
        request.setAttribute("shop", s);
        request.setAttribute("listU", listU);
        request.setAttribute("voucher", v);
        request.getRequestDispatcher("VoucherDetailShop.jsp").forward(request, response);
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
