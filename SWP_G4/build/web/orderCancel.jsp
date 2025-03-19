<%-- 
    Document   : orderCancel
    Created on : 25 Jun 2024, 22:56:03
    Author     : admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html>
<html>
<head>
    <title>Order Manager</title>
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

        td {
            height: 75px;
        }

        .action-links a {
            margin-right: 10px;
            color: orange;
            text-decoration: none;
        }

        .action-links a:hover {
            text-decoration: underline;
        }

        .order-row {
            border-bottom: 2px solid orange;
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
            text-decoration: none;
        }

        .btn:hover {
            background-color: #ff7f00;
        }

        .search-form {
            display: flex;
            align-items: center;
            gap: 10px;
        }

        .search-input {
            border: 2px solid orange;
            padding: 8px;
            border-radius: 5px;
            color: black;
            background-color: white;
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

        .btn-wrapper {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 20px;
        }

        .next-status-button {
            background: none;
            border: none;
            color: blue;
            text-decoration: none;
            cursor: pointer;
            padding: 0;
        }

        .next-status-button:focus {
            outline: none;
        }
    </style>
</head>
<body class="bg-gray-100">
    <div class="mx-auto mt-10">
        <div class="flex">
            <jsp:include page="menu.jsp"></jsp:include>
            <div class="w-full bg-white p-6 rounded shadow ml-6">
                <div class="btn-wrapper">
                    <h1 class="text-2xl font-bold">Đơn Hàng Đã Hủy</h1>
                    
                </div>
                <div class="btn-wrapper">
                <form action="orderCancel" method="post" class="search-form">
                    <input type="hidden" name="shopId" value="${shopId}">
                    <input required name="phone" class="search-input" type="text" value="${txt}" placeholder="Search by Phone">
                    <input type="submit" value="Ok" class="search-submit">
                </form>
                    </div>
                <div>
                    <table>
                        <thead>
                            <tr>
                                <th>STT</th>
                                <th>Tên</th>
                                <th>Số Điện Thoại</th>
                                <th>Địa Chỉ</th>
                                <th>Ngày Đặt Hàng</th>
                                <th>Tổng Tiền</th>
                                <th colspan="2">Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${orderList}" var="o" varStatus="status">
                                <tr class="order-row">
                                    <td>${status.index + 1}</td>
                                    <td>${o.name}</td>
                                    <td>${o.phone}</td>
                                    <td>${o.address}</td>
                                    <td>${o.date}</td>
                                     <td> <fmt:formatNumber value="${o.totalMoney}"/> VND</td>
                                    <td class="action-links">
                                        <a href="cancelDetail?orderId=${o.orderId}&shopId=${shopId}&statusId=${o.statusId}">Chi Tiết</a>
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
