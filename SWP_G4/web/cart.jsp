

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="description" content="Ogani Template">
        <meta name="keywords" content="Ogani, unica, creative, html">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Shopping Cart</title>

        <!-- Google Font -->
        <link href="https://fonts.googleapis.com/css2?family=Cairo:wght@200;300;400;600;900&display=swap" rel="stylesheet">

        <!-- Css Styles -->
        <link rel="stylesheet" href="assets/css/bootstrap.min.css" type="text/css">
        <link rel="stylesheet" href="assets/css/font-awesome.min.css" type="text/css">
        <link rel="stylesheet" href="assets/css/elegant-icons.css" type="text/css">
        <link rel="stylesheet" href="assets/css/nice-select.css" type="text/css">
        <link rel="stylesheet" href="assets/css/jquery-ui.min.css" type="text/css">
        <link rel="stylesheet" href="assets/css/owl.carousel.min.css" type="text/css">
        <link rel="stylesheet" href="assets/css/slicknav.min.css" type="text/css">
        <link rel="stylesheet" href="assets/css/style.css" type="text/css">
        <<link rel="stylesheet" href="assets\css\base.css">
        <link rel="stylesheet" href="assets\css\main.css">
        <link rel="stylesheet" href="assets/fonts/fontawesome-free-5.15.3-web/css/all.min.css">
        <style>
            img {
                max-width: 100px; /* Thiết lập chiều rộng tối đa */
                max-height: 100px; /* Thiết lập chiều cao tối đa */
            }
        </style>
    </head>

    <body>
        <header style="margin-top: -15px" class="header">
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
                            <li class="header__navbar-item">
                              
                            <li class="header__navbar-item header__navbar-user">
                                <c:if test="${requestScope.customer.getImage().length()==0||requestScope.customer.getImage().length()==null}">
                                    <i class="fas fa-user text-gray-400 text-4xl"></i>
                                </c:if>
                                <c:if test="${requestScope.customer.getImage().length() > 0}">
                                    <img class="header__navbar-img" src="${requestScope.customer.getImage()}"/>
                                </c:if>



                                <span class="header__navbar-name no_cursor"> ${sessionScope.user.username}</span>
                                <ul style="margin-top: -5px" class="header__navbar-user-menu">
                                    <li class="header__navbar-user-item">
                                        <a  href="updateuser" class="header__navbar-user-info">Tài khoản của tôi</a>
                                    </li>
                                    <li class="header__navbar-user-item">
                                        <a  href="changepassword" class="header__navbar-user-info">Thay đổi mật khẩu</a>
                                    </li>
                                    <li class="header__navbar-user-item">
                                        <a  href="listOrder" class="header__navbar-user-info">Đơn mua</a>
                                    </li>
                                    <li class="header__navbar-user-item">
                                        <a  href="logout" class="header__navbar-user-info speacial">Đăng xuất</a>
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
                            <img src="images/logohome1.png" style="width: 90px;margin-left: 15px;height: 70px;margin-bottom: 0px;margin-top: 20px;" alt="" class="header__logo-img" onclick="resetFilters()">
                        </div>
                    </a>
                    <form action="searchName" style="width: 100%">
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
                            <i>
                                <a class="header__cart-icon fas fa-shopping-cart" href="showcart"></a>
                                <span class="header__cart-notice">
                                    <%= uniqueProductCount %>
                                </span>
                            </i>
                        </div>
                    </div>

                </div>
        </header>

        <!-- Breadcrumb Section Begin -->
        <section class="breadcrumb-section set-bg"  data-setbg="">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12 text-center">
                        <div class="breadcrumb__text">
                            <h2 style="color: red">Giỏ Hàng</h2>
                            <div class="breadcrumb__option">
                                <a style="color: red" href="home">Trang chủ</a>
                                <span style="color: red">Giỏ Hàng</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- Breadcrumb Section End -->

        <!-- Shoping Cart Section Begin -->
        <section class="shopping-cart spad">
            <h5 style="color: red;margin-left: 400px;font-weight: 800">${requestScope.err}</h5>
            <div class="container">

                <div class="row">

                    <div class="col-lg-12">

                        <div class="shopping__cart__table">
                            <table style="width: 1300px;height: 245px;">
                                <thead>
                                    <tr>
                                        <th>Lựa chọn</th> <!-- Thêm cột Select -->
                                        <th>STT</th>
                                        <th class="shopping__product" style="width: 130px;" >Ảnh sản phẩm</th>
                                        <th>Tên cửa hàng</th>
                                        <th>Kích thước</th>
                                        <th>Màu sắc</th>
                                        <th>Giá</th>
                                        <th>Số lượng</th>
                                        <th>Tổng tiền</th>
                                        <th></th>
                                    </tr>
                                </thead>
                                <tbody>
                                <tbody>
                                    <c:set var="cart" value="${requestScope.cart}"/>
                                    <c:forEach items="${cart.items}" var="item" varStatus="status">
                                    <form action="processcart" method="post"  id="form">
                                        <tr>
                                            <td class="shopping__cart__select">
                                                <input type="checkbox" name="selectedItems" value="${item.productItem.productItemId}">

                                            </td>
                                            <td style="font-size: medium">${status.index + 1}</td>
                                            <td class="shopping__cart__item">
                                                <img src="${item.productItem.shopProduct.image}" alt="ProductImage">
                                            </td>
                                            <td class="shopping__cart__price" style="font-size: medium">
                                                <a href="viewshop?sid=${item.productItem.shop.shopId}" title="View Shop">
                                                    ${item.productItem.shop.shopName}
                                                </a>
                                            </td>

                                            <td class="shopping__cart__price" style="font-size: medium">
                                                ${item.productItem.sizeValue}
                                            </td>
                                            <td class="shopping__cart__price" style="font-size: medium">
                                                ${item.productItem.colorValue}
                                            </td>
                                            <td class="shopping__cart__price" style="font-size: medium">
                                                <c:choose>
                                                    <c:when test="${item.promotionalPrice > 0}">
                                                        <fmt:formatNumber value="${item.promotionalPrice}"/>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <fmt:formatNumber value="${item.price}"/>
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>


                                            <td class="shopping__cart__quantity" style="font-size: medium">
                                                <div class="quantity">
                                                    <button style="width: 25px;height: 25px"><a href="processcart?num=-1&id=${item.productItem.productItemId}">-</a></button>
                                                    ${item.quantity}
                                                    <button style="width: 25px;height: 25px"><a  href="processcart?num=1&id=${item.productItem.productItemId}">+</a></button>
                                                </div>
                                            </td>
                                            <td class="shopping__cart__total" style="font-size: medium">
                                                <c:set var="totalPrice" value="${item.promotionalPrice >= 0 ? item.promotionalPrice : item.productItem.shopProduct.price}" />
                                                <fmt:formatNumber value="${totalPrice * item.quantity}" var="formattedTotal"/>
                                                <span>${formattedTotal}</span>
                                            </td>

                                    </form>
                                    <form action="removecart" method="post">
                                        <td>
                                            <input type="hidden" name="ProductItemId" value="${item.productItem.productItemId}">
                                            <input type="submit" name="removeItemId" value="Xóa">
                                        </td>
                                        </tr>
                                    </c:forEach>
                                    <tr>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td>
                                            <input style="margin-right: 40px" type="submit" name="removeAllItems" value="Xóa tất cả">
                                        </td>

                                    </tr>
                                </form>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-lg-6">
                        <div class="shoping__cart__btns">
                            <a href="home" class="btn btn-primary cart-btn">Trở về trang chủ</a>
                        </div>
                    </div>
                    <div class="col-lg-6">
                        <div class="shoping__checkout">
                            <form action="checkout" method="post" id="checkoutForm">
                                <!-- Các nội dung khác của form -->

                                <!-- Input hidden để lưu danh sách các checkbox được chọn -->
                                <input type="hidden" id="selectedItemsInput" name="selectedItems" value="${item.productItem.productItemId}">

                                <!-- Button submit -->
                                <input type="submit" class="primary-btn" value="                   Thanh toán                "/>
                            </form>

                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- Shoping Cart Section End -->



        <!-- Js Plugins -->
        <script src="assets/js/jquery-3.3.1.min.js"></script>
        <script src="assets/js/bootstrap.min.js"></script>
        <script src="assets/js/jquery.nice-select.min.js"></script>
        <script src="assets/js/jquery-ui.min.js"></script>
        <script src="assets/js/jquery.slicknav.js"></script>
        <script src="assets/js/mixitup.min.js"></script>
        <script src="assets/js/owl.carousel.min.js"></script>
        <script src="assets/js/main.js"></script>
        <!-- Thêm mã JavaScript -->

        <script>

                                function removeProduct(productId) {
                                    // Gửi productId đến servlet để xóa sản phẩm
                                    // Sử dụng Ajax
                                    var xhr = new XMLHttpRequest();
                                    xhr.open('POST', 'processcart'); // Thay 'cart' bằng URL của servlet
                                    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
                                    xhr.send('productItemID=' + productId + '&quantity=0');
                                    xhr.onload = function () {
                                        if (xhr.status === 200) {
                                            // Reload trang để cập nhật giỏ hàng sau khi xóa sản phẩm
                                            location.reload();
                                        }
                                    };
                                }
                                function removeAllProducts() {
                                    // Gửi yêu cầu xóa toàn bộ giỏ hàng đến servlet
                                    // Sử dụng Ajax
                                    var xhr = new XMLHttpRequest();
                                    xhr.open('POST', 'processcart'); // Thay 'cart' bằng URL của servlet
                                    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
                                    xhr.send('removeAll=true');
                                    xhr.onload = function () {
                                        if (xhr.status === 200) {
                                            // Reload trang để cập nhật giỏ hàng sau khi xóa toàn bộ sản phẩm
                                            location.reload();
                                        }
                                    };
                                }


        </script>

        <script>
            document.querySelectorAll('input.select-item').forEach(item => {
                item.addEventListener('change', function () {
                    let total = 0;
                    document.querySelectorAll('input.select-item:checked').forEach(checkedItem => {
                        let row = checkedItem.closest('tr');
                        let price = parseFloat(row.querySelector('.shopping__cart__total span').textContent);
                        total += price;
                    });
                    document.getElementById('selectedTotalPrice').textContent = total.toFixed(2) + " VND";
                });
            });
        </script>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

        <script type="text/javascript">
            // Function to update selected items in the hidden input field
            function updateSelectedItems() {
                var selectedItems = []; // Array to store selected item IDs

                // Loop through each checkbox and store its value if checked
                document.querySelectorAll('input[name="selectedItems"]:checked').forEach(function (checkbox) {
                    selectedItems.push(checkbox.value); // Add checked item ID to array
                });

                // Update the value of the hidden input field with selected item IDs
                document.getElementById('selectedItemsInput').value = selectedItems.join(',');
            }

// Event listener to update selected items before form submission
            document.getElementById('checkoutForm').addEventListener('submit', function (event) {
                updateSelectedItems(); // Update selected items before submitting the form
            });

        </script>

        <script>
            document.addEventListener('DOMContentLoaded', function () {
                const checkboxes = document.querySelectorAll('input[type="checkbox"][name="selectedItems"]');
                checkboxes.forEach(function (checkbox) {
                    checkbox.checked = true;
                });
            });
        </script>







    </body>
</html>
