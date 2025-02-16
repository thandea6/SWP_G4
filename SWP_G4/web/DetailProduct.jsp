<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>SOS</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.min.css">
        <link rel="stylesheet" href="assets/css/base.css">
        <link rel="stylesheet" href="assets/css/main.css">
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



            .product-detail__image {
                max-width: 100%;

                border-radius: 8px;
            }

            .product-detail__thumbnails {
                display: flex;
                max-width: 100%;
                margin-top: 10px;
            }

            .thumbnail-image {
                width: 100px;
                height:75px;
                margin: 0 5px;
                cursor: pointer;
                border: 2px solid transparent;
                border-radius: 4px;
            }

            .thumbnail-image:hover,
            .thumbnail-image.selected {
                border-color: #ff4500;
            }

            .product-detail__info {
                flex: 2;
                padding-left: 20px;
            }
            .product-detail__imageshop {
                max-width: 10%;
                height: auto;
                border-radius: 1px;
            }


            .product-detail__title {
                font-size: 2rem;
                margin-bottom: 10px;
            }

            .product-detail__price {
                font-size: 1.5rem;
                color: #ff4500;
                margin-bottom: 20px;
            }

            .product-detail__description {
                font-size: 1.7rem;
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
                padding: 10px;
                border: 1px solid #ccc;
                background-color: #f9f9f9;
                cursor: pointer;
            }

            .color-button:hover, .size-button:hover {
                background-color: #e0e0e0;
            }

            .selected {
                background-color: #007bff;
                color: white;
                font-weight: bold;
                border-color: #0056b3;
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
            .quantity-message-container {
                font-size: 1.4rem; /* Increase font size */
                color: #333; /* Adjust color if needed */
                margin-top: 10px; /* Add margin for spacing */
            }
            .text-gray-600 {
                color: #4b5563; /* Example gray color */
                font-size: 1.5rem; /* Increase font size as needed */
                margin-top: 10px;
                margin-bottom: 10px;
            }

            .hidden {
                display: none;
            }
            .custom-container {
                margin-top: 40px;
                margin-bottom: 40px;
            }

            .custom-bg {
                background-color: white;
                padding: 20px;
                padding-left: 40px;
                padding-right: 40px;
            }

            .add-comment {
                margin-top: 20px;
                margin-bottom: 20px;
            }

            .custom-avatar {
                margin-right: 10px;
            }

            .custom-input {
                margin-right: 15px;
            }

            .custom-button {
                background-color: #007bff;
                color: white;
                border: none;
            }

            .comment-user {
                display: flex;
                align-items: center;
                margin-top: 10px;
                margin-bottom: 10px;
                font-size: 1.5rem;
            }

            .comment-user img {
                width: 60px;
                height: 60px;
                border-radius: 50%;
                margin-right: 16px;
            }

            .comment-text {
                font-size: 1.5rem; /* Increased font size */
                margin-top: 5px;
            }

            .comment-time {
                display: flex;
                align-items: center;
                font-size: 1.1rem; /* Slightly larger font size */
                color: #999; /* Lighter color for the timestamp */
                margin-left: 10px;
            }

            .comment-user .dot {
                color: #666; /* Slightly faded color for the name */
                font-size: 1.5rem; /* Larger font size */
                margin-left: 10px;
            }

            .ml-2 {
                margin-left: 10px;
            }


            .reply-section {
                display: flex;
                flex-direction: row;
                align-items: center;
                margin-top: 10px;
            }

            .voting-icons {
                display: flex;
                flex-direction: row;
                align-items: center;
            }

            .voting-icons i {
                cursor: pointer;
            }

            .voting-icons span {
                margin-left: 10px;
            }

            .voting-icons .dot {
                margin-left: 10px;
            }

            .voting-icons h6 {
                margin-left: 10px;
                margin-top: 5px;
                cursor: pointer;
            }
            .row {
                display: flex;
                align-items: flex-start;
                margin-bottom: 15px;
            }

            .col-2 {
                flex: 0 0 70px;
                max-width: 70px;
                padding-right: 15px;
            }

            .col-10 {
                flex: 0 0 calc(100% - 70px);
                max-width: calc(100% - 70px);
            }

            img.rounded-circle {
                border-radius: 50%;
                margin-top: 5px;
            }

            .comment-box {
                background-color: #f9f9f9;
                padding: 15px;
                border-radius: 8px;
                box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            }

            .comment-box h4 {
                margin-bottom: 10px;
                color: #333;
            }

            .rating {
                display: flex;
                margin-bottom: 10px;
                flex-direction: row-reverse;
                margin-left: -4px;
                float: left;

            }

            .rating>input {
                display: none
            }

            .rating>label {
                position: relative;
                width: 19px;
                font-size: 25px;
                color: #ff0000;
                cursor: pointer;
            }

            .rating>label::before {
                content: "\2605";
                position: absolute;
                opacity: 0
            }

            .rating>label:hover:before,
            .rating>label:hover~label:before {
                opacity: 1 !important
            }

            .rating>input:checked~label:before {
                opacity: 1
            }

            .rating:hover>input:checked~label:before {
                opacity: 0.4
            }

            .comment-area textarea {
                width: 100%;
                border: 1px solid #ddd;
                border-radius: 5px;
                padding: 10px;
                font-size: 14px;
                resize: vertical;
            }

            .comment-btns {
                margin-top: 10px;
            }

            .comment-btns .row {
                display: flex;
                justify-content: space-between;
            }

            .comment-btns .btn {
                padding: 8px 12px;
                font-size: 14px;
                border-radius: 5px;
            }

            .comment-btns .btn-success {
                background-color: #28a745;
                border-color: #28a745;
            }

            .comment-btns .btn-success:hover {
                background-color: #218838;
                border-color: #1e7e34;
            }
            .star {
                font-size: 2em; /* Kích thước của các ngôi sao */
                color: red;    /* Màu vàng cho các ngôi sao đầy */
            }
            .empty-star {
                font-size: 2em; /* Kích thước của các ngôi sao */
                color: lightgray; /* Màu xám nhạt cho các ngôi sao rỗng */
            }
            .home-filter {
                display: flex;
                font-size: 1.1em; /* Increase overall font size */
                justify-content: flex-start;
                flex-direction: column;
            }

            .stars {
                display: flex;
                margin-top: 10px; /* Add some space between the rating text and stars */
                font-size: 1.1em; /* Increase font size for the stars */
                justify-content: flex-start;

            }
            .rating-text {
                margin: 0;
                padding: 0;
                font-size: 1.6em; /* Increase font size for the rating text */

                color: red;
            }
            .dropdown {
                position: relative;
                display: inline-block;
            }

            .dropdown-content {
                display: none;
                position: absolute;
                background-color: #f9f9f9;
                min-width: 160px;
                box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
                z-index: 1;
            }

            .dropdown-content button {
                color: black;
                padding: 12px 16px;
                text-decoration: none;
                display: block;
                background: none;
                border: none;
                width: 100%;
                text-align: left;
            }

            .dropdown-content button:hover {
                background-color: #f1f1f1;
            }

        </style>
    </head>
    <body>
        <header class="header">
            <div class="grid">
                <!-- Header Navbar -->
                <jsp:include page="header.jsp"></jsp:include>
                </div>
            </header>
            <!-- End Header -->

            <!-- Begin Container -->
            <div class="container">
                <div class="grid">
                    <!-- Danh Mục Sản Phẩm -->
                <jsp:include page="left.jsp"></jsp:include>

                    <!-- Sắp xếp, Lọc SP -->
                    <div class="grid__column-8">
                        <!-- Sắp xếp, Lọc -->
                        <div class="home-filter"></div>

                        <!-- DMSP -->
                    <c:set var="data" value="${requestScope.data}"/>
                    <h3 style="color: red">${requestScope.err}</h3>
                    <h3 style="color: green;margin-left: 10px">${requestScope.ms}</h3>  
                    <div class="product-detail">

                        <div class="product-detail__images" style="width: 470px;">
                            <img src="${requestScope.shopProduct.image}" alt="Product Image" class="product-detail__image" id="mainImage" style="width: 100%">
                            <div class="product-detail__thumbnails" id="thumbnailContainer">
                                <button class="thumbnail-button" onclick="showPreviousThumbnail()">&lt;</button>
                                <c:forEach items="${requestScope.images}" var="image" varStatus="loop">
                                    <img src="${image.imageLink}" alt="Thumbnail Image" class="thumbnail-image${loop.index < 5 ? '' : ' hidden'}">
                                </c:forEach>
                                <button class="thumbnail-button" onclick="showNextThumbnail()">&gt;</button>
                            </div>
                        </div>




                        <div class="product-detail__info">
                            <h1 class="product-detail__title">${requestScope.shopProduct.title}</h1>
                            <c:choose>
                                <c:when test="${requestScope.dis.promotionalPrice > 0}">
                                   
                                    <p class="product-detail__price"> <fmt:formatNumber value="${requestScope.dis.promotionalPrice}"/> VND</p>
                                </c:when>
                                <c:otherwise>
                                   
                                    <p class="product-detail__price"> <fmt:formatNumber value="${requestScope.shopProduct.price}"/> VND</p>
                                </c:otherwise>
                            </c:choose>

                            <p class="product-detail__description">${requestScope.shopProduct.description}</p>

                            <form action="detail" method="post" id="productForm">
                                <input type="hidden" name="sid" value="${requestScope.shop.shopId}">
                                <input type="hidden" name="pId" value="${requestScope.shopProduct.getId()}">

                                <c:set var="selectedColor" value="${param.color}" />
                                <c:set var="selectedSize" value="${param.size}" />

                                <div class="flex items-center mt-2 space-x-2" id="colorOptions">
                                    <div class="text-gray-600">Color</div>
                                    <c:forEach items="${color}" var="color">
                                        <button type="button" value="${color.getColorId()}" name="colorId" class="color-button ${selectedColor == color.getColorId() ? 'selected' : ''}" onclick="selectColor('${color.getColorId()}', this)">${color.colorValue}</button>
                                    </c:forEach>
                                </div>

                                <div class="select-container">
                                    <div class="text-gray-600">Size</div>
                                    <div class="flex items-center mt-2 space-x-2" id="sizeOptions">
                                        <c:forEach items="${size}" var="size">
                                            <button type="button" value="${size.getSizeId()}" name="sizeId" class="size-button ${selectedSize == size.getSizeId() ? 'selected' : ''}" onclick="selectSize('${size.getSizeId()}', this)">${size.sizeValue}</button>
                                        </c:forEach>
                                    </div>
                                </div>


                                <!-- Select Quantity -->
                                <div class="quantity-container">
                                    <label for="quantity-input" class="quantity-label">Số lượng:</label>
                                    <c:choose>
                                        <c:when test="${quantity.quantity == null }">
                                            <input type="number" id="quantity-input" name="quantity" class="quantity-input" min="0" value="1" disabled>

                                        </c:when>
                                        <c:otherwise>
                                            <input type="number" id="quantity-input" name="quantity" class="quantity-input" min="1" max="${quantity.quantity}" value="1">
                                        </c:otherwise>
                                    </c:choose>

                                    <input type="hidden" id="availableQuantity" value="${quantity.quantity}">
                                    <p id="quantity-message" class="quantity-message-container">
                                        <c:choose>
                                            <c:when test="${sumquantity.sumQuantity != null && quantity.colorId == null && quantity.sizeId == null && quantity.quantity != null}">
                                                ${sumquantity.sumQuantity} sản phẩm có sẵn
                                            </c:when>
                                            <c:when test="${quantity.quantity == null || quantity.quantity == 0 }">
                                                Hết hàng
                                            </c:when>
                                            <c:when test="${quantity.quantity > 0 }">
                                                ${quantity.quantity} sản phẩm có sẵn
                                            </c:when>
                                        </c:choose>
                                    </p>


                                </div>

                                <input type="hidden" name="color" id="selectedColor" >
                                <input type="hidden" name="size" id="selectedSize" >

                            </form>


                            <c:set var="wishlist" value="${requestScope.wishlist}" />
                            <c:set var="promotionalPrice" value="${requestScope.dis.promotionalPrice}" />
                            <div class="product-detail__actions">
                                <c:choose>
                                    <c:when test="${quantity == null || quantity.quantity == 0 || promotionalPrice == 0}">
                                        <!-- Buttons when quantity is null or 0 -->
                                        <button style="background: gray" class="btn btn--primary" disabled>Thêm vào giỏ hàng</button>
                                       

                                        <!-- Conditional button based on wishlist existence -->
                                        <c:choose>
                                            <c:when test="${wishlist == null}">
                                                <button class="btn btn--primary" onclick="addToFavorites('${requestScope.shopProduct.id}', '${requestScope.shop.shopId}')">Thêm vào yêu thích</button>
                                            </c:when>
                                            <c:otherwise>
                                                <button class="btn btn--primary" onclick="removeFromFavorites('${requestScope.shopProduct.id}', '${requestScope.shop.shopId}')">Xóa bỏ yêu thích</button>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:when>
                                    <c:otherwise>
                                        <!-- Buttons when quantity is not null and not 0 -->
                                        <c:choose>
                                            <c:when test="${promotionalPrice >= 0 || promotionalPrice == null}">
                                                <button class="btn btn--primary" onclick="addtocart('${quantity.productItemId}', '${requestScope.shopProduct.id}', '${requestScope.shop.shopId}', '${promotionalPrice}')">Thêm vào giỏ hàng</button>
                                            </c:when>
                                            <c:otherwise>
                                                <button class="btn btn--primary" onclick="addtocart('${quantity.productItemId}', '${requestScope.shopProduct.id}', '${requestScope.shop.shopId}', '${quantity.price}')">Thêm vào giỏ hàng</button>
                                            </c:otherwise>
                                        </c:choose>
                                        

                                        <!-- Conditional button based on wishlist existence -->
                                        <c:choose>
                                            <c:when test="${wishlist == null}">
                                                <button class="btn btn--primary" onclick="addToFavorites('${requestScope.shopProduct.id}', '${requestScope.shop.shopId}')">Thêm vào yêu thích</button>
                                            </c:when>
                                            <c:otherwise>
                                                <button class="btn btn--primary" onclick="removeFromFavorites('${requestScope.shopProduct.id}', '${requestScope.shop.shopId}')">Xóa bỏ yêu thích</button>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:otherwise>
                                </c:choose>
                            </div>





                            <div class="product-detail__actions">
                                <br>
                            </div>        
                        </div>
                    </div>

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

                    <div class="shop-detail">
                        <p class="product-detail__description">ĐÁNH GIÁ SẢN PHẨM</p>
                        <div class="comments-list mt-2">
                            <div class="home-filter">
                                <div class="rating-text">
                                    ${rating.average_starRating} trên 5 
                                </div>
                                <div class="stars">
                                    <c:set var="rating" value="${rating.average_starRating}" />
                                    <c:forEach var="i" begin="1" end="${rating}">
                                        <span class="star">★</span>
                                    </c:forEach>
                                    <c:forEach var="i" begin="${rating + 1}" end="5">
                                        <span class="empty-star">☆</span>
                                    </c:forEach>
                                </div>
                            </div>
                            <div class="d-flex justify-content-center row">
                                <div class="d-flex flex-column col-md-8">
                                    <div class="comment-section custom-bg p-2 px-4">


                                        <c:forEach var="c" items="${comment}">
                                            <div class="comment-user">
                                                <div class="flex items-center mb-6">
                                                    <c:if test="${c.user.image.length()==0 || c.user.image.length()==null}">
                                                        <i class="fas fa-user text-gray-400 text-4xl"></i>
                                                    </c:if>
                                                    <c:if test="${c.user.image.length() > 0}">
                                                        <img src="${c.user.image}" alt="User Image">
                                                    </c:if>
                                                </div>
                                                <div class="mb-1 mb-2">${c.user.fullName}</div>
                                                <div class="comment-time">${c.created_at} |  Phân loại hàng: ${c.colorValue}, ${c.sizeValue}</div>
<!-- Biểu tượng ba chấm dọc -->
                                                <c:if test="${c.user.accountId == sessionScope.user.accountId}">
                                                    <div class="dropdown">
                                                        <i class="fas fa-ellipsis-v" onclick="toggleEditButton('${c.ratingId}')"></i>
                                                        <div id="editButton-${c.ratingId}" class="dropdown-content" style="display: none;">
                                                            <button class="btn btn-primary btn-edit" onclick="toggleEditForm('${c.ratingId}')">Chỉnh sửa</button>
                                                        </div>
                                                    </div>
                                                </c:if>

                                            </div>

                                            <div>
                                                <c:set var="rating" value="${c.starRating}" />
                                                <c:forEach var="i" begin="1" end="${rating}">
                                                    <span class="star">★</span>
                                                </c:forEach>
                                                <c:forEach var="i" begin="${rating + 1}" end="5">
                                                    <span class="empty-star">☆</span>
                                                </c:forEach>
                                            </div>

                                            <div class="comment-text">
                                                <span>${c.content}</span>
                                            </div>
                                            <!-- Form chỉnh sửa -->
                                            <div id="editForm-${c.ratingId}" class="edit-form" style="display: none;">



                                                <form action="updatefeedback" method="get">
                                                    <input type="hidden" name="commentId" value="${c.ratingId}">
                                                    <input type="hidden" name="ratingId" value="${c.ratingId}">
                                                    <input type="hidden" name="sid" value="${requestScope.shop.shopId}">
                                                    <input type="hidden" name="pId" value="${requestScope.shopProduct.getId()}">
                                                    <div class="comment-box ml-2">
                                                        <div class="rating">
                                                            <input type="radio" name="rating" value="5" id="5-${c.ratingId}"<c:if test="${rating == 5}"> checked</c:if>><label for="5-${c.ratingId}">☆</label>
                                                            <input type="radio" name="rating" value="4" id="4-${c.ratingId}"<c:if test="${rating == 4}"> checked</c:if>><label for="4-${c.ratingId}">☆</label>
                                                            <input type="radio" name="rating" value="3" id="3-${c.ratingId}"<c:if test="${rating == 3}"> checked</c:if>><label for="3-${c.ratingId}">☆</label>
                                                            <input type="radio" name="rating" value="2" id="2-${c.ratingId}"<c:if test="${rating == 2}"> checked</c:if>><label for="2-${c.ratingId}">☆</label>
                                                            <input type="radio" name="rating" value="1" id="1-${c.ratingId}"<c:if test="${rating == 1}"> checked</c:if>><label for="1-${c.ratingId}">☆</label>
                                                            </div>
                                                            <div class="comment-area">
                                                                <textarea name="comment" class="form-control" placeholder="Viết đánh giá của bạn" rows="4">${c.content}</textarea>
                                                        </div>
                                                        <div class="comment-btns mt-2">
                                                            <div class="row">
                                                                <div class="col-6">
                                                                    <div class="flex items-center mt-4">
                                                                        <button type="button" class="bg-red-500 text-white px-4 py-2 rounded mr-2" onclick="toggleEditForm('${c.ratingId}')">Hủy</button>
                                                                        <button type="submit" class="bg-red-500 text-white px-4 py-2 rounded">Gửi <i class="fa fa-long-arrow-right ml-1"></i></button>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </form>



                                            </div>

                                        </c:forEach>
                                        <!--                                        <div class="reply-section">
                                                                                    <div class="d-flex flex-row align-items-center voting-icons">
                                                                                        <i class="fa fa-sort-up fa-2x mt-3 hit-voting"></i>
                                                                                        <i class="fa fa-sort-down fa-2x mb-3 hit-voting"></i>
                                                                                        <span class="ml-2">25</span>
                                                                                        <span class="dot ml-2"></span>
                                                                                        <h6 class="ml-2 mt-1">Reply</h6>
                                                                                    </div>
                                                                                </div>-->
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>



                    <p class="product-detail__description">CÁC SẢN PHẨM KHÁC CỦA SHOP</p>
                    <div class="grid__row">
                        <c:forEach items="${data}" var="p">                          
                            <div class="grid-column-2">
                                <a class="home-product-item" href="detail?pid=${p.id}&sid=${p.shop.shopId}">
                                    <div class="home-product-item__img"  style="background-image: url('${p.image}'); width: 250px"></div>
                                    <div class="home-product-item__heading">
                                        <!--                                                <h4 class="home-product-item-MSP">1</h4>-->
                                        <h4 class="home-product-item__name" style="    white-space: nowrap;
                                            overflow: hidden;
                                            text-overflow: ellipsis;
                                            width: 230px;">${p.title}</h4>
                                    </div>
                                    <div class="home-product-item__price">
                                        <span style="margin-left: 130px" class="home-product-item__price-current">${p.price} $</span>

                                    </div>

                                    <div class="shop-detail__section">
                                        <span class="shop-detail__label"></span>
                                        <!-- Add followers count here -->
                                    </div>


                                </a>
                            </div>
                        </c:forEach>


                    </div>

                </div>
            </div>
        </div>
    </div>
    <!-- End Container -->

    <!-- Begin Footer -->
    <jsp:include page="footer.jsp"></jsp:include>
        <!-- End Footer -->

        <script>
            let isSubmitting = false;
            document.addEventListener("DOMContentLoaded", function () {
                // Kiểm tra xem có dữ liệu đã lưu trong Local Storage không
                const selectedColor = localStorage.getItem('selectedColor');
                const selectedSize = localStorage.getItem('selectedSize');
                // Nếu có, cập nhật các nút màu và kích cỡ đã chọn
                if (selectedColor && selectedSize) {
                    document.getElementById('selectedColor').value = selectedColor;
                    document.getElementById('selectedSize').value = selectedSize;
                    updateSelectedButtons('color-button', selectedColor);
                    updateSelectedButtons('size-button', selectedSize);
                } else {
                    // Nếu không có dữ liệu trong Local Storage, xóa lựa chọn của các nút
                    updateSelectedButtons('color-button', null);
                    updateSelectedButtons('size-button', null);
                    // Display total quantity message
                    document.getElementById('quantity-message').innerText = '${sumquantity.sumQuantity} sản phẩm có sẵn';
                }
            });
            function selectColor(colorId) {
                const selectedColor = localStorage.getItem('selectedColor');
                if (selectedColor === colorId) {
                    // Nếu đã chọn màu này, hủy chọn
                    localStorage.removeItem('selectedColor');
                    document.getElementById('selectedColor').value = null;
                    updateSelectedButtons('color-button', null);
                    // Display total quantity message
                    document.getElementById('quantity-message').innerText = '${sumquantity.sumQuantity} sản phẩm có sẵn';
                } else {
                    // Lưu giá trị màu đã chọn vào Local Storage
                    localStorage.setItem('selectedColor', colorId);
                    document.getElementById('selectedColor').value = colorId;
                    updateSelectedButtons('color-button', colorId);
                }
                checkAndSubmit();
            }

            function selectSize(sizeId) {
                const selectedSize = localStorage.getItem('selectedSize');
                if (selectedSize === sizeId) {
                    // Nếu đã chọn kích cỡ này, hủy chọn
                    localStorage.removeItem('selectedSize');
                    document.getElementById('selectedSize').value = null;
                    updateSelectedButtons('size-button', null);
                    // Display total quantity message
                    document.getElementById('quantity-message').innerText = '${sumquantity.sumQuantity} sản phẩm có sẵn';
                } else {
                    // Lưu giá trị kích cỡ đã chọn vào Local Storage
                    localStorage.setItem('selectedSize', sizeId);
                    document.getElementById('selectedSize').value = sizeId;
                    updateSelectedButtons('size-button', sizeId);
                }
                checkAndSubmit();
            }

            function updateSelectedButtons(buttonClass, selectedValue) {
                // Xóa lớp 'selected' của tất cả các nút và thêm vào nút đã chọn (nếu có)
                const buttons = document.getElementsByClassName(buttonClass);
                for (let button of buttons) {
                    button.classList.remove('selected');
                    if (button.getAttribute('value') === selectedValue) {
                        button.classList.add('selected');
                    }
                }
            }

            function checkAndSubmit() {
                const selectedColor = localStorage.getItem('selectedColor');
                const selectedSize = localStorage.getItem('selectedSize');
                if (selectedColor && selectedSize) {
                    isSubmitting = true;
                    document.getElementById('productForm').submit();
                }
            }

            // Hàm xóa toàn bộ lựa chọn
            function resetSelections() {
                if (!isSubmitting) {
                    localStorage.removeItem('selectedColor');
                    localStorage.removeItem('selectedSize');
                    updateSelectedButtons('color-button', null);
                    updateSelectedButtons('size-button', null);
                    document.getElementById('selectedColor').value = "";
                    document.getElementById('selectedSize').value = "";
                    // Display total quantity message
                    document.getElementById('quantity-message').innerText = '${sumquantity.sumQuantity} sản phẩm có sẵn';
                }
            }

            // Gọi hàm resetSelections khi người dùng tải lại trang
            window.addEventListener('beforeunload', resetSelections);
            let currentIndex = 0;
            function showNextThumbnail() {
                const thumbnails = document.querySelectorAll('.thumbnail-image');
                // Check if there are enough thumbnails to move forward
                if (currentIndex + 5 < thumbnails.length) {
                    // Hide the first thumbnail in the current set of 5 thumbnails
                    thumbnails[currentIndex].classList.add('hidden');
                    // Show the next thumbnail at the end of the current set of 5 thumbnails
                    thumbnails[currentIndex + 5].classList.remove('hidden');
                    currentIndex++;
                }
            }



            function showPreviousThumbnail() {
                const thumbnails = document.querySelectorAll('.thumbnail-image');
                // Check if there are enough thumbnails to move backward
                if (currentIndex > 0) {
                    // Move all visible thumbnails one position to the right
                    for (let i = 0; i < 5; i++) {
                        const prevIndex = (currentIndex + i) % thumbnails.length;
                        thumbnails[prevIndex].classList.add('hidden');
                        thumbnails[currentIndex + i - 1].classList.remove('hidden');
                    }
                    currentIndex--;
                }
            }
            document.addEventListener("DOMContentLoaded", function () {
                // Bắt sự kiện click trên mỗi thumbnail
                const thumbnails = document.querySelectorAll('.thumbnail-image');
                thumbnails.forEach((thumbnail, index) => {
                    thumbnail.addEventListener('mouseover', function () {
                        // Cập nhật src của ảnh lớn
                        const mainImage = document.getElementById('mainImage');
                        mainImage.src = thumbnail.src;
                    });
                });
            });
            function addtocart(productItemId, shopProductId, shopId) {
                var quantity = document.getElementById('quantity-input').value;
                var availableQuantity = document.getElementById('availableQuantity').value;
                if (quantity <= 0) {
                    alert('Quantity must be greater than zero.');
                    return;
                }

                if (${quantity.quantity == null || quantity.quantity == 0}) {
                    // Hiển thị thông báo cho người dùng
                    alert('Không thể thêm vào giỏ hàng vì số lượng không hợp lệ.');
                    return; // Ngừng hàm addtocart
                }
                // Construct the URL to add the product to the cart
                var newUrl = 'addtocart?productItemId=' + productItemId + '&ShopProductId=' + shopProductId + '&quantity=' + quantity + '&sid=' + shopId;
                // Redirect to the URL to add the product to the cart
                window.location.href = newUrl;
            }

            function buyNow() {
                var color = document.getElementById('selectedColor').value;
                var size = document.getElementById('selectedSize').value;
                if (!color || !size) {
                    alert('Please select both color and size.');
                    return;
                }

                var form = document.getElementById('productForm');
                form.action = "buynow";
                form.submit();
            }


            function addToFavorites(shopProductId, shopId) {
                if (shopProductId) {
                    var newUrl = 'wishlist?pid=' + shopProductId + "&sid=" + shopId;
                    window.location.href = newUrl;
                } else {
                    alert("Không thể lấy được shopproductid");
                }
            }

            function removeFromFavorites(shopProductId, shopId) {
                if (shopProductId) {
                    var newUrl = 'unwishlist?pid=' + shopProductId + "&sid=" + shopId;
                    window.location.href = newUrl;
                } else {
                    alert("Không thể lấy được shopproductid");
                }
            }
              function toggleEditForm(commentId) {
                var form = document.getElementById('editForm-' + commentId);
                if (form.style.display === 'none' || form.style.display === '') {
                    form.style.display = 'block';
                } else {
                    form.style.display = 'none';
                }
            }
            function toggleEditButton(commentId) {
                var button = document.getElementById('editButton-' + commentId);
                if (button.style.display === 'none' || button.style.display === '') {
                    button.style.display = 'block';
                } else {
                    button.style.display = 'none';
                }
            }

    </script>

</body>
</html>
