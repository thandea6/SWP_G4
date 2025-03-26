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
         <link rel="stylesheet" href="./style/assets/css/addProduct.css">
    </head>
    <body class="bg-gray-100">
        <div class="container mx-auto mt-10">
            <div class="menu">
                <jsp:include page="../common/menu.jsp"></jsp:include>
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
                        <label>Gía</label>
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
