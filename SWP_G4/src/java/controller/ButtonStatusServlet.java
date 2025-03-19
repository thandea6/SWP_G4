/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.VoucherDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Voucher;

/**
 *
 * @author VIET HOANG
 */
@WebServlet(name = "ButtonStatusServlet", urlPatterns = {"/buttonstatus"})
public class ButtonStatusServlet extends HttpServlet {

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
            out.println("<title>Servlet ButtonStatusServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ButtonStatusServlet at " + request.getContextPath() + "</h1>");
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

        Cookie[] cookies = request.getCookies();
        String cart1 = null;
        for (Cookie cookie : cookies) {
            if ("cart1".equals(cookie.getName())) {
                cart1 = cookie.getValue();
                break;
            }
        }
        VoucherDAO vd = new VoucherDAO();
        String discountCode = "";
        double discountAmount = 0;
        boolean showButtons = false;
        if (cart1 != null) {
            String[] parts = cart1.split("/");
            for (String part : parts) {
                String[] itemDetails = part.split(":");
                if (itemDetails[0].equals(productId)) {
                    if ("0".equals(itemDetails[2])) {
                        showButtons = true;
                    } else {
                        Voucher v = vd.getVoucherByCode(itemDetails[2]);
                        discountAmount = v.getReducedAmount();
                        discountCode = v.getCode();
                    }
                    break;
                }
            }
        } 
        System.out.println("test");
        System.out.println(discountAmount + "  " + discountCode);
        response.setContentType("application/json");
        response.getWriter().write("{\"showButtons\": " + showButtons + ", \"discountCode\": \"" + discountCode + "\", \"discountAmount\": \"" + discountAmount + "\"}");
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
