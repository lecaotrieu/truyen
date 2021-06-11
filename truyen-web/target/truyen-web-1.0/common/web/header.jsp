<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!-- Navigation -->
<c:url var="accountUrl" value="/account-login">
    <c:param name="urlType" value="login"/>
</c:url>
<c:url value="/account-login" var="logoutUrl">
    <c:param name="urlType" value="logout"/>
</c:url>
<c:url value="/manage-book" var="bookListUrl">
    <c:param name="urlType" value="url_list"/>
</c:url>
<c:url value="/manage-book" var="bookEditUrl">
    <c:param name="urlType" value="url_edit"/>
</c:url>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container">
        <div class="col-2">
            <a href=""> <img id="logo" class="navbar-brand" src="<c:url value="/template/web/image/logo.png"/>" alt=""></a>
        </div>
        <div class="collapse navbar-collapse" id="">
            <ul class="navbar-nav mr-auto danh-muc">
                <li class="nav-item muc">
                    <a class="nav-link" href="#">Danh mục<span class="sr-only">()</span></a>
                    <ul class="sub-menu">
                        <li class="item col-3"><a href="">Truyện mới</a></li>
                        <li class="item col-3"><a href="">Truyện sáng tác</a></li>
                        <li class="item col-3"><a href="">Truyện hoàn thành</a></li>
                        <li class="item col-3"><a href="">Truyện đề cử</a></li>
                        <li class="item col-3"><a href="">Truyện vip</a></li>
                        <li class="item col-3"><a href="">Bảng xếp hạng</a></li>
                    </ul>
                </li>
                <li class="nav-item muc">
                    <a class="nav-link" href="#">Thể loại</a>
                    <ul class="sub-menu">
                        <c:forEach items="${category_list}" var="item">
                            <li class="item col-3"><a href="">${item.categoryName}</a></li>
                        </c:forEach>
                    </ul>
                </li>
                <li class="nav-item muc">
                    <a class="nav-link" href="#">Lọc truyện</a>
                </li>
            </ul>
            <form class="form-inline my-2 my-lg-0 col-5">
                <div class="input-group input-group-sm col-12">
                    <input type="text" name="pojo.bookFullName" class="form-control col-9" placeholder="Tìm kiếm"
                           value="">
                    <div class="input-group-append">
                        <button type="submit" class="btn btn-outline-info"><i class="fas fa-search" id="btn-search"></i>
                        </button>
                    </div>
                </div>

            </form>
            <div class="col-2">
                <c:if test="${not empty login_user}">
                    <div class="dropdown custom-dropdown show float-right">
                        <a class="dropdown-toggle" role="button" id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">${login_user.fullName}
                        </a>

                        <div class="dropdown-menu custom-dropdown-menu dropdown-menu-right" aria-labelledby="dropdownMenuLink">
                            <a class="dropdown-item" href="${bookEditUrl}">Đăng sách</a>
                            <a class="dropdown-item" href="${bookListUrl}">Quản lý sách</a>
                            <a class="dropdown-item" href="#">Tủ sách</a>
                            <a class="dropdown-item" href="#">Tin nhắn</a>
                            <a class="dropdown-item" href="${logoutUrl}">Đăng xuất</a>
                        </div>
                    </div>
                </c:if>
                <c:if test="${empty login_user}">
                    <a href="${accountUrl}" class="btn btn-outline-light float-right" type="submit">Đăng nhập</a>
                </c:if>
            </div>
        </div>
    </div>
</nav>


