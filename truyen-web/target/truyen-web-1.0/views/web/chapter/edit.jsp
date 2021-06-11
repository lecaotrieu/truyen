<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp" %>
<c:url value="/manage-chapter" var="postChapterUrl">
</c:url>
<!DOCTYPE html>
<html>
<head>
    <title><fmt:message key="label.book.add" bundle="${lang}"/></title>
</head>
<body>
<div class="page-content">
    <div class="row content">
        <div class="col-3">
            <%@include file="/common/web/menu.jsp" %>
        </div>
        <div class="col-9">
            <div class="page-header">
                <c:if test="${empty item.pojo.chapter}" >
                    <h3><i class="fas fa-book-open" style="margin-right: 10px;"></i><fmt:message key="label.chapter.add"
                                                                                                 bundle="${lang}"/></h3>
                </c:if>
                <c:if test="${not empty item.pojo.chapter}">
                    <h3><i class="fas fa-book-open" style="margin-right: 10px;"></i><fmt:message key="label.chapter.update"
                                                                                                 bundle="${lang}"/></h3>
                </c:if>

            </div>
            <form action="${postChapterUrl}" class="form-horizontal" method="post" id="formChapterEdit" enctype="multipart/form-data">
                <div class="form-group row">
                    <label for="txtTitle" class="col-sm-12">Tiêu đề</label>
                    <div class="col-sm-12">
                        <input type="text" class="form-control" name="pojo.title" id="txtTitle"
                               value="${item.pojo.title}"
                               placeholder="<fmt:message key="label.chapter.title" bundle="${lang}"/>">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="txtContent" class="col-sm-12">Nội dung</label>
                    <div class="col-sm-12">
                            <textarea class="form-control" name="pojo.content" id="txtContent" rows="20"
                                      placeholder="<fmt:message key="label.chapter.content" bundle="${lang}"/>">${item.pojo.content}</textarea>
                    </div>
                </div>
                <c:if test="${not empty item.pojo.chapter}">
                    <input type="hidden" name="pojo.chapter" value="${item.pojo.chapter}">
                    <input type="hidden" name="pojo.chapterId" value="${item.pojo.chapterId}">
                </c:if>
                <input type="hidden" name="bookName" value="${item.bookName}">
                <input type="hidden" name="bookId" value="${item.bookId}">
                <input type="hidden" name="urlType" value="url_edit">
                <div class="row">
                    <div class="col-sm-12">
                        <c:if test="${not empty item.pojo.chapter}" >
                            <button type="submit" class="btn btn-outline-primary float-right" value="update chương">
                                <fmt:message key="label.chapter.update" bundle="${lang}"/>
                            </button>
                        </c:if>
                        <c:if test="${empty item.pojo.chapter}">
                            <button type="submit" class="btn btn-outline-primary float-right" value="Đăng chương">
                                <fmt:message key="label.chapter.add" bundle="${lang}"/>
                            </button>
                        </c:if>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<script>
    $(document).ready(function () {
        var editor = CKEDITOR.replace('txtContent',{
            width: ['100%'], height: ['500px']
        });
        validateData()
    });
    function validateData() {
        $('#formChapterEdit').validate({
            ignore: [],
            rules: [],
            messages: []
        });
        $('#txtTitle').rules("add", {
            required: true,
            messages: {
                required: '<fmt:message key="label.notempty" bundle="${lang}"/> '
            }
        });
    }
</script>
</body>
</html>