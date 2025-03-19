/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.AccountDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;

/**
 *
 * @author admin
 */
@WebServlet(name = "ChangePassWordByEmailServlet", urlPatterns = {"/changepasswordbyemail"})
public class ChangePassWordByEmailServlet extends HttpServlet {

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
            out.println("<title>Servlet ChangePassWordByEmailServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ChangePassWordByEmailServlet at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("ChangePassBySendEmail.jsp").forward(request, response);
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

        HttpSession session = request.getSession(); // Pass false to not create a new session if none exists
        Account account = (Account) session.getAttribute("accgetemail");
        // Proceed with handling the account object

        String newPassword = request.getParameter("pass");
        String confirmPassword = request.getParameter("cfpass");
        
         

        AccountDAO accountDAO = new AccountDAO();

        String passwordPattern = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])?[A-Za-z\\d@$!%*?&]{8,}$";
        if (!newPassword.matches(passwordPattern)) {
            String errorMessage = "Mật khẩu phải chứa ít nhất một chữ in hoa, một số và dài ít nhất 8 ký tự, không có khoảng trắng.";
            request.setAttribute("err", errorMessage);
            request.getRequestDispatcher("ChangePassBySendEmail.jsp").forward(request, response);
            return;
        }

        if (!newPassword.equals(confirmPassword)) {
            String ms = "Nhập lại mật khẩu không đúng";
            request.setAttribute("err", ms);
            request.getRequestDispatcher("ChangePassBySendEmail.jsp").forward(request, response);
            return;
        }
        if (account == null) {
            String errorMessage = "Hết phiên thay mật khẩu";
            request.setAttribute("err", errorMessage);
            request.getRequestDispatcher("ChangePassBySendEmail.jsp").forward(request, response);
        } else if (!newPassword.equals(confirmPassword)) {
            String errorMessage = "Xác nhận mật khẩu không chính xác";
            request.setAttribute("err", errorMessage);
            request.getRequestDispatcher("ChangePassBySendEmail.jsp").forward(request, response);
        } else {
            String hashedPassword = Mahoa.toSHA1(confirmPassword);
            account.setPassword(hashedPassword);
            accountDAO.ChangePassByGetEmail(account);
            request.getRequestDispatcher("SignUpIn.jsp").forward(request, response);
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
