<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
<link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/css/bootstrap.min.css" rel="stylesheet">
<link href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.4.0/mdb.min.css" rel="stylesheet">
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
<!DOCTYPE html>
<html>
    <style>
        .account-options {
            display: none;
            position: absolute;
            background: #fff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            z-index: 1000; /* Đảm bảo menu hiển thị phía trên các phần tử khác */
        }

        .account:hover .account-options {
            display: block; /* Hiển thị menu khi hover vào phần tử account */
        }
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
        <title>Đổi mật khẩu</title>
    </head>
    <header>
        <div class="p-3 text-center bg-white border-bottom">
            <div class="container">
                <div class="row gy-3">
                    <div class="col-lg-2 col-sm-4 col-4">
                        <a href="home" target="_blank" class="float-start">
                            <img src="image/logo.png" height="35" />
                        </a>
                    </div>

                    <div class="order-lg-last col-lg-5 col-sm-8 col-8">
                        <div class="d-flex float-end account">
                            <c:choose>
                                <c:when test="${not empty acc}">
                                    <span class="me-1 border rounded py-1 px-3 nav-link d-flex align-items-center">${acc.user}</span>
                                    <div class="account-options">
                                        <a href="add?username=${acc.user}" class="dropdown-item">Đăng truyện</a>
                                        <a href="changePass.jsp" class="dropdown-item">Đổi mật khẩu</a>
                                        <a href="logout" class="dropdown-item">Đăng xuất</a>
                                        <a href="deleteAccount" class="dropdown-item" onclick="return confirm('Bạn có chắc chắn muốn xóa tài khoản?')">Xóa tài khoản</a>
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <a href="login" class="me-1 border rounded py-1 px-3 nav-link d-flex align-items-center" target="_blank"> 
                                        <i class="fas fa-user-alt m-1 me-md-2"></i>
                                        <p class="d-none d-md-block mb-0">Đăng nhập</p> 
                                    </a>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                    <!-- Center elements -->

                    <!-- Right elements -->
                    <form action="search" method="get" class="col-lg-5 col-md-12 col-12 mb-0">
                        <div class="input-group float-center">
                            <input type="text" name="keyword" placeholder="Tìm truyện (tên truyện, tác giả)" required class="form-control" />
                            <input type="hidden" name="username" value="${acc.user}" />
                            <button type="submit" class="btn btn-primary shadow-0">
                                <i class="fas fa-search"></i>
                            </button>
                        </div>
                    </form>
                    <!-- Right elements -->
                </div>
            </div>
        </div>
        <!-- Jumbotron -->

        <!-- Heading -->
        <div class="bg-primary">
            <div class="container py-4">
                <!-- Breadcrumb -->
                <nav class="d-flex">
                    <h6 class="mb-0">
                        <a href="home" class="text-white-50">Trang chủ</a>
                        <span class="text-white-50 mx-2"> > </span>
                        <a class="text-white">${manga.name}</a>
                    </h6>
                </nav>
                <!-- Breadcrumb -->
            </div>
        </div>
        <!-- Heading -->
    </header>
    <section class="py-3 py-md-5 py-xl-8">
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <div class="mb-5">
                        <h2 class="display-5 fw-bold text-center">Đổi mật khẩu</h2>
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
                            <form action="updateAccount" method="POST">
                                <div class="row gy-3 overflow-hidden">
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
</html>