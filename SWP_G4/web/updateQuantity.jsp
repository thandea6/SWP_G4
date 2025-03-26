<%-- 
    Document   : updateQuantity
    Created on : 22 Jun 2024, 20:32:41
    Author     : admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Product</title>
        <script src="https://cdn.tailwindcss.com"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
        <style>
            .container {
                display: flex;
                align-items: flex-start;
            }
            .menu {
                width: 250px;
                margin-right: 20px;
            }
            .content {
                flex-grow: 1;
                background-color: white;
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }
            .form-control, .form-select {
                width: 100%;
                padding: 10px;
                margin: 5px 0 10px;
                border: 1px solid #ccc;
                border-radius: 5px;
            }
            .btn {
                padding: 10px 20px;
                border: none;
                border-radius: 5px;
                cursor: pointer;
                margin-right: 10px;
            }
            .btn-default {
                background-color: #ccc;
                color: #333;
            }
            .btn-success {
                background-color: #4CAF50;
                color: white;
            }
            .btn:hover {
                opacity: 0.8;
            }
            .modal-header {
                display: flex;
                justify-content: space-between;
                align-items: center;
                border-bottom: 1px solid #e5e5e5;
                padding-bottom: 10px;
            }
            .modal-title {
                margin: 0;
                font-size: 24px;
            }
            .modal-footer {
                display: flex;
                justify-content: space-between;
                align-items: center;
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

            .header-container {
                display: flex;
                justify-content: space-between;
                align-items: center;
                margin-bottom: 20px;
            }

            .update-images-btn {
                background-color: orange;
                color: white;
                padding: 10px 20px;
                border-radius: 5px;
                text-decoration: none;
                margin-left: 10px; /* Cách nhau một khoảng */
            }

            .update-images-btn:hover {
                background-color: #ff7f00;
            }
            input[type=number]::-webkit-outer-spin-button,
            input[type=number]::-webkit-inner-spin-button {
                -webkit-appearance: none;
                margin: 0;
            }
            input[type=number] {
                -moz-appearance: textfield;
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
            .action-links a {
                margin-right: 10px;
                color: orange;
                text-decoration: none;
            }

            .action-links a:hover {
                text-decoration: underline;
            }


        </style>
        <script type="text/javascript">
            // Hàm để ẩn thông báo sau 4 giây
            function hideMessage() {
                var messageElement = document.getElementById("message");
                if (messageElement) {
                    setTimeout(function () {
                        messageElement.style.display = "none";
                    }, 4000); // 4 giây
                }
            }

            // Gọi hàm hideMessage khi trang đã tải xong
            window.onload = hideMessage;
        </script>
    </head>
    <body class="bg-gray-100">
        <div class="container mx-auto mt-10">
            <div class="menu">
                <jsp:include page="menu.jsp"></jsp:include>
                </div>
                <div class="content">
                    <div class="header-container">
                        <h1 class="text-2xl font-bold mb-6">Cập Nhật Số Lượng</h1>
                    </div>
                <%
                String mess = (String) request.getAttribute("mess");
                if (mess != null) {
                %>
                <p style="color: orange" id="message">${mess}</p>
                <%
                }
                %>

                <form action="updateQuantity" method="post">
                    <input name="action" value="update" type="hidden"/>
                    <input name="productId" value="${requestScope.productId}" type="hidden"/>

                    <div class="form-group">
                        <label>Màu</label>
                        <select name="color" class="form-select" aria-label="Default select example">
                            <option value="" disabled selected hidden></option>
                            <c:forEach items="${listC}" var="color">
                                <option value="${color.colorId}" ${color.colorId == requestScope.colorU ? 'selected' : ''}>${color.colorValue}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="form-group">
                        <label>Kích Cỡ</label>
                        <select name="size" class="form-select" aria-label="Default select example">
                            <option value="" disabled selected hidden></option>
                            <c:forEach items="${listS}" var="size">
                                <option value="${size.sizeId}" ${size.sizeId == requestScope.sizeU ? 'selected' : ''}>${size.sizeValue}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="form-group">
                        <label>Số Lượng</label>
                        <input name="quantity" type="number" class="form-control" value="${requestScope.quantityU}" min="0">
                    </div>

                    <div class="modal-footer">
                        <input type="submit" class="btn btn-success" value="Cập Nhật">
                    </div>
                </form>
                <h2 class="text-xl font-semibold mt-6">Thông tin</h2>

                <form method="post" action="updateQuantity" class="mb-6">
                    <input type="hidden" name="action" value="search"/>
                    <input name="productId" value="${requestScope.productId}" type="hidden"/>
                    <div class="form-group-inline">
                        <label for="color">Màu:</label>
                        <select name="color1" class="p-2 border rounded" aria-label="Default select example">
                            <option value="" disabled selected hidden></option>
                            <c:forEach items="${listC}" var="color">
                                <option value="${color.colorId}" <c:if test="${requestScope.color == color.colorId}">selected</c:if>>${color.colorValue}</option>
                            </c:forEach>
                        </select>

                        <label for="size">Kích cỡ:</label>
                        <select name="size1" class="p-2 border rounded" aria-label="Default select example">
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
                            <th>Action</th>
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
                                        <td class="action-links">
                                            <a href="updateQuantity?action=edit&colorU=${variant.colorId}&sizeU=${variant.sizeId}&quantityU=${variant.quantity}&productId=${requestScope.productId}">Cập Nhật</a>
                                        </td>
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
    </body>
</html>
