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
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Quên mật khẩu</title>
    </head>
    <a href="login.jsp" class="back-button">
        <i class="bi bi-arrow-left"></i>Quay lại
    </a>
    <section class="py-3 py-md-5 py-xl-8">
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <div class="mb-5">
                        <h2 class="display-5 fw-bold text-center">Quên mật khẩu</h2>
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
                            <form action="forget" method="POST">
                                <div class="row gy-3 overflow-hidden">
                                    <div class="col-12">
                                        <div class="form-floating mb-3">
                                            <input type="text" name="username" id="username" class="form-control border-0 border-bottom rounded-0" placeholder="Tên đăng nhập" required>
                                            <label for="username" class="form-label">Tên đăng nhập</label>
                                        </div>
                                    </div>
                                    <div class="col-12">
                                        <div class="form-floating mb-3">
                                            <input type="text" name="email" id="email" class="form-control border-0 border-bottom rounded-0" placeholder="Email" required>
                                            <label for="email" class="form-label">Email</label>
                                        </div>
                                    </div>
                                    <div class="col-12">
                                        <div class="form-floating mb-3">
                                            <input type="password" name="password" id="password" class="form-control border-0 border-bottom rounded-0" placeholder="Mật khẩu mới" required>
                                            <label for="password" class="form-label">Mật khẩu mới</label>
                                        </div>
                                    </div>
                                    <div class="col-12">
                                        <div class="form-floating mb-3">
                                            <input type="password" name="repassword" id="repassword" class="form-control border-0 border-bottom rounded-0" placeholder="Nhập lại mật khẩu mới" required>
                                            <label for="repassword" class="form-label">Nhập lại mật khẩu mới</label>
                                        </div>
                                    </div>
                                    <div class="col-12">
                                        <div class="d-grid">
                                            <button class="btn btn-lg btn-dark rounded-0 fs-6" type="submit">Thay đổi</button>
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