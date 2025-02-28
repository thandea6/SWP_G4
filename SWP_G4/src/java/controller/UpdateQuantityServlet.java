/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.ColorDAO;
import dal.ProductItemDAO;
import dal.ProductLineDAO;
import dal.ShopDAO;
import dal.SizeDAO;
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
import model.Color;
import model.ProductLine;
import model.Shop;
import model.Size;

/**
 *
 * @author admin
 */
@WebServlet(name = "UpdateQuantityServlet", urlPatterns = {"/updateQuantity"})
public class UpdateQuantityServlet extends HttpServlet {

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
            out.println("<title>Servlet UpdateQuantityServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateQuantityServlet at " + request.getContextPath() + "</h1>");
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
    Account user = (Account) session.getAttribute("user");

    if (user == null) {
        request.getRequestDispatcher("logout").forward(request, response);
    } else {
        int accountId = user.getAccountId();
        int productId = Integer.parseInt(request.getParameter("productId"));
        String action = request.getParameter("action");
        
        ColorDAO c = new ColorDAO();
        SizeDAO s = new SizeDAO();

        ShopDAO sd = new ShopDAO();
        List<Shop> listShop = sd.getShop(accountId);
        Shop shop = listShop.get(0);
        ProductLineDAO pd = new ProductLineDAO();
        List<ProductLine> listP = pd.getQuantityProductById(productId, accountId);

        List<Color> listC = c.getAllColor();
        List<Size> listS = s.getAllSize();

        request.setAttribute("productId", productId);
        request.setAttribute("listC", listC);
        request.setAttribute("listS", listS);
        request.setAttribute("shop", shop);
        request.setAttribute("listP", listP);

        if ("edit".equals(action)) {
            String colorU = request.getParameter("colorU");
            String sizeU = request.getParameter("sizeU");
            String quantityU = request.getParameter("quantityU");

            request.setAttribute("colorU", colorU);
            request.setAttribute("sizeU", sizeU);
            request.setAttribute("quantityU", quantityU);
        }

        request.getRequestDispatcher("updateQuantity.jsp").forward(request, response);
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
    Account user = (Account) session.getAttribute("user");
    String action = request.getParameter("action");
    int accountId = user.getAccountId();
    int productId = Integer.parseInt(request.getParameter("productId"));
    ProductLineDAO pd = new ProductLineDAO();
    List<ProductLine> listP;

    if ("update".equals(action)) {
        String colorId_raw = request.getParameter("color");
        String sizeId_raw = request.getParameter("size");
        String quantity_raw = request.getParameter("quantity");

        // Check if any of the parameters are null or empty
        if (colorId_raw == null || colorId_raw.isEmpty()
                || sizeId_raw == null || sizeId_raw.isEmpty()
                || quantity_raw == null || quantity_raw.isEmpty()) {
            listP = pd.getQuantityProductById(productId, accountId);
            request.setAttribute("listP", listP);
            request.setAttribute("mess", "Vui lòng nhập đúng định dạng");
        } else {
            int colorId = Integer.parseInt(colorId_raw);
            int sizeId = Integer.parseInt(sizeId_raw);
            int quantity = Integer.parseInt(quantity_raw);

            ProductItemDAO pi = new ProductItemDAO();
            pi.updateProductItem(productId, quantity, user.getAccountId(), sizeId, colorId);

            listP = pd.getQuantityProductById(productId, accountId);
            request.setAttribute("listP", listP);
            request.setAttribute("mess", "Cập nhật số lượng thành công!");
        }

    } else if ("search".equals(action)) {
        String color_raw = request.getParameter("color1");
        String size_raw = request.getParameter("size1");

        if ((color_raw == null || color_raw.isEmpty()) && (size_raw == null || size_raw.isEmpty())) {
            listP = pd.getQuantityProductById(productId, accountId);
        } else if (color_raw == null || color_raw.isEmpty()) {
            int sizeId = Integer.parseInt(size_raw);
            listP = pd.getQuantityProductBySize(productId, accountId, sizeId);
        } else if (size_raw == null || size_raw.isEmpty()) {
            int colorId = Integer.parseInt(color_raw);
            listP = pd.getQuantityProductByColor(productId, accountId, colorId);
        } else {
            int colorId = Integer.parseInt(color_raw);
            int sizeId = Integer.parseInt(size_raw);
            listP = pd.getQuantityProductByColorAndSize(productId, accountId, colorId, sizeId);
        }

        request.setAttribute("listP", listP);
    }

    // Set up other attributes for the JSP page
    ColorDAO c = new ColorDAO();
    SizeDAO s = new SizeDAO();
    ShopDAO sd = new ShopDAO();
    
    List<Shop> listShop = sd.getShop(accountId);
    Shop shop = listShop.get(0);
    List<Color> listC = c.getAllColor();
    List<Size> listS = s.getAllSize();

    request.setAttribute("productId", request.getParameter("productId"));
    request.setAttribute("listC", listC);
    request.setAttribute("listS", listS);
    request.setAttribute("shop", shop);

    request.getRequestDispatcher("updateQuantity.jsp").forward(request, response);
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
