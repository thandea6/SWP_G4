<<<<<<< HEAD

=======
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
>>>>>>> 420fd3596f7a021da356974e42a221d3c07efdbe
package controller;

import dal.OrderDAO;
import dal.ShopDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import model.Account;
import model.Order;
import model.Shop;

/**
 *
 * @author admin
 */
@WebServlet(name = "OrderManagementServlet", urlPatterns = {"/orderManagement"})
public class OrderManagementServlet extends HttpServlet {

<<<<<<< HEAD
    
=======
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
            out.println("<title>Servlet OrderManagementServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet OrderManagementServlet at " + request.getContextPath() + "</h1>");
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
>>>>>>> 420fd3596f7a021da356974e42a221d3c07efdbe
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        OrderDAO od = new OrderDAO();
        ShopDAO sd = new ShopDAO();
        HttpSession session = request.getSession();
        Account user = (Account) session.getAttribute("user");
        if (user == null) {
            request.getRequestDispatcher("logout").forward(request, response);
        } else {
            int accountId = user.getAccountId();
            Shop shop = sd.getShopByAccountId(accountId);
            int shopId = shop.getShopId();
            List<Order> orderList = od.getOrderListByShop(shopId);

//            List<Shop> listS;
//            listS = sd.getShop(accountId);
//            Shop shop1 = listS.get(0);

            request.setAttribute("orderList", orderList);
            request.setAttribute("shopId", shopId);
 //            request.setAttribute("shop", shop1);
<<<<<<< HEAD
            request.getRequestDispatcher("manager/orderManagement.jsp").forward(request, response);
        }
    }

   
=======
            request.getRequestDispatcher("orderManagement.jsp").forward(request, response);
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
>>>>>>> 420fd3596f7a021da356974e42a221d3c07efdbe
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account user = (Account) session.getAttribute("user");
        String txt = request.getParameter("phone");
        int shopId = Integer.parseInt(request.getParameter("shopId"));

        OrderDAO od = new OrderDAO();
        List<Order> list = od.getOrderListByShop(shopId);
        List<Order> orderList = new ArrayList<>();


        for (Order o : list) {
            String phone = o.getPhone();
            if (phone != null && phone.contains(txt)) {
                orderList.add(o);
            }
        }
        request.setAttribute("txt", txt);
        request.setAttribute("shopId", shopId);
        request.setAttribute("orderList", orderList);
<<<<<<< HEAD
        request.getRequestDispatcher("manager/orderManagement.jsp").forward(request, response);
    }

   
=======
        request.getRequestDispatcher("orderManagement.jsp").forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
>>>>>>> 420fd3596f7a021da356974e42a221d3c07efdbe
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
