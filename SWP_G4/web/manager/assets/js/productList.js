/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


// Hàm để ẩn thông báo sau 4 giây
function hideMessage() {
    var messageElement = document.getElementById("message");
    if (messageElement) {
        setTimeout(function () {
            messageElement.style.display = "none";
        }, 4000); // 4 giây
    }
}

// Gọi hàm hideMessage khi trang đã tải xong
window.onload = hideMessage;