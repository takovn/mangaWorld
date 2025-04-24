<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
<link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/css/bootstrap.min.css" rel="stylesheet">
<link href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.4.0/mdb.min.css" rel="stylesheet">
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Thêm Truyện</title>
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
            .type-btn {
                display: inline-block;
                padding: 5px 10px;
                margin: 5px;
                border: 1px solid #ccc;
                border-radius: 5px;
                background-color: #f0f0f0;
                cursor: pointer;
            }
            .type-btn.selected {
                background-color: #00bfff; /* Màu nền khi được chọn */
                color: white;
            }
        </style>
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
        <h1>Thêm Truyện Mới</h1>
        <form action="add" method="post" onsubmit="return validateForm()">
            <input value="${username}"  name="username" type="hidden">
            Tên: <input name="name" type="text" pattern="^(?!\s*$).+" required title="Tên không được để trống hoặc chỉ có dấu cách">
            <br>
            Link Ảnh: <input name="imageUrl" type="url" pattern="https?://.*\.(jpg|jpeg|png|gif|bmp|webp)$" required>
            <br>
            Tác giả: <input name="author" pattern="^(?!\s*$).+" required title="Tên không được để trống hoặc chỉ có dấu cách">
            <br>
            Thể loại:
            <div class="type-list">
                <c:forEach var="type" items="${typeList}">
                    <label>
                        <input type="checkbox" name="types" value="${type.name}"/> ${type.name}
                    </label>
                    <br>
                </c:forEach>
            </div>
            Ngày phát hành: <input name="date" type="date" required>
            <br>
            Trạng thái:
            <select name="status" required>
                <option value="Đang cập nhật">Đang cập nhật</option>
                <option value="Hoàn thành">Hoàn thành</option>
            </select>
            <br>
            <button type="submit">Thêm</button>
        </form>
        <c:if test="${not empty error}">
            <p style="color: red;">${error}</p>
        </c:if>
    </body>
    <script>
        function validateForm() {
            // Kiểm tra ít nhất một checkbox đã được chọn
            var checkboxes = document.querySelectorAll('input[name="types"]:checked');
            if (checkboxes.length === 0) {
                alert("Vui lòng chọn ít nhất một thể loại!");
                return false;
            }
            return true;
        }
    </script>
</html>
