<%-- 
    Document   : discount
    Created on : 8 Jul 2024, 20:12:19
    Author     : admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
            .container {
                display: flex;
            }
            .menu-container {
                flex: 1;
            }
            .content-container {
                flex: 3;
                display: flex;
                flex-direction: column;
                background-color: #fff;
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }
            .header {
                text-align: center;
                margin-bottom: 20px;
            }
            .product-section {
                display: flex;
                justify-content: space-between;
                margin-bottom: 20px;
            }
            .product-info {
                flex: 1;
                margin-right: 20px;
            }
            .discount-form {
                flex: 1;
                max-width: 400px;
            }
            .discount-form h2 {
                font-size: 20px;
                margin-bottom: 15px;
            }
            .discount-form label {
                display: block;
                font-weight: bold;
                margin-bottom: 5px;
            }
            .discount-form input {
                width: 100%;
                padding: 8px;
                margin-bottom: 15px;
                border: 1px solid #ddd;
                border-radius: 5px;
            }
            .discount-form button {
                width: 100%;
                padding: 10px;
                background-color: #4CAF50;
                color: #fff;
                border: none;
                border-radius: 5px;
                cursor: pointer;
                font-size: 16px;
            }
            .discount-form button:hover {
                background-color: #45a049;
            }
            .product-image {
                width: 100%;
                height: auto;
                max-width: 300px;
            }
            .product-details {
                margin-bottom: 20px;
            }
            .product-details p {
                margin-bottom: 10px;
                font-size: 16px;
            }
            .back-link {
                margin-right: auto; /* Đẩy link "Back to Product List" sang bên trái */
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
            .discount-table {
                width: 100%;
                border-collapse: collapse;
                margin-top: 20px;
            }
            .discount-table th, .discount-table td {
                border: 1px solid #ddd;
                padding: 8px;
                text-align: center;
            }
            .discount-table th {
                background-color: #f2f2f2;
            }
            .discount-section {
                display: flex;
                flex-direction: column;
                flex: 1;
            }
            input[type="date"] {
                padding: 0.5rem; /* Thêm khoảng cách bên trong */
                border: 1px solid #cbd5e0; /* Màu viền */
                border-radius: 0.25rem; /* Bo tròn góc */
                font-size: 1rem; /* Kích thước chữ */
                color: #4a5568; /* Màu chữ */
                background-color: #fff; /* Màu nền */
                transition: border-color 0.2s, box-shadow 0.2s; /* Hiệu ứng chuyển đổi */
            }

            input[type="date"]:focus {
                border-color: #63b3ed; /* Màu viền khi focus */
                box-shadow: 0 0 0 3px rgba(99, 179, 237, 0.5); /* Hiệu ứng bóng khi focus */
                outline: none; /* Bỏ viền mặc định khi focus */
            }
            .action-links a {
                margin-right: 10px;
                color: orange;
                text-decoration: none;
            }

            .action-links a:hover {
                text-decoration: underline;
            }

        </style>
        <script>
            window.onload = function () {
                // Lấy ngày hôm nay
                var today = new Date();
                var day = String(today.getDate()).padStart(2, '0');
                var month = String(today.getMonth() + 1).padStart(2, '0'); // Tháng bắt đầu từ 0
                var year = today.getFullYear();

                // Định dạng yyyy-mm-dd
                var todayDate = year + '-' + month + '-' + day;

                // Thiết lập giá trị mặc định và giá trị min cho input type="date"
                var endDateInput = document.querySelector('input[name="endDate"]');
                var startDateInput = document.querySelector('input[name="startDate"]');
                startDateInput.value = todayDate;
                startDateInput.min = todayDate;
                endDateInput.min = todayDate;
            };
        </script>
    </head>
    <body class="bg-gray-100">
        <div class="container mx-auto mt-10">
            <div class="menu-container">
                <jsp:include page="../common/menu.jsp"></jsp:include>
                </div>
                <div class="content-container">
                    <div class="header">
                        <h1 class="text-2xl font-bold">Discount</h1>
                        <h1>${requestScope.mess}</h1>
                </div>
                <c:set value="${requestScope.listInfo}" var="product"/>
                <div class="product-section">
                    <div class="product-info">
                        <img id="mainImage" src="${product.image}" alt="${product.title}" class="product-image">
                        <div class="product-details">
                            <h2 class="text-xl font-semibold">${product.title}</h2>
                            <p>Giá: ${product.price} VND</p>
                            <p>Loại Sản Phẩm: ${product.productLineName}</p>
                            <p>Mô Tả: ${product.description}</p>
                            <p>Hãng: ${product.brandName}</p>
                            <p>Thể Loại: ${product.categoryName}</p>
                        </div>
                    </div>
                    <div class="discount-section">
                        <div class="discount-form">
                            <form action="discount" method="post">
                                <input type="hidden" name="productId" value="${requestScope.productId}"/>
                                <input type="hidden" name="price" value="${product.price}"/>
                                <label for="discountValue">Giá trị discount(%):</label>
                                <input type="number" id="discountValue" name="discountValue" required>
                                <label for="endDate">Ngày bắt đầu:</label>
                                <input type="date" id="startDate" name="startDate" required>
                                <label for="endDate">Ngày kết thúc:</label>
                                <input type="date" id="endDate" name="endDate" required>


                                <button type="submit">Cập Nhật</button>
                            </form>
                        </div>
                        <h2 class="text-xl font-semibold" style="color: lightsalmon">${requestScope.mess1}</h2>
                        <h2 class="text-xl font-semibold">Lịch Giảm Giá</h2>
                        <table class="discount-table">
                            <thead>
                                <tr>
                                    <th>STT</th>
                                    <th>Ngày Bắt Đầu</th>
                                    <th>Ngày Kết Thúc</th>
                                    <th>Giá Trị</th>
                                    <th>Khác</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="discount" items="${listD}" varStatus="status">
                                    <tr>
                                        <td>${status.index + 1}</td>
                                        <td>${discount.startDate}</td>
                                        <td>${discount.endDate}</td>
                                        <td>${discount.discountValue} %</td>
                                        <td class="action-links">      
                                            <c:if test="${currentDate >= discount.startDate && currentDate <= discount.endDate}">
                                                Đang áp dụng
                                            </c:if>
                                            <c:if test="${discount.startDate > currentDate}">
                                                <a href="deleteDiscount?id=${discount.discountId}&&productId=${requestScope.productId}" onclick="return confirm('Bạn chắc chắn muốn xóa discount này?')">Xóa</a>
                                            </c:if>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
                <a href="productList" class="back-link">Quay Về</a>
            </div>
        </div>
    </body>
</html>

