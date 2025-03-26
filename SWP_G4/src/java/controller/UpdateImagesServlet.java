/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.ImagesDAO;
import dal.ShopDAO;
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
import model.Images;
import model.Shop;

/**
 *
 * @author admin
 */
@WebServlet(name = "UpdateImagesServlet", urlPatterns = {"/updateImages"})
public class UpdateImagesServlet extends HttpServlet {

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
            /* TODO output your page here. You may use following sample code  */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UpdateImagesServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateImagesServlet at " + request.getContextPath() + "</h1>");
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
        int productId = Integer.parseInt(request.getParameter("productId"));
        HttpSession session = request.getSession();
        Account user = (Account) session.getAttribute("user");

        if (user == null) {
            request.getRequestDispatcher("logout").forward(request, response);

        } else {
            int accountId = user.getAccountId();
            ImagesDAO image = new ImagesDAO();
            ShopDAO sd = new ShopDAO();
            List<Images> listI = image.getAllImagesByshopProductId(productId);
            List<Shop> listS = sd.getShop(accountId);
            Shop shop = listS.get(0);
            
            request.setAttribute("productId", productId);
            request.setAttribute("listI", listI);
            request.setAttribute("shop", shop);

            request.getRequestDispatcher("updateImages.jsp").forward(request, response);
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
        int productId = Integer.parseInt(request.getParameter("id"));
        String newImageLink = request.getParameter("newImageLink");
        HttpSession session = request.getSession();
        Account user = (Account) session.getAttribute("user");
        
        if (user == null) {
            request.getRequestDispatcher("logout").forward(request, response);
        } else {
            int accountId = user.getAccountId();
            ImagesDAO im = new ImagesDAO();
            
            // Add new image link if it's provided
            if (newImageLink != null && !newImageLink.isEmpty()) {
                im.addImagesByshopProductId(productId, "images/"+ newImageLink);
                request.setAttribute("mess", "Thêm Ảnh thành công!");
            } else {
                request.setAttribute("mess", "Vui lòng chọn ảnh");
            }
            
            // Get updated list of images and shop info
            ImagesDAO image = new ImagesDAO();
            ShopDAO sd = new ShopDAO();
            List<Images> listI = image.getAllImagesByshopProductId(productId);
            List<Shop> listS = sd.getShop(accountId);
            Shop shop = listS.get(0);
            
            request.setAttribute("productId", productId);
            request.setAttribute("listI", listI);
            request.setAttribute("shop", shop);

            request.getRequestDispatcher("updateImages.jsp").forward(request, response);
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
