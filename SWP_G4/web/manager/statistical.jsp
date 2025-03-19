<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.Arrays"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Statistic Page</title>
        <script src="https://www.chartjs.org/dist/2.9.3/Chart.min.js"></script>
        <script src="https://www.chartjs.org/samples/latest/utils.js"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <style>
            canvas {
                -moz-user-select: none;
                -webkit-user-select: none;
                -ms-user-select: none;
            }
            #body{
                margin-right: -300px;
                margin-left: 20px;
            }
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

            .product-row {
                border-bottom: 2px solid orange;
                margin-bottom: 20px;
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
                width: fit-content;
                text-decoration: none;
            }

            .btn:hover {
                background-color: #ff7f00;
            }
            #hideTopProductsLink {
    display: none;
    text-align: center;
    background-color: #ff6600; 
    color: white; 
    padding: 10px 20px;
    border-radius: 5px;
    font-size: 16px;
    font-weight: bold;
    text-decoration: none;
    margin: 20px auto;
    display: block;
    width: fit-content;
    transition: background-color 0.3s ease-in-out;
}

#hideTopProductsLink:hover {
    background-color: #e65c00; 
    text-decoration: none;
}
#topProductsBtn{
      font-weight: bold;
}
        </style>
    </head>

    <body class="bg-gray-100">
        <div class="mx-auto mt-10">
            <div class="flex">
                <jsp:include page="../common/menu.jsp"></jsp:include>

                    <div class="w-full bg-white p-6 rounded shadow ml-6" style="background-color: lightgoldenrodyellow">
                        <button id="topProductsBtn" class="btn btn-primary">Top sản phẩm bán chạy</button>

                        <div class="col mt-4" id="Common">
                            <h2 class="text-lg font-semibold mb-4">Thống kê chung</h2>
                            <table class="table table-bordered">
                                <thead>
                                    <tr>
                                        <th scope="col">Thông tin</th>
                                        <th scope="col">Số liệu</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>Tổng số lượng sản phẩm đã bán</td>
                                        <td>${requestScope.totalQuantitySold}</td>
                                    </tr>
                                    <tr>
                                        <td>Tổng số tiền thu nhập được</td>
                                         <td><fmt:formatNumber value="${requestScope.totalPriceSold}"/> VND</td>
                                    </tr>
                                    <tr>
                                        <td>Tổng số khách đã mua sản phẩm</td>
                                        <td>${requestScope.userCount}</td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>

                        <div class="row" id="chart">
                             <c:if test="${not empty dataListDay}">
                            <div class="col-md-6">
                                <h2 class="text-lg font-semibold mb-4">Biểu đồ sản phẩm đã bán theo ngày</h2>
                                <div style="width:100%; margin-top: 50px;" id="dailyChartContainer">
                                    <canvas id="daily-chart" width="600" height="450"></canvas>
                                </div>
                            </div>
                        </c:if>
                        <c:if test="${not empty dataListMonth}">
                            <div class="col-md-6">
                                <h2 class="text-lg font-semibold mb-4">Biểu đồ đơn hàng theo tháng</h2>
                                <div style="width:100%;" id="lineChartContainer">
                                    <canvas id="line-chart" width="600" height="450"></canvas>
                                </div>
                            </div>
                        </c:if>
                       
                    </div>

                    <form id="dateForm" action="statistical" method="post">
                        <label>Chọn tháng và năm để hiển thị biểu đồ:</label>
                        <div class="form-group">
                            <label for="month">Tháng:</label>
                            <select class="form-control" id="month" name="month">
                                <% for (int i = 1; i <= 12; i++) { %>
                                <option><%= i %></option>
                                <% } %>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="year">Năm:</label>
                            <select class="form-control" id="year" name="year">
                                <% for (int i = 2020; i <= 2030; i++) { %>
                                <option><%= i %></option>
                                <% } %>
                            </select>
                        </div>
                        <button type="submit" class="btn btn-primary">Gửi</button>
                    </form>

                    <div id="topProductsTable" style="display: none;">
                        <table>
                            <thead>
                                <tr>
                                    <th>STT</th>
                                    <th>Tên</th>
                                    <th>Ảnh</th>
                                    <th>Giá</th>
                                    <th>Thể Loại</th>
                                    <th>Thương Hiệu</th>
                                    <th>Số Lượng Đã Bán</th>
                                    <th>Tổng Tiền Đã Bán</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${listSold}" var="s" varStatus="status">
                                    <tr class="product-row">
                                        <td>${status.index + 1}</td>
                                        <td>${s.productLineName}</td>
                                        <td>
                                            <img src="${s.image}" alt="Product Image">
                                        </td>
                                         <td> <fmt:formatNumber value="${s.price}"/> VND</td>
                                        <td>${s.categoryName}</td>
                                        <td>${s.brandName}</td>
                                        <td>${s.totalQuantitySold}</td>
                                       
                                        <td> <fmt:formatNumber value="${s.totalPriceSold}"/> VND</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                     <a id="hideTopProductsLink" href="statistical" style="display: none;">Quay Lại</a>
                </div>
            </div>
        </div>

        <script>
            document.getElementById('topProductsBtn').addEventListener('click', function(event) {
                document.getElementById('chart').style.display = 'none';
                document.getElementById('Common').style.display = 'none';
                document.getElementById('dateForm').style.display = 'none';
                document.getElementById('topProductsTable').style.display = 'block';
                document.getElementById('hideTopProductsLink').style.display = 'inline-block';
            });

            document.getElementById('hideTopProductsLink').addEventListener('click', function(event) {
                document.getElementById('chart').style.display = 'flex';
                document.getElementById('Common').style.display = 'block';
                document.getElementById('dateForm').style.display = 'block';
                document.getElementById('topProductsTable').style.display = 'none';
                document.getElementById('hideTopProductsLink').style.display = 'none';
            });

            // Prepare the data from the servlet as JSON
            var dataFromServlet = ${dataListMonth != null ? dataListMonth : '[]'};
            var dailyDataFromServlet = ${dataListDay != null ? dataListDay : '[]'};

            // Line chart for monthly data
            if (dataFromServlet.length > 0) {
                var ctxLine = document.getElementById('line-chart').getContext('2d');
                var lineChart = new Chart(ctxLine, {
                    type: 'line',
                    data: {
                        labels: ['Tháng 1', 'Tháng 2', 'Tháng 3', 'Tháng 4', 'Tháng 5', 'Tháng 6', 'Tháng 7', 'Tháng 8', 'Tháng 9', 'Tháng 10', 'Tháng 11', 'Tháng 12'],
                        datasets: [{
                                label: 'Đơn theo tháng',
                                backgroundColor: 'rgba(255,99,132,0.2)',
                                borderColor: 'rgba(255,99,132,1)',
                                data: dataFromServlet,
                                fill: false,
                            }]
                    },
                    options: {
                        responsive: true,
                        scales: {
                            yAxes: [{
                                    ticks: {
                                        beginAtZero: true
                                    }
                                }]
                        }
                    }
                });
            }

            // Bar chart for daily data
            if (dailyDataFromServlet.length > 0) {
                var ctxBar = document.getElementById('daily-chart').getContext('2d');
                var dailyChart = new Chart(ctxBar, {
                    type: 'bar',
                    data: {
                        labels: ['Ngày 1', 'Ngày 2', 'Ngày 3', 'Ngày 4', 'Ngày 5', 'Ngày 6', 'Ngày 7', 'Ngày 8', 'Ngày 9', 'Ngày 10', 'Ngày 11', 'Ngày 12', 'Ngày 13', 'Ngày 14', 'Ngày 15', 'Ngày 16', 'Ngày 17', 'Ngày 18', 'Ngày 19', 'Ngày 20', 'Ngày 21', 'Ngày 22', 'Ngày 23', 'Ngày 24', 'Ngày 25', 'Ngày 26', 'Ngày 27', 'Ngày 28', 'Ngày 29', 'Ngày 30', 'Ngày 31'],
                        datasets: [{
                                label: 'Đơn theo ngày',
                                backgroundColor: 'rgba(54, 162, 235, 0.2)',
                                borderColor: 'rgba(54, 162, 235, 1)',
                                borderWidth: 1,
                                data: dailyDataFromServlet
                            }]
                    },
                    options: {
                        responsive: true,
                        scales: {
                            yAxes: [{
                                    ticks: {
                                        beginAtZero: true
                                    }
                                }]
                        }
                    }
                });
            }
        </script>
    </body>
</html>
