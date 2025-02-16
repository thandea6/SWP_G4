<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Left</title>
    </head>
    <body>
        <!-- Danh Mục Sản Phẩm -->
        <style>
            .submit-button {
                display: inline-block;
                padding: 10px 20px;
                font-size: 16px;
                color: #fff;
                background-color: #007bff;
                border: none;
                border-radius: 5px;
                cursor: pointer;
                text-align: center;
                text-decoration: none;
                transition: background-color 0.3s ease;
            }

            .submit-button:hover {
                background-color: #0056b3;
            }

            .container__DMSP-active {
                margin: 10px 0;
            }
        </style>
        <div class="grid__row">
            <div class="grid__column-2">
                <div class="container__DMSP">
                    <form action="searchHome" id="searchHome" method="get">
                        <h4 class="container__DMSP-heading">
                            <i class="container__DMSP-icon fas fa-list-ul"></i>
                            Danh mục sản phẩm
                        </h4>
                        <input type="hidden" value="${requestScope.key}" name="key"/>
                        <c:set var="cat" value="${requestScope.data1}"/>
                        <c:set var="cid" value="${requestScope.cid != null ? requestScope.cid : 0}"/>
                        <c:set value="${requestScope.cb}" var="cb"/>
                        <ul class="container__DMSP-menu">
                            <li class="container__DMSP-item">
                                <a href="#" class="container__DMSP-active">
                                    <input class="fas fa-caret-right" type="checkbox" name="cid" id="cid0" ${cid == 0 ? "checked" : ""} value="0" onclick="setCheck(this, 'cid')"/>Sản Phẩm<br/>
                                </a>
                            </li>
                            <c:forEach items="${cat}" var="c" varStatus="status">
                                <li class="container__DMSP-item">
                                    <input type="checkbox" name="cid" id="cid${c.categoryId}" value="${c.categoryId}" 
                                           ${c.categoryId == cid ? "checked" : ""} ${cb[status.index + 1] ? "checked" : ""} 
                                           onclick="setCheck(this, 'cid')" />${c.categoryName}
                                </li>
                            </c:forEach>
                        </ul>

                        <h4 class="container__DMSP-heading">
                            <i class="container__DMSP-icon fas fa-list-ul"></i>
                            Danh mục thương hiệu
                        </h4>
                        <c:set var="bra" value="${requestScope.listB}"/>
                        <c:set var="bid" value="${requestScope.bid != null ? requestScope.bid : 0}"/>
                        <c:set value="${requestScope.bb}" var="bb"/>
                        <ul class="container__DMSP-menu">
                            <li class="container__DMSP-item">
                                <a href="#" class="container__DMSP-active">
                                    <input class="fas fa-caret-right" type="checkbox" name="bid" id="bid0" ${bid == 0 ? "checked" : ""} value="0" 
                                           onclick="setCheck(this, 'bid')"/>Thương Hiệu<br/>
                                </a>
                            </li>
                            <c:forEach items="${bra}" var="b" varStatus="status">
                                <li class="container__DMSP-item">
                                    <input type="checkbox" name="bid" id="bid${b.brandId}" value="${b.brandId}" 
                                           ${b.brandId == bid ? "checked" : ""} ${bb[status.index + 1] ? "checked" : ""} 
                                           onclick="setCheck(this, 'bid')" />${b.brandName}
                                </li>
                            </c:forEach>
                        </ul>

                        <h4 class="container__DMSP-heading">
                            <i class="container__DMSP-icon fas fa-arrow-down"></i>
                            Khoảng giá
                        </h4>
                        <fmt:formatNumber type="number" pattern="######" value="${requestScope.min != null ? requestScope.min : 0}" var="formattedMinValue" />
                        <fmt:formatNumber type="number" pattern="######" value="${requestScope.max != null ? requestScope.max : 0}" var="formattedMaxValue" />

                        từ &nbsp;&nbsp;&nbsp;&nbsp;<input class="container__DMSP-active" step="100000" id="minValue" type="number" name="minValue" style="width: 120px;" value="${formattedMinValue}"/><br> 
                        đến&nbsp;&nbsp;<input class="container__DMSP-active" id="maxValue" step="100000" type="number" name="maxValue" style="width: 120px;" value="${formattedMaxValue}"/>
                        <br>
                        <button type="button" onclick="submitPriceForm()">Áp dụng</button>
                        <button type="button" onclick="resetFilters()">Tạo lại</button>

                    </form>
                </div>
            </div>

    </body>
    <script>
        // Lưu trạng thái checkbox category vào localStorage
        function saveCategoryCheckboxState() {
            var checkboxes = document.getElementsByName('cid');
            var checkboxValues = [];
            for (var i = 0; i < checkboxes.length; i++) {
                checkboxValues.push(checkboxes[i].checked);
            }
            localStorage.setItem('categoryCheckboxValues', JSON.stringify(checkboxValues));
        }

        // Khôi phục trạng thái checkbox category từ localStorage
        function restoreCategoryCheckboxState() {
            var storedValues = JSON.parse(localStorage.getItem('categoryCheckboxValues'));
            var checkboxes = document.getElementsByName('cid');
            var isAnyChecked = false;

            if (storedValues) {
                for (var i = 0; i < checkboxes.length; i++) {
                    checkboxes[i].checked = storedValues[i];
                    if (storedValues[i]) {
                        isAnyChecked = true;
                    }
                }
            }

            if (!isAnyChecked) {
                checkboxes[0].checked = true;
            }
        }

        // Lưu trạng thái checkbox brand vào localStorage
        function saveBrandCheckboxState() {
            var checkboxes = document.getElementsByName('bid');
            var checkboxValues = [];
            for (var i = 0; i < checkboxes.length; i++) {
                checkboxValues.push(checkboxes[i].checked);
            }
            localStorage.setItem('brandCheckboxValues', JSON.stringify(checkboxValues));
        }

        // Khôi phục trạng thái checkbox brand từ localStorage
        function restoreBrandCheckboxState() {
            var storedValues = JSON.parse(localStorage.getItem('brandCheckboxValues'));
            var checkboxes = document.getElementsByName('bid');
            var isAnyChecked = false;

            if (storedValues) {
                for (var i = 0; i < checkboxes.length; i++) {
                    checkboxes[i].checked = storedValues[i];
                    if (storedValues[i]) {
                        isAnyChecked = true;
                    }
                }
            }

            if (!isAnyChecked) {
                checkboxes[0].checked = true;
            }
        }



        // Gọi hàm khôi phục trạng thái checkbox khi trang được tải lại
        window.onload = function () {
        <% 
            // Kiểm tra trạng thái khởi động lại của server trong session
            Boolean isServerRestarted = (Boolean) session.getAttribute("isServerRestarted");
            if (isServerRestarted == null || !isServerRestarted) {
                // Đặt các giá trị mặc định và lưu trạng thái vào session
                session.setAttribute("isServerRestarted", true);
        %>
            localStorage.removeItem('categoryCheckboxValues');
            localStorage.removeItem('brandCheckboxValues');
            document.getElementById('minValue').value = 0;
            document.getElementById('maxValue').value = 0;
        <% 
            }
        %>
            restoreCategoryCheckboxState();
            restoreBrandCheckboxState();
        };

        // Hàm xử lý khi checkbox được chọn
        function setCheck(obj, name) {
            var checkboxes = document.getElementsByName(name);
            if (obj.value === '0') {
                if (obj.checked) {
                    for (var i = 1; i < checkboxes.length; i++) {
                        checkboxes[i].checked = false;
                    }
                } else {
                    obj.checked = true; // Không cho phép bỏ chọn giá trị 0 nếu nó đang được chọn
                }
            } else {
                if (obj.checked) {
                    checkboxes[0].checked = false;
                } else {
                    var isAnyChecked = false;
                    for (var i = 1; i < checkboxes.length; i++) {
                        if (checkboxes[i].checked) {
                            isAnyChecked = true;
                            break;
                        }
                    }
                    if (!isAnyChecked) {
                        checkboxes[0].checked = true; // Tự động tích 0 nếu không có giá trị nào được chọn
                    }
                }
            }

            // Cập nhật trạng thái checkbox category
            if (name === 'cid') {
                saveCategoryCheckboxState();
            }
            // Cập nhật trạng thái checkbox brand
            else if (name === 'bid') {
                saveBrandCheckboxState();
            }

            // Sau khi cập nhật, submit form
            submitForm();
        }

        // Function để submit form
        function submitForm() {
            var form = document.getElementById('searchHome');
            form.submit();
        }

        // Function để submit form khi bấm button
        function submitPriceForm() {
            var minValue = parseInt(document.getElementById('minValue').value);
            var maxValue = parseInt(document.getElementById('maxValue').value);

            if (maxValue < minValue) {
                alert('Hãy nhập đúng giá trị');
                return;
            }

            var form = document.getElementById('searchHome');
            form.submit();
        }
        function resetFilters() {
            // Xóa trạng thái lưu trong localStorage
            localStorage.removeItem('categoryCheckboxValues');
            localStorage.removeItem('brandCheckboxValues');

            // Đặt lại trạng thái của các checkbox category
            var categoryCheckboxes = document.getElementsByName('cid');
            for (var i = 0; i < categoryCheckboxes.length; i++) {
                categoryCheckboxes[i].checked = false;
            }
            categoryCheckboxes[0].checked = true; // Đặt lại giá trị đầu tiên về checked

            // Đặt lại trạng thái của các checkbox brand
            var brandCheckboxes = document.getElementsByName('bid');
            for (var i = 0; i < brandCheckboxes.length; i++) {
                brandCheckboxes[i].checked = false;
            }
            brandCheckboxes[0].checked = true; // Đặt lại giá trị đầu tiên về checked

            // Đặt lại giá trị của các input khoảng giá
            document.getElementById('minValue').value = 0;
            document.getElementById('maxValue').value = 0;
            var form = document.getElementById('searchHome');
            form.submit();
        }
    </script>

</html>
