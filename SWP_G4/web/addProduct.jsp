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
              input[type=number]::-webkit-outer-spin-button,
            input[type=number]::-webkit-inner-spin-button {
                -webkit-appearance: none;
                margin: 0;
            }
            input[type=number] {
                -moz-appearance: textfield;
            }
        </style>
    </head>
    <body class="bg-gray-100">
        <div class="container mx-auto mt-10">
            <div class="menu">
                <jsp:include page="menu.jsp"></jsp:include>
            </div>
            <div class="content">
                <h1 class="text-2xl font-bold mb-6">Thêm Sản Phẩm</h1>
                <c:if test="${not empty mess}">
                    <div class="message">${mess}</div>
                </c:if>
                <form action="addProduct" method="post">
                    <div class="form-group">
                        <label>Tên</label>
                        <input name="name" value="${name}" type="text" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label>Giá</label>
                        <input name="price" value="${price}" type="number" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label>Ảnh</label>
                        <input name="image" type="file" value="${image}" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label>Mô tả</label>
                        <textarea name="description" class="form-control" required>${description}</textarea>
                    </div>
                    <div class="form-group">
                        <label>Loại Sản Phẩm</label>
                        <select name="productLineName" class="form-select" required>
                             <option value="" disabled selected>Select a product line</option>
                            <c:forEach items="${listP}" var="productLine">
                                <option value="${productLine.productLineId}">${productLine.productLineName}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="modal-footer">
                        <a href="productList" class="btn btn-default">Quay Về</a>
                        <input type="submit" class="btn btn-success" value="Tạo Mới">
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>
