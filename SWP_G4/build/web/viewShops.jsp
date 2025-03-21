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
        <title>View Shops</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.min.css">
        <link rel="stylesheet" href="assets\css\base.css">
        <link rel="stylesheet" href="assets\css\main.css">
        <link rel="stylesheet" href="assets/fonts/fontawesome-free-5.15.3-web/css/all.min.css">
        <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;700&display=swap" rel="stylesheet">
        <style>
            .product-detail {
                display: flex;
                padding: 20px;
                border: 1px solid #ddd;
                border-radius: 8px;
                background-color: #fff;
            }
            .product-detail__imageshop {
                max-width: 10%;
                height: auto;
                border-radius: 1px;
            }
            .product-detail__images {
                flex: 1;
                text-align: center;
            }

            .product-detail__image {
                max-width: 100%;
                height: auto;
                border-radius: 8px;
            }

            .product-detail__info {
                flex: 2;
                padding-left: 20px;
            }

            .product-detail__title {
                font-size: 1.5rem;
                margin-bottom: 10px;
            }

            .product-detail__price {
                font-size: 1.5rem;
                color: #ff4500;
                margin-bottom: 20px;
            }

            .product-detail__description {
                font-size: 1rem;
                color: #666;
                margin-bottom: 20px;
            }

            .product-detail__actions {
                display: flex;
                gap: 10px;
            }

            .btn {
                padding: 10px 20px;
                border: none;
                border-radius: 4px;
                cursor: pointer;
                font-size: 1rem;
            }

            .btn--primary {
                background-color: #ff4500;
                color: #fff;
            }

            .btn--secondary {
                background-color: #fff;
                color: #ff4500;
                border: 1px solid #ff4500;
            }

            .select-container {
                margin-bottom: 20px;
            }

            .select-label {
                font-size: 1rem;
                margin-right: 10px;
            }

            .select-dropdown {
                padding: 10px;
                border: 1px solid #ddd;
                border-radius: 4px;
                font-size: 1rem;
            }

            .quantity-container {
                margin-bottom: 20px;
            }

            .quantity-label {
                font-size: 1rem;
                margin-right: 10px;
            }

            .quantity-input {
                width: 60px;
                padding: 10px;
                border: 1px solid #ddd;
                border-radius: 4px;
                font-size: 1rem;
                text-align: center;
            }

            /* New styles for color and size buttons */
            .color-button, .size-button {
                padding: 10px 20px;
                font-size: 1.25rem;
                border-radius: 6px;
                cursor: pointer;
                margin: 5px;
            }

            .color-button {
                border: 1px solid #ff4500;
                color: #ff4500;
                background-color: #fff;
            }

            .size-button {
                border: 1px solid #333;
                color: #333;
                background-color: #fff;
            }

            .color-button:hover, .size-button:hover {
                background-color: #ff4500;
                color: #fff;
            }
            .shop-detail {
                display: flex;
                flex-direction: column;
                border: 1px solid #e0e0e0;
                border-radius: 8px;
                overflow: hidden;
                background-color: #ffffff;
                width: auto;
                max-width: 100%; /* Adjust width as needed */
                margin: 0 auto;
            }

            .shop-detail__header {
                display: flex;
                align-items: center;
                padding: 16px;
                background-color: #f5f5f5;
            }

            .shop-detail__actions {
                display: flex;
                align-items: center;
                text-decoration: none;
            }

            .shop-detail__imageshop {
                width: 60px;
                height: 60px;
                border-radius: 50%;
                margin-right: 16px;
            }

            .shop-detail__title {
                font-size: 18px;
                color: #333333;
                margin: 0;
            }

            .shop-detail__info {
                display: flex;
                justify-content: space-between;
                padding: 16px;
            }

            .shop-detail__column {
                display: flex;
                flex-direction: column;
                width: 30%;
            }

            .shop-detail__section {
                display: flex;
                flex-direction: column;
                margin-bottom: 16px;
            }

            .shop-detail__label {
                font-size: 16px;
                color: #666666;
                margin-bottom: 4px;
            }

            .container {
                max-width: 1200px; /* Adjust the max-width as needed */
                margin: 0 auto;
                padding: 20px; /* Optional: add padding for better spacing */
            }
.grid__row {
    display: flex;
    flex-wrap: wrap;
}

.grid-column-3 {
    width: 25%; /* 100% / 4 = 25% */
    padding: 0 10px; /* Adjust padding as needed */
    box-sizing: border-box;
}

.home-product-item {
    display: block;
    background-color: #fff;
    text-decoration: none;
    padding: 10px;
    border-radius: 8px;
    overflow: hidden;
    border: 1px solid #ddd;
    margin-bottom: 20px;
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
               

                    <!-- Sắp xếp , Lọc SP -->
                    <div class="grid__column">
                        <div class="shop-detail">

                            <div class="shop-detail__info">
                                <div class="shop-detail__column">
                                    <a class="shop-detail__actions" href="viewshop?sid=${shop.shopId}">
                                    <img src="${shop.image}" alt="Product Image" class="shop-detail__imageshop">
                                    <h1 class="shop-detail__title">${shop.shopName}</h1>
                                </a>
                            </div>
                            <div class="shop-detail__column">
                                <div class="shop-detail__section">
                                    <span class="shop-detail__label">Sản Phẩm: ${countproduct.numberOfProductLineId}</span>
                                    <!-- Add product details here -->
                                </div>
                                <div class="shop-detail__section">
                                    <span class="shop-detail__label">Đánh Giá: ${countcmt.num_Rating}</span>
                                    <!-- Add review details here -->
                                </div>
                            </div>
                            <div class="shop-detail__column">
                                <div class="shop-detail__section">
                                    <span class="shop-detail__label"></span>
                                    <!-- Add response rate details here -->
                                </div>
                                <div class="shop-detail__section">
                                    <span class="shop-detail__label"></span>
                                    <!-- Add response time details here -->
                                </div>
                            </div>
                            <div class="shop-detail__column">
                                <div class="shop-detail__section">
                                    <span class="shop-detail__label"></span>
                                    <!-- Add join date details here -->
                                </div>
                                <div class="shop-detail__section">
                                    <span class="shop-detail__label"></span>
                                    <!-- Add followers count here -->
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- Sắp xếp . Lọc -->
                                        </div>


                    <div class="home-product">
                        <div class="grid">
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
                                                        <span style="margin-left: 140px" class="home-product-item__price-current">
                                                            ${p.price} $
                                                        </span>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <span style="margin-left: 50px" class="home-product-item__price-current">
                                                            <span style="text-decoration: line-through; margin-left: -30px; margin-right: 35px; color: grey;">
                                                                ${p.price} $
                                                            </span>
                                                            ${p.discountPrice} $
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
                                        <a onclick="changePage(${i})" class="pagination-item__link">${i}</a>
                                    </c:forEach>
                                </li>
                               
                            </ul>
                                    
                        </div>
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
    </script>
</html>
