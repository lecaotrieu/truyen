<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="listBookUrl" value="/manage-book">
    <c:param name="urlType" value="url_list"/>
</c:url>
<c:url value="/manage-chapter" var="addChapterUrl">
    <c:param name="urlType" value="url_edit"/>
    <c:param name="bookName" value="${items.bookName}"/>
</c:url>
<html>
<head>
    <title><fmt:message key="label.book.manage" bundle="${lang}"/></title>
</head>
<body>
<div class="page-content">
    <div class="row content">
        <div class="col-3">
            <%@include file="/common/web/menu.jsp" %>
        </div>
        <div class="col-9">
            <c:if test="${not empty messageResponse}">
                <div class="col-12 alert alert-${alert}" style="margin-bottom: 10px;width: 97%;">
                    <button type="button" class="close" data-dismiss="alert">
                        <i class="ace-icon fa fa-times"></i>
                    </button>
                        ${messageResponse}
                </div>
            </c:if>
            <form action="${listUserUrl}" method="get" id="formUrl">
                <div class="page-header">
                    <div class="row">
                        <div class="col-9">
                            <h3><i class="fas fa-list" style="margin-right: 4px;font-size: 23px;"></i><fmt:message key="label.chapter.list"
                                                                                                         bundle="${lang}"/></h3>
                        </div>
                        <div class="col-3">
                            <a class="btn btn-outline-dark" data-toggle="tooltip"
                               href="${addChapterUrl}">
                                <fmt:message key='label.chapter.add' bundle='${lang}'/>
                            </a>
                        </div>
                    </div>
                </div>
                <div class="chapter-list">
                    <div class="row chapter-list-title">
                        <div class="col-9"><h5>Tên Chương</h5></div>
                        <div class="col-3"><h5>Thao tác</h5></div>
                    </div>
                    <div class="row chapter-list-content">
                        <c:forEach items="${items.listResult}" var="item">
                            <c:url value="/doc-truyen" var="chapterUrl">
                                <c:param name="urlType" value="url_edit"/>
                                <c:param name="bookName" value="${items.bookName}"/>
                                <c:param name="pojo.chapter" value="${item.chapter}"/>
                            </c:url>
                            <div class="col-9"><a href="${chapterUrl}">Chương ${item.chapter} : ${item.title}</a></div>
                            <div class="col-3">
                                <c:url value="/manage-chapter" var="updateChapterUrl">
                                    <c:param name="urlType" value="url_edit"/>
                                    <c:param name="bookName" value="${items.bookName}"/>
                                    <c:param name="pojo.chapter" value="${item.chapter}"/>
                                </c:url>
                                <c:url value="/manage-chapter" var="deleteChapterUrl">
                                    <c:param name="pojo.chapterId" value="${item.chapterId}"/>
                                </c:url>
                                <a href="${updateChapterUrl}" class="chinhsua">Chỉnh sửa</a>
                                <a class="xoa" sc-url="${deleteChapterUrl}" onclick="warningBeforeDelete(this)">Xóa</a>
                            </div>
                        </c:forEach>
                    </div>
                </div>
                <input type="hidden" name="crudaction" id="crudaction"/>
                <input type="hidden" name="urlType" id="urlType" value="url_list"/>
                <input type="hidden" name="bookName" value="${items.bookName}">
                <input type="hidden" id="page" name="page" value="${items.page}">
            </form>
            <ul id="pagination-chapter" class="pagination-sm"></ul>
        </div>
    </div>
</div>
<div id="edit"></div>
<script>
    function warningBeforeDelete(x) {
        if (x != null) {
            showAlertBeforeDelete(function () {
                var url = $(x).attr('sc-url');
                $('#formUrl').attr('action', url);
                $('#formUrl').attr('method', 'post');
                if (items == 1) {
                    var currentPage = $('#page').val();
                    currentPage--;
                    $('#page').val(currentPage);
                }
                $('#crudaction').val('redirect_delete');
                $('#formUrl').submit();
            });
        } else {
            showAlertBeforeDelete(function () {
                $('#crudaction').val('redirect_delete');
                $('#formUrl').attr('method', 'post');
                $('#formUrl').submit();
            });
        }
    }
    var items = ${items.listResult.size()}
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
                    $("#formUrl").submit();
                }
            }
        });
    });
</script>
</body>
</html>
