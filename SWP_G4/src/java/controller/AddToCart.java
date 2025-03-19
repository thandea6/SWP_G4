/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.ProductItemDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import model.ProductItem;

/**
 *
 * @author admin
 */
@WebServlet(name = "AddToCart", urlPatterns = {"/addtocart"})
public class AddToCart extends HttpServlet {

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
            out.println("<title>Servlet AddToCart</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddToCart at " + request.getContextPath() + "</h1>");
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
        ProductItemDAO d = new ProductItemDAO();
        List<ProductItem> list = d.getAllProductItem();
        Cookie[] arr = request.getCookies();
        String txt = "";
        HttpSession session = request.getSession();
        Set<String> uniqueProducts = new HashSet<>();

// Retrieve the current cart content from cookies
        if (arr != null) {
            for (Cookie o : arr) {
                if (o.getName().equals("cart")) {
                    txt += o.getValue();
                }
            }
        }

        if (!txt.isEmpty()) {
            String[] items = txt.split("/");
            for (String item : items) {
                String[] parts = item.split(":");
                String productId = parts[0];
                uniqueProducts.add(productId);
            }
        }

        String num = request.getParameter("quantity");
        String Pid = request.getParameter("productItemId");
        String Sid = request.getParameter("ShopProductId");

// Validate Pid, Sid, and num
        if (Pid == null || Pid.isEmpty() || Sid == null || Sid.isEmpty() || num == null || num.isEmpty()) {
            request.setAttribute("err", "Please provide valid product, shop, and quantity information or Product out of stock.");
            request.getRequestDispatcher("detail?pid=" + Sid).forward(request, response);
        } else {
            try {
                int quantity = Integer.parseInt(num);
                int ProductItemId = Integer.parseInt(Pid);
                int ShopProductItemId = Integer.parseInt(Sid);

                if (quantity <= 0) {
                    request.setAttribute("err", "Quantity must be greater than zero.");
                    request.getRequestDispatcher("detail?pid=" + Sid).forward(request, response);
                    return;
                }

                boolean productFound = false;
                boolean ShopProductFound = false;
                for (ProductItem item : list) {
                    if (item.getProductItemId() == ProductItemId) {
                        productFound = true;
                        ShopProductFound = true;
                        break;
                    }
                }

                if (!productFound || !ShopProductFound) {
                    request.setAttribute("err", "Selected product or shop product not found.");
                    request.getRequestDispatcher("detail?pid=" + Sid).forward(request, response);
                } else {
                    int stock = d.getQuantitybyProductItemId(ProductItemId);
                    if (quantity > stock) {
                        request.setAttribute("err", "Requested quantity exceeds available stock.");
                        request.getRequestDispatcher("detail?pid=" + Sid).forward(request, response);
                    } else {
                        // Update cart content
                        if (txt.isEmpty()) {
                            txt = Pid + ":" + num + ":0";
                        } else {
                            txt = txt + "/" + Pid + ":" + num + ":0";
                        }

                        uniqueProducts.add(Pid);

                        // Remove old cart cookies
                        if (arr != null) {
                            for (Cookie o : arr) {
                                if (o.getName().equals("cart")) {
                                    o.setMaxAge(0);
                                    response.addCookie(o);
                                }
                            }
                        }

                        String[] length = txt.split("/");

                        // Add updated cart content as a new cookie
                        Cookie c = new Cookie("cart", txt);
                        c.setMaxAge(2 * 24 * 60 * 60); // 2 days
                        response.addCookie(c);

                        Cookie uniqueProductCountCookie = new Cookie("uniqueProductCount", String.valueOf(length.length));
                        uniqueProductCountCookie.setMaxAge(2 * 24 * 60 * 60); // 2 days
                        response.addCookie(uniqueProductCountCookie);

                        response.setContentType("application/json");
                        response.setCharacterEncoding("UTF-8");
                        response.getWriter().write("{\"uniqueProductCount\": \"" + length.length + "\"}");
                        request.setAttribute("ms", "Sản phẩm được thêm vào giỏ hàng thành công.");
                        request.getRequestDispatcher("detail?pid=" + Pid +"sid=" + Sid ).forward(request, response);
                        request.getRequestDispatcher("header.jsp").forward(request, response);
                    }
                }
            } catch (NumberFormatException e) {
                
                request.getRequestDispatcher("detail?pid=" + Sid).forward(request, response);
            }
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
        ProductItemDAO d = new ProductItemDAO();
        List<ProductItem> list = d.getAllProductItem();
        Cookie[] arr = request.getCookies();
        String txt = "";
        HttpSession session = request.getSession();
        int totalQuantity = 0;

// Retrieve the current cart content from cookies
        if (arr != null) {
            for (Cookie o : arr) {
                if (o.getName().equals("cart")) {
                    txt += o.getValue();
                }
            }
        }

        String num = request.getParameter("quantity");
        String Pid = request.getParameter("productItemId");
        String Sid = request.getParameter("ShopProductId");

        if (num == null || num.isEmpty() || Pid == null || Pid.isEmpty()) {
            request.setAttribute("err", "The product is out of stock");
            request.getRequestDispatcher("detail?pid=" + Sid).forward(request, response);
        } else {
            try {
                int quantity = Integer.parseInt(num);
                int ProductItemId = Integer.parseInt(Pid);
                int ShopProductItemId = Integer.parseInt(Sid);

                boolean productFound = false;
                boolean ShopProductFound = false;
                for (ProductItem item : list) {
                    if (item.getProductItemId() == ProductItemId) {
                        productFound = true;
                        ShopProductFound = true;
                        break;
                    }
                }

                if (!productFound || !ShopProductFound) {
                    request.setAttribute("err", "Selected product or shopProduct not found.");
                    request.getRequestDispatcher("detail?pid=" + Sid).forward(request, response);
                } else {
                    // Update cart content
                    if (txt.isEmpty()) {
                        txt = Pid + ":" + num + ":0";
                    } else {
                        txt = txt + "/" + Pid + ":" + num + ":0";
                    }

                    // Remove old cart cookies
                    if (arr != null) {
                        for (Cookie o : arr) {
                            if (o.getName().equals("cart")) {
                                o.setMaxAge(0);
                                response.addCookie(o);
                            }
                        }
                    }
                    String[] length = txt.split("/");
                    // Add updated cart content as a new cookie
                    Cookie c = new Cookie("cart", txt);
                    c.setMaxAge(2 * 24 * 60 * 60); // 2 days
                    response.addCookie(c);
                    Cookie uniqueProductCountCookie = new Cookie("uniqueProductCount", "" + length.length);
                    uniqueProductCountCookie.setMaxAge(2 * 24 * 60 * 60); // 2 days
                    response.addCookie(uniqueProductCountCookie);
                    response.sendRedirect("showcart");
                }
            } catch (NumberFormatException e) {
                request.setAttribute("err", "Invalid quantity or product ID format.");
                request.getRequestDispatcher("detail?pid=" + Sid).forward(request, response);
            }

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
