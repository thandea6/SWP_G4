<%-- 
    Document   : userOrderHistory
    Created on : Jun 11, 2024, 4:19:37 PM
    Author     : VIET HOANG
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>User Order History</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script src="https://cdn.tailwindcss.com"></script>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    </head>
    <style>
        .selected {
            color: red !important;
        }
        .hidden {
            display: none;
        }
        .rating {
            display: flex;
            margin-bottom: 10px;
            flex-direction: row-reverse;
            margin-left: -4px;
            float: left;

        }

        .rating>input {
            display: none
        }

        .rating>label {
            position: relative;
            width: 19px;
            font-size: 25px;
            color: #ff0000;
            cursor: pointer;
        }

        .rating>label::before {
            content: "\2605";
            position: absolute;
            opacity: 0
        }

        .rating>label:hover:before,
        .rating>label:hover~label:before {
            opacity: 1 !important
        }

        .rating>input:checked~label:before {
            opacity: 1
        }

        .rating:hover>input:checked~label:before {
            opacity: 0.4
        }
        .comment-area textarea {
            width: 100%;
            border: 1px solid #ddd;
            border-radius: 5px;
            padding: 10px;
            font-size: 14px;
            resize: vertical;
        }
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
                    <div class="bg-white p-4 rounded shadow">
                        <div class="flex border-b pb-2 mb-4">
                            <a href="#all" class="text-gray-700 font-bold mr-4" onclick="highlightSelected(this, 'All')">Tất cả</a>
                            <a href="#wait" class="text-gray-700 mr-4" onclick="highlightSelected(this, 'Wait')">Chờ shop chuẩn bị hàng</a>
                            <a href="#process" class="text-gray-700 mr-4" onclick="highlightSelected(this, 'Process')">Vận chuyển</a>
                            <a href="#complete" class="text-gray-700 mr-4" onclick="highlightSelected(this, 'Complete')">Hoàn thành</a>
                            <a href="#cancel" class="text-gray-700" onclick="highlightSelected(this, 'Cancel')">Đã hủy</a>
                        </div>
                        <form action="userOrderHistory" method="post" class="flex">
                            <div class="flex-1 mb-4">
                                <input id="search" name="key" type="text" class="w-full p-2 border rounded" placeholder="Bạn có thể tìm kiếm theo Tên Sản phẩm">
                                <input type="hidden" name="orderId" value="${requestScope.oid}"/>
                            </div>
                            <div class="ml-2 mb-4">
                                <button type="submit" class="p-2 bg-blue-500 text-white rounded">
                                    <i class="fas fa-search"></i> Tìm kiếm
                                </button>
                            </div>
                        </form>
                        <!-- Order Item 1 -->
                        <div id="all">
                            <c:forEach var="order" items="${requestScope.list}">

                                <div class="bg-white p-4 rounded shadow mb-4">
                            
                                    <div class="flex">
                                        <img src="${order.getImage()}" alt="image" class="w-24 h-24 object-cover rounded">
                                        <div class="ml-4 flex-1">
                                            <div class="font-bold">${order.getTitle()}</div>
                                            <div class="text-gray-500">Size: ${order.getSizeValue()}</div>
                                            <div class="text-gray-500">Màu: ${order.getColorValue()}</div>
                                            <div class="text-gray-500">Số lượng: ${order.getQuantity()}</div>
                                        </div>
                                        <div class="text-right">
                                            <fmt:formatNumber type="number" pattern="######" value="${order.getPrice()}" var="total"/>
                                            <div class="text-red-500 font-bold"><fmt:formatNumber value="${total}"/>VND</div>
                                            <c:if test="${order.getCode() != null}">
                                                <fmt:formatNumber type="number" pattern="######" value="${order.getReducedAmount()}" var="reduced"/>
                                                <div class="text-green-500 font-bold">${order.getCode()}</div>
                                                <div class="text-green-500 font-bold">-<fmt:formatNumber value="${reduced}"/>VND</div>
                                            </c:if>
                                        </div>
                                    </div>
                                    <div class="flex items-center mt-4">
                                        <div class="text-red-500">${order.getStatusValue()}</div>
                                        <c:if test="${order.getStatusId()==3||order.getStatusId()==4}">
                                            <button onclick="confirmRebuyOrder('${order.getProductItemId()}', '${order.getShopProductId()}',
                                                            '${order.getQuantity()}')" class="ml-auto bg-red-500 text-white px-4 py-2 rounded">Mua lại</button>
                                        </c:if>
                                        <c:if test="${order.getStatusId()==1}">
                                            <button onclick="confirmCancelOrder('${order.getOrderDetailId()}')" class="ml-auto bg-red-500 text-white px-4 py-2 rounded">Hủy đơn</button>
                                        </c:if>
                                        <c:if test="${order.getStatusId() == 3 && order.getStarRating() == null }">
                                            <button 
                                                onclick="showFeedbackSection('${order.getShopName()}', '${order.getOrderDate()}', '${order.getShopProductId()}',
                                                                '${order.getImage()}', '${order.getTitle()}', '${order.getSizeValue()}', '${order.getColorValue()}', '${order.getQuantity()}',
                                                                '${order.getPrice()}', '${order.getOrderId()}', '${order.getOrderDetailId()}', '${order.getShopId()}');
                                                        hideButton('${order.getOrderDetailId()}');"
                                                class="ml-2 bg-red-500 text-white px-4 py-2 rounded">
                                                Đánh giá
                                            </button>
                                        </c:if>

                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                        <div id="wait" class="hidden">


                            <c:forEach var="order" items="${requestScope.list}">
                                <c:if test="${order.getStatusId()==1}">
                                    <div class="bg-white p-4 rounded shadow mb-4">
                                        <div class="flex items-center mb-2">
                                            <span class="ml-2 font-bold fas fa-store">${order.getShopName()}</span>
                                            <p class="ml-2 font-weight-lighter">${order.getOrderDate()}</p>
                                            <a href="userchatwithshop?sid=${order.getShopId()}" class="ml-auto text-blue-500">Chat</a>
                                            <button class="ml-2 text-blue-500">Xem Shop</button>
                                        </div>
                                        <div class="flex">
                                            <img src="${order.getImage()}" alt="image" class="w-24 h-24 object-cover rounded">
                                            <div class="ml-4 flex-1">
                                                <div class="font-bold">${order.getTitle()}</div>
                                                <div class="text-gray-500">Size: ${order.getSizeValue()}</div>
                                                <div class="text-gray-500">Màu: ${order.getColorValue()}</div>
                                                <div class="text-gray-500">Số lượng: ${order.getQuantity()}</div>
                                            </div>
                                            <div class="text-right">
                                                <fmt:formatNumber type="number" pattern="######" value="${order.getPrice()}" var="total"/>
                                                <div class="text-red-500 font-bold"><fmt:formatNumber value="${total}"/>VND</div>
                                                <c:if test="${order.getCode() != null}">
                                                    <fmt:formatNumber type="number" pattern="######" value="${order.getReducedAmount()}" var="reduced"/>
                                                    <div class="text-green-500 font-bold">${order.getCode()}</div>
                                                    <div class="text-green-500 font-bold">-<fmt:formatNumber value="${reduced}"/>VND</div>
                                                </c:if>
                                            </div>
                                        </div>
                                        <div class="flex items-center mt-4">
                                            <div class="text-red-500">${order.getStatusValue()}</div>
                                            <button onclick="confirmCancelOrder('${order.getOrderDetailId()}')" class="ml-auto bg-red-500 text-white px-4 py-2 rounded">Hủy đơn</button>
                                        </div>
                                    </div>
                                </c:if>
                            </c:forEach>

                        </div>

                        <div id="process" class="hidden">


                            <c:forEach var="order" items="${requestScope.list}">
                                <c:if test="${order.getStatusId()==2}">
                                    <div class="bg-white p-4 rounded shadow mb-4">
                                        <div class="flex items-center mb-2">
                                            <span class="ml-2 font-bold fas fa-store">${order.getShopName()}</span>
                                            <p class="ml-2 font-weight-lighter">${order.getOrderDate()}</p>
                                           <a href="userchatwithshop?sid=${order.getShopId()}" class="ml-auto text-blue-500">Chat</a>
                                            <button class="ml-2 text-blue-500">Xem Shop</button>
                                        </div>
                                        <div class="flex">
                                            <img src="${order.getImage()}" alt="image" class="w-24 h-24 object-cover rounded">
                                            <div class="ml-4 flex-1">
                                                <div class="font-bold">${order.getTitle()}</div>
                                                <div class="text-gray-500">Size: ${order.getSizeValue()}</div>
                                                <div class="text-gray-500">Màu: ${order.getColorValue()}</div>
                                                <div class="text-gray-500">Số lượng: ${order.getQuantity()}</div>
                                            </div>
                                            <div class="text-right">
                                                <fmt:formatNumber type="number" pattern="######" value="${order.getPrice()}" var="total"/>
                                                <div class="text-red-500 font-bold"><fmt:formatNumber value="${total}"/>VND</div>
                                                <c:if test="${order.getCode() != null}">
                                                    <fmt:formatNumber type="number" pattern="######" value="${order.getReducedAmount()}" var="reduced"/>
                                                    <div class="text-green-500 font-bold">${order.getCode()}</div>
                                                    <div class="text-green-500 font-bold">-<fmt:formatNumber value="${reduced}"/>VND</div>
                                                </c:if>
                                            </div>
                                        </div>
                                        <div class="flex items-center mt-4">
                                            <div class="text-red-500">${order.getStatusValue()}</div>
                                        </div>
                                    </div>
                                </c:if>
                            </c:forEach>

                        </div>
                        <div id="complete" class="hidden">


                            <c:forEach var="order" items="${requestScope.list}">
                                <c:if test="${order.getStatusId()==3}">
                                    <div class="bg-white p-4 rounded shadow mb-4">
                                        
                                        <div class="flex">
                                            <img src="${order.getImage()}" alt="image" class="w-24 h-24 object-cover rounded">
                                            <div class="ml-4 flex-1">
                                                <div class="font-bold">${order.getTitle()}</div>
                                                <div class="text-gray-500">Size: ${order.getSizeValue()}</div>
                                                <div class="text-gray-500">Màu: ${order.getColorValue()}</div>
                                                <div class="text-gray-500">Số lượng: ${order.getQuantity()}</div>
                                            </div>
                                            <div class="text-right">
                                                <fmt:formatNumber type="number" pattern="######" value="${order.getPrice()}" var="total"/>
                                                <div class="text-red-500 font-bold"><fmt:formatNumber value="${total}"/>VND</div>
                                                <c:if test="${order.getCode() != null}">
                                                    <fmt:formatNumber type="number" pattern="######" value="${order.getReducedAmount()}" var="reduced"/>
                                                    <div class="text-green-500 font-bold">${order.getCode()}</div>
                                                    <div class="text-green-500 font-bold">-<fmt:formatNumber value="${reduced}"/>VND</div>
                                                </c:if>
                                            </div>
                                        </div>
                                        <div class="flex items-center mt-4">
                                            <div class="text-red-500">${order.getStatusValue()}</div>
                                            <button onclick="confirmRebuyOrder('${order.getProductItemId()}', '${order.getShopProductId()}',
                                                            '${order.getQuantity()}')" class="ml-auto bg-red-500 text-white px-4 py-2 rounded">Mua lại</button>

                                            <c:if test="${order.getStatusId() == 3 && order.getStarRating() == null }">
                                                <button 
                                                    onclick="showFeedbackSection('${order.getShopName()}', '${order.getOrderDate()}', '${order.getShopProductId()}',
                                                                    '${order.getImage()}', '${order.getTitle()}', '${order.getSizeValue()}', '${order.getColorValue()}', '${order.getQuantity()}',
                                                                    '${order.getPrice()}', '${order.getOrderId()}', '${order.getOrderDetailId()}', '${order.getShopId()}');
                                                            hideButton('${order.getOrderDetailId()}');"
                                                    class="ml-2 bg-red-500 text-white px-4 py-2 rounded">
                                                    Đánh giá
                                                </button>
                                            </c:if>

                                        </div>
                                    </div>
                                </c:if>
                            </c:forEach>

                        </div>
                        <div id="cancel" class="hidden">


                            <c:forEach var="order" items="${requestScope.list}">
                                <c:if test="${order.getStatusId()==4}">
                                    <div class="bg-white p-4 rounded shadow mb-4">
                                        
                                        <div class="flex">
                                            <img src="${order.getImage()}" alt="image" class="w-24 h-24 object-cover rounded">
                                            <div class="ml-4 flex-1">
                                                <div class="font-bold">${order.getTitle()}</div>
                                                <div class="text-gray-500">Size: ${order.getSizeValue()}</div>
                                                <div class="text-gray-500">Màu: ${order.getColorValue()}</div>
                                                <div class="text-gray-500">Số lượng: ${order.getQuantity()}</div>
                                            </div>
                                            <div class="text-right">
                                                <fmt:formatNumber type="number" pattern="######" value="${order.getPrice()}" var="total"/>
                                                <div class="text-red-500 font-bold">${total}</div>
                                                <c:if test="${order.getCode() != null}">
                                                    <fmt:formatNumber type="number" pattern="######" value="${order.getReducedAmount()}" var="reduced"/>
                                                    <div class="text-green-500 font-bold">${order.getCode()}</div>
                                                    <div class="text-green-500 font-bold">-${reduced}</div>
                                                </c:if>
                                            </div>
                                        </div>
                                        <div class="flex items-center mt-4">
                                            <div class="text-red-500">${order.getStatusValue()}</div>
                                            <button onclick="confirmRebuyOrder('${order.getProductItemId()}', '${order.getShopProductId()}',
                                                            '${order.getQuantity()}')" class="ml-auto bg-red-500 text-white px-4 py-2 rounded">Mua lại</button>
                                        </div>
                                    </div>
                                </c:if>
                            </c:forEach>

                        </div>

                        <div id="feedback" class="hidden">
                            <div class="bg-white p-4 rounded shadow mb-4">
                                <div class="flex items-center mb-2">
                                    <span id="feedbackProductShopName" class="ml-2 font-bold fas fa-store"></span>
                                    <p id="feedbackProductOrderDate" class="ml-2 font-weight-lighter"></p>

                                </div>
                                <div class="flex">
                                    <img id="feedbackProductImage" src="" alt="image" class="w-24 h-24 object-cover rounded">
                                    <div class="ml-4 flex-1">
                                        <div id="feedbackProductTitle" class="font-bold"></div>
                                        <div id="feedbackProductSize" class="text-gray-500"></div>
                                        <div id="feedbackProductColor" class="text-gray-500"></div>
                                        <div id="feedbackProductQuantity" class="text-gray-500"></div>

                                    </div>
                                    <div class="text-right">
                                        <div id="feedbackProductPrice" class="text-red-500 font-bold"></div>
                                    </div>
                                </div>
                            </div>
                            <div class="card">
                                <form id="sendFeedbackForm" action="comment" >
                                    <input type="hidden" id="feedbackOrderId" name="odId" value=" "/>
                                    <input type="hidden" id="feedbackProductId" name="pId" value=""/>
                                    <input type="hidden" id="feedbackOrderDetailId" name="odDetailId" value=""/>
                                    <input type="hidden" id="feedbackShopId" name="sId" value=""/>
                                    <div class="container mt-4">
                                        <div class="row">

                                            <div class="col-10">
                                                <div class="comment-box ml-2">
                                                    <div class="rating">
                                                        <input type="radio" name="rating" value="5" id="5"><label for="5">☆</label>
                                                        <input type="radio" name="rating" value="4" id="4"><label for="4">☆</label>
                                                        <input type="radio" name="rating" value="3" id="3"><label for="3">☆</label>
                                                        <input type="radio" name="rating" value="2" id="2"><label for="2">☆</label>
                                                        <input type="radio" name="rating" value="1" id="1"><label for="1">☆</label>

                                                    </div>
                                                    <div class="comment-area">
                                                        <textarea name="comment" class="form-control" placeholder="Viết đánh giá của bạn" rows="4" ></textarea>
                                                    </div>
                                                    <div class="comment-btns mt-2">
                                                        <div class="row">
                                                            <div class="col-6">
                                                                <div class="flex items-center mt-4">
                                                                    <button type="button" class="bg-red-500 text-white px-4 py-2 rounded mr-2" onclick="cancelFeedBack()">Hủy</button>

                                                                    <button type="submit" id="sendFeedbackBtn" class="bg-red-500 text-white px-4 py-2 rounded">Gửi <i class="fa fa-long-arrow-right ml-1"></i></button>

                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>


                        <div id="cancelModal" class="modal">
                            <div class="modal-content">
                                <span class="close" onclick="closeModal()">&times;</span>
                                <h3>Vui lòng chọn lý do hủy đơn:</h3>
                                <select id="reasonSelect" onchange="toggleOtherReasonInput()">
                                    <option value="sản phẩm không cần thiết">Sản phẩm không cần thiết</option>
                                    <option value="tôi muốn chọn sản phẩm khác">Tôi muốn chọn sản phẩm khác</option>
                                    <option value="tôi chọn size sai">Tôi chọn size sai</option>
                                    <option value="tôi chọn màu sai">Tôi chọn màu sai</option>
                                    <option value="khác">Khác</option>
                                </select>
                                <div id="otherReasonDiv" style="display: none; margin-top: 10px;">
                                    <label for="otherReason">Lý do khác:</label>
                                    <input type="text" id="otherReason">
                                </div>
                                <input type="hidden" id="orderId">
                                <button onclick="submitCancelForm()">Xác nhận hủy</button>
                            </div>
                        </div>
                    </div>
                </div>
                </body>
                <style>
                    .modal {
                        display: none; /* Hidden by default */
                        position: fixed; /* Stay in place */
                        z-index: 1; /* Sit on top */
                        left: 0;
                        top: 0;
                        width: 100%; /* Full width */
                        height: 100%; /* Full height */
                        overflow: auto; /* Enable scroll if needed */
                        background-color: rgb(0,0,0); /* Fallback color */
                        background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
                    }

                    /* Modal content */
                    .modal-content {
                        background-color: #fefefe;
                        margin: 15% auto; /* 15% from the top and centered */
                        padding: 20px;
                        border: 1px solid #888;
                        width: 80%; /* Could be more or less, depending on screen size */
                        box-shadow: 0 5px 15px rgba(0,0,0,0.3);
                        border-radius: 10px;
                    }

                    /* Close button */
                    .close {
                        color: #aaa;
                        float: right;
                        font-size: 28px;
                        font-weight: bold;
                    }

                    .close:hover,
                    .close:focus {
                        color: black;
                        text-decoration: none;
                        cursor: pointer;
                    }

                    /* Highlight form elements */
                    .modal-content h3 {
                        font-size: 24px;
                        margin-bottom: 20px;
                        color: #333;
                    }

                    .modal-content select,
                    .modal-content input[type="text"] {
                        width: 100%;
                        padding: 10px;
                        margin-top: 10px;
                        margin-bottom: 20px;
                        border: 1px solid #ddd;
                        border-radius: 5px;
                        box-sizing: border-box;
                        font-size: 16px;
                    }

                    .modal-content select:focus,
                    .modal-content input[type="text"]:focus {
                        border-color: #007bff;
                        outline: none;
                        box-shadow: 0 0 5px rgba(0, 123, 255, 0.5);
                    }

                    .modal-content button {
                        background-color: #007bff;
                        color: white;
                        padding: 10px 20px;
                        border: none;
                        border-radius: 5px;
                        cursor: pointer;
                        font-size: 16px;
                    }

                    .modal-content button:hover {
                        background-color: #0056b3;
                    }
                </style>
                <script>

                    function confirmCancelOrder(orderId) {
                        document.getElementById("cancelModal").style.display = "block";
                        document.getElementById("orderId").value = orderId;
                    }

                    function closeModal() {
                        document.getElementById("cancelModal").style.display = "none";
                    }

                    function submitCancelForm() {
                        var reasonSelect = document.getElementById("reasonSelect");
                        var otherReasonInput = document.getElementById("otherReason");
                        var reason = reasonSelect.value;

                        if (reason === "khác") {
                            reason = otherReasonInput.value.trim();
                            if (reason === "") {
                                alert("Vui lòng nhập lý do hủy đơn.");
                                return false;
                            }
                        }

                        var orderId = document.getElementById("orderId").value;
                        console.log('lydo:' + reason);
                        // Mã hóa dữ liệu để gửi qua URL
                        var form = document.createElement("form");
                        form.method = "GET";
                        form.action = "cancelOrder";
                        form.acceptCharset = "UTF-8";

                        var orderIdField = document.createElement("input");
                        orderIdField.type = "hidden";
                        orderIdField.name = "orderDetailId";
                        orderIdField.value = orderId;
                        form.appendChild(orderIdField);

                        var reasonField = document.createElement("input");
                        reasonField.type = "hidden";
                        reasonField.name = "reason";
                        reasonField.value = reason;
                        form.appendChild(reasonField);

                        document.body.appendChild(form);
                        form.submit();
                    }

                    function toggleOtherReasonInput() {
                        var reasonSelect = document.getElementById("reasonSelect");
                        var otherReasonDiv = document.getElementById("otherReasonDiv");

                        if (reasonSelect.value === "khác") {
                            otherReasonDiv.style.display = "block";
                        } else {
                            otherReasonDiv.style.display = "none";
                        }
                    }

                    function confirmRebuyOrder(productItemId, shopProductId, quantity) {
                        var confirmCancel = confirm("Bạn có chắc chắn muốn đặt lại đơn này?");

                        if (confirmCancel) {
                            if (quantity <= 0) {
                                alert('Sản phẩm đã bán hết.');
                                return;
                            }

                            // Tạo một form động
                            var form = document.createElement('form');
                            form.method = 'POST';
                            form.action = 'addtocart';

                            // Tạo các input ẩn và thêm vào form
                            var inputProductItemId = document.createElement('input');
                            inputProductItemId.type = 'hidden';
                            inputProductItemId.name = 'productItemId';
                            inputProductItemId.value = productItemId;
                            form.appendChild(inputProductItemId);

                            var inputShopProductId = document.createElement('input');
                            inputShopProductId.type = 'hidden';
                            inputShopProductId.name = 'ShopProductId';
                            inputShopProductId.value = shopProductId;
                            form.appendChild(inputShopProductId);

                            var inputQuantity = document.createElement('input');
                            inputQuantity.type = 'hidden';
                            inputQuantity.name = 'quantity';
                            inputQuantity.value = quantity;
                            form.appendChild(inputQuantity);

                            // Thêm form vào body và gửi form
                            document.body.appendChild(form);
                            form.submit();
                        } else {
                            // Nếu người dùng không xác nhận, không thực hiện gì cả
                            return false;
                        }
                    }

                    document.getElementById('search').addEventListener('keypress', function (event) {
                        if (event.key === 'Enter') {
                            event.preventDefault();
                            const text = event.target.value;
                            if (text) {
                                searchProduct(text);
                            }
                        }
                    });

                    function showFeedbackSection(shopName, orderDate, shopProductId, image, title, size, color, quantity, price, orderId, orderDetailId, ShopId) {
                        // Hide all sections
                        var sections = document.querySelectorAll('#all, #wait, #process, #complete, #cancel');
                        sections.forEach(function (section) {
                            section.classList.add('hidden');
                        });
                        var navigationSection = document.querySelector('.flex.border-b.pb-2.mb-4');
                        var searchForm = document.querySelector('form[action="userOrderHistory"]');

                        // Check if elements are found before attempting to hide
                        if (navigationSection && searchForm) {
                            navigationSection.style.display = 'none';  // Hide the navigation section
                            searchForm.style.display = 'none';  // Hide the search form
                        }
                        // Show the feedback section
                        document.getElementById('feedback').classList.remove('hidden');

                        // Populate the feedback section with order details
                        document.getElementById('feedbackProductShopName').textContent = shopName;
                        document.getElementById('feedbackProductOrderDate').textContent = orderDate;
                        document.getElementById('feedbackProductImage').src = image;
                        document.getElementById('feedbackProductTitle').textContent = title;
                        document.getElementById('feedbackProductSize').textContent = 'Size: ' + size;
                        document.getElementById('feedbackProductColor').textContent = 'Màu: ' + color;
                        document.getElementById('feedbackProductQuantity').textContent = 'Số lượng: ' + quantity;
                        document.getElementById('feedbackProductPrice').textContent = price;

                        // Set the productId in the hidden input for the form submission
                        document.getElementById('feedbackProductId').value = shopProductId;
                        document.getElementById('feedbackOrderId').value = orderId;
                        document.getElementById('feedbackOrderDetailId').value = orderDetailId;
                        document.getElementById('feedbackShopId').value = ShopId;
                    }

                    // Check if the button should be hidden
                    document.addEventListener('DOMContentLoaded', function () {
                        var orderDetailId = '${order.getOrderDetailId()}';
                        var isButtonHidden = localStorage.getItem('hideButton_' + orderDetailId);

                        if (isButtonHidden) {
                            hideButton(orderDetailId);
                        }
                    });

                    // Function to hide the button and persist its state
                    function hideAndPersistButton(orderDetailId) {
                        hideButton(orderDetailId);
                        localStorage.setItem('hideButton_' + orderDetailId, true);
                    }

                    // Function to hide the button
                    function hideButton(orderDetailId) {
                        var button = document.getElementById('feedbackButton_' + orderDetailId);
                        if (button) {
                            button.style.display = 'none';
                        }
                    }
                    function cancelFeedBack() {
                        // Get the orderId from the hidden input field
                        var orderId = document.getElementById('feedbackOrderId').value;

                        // Redirect to userOrderHistory with the orderId parameter
                        window.location.href = "userOrderHistory?orderId=" + orderId;
                    }
                    $(document).ready(function () {
                        $('#sendFeedbackForm').submit(function (event) {
                            // Kiểm tra xem có radio button nào được chọn không
                            if (!$("input[name='rating']:checked").val()) {
                                // Nếu không có radio button nào được chọn, hiển thị thông báo
                                alert('Bạn phải chọn đánh giá sao trước khi gửi.');
                                event.preventDefault(); // Ngăn không gửi form đi
                            }
                        });
                    });
                    function highlightSelected(element, sectionId) {
                        // Remove 'selected' class from all links
                        var links = document.querySelectorAll('.flex a');
                        links.forEach(function (link) {
                            link.classList.remove('selected');
                        });

                        // Add 'selected' class to the clicked link
                        element.classList.add('selected');

                        // Hide all sections
                        var sections = document.querySelectorAll('#all, #wait, #process, #complete, #cancel, #feedback');
                        sections.forEach(function (section) {
                            section.classList.add('hidden');
                        });

                        // Show the selected section
                        document.getElementById(sectionId).classList.remove('hidden');
                    }
                </script>
                </html>
