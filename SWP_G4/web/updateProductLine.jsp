<%-- 
    Document   : addProductLine
    Created on : 28 Jun 2024, 23:46:09
    Author     : admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Add Product</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
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
            .modal-footer {
                display: flex;
                justify-content: space-between;
                border-top: 1px solid #e5e5e5;
                padding-top: 10px;
            }
            .message {
                color: red;
                margin-bottom: 10px;
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
                <jsp:include page="./common/menu.jsp"></jsp:include>
                </div>
                <div class="content">
                    <h1 class="text-2xl font-bold mb-6">Cập Nhật Dòng Sản Phẩm</h1>
                    <p style="color: orange" id="message">${mess}</p>
                <form action="updateProductLine" method="post">
                    <input type="hidden" value="${requestScope.id}" name="id"/>
                    <div class="form-group">
                        <label>Tên</label>
                        <input name="name" value="${productLine.productLineName}" type="text" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label>Thể Loại</label>
                        <select name="category" class="form-select" required>
                            <option value="" disabled selected>Chọn Thể Loại</option>
                            <c:forEach items="${listC}" var="c">
                                <option value="${c.categoryId}" <c:if test="${c.categoryId == productLine.categoryId}">selected</c:if>>${c.categoryName}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label>Thương Hiệu</label>
                        <select name="brand" class="form-select" required>
                            <option value="" disabled selected>Chọn Thương Hiệu</option>
                            <c:forEach items="${listB}" var="b">
                                <option value="${b.brandId}" <c:if test="${b.brandId == productLine.brandId}">selected</c:if>>${b.brandName}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="modal-footer">
                        <a href="listProductLine" class="btn btn-default">Quay Về</a>
                        <input type="submit" class="btn btn-success" value="Cập Nhật">
                    </div>
                    
                </form>
            </div>
        </div>
    </body>
</html>

