/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.AccountDAO;
import dal.UserDAO;
import jakarta.mail.Session;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Date;
import model.Account;
import model.User;

/**
 *
 * @author VIET HOANG
 */
@WebServlet(name = "UpdateProfileUserServlet", urlPatterns = {"/updateuser"})
public class UpdateProfileUserServlet extends HttpServlet {

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
            out.println("<title>Servlet UpdateProfileUserServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateProfileUserServlet at " + request.getContextPath() + "</h1>");
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
        UserDAO ud = new UserDAO();
        HttpSession session = request.getSession();
        Account user = (Account) session.getAttribute("user");
        if (user == null) {
            request.getRequestDispatcher("login").forward(request, response);
        } else {
            User u = ud.getUserByAccountId(user.getAccountId());
            request.setAttribute("userProfile", u);
            request.getRequestDispatcher("userProfile.jsp").forward(request, response);
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
        UserDAO ud = new UserDAO();
        AccountDAO ad = new AccountDAO();
        Account user = (Account) session.getAttribute("user");
        User oldU = ud.getUserByAccountId(user.getAccountId());
        int accountId = user.getAccountId();
        System.out.println(accountId);
        String fullName = request.getParameter("fullName").trim();
        String address = request.getParameter("address").trim();
        String image = request.getParameter("image");
        String dob_raw = request.getParameter("date");
        String gender_raw = request.getParameter("gender");
        boolean gender;
        String phone = request.getParameter("phone");

        if (gender_raw.equals("male")) {
            gender = true;
        } else {
            gender = false;
        }
        Date dob = Date.valueOf(dob_raw);
        if((fullName==null||fullName.length()==0) || (address==null||address.length()==0)){
            User u = ud.getUserByAccountId(user.getAccountId());
            request.setAttribute("userProfile", u); 
            request.setAttribute("err1", "tên hoặc địa chỉ không được để trống");
            request.getRequestDispatcher("userProfile.jsp").forward(request, response);
        }
        if (!validatePhone(phone)) {
            User u = ud.getUserByAccountId(user.getAccountId());
            request.setAttribute("userProfile", u); 
            request.setAttribute("err", "Số điện thoại không hợp lệ");
            request.getRequestDispatcher("userProfile.jsp").forward(request, response);
        }else{
            if (image == null || image.isEmpty()) {
            ud.update(accountId, fullName, address, oldU.getImage(), dob, gender);
        } else {
            ud.update(accountId, fullName, address, "images/" + image, dob, gender);
        }
        ad.updatePhone(accountId, phone);
        Account newUser = ad.getAccountByAID(user.getAccountId());
        session.setAttribute("user", newUser);
        request.setAttribute("mess", "cập nhật thông tin thành công");
        User u = ud.getUserByAccountId(user.getAccountId());
        request.setAttribute("userProfile", u);
        request.getRequestDispatcher("userProfile.jsp").forward(request, response);
        request.getRequestDispatcher("cart.jsp").forward(request, response);
        }
        
    }

    private boolean validatePhone(String phone) {
        if (phone.length() != 10) {
            return false;
        }
        if (!phone.startsWith("0")) {
            return false;
        }
        return true;
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
