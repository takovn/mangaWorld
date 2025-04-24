<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
<link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/css/bootstrap.min.css" rel="stylesheet">
<link href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.4.0/mdb.min.css" rel="stylesheet">
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>Quản lý người dùng</title>
    </head>
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
        table {
            width: 100%;
            border-collapse: collapse;
        }

        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }

        th {
            background-color: #f2f2f2;
            text-align: center;
        }

        td form {
            display: flex;
            gap: 8px;
            align-items: center;
        }

        input[type="text"], input[type="email"], select {
            width: 100%;
            padding: 4px;
            box-sizing: border-box;
        }

        button {
            padding: 4px 8px;
        }

        .action-buttons {
            display: flex;
            gap: 8px;
        }
    </style>
    <body>
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
        <h1>Quản lý người dùng</h1>
        ${mess != null && !mess.isEmpty() ? mess : '&nbsp;'}
        <table>
            <tr>
                <th>ID</th>
                <th>Tên người dùng</th>
                <th>Mật khẩu</th>
                <th>Email</th>
                <th>Loại tài khoản</th>
                <th>Hành động</th>
            </tr>
            <c:forEach var="account" items="${accounts}">
                <tr>
                    <td>${account.uid}</td>
                    <td>${account.user}</td>
                <form action="manage" method="post">
                    <input type="hidden" name="uid" value="${account.uid}" />
                    <td>
                        <input type="text" name="pass" value="${account.pass}" required />
                    </td>
                    <td>
                        <input type="email" name="email" value="${account.email}" required />
                    </td>
                    <td>${account.accountType}</td>
                    <td class="action-buttons">
                        <c:choose>
                            <c:when test="${account.accountType == 'admin'}">
                            </c:when>
                            <c:otherwise>
                                <button type="submit" name="action" value="update">Cập nhật</button>
                                <button type="submit" name="action" value="delete" onclick="return confirm('Bạn có chắc chắn muốn xóa người dùng này?')">Xóa</button>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </form>
            </tr>
        </c:forEach>
    </table>

</body>
</html>
