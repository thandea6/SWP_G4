/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.ShopProductDAO;
import dal.UserDAO;
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
import model.ShopProduct;
import model.User;

/**
 *
 * @author GiaKhiem
 */
@WebServlet(name="SortServlet", urlPatterns={"/sort"})
public class SortServlet extends HttpServlet {
   
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
            out.println("<title>Servlet SortServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SortServlet at " + request.getContextPath () + "</h1>");
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
        UserDAO ud = new UserDAO();
        String key = request.getParameter("key");
        String[] cateID_raw = request.getParameterValues("cid");
        String[] BrandID_raw = request.getParameterValues("bid");
        String min_raw = request.getParameter("minValue");
        String max_raw = request.getParameter("maxValue");
        String sort = request.getParameter("sort");
        double minValue = 0, maxValue = 0;
        try {
            if (min_raw != null) {
                minValue = Double.parseDouble(min_raw);
            }
            if (max_raw != null) {
                maxValue = Double.parseDouble(max_raw);
            }

        } catch (NumberFormatException e) {

        }
        ShopProductDAO spd = new ShopProductDAO();
        List<ShopProduct> list = null;
        int[] bIdList = null;
        int[] cIdList = null;
        if (cateID_raw != null) {
            cIdList = new int[cateID_raw.length];
            for (int i = 0; i < cIdList.length; i++) {
                cIdList[i] = Integer.parseInt(cateID_raw[i]);
                System.out.print("a" + cIdList[i]);
            }
        }
        if (BrandID_raw != null) {
            bIdList = new int[BrandID_raw.length];
            for (int i = 0; i < bIdList.length; i++) {
                bIdList[i] = Integer.parseInt(BrandID_raw[i]);
                System.out.print("b" + bIdList[i]);
            }
        }
        HttpSession session = request.getSession();
        Account a = (Account) session.getAttribute("user");
        if (a == null) {
            list = spd.searchProductAndSort(key, cIdList, bIdList, minValue, maxValue, sort);
            System.out.println(list.size());
            for (int i = 0; i < list.size(); i++) {
                System.out.println(list.get(i));
                System.out.println(i);
            }
            request.setAttribute("key", key);
            request.setAttribute("max", maxValue);
            request.setAttribute("min", minValue);
            request.setAttribute("ListP", list);
            request.getRequestDispatcher("home").forward(request, response);
        } else {
            User u = ud.getUserByAccountId(a.getAccountId());
            list = spd.searchProductAndSortbyWishList(key, cIdList, bIdList, minValue, maxValue, sort,u.getUserId());
            System.out.println(list.size());
            for (int i = 0; i < list.size(); i++) {
                System.out.println(list.get(i));
                System.out.println(i);
            }
            request.setAttribute("key", key);
            request.setAttribute("max", maxValue);
            request.setAttribute("min", minValue);
            request.setAttribute("ListP", list);
            request.getRequestDispatcher("home").forward(request, response);
        }
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

}
