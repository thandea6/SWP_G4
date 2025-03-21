/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
<<<<<<< HEAD

=======
>>>>>>> c8efd27cc5f43b5bce07f6445cf0142944da1b70
package controller;

import dal.ShopProductDAO;
import dal.UserDAO;
import dal.WishListDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
<<<<<<< HEAD
=======
import jakarta.servlet.annotation.WebServlet;
>>>>>>> c8efd27cc5f43b5bce07f6445cf0142944da1b70
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;
import model.ShopProduct;
import model.User;

/**
 *
<<<<<<< HEAD
 * @author GiaKhiem
 */
public class WishListServlet extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
=======
 * @author admin
 */
@WebServlet(name = "WishListServlet", urlPatterns = {"/wishlist"})
public class WishListServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
>>>>>>> c8efd27cc5f43b5bce07f6445cf0142944da1b70
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
<<<<<<< HEAD
    throws ServletException, IOException {
=======
            throws ServletException, IOException {
>>>>>>> c8efd27cc5f43b5bce07f6445cf0142944da1b70
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
<<<<<<< HEAD
            out.println("<title>Servlet WishListServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet WishListServlet at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
=======
            out.println("<title>Servlet WishListServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet WishListServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
>>>>>>> c8efd27cc5f43b5bce07f6445cf0142944da1b70
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
<<<<<<< HEAD
    throws ServletException, IOException {
=======
            throws ServletException, IOException {
>>>>>>> c8efd27cc5f43b5bce07f6445cf0142944da1b70
        int pid = Integer.parseInt(request.getParameter("pid"));
        int sid = Integer.parseInt(request.getParameter("sid"));
        System.out.println(pid);
        ShopProductDAO spd = new ShopProductDAO();
        ShopProduct sp = spd.getShopProductById(pid);
        WishListDAO wld = new WishListDAO();
        HttpSession session = request.getSession();
        Account a = (Account) session.getAttribute("user");
        if (a == null) {
            response.sendRedirect("login");
        } else {

            if (a.getRoleId() != 2) {
<<<<<<< HEAD
                String ms = "You must login with a user account";
=======
                String ms = "You must log in with a user account";
>>>>>>> c8efd27cc5f43b5bce07f6445cf0142944da1b70
                request.setAttribute("err", ms);
                request.getRequestDispatcher("login").forward(request, response);
                return;
            }
            UserDAO ud = new UserDAO();
            User u = ud.getUserByAccountId(a.getAccountId());
            wld.insertWishList(pid, u.getUserId());
            request.setAttribute("ms", "Đã thêm sản phẩm vào WishList thành công.");
            request.getRequestDispatcher("detail?pid=" + pid + "&sid=" + sid).forward(request, response);
            request.getRequestDispatcher("header.jsp").forward(request, response);
<<<<<<< HEAD
        }
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
=======

        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
>>>>>>> c8efd27cc5f43b5bce07f6445cf0142944da1b70
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
<<<<<<< HEAD
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
=======
            throws ServletException, IOException {
    }


/**
 * Returns a short description of the servlet.
 *
 * @return a String containing servlet description
 */
@Override
public String getServletInfo() {
>>>>>>> c8efd27cc5f43b5bce07f6445cf0142944da1b70
        return "Short description";
    }// </editor-fold>

}
