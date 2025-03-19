/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.AccountDAO;
import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
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
public class ChangePassServlet extends HttpServlet {

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
            out.println("<title>Servlet ChangePassServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ChangePassServlet at " + request.getContextPath() + "</h1>");
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
        UserDAO ud = new UserDAO();
        User u = ud.getUserByAccountId(user.getAccountId());
        if (user == null) {
            request.getRequestDispatcher("logout").forward(request, response);
        }
        if (user.getRoleId() == 2) {
            request.setAttribute("customer", u);
            request.getRequestDispatcher("ChangePassword.jsp").forward(request, response);

        }
        if (user.getRoleId() == 3) {
            request.getRequestDispatcher("ChangePassShop.jsp").forward(request, response);
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
        HttpSession session = request.getSession();
        Account user = (Account) session.getAttribute("user");

        String username = request.getParameter("user");
        String oldpassword = request.getParameter("oldpass");
        String password = request.getParameter("pass");
        String cfpassword = request.getParameter("cfpass");
        AccountDAO ud = new AccountDAO();
        Account a = (Account) request.getSession().getAttribute("user");
        String hashedOldpassword = Mahoa.toSHA1(oldpassword);

        // Check if the new password is the same as the old password
        if (password.equals(hashedOldpassword)) {
            String ms = "Mật khẩu mới không thể giống với mật khẩu cũ.";
            request.setAttribute("err", ms);
            if (user.getRoleId() == 2) {
                request.getRequestDispatcher("ChangePassword.jsp").forward(request, response);
            }
            if (user.getRoleId() == 3) {
                request.getRequestDispatcher("ChangePassShop.jsp").forward(request, response);
            }
            return;
        }

        // Password validation
        String passwordPattern = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])?[A-Za-z\\d@$!%*?&]{8,}$";
        if (!password.matches(passwordPattern)) {
            String ms = "Mật khẩu phải chứa ít nhất một chữ in hoa, một số và dài ít nhất 8 ký tự, không có khoảng trắng.";
            request.setAttribute("err", ms);
            if (user.getRoleId() == 2) {
                request.getRequestDispatcher("ChangePassword.jsp").forward(request, response);
            }
            if (user.getRoleId() == 3) {
                request.getRequestDispatcher("ChangePassShop.jsp").forward(request, response);
            }
            return;
        }

        if (a == null || !hashedOldpassword.equals(a.getPassword())) {
            String ms = "Mật khẩu cũ không đúng";
            request.setAttribute("err", ms);
            if (user.getRoleId() == 2) {
                request.getRequestDispatcher("ChangePassword.jsp").forward(request, response);
            }
            if (user.getRoleId() == 3) {
                request.getRequestDispatcher("ChangePassShop.jsp").forward(request, response);
            }
        } else if (!password.equals(cfpassword)) {
            String ms = "Xác nhận mật khẩu không chính xác";
            request.setAttribute("err", ms);
            if (user.getRoleId() == 2) {
                request.getRequestDispatcher("ChangePassword.jsp").forward(request, response);
            }
            if (user.getRoleId() == 3) {
                request.getRequestDispatcher("ChangePassShop.jsp").forward(request, response);
            }
        } else {
            String hashedPassword = Mahoa.toSHA1(password);
            Account ac = new Account(a.getAccountId(), username, hashedPassword, a.getRoleId(), a.getEmail(), a.getPhone());
            ud.changePassword(ac);
            session.setAttribute("user", ac);
            response.sendRedirect("logout");
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
