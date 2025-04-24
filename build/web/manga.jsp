<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
<link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/css/bootstrap.min.css" rel="stylesheet">
<link href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.4.0/mdb.min.css" rel="stylesheet">
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        .icon-hover:hover {
            border-color: #3b71ca !important;
            background-color: white !important;
            color: #3b71ca !important;
        }

        .icon-hover:hover i {
            color: #3b71ca !important;
        }
    </style>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${manga.name}</title>
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

        <!-- content -->
        <section class="py-5">
            <div class="container">
                <div class="row gx-5">
                    <aside class="col-lg-6">
                        <div class="border rounded-4 mb-3 d-flex justify-content-center">
                            <a data-fslightbox="mygalley" class="rounded-4" target="_blank" data-type="image">
                                <img style="max-width: 100%; max-height: 100vh; margin: auto;" class="rounded-4 fit" src="${manga.imageUrl}" />
                            </a>
                        </div>
                    </aside>
                    <main class="col-lg-6">
                        <div class="ps-lg-3">
                            <h1 class="title text-dark">${manga.name}</h1>
                            <div class="row">
                                <dt class="col-4 mb-2 fs-5">Thể loại:</dt>
                                <dd class="col-8 mb-2 fs-5">
                                    <c:forEach var="typeName" items="${manga.typeNames}" varStatus="status">
                                        <a href="type?name=${typeName}">${typeName}</a>${status.last ? '' : ', '}
                                    </c:forEach>
                                </dd>

                                <dt class="col-4 mb-2 fs-5">Tác giả:</dt>
                                <dd class="col-8 mb-2 fs-5">${manga.author}</dd>

                                <dt class="col-4 mb-2 fs-5">Người đăng:</dt>
                                <dd class="col-8 mb-2 fs-5">${manga.owner}</dd>

                                <dt class="col-4 mb-2 fs-5">Tình trạng:</dt>
                                <dd class="col-8 mb-2 fs-5">${manga.status}</dd>

                                <dt class="col-4 mb-2 fs-5">Ngày phát hành:</dt>
                                <dd class="col-8 mb-2 fs-5">${manga.formattedDate}</dd>

                                <dt class="col-4 mb-2 fs-5">Lượt theo dõi:</dt>
                                <dd class="col-8 mb-2 fs-5">${manga.followNumber}</dd>

                                <dt class="col-4 mb-2 fs-5">Số chương:</dt>
                                <dd class="col-8 mb-2 fs-5">${manga.chap}</dd>
                            </div>

                            <c:choose>
                                <c:when test="${isFollowing}">
                                    <a href="followManga?id=${manga.id}&action=unfollow&username=${acc.user}" class="btn btn-warning shadow-0 fw-bold">Bỏ theo dõi</a>
                                </c:when>
                                <c:otherwise>
                                    <c:choose>
                                        <c:when test="${not empty acc}">
                                            <a href="followManga?id=${manga.id}&action=follow&username=${acc.user}" class="btn btn-warning shadow-0 fw-bold">Theo dõi</a>
                                        </c:when>
                                        <c:otherwise>
                                            <a href="login.jsp" class="btn btn-warning shadow-0 fw-bold">Theo dõi</a>
                                        </c:otherwise>
                                    </c:choose>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </main>
                </div>
            </div>
        </section>
        <!-- content -->

        <section class="bg-light border-top py-4">
            <div class="container">
                <div class="row gx-4">
                    <div class="col-lg-8 mb-4">
                        <div class="border rounded-2 px-3 py-2 bg-white">


                            <!-- Pills content -->
                            <div class="tab-content" id="ex1-content">
                                <div class="tab-pane fade show active" id="ex1-pills-1" role="tabpanel" aria-labelledby="ex1-tab-1">
                                    <h2>Danh sách chương:</h2>
                                    <c:if test="${not empty chapList}">
                                        <table class="table border mt-3 mb-2">
                                            <c:forEach var="chap" items="${chapList}">
                                                <tr>
                                                    <td>
                                                        <a href="viewChapImages?id=${chap.chapId}&username=${acc.user}">${chap.chapName}</a>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </table>
                                    </c:if>
                                    <c:if test="${empty chapList}">
                                        <p>Không có chap nào được tìm thấy.</p>
                                    </c:if>
                                </div>
                            </div>
                            <!-- Pills content -->
                        </div>
                    </div>
                    <div class="col-lg-4">
                        <div class="px-0 border rounded-2 shadow-0">
                            <div class="card">
                                <div class="card-body">
                                    <h3 class="card-title">Truyện liên quan</h3>
                                    <c:forEach var="related" items="${relatedManga}">
                                        <div class="d-flex mb-3">
                                            <a href="manga?id=${related.id}" class="me-3">
                                                <img src="${related.imageUrl}" alt="${related.name}" style="max-width: 96px; max-height: 120px; width: auto; height: auto;" class="img-md img-thumbnail" />
                                            </a>
                                            <div class="info">
                                                <h5><a href="manga?id=${related.id}" class="nav-link mb-1">
                                                    ${related.name}
                                                </a></h5>
                                                <a>Số chương: ${related.chap}</a><br>
                                                <a>Tình trạng: ${related.status}</a>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </section>
    </body>
</html>