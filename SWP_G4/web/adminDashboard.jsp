<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Admin dashboard</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script src="https://cdn.tailwindcss.com"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
        <style>
            input[type="date"] {
                padding: 0.5rem;
                border: 1px solid #cbd5e0;
                border-radius: 0.25rem;
                font-size: 1rem;
                color: #4a5568;
                background-color: #fff;
                transition: border-color 0.2s, box-shadow 0.2s;
            }

            input[type="date"]:focus {
                border-color: #63b3ed;
                box-shadow: 0 0 0 3px rgba(99, 179, 237, 0.5);
                outline: none;
            }
        </style>
    </head>
    <body class="bg-gray-100">
        <div class="mx-auto mt-10">
            <div class="flex">
                <jsp:include page="menu.jsp"></jsp:include>
                    <div class="w-full bg-white p-6 rounded shadow ml-6">
                        <div class="w-full bg-white p-6 rounded shadow ml-6">
                            <h2 class="text-2xl font-bold mb-4">Dashboard admin</h2>
                            <div class="grid grid-cols-4 gap-4 w-full max-w-screen-lg p-4">
                                <div class="bg-red-500 p-4 text-white text-center rounded-lg">
                                    <a href="adminManagerUser?user=true">
                                        <h2 class="text-lg font-bold mb-2">Users</h2>
                                        <p class="text-2xl">${users.size()}</p>
                                    <p class="text-2xl">accounts</p>
                                </a>
                            </div>
                            <div class="bg-green-500 p-4 text-white text-center rounded-lg">
                                <a href="adminManagerUser?shop=true"> 
                                    <h2 class="text-lg font-bold mb-2">Shop</h2>
                                    <p class="text-2xl">${shops.size()}</p>
                                    <p class="text-2xl">shop</p>
                                </a>
                            </div>
                        </div>
                        <c:if test="${param.user != null && param.user == 'true'}">
                            <div class="w-full bg-white p-6 rounded shadow ml-6">
                                <h2 class="text-2xl font-bold mb-4">User management</h2>
                                <div class="grid grid-cols-1 gap-4 w-full max-w-screen-lg p-4">
                                    <div class="overflow-x-auto">
                                        <table class="min-w-full bg-white">
                                            <thead>
                                                <tr>
                                                    <th class="w-1/3 px-4 py-2 text-left text-gray-600">Fullname</th>
                                                    <th class="w-1/3 px-4 py-2 text-left text-gray-600">Address</th>
                                                    <th class="w-1/3 px-4 py-2 text-left text-gray-600">Image</th>

                                                    <th class="w-1/3 px-4 py-2 text-left text-gray-600">DBO</th>
                                                    <th class="w-1/3 px-4 py-2 text-left text-gray-600">Gender</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach items="${userShow}" var="user">
                                                    <tr class="bg-gray-100">
                                                        <td class="border px-4 py-2">${user.fullName}</td>
                                                        <td class="border px-4 py-2">${user.address}</td>
                                                        <td class="border px-4 py-2">
                                                            <c:if test="${user.image != null}">
                                                                <img src="${user.image}" style="width: 100px"/>
                                                            </c:if>
                                                        </td>

                                                        <td class="border px-4 py-2">${user.dob}</td>
                                                        <td class="border px-4 py-2">${user.gender == true ? "Male" : "Female"}</td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </c:if>
                        <c:if test="${param.shop != null && param.shop == 'true'}">
                            <div class="w-full bg-white p-6 rounded shadow ml-6">
                                <h2 class="text-2xl font-bold mb-4">Shop management</h2>
                                <div class="grid grid-cols-1 gap-4 w-full max-w-screen-lg p-4">
                                    <div class="overflow-x-auto">
                                        <table class="min-w-full bg-white">
                                            <thead>
                                                <tr>
                                                    <th class="w-1/3 px-4 py-2 text-left text-gray-600">Shop name</th>
                                                    <th class="w-1/3 px-4 py-2 text-left text-gray-600">Address</th>
                                                    <th class="w-1/3 px-4 py-2 text-left text-gray-600">Image</th>

                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach items="${shopShow}" var="shop">
                                                    <tr class="bg-gray-100">
                                                        <td class="border px-4 py-2">${shop.shopName}</td>
                                                        <td class="border px-4 py-2">${shop.address}</td>
                                                        <td class="border px-4 py-2">
                                                            <c:if test="${shop.image != null}">
                                                                <img src="${shop.image}" style="width: 100px"/>
                                                            </c:if>
                                                        </td>

                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
