<<<<<<< HEAD
=======
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
>>>>>>> 420fd3596f7a021da356974e42a221d3c07efdbe

package controller;

import dal.ShopDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Account;
import model.Shop;

<<<<<<< HEAD

@WebServlet(name="HomeShopServlet", urlPatterns={"/homeShop"})
public class HomeShopServlet extends HttpServlet {
   
   
=======
/**
 *
 * @author admin
 */
@WebServlet(name="HomeShopServlet", urlPatterns={"/homeShop"})
public class HomeShopServlet extends HttpServlet {
   
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
            out.println("<title>Servlet HomeShopServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet HomeShopServlet at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

>>>>>>> 420fd3596f7a021da356974e42a221d3c07efdbe
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
         HttpSession session = request.getSession();
        Account user = (Account) session.getAttribute("user");

        if (user == null) {
            request.getRequestDispatcher("logout").forward(request, response);
        } else {
            int accountId = user.getAccountId();

            ShopDAO sd = new ShopDAO();
            List<Shop> listS;
            listS = sd.getShop(accountId);
            Shop shop = listS.get(0);

            session.setAttribute("shop", shop);
<<<<<<< HEAD
            request.getRequestDispatcher("manager/shopHome.jsp").forward(request, response);
=======
            request.getRequestDispatcher("shopHome.jsp").forward(request, response);
>>>>>>> 420fd3596f7a021da356974e42a221d3c07efdbe
        }

    } 

<<<<<<< HEAD
   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
    }


=======
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
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
>>>>>>> 420fd3596f7a021da356974e42a221d3c07efdbe

}
