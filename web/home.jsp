<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
<link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/css/bootstrap.min.css" rel="stylesheet">
<link href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.4.0/mdb.min.css" rel="stylesheet">
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page import="dal.MangaDAO"%>
<%@page import="model.Manga"%>
<%@page import="dal.TypeDAO"%>
<%@page import="model.Type"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Trang chủ</title>
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
        .menu {
            display: flex;
            gap: 20px;
            margin-top: 10px;
        }
        .type-content {
            display: none;
            position: absolute;
            background-color: #fff;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
        }
        .type:hover .type-content {
            display: flex;
            gap: 20px;
        }
        .column {
            padding: 10px;
        }
        #manga-list .ul .li img{
            width:30%;
        }
        .manga-list ul {
            display: flex;
            flex-wrap: wrap;
            list-style-type: none;
            padding: 0;
        }

        .manga-list li {
            flex: 0 0 20%; /* Để mỗi item chiếm 20% chiều rộng, chia đều cho 5 phần tử */
            box-sizing: border-box;
            padding: 10px;
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

            <div class="bg-primary">
                <div class="container py-4">
                    <!-- Breadcrumb -->
                    <nav class="d-flex">
                        <h6 class="mb-0 account">
                            <a class="text-white me-4">Thể loại</a>
                            <div class="account-options">
                                <a href="type?name=Hành động&username=${acc.user}">Hành động</a><br>
                                <a href="type?name=Phiêu lưu&username=${acc.user}">Phiêu lưu</a><br>
                                <a href="type?name=Bí ẩn&username=${acc.user}">Bí ẩn</a><br>
                                <a href="type?name=Kinh dị&username=${acc.user}">Kinh dị</a><br>
                                <a href="type?name=Lãng mạn&username=${acc.user}">Lãng mạn</a><br>
                                <a href="type?name=Tâm lý học&username=${acc.user}">Tâm lý học</a><br>
                                <a href="type?name=Hài hước&username=${acc.user}">Hài hước</a><br>
                                <a href="type?name=Chính kịch&username=${acc.user}">Chính kịch</a><br>
                                <a href="type?name=Đời thường&username=${acc.user}">Đời thường</a><br>
                                <a href="type?name=Thể thao&username=${acc.user}">Thể thao</a><br>
                                <a href="type?name=Lịch sử&username=${acc.user}">Lịch sử</a><br>
                                <a href="type?name=Khoa học viễn tưởng&username=${acc.user}">Khoa học viễn tưởng</a><br>
                                <a href="type?name=Siêu nhiên&username=${acc.user}">Siêu nhiên</a><br>
                                <a href="type?name=Giả tưởng&username=${acc.user}">Giả tưởng</a><br>
                                <a href="type?name=Thần bí&username=${acc.user}">Thần bí</a>
                            </div>
                            </h6>
                            <h6 class="mb-0">
                            <c:choose>
                                <c:when test="${acc.accountType == 'admin'}">
                                    <a class="type-icon text-white me-4" href="manage">Quản lý người dùng</a>
                                    <a class="type-icon text-white me-4" href="owner?username=${acc.user}">Danh sách sở hữu</a>
                                    <a class="type-icon text-white me-4" href="follow?username=${acc.user}">Danh sách theo dõi</a>
                                </c:when>
                                <c:when test="${acc.accountType == 'uploader'}">
                                    <a class="type-icon text-white me-4" href="owner?username=${acc.user}">Danh sách sở hữu</a>
                                    <a class="type-icon text-white me-4" href="follow?username=${acc.user}">Danh sách theo dõi</a>
                                </c:when>
                                <c:when test="${acc.accountType == 'reader'}">
                                    <a class="type-icon text-white me-4 " href="follow?username=${acc.user}">Danh sách theo dõi</a>
                                </c:when>
                            </c:choose>
                        </h6>
                    </nav>
                    <!-- Breadcrumb -->
                </div>
            </div>
            <!-- Heading -->
        </header>
        <h1>Truyện hot</h1>
        <div class="manga-list">
            <ul>
                <c:forEach var="manga" items="${top5Manga}">
                    <li class="manga-item">
                        <h3><a href="manga?id=${manga.id}&username=${acc.user}">${manga.name}</a></h3>
                        <a href="manga?id=${manga.id}&username=${acc.user}">
                            <img src="${manga.imageUrl}" alt="${manga.name}" style="width: 150px; height: auto;" />
                        </a>
                        <p>Tác giả: ${manga.author}</p>
                        <p>Người đăng: ${manga.owner}</p>
                        <p>Thể loại: 
                            <c:forEach var="typeName" items="${manga.typeNames}" varStatus="status">
                                <a href="type?name=${typeName}">${typeName}</a>${status.last ? '' : ', '}
                            </c:forEach>
                        </p>
                        <p>Chương: ${manga.chap}</p>
                        <p>Tình trạng: ${manga.status}</p>
                        <p>Ngày phát hành: ${manga.formattedDate}</p>
                        <p>Lượt theo dõi: ${manga.followNumber}</p>
                    </li>
                </c:forEach>
            </ul>
        </div>
        <h1>Truyện mới cập nhật</h1>
        <div class="manga-list">
            <ul>
                <c:forEach var="manga" items="${mangaList}">
                    <li class="manga-item">
                        <h3><a href="manga?id=${manga.id}&username=${acc.user}">${manga.name}</a></h3>
                        <a href="manga?id=${manga.id}&username=${acc.user}">
                            <img src="${manga.imageUrl}" alt="${manga.name}" style="width: 150px; height: auto;" />
                        </a>
                        <p>Tác giả: ${manga.author}</p>
                        <p>Người đăng: ${manga.owner}</p>
                        <p>Thể loại: 
                            <c:forEach var="typeName" items="${manga.typeNames}" varStatus="status">
                                <a href="type?name=${typeName}">${typeName}</a>${status.last ? '' : ', '}
                            </c:forEach>
                        </p>
                        <p>Chương: ${manga.chap}</p>
                        <p>Tình trạng: ${manga.status}</p>
                        <p>Ngày phát hành: ${manga.formattedDate}</p>
                        <p>Lượt theo dõi: ${manga.followNumber}</p>
                    </li>
                </c:forEach>
            </ul>
        </div>

        <div class="pagination d-flex justify-content-center mt-4">
    <nav aria-label="Page navigation">
        <ul class="pagination">
            <!-- Nút trang trước -->
            <c:if test="${currentPage > 1}">
                <li class="page-item">
                    <a class="page-link" href="?page=${currentPage - 1}" aria-label="Previous">
                        <span aria-hidden="true">&laquo; Trước</span>
                    </a>
                </li>
            </c:if>

            <!-- Các trang -->
            <c:forEach begin="1" end="${totalPages}" var="i">
                <c:if test="${i == currentPage}">
                    <li class="page-item active"><span class="page-link">${i}</span></li>
                </c:if>
                <c:if test="${i != currentPage}">
                    <li class="page-item"><a class="page-link" href="?page=${i}">${i}</a></li>
                </c:if>
            </c:forEach>

            <!-- Nút trang sau -->
            <c:if test="${currentPage < totalPages}">
                <li class="page-item">
                    <a class="page-link" href="?page=${currentPage + 1}" aria-label="Next">
                        <span aria-hidden="true">Sau &raquo;</span>
                    </a>
                </li>
            </c:if>
        </ul>
    </nav>
</div>

    </body>
</html>

</html>
