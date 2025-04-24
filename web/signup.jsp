<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css">
<link rel="stylesheet" href="https://unpkg.com/bootstrap@5.3.3/dist/css/bootstrap.min.css">
<link rel="stylesheet" href="https://unpkg.com/bs-brain@2.0.4/components/logins/login-12/assets/css/login-12.css">
<!DOCTYPE html>
<html>
    <style>
        .back-button {
            position: absolute;
            top: 20px;
            left: 20px;
            background-color: black;
            color: white;
            padding: 10px;
            text-decoration: none;
            display: flex;
            align-items: center;
        }

        .back-button i {
            margin-right: 5px;
        }
    </style>
    <body>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Đăng kí</title>
    </head>
    <a href="home" class="back-button">
        <i class="bi bi-arrow-left"></i>Trang chủ
    </a>
    <section class="py-3 py-md-5 py-xl-8">
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <div class="mb-5">
                        <h2 class="display-5 fw-bold text-center">Đăng kí</h2>
                        <p class="text-center m-0">Đã có tài khoản? <a href="login.jsp">Đăng nhập</a></p>
                        <p class="text-center" style="font-weight: bold; color: ${messType == 'success' ? 'green' : 'red'};">
                            ${mess != null && !mess.isEmpty() ? mess : '&nbsp;'}
                        </p>
                    </div>
                </div>
            </div>
            <div class="row justify-content-center">
                <div class="col-12 col-lg-10 col-xl-8">
                    <div class="row gy-5 justify-content-center">
                        <div class="col-12 col-lg-5">
                            <form action="signup" method="POST">
                                <div class="row gy-3 overflow-hidden">
                                    <div class="col-12">
                                        <div class="form-floating mb-3">
                                            <input type="text" name="username" id="username" pattern="^(?!\s*$).+" class="form-control border-0 border-bottom rounded-0" placeholder="Tên đăng nhập" required>
                                            <label for="username" class="form-label">Tên đăng nhập</label>
                                        </div>
                                    </div>
                                    <div class="col-12">
                                        <div class="form-floating mb-3">
                                            <input type="text" name="email" id="email" pattern = "/^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/" class="form-control border-0 border-bottom rounded-0" placeholder="Email" required oninput="validateEmail()" onblur="validateEmail()">                                            <label for="email" class="form-label">Email</label>
                                        </div>
                                    </div>
                                    <div class="col-12">
                                        <div class="form-floating mb-3">
                                            <input type="password" name="password" id="password" pattern="^(?!\s*$).+" class="form-control border-0 border-bottom rounded-0" placeholder="Mật khẩu" required>
                                            <label for="password" class="form-label">Mật khẩu</label>
                                        </div>
                                    </div>
                                    <div class="col-12">
                                        <div class="form-floating mb-3">
                                            <input type="password" name="repassword" id="repassword" pattern="^(?!\s*$).+" class="form-control border-0 border-bottom rounded-0" placeholder="Nhập lại mật khẩu" required>
                                            <label for="repassword" class="form-label">Nhập lại mật khẩu</label>
                                        </div>
                                    </div>
                                    <div class="col-12">
                                        <div class="d-grid">
                                            <button class="btn btn-lg btn-dark rounded-0 fs-6" type="submit">Đăng kí</button>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
</body>
</html>
<script>
    function validateEmail() {
        var emailField = document.getElementById('email');
        var emailValue = emailField.value;
        var emailError = document.getElementById('emailError');

        // Kiểm tra email có chứa dấu cách không
        if (/\s/.test(emailValue)) {
            emailError.style.display = 'block';
            emailField.setCustomValidity("Email không được chứa dấu cách.");
        } else {
            // Kiểm tra email có đúng định dạng hay không
            var emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
            if (!emailPattern.test(emailValue)) {
                emailError.style.display = 'block';
                emailField.setCustomValidity("Email không hợp lệ.");
            } else {
                emailError.style.display = 'none';
                emailField.setCustomValidity('');
            }
        }
    }
</script>