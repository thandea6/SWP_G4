/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.CommentDAO;
import dal.RatingDAO;
import dal.UserDAO;
import dal.WishListDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Account;
import model.Category;
import model.Rating;
import model.Shop;
import model.ShopProduct;
import model.User;
import model.WishList;

/**
 *
 * @author GiaKhiem
 */
public class ViewShopServlet extends HttpServlet {
   
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
            out.println("<title>Servlet ViewShopServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ViewShopServlet at " + request.getContextPath () + "</h1>");
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
        HttpSession session = request.getSession();
          Account a = (Account) session.getAttribute("user");
        int shopp = Integer.parseInt(request.getParameter("sid"));
        List<ShopProduct> list = null;
        List<Category> list1 = null;
        dal.ShopDAO s = new dal.ShopDAO();
        WishListDAO wld = new WishListDAO();
        dal.CategoryDAO d = new dal.CategoryDAO();
        dal.ShopProductDAO sp = new dal.ShopProductDAO();
        CommentDAO cmt = new CommentDAO();
        UserDAO ud = new UserDAO();
        RatingDAO r=new RatingDAO();
        
        
        list1 = d.getCategoryAll();
        list = sp.getShopProductsByShop(shopp);
        Shop shop = s.getShopByShopShopId(shopp);
        Shop countproduct = s.getCountProductOfShopByShopId(shopp);
         Rating countRating = r.countRatingByShop(shopp);
         
          //wishlist
        List<WishList> listD = null;
        if (a == null) {
            if (list == null) {
                list = sp.getShopProductsAll();
            }
        } else {
            if (list == null) {
                User u = ud.getUserByAccountId(a.getAccountId());
                list = sp.getShopProductsAllbyWishList(u.getUserId());
                listD = wld.getAllWishList(u.getUserId());
            }
            User u = ud.getUserByAccountId(a.getAccountId());
            listD = wld.getAllWishList(u.getUserId());
        }
        //Phân Trang
        int page, numberpage = 8;
        int size = list.size();
        int num = (size % 8 == 0 ? (size / 8) : ((size / 8)) + 1);
        String xpage = request.getParameter("page");
        if (xpage == null) {
            page = 1;
        } else {
            page = Integer.parseInt(xpage);
        }
        int start, end;
        start = (page - 1) * numberpage;
        end = Math.min(page * numberpage, size);
        List<ShopProduct> listP = sp.getListByPage(list, start, end);
        if (a != null) {
            User u = ud.getUserByAccountId(a.getAccountId());
            request.setAttribute("customer", u);
        }
        request.setAttribute("listD", listD);
        request.setAttribute("countproduct", countproduct);
        request.setAttribute("countcmt", countRating);
        request.setAttribute("shop", shop);
        request.setAttribute("data", listP);
        request.setAttribute("data1", list1);
        

        request.getRequestDispatcher("viewShops.jsp").forward(request, response);
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
//Tu commit
}
