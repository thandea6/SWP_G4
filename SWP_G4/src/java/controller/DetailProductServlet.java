/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.BrandDAO;
import dal.CommentDAO;
import dal.DiscountDAO;
import dal.OrderDetailDAO;
import dal.RatingDAO;
import dal.ShopProductDAO;
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
import java.awt.Image;
import java.util.HashSet;
import java.util.List;
import model.Account;
import model.Brand;
import model.Category;
import model.Color;
import model.Comment;
import model.Discount;
import model.Images;
import model.Order;
import model.OrderDetail;
import model.ProductItem;
import model.Rating;
import model.Shop;
import model.ShopProduct;
import model.Size;
import model.User;
import model.WishList;

/**
 *
 * @author admin
 */
@WebServlet(name = "DetailProductServlet", urlPatterns = {"/detail"})
public class DetailProductServlet extends HttpServlet {

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
            out.println("<title>Servlet DetailProductServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DetailProductServlet at " + request.getContextPath() + "</h1>");
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
        List<ShopProduct> list = null;
        List<Category> list1 = null;
        List<Color> list2 = null;
        List<Size> list3 = null;
        List<Images> list4 = null;
        List<Rating> listr = null;
        int shopp = Integer.parseInt(request.getParameter("sid"));
        int id = Integer.parseInt(request.getParameter("pid"));
        ShopProductDAO spd = new ShopProductDAO();
        ShopProduct sp = spd.getShopProductById(id);
        Discount dis = new Discount();
        DiscountDAO disD = new DiscountDAO();
        dis = disD.getDisInfoDiscountById(id);
        dal.CategoryDAO d = new dal.CategoryDAO();
        dal.ProductItemDAO pl = new dal.ProductItemDAO();
        dal.ShopDAO s = new dal.ShopDAO();
        dal.ImagesDAO i = new dal.ImagesDAO();
        BrandDAO bd = new BrandDAO();
        CommentDAO cmt = new CommentDAO();
        RatingDAO r = new RatingDAO();
        WishListDAO wld = new WishListDAO();
        list = spd.getRandomProductOfShop(shopp);
        list1 = d.getCategoryAll();
        list2 = pl.getAllProductColor(id);
        list3 = pl.getAllProductSize(id);
        list4 = i.getAllImagesByShopProductId(id);
        List<Brand> listB = bd.getBrandAll();
        listr = r.getAllRatingByShopProductId(id);
        Rating rating = r.getAvgRatingByShopProductId(id);
        Shop countproduct = s.getCountProductOfShopByShopId(shopp);
        ProductItem sumquantity = pl.getSumQuantityByShopProductId(id);
        ProductItem pr = pl.getProductByID(id);
        Shop shop = s.getShopByShopProductId(id);
        Rating countRating = r.countRatingByShop(shopp);
        Account a = (Account) session.getAttribute("user");
        if (a == null) {
            request.setAttribute("data", list);
            request.setAttribute("countproduct", countproduct);
            request.setAttribute("listB", listB);
            request.setAttribute("sumquantity", sumquantity);
            request.setAttribute("shopProduct", sp);
            request.setAttribute("shop", shop);
            request.setAttribute("detail", pr);
            request.setAttribute("data1", list1);
            request.setAttribute("color", list2);
            request.setAttribute("size", list3);
            request.setAttribute("images", list4);
            request.setAttribute("comment", listr);
            request.setAttribute("rating", rating);
            request.setAttribute("dis", dis);
            request.setAttribute("countcmt", countRating);
            request.getRequestDispatcher("DetailProduct.jsp").forward(request, response);
        } else {
            UserDAO ud = new UserDAO();
            User u = ud.getUserByAccountId(a.getAccountId());
            WishList w1 = wld.checkWishList(id, u.getUserId());
            request.setAttribute("data", list);
            request.setAttribute("countproduct", countproduct);
            request.setAttribute("listB", listB);
            request.setAttribute("sumquantity", sumquantity);
            request.setAttribute("shopProduct", sp);
            request.setAttribute("shop", shop);
            request.setAttribute("detail", pr);
            request.setAttribute("data1", list1);
            request.setAttribute("color", list2);
            request.setAttribute("size", list3);
            request.setAttribute("images", list4);
            request.setAttribute("comment", listr);
            request.setAttribute("rating", rating);
            request.setAttribute("countcmt", countRating);
            request.setAttribute("customer", u);
            request.setAttribute("dis", dis);
            request.setAttribute("wishlist", w1);
            request.getRequestDispatcher("DetailProduct.jsp").forward(request, response);
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
        List<ShopProduct> list = null;
        List<Category> list1 = null;
        List<Color> list2 = null;
        List<Size> list3 = null;
        List<Images> list4 = null;
        List<Comment> listc = null;
        List<Rating> listr = null;
        int shopp = Integer.parseInt(request.getParameter("sid"));
        int id = Integer.parseInt(request.getParameter("pId")); //pId ShopProductID
        ShopProductDAO spd = new ShopProductDAO();
        ShopProduct sp = spd.getShopProductById(id);
        dal.CategoryDAO d = new dal.CategoryDAO();
        dal.ProductItemDAO pl = new dal.ProductItemDAO();
        WishListDAO wld = new WishListDAO();
        dal.ShopDAO s = new dal.ShopDAO();
        dal.ImagesDAO i = new dal.ImagesDAO();
        BrandDAO bd = new BrandDAO();
        CommentDAO cmt = new CommentDAO();
        RatingDAO r = new RatingDAO();
        Discount dis = new Discount();
        
        DiscountDAO disD = new DiscountDAO();
        dis =  disD.getDisInfoDiscountById(id);
        list = spd.getRandomProductOfShop(shopp);
        list1 = d.getCategoryAll();
        list2 = pl.getAllProductColor(id);
        list3 = pl.getAllProductSize(id);
        list4 = i.getAllImagesByShopProductId(id);
         listr = r.getAllRatingByShopProductId(id);
        List<Brand> listB = bd.getBrandAll();
        Rating rating = r.getAvgRatingByShopProductId(id);
        //create boolean brand to save value checkbox brand
        boolean[] bb = new boolean[listB.size() + 1];
        //set value[0]
        bb[0] = true;
        listc = cmt.getAllCommentByShopProductId(id);
        int sizeId = Integer.parseInt(request.getParameter("size"));
        int colorId = Integer.parseInt(request.getParameter("color"));
        ProductItem quantity = pl.getQuantity(id, sizeId, colorId);
        ProductItem sumquantity = pl.getSumQuantityByShopProductId(id);
        Shop countproduct = s.getCountProductOfShopByShopId(shopp);
        ProductItem pr = pl.getProductByID(id);
        Shop shop = s.getShopByShopProductId(id);
         Rating countRating = r.countRatingByShop(shopp);
        Account a = (Account) session.getAttribute("user");
        if (a == null) {
            request.setAttribute("countcmt", countRating);
            request.setAttribute("data", list);
            request.setAttribute("countproduct", countproduct);
            request.setAttribute("listB", listB);
            request.setAttribute("sumquantity", sumquantity);
            request.setAttribute("quantity", quantity);
            request.setAttribute("shopProduct", sp);
            request.setAttribute("shop", shop);
            request.setAttribute("detail", pr);
            request.setAttribute("data1", list1);
            request.setAttribute("color", list2);
            request.setAttribute("size", list3);
            request.setAttribute("images", list4);
            request.setAttribute("comment", listr);
            request.setAttribute("dis", dis);
            request.setAttribute("sid", shopp);
            request.setAttribute("rating", rating);
            request.getRequestDispatcher("DetailProduct.jsp").forward(request, response);
        } else {
            UserDAO ud = new UserDAO();
            User u = ud.getUserByAccountId(a.getAccountId());
            WishList w1 = wld.checkWishList(id, u.getUserId());
            request.setAttribute("countcmt", countRating);
            request.setAttribute("data", list);
            request.setAttribute("countproduct", countproduct);
            request.setAttribute("listB", listB);
            request.setAttribute("sumquantity", sumquantity);
            request.setAttribute("quantity", quantity);
            request.setAttribute("shopProduct", sp);
            request.setAttribute("shop", shop);
            request.setAttribute("detail", pr);
            request.setAttribute("data1", list1);
            request.setAttribute("color", list2);
            request.setAttribute("size", list3);
            request.setAttribute("images", list4);
            request.setAttribute("comment", listr);
            request.setAttribute("sid", shopp);
            request.setAttribute("customer", u);
            request.setAttribute("dis", dis);
            request.setAttribute("wishlist", w1);
            request.setAttribute("rating", rating);
            request.getRequestDispatcher("DetailProduct.jsp").forward(request, response);
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
