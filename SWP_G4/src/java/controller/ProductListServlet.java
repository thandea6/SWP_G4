/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

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
@WebServlet(name = "ProductListServlet", urlPatterns = {"/productList"})
public class ProductListServlet extends HttpServlet {

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
            out.println("<title>Servlet ProductListServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProductListServlet at " + request.getContextPath() + "</h1>");
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
        }else{
            int accountId = user.getAccountId();
        //lay ra san pham
        ProductLineDAO pd = new ProductLineDAO();
        List<ProductLine> listP;

        String txt = request.getParameter("txt");
        if (txt == null || txt.isEmpty()) {
            listP = pd.getAllProductByShop(accountId);
        } else {
            listP = pd.getAllProductShopBySearch(accountId, txt);
        }

//        ShopDAO sd = new ShopDAO();
//        List<Shop> listS;
//        listS = sd.getShop(accountId);
//        Shop shop = listS.get(0);

        String sort = request.getParameter("sort");
        if (sort != null) {
            if (sort.equals("giam")) {
                for (int i = 0; i < listP.size(); i++) {
                    for (int j = i; j < listP.size(); j++) {
                        if (listP.get(i).getPrice() < listP.get(j).getPrice()) {
                            ProductLine temp = listP.get(i);
                            listP.set(i, listP.get(j));
                            listP.set(j, temp);
                        }
                    }
                }
            } else if (sort.equals("tang")) {
                for (int i = 0; i < listP.size(); i++) {
                    for (int j = i; j < listP.size(); j++) {
                        if (listP.get(i).getPrice() > listP.get(j).getPrice()) {
                            ProductLine temp = listP.get(i);
                            listP.set(i, listP.get(j));
                            listP.set(j, temp);
                        }
                    }
                }
            }
        }
        request.setAttribute("txt", txt);
 //       request.setAttribute("shop", shop);
        request.setAttribute("listP", listP);
        request.getRequestDispatcher("productList.jsp").forward(request, response);
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
        processRequest(request, response);

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
