/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import Mail.MailSender;
import jakarta.mail.MessagingException;
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
import java.security.SecureRandom;
import model.Account;

/**
 *
 * @author admin
 */
@WebServlet(name="ResendPinServlet", urlPatterns={"/resendpin"})
public class ResendPinServlet extends HttpServlet {
   
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
            out.println("<title>Servlet ResendPinServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ResendPinServlet at " + request.getContextPath () + "</h1>");
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
    
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final SecureRandom RANDOM = new SecureRandom();

    public static String generateRandomString(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = RANDOM.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(index));
        }
        return sb.toString();
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

    }

    private String generateEmailContent(String pinCode) {
        return "<!DOCTYPE html>"
                + "<html lang='en'>"
                + "<head>"
                + "    <meta charset='UTF-8'>"
                + "    <meta name='viewport' content='width=device-width, initial-scale=1.0'>"
                + "    <title>Your Pin Code</title>"
                + "    <style>"
                + "        body { font-family: Arial, sans-serif; background-color: #f4f4f4; margin: 0; padding: 0; }"
                + "        .email-container { max-width: 600px; margin: 0 auto; background: #ffffff; border-radius: 10px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); overflow: hidden; }"
                + "        .email-header { background-color: #007bff; color: #ffffff; padding: 20px; text-align: center; font-size: 24px; }"
                + "        .email-body { padding: 20px; color: #333333; }"
                + "        .email-body h2 { font-size: 20px; margin-bottom: 10px; }"
                + "        .email-body p { font-size: 16px; line-height: 1.6; margin-bottom: 20px; }"
                + "        .email-footer { background-color: #f4f4f4; color: #888888; padding: 20px; text-align: center; font-size: 14px; }"
                + "        .pin-code { display: inline-block; padding: 10px 20px; background-color: #28a745; color: #ffffff; text-decoration: none; border-radius: 5px; font-size: 20px; }"
                + "    </style>"
                + "</head>"
                + "<body>"
                + "    <div class='email-container'>"
                + "        <div class='email-header'>"
                + "            Your Pin Code"
                + "        </div>"
                + "        <div class='email-body'>"
                + "            <h2>Hello,</h2>"
                + "            <p>We received information that you forgot your password. Here is your pin code:</p>"
                + "            <p class='pin-code'>" + pinCode + "</p>"
                + "            <p>Please enter this code on the website to reset your password.</p>"
                + "        </div>"
                + "        <div class='email-footer'>"
                + "            &copy; 2024 Shopee. All rights reserved."
                + "        </div>"
                + "    </div>"
                + "</body>"
                + "</html>";
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
