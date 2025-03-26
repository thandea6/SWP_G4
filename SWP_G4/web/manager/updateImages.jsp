<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Images</title>
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
            .form-control {
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
            .btn-danger {
                background-color: #e74c3c;
                color: white;
            }
            .btn:hover {
                opacity: 0.8;
            }
            .image-item {
                display: flex;
                align-items: center;
                margin-bottom: 20px;
            }
            .image-preview {
                width: 100px;
                height: 100px;
                object-fit: cover;
                margin-right: 20px;
                border: 1px solid #ccc;
                border-radius: 5px;
            }
            .add-image-item {
                display: none;
                align-items: center;
                margin-bottom: 20px;
                gap: 10px;
            }
            .add-image-item.active {
                display: flex;
            }
            .buttons {
                display: flex;
                justify-content: space-between;
                margin-top: 20px;
            }
            .back-link {
                background-color: orange;
                color: white;
                padding: 10px 20px;
                border-radius: 5px;
                text-decoration: none;
                display: inline-block;
            }
            .button-group {
                display: flex;
                gap: 10px;
            }
        </style>
        <script>
            function addImage() {
                document.getElementById('addImageItem').classList.add('active');
            }

            function deleteImage(imagesId, productId) {
                if (confirm('Do you want to delete this image?')) {
                    window.location.href = 'deleteImage?imagesId=' + imagesId + '&productId=' + productId;
                }
            }

            function hideMessage() {
                var messageElement = document.getElementById("message");
                if (messageElement) {
                    setTimeout(function () {
                        messageElement.style.display = "none";
                    }, 4000);
                }
            }

            window.onload = hideMessage;
        </script>
    </head>
    <body class="bg-gray-100">
        <div class="container mx-auto mt-10">
            <div class="menu">
                <jsp:include page="../common/menu.jsp"></jsp:include>
            </div>
            <div class="content">
                <h1 class="text-2xl font-bold mb-6">Cập Nhật Ảnh</h1>
                <%
                    String mess = (String) request.getAttribute("mess");
                    if (mess != null) {
                %>
                <p style="color: orange" id="message">${mess}</p>
                <%
                    }
                %>
                <form action="updateImages" method="post">
                    <input name="id" value="${requestScope.productId}" type="hidden"/>

                    <c:forEach var="image" items="${listI}">
                        <div class="image-item">
                            <img src="${image.imageLink}" class="image-preview" alt="Image Preview">
                            <div class="form-group">
                                <label for="imageLink_${image.imagesId}">Link Ảnh</label>
                                <input type="text" id="imageLink_${image.imagesId}" name="imageLink_${image.imagesId}" class="form-control" value="${image.imageLink}" required>
                            </div>
                            <div class="form-group">
                                <button type="button" class="btn btn-danger" onclick="deleteImage(${image.imagesId}, ${requestScope.productId})">Delete</button>
                            </div>
                        </div>
                    </c:forEach>

                    <!-- Section for adding new image comment here -->
                    <div id="addImageItem" class="add-image-item">
                        <div class="form-group">
                            <label for="newImageLink">Thêm Ảnh</label>
                            <input type="file" id="newImageLink" name="newImageLink" class="form-control">
                        </div>
                        <input type="submit" class="btn btn-success" value="Add">
                    </div>

                    <div class="buttons">
                        <a href="productList" class="back-link">Quay Về</a>
                        <div class="button-group">
                            <button type="button" class="btn btn-success" onclick="addImage()">Thêm Ảnh</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>
