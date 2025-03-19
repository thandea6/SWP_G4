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

            .action-links a {
                margin-right: 10px;
                color: orange;
                text-decoration: none;
            }

            .action-links a:hover {
                text-decoration: underline;
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

            .status-select {
                padding: 8px;
                border-radius: 4px;
                border: 1px solid #ccc;
                font-size: 16px;
                margin-top: 22px;
            }

            .form-container {
                display: flex;
                justify-content: space-between;
                align-items: center;
                margin-top: 20px;
            }

            .status-form {
                display: flex;
                align-items: center;
            }

            .status-form label {
                margin-right: 10px;
            }

            .status-form .btn {
                margin-left: 10px;
            }

            .summary-container {
                text-align: right;
                margin-top: 20px;
            }

            .summary-item {
                margin-bottom: 10px;
            }

            .summary-item h4 {
                color: orange;
                display: inline;
            }

            .summary-item span {
                display: inline;
            }

            .next-status-button {
                display: inline-block;
                background-color: #4CAF50;
                color: white;
                padding: 10px 20px;
                border-radius: 50px;
                border: none;
                cursor: pointer;
                font-size: 16px;
                transition: background-color 0.3s ease, box-shadow 0.3s ease;
            }

            .next-status-button:hover {
                background-color: #45a049;
                box-shadow: 0px 6px 8px rgba(0, 0, 0, 0.15);
            }

            .status-form {
                margin-top: 22px;
            }
        </style>
        <script>
            function goToChat() {
                window.location.href = '#';
            }

            // Hàm để ẩn thông báo sau 2 giây
            function hideMessage() {
                var messageElement = document.getElementById("message");
                if (messageElement) {
                    setTimeout(function () {
                        messageElement.style.display = "none";
                    }, 2000); // 2 giây
                }
            }

            // Gọi hàm hideMessage khi trang đã tải xong
            window.onload = hideMessage;
        </script>
    </head>
    <body class="bg-gray-100">
        <div class="mx-auto mt-10 relative-container">
            <div class="flex">
                <jsp:include page="../common/menu.jsp"></jsp:include>
                <c:set var="listO" value="${requestScope.listO}" />

                <div class="w-full bg-white p-6 rounded shadow ml-6">
                    <div class="chat-container">

                       
                        <div class="status-container">
                            <h4>Trạng Thái: </h4>
                            <span>${listO[0].statusValue}</span>
                        </div>
                    </div>
                    <h1 class="text-2xl font-bold mb-6">Chi Tiết Đơn Hàng</h1>
                    <h6 id="message" style="color: orange">${requestScope.mess}</h6>
                    <div>
                        <table>
                            <thead>
                                <tr>
                                    <th>Tên</th>
                                    <th>Ảnh</th>
                                    <th>Màu</th>
                                    <th>Size</th>
                                    <th>Giá</th>
                                    <th>Số Lượng</th>
                                    <th>Voucher</th>
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
                                        <td> <fmt:formatNumber value="${p.reducedAmount}"/> VND</td>
                                         <td> <fmt:formatNumber value="${p.total}"/> VND</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <div class="summary-container">
                        <div class="summary-item">
                            <h4>Tổng Tiền: </h4>
                            <span><fmt:formatNumber value="${totalAll}"/>  VND</span>
                        </div>
                    </div>
                    <div class="form-container">
                        <a href="orderManagement" class="btn">Back to Previous</a>

                        <c:if test="${requestScope.statusId == 1}">
                            <a href="cancelOrderByShop?orderId=${requestScope.orderId}" class="btn" style="background-color: red;margin-left: 64%;margin-bottom: 20px;" onclick="return confirm('Bạn chắc chắn muốn hủy đơn hàng này?')">Cancel Order</a>
                        </c:if>
                        <form action="billDetail" method="post">
                            <c:if test="${requestScope.statusId != 3}">
                                <input type="hidden" name="orderId" value="${requestScope.orderId}"/>
                                <input type="submit" value="Next Status" class="next-status-button" onclick="return confirm('Xác Nhận Cập Nhật Trạng Thái Đơn Hàng?')" id="next-status-button">
                            </c:if>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
