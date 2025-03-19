<%-- 
    Document   : userOrder
    Created on : Jun 16, 2024, 5:50:12 PM
    Author     : VIET HOANG
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <title>UserOrder</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script src="https://cdn.tailwindcss.com"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    </head>
    <style>
        /* Custom CSS */

        /* Table Styling */
        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }

        th,
        td {
            text-align: left;
            padding: 10px;
        }

        th {
            background-color: #f2f2f2;
        }


        /* End of Custom CSS */
    </style>
    <body class="bg-gray-100">

        <div class="mx-auto mt-10">

            <div class="flex">
                <!-- Sidebar -->
                <c:set value="${requestScope.userProfile}" var="user"/>
                <c:set value="${sessionScope.user}" var="account"/>
                <div class="w-1/4 bg-white p-4 rounded shadow">
                    <div class="flex items-center mb-6">
                        <div class="w-12 h-12 bg-gray-200 rounded-full flex items-center justify-center">
                            <c:if test="${user.image.length()==0||user.image.length()==null}">
                                <i class="fas fa-user text-gray-400 text-4xl"></i>
                            </c:if>
                            <c:if test="${user.image.length() > 0}">
                                <img src="${user.image}" alt="img"/>
                            </c:if>
                        </div>
                        <div class="ml-4">
                            <div class="font-bold">${account.username}</div>
                        </div>
                    </div>
                    <ul>
                        <li class="mb-4">
                            <a href="updateuser" class="flex items-center text-gray-700">
                                <i class="fas fa-id-card mr-2"></i>
                                Hồ Sơ
                            </a>
                        </li>
                        <li class="mb-4">
                            <a href="changepassword" class="flex items-center text-gray-700">
                                <i class="fas fa-lock mr-2"></i>
                                Đổi Mật Khẩu
                            </a>
                        </li>
                        <li class="mb-4">
                            <a href="listOrder" class="flex items-center text-red-500">
                                <i class="fas fa-shopping-cart mr-2"></i>
                                Đơn Mua
                            </a>
                        </li>
                        
                        
                        <li class="mb-4">
                            <a href="home" class="flex items-center text-gray-700">
                                <i class="fas fa-backward mr-2"></i>
                                Trở về trang chủ
                            </a>
                        </li>
                    </ul>
                </div>
                <!-- Main Content -->
                <div class="w-3/4 bg-white p-6 rounded shadow ml-6">
                    <div class="bg-white p-4 rounded shadow mb-4">
                        <table>
                            <thead>
                                <tr>
                                    <th>Tên người nhận</th>
                                    <th>Số điện thoại</th>
                                    <th>Address</th>
                                    <th>Ngày đặt hàng</th>
                                    <th>Tổng tiền</th>
                                    <th colspan="6">Hành động</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="order" items="${requestScope.list}">
                                    <fmt:formatNumber type="number" pattern="######" value="${order.getTotalMoney()}" var="total"/>
                                    <tr>
                                        <td>${order.getName()}</td>
                                        <td>${order.getPhone()}</td>
                                        <td>${order.getAddress()}</td>
                                        <td>${order.getDate()}</td>
                                        <c:choose>
                                            <c:when test="${total > 0}">
                                                <td style="color: green"><fmt:formatNumber value="${total}"/>VND</td>
                                            </c:when>
                                            <c:otherwise>
                                                <td style="color: red">${total}(đã hủy)</td>
                                            </c:otherwise>
                                        </c:choose>
                                        <td><button class="ml-auto bg-red-500 text-white px-4 py-2 rounded"><a href="userOrderHistory?orderId=${order.getOrderId()}">Xem chi tiết</a></button></td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>

                </div>
            </div>

    </body>
</html>
