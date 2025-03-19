/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import model.Account;

/**
 *
 * @author admin
 */
@WebServlet(name = "EnterPinCodeServlet", urlPatterns = {"/pincode"})
public class EnterPinCodeServlet extends HttpServlet {

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
            out.println("<title>Servlet EnterPinCodeServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EnterPinCodeServlet at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("EnterPinCode.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static final long PIN_CODE_EXPIRATION_TIME = 90 * 1000;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String storedPinCode = (String) session.getAttribute("PinCode");
        String email = (String) session.getAttribute("email");
        String enteredPinCode = request.getParameter("pincode");
        Long timestamp = (Long) session.getAttribute("PinCodeTimestamp");

        try {
            if (timestamp == null || System.currentTimeMillis() - timestamp > PIN_CODE_EXPIRATION_TIME) {
                String message = "Mã code đã hết hạn. Vui lòng yêu cầu mã mới.";
                request.setAttribute("err", message);
                session.setAttribute("resetCountdown", true);
                request.getRequestDispatcher("EnterPinCode.jsp").forward(request, response);
            } else if (storedPinCode != null && storedPinCode.equals(enteredPinCode)) {
                session.removeAttribute("resetCountdown");
                request.getRequestDispatcher("ChangePassBySendEmail.jsp").forward(request, response);
            } else {
                String message = "Mã Code không đúng";
                request.setAttribute("err", message);
                session.setAttribute("resetCountdown", false);
                request.getRequestDispatcher("EnterPinCode.jsp").forward(request, response);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

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
