<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Manager Page</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script src="https://cdn.tailwindcss.com"></script>
       
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
        <link rel="stylesheet" href="./style/assets/css/style.css">
    </head>
    <body class="m-0">

        
        <%-- Main Content --%>
        <div class="flex">
            <jsp:include page="../common/menu.jsp"></jsp:include>
            <div class="background-image-container" style="background-image: url('${shop.image}')">
                <div class="background-tesxt">
                    <h1>Welcome</h1>
                    <h2>${shop.shopName}</h2>
                </div>
            </div>
        </div>
    </body>
</html>
