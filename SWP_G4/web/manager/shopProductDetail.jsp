<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html>
<html>
<head>
    <title>Product Detail</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://cdn.tailwindcss.com"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <style>
        .product-image {
            width: 300px;
            height: auto;
        }
        .product-info {
            margin-left: 20px;
        }
        .product-description,
        .product-brand,
        .product-category {
            margin-top: 20px;
            font-size: 16px;
        }
        .back-link {
            margin-top: 20px;
            display: inline-block;
            background-color: orange;
            color: white;
            padding: 10px 20px;
            border-radius: 5px;
            text-decoration: none;
        }
        .back-link:hover {
            background-color: #ff7f00;
        }
        .product-details-table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
            border: 2px solid orange;
        }
        .product-details-table th,
        .product-details-table td {
            border: 1px solid orange;
            padding: 8px;
            text-align: left;
        }
        .product-details-table th {
            background-color: #f2f2f2;
            font-weight: bold;
        }
        .divider {
            height: 2px;
            background-color: orange;
            margin: 20px 0;
        }
        .product-section {
            margin-bottom: 20px;
        }
        .form-group-inline {
            display: flex;
            align-items: center;
            margin-bottom: 10px;
        }
        .form-group-inline label {
            margin-right: 10px;
            font-weight: bold;
        }
        .form-group-inline select,
        .form-group-inline button {
            margin-right: 10px;
        }
        .related-images {
            display: flex;
            flex-wrap: wrap;
            margin-top: 20px;
        }
        .related-image {
            width: 100px;
            height: 100px;
            margin-right: 10px;
            margin-bottom: 10px;
            cursor: pointer;
            border: 2px solid #ddd;
            border-radius: 5px;
        }
        .related-image img {
            width: 100%;
            height: 100%;
            object-fit: cover;
        }
          .discount-btn {
            background-color: #4CAF50;
            color: white;
            border: none;
            padding: 10px 20px;
            font-size: 16px;
            border-radius: 5px;
            cursor: pointer;
            text-decoration: none;
        }
        .discount-btn:hover {
            background-color: #45a049;
        }
    </style>
    <script>
        function showImage(src) {
            document.getElementById('mainImage').src = src;
        }
    </script>
</head>
<body class="bg-gray-100">
    <div class="container mx-auto mt-10">
        <div class="flex">
            <jsp:include page="../common/menu.jsp"></jsp:include>
            <div class="w-full bg-white p-6 rounded shadow ml-6">
                <h1 class="text-2xl font-bold mb-6">Thông tin Sản Phẩm</h1>
               
                <c:set value="${requestScope.listInfo}" var="product"/>
                <div class="flex product-section">
                    <img id="mainImage" src="${product.image}" alt="${product.title}" class="product-image">
                    <div class="product-info">
                        <h2 class="text-xl font-semibold">${product.title}</h2>
                        <p class="text-lg text-gray-600">Gía:  <fmt:formatNumber value="${product.price}"/> VND</p>
                        <div class="product-description">
                            <p>Loại Sản Phẩm: ${product.productLineName}</p>
                        </div>
                        <div class="product-description">
                            <p>Mô Tả: ${product.description}</p>
                        </div>
                        <div class="product-brand">
                            <p>Hãng: ${product.brandName}</p>
                        </div>
                        <div class="product-category">
                            <p>Thể Loại: ${product.categoryName}</p>
                        </div>
                        <div class="related-images">
                            <c:forEach items="${listI}" var="image">
                                <div class="related-image" onclick="showImage('${image.imageLink}')">
                                    <img src="${image.imageLink}" alt="Related Image">
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>

                <div class="divider"></div>

                <h2 class="text-xl font-semibold mt-6">Số Lượng</h2>

                <form method="post" action="shopProductDetail" class="mb-6">
                    <input type="hidden" name="ID" value="${param.ID}"/> <!-- Ensure productId is passed -->
                    <div class="form-group-inline">
                        <label for="color">Màu:</label>
                        <select name="color" class="p-2 border rounded" aria-label="Default select example">
                            <option value="" disabled selected hidden></option>
                            <c:forEach items="${listC}" var="color">
                                <option value="${color.colorId}">${color.colorValue}</option>
                            </c:forEach>
                        </select>

                        <label for="size">Kích cỡ:</label>
                        <select name="size" class="p-2 border rounded" aria-label="Default select example">
                            <option value="" disabled selected hidden></option>
                            <c:forEach items="${listS}" var="size">
                                <option value="${size.sizeId}">${size.sizeValue}</option>
                            </c:forEach>
                        </select>

                        <button type="submit" class="bg-orange-500 text-white px-4 py-2 rounded">Tìm Kiếm</button>
                    </div>
                </form>

                <table class="product-details-table">
                    <thead>
                        <tr>
                            <th>Màu</th>
                            <th>Kích Cỡ</th>
                            <th>Số Lượng</th>
                        </tr>
                    </thead>
                    <tbody>
                       <c:choose>
                            <c:when test="${empty listP}">
                                <tr>
                                    <td colspan="3" class="text-center"><h5 style="text-align: center">Sản Phẩm hiện không có hàng</h5></td>
                                </tr>
                            </c:when>
                            <c:otherwise>
                                <c:forEach var="variant" items="${listP}">
                                    <tr>
                                        <td>${variant.color}</td>
                                        <td>${variant.size}</td>
                                        <td>${variant.quantity}</td>
                                    </tr>
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>
                    </tbody>
                </table>

                <div class="divider"></div>

                <a href="productList" class="back-link">Quay Về</a>
            </div>
        </div>
    </div>
</body>
</html>
