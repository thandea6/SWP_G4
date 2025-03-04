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
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import model.Account;
import model.User;

/**
 *
 * @author admin
 */
@WebServlet(name = "SignUpUserController", urlPatterns = {"/sign-up/user"})
public class SignUpUserController extends HttpServlet {

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
            out.println("<title>Servlet SignUpUserController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SignUpUserController at " + request.getContextPath() + "</h1>");
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
        processRequest(request, response);
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
        String btn = request.getParameter("btn-signup");
        if (btn != null) {
            String username = request.getParameter("user");
            AccountDAO accountDao = new AccountDAO();
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            Account isExist = accountDao.isExistAccount(username, email, phone);
            if (isExist != null) {
                request.setAttribute("message", "Username, email or phone is exist");
                request.getRequestDispatcher("../SignUpIn.jsp").forward(request, response);
                return;
            }
            String password = request.getParameter("password");
            String rePassword = request.getParameter("rePassword");
            if (password.equals(rePassword)) {
                Account a = new Account();
                a.setUsername(username);
                a.setPassword(Mahoa.toSHA1(password));
                a.setRoleId(2);
                a.setPhone(phone);
                a.setEmail(email);
                int result = accountDao.addAccount(a);
                if (result > 1) {
                    UserDAO userDao = new UserDAO();
                    User u = new User();
                    u.setAccountId(result);
                    int isAdd = userDao.addUser(u);
                    if (isAdd >= 1) {
                        response.sendRedirect("/SWP/SignUpIn.jsp");
                    } else {
                        request.setAttribute("message", "sign up fail");
                    }
                }
            } else {
                request.setAttribute("message", "Password confirm in correct");
                request.getRequestDispatcher("../SignUpIn.jsp").forward(request, response);
            }
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
