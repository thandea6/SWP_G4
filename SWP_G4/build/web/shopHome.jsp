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

            .sidebar i {
                margin-right: 10px;
            }

            .sidebar .username {
                font-weight: bold;
                font-size: 1.1em;
            }

            .background-image-container {
                position: relative;
                background-size: cover;
                background-position: center;
                padding: 20px;
                height: 100vh;
                width: 100%;
                display: flex;
                align-items: center;
                justify-content: center;
                color: white;
                text-shadow: 0 0 10px rgba(0, 0, 0, 0.7);
                border-radius: 0;
                box-shadow: none;
            }

            .background-text {
                position: absolute;
                text-align: center;
            }

            .background-text h1 {
                font-size: 4rem;
                font-weight: bold;
            }

            .background-text h2 {
                font-size: 2rem;
            }
        </style>
    </head>
    <body class="m-0">

        <%-- Main Content --%>
        <div class="flex">
            <jsp:include page="menu.jsp"></jsp:include>
            <div class="background-image-container" style="background-image: url('${shop.image}')">
                <div class="background-text">
                    <h1>Welcome</h1>
                    <h2>${shop.shopName}</h2>
                </div>
            </div>
        </div>
    </body>
</html>
