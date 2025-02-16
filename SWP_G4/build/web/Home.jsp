<%-- 
    Document   : Home
    Created on : 19 May 2024, 19:11:06
    Author     : hoang
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Home</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.min.css">
        <link rel="stylesheet" href="assets\css\base.css">
        <link rel="stylesheet" href="assets\css\main.css">
        <link rel="stylesheet" href="assets/fonts/fontawesome-free-5.15.3-web/css/all.min.css">
        <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;700&display=swap" rel="stylesheet">
        <style>
            .pagination-item__link {
                background-color: white;
                padding: 5px 10px;
                margin: 0 2px;
                text-decoration: none;
                color: black;
                border-radius: 3px;
            }

            .pagination-item__link.active {
                background-color: red;
                color: white;
            }
            .star {

                color: gold;    /* Màu vàng cho các ngôi sao đầy */
            }
        </style>
    </head>
    <body>
        <header class="header">
            <div class="grid">
                <!-- --------------------------------------------Header Navbar -->
                <jsp:include page="header.jsp"></jsp:include>
                </div>

            </header>
            <!-- End Header -->

            <!-- Begin Container -->
            <div class="container">
                <div class="grid">
                    <!-- Danh Mục Sản Phẩm -->
                <jsp:include page="left.jsp"></jsp:include>

                    <!-- Sắp xếp , Lọc SP -->
                    <div class="grid__column-8">
                        <!-- Sắp xếp . Lọc -->
                        <div class="home-filter">
                            <span class="home-filter__label">Sắp xếp theo</span>
                            <form id="sortForm" action="sort">
                                <div class="select-input">
                                    <span class="select-input__label">
                                        Giá
                                        <i class="select-input__label-icon fas fa-chevron-down"></i>
                                    </span>
                                    <ul class="select-input__list">
                                        <li class="select-input__item">
                                            <a href="#" class="select-input__link" onclick="sortProducts('desc')">Giá: Cao đến thấp</a>
                                        </li>
                                        <li class="select-input__item">
                                            <a href="#" class="select-input__link" onclick="sortProducts('asc')">Giá: Thấp đến cao</a>
                                        </li>
                                    </ul>
                                </div>
                            </form>
                        </div>

                        <!-- DMSP -->                                   
                    <c:set var="data" value="${requestScope.data}"/>
                    <div class="home-product">
                        <div class="grid">
                            <h3 style="color: green;margin-left: 10px">${requestScope.ms}</h3> 
                            <div class="grid__row">
                                        
                                <c:forEach items="${data}" var="p">                          
                                    <div class="grid-column-2">
                                        <a class="home-product-item" href="detail?pid=${p.id}&sid=${p.shop.shopId}">
                                            <div class="home-product-item__img" style="background-image: url('${p.image}'); width: 250px"></div>
                                            <div class="home-product-item__heading">
                                                <h4 class="home-product-item__name" style="white-space: nowrap; overflow: hidden; text-overflow: ellipsis; width: 230px;">${p.title}</h4>
                                            </div>
                                            <div class="home-product-item__price">
                                                <c:choose>
                                                    <c:when test="${p.discountPrice == 0}">
                                                        <span style="margin-left: 110px" class="home-product-item__price-current">
                                                           <fmt:formatNumber value="${p.price}"/> VND
                                                        </span>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <span style="margin-left: 30px" class="home-product-item__price-current">
                                                            <span style="text-decoration: line-through; margin-left: -25px; margin-right: 15px; color: grey;">
                                                                <fmt:formatNumber value="${p.price}"/>VND
                                                            </span>
                                                            <fmt:formatNumber value=" ${p.discountPrice}"/> VND
                                                        </span>
                                                    </c:otherwise>
                                                </c:choose>
                                            </div>
                                            <c:set var="wishlist" value="${requestScope.listD}"/>
                                            <div class="home-product-item__action">
                                                <c:forEach var="item" items="${wishlist}">
                                                    <c:if test="${item.shopProductId == p.id}">
                                                        <span class="home-product-item__like">
                                                            <i class="fas fa-heart"></i>
                                                        </span>
                                                    </c:if>
                                                </c:forEach>
                                                <div class="home-product-item__rating">
                                                    <c:set var="rating" value="${p.averageRating}" />
                                                    <c:forEach var="i" begin="1" end="${rating}">
                                                        <span class="fas start-gold fa-star"></span>
                                                    </c:forEach>
                                                    <c:forEach var="i" begin="${rating + 1}" end="5">
                                                        <span class="fas fa-star"></span>
                                                    </c:forEach>
                                                </div>

                                                <div class="home-product-item__sold">
                                                    ${p.totalSold} đã bán
                                                </div>
                                            </div>
                                            <div class="home-product-item__origin">
                                                <span class="home-product-item__brand">${p.shop.shopName}</span>
                                                <span class="home-product-item__origin-name">${p.shop.address}</span>
                                            </div>
                                            <c:forEach var="item" items="${wishlist}">
                                                <c:if test="${item.shopProductId == p.id}">
                                                    <div class="home-product-item__favourite">                                                 
                                                        Yêu thích                                                    
                                                    </div>
                                                </c:if>
                                            </c:forEach>
                                            <c:if test="${p.discountValue!=0}">
                                                <div class="home-product-item__sale-off">
                                                    <span class="home-product-item__percent">${p.discountValue}%</span>
                                                    <span class="home-product-item__label">Giảm</span>
                                                </div>
                                            </c:if>
                                        </a>
                                    </div>
                                </c:forEach>

                            </div>

                            <!-- Số trang -->
                            <ul class="pagination">
                                <li class="pagination-item">
                                    <c:set var="page" value="${requestScope.page}" />
                                    <c:forEach begin="${1}" end="${requestScope.num}" var="i">
                                      <!--  <a onclick="changePage(${i})" class="pagination-item__link">${i}</a> -->
                                        <a class="pagination-item__link">${i}</a>
                                    </c:forEach>
                                </li>
                            </ul>


                        </div>
                    </div>
                </div>
            </div>
            <!-- End Container -->

            <!-- Begin Footer -->
            <jsp:include page="footer.jsp"></jsp:include>
            <!-- End Footer -->
    </body>
    <script>
        function changePage(currentPagge) {
            var urlParams = new URLSearchParams(window.location.search);
            urlParams.set('page', currentPagge); // Đặt tham số sắp xếp vào URL
            var newUrl = 'pageServlet' + '?' + urlParams.toString();
            window.location.href = newUrl; // Chuyển hướng đến URL mới
        }
        
        function sortProducts(order) {
            var urlParams = new URLSearchParams(window.location.search);
            urlParams.set('sort', order); // Đặt tham số sắp xếp vào URL
            var newUrl = 'sort' + '?' + urlParams.toString();
            window.location.href = newUrl; // Chuyển hướng đến URL mới
        }
        document.addEventListener("DOMContentLoaded", function () {
            var paginationItems = document.querySelectorAll(".pagination-item__link");

            paginationItems.forEach(function (item) {
                item.addEventListener("click", function (event) {
                    // Xóa lớp active từ tất cả các nút
                    paginationItems.forEach(function (el) {
                        el.classList.remove("active");
                    });
                    // Thêm lớp active vào nút được click
                    this.classList.add("active");
                });
            });

            // Lấy trang hiện tại từ URL và áp dụng lớp active
            var currentPage = window.location.href.match(/page=(\d+)/);
            if (currentPage) {
                var pageNumber = currentPage[1];
                paginationItems.forEach(function (item) {
                    if (item.textContent.trim() === pageNumber) {
                        item.classList.add("active");
                    }
                });
            }
        });
        document.addEventListener("DOMContentLoaded", function () {
            var prevPageLink = document.getElementById('prevPage');
            var nextPageLink = document.getElementById('nextPage');

            prevPageLink.addEventListener('click', function (event) {
                event.preventDefault(); // Ngăn chặn hành vi mặc định của thẻ <a>

                var currentPage = getCurrentPage();
                var prevPage = currentPage - 1;
                if (prevPage >= 1) {
                    navigateToPage(prevPage);
                }
            });

            nextPageLink.addEventListener('click', function (event) {
                event.preventDefault(); // Ngăn chặn hành vi mặc định của thẻ <a>

                var currentPage = getCurrentPage();
                var nextPage = currentPage + 1;
                var lastPage = getLastPage(); // Hàm để lấy trang cuối cùng, bạn cần tự định nghĩa

                if (nextPage <= lastPage) {
                    navigateToPage(nextPage);
                }
            });

            // Hàm để lấy số trang hiện tại từ URL
            function getCurrentPage() {
                var urlParams = new URLSearchParams(window.location.search);
                return parseInt(urlParams.get('page')) || 1; // Mặc định là trang 1 nếu không có tham số page
            }

            // Hàm để chuyển đến trang cụ thể
            function navigateToPage(page) {
                window.location.href = 'home?page=' + page;
            }

            // Hàm để lấy trang cuối cùng, bạn cần tự định nghĩa tùy vào logic phân trang của bạn
            function getLastPage() {
                return 10; // Ví dụ: trang cuối cùng là trang số 10
            }
        });
    </script>
</html>
