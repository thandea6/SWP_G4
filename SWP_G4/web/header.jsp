<%-- 
    Document   : header
    Created on : May 20, 2024, 3:52:00 PM
    Author     : VIET HOANG
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="assets\css\base.css">
        <link rel="stylesheet" href="assets\css\main.css">
        <link rel="stylesheet" href="assets/fonts/fontawesome-free-5.15.3-web/css/all.min.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <!-- Begin Header -->
        <header class="header">
            <div class="grid">
                <!-- --------------------------------------------Header Navbar -->
                <nav class="header__navbar">
                    <ul class="header__navbar-list">
                        <li class="header__navbar-item header__navbar-item--get-qr">
                            Vào cửa hàng trên ứng dụng
                            <div class="header_qr">
                                <img src="assets\img\qr_code.png" alt="QR Code" class="header_qr-img">
                                <div class="header_qr-appdownload">
                                    <a href="" class="header__qr-link">
                                        <img src="assets\img\ggplay.png" alt="Google Play" class="header_qr-download-img">
                                    </a>
                                    <a href="" class="header__qr-link">
                                        <img src="assets\img\appstore.png" alt="App Store" class="header_qr-download-img">
                                    </a>
                                </div>
                            </div>
                        </li>
                        <li class="header__navbar-item"><span class="no_cursor">Kết nối</span>
                            <a href="" class="header__navbar-icon-link"><i class="icon_header fab fa-facebook"></i></a>
                            <a href="" class="header__navbar-icon-link"><i class="icon_header fab fa-instagram"></i></a>
                        </li>
                    </ul>
                    <c:if test="${sessionScope.user != null}">
                        <ul class="header__navbar-list">
                           
                           
                            <li class="header__navbar-item header__navbar-user">
                                <c:if test="${requestScope.customer.getImage().length()==0||requestScope.customer.getImage().length()==null}">
                                    <i class="fas fa-user text-gray-400 text-4xl"></i>
                                </c:if>
                                <c:if test="${requestScope.customer.getImage().length() > 0}">
                                    <img class="header__navbar-img" src="${requestScope.customer.getImage()}"/>
                                </c:if>



                                <span class="header__navbar-name no_cursor"> ${sessionScope.user.username}</span>
                                <ul class="header__navbar-user-menu">
                                    <li " class="header__navbar-user-item">
                                        <a style="margin-left:-40px" href="updateuser" class="header__navbar-user-info">Tài khoản của tôi</a>
                                    </li>
                                    <li class="header__navbar-user-item">
                                        <a style="margin-left: -40px" href="changepassword" class="header__navbar-user-info">Thay đổi mật khẩu</a>
                                    </li>
                                    <li class="header__navbar-user-item">
                                        <a style="margin-left:-40px" href="listOrder" class="header__navbar-user-info">Đơn mua</a>
                                    </li>
                                    <li class="header__navbar-user-item">
                                        <a style="margin-left:-40px" href="logout" class="header__navbar-user-info speacial">Đăng xuất</a>
                                    </li>
                                </ul>


                            </li>
                        </c:if>

                        <c:if test="${sessionScope.user == null}">
                            <li class="header__navbar-item header__navbar-user">
                                <img src="assets\img\user.png" alt="" class="header__navbar-img">
                                <a href="SignUpIn.jsp" class="header__navbar-name"> Đăng Nhập</a>
                            </li> 
                        </c:if>
                    </ul>
                </nav>
                <!-- --------------------------------------------End Header Navbar -->


                <!-- -----------------------------------------------Header Search -->
                <div class="header-with-search">
                    <a href="home" class="header__qr-link">
                        <div class="header__logo">
                            <img src="images/logohome.png" style="width: 90px;margin-left: 15px;height: 70px;margin-bottom: 0px;margin-top: 20px;" alt="" class="header__logo-img" onclick="resetFilters()">
                        </div>
                    </a>
               <!--     <form action="searchName" style="width: 100%"> -->
                <form  style="width: 100%">
                        <div class="header__search">
                            <!-- History -->

                            <div class="header__search-input-wrap">
                                <input type="text" class="header__search-input" placeholder="Tìm kiếm sản phẩm" name="key" value="${requestScope.key}">
                                <!--                                                            <div class="header__search-history">
                                                                                                <h3 class="header__search-heading">Lịch sử tìm kiếm</h3>
                                                                                                <ul class="header__search-history-list">
                                                                                                    <li class="header__search-history-item">
                                                                                                        <a href="" class="history-item">Bàn phím cơ</a>
                                                                                                    </li>
                                                                                                    <li class="header__search-history-item">
                                                                                                        <a href="" class="history-item">Chuột gaming</a>
                                                                                                    </li>
                                                                                                </ul>
                                                                                            </div>-->
                            </div>
                            <!--End History  -->
                            <div class="header__search-btn">
                                <button type="submit" class="header__search-icon-btn fas fa-search" style="background-color: #F7452E; border: none;">

                                </button>


                            </div>
                        </div>
                    </form>
                    <%
                       Cookie[] cookies = request.getCookies();
                       String uniqueProductCount = "0";
                       if (cookies != null) {
                           for (Cookie cookie : cookies) {
                               if (cookie.getName().equals("uniqueProductCount")) {
                                   uniqueProductCount = cookie.getValue();
                                   break;
                               }
                           }
                       }
                    %>           
                    <!-- Cart Giỏ Hàng -->
                    <div class="header__cart">
                        <div class="header__cart-wrap">
                            <a class="header__cart-icon fas fa-shopping-cart" href="showcart"></a>
                            <span class="header__cart-notice">
                                <%= uniqueProductCount %>
                            </span>
                        </div>
                    </div>


                </div>
        </header>
        <!-- End Header -->
    </body>

</html>
