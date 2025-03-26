/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import com.google.gson.Gson;
import dal.ProductItemDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import model.Cart;
import model.Item;
import model.ProductItem;

/**
 *
 * @author admin
 */
@WebServlet(name = "RemoveCartServlet", urlPatterns = {"/removecart"})
public class RemoveCartServlet extends HttpServlet {

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
            out.println("<title>Servlet RemoveCartServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RemoveCartServlet at " + request.getContextPath() + "</h1>");
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
        processRequest(request, response);
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
        String RemoveProductItemId = request.getParameter("removeItemId");
        String RemoveAll_raw = request.getParameter("removeAllItems");
        //ProductItemId
        String ProductItemId = request.getParameter("ProductItemId");
        ProductItemDAO d = new ProductItemDAO();
        List<ProductItem> list = d.getAllProductItem();
        Cookie[] arr = request.getCookies();
        String txt = "";
        if (arr != null) {
            for (Cookie o : arr) {
                if (o.getName().equals("cart")) {
                    txt += o.getValue();
                    o.setMaxAge(0);
                    response.addCookie(o);
                }
            }
        }

        Cart cart = new Cart(txt, list);
        if (RemoveProductItemId != null) {
            int id = Integer.parseInt(ProductItemId);
            cart.removeAllItemsById(id);
        }

        if (RemoveAll_raw != null) {
            cart.removeItemAll();
        }

        List<Item> items = cart.getItems();
        txt = "";
        if (items.size() > 0) {
            txt = items.get(0).getProductItem().getProductItemId() + ":"
                    + items.get(0).getQuantity() + ":" + "0";
            for (int i = 1; i < items.size(); i++) {
                txt += "/" + items.get(i).getProductItem().getProductItemId() + ":"
                        + items.get(i).getQuantity() + ":" + "0";
            }
        }
        Cookie c = new Cookie("cart", txt);
        c.setMaxAge(2 * 24 * 60 * 60);
        response.addCookie(c);

        int uniqueProductCount = calculateUniqueProductCount(items);
        Cookie uniqueProductCountCookie = new Cookie("uniqueProductCount", String.valueOf(uniqueProductCount));
        uniqueProductCountCookie.setMaxAge(2 * 24 * 60 * 60);
        response.addCookie(uniqueProductCountCookie);

        request.setAttribute("cart", cart);
        request.setAttribute("uniqueProductCount", uniqueProductCount);
        request.getRequestDispatcher("cart.jsp").forward(request, response);
        request.getRequestDispatcher("header.jsp").forward(request, response);
    }

    private int calculateUniqueProductCount(List<Item> items) {
        // Implement logic to count unique product items in the cart
        // Here, you might use a set or other data structure to count unique product IDs
        // Example logic (you may need to adjust based on your Item and ProductItem models):
        if (items == null || items.isEmpty()) {
            return 0;
        }

        // Use a set to count unique product item IDs
        Set<Integer> uniqueProductIds = new HashSet<>();
        for (Item item : items) {
            uniqueProductIds.add(item.getProductItem().getProductItemId());
        }

        return uniqueProductIds.size();
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
