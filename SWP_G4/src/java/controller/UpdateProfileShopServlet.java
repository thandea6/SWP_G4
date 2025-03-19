/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.AccountDAO;
import dal.ProductLineDAO;
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
import model.ProductLine;
import model.Shop;

/**
 *
 * @author admin
 */
@WebServlet(name = "UpdateProfileShopServlet", urlPatterns = {"/updateShop"})
public class UpdateProfileShopServlet extends HttpServlet {

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
            /* TODO output your page here. You may use following sample code */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UpdateProfileShopServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateProfileShopServlet at " + request.getContextPath() + "</h1>");
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
            ShopDAO sd = new ShopDAO();
            Shop shopA;
            List<Shop> listS;
            shopA = sd.getShopByAccountId(accountId);

            listS = sd.getShop(accountId);
            Shop shop = listS.get(0);
            AccountDAO ad = new AccountDAO();
            Account acc = ad.getAccountByAID(accountId);
            request.setAttribute("acc", acc);
            request.setAttribute("shopA", shopA);
            request.setAttribute("shop", shop);
            request.getRequestDispatcher("shopProfile.jsp").forward(request, response);
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

        if (user == null) {
            request.getRequestDispatcher("logout").forward(request, response);
        } else {
            int accountId = user.getAccountId();
            ShopDAO sd = new ShopDAO();
            AccountDAO ad = new AccountDAO();

            String address = request.getParameter("address").trim();
            String phone = request.getParameter("phone");
            String image = request.getParameter("image");
            Shop shopById = sd.getShopByAccountId(accountId);
            if (!validatePhoneNumber(phone) || address.isEmpty() || address ==null) {
                request.setAttribute("mess", "Vui lòng nhập đúng định dạng");
            } else {

                if (image == null || image.isEmpty()) {
                    sd.update(accountId, address, shopById.getImage());
                } else {
                    sd.update(accountId, address, "images/" + image);
                }
                ad.updatePhone(accountId, phone);
                request.setAttribute("mess", "cập nhật thông tin thành công");
            }
                Shop shopA = sd.getShopByAccountId(accountId);
                request.setAttribute("shopA", shopA);

                List<Shop> listS;
                listS = sd.getShop(accountId);
                Shop shop = listS.get(0);
                request.setAttribute("shop", shop);

                Account acc = ad.getAccountByAID(accountId);
                request.setAttribute("acc", acc);
                request.getRequestDispatcher("shopProfile.jsp").forward(request, response);
            
        }
    }

    public boolean validatePhoneNumber(String phoneNumber) {
        String regexPhoneNumber = "^(0[3|5|7|8|9])+([0-9]{8})\\b";

        return phoneNumber.matches(regexPhoneNumber);
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
