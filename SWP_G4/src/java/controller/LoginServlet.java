/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.AccountDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;

/**
 *
 * @author admin
 */
public class LoginServlet extends HttpServlet {
   
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
            out.println("<title>Servlet login</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet login at " + request.getContextPath () + "</h1>");
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
        request.getRequestDispatcher("SignUpIn.jsp").forward(request, response);
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
    // Lấy thông tin từ request
    String username = request.getParameter("user");
    String password = request.getParameter("password");
    String remember = request.getParameter("remember");

    // Mã hóa mật khẩu đầu vào
    String hashedPassword = Mahoa.toSHA1(password);

    // Xử lý cookies
    Cookie cn = new Cookie("cname", username);
    Cookie cp = new Cookie("cpass", password);
    Cookie cr = new Cookie("crem", remember);
    if (remember != null) {
        cn.setMaxAge(60 * 60 * 24);
        cp.setMaxAge(60 * 60 * 24);
        cr.setMaxAge(60 * 60 * 24);
    } else {
        cn.setMaxAge(0);
        cp.setMaxAge(0);
        cr.setMaxAge(0);
    }
    response.addCookie(cr);
    response.addCookie(cn);
    response.addCookie(cp);

    // Login
    AccountDAO ud = new AccountDAO();
    Account a = ud.Login(username, hashedPassword); // Sử dụng mật khẩu đã mã hóa để đăng nhập

    // Kiểm tra tài khoản
    if (a == null) {
        request.setAttribute("err", "Wrong username or password!");
        request.getRequestDispatcher("SignUpIn.jsp").forward(request, response);
    } else if (a.getRoleId() == 1) {  // Kiểm tra quyền tài khoản
        HttpSession session = request.getSession();
        session.setAttribute("user", a);
        response.sendRedirect("adminHome");
    } else if (a.getRoleId() == 3) {
        HttpSession session = request.getSession();
        session.setAttribute("user", a);
        response.sendRedirect("homeShop");
    } else {
        HttpSession session = request.getSession();
        session.setAttribute("user", a);
        response.sendRedirect("home");
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
