/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import Mail.MailSender;
import dal.OrderDAO;
import dal.OrderDetailDAO;
import dal.ProductItemDAO;
import dal.UserDAO;
import dal.VoucherDAO;
import jakarta.mail.MessagingException;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import static java.lang.Double.sum;
import static java.lang.Integer.sum;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.Cart;
import model.Order;
import model.OrderDetail;
import model.ProductItem;
import model.User;
import model.Voucher;
import java.text.DecimalFormat;

/**
 *
 * @author admin
 */
@WebServlet(name = "CheckOutServlet", urlPatterns = {"/checkout"})
public class CheckOutServlet extends HttpServlet {

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
            out.println("<title>Servlet CheckOutServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CheckOutServlet at " + request.getContextPath() + "</h1>");
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
        UserDAO userdao = new UserDAO();
        List<ProductItem> list = d.getAllProductItem();
        HttpSession session = request.getSession();
        String name1 = "";
        String phone1 = "";
        String address1 = "";
        String name = "";
        String phone = "";
        String address = "";
        String bankname = "";
        boolean check;
        if ("00".equals(request.getParameter("vnp_TransactionStatus"))) {
            name1 = (String) session.getAttribute("name1");
            phone1 = (String) session.getAttribute("phone1");
            address1 = (String) session.getAttribute("address1");
            name = (String) session.getAttribute("name");
            phone = (String) session.getAttribute("phone");
            address = (String) session.getAttribute("address");
            bankname = "true";
            check = false;

        } else if ("01".equals(request.getParameter("vnp_TransactionStatus")) || "02".equals(request.getParameter("vnp_TransactionStatus"))
                || "05".equals(request.getParameter("vnp_TransactionStatus")) || "04".equals(request.getParameter("vnp_TransactionStatus"))
                || "05".equals(request.getParameter("vnp_TransactionStatus")) || "06".equals(request.getParameter("vnp_TransactionStatus"))
                || "09".equals(request.getParameter("vnp_TransactionStatus"))
                || "07".equals(request.getParameter("vnp_TransactionStatus"))) {
            request.setAttribute("buysuccess", "Failed Purchase");
            System.out.println("buy error");
            check = false;
            request.getRequestDispatcher("vnpay_return.jsp").forward(request, response);
            return;
        } else {
            name1 = request.getParameter("name1").trim();
            phone1 = request.getParameter("phone1").trim();
            address1 = request.getParameter("address1").trim();
            name = request.getParameter("name").trim();
            phone = request.getParameter("phone").trim();
            address = request.getParameter("address").trim();
            bankname = request.getParameter("bankCode");
            check = true;
        }

        String selectItems = request.getParameter("selectedItems");
        System.out.println(selectItems);

        Account a = (Account) session.getAttribute("user");
        Cookie[] arr = request.getCookies();
        String txt = "";
        String txt1 = "";

        if (arr != null) {
            for (Cookie o : arr) {
                if (o.getName().equals("cart")) {
                    txt += o.getValue();
                }
            }
        }

        if (arr != null) {
            for (Cookie o : arr) {
                if (o.getName().equals("cart1")) {
                    txt1 += o.getValue();
                }
            }
        }
        Cart cart = new Cart(txt1, list);
        if ("false".equals(bankname)) {
            request.setAttribute("cart1", cart);
            session.setAttribute("phone1", phone1);
            session.setAttribute("address1", address1);
            session.setAttribute("name1", name1);
            session.setAttribute("name", name);
            session.setAttribute("phone", phone);
            session.setAttribute("address", address);
            request.getRequestDispatcher("vnpay_pay.jsp").forward(request, response);
        } else {
            User u = userdao.getUserByAccountId(a.getAccountId());
            d.addOrder(u, cart, name1, address1, phone1);
            String newCartTxt = cart.updateCartAfterPurchase(txt, txt1.split("/")); // Update cart after purchase
            System.out.println(newCartTxt);

// Create and add the new 'cart' cookie
            Cookie c = new Cookie("cart", newCartTxt);
            c.setMaxAge(2 * 24 * 60 * 60); // 2 days

            response.addCookie(c);

            Set<String> uniqueProducts = new HashSet<>();
            if (!newCartTxt.isEmpty()) {
                String[] items = newCartTxt.split("/");
                for (String item : items) {
                    String[] parts = item.split(":");
                    uniqueProducts.add(parts[0]);
                }
            }

            Cookie uniqueProductCountCookie = new Cookie("uniqueProductCount", String.valueOf(uniqueProducts.size()));
            uniqueProductCountCookie.setMaxAge(2 * 24 * 60 * 60); // 2 days
            response.addCookie(uniqueProductCountCookie);

            OrderDAO orderDao = new OrderDAO();
            OrderDetailDAO orderDetailDao = new OrderDetailDAO();
            Order order = new Order();
            String email = a.getEmail();

            int orderId = orderDao.getLatestOrderIdByUserId(u.getUserId());
            
            List<OrderDetail> orderdetail = orderDetailDao.getOrderDetailByOrderId(orderId);
            String emailContent = generateEmailContent(orderId, name1, email, phone1, address1, name, phone, address, orderdetail, check);
            try {
                MailSender.sendEmail(email, "", emailContent);
            } catch (MessagingException ex) {
                Logger.getLogger(CheckOutServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (check == true) {
                request.setAttribute("ms", "Mua hàng thành công");
                System.out.println("buy done");
                request.getRequestDispatcher("home").forward(request, response);
                request.getRequestDispatcher("header.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("vnpay_return.jsp").forward(request, response);
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
        UserDAO userdao = new UserDAO();
        String id_raw = request.getParameter("selectedItems");
        System.out.println(id_raw);
        System.out.println("hihi");
        int[] id_list = null;

        // Kiá»ƒm tra náº¿u id_raw lÃ  null hoáº·c rá»—ng
        if (id_raw == null || id_raw.isEmpty()) {
            request.setAttribute("err", "Bạn phải chọn sản phẩm để Mua");
            request.getRequestDispatcher("showcart").forward(request, response);
            return;
        }

        String[] idlistraw = id_raw.split(",");

        for (int i = 0; i < idlistraw.length; i++) {
            System.out.println(idlistraw[i]);
        }

        // Chuyá»ƒn Ä‘á»•i chuá»—i thÃ nh máº£ng sá»‘ nguyÃªn
        try {
            id_list = new int[idlistraw.length];
            for (int i = 0; i < idlistraw.length; i++) {
                id_list[i] = Integer.parseInt(idlistraw[i].trim()); // Sá»­ dá»¥ng trim() Ä‘á»ƒ loáº¡i bá»� khoáº£ng tráº¯ng thá»«a
                System.out.print("a" + id_list[i]);
            }
        } catch (NumberFormatException e) {
            // Xá»­ lÃ½ ngoáº¡i lá»‡ NumberFormatException (vÃ­ dá»¥: in ra thÃ´ng bÃ¡o lá»—i)
            System.err.println("NumberFormatException: " + e.getMessage());
            // Forward hoáº·c redirect vá»� trang lá»—i
            request.setAttribute("err", "Các mục đã chọn không hợp lệ");
            request.getRequestDispatcher("showcart").forward(request, response);
        }

        List<ProductItem> list = d.getProductItemByCheckBoxId(id_list);
        Cookie[] arr = request.getCookies();
        String txt = "";
        if (arr != null) {
            for (Cookie o : arr) {
                if (o.getName().equals("cart")) {
                    txt += o.getValue();
                }
            }
        }
        System.out.println(txt);
        HttpSession session = request.getSession();
        Account a = (Account) session.getAttribute("user");
        UserDAO ud = new UserDAO();
        if (a == null) {
            response.sendRedirect("login");
        } else {

            if (a.getRoleId() != 2) {
                String ms = "You must log in with a user account";
                request.setAttribute("err", ms);
                request.getRequestDispatcher("login").forward(request, response);
                return;
            }

            if (arr != null) {
                for (Cookie o : arr) {
                    if (o.getName().equals("cart1")) {
                        o.setMaxAge(0);
                        response.addCookie(o);
                    }
                }
            }
            User u = ud.getUserByAccountId(a.getAccountId());
            request.setAttribute("customer", u);
            Cart cart1 = new Cart(txt, list);
            String newCartTxt = cart1.saveSelectedCartItems(txt, id_list); 
            System.out.println(newCartTxt);
            Cookie c1 = new Cookie("cart1", newCartTxt);
            c1.setMaxAge(2 * 24 * 60 * 60); // 2 days
            response.addCookie(c1);
            VoucherDAO vd = new VoucherDAO();
            List<Voucher> listV = vd.getAllVoucherByUserId(u.getUserId());
            request.setAttribute("listVoucher", listV);
            request.setAttribute("cart1", cart1);
            request.setAttribute("selectedItems", id_raw);
            request.setAttribute("name", u.getFullName());
            request.setAttribute("address", u.getAddress());
            request.setAttribute("phone", a.getPhone());
            request.getRequestDispatcher("checkout.jsp").forward(request, response);
        }

    }

    private String generateEmailContent(int order_id, String receiver_name, String buyer_email, String receiver_phone, String receiver_address, String buyer_name, String buyer_phone, String buyer_address, List<OrderDetail> list, boolean check) {
        DecimalFormat decimalFormat = new DecimalFormat("#,##0");
        String content = "<!DOCTYPE html>\n"
                + "<html lang=\"en\">\n"
                + "<head>\n"
                + "    <meta charset=\"UTF-8\">\n"
                + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                + "    <title>Order Confirmation</title>\n"
                + "    <style>\n"
                + "        body {\n"
                + "            font-family: 'Arial', sans-serif;\n"
                + "            line-height: 1.6;\n"
                + "            color: #333;\n"
                + "            background-color: #f9f9f9;\n"
                + "            margin: 0;\n"
                + "            padding: 0;\n"
                + "        }\n"
                + "        .container {\n"
                + "            width: 80%;\n"
                + "            margin: 20px auto;\n"
                + "            background-color: #fff;\n"
                + "            padding: 20px;\n"
                + "            border-radius: 5px;\n"
                + "            box-shadow: 0 0 10px rgba(0,0,0,0.1);\n"
                + "        }\n"
                + "        .header, .footer {\n"
                + "            background-color: #d9534f;\n"
                + "            color: #fff;\n"
                + "            padding: 10px;\n"
                + "            text-align: center;\n"
                + "            border-radius: 5px 5px 0 0;\n"
                + "        }\n"
                + "        .header h2, .footer p {\n"
                + "            margin: 0;\n"
                + "        }\n"
                + "        .content {\n"
                + "            padding: 20px;\n"
                + "        }\n"
                + "        table {\n"
                + "            width: 100%;\n"
                + "            border-collapse: collapse;\n"
                + "            margin-top: 20px;\n"
                + "        }\n"
                + "        th, td {\n"
                + "            border: 1px solid #ddd;\n"
                + "            padding: 8px;\n"
                + "        }\n"
                + "        th {\n"
                + "            background-color: #f2f2f2;\n"
                + "        }\n"
                + "        .total-summary {\n"
                + "            font-size: 1.2em;\n"
                + "            color: #d9534f;\n"
                + "            margin-top: 20px;\n"
                + "        }\n"
                + "        .footer {\n"
                + "            margin-top: 20px;\n"
                + "            background-color: #d9534f;\n"
                + "            color: #fff;\n"
                + "            border-radius: 0 0 5px 5px;\n"
                + "        }\n"
                + "    </style>\n"
                + "</head>\n"
                + "<body>\n"
                + "    <div class=\"container\">\n"
                + "        <div class=\"header\">\n"
                + "            <h2>Order Confirmation #" + order_id + "</h2>\n"
                + "        </div>\n"
                + "        <div class=\"content\">\n"
                + "            <p>Dear " + receiver_name + ",</p>\n"
                + "            <p>Thank you for your order at ShoeOnlineShops. We are pleased to confirm your order with the following details:</p>\n"
                + "\n"
                + "            <h3>Order Details</h3>\n"
                + "            <p>\n"
                + "                <strong>Order ID:</strong> " + order_id + "<br>\n"
                + "                <strong>Order Date:</strong> " + list.get(0).getOrderDate() + "\n"
                + "            </p>\n"
                + "\n"
                + "            <h3>Buyer Information</h3>\n"
                + "            <p>\n"
                + "                <strong>Name:</strong> " + buyer_name + "<br>\n"
                + "                <strong>Email:</strong> " + buyer_email + "<br>\n"
                + "                <strong>Phone:</strong> " + buyer_phone + "<br>\n"
                + "                <strong>Address:</strong> " + buyer_address + "\n"
                + "            </p>\n"
                + "\n"
                + "            <h3>Receiver Information</h3>\n"
                + "            <p>\n"
                + "                <strong>Name:</strong> " + receiver_name + "<br>\n"
                + "                <strong>Phone:</strong> " + receiver_phone + "<br>\n"
                + "                <strong>Address:</strong> " + receiver_address + "\n"
                + "            </p>\n"
                + "\n"
                + "            <h3>Ordered Items</h3>\n"
                + "            <table>\n"
                + "                <thead>\n"
                + "                    <tr>\n"
                + "                        <th>Product Name</th>\n"
                + "                        <th>Shop Name</th>\n"
                + "                        <th>Size</th>\n"
                + "                        <th>Color</th>\n"
                + "                        <th>Quantity</th>\n"
                + "                        <th>Unit Price</th>\n"
                + "                        <th>Total Price</th>\n"
                + "                    </tr>\n"
                + "                </thead>\n"
                + "                <tbody>\n";

        double sum = 0.0; // Initialize the sum
        // Add rows for each product
        for (OrderDetail item : list) {
            double itemTotalPrice = item.getPrice();
            sum += itemTotalPrice; // Update the sum

            content += "                    <tr>\n"
                    + "                        <td>" + item.getTitle() + "</td>\n"
                    + "                        <td>" + item.getShopName() + "</td>\n"
                    + "                        <td>" + item.getSizeValue() + "</td>\n"
                    + "                        <td>" + item.getColorValue() + "</td>\n"
                    + "                        <td>" + item.getQuantity() + "</td>\n"
                    + "                        <td>" + decimalFormat.format(item.getPrice()/item.getQuantity()) + "</td>\n"
                    + "                        <td>" + decimalFormat.format(itemTotalPrice) + "</td>\n"
                    + "                    </tr>\n";
        }

        content += "                </tbody>\n"
                + "            </table>\n"
                + "\n"
                + "            <h3 class=\"total-summary\">Total: " + decimalFormat.format(sum) + "</h3>\n"
                + "\n"
                + "            <h3>Payment Method: <span  style=\"color: red;display: inline-block;\">" + (check ? "Payment on delivery" : "Online payment") + "</span></h3>\n"
                + "\n"
                + "            <p>Your order will be processed and delivered as soon as possible.</p>\n"
                + "\n"
                + "            <p>Once again, thank you for shopping at ShoeOnlineShops!</p>\n"
                + "        </div>\n"
                + "        <div class=\"footer\">\n"
                + "            <p>ShoeOnlineShops<br>\n"
                + "            FPT Hoa Lac<br>\n"
                + "            Phone: 0962 698 931<br>\n"
                + "            Email: lytienkhoi1598@gmail.com</p>\n"
                + "        </div>\n"
                + "    </div>\n"
                + "</body>\n"
                + "</html>";

        return content;
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
