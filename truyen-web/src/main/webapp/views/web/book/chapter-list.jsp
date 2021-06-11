<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<div class="chapter-content">
    <div class="row">
        <c:forEach items="${items.listResult}" var="chapter">
            <div class="col-4 sub-chapter">
                <c:url value="/doc-truyen" var="chapterUrl">
                    <c:param name="urlType" value="url_edit"/>
                    <c:param name="bookName" value="${items.bookName}"/>
                    <c:param name="pojo.chapter" value="${chapter.chapter}"/>
                </c:url>
                <a href="${chapterUrl}" maxlength="1h">Chương ${chapter.chapter}: ${chapter.title}</a>
            </div>
        </c:forEach>
    </div>
</div>
<div class="row float-right">
    <input type="hidden" id="page" name="page">
    <ul id="pagination-chapter" class="pagination-sm"></ul>
</div>
<script>
    var totalPages = ${items.totalPage};
    var starPage = ${items.page};
    var visiblePages = ${items.maxPageItems};
    $(function () {
        var obj = $('#pagination-chapter').twbsPagination({
            totalPages: totalPages,
            startPage: starPage,
            visiblePages: visiblePages,
            onPageClick: function (event, page) {
                if (page != starPage) {
                    $("#page").val(page);
                    showListChapter();
                }
            }
        });
    });
</script>

