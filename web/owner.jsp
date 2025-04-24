<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
<link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/css/bootstrap.min.css" rel="stylesheet">
<link href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.4.0/mdb.min.css" rel="stylesheet">
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
    </style>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Danh sách sở hữu</title>
    </head>
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
                        <h6 class="mb-0"><a href="home" class="text-white">Trang chủ</a></h6>
                    </nav>
                    <!-- Breadcrumb -->
                </div>
            </div>
            <!-- Heading -->
        </header>
        <h1>Danh sách sở hữu</h1>
        <div class="container mt-4">
            <div class="container mt-4">
                <c:if test="${not empty ownerManga}">
                    <c:forEach var="manga" items="${ownerManga}">
                        <div class="d-flex align-items-center mb-4 p-3 border rounded shadow-sm">
                            <!-- Hình ảnh bên trái -->
                            <div class="me-3">
                                <a href="manga?id=${manga.id}&username=${acc.user}">
                                    <img src="${manga.imageUrl}" alt="${manga.name}" class="img-fluid" style="width: 120px; height: auto; object-fit: cover;" />
                                </a>
                            </div>

                            <!-- Thông tin ở giữa -->
                            <div class="flex-grow-1">
                                <h5><a href="manga?id=${manga.id}&username=${acc.user}" class="text-dark">${manga.name}</a></h5>
                                <p class="mb-1"><strong>Tác giả:</strong> ${manga.author}</p>
                                <p class="mb-1"><strong>Thể loại:</strong> 
                                    <c:forEach var="typeName" items="${manga.typeNames}" varStatus="status">
                                        <a href="type?name=${typeName}" class="text-decoration-none">${typeName}</a>${status.last ? '' : ', '}
                                    </c:forEach>
                                </p>
                                <p class="mb-1"><strong>Chương:</strong> ${manga.chap}</p>
                                <p class="mb-1"><strong>Tình trạng:</strong> ${manga.status}</p>
                                <p class="mb-1"><strong>Ngày phát hành:</strong> ${manga.formattedDate}</p>
                                <p class="mb-0"><strong>Lượt theo dõi:</strong> ${manga.followNumber}</p>
                            </div>

                            <!-- Các nút bên phải -->
                            <div class="ms-3 d-flex flex-column gap-2 text-end">
                                <a href="updateManga?id=${manga.id}&username=${acc.user}&mangaName=${manga.name}&userType=${acc.accountType}" class="btn btn-primary btn-sm">Thêm chap</a>
                                <a href="deleteChap?id=${manga.id}&userType=${acc.accountType}" class="btn btn-warning btn-sm">Xóa Chap</a>
                                <a href="editManga?id=${manga.id}&userType=${acc.accountType}" class="btn btn-info btn-sm">Sửa Manga</a>
                                <a href="deleteManga?id=${manga.id}" class="btn btn-danger btn-sm" onclick="return confirm('Bạn có chắc chắn muốn xóa ${manga.name}?')">Xóa</a>
                            </div>
                        </div>
                    </c:forEach>
                </c:if>
            </div>
            <c:if test="${empty ownerManga}">
                <p>Không có truyện</p>
            </c:if>
    </body>
</html>
