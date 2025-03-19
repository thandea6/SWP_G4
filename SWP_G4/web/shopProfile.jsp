<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <title>Profile</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script src="https://cdn.tailwindcss.com"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">

        <style>
            input[type="date"] {
                padding: 0.5rem; /* Thêm khoảng cách bên trong */
                border: 1px solid #cbd5e0; /* Màu viền */
                border-radius: 0.25rem; /* Bo tròn góc */
                font-size: 1rem; /* Kích thước chữ */
                color: #4a5568; /* Màu chữ */
                background-color: #fff; /* Màu nền */
                transition: border-color 0.2s, box-shadow 0.2s; /* Hiệu ứng chuyển đổi */
            }

            input[type="date"]:focus {
                border-color: #63b3ed; /* Màu viền khi focus */
                box-shadow: 0 0 0 3px rgba(99, 179, 237, 0.5); /* Hiệu ứng bóng khi focus */
                outline: none; /* Bỏ viền mặc định khi focus */
            }

            .required::after {
                content: ' *';
                color: red;
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

        <div class="mx-auto mt-10">

            <div class="flex">
                <!-- Sidebar -->

                <c:set value="${requestScope.user}" var="account"/>
                <c:set value="${requestScope.shopA}" var="user"/>
                <jsp:include page="menu.jsp"></jsp:include>
                    <!-- Main Content -->
                    <div class="w-3/4 bg-white p-6 rounded shadow ml-6">
                        <h2 class="text-2xl font-bold mb-4">Hồ Sơ Của Tôi</h2>
                        <p class="text-gray-600 mb-6">Quản lý thông tin hồ sơ để bảo mật tài khoản</p>
                    <%
                           String mess = (String) request.getAttribute("mess");
                           if (mess != null) {
                    %>
                    <p style="color: red" id="message">${mess}</p>
                    <%
                        }
                    %>
                    <form action="updateShop" method="post">
                        <div class="mb-4">
                            <label class="block text-gray-700 required">Tên Shop</label>
                            <input type="text" readonly value="${user.shopName}" class="w-full p-2 border border-gray-300 rounded" disabled>
                        </div>
                        <div class="mb-4">
                            <label class="block text-gray-700">Địa chỉ</label>
                            <input type="text" value="${user.address}" class="w-full p-2 border border-gray-300 rounded" required name="address">
                        </div>
                        <div class="mb-4">
                            <label class="block text-gray-700">Số Điện Thoại</label>
                            <input type="number" value="${acc.phone}" class="w-full p-2 border border-gray-300 rounded" required name="phone">
                        </div>

                        <div class="mb-4">
                            <label class="block text-gray-700">Email</label>
                            <div class="flex items-center">
                                <input type="email" value="${user.email}" class="w-full p-2 border border-gray-300 rounded" disabled name="email">
                                <a href="confirmCode" class="text-blue-500 ml-2">Thay Đổi</a>
                            </div>
                        </div>  

                        <!-- Button container to align the buttons on the same line -->
                        <div class="flex space-x-2">
                            <button class="bg-red-500 text-white px-4 py-2 rounded" type="submit">Lưu</button>
                            <a href="changepassword" class="bg-blue-500 text-white px-4 py-2 rounded">Đổi Mật Khẩu</a>
                        </div>


                </div>

                <!-- Profile Picture -->
                <div class="w-1/4 bg-white p-6 rounded shadow ml-6 flex flex-col items-center">
                    <div class="w-24 h-24 bg-gray-200 rounded-full flex items-center justify-center mb-4" id="image-preview">
                        <c:if test="${user.image.length()==0||user.image.length()==null}">
                            <i class="fas fa-user text-gray-400 text-4xl"></i>
                        </c:if>
                        <c:if test="${user.image.length() > 0}">
                            <img src="${user.image}" alt="img"/>
                        </c:if>
                    </div>
                    <label class="bg-gray-300 text-gray-700 px-4 py-2 rounded mb-4 cursor-pointer">
                        <input type="file" name="image" value="${user.image}" accept=".jpeg, .png, .jpg", style="display: none;">
                        Chọn ảnh
                    </label>

                    <p class="text-gray-600 text-center">Định dạng: .JPEG, .PNG, .JPG</p>
                </div>
                </form>

            </div>
        </div>
    </body>
</html>
