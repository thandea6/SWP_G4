<%-- 
    Document   : cancelDetail
    Created on : 26 Jun 2024, 22:48:28
    Author     : admin
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Bill Detail</title>
        <style>
            table {
                width: 100%;
                border-collapse: collapse;
            }

            th, td {
                padding: 12px;
                text-align: left;
                border-bottom: 1px solid #ddd;
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

            .btn {
                display: inline-block;
                background-color: orange;
                color: #fff;
                padding: 8px 16px;
                text-align: center;
                text-decoration: none;
                border-radius: 4px;
                transition: background-color 0.3s ease;
                margin-top: 20px;
            }

            .btn:hover {
                background-color: darkorange;
            }

            .chat-btn {
                background-color: #4CAF50;
                color: white;
                border: none;
                padding: 10px 20px;
                font-size: 16px;
                border-radius: 50px;
                cursor: pointer;
                box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1);
                transition: background-color 0.3s ease, box-shadow 0.3s ease;
                margin-bottom: 20px;
            }

            .chat-btn:hover {
                background-color: #45a049;
                box-shadow: 0px 6px 8px rgba(0, 0, 0, 0.15);
            }

            .relative-container {
                position: relative;
            }

            .chat-container {
                position: absolute;
                top: 20px;
                right: 20px;
                text-align: center;
            }

            .cancel-reason {
                background-color: #ffcccc;
                border: 1px solid #ff3333;
                padding: 10px;
                border-radius: 4px;
                margin-top: 20px;
            }
            .status-container {
                background-color: lightgreen;
                padding: 10px 20px;
                border-radius: 20px;
                box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1);
                display: inline-block;
                margin-top: 10px;
            }

            .status-container h4, .status-container span {
                display: inline;
            }
        </style>
        <script>
            function goToChat() {
                window.location.href = '#';
            }
        </script>
    </head>
    <body class="bg-gray-100">
        <div class="mx-auto mt-10 relative-container">
            <div class="flex">
                <jsp:include page="menu.jsp"></jsp:include>
                <c:set var="listO" value="${requestScope.listO}" />

                <div class="w-full bg-white p-6 rounded shadow ml-6">
                    <div class="chat-container">
                       
                        <div class="status-container">
                            <h4>Trạng Thái: </h4>
                            <span>${listO[0].statusValue}</span>
                        </div>
                    </div>
                    <h1 class="text-2xl font-bold mb-6">Chi Tiết Đơn Hàng</h1>
                    <div>
                        <table>
                            <thead>
                                <tr>
                                    <th>Tên</th>
                                    <th>Ảnh</th>
                                    <th>Màu</th>
                                    <th>Kích Cỡ</th>
                                    <th>Giá</th>
                                    <th>Số Lượng</th>
                                    <th>Tổng</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${listO}" var="p" varStatus="status">
                                    <tr>
                                        <td>${p.title}</td>
                                        <td><img src="${p.image}" alt="Product Image"></td>
                                        <td>${p.colorValue}</td>
                                        <td>${p.sizeValue}</td>
                                        <td> <fmt:formatNumber value="${p.price}"/> VND</td>
                                        <td>${p.quantity}</td>
                                         <td> <fmt:formatNumber value="${p.total}"/> VND</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>

                    <div class="form-container">
                        <a href="orderCancel" class="btn">Back to Previous</a>
                    </div>

                    <div class="cancel-reason">
                        <p>Lý do hủy đơn hàng: ${listO[0].description}</p>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
