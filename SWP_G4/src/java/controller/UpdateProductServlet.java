/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.BrandDAO;
import dal.CategoryDAO;
import dal.ColorDAO;
import dal.ProductItemDAO;
import dal.ProductLineDAO;
import dal.ShopDAO;
import dal.ShopProductDAO;
import dal.SizeDAO;
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
import model.Color;
import model.ProductLine;
import model.Shop;
import model.Size;

/**
 *
 * @author admin
 */
@WebServlet(name = "UpdateProductServlet", urlPatterns = {"/updateProduct"})
public class UpdateProductServlet extends HttpServlet {

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
            out.println("<title>Servlet UpdateProductServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateProductServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    
    //fixxxx code
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account user = (Account) session.getAttribute("user");

        if (user == null) {
            request.getRequestDispatcher("logout").forward(request, response);
        } else {
            int accountId = user.getAccountId();
            int productId = Integer.parseInt(request.getParameter("ID"));
            ProductLineDAO pd = new ProductLineDAO();

            ShopDAO sd = new ShopDAO();
            List<Shop> listShop = sd.getShop(accountId);
            Shop shop = listShop.get(0);
            ProductLine InfoP = pd.getInfoProductById(productId, accountId);
            List<ProductLine> listPL = pd.getAllProductLine();
            request.setAttribute("listPL", listPL);

            request.setAttribute("InfoP", InfoP);

            request.setAttribute("shop", shop);
            request.getRequestDispatcher("updateProduct.jsp").forward(request, response);
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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account user = (Account) session.getAttribute("user");

        if (user == null) {
            request.getRequestDispatcher("logout").forward(request, response);
        } else {
            try {
                int accountId = user.getAccountId();
                int productId = Integer.parseInt(request.getParameter("id"));
                String productLineIdStr = request.getParameter("productLineName");
                String priceStr = request.getParameter("price");
                String description = request.getParameter("des");

                String image = request.getParameter("image");
                String title = request.getParameter("title");
                String oldImage = request.getParameter("oldImage");

                double price = Double.parseDouble(priceStr);
                int productLineId = Integer.parseInt(productLineIdStr);

                // Perform update operations
                ProductLineDAO pd = new ProductLineDAO();
                ShopProductDAO sp = new ShopProductDAO();
                ProductItemDAO pi = new ProductItemDAO();
                // DAO

                ShopDAO sd = new ShopDAO();

                List<Shop> listShop = sd.getShop(accountId);
                Shop shop = listShop.get(0);
                ProductLine InfoP = pd.getInfoProductById(productId, accountId);

                List<ProductLine> listPL = pd.getAllProductLine();

                if (title == null || title.trim().isEmpty()
                        || priceStr == null || priceStr.trim().isEmpty()
                        || description == null || description.trim().isEmpty()
                        || productLineIdStr == null || productLineIdStr.trim().isEmpty()) {
                    request.setAttribute("listPL", listPL);
                    request.setAttribute("InfoP", InfoP);

                    request.setAttribute("shop", shop);
                    request.setAttribute("mess", "Vui lòng nhập đúng định dạng");
                    request.getRequestDispatcher("updateProduct.jsp").forward(request, response);
                    return;
                }
                //update    
                pd.updateProductLineName(productId, productLineId);

                if (image == null || image.isEmpty()) {
                    sp.updateShopProduct(productId, price, description, oldImage, title);
                } else {
                    sp.updateShopProduct(productId, price, description, "images/" + image, title);
                }

                InfoP = pd.getInfoProductById(productId, accountId);
                // Set attributes for JSP
                request.setAttribute("listPL", listPL);
                request.setAttribute("InfoP", InfoP);
                request.setAttribute("shop", shop);
                request.setAttribute("mess", "Cập nhật thành công!");
                request.getRequestDispatcher("updateProduct.jsp").forward(request, response);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                request.setAttribute("mess", "Invalid input");
                request.getRequestDispatcher("updateProduct.jsp").forward(request, response);
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
