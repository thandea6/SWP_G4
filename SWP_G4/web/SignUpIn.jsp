<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" />
        <link rel="stylesheet" href="<%=request.getContextPath() %>/style/assets/css/stylePageSignIN_UP.css" />

        <title>Đăng nhập/ Đăng kí</title>
        <style>
            .remember-me {
                display: flex;
                align-items: center;
                margin-bottom: 15px;
            }
            .remember-me input {
                margin-right: 10px;
            }
        </style>
    </head>
    <body>
        <%
                String eror = (String) request.getAttribute("mess");
        %>

        <div class="function">    
            <button id="in">Đăng nhập</button>
            <button id="up">Đăng kí</button>
        </div>
        <% if(eror != null) {
        %>
        <h5>${eror}</h5>
        <% 
            } 
        %>
        <a href="<%=request.getContextPath() %>/home">← Trở về trang chủ</a>
        <p>${message}</p>
        <div class="container close" id="container">
            <div class="form-container sign-up-container">
                <h1 class="header--iconclose js--close--pay" style="padding: 16px;position: absolute;top: 0;right: 0;cursor: pointer;"><i class="fa fa-times" style="font-size: 18px;"></i></h1>
                <form method="post" action="/SWP/sign-up/shop">
                    <h1>Đăng kí cho cửa hàng</h1>
                    
                    <input type="text" style="display: none" name="account" value="shop"/>
                
                    <input type="text" placeholder="Người dùng" name="user" required/>
                    <input type="text" placeholder="Tên cửa hàng" name="nameShop" required/>
                    <input type="text" placeholder="Số điện thoại" name="phone" required/>
                    <input type="email" placeholder="Email" name="email" required/>
                    <input type="password" placeholder="Mật khẩu" name="password" required/>
                    <input type="password" placeholder="Nhập lại mật khẩu" name="rePassword" required/>
                    <button name="btn-signup">Đăng kí</button>
                    ${message}
                </form>
            </div>
            <div class="form-container sign-in-container">
                <form method="post" action="/SWP/sign-up/user">
                    <h1>Đăng ký cho khách hàng</h1>
                    
                    <input type="text" style="display: none" name="account" value="client"/>
                    <input type="text" placeholder="Người dùng" name="user" required/>
                    <input type="email" placeholder="Email" name="email" required/>
                    <input type="password" placeholder="Mật khẩu" name="password" required/>
                    <input type="password" placeholder="Nhập lại mật khẩu" name="rePassword" required/>
                    <input type="text" placeholder="Số điện thoại" name="phone" required/>
                    <button type="submit" name="btn-signup">Đăng ký</button>
                </form>
            </div>
            <div class="overlay-container">
                <div class="overlay">
                    <div class="overlay-panel overlay-left">
                        <h1>Chào mừng trở lại!</h1>
                        <p>Nhập thông tin cá nhân của bạn và bắt đầu hành trình với chúng tôi</p>
                        <button class="ghost" id="signIn">Đăng ký người dùng</button>
                    </div>
                    <div class="overlay-panel overlay-right">
                        <h1>Chào bạn!</h1>
                        <p>Nhập thông tin cá nhân của bạn và bắt đầu hành trình với chúng tôi</p>
                        <button class="ghost" id="signUp">Đăng ký cho cửa hàng</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="container" id="containerIn" style="width: 500px; min-height: 480px;">
            <div class="form-container sign-in-container" style="width: 100%;">
<<<<<<< HEAD
                <form action="/SWP_G4/login" method="post">
                    <h1>Đăng nhập</h1>
                    <div class="social-container">
                       
                        <a href="https://accounts.google.com/o/oauth2/auth?scope=email%20profile%20openid&redirect_uri=http://localhost:8080/SWP_G4/LoginGoogleHandler&response_type=code&client_id=406240029679-tser79dm9n4l3ci224sndgabgo8u5lun.apps.googleusercontent.com&approval_prompt=force" class="social"><i class="fab fa-google-plus-g"></i></a>
=======
                <form action="/SWP/login" method="post">
                    <h1>Đăng nhập</h1>
                    <div class="social-container">
                       
                        <a href="https://accounts.google.com/o/oauth2/auth?scope=email%20profile%20openid&redirect_uri=http://localhost:8080/SWP/LoginGoogleHandler&response_type=code&client_id=406240029679-tser79dm9n4l3ci224sndgabgo8u5lun.apps.googleusercontent.com&approval_prompt=force" class="social"><i class="fab fa-google-plus-g"></i></a>
>>>>>>> c8efd27cc5f43b5bce07f6445cf0142944da1b70
                       
                    </div>
                    <span>hoặc sử dụng tài khoản của bạn</span>
                    <input type="text"  style="display: none" name="account" />
                    <input type="text" placeholder="Người dùng" name="user" value="${cookie.cname.value}" required/>
                    <input type="password" placeholder="Mật khẩu" value="${cookie.cpass.value}" name="password" required/>
                    <div class="remember-me">
                        <input name="remember" value="1" type="checkbox" ${cookie.crem!=null?'checked':''} class="form-check-input" id="exampleCheck1">
                        <label class="form-check-label" for="exampleCheck1">Lưu</label>
                    </div>
                    <a href="Forgotpassword.jsp">Quên mật khẩu</a>
                    <button>Đăng nhập</button>
                    <h5 style="color: red">${requestScope.err}</h5>
                </form>
            </div>
        </div>
        <script>
            const signUpButton = document.getElementById('signUp');
            const signInButton = document.getElementById('signIn');
            const container = document.getElementById('container');
            const containerIn = document.getElementById('containerIn');
            const signIn = document.getElementById('in');
            const signUp = document.getElementById('up');

            signUpButton.addEventListener('click', () => {
                container.classList.add('right-panel-active');
            });

            signInButton.addEventListener('click', () => {
                container.classList.remove('right-panel-active');
            });

            signIn.addEventListener('click', () => {
                containerIn.classList.remove('close');
                container.classList.add('close');
            });

            signUp.addEventListener('click', () => {
                container.classList.remove('close');
                containerIn.classList.add('close');
            });
        </script>
    </body>
</html>
