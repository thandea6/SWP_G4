<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Cập Nhật Tất Cả Sản Phẩm</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <style>
        /* Tổng quan CSS cho trang */
        body {
            font-family: Arial, sans-serif;
            background-color: #f9fafb;
            margin: 0;
            padding: 0;
        }

        .container {
            max-width: 1200px;
            margin: 0 auto;
            padding: 20px;
        }

        h1 {
            font-size: 24px;
            font-weight: bold;
            color: #333;
            text-align: center;
        }

        /* Table styling */
        .product-details-table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
            border: 2px solid #f57c00;
        }

        .product-details-table th,
        .product-details-table td {
            padding: 12px;
            text-align: center;
            border: 1px solid #f57c00;
        }

        .product-details-table th {
            background-color: #ffb74d;
            color: white;
            font-weight: bold;
        }

        .product-details-table td {
            background-color: #fff3e0;
        }

        /* Select and input field styling */
        .product-details-table select,
        .product-details-table input {
            width: 100%;
            padding: 10px;
            margin: 5px 0;
            border: 1px solid #ddd;
            border-radius: 5px;
            box-sizing: border-box;
        }

        .product-details-table select:focus,
        .product-details-table input:focus {
            border-color: #f57c00;
            outline: none;
        }

        /* Button styling */
        .btn {
            background-color: #f57c00;
            color: white;
            border: none;
            padding: 12px 20px;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
            transition: background-color 0.3s;
        }

        .btn:hover {
            background-color: #ff9800;
        }

        .modal-footer {
            text-align: right;
            margin-top: 20px;
        }

        /* Form styling */
        form {
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            margin-top: 20px;
        }

        /* Footer link */
        .back-link {
            display: inline-block;
            background-color: orange;
            color: white;
            padding: 10px 20px;
            border-radius: 5px;
            text-decoration: none;
            margin-top: 20px;
        }

        .back-link:hover {
            background-color: #ff7f00;
        }

        /* Thêm khoảng cách cho các phần tử */
        .product-details-table td,
        .modal-footer {
            padding-top: 20px;
        }

        /* Hover effect for table rows */
        .product-details-table tr:hover {
            background-color: #ffe0b2;
        }

        /* Modal Footer */
        .modal-footer {
            text-align: right;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1 style="font-weight: bold; font-size: 36px;">Cập Nhật Tất Cả Sản Phẩm</h1>

        <form action="updateAllQuantities" method="post">
    <input name="action" value="updateAll" type="hidden"/>
    
    <table class="product-details-table">
        <thead>
            <tr>
                <th>Màu</th>
                <th>Kích Cỡ</th>
                <th>Số Lượng</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${listP}" var="variant">
                <tr>
                    <td>
    <select name="color_${variant.productId}_${variant.sizeId}" class="form-select">
        <c:forEach items="${listC}" var="color">
            <option value="${color.colorId}" 
                    ${color.colorId == variant.colorId ? 'selected' : ''}>
                ${color.colorValue}
            </option>
        </c:forEach>
    </select>
</td>
<td>
    <select name="size_${variant.productId}_${variant.colorId}" class="form-select">
        <c:forEach items="${listS}" var="size">
            <option value="${size.sizeId}" 
                    ${size.sizeId == variant.sizeId ? 'selected' : ''}>
                ${size.sizeValue}
            </option>
        </c:forEach>
    </select>
</td>
<td>
    <input name="quantity_${variant.productId}_${variant.colorId}_${variant.sizeId}" 
           type="number" 
           class="form-control" 
           value="${variant.quantity}" 
           min="0">
</td>


                </tr>
            </c:forEach>
        </tbody>
    </table>
    
    <div class="modal-footer">
        <input type="submit" class="btn" value="Cập Nhật Tất Cả">
    </div>
</form>


        <a href="productList" class="back-link">Quay Về</a>
    </div>
</body>
</html>
