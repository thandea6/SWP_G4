<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Seller Profile</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script src="https://cdn.tailwindcss.com"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
        <style>
            .profile-image {
                width: 50px;
                height: 50px;
                border-radius: 50%;
                object-fit: cover;
                margin-right: 10px;
            }

            .sidebar {
                width: 100%;
                max-width: 300px;
                background-color: #ffffff;
                padding: 20px;
                border-radius: 10px;
                box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            }

            .sidebar ul {
                list-style: none;
                padding: 0;
                margin: 0;
            }

            .sidebar li {
                margin-bottom: 20px;
            }

            .sidebar a {
                display: flex;
                align-items: center;
                text-decoration: none;
                color: #4a5568; /* Gray-700 */
                transition: color 0.3s;
            }

            .sidebar a:hover {
                color: #e53e3e; /* Red-600 */
            }

            .sidebar a.active {
                color: orange;
            }

            .sidebar i {
                margin-right: 10px;
            }

            .sidebar .username {
                font-weight: bold;
                font-size: 1.1em;
            }
        </style>
    </head>
    <body>
        <c:if test="${sessionScope.user.roleId==3}" >
            <div class="sidebar" style="background-color: lightyellow">
                <div class="flex items-center mb-6">
                    <div class="w-12 h-12 bg-gray-200 rounded-full flex items-center justify-center">
                        <img src="${shop.image}" alt="img" class="profile-image"/>
                    </div>
                    <div class="ml-4">
                        <div class="font-bold">${shop.shopName}</div>
                    </div>
                </div>

                <ul>
                    <li>
                        <a href="productList" class="flex items-center text-gray-700" >
                            <i class="fas fa-id-card"></i>
                            Quản Lý Sản Phẩm
                        </a>
                    </li>
                    <li>
                        <a href="orderManagement" class="flex items-center text-gray-700" >
                            <i class="fas fa-shopping-cart"></i>
                            Quản Lý Đơn Hàng
                        </a>
                    </li>
                    <li>
                        <a href="statistical" class="flex items-center text-gray-700" >
                            <i class="fas fa-lock"></i>
                            Thống Kê
                        </a>
                    </li>
                    <li class="mb-4">
                        <a href="shopchatwithuser" class="flex items-center text-gray-700">
                            <i class="fas fa-envelope mr-2"></i>
                            Tin nhắn
                        </a>
                    </li>
                    <li class="mb-4">
                        <a href="manageVoucherShop" class="flex items-center text-gray-700">
                            <i class="fas fa-gifts mr-2"></i>
                            Quản lý voucher
                        </a>
                    </li>
                    <li>
                        <a href="updateShop" class="flex items-center text-gray-700" >
                            <i class="fas fa-gift"></i>
                            Tài Khoản
                        </a>
                    </li>
                    <li>
                        <a href="logout" class="flex items-center text-gray-700">
                            <i class="fas fa-backward"></i>
                            Đăng xuất
                        </a>
                    </li>
                </ul>
            </div>
        </c:if>
        <c:if test="${sessionScope.user.roleId==1}" >
            <div class="sidebar" style="background-color: lightsalmon">
                <div class="flex items-center mb-6">
                    <div class="w-12 h-12 bg-gray-200 rounded-full flex items-center justify-center">
                        <img src="images/imageAdmin.png" alt="img" class="profile-image"/>
                    </div>
                    <div class="ml-4">
                        <div class="font-bold">Admin</div>
                    </div>
                </div>

                <ul>
                    <li>
                        <a href="adminManagerUser" class="flex items-center text-gray-700">
                            <i class="fas fa-id-card"></i>
                            Quản Lý Người Dùng
                        </a>
                    </li>
                    <li>
                        <a href="listProductLine" class="flex items-center text-gray-700">
                            <i class="fas fa-shopping-cart"></i>
                            Quản Lý Dòng Sản Phẩm
                        </a>
                    </li>
                    <li>
                        <a href="logout" class="flex items-center text-gray-700">
                            <i class="fas fa-backward"></i>
                            Đăng xuất
                        </a>
                    </li>
                </ul>
            </div>


        </c:if>

    </body>
</html>
