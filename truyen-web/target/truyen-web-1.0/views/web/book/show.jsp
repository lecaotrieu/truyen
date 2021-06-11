<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<c:url value="/ajax-chapter-list" var="showListChapterUrl">
</c:url>
<!DOCTYPE html>
<html>
<head>
    <title><fmt:message key="label.book.add" bundle="${lang}"/></title>
</head>
<body>
<div class="book-detail content">
    <div class="row book-detail-header">
        <div class="col-3">
            <c:if test="${not empty item.pojo.poster}">
                <c:set value="/repository/${item.pojo.poster}" var="imgUrl"/>
            </c:if>
            <c:if test="${empty item.pojo.poster}">
                <c:set value="/repository/bookPoster\null\poster.jpg" var="imgUrl"/>
            </c:if>
            <img class="d-block"
                 src="${imgUrl}" alt="Card image cap" style="width: -webkit-fill-available; height: 250px"/>
        </div>
        <div class="col-6">
            <h2 class="book-name">
                ${item.pojo.bookFullName}
            </h2>
            <div class="row">
                <div class="book-sub author"><a href="#">${item.pojo.authorDTO.authorName}</a></div>
                <div class="book-sub user"><a href="#">${item.pojo.userDTO.fullName}</a></div>
            </div>
            <div class="row">
                <c:forEach items="${item.pojo.categoryDTOS}" var="item">
                    <div class="book-sub category"><a href="#">${item.categoryName}</a></div>
                </c:forEach>
            </div>
        </div>
        <div class="col-3">Đánh giá</div>
    </div>
    <div class="row book-content">
        <div class="col-12">
            <nav>
                <div class="nav nav-tabs" id="nav-tab" role="tablist">
                    <a class="nav-item nav-link active" id="detail-tab" data-toggle="tab" href="#detail-book" role="tab"
                       aria-controls="#chapter-list" aria-selected="true">Thông tin chi tiết</a>
                    <a class="nav-item nav-link" id="chapter-tab" data-toggle="tab" href="#chapter-list" role="tab"
                       aria-controls="#chapter-list" aria-selected="false">Danh sách chương</a>
                    <a class="nav-item nav-link " id="comment-tab" data-toggle="tab" href="#comment" role="tab"
                       aria-controls="#comment" aria-selected="false">Bình luận</a>
                </div>
            </nav>
            <div class="tab-content book-detail-content" id="nav-tabContent">
                <div class="tab-pane fade detail show active" id="detail-book" role="tabpanel"
                     aria-labelledby="nav-home-tab">
                    <div class="row">
                        <div class="col-8">
                            ${item.pojo.brief}
                        </div>
                    </div>
                </div>
                <div class="tab-pane fade chapter" id="chapter-list" role="tabpanel" aria-labelledby="nav-profile-tab">
                    <div class="chapter-content">
                        <div class="row">
                            <c:forEach items="${item.pojo.chapterDTOS}" var="chapter">
                                <div class="col-4 sub-chapter">
                                    <a href="" maxlength="1h">Chương ${chapter.chapter}: ${chapter.title}</a>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                    <input type="hidden" id="bookName" name="pojo.bookName" value="${item.pojo.bookName}">
                    <div class="row float-right">
                        <ul id="pagination-chapter" class="pagination-sm"></ul>
                    </div>
                </div>
                <div class="tab-pane fade comment" id="comment" role="tabpanel" aria-labelledby="nav-contact-tab">
                    <div class="total-comment row">
                        <h6>${item.totalComment} bình luận</h6>
                    </div>
                    <div class="row">
                        <div class="comment-content col-7">
                            <div class="post-comment row">
                                <textarea class="comment-text col-11" name="" id="" rows="2"></textarea>
                                <button type="submit" class="btn btn-success dang-comment col-1"><i
                                        class="far fa-comment"></i></button>
                            </div>
                            <div class="comment-detail row" id="comment-detail">

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    $(document).ready(function () {
    });

    $('#chapter-tab').click(function () {
        showListChapter();
    });
    $('#comment-tab').click(function () {
        showCommentList();
    });
    function showListChapter() {
        var url = "/ajax-chapter-list?urlType=url_list&bookName=${item.pojo.bookName}";
        if ($('#page').val() != "" && $('#page').val() != "undefined") {
            url = url + "&page=" + $('#page').val();
        }
        $('#chapter-list').load(url)
    }

    function showCommentList() {
        var url = "/ajax-comment-list?urlType=url_list&bookName=${item.pojo.bookName}";
        $('#comment-detail').load(url)
    }
</script>
</body>
</html>