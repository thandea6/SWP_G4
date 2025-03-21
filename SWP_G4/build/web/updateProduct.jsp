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
        <c:set value="${requestScope.InfoP}" var="product"/>
        <div class="content">
            <div class="header-container">
                <h1 class="text-2xl font-bold mb-6">Chỉnh sửa sản phẩm</h1>
                <div class="flex space-x-2">
<!--                     <a href="discount?productId=${product.id}" class="update-images-btn">Discount</a>-->
                    <a href="updateQuantity?productId=${product.id}" class="update-images-btn">Cập Nhật Số Lượng</a>
                    <a href="updateImages?productId=${product.id}" class="update-images-btn">Cập Nhật Ảnh</a>
                </div>
            </div>
            <%
            String mess = (String) request.getAttribute("mess");
            if (mess != null) {
            %>
            <p style="color: orange" id="message">${mess}</p>
            <%
                }
            %>
            
            <form action="updateProduct" method="post">
                <input name="id" value="${product.id}" type="hidden"/>

                <div class="form-group">
                    <label>Tên Sản Phẩm</label>
                    <input name="title" value="${product.title}" type="text" class="form-control" required>
                </div>

                <div class="form-group">
                    <label>Ảnh</label>
                    <input id="imageFile" name="image" type="file" class="form-control">
                    <input type="hidden" id="oldImage" name="oldImage" value="${product.image}">
                </div>
                <div class="form-group">
                    <label>Gía</label>
                    <input value="${product.price}" name="price" type="number" class="form-control" required>
                </div> 
                <div class="form-group">
                    <label>Mô tả</label>
                    <textarea name="des" class="form-control" required>${product.description}</textarea>
                </div>
                <div class="form-group">
                    <label>Loại Sản Phẩm</label>
                    <select name="productLineName" class="form-select" required>
                        <c:forEach items="${listPL}" var="productLine">
                            <option value="${productLine.productLineId}" <c:if test="${product.productLineName == productLine.productLineName}">selected</c:if>>${productLine.productLineName}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="modal-footer">
                    <div class="back-link">
                        <a href="productList">Quay Về</a>
                    </div>
                    <input type="submit" class="btn btn-success" value="Edit">
                </div>
            </form>
        </div>
    </div>
</body>
</html>
