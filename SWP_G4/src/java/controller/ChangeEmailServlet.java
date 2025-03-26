/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import Mail.MailSender;
import static controller.ConfirmCodeServlet.generateRandomString;
import dal.AccountDAO;
import jakarta.mail.MessagingException;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.security.SecureRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;

/**
 *
 * @author VIET HOANG
 */
@WebServlet(name = "ChangeEmailServlet", urlPatterns = {"/changeEmail"})
public class ChangeEmailServlet extends HttpServlet {

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
            out.println("<title>Servlet SubmitEmailServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SubmitEmailServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    private static final String CHARACTERS = "0123456789";
    private static final SecureRandom RANDOM = new SecureRandom();

    // Method to generate a random string of a given length
    public static String generateRandomString(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = RANDOM.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(index));
        }
        return sb.toString();
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
        String email = request.getParameter("email");
        AccountDAO ad = new AccountDAO();
        Account newA = ad.getEmail(email);
        if(newA != null){
            request.setAttribute("err", "email is exist");
            request.getRequestDispatcher("ConfirmCode.jsp").forward(request, response);
        }
        String code = generateRandomString(6);
        HttpSession session = request.getSession();
        try {
            MailSender.sendEmail(email, "Your Code", "Your code is: " + code);
        } catch (MessagingException ex) {
            Logger.getLogger(ChangeEmailServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("err", "Failed to send email. Please try again later.");
            request.getRequestDispatcher("ConfirmCode.jsp").forward(request, response);
        } catch (Exception e) {
            Logger.getLogger(ChangeEmailServlet.class.getName()).log(Level.SEVERE, null, e);
            request.setAttribute("err", "An error occurred. Please try again later.");
            request.getRequestDispatcher("ConfirmCode.jsp").forward(request, response);
        }
        session.removeAttribute("verificationCompleted");
        session.setAttribute("code", code);
        session.setAttribute("email", email);
        request.getRequestDispatcher("ChangeEmail.jsp").forward(request, response);
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
        AccountDAO ad = new AccountDAO();
        HttpSession session = request.getSession();
        String email = (String)session.getAttribute("email");
        System.out.println(email);
        Account a = (Account) session.getAttribute("user");
        String code = request.getParameter("code");
        String verificationCode = request.getParameter("verificationCode");
        //  System.out.println(code+" " + verificationCode);
        if (code.equals(verificationCode)) {
            ad.updateEmail(a.getAccountId(), email);
            Account newA = ad.getAccountByAID(a.getAccountId());
            session.removeAttribute("code");
            session.removeAttribute("email");
            session.setAttribute("user", newA);
            if(a.getRoleId()==3){
                response.sendRedirect("updateShop");
            }else{
                response.sendRedirect("updateuser");
            }
            
        } else {
            request.setAttribute("err", "your code is not correct");
            request.getRequestDispatcher("ChangeEmail.jsp").forward(request, response);
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