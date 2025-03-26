package controller;

import dal.ColorDAO;
import dal.ImagesDAO;
import dal.ProductLineDAO;
import dal.ShopDAO;
import dal.SizeDAO;
import java.io.IOException;
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
import model.Images;
import model.ProductLine;
import model.Shop;
import model.Size;

@WebServlet(name = "ShopProductDetail", urlPatterns = {"/shopProductDetail"})
public class ShopProductDetail extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        // Mã xử lý trong phương thức processRequest
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int productId = Integer.parseInt(request.getParameter("ID"));

        HttpSession session = request.getSession();
        Account user = (Account) session.getAttribute("user");

        if (user == null) {
            request.getRequestDispatcher("logout").forward(request, response);
        } else {

            int accountId = user.getAccountId();

            ProductLineDAO pd = new ProductLineDAO();
            ShopDAO sd = new ShopDAO();

            List<Shop> listS = sd.getShop(accountId);
            Shop shop = listS.get(0);
            ColorDAO c = new ColorDAO();
            SizeDAO size = new SizeDAO();
            List<Color> listC = c.getAllColor();
            List<Size> listSize = size.getAllSize();
            ImagesDAO image = new ImagesDAO();
            List<Images> listI =image.getAllImagesByshopProductId(productId);
            ProductLine listInfo = pd.getInfoProductById(productId, accountId);

            List<ProductLine> listP = pd.getQuantityProductById(productId, accountId);
            request.setAttribute("productId", productId);
            request.setAttribute("shop", shop);
            request.setAttribute("listInfo", listInfo);
            request.setAttribute("listP", listP);
            request.setAttribute("listI", listI);

            request.setAttribute("listC", listC);
            request.setAttribute("listS", listSize);

            request.getRequestDispatcher("shopProductDetail.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int productId = Integer.parseInt(request.getParameter("ID"));
        HttpSession session = request.getSession();
        Account user = (Account) session.getAttribute("user");
        String color = request.getParameter("color");
        String size_raw = request.getParameter("size");

        if (user == null) {
            request.getRequestDispatcher("logout").forward(request, response);
            return;
        }

        int accountId = user.getAccountId();
        ProductLineDAO pd = new ProductLineDAO();
        ShopDAO sd = new ShopDAO();
        List<Shop> listS = sd.getShop(accountId);
        Shop shop = listS.get(0);
        ColorDAO c = new ColorDAO();
        SizeDAO size = new SizeDAO();
        List<Color> listC = c.getAllColor();
        List<Size> listSize = size.getAllSize();
        ProductLine listInfo = pd.getInfoProductById(productId, accountId);
        List<ProductLine> listP = new ArrayList<>();
        ImagesDAO image = new ImagesDAO();
        List<Images> listI =image.getAllImagesByshopProductId(productId);

        try {
            if (color != null && !color.isEmpty() && size_raw != null && !size_raw.isEmpty()) {
                int colorId = Integer.parseInt(color);
                int sizeId = Integer.parseInt(size_raw);
                listP = pd.getQuantityProductByColorAndSize(productId, accountId, colorId, sizeId);
            } else if (color != null && !color.isEmpty()) {
                int colorId = Integer.parseInt(color);
                listP = pd.getQuantityProductByColor(productId, accountId, colorId);
            } else if (size_raw != null && !size_raw.isEmpty()) {
                int sizeId = Integer.parseInt(size_raw);
                listP = pd.getQuantityProductBySize(productId, accountId, sizeId);
            } else {
                listP = pd.getQuantityProductById(productId, accountId);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        request.setAttribute("shop", shop);
        request.setAttribute("listInfo", listInfo);
        request.setAttribute("listP", listP);

        request.setAttribute("listC", listC);
        request.setAttribute("listS", listSize);
         request.setAttribute("listI", listI);

        request.getRequestDispatcher("shopProductDetail.jsp").forward(request, response);
    }

   

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
