/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 *
 * @author Admin
 */
@WebServlet(name="updateAllQuantities", urlPatterns={"/updateAllQuantities"})
public class updateAllQuantities extends HttpServlet {
   
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
            out.println("<title>Servlet updateAllQuantities</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet updateAllQuantities at " + request.getContextPath () + "</h1>");
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
        processRequest(request, response);
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
        // Lấy danh sách các tham số gửi lên từ form
        Map<String, String[]> parameters = request.getParameterMap();
        
        // Lặp qua tất cả các tham số (mỗi tham số là thông tin về màu, kích cỡ và số lượng)
        for (Map.Entry<String, String[]> entry : parameters.entrySet()) {
            String paramName = entry.getKey();
            String[] paramValues = entry.getValue();

            // Phân tích thông tin từ tên tham số để lấy thông tin về sản phẩm
            // Ví dụ: color_1_1, size_1_1, quantity_1_1
            if (paramName.startsWith("color_")) {
                String[] parts = paramName.split("_");
                int productId = Integer.parseInt(parts[1]);
                int sizeId = Integer.parseInt(parts[2]);
                String colorId = paramValues[0];
                
                // Cập nhật thông tin màu sắc cho sản phẩm
                // Tiến hành cập nhật trong cơ sở dữ liệu
            } else if (paramName.startsWith("size_")) {
                String[] parts = paramName.split("_");
                int productId = Integer.parseInt(parts[1]);
                int colorId = Integer.parseInt(parts[2]);
                String sizeId = paramValues[0];
                
                // Cập nhật thông tin kích cỡ cho sản phẩm
                // Tiến hành cập nhật trong cơ sở dữ liệu
            } else if (paramName.startsWith("quantity_")) {
                String[] parts = paramName.split("_");
                int productId = Integer.parseInt(parts[1]);
                int colorId = Integer.parseInt(parts[2]);
                int sizeId = Integer.parseInt(parts[3]);
                int quantity = Integer.parseInt(paramValues[0]);
                
                // Cập nhật số lượng cho sản phẩm
                // Tiến hành cập nhật trong cơ sở dữ liệu
            }
        }
        
        // Sau khi cập nhật thành công, chuyển hướng lại trang hoặc hiển thị thông báo
        response.sendRedirect("successPage.jsp");
    }
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */


