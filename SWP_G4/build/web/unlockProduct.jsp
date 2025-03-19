<%-- 
    Document   : hiddenProduct
    Created on : 9 Jun 2024, 23:02:46
    Author     : admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Product List</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script src="https://cdn.tailwindcss.com"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
        <style>
            table {
                width: 100%;
                border-collapse: collapse;
            }

            th, td {
                padding: 12px;
                text-align: left;
            }

            th {
                background-color: #f8f8f8;
                font-weight: bold;
            }

            img {
                width: 100px;
                max-width: 100px;
                height: auto;
            }

            .action-links a {
                margin-right: 10px;
                color: orange;
                text-decoration: none;
            }

            .action-links a:hover {
                text-decoration: underline;
            }

            .product-row {
                border-bottom: 2px solid orange;
                margin-bottom: 20px;
                padding-bottom: 10px;
            }

            .btn-container {
                display: flex;
                justify-content: space-between;
                align-items: center;
                margin-bottom: 20px;
            }

            .btn {
                background-color: orange;
                border: 2px solid orange;
                padding: 10px;
                border-radius: 5px;
                color: white;
                width: fit-content;
                text-decoration: none;
            }

            .btn:hover {
                background-color: #ff7f00;
            }

            .search-form {
                display: flex;
                align-items: center;
                gap: 10px; /* Khoảng cách giữa các phần tử */
                justify-content: space-between;
                width: 100%;
            }

            .search-input {
                border: 2px solid orange;
                padding: 8px;
                border-radius: 5px;
                transition: border-color 0.3s ease;
                color: black;
                background-color: white;
                flex-grow: 1;
            }

            .search-input:focus {
                outline: none;
            }

            .search-submit {
                background-color: orange;
                color: white;
                border: 2px solid orange;
                padding: 8px 16px;
                border-radius: 5px;
                cursor: pointer;
                transition: background-color 0.3s ease;
            }

            .search-submit:hover {
                background-color: #ff7f00;
            }

            .sort-select {
                border: 2px solid orange;
                padding: 8px;
                border-radius: 5px;
                color: black;
                background-color: white;
                transition: border-color 0.3s ease;
            }

            .sort-select:focus {
                outline: none;
                border-color: #ff7f00;
            }
        </style>
    </head>
    <body class="bg-gray-100">
        <div class="mx-auto mt-10">
            <div class="flex">

                <jsp:include page="menu.jsp"></jsp:include>
                    <div class="w-full bg-white p-6 rounded shadow ml-6">
                        <h1 class="text-2xl font-bold mb-6">Danh Sách Sản Phẩm Khóa</h1>
                    <c:if test="${not empty requestScope.mess}">
                        <p style="color: green" id="message">${requestScope.mess}</p>
                    </c:if>
                    <div class="btn-container">
                        <a href="productList" class="btn">Sản Phẩm Đang Bán</a>
                    </div>
                    <form action="lockProductList" method="get" class="search-form">
                        <input required name="txt" class="search-input" type="text" value="${txt}" placeholder="Tìm theo tên">
                        <select name="sort" class="sort-select">
                            <option value="" disabled selected hidden>Sắp xếp theo giá</option>
                            <option value="giam">Sắp Xếp Giảm Dần</option>
                            <option value="tang">Sắp Xếp Tăng Dần</option>
                        </select>
                        <input type="submit" value="Ok" class="search-submit">
                    </form>
                    <div>
                        <table>
                            <thead>
                                <tr>
                                    <th>Tên</th>
                                    <th>Ảnh</th>
                                    <th>Giá</th>
                                    <th>Thể Loại</th>
                                    <th>Thương Hiệu</th>
                                    <th colspan="6">Actions</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${listP}" var="p" varStatus="status">
                                    <tr class="product-row">                                   
                                        <td>${p.title}</td>
                                        <td>
                                            <img src="${p.image}" alt="Product Image">
                                        </td>
                                         <td> <fmt:formatNumber value="${p.price}"/> VND</td>
                                        <td>${p.categoryName}</td>
                                        <td>${p.brandName}</td>
                                        <td class="action-links">
                                            <a href="unlockProduct?ID=${p.id}" onclick="return confirm('Bạn có muốn mở khóa sản phẩm này?')">Mở Khóa</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
