/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.BrandDAO;
import dal.CategoryDAO;
import dal.ProductLineDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Account;
import model.Brand;
import model.Category;
import model.ProductLine;

/**
 *
 * @author GiaKhiem
 */
public class AddProductLineServlet extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
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
            out.println("<title>Servlet AddProductLineServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddProductLineServlet at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
        }
        CategoryDAO cd = new CategoryDAO();
        BrandDAO bd = new BrandDAO();
        List<Category> listC = cd.getCategoryAll();
        List<Brand> listB = bd.getBrandAll();

        request.setAttribute("listC", listC);
        request.setAttribute("listB", listB);
        request.getRequestDispatcher("addProductLine.jsp").forward(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
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
            String name = request.getParameter("name");
            String category = request.getParameter("category");
            String brand = request.getParameter("brand");

            CategoryDAO cd = new CategoryDAO();
            BrandDAO bd = new BrandDAO();

            if (name == null || name.trim().isEmpty()
                    || category == null || category.trim().isEmpty()
                    || brand == null || brand.trim().isEmpty()) {

                request.setAttribute("mess", "Vui Lòng Nhập Đúng Định Dạng");
                //select cate,brand again
                List<Category> listC = cd.getCategoryAll();
                List<Brand> listB = bd.getBrandAll();

                request.setAttribute("listC", listC);
                request.setAttribute("listB", listB);

                request.getRequestDispatcher("addProductLine.jsp").forward(request, response);
            } else {
                int cateId = Integer.parseInt(category);
                int brandId = Integer.parseInt(brand);
                ProductLineDAO pd = new ProductLineDAO();
                pd.addProductLine(name, cateId, brandId);

                request.setAttribute("mess", "Thêm Thành Công");
                //print again listP
                List<ProductLine> listP;
                listP = pd.getProductLine();
                request.setAttribute("listP", listP);
                request.getRequestDispatcher("listProductLine.jsp").forward(request, response);
            }
        }
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
