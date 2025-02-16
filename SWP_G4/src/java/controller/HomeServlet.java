/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.BrandDAO;
import dal.UserDAO;
import dal.WishListDAO;
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
import model.Brand;
import model.Category;
import model.ShopProduct;
import model.User;
import model.WishList;

/**
 *
 * @author GiaKhiem
 */
@WebServlet(name="HomeServlet", urlPatterns={"/home"})
public class HomeServlet extends HttpServlet {
   
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
            out.println("<title>Servlet HomeServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet HomeServlet at " + request.getContextPath () + "</h1>");
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
        String key = (String) request.getAttribute("key");
        Account a = (Account) session.getAttribute("user");
        UserDAO ud = new UserDAO();
        BrandDAO bd = new BrandDAO();
        WishListDAO wld = new WishListDAO();
        List<ShopProduct> listP = (List<ShopProduct>) request.getAttribute("ListP");
        String max_raw = request.getParameter("maxValue");
        String min_raw = request.getParameter("minValue");
        double max = 0;
        double min = 0;
        if (min_raw != null) {
            min = Double.parseDouble(min_raw);
        }
        if (max_raw != null) {
            max = Double.parseDouble(max_raw);
        }
        List<Category> listC = null;
        dal.CategoryDAO d = new dal.CategoryDAO();
        dal.ShopProductDAO sp = new dal.ShopProductDAO();
        //get all category
        listC = d.getCategoryAll();
        //get all brand
        List<Brand> listB = bd.getBrandAll();
        //create boolean brand to save value checkbox brand
        boolean[] bb = new boolean[listB.size() + 1];
        //set value[0]
        bb[0] = true;
        //create boolean category to save value checkbox category
        boolean[] cb = new boolean[listC.size() + 1];
        //set value[0]
        cb[0] = true;
        //get all shop product
        List<WishList> listD = null;
        if (a == null) {
            if (listP == null) {
                listP = sp.getShopProductsAll();
            }
        } else {
            if (listP == null) {
                User u = ud.getUserByAccountId(a.getAccountId());
                listP = sp.getShopProductsAllbyWishList(u.getUserId());
                listD = wld.getAllWishList(u.getUserId());
            }
            User u = ud.getUserByAccountId(a.getAccountId());
            listD = wld.getAllWishList(u.getUserId());
        }

        //Phân Trang
        int page, numberpage = 8;
        int size = listP.size();
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
        List<ShopProduct> list = sp.getListByPage(listP, start, end);

        request.setAttribute("data", list);
        request.setAttribute("num", num);
        request.setAttribute("max", max);
        request.setAttribute("key", key);
        request.setAttribute("min", min);
        request.setAttribute("bb", bb);
        request.setAttribute("cb", cb);
        request.setAttribute("cid", 0);
        request.setAttribute("bid", 0);
        request.setAttribute("data1", listC);
        request.setAttribute("listB", listB);
        request.setAttribute("listD", listD);
        if (a != null) {
            User u = ud.getUserByAccountId(a.getAccountId());
            request.setAttribute("customer", u);
        }
        request.getRequestDispatcher("Home.jsp").forward(request, response);
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
        HttpSession session = request.getSession();
        String key = (String) request.getAttribute("key");
        Account a = (Account) session.getAttribute("user");
        UserDAO ud = new UserDAO();
        BrandDAO bd = new BrandDAO();
        WishListDAO wld = new WishListDAO();
        List<ShopProduct> listP = (List<ShopProduct>) request.getAttribute("ListP");
        String max_raw = request.getParameter("maxValue");
        String min_raw = request.getParameter("minValue");
        double max = 0;
        double min = 0;
        if (min_raw != null) {
            min = Double.parseDouble(min_raw);
        }
        if (max_raw != null) {
            max = Double.parseDouble(max_raw);
        }
        List<Category> listC = null;
        dal.CategoryDAO d = new dal.CategoryDAO();
        dal.ShopProductDAO sp = new dal.ShopProductDAO();
        //get all category
        listC = d.getCategoryAll();
        //get all brand
        List<Brand> listB = bd.getBrandAll();
        //create boolean brand to save value checkbox brand
        boolean[] bb = new boolean[listB.size() + 1];
        //set value[0]
        bb[0] = true;
        //create boolean category to save value checkbox category
        boolean[] cb = new boolean[listC.size() + 1];
        //set value[0]
        cb[0] = true;
        //get all shop product
        List<WishList> listD = null;
        if (a == null) {
            if (listP == null) {
                listP = sp.getShopProductsAll();
            }

        } else {
            if (listP == null) {
                User u = ud.getUserByAccountId(a.getAccountId());
                listP = sp.getShopProductsAllbyWishList(u.getUserId());
                listD = wld.getAllWishList(u.getUserId());
            }
            User u = ud.getUserByAccountId(a.getAccountId());
            listD = wld.getAllWishList(u.getUserId());
        }
        int page, numberpage = 8;
        int size = listP.size();
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
        List<ShopProduct> list = sp.getListByPage(listP, start, end);

        request.setAttribute("data", list);
        request.setAttribute("num", num);
        request.setAttribute("max", max);
        request.setAttribute("key", key);
        request.setAttribute("min", min);
        request.setAttribute("bb", bb);
        request.setAttribute("cb", cb);
        request.setAttribute("cid", 0);
        request.setAttribute("bid", 0);
        request.setAttribute("data1", listC);
        request.setAttribute("listB", listB);
        request.setAttribute("listD", listD);
        if (a != null) {
            User u = ud.getUserByAccountId(a.getAccountId());
            request.setAttribute("customer", u);
        }
        request.getRequestDispatcher("Home.jsp").forward(request, response);
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
