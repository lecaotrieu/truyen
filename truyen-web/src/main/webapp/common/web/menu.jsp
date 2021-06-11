<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<c:url value="/manage-book" var="bookListUrl">
    <c:param name="urlType" value="url_list"/>
</c:url>
<c:url value="/manage-book" var="bookEditUrl">
    <c:param name="urlType" value="url_edit"/>
</c:url>
<div class="row">
    <div class="col-12">
        <div class="panel panel-success">
            <div class="panel-heading">
                <h3 class="panel-title"><span class="glyphicon glyphicon-book" aria-hidden="true"></span>
                    Tài khoản</h3>
            </div>
            <ul class="list-group">
                <li class="list-group-item">
                    <a href="/account/">Hồ sơ</a>
                </li>
                <li class="list-group-item">
                    <a href="/account/message/">Tin nhắn</a>
                </li>
                <li class="list-group-item">
                    <a href="#">Tủ truyện</a>
                </li>
                <li class="list-group-item">
                    <a href="/account/change_pass/">Cài đặt</a>
                </li>
            </ul>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-12">
        <div class="panel panel-success">
            <div class="panel-heading">
                <h3 class="panel-title"><span class="glyphicon glyphicon-book" aria-hidden="true"></span> Truyện</h3>
            </div>
            <ul class="list-group">
                <li class="list-group-item">
                    <a href="${bookEditUrl}">Đăng truyện</a>
                </li>
                <li class="list-group-item">
                    <a href="${bookListUrl}">Thêm chương</a>
                </li>
                <li class="list-group-item">
                    <a href="/account/list_error/">Duyệt lỗi</a>
                </li>

            </ul>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-12">
        <div class="panel panel-success">
            <div class="panel-heading">
                <h3 class="panel-title"><span class="glyphicon glyphicon-book" aria-hidden="true"></span> Tài sản</h3>
            </div>
            <ul class="list-group">
                <li class="list-group-item">
                    <a href="/account/nap_vang/">Nạp Đậu</a>
                </li>

                <li class="list-group-item">
                    <a href="/account/vat_pham/">Rương Chứa Đồ</a>
                </li>
                <li class="list-group-item">
                    <a href="/account/invoice/">Hóa đơn</a>
                </li>

                <li class="list-group-item">
                    <a href="/account/log_xu/">Log giao dịch</a>
                </li>
            </ul>
        </div>
    </div>
</div>