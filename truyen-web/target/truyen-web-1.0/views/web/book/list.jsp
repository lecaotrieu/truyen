<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="listBookUrl" value="/manage-book">
    <c:param name="urlType" value="url_list"/>
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
                <div class="alert alert-${alert}" style="margin: 0;">
                    <button type="button" class="close" data-dismiss="alert">
                        <i class="ace-icon fa fa-times"></i>
                    </button>
                        ${messageResponse}
                </div>
            </c:if>
            <form action="${listUserUrl}" method="get" id="formUrl">
                <div class="card">
                    <div class="card-header">
                        <div class="row">
                            <h3 class="col-6"><fmt:message key="label.book.list" bundle="${lang}"/></h3>
                            <div class="card-tools col-6">
                                <div class="input-group input-group-sm float-lg-right" style="width: 400px;">
                                    <input type="text" name="pojo.bookFullName" class="form-control float-right"
                                           placeholder="Tìm kiếm" value="${items.pojo.bookFullName}">

                                    <div class="input-group-append">
                                        <button type="submit" class="btn btn-default"><i class="fas fa-search"
                                                                                         id="btn-search"></i>
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="table-responsive">
                        <fmt:bundle basename="ApplicationResources">
                            <display:table id="tableList" name="items.listResult" partialList="true"
                                           size="${items.totalItems}" pagesize="${items.maxPageItems}"
                                           sort="external"
                                           requestURI="${requestUrl}"
                                           class="table table-fcv-ace table-striped table-bordered table-hover dataTable no-footer"
                                           style="margin: 0 0 1em;">
                                <display:column titleKey="label.book.fullName"
                                                sortable="true" sortName="bookFullName" style="width: auto;">
                                    <c:url value="/detail-book" var="detailBookUrl">
                                        <c:param name="pojo.bookName" value="${tableList.bookName}"/>
                                    </c:url>
                                    <a href="${detailBookUrl}">${tableList.bookFullName}</a>
                                </display:column>
                                <display:column property="authorDTO.authorName" titleKey="label.author"/>
                                <display:column titleKey="label.action" class="" style="width: 145px;">
                                    <c:url value="/manage-book" var="deleteUrl">
                                        <c:param name="urlType" value="url_list"/>
                                        <c:param name="pojo.bookId" value="${tableList.bookId}"/>
                                    </c:url>
                                    <c:url value="/manage-book" var="updateBook">
                                        <c:param name="urlType" value="url_edit"/>
                                        <c:param name="pojo.bookName" value="${tableList.bookName}"/>
                                    </c:url>
                                    <c:url value="/manage-chapter" var="addChapter">
                                        <c:param name="urlType" value="url_list"/>
                                        <c:param name="bookName" value="${tableList.bookName}"/>
                                    </c:url>
                                    <a class="btn btn-sm btn-primary btn-edit" href="${updateBook}"
                                       data-toggle="tooltip"
                                       title="<fmt:message key='label.book.update' bundle='${lang}'/>">
                                        <i class="fa fa-pencil-square-o" aria-hidden="true"></i>
                                    </a>
                                    <a class="btn btn-sm btn-danger btn-cancel" data-toggle="tooltip"
                                       sc-url="${deleteUrl}"
                                       title="<fmt:message key='label.book.delete' bundle='${lang}'/>"
                                       onclick="warningBeforeDelete(this)">
                                        <i class="fa fa-trash" aria-hidden="true"></i>
                                    </a>
                                    <a class="btn btn-sm btn-outline-dark" data-toggle="tooltip"
                                       href="${addChapter}"
                                       title="<fmt:message key='label.chapter.add' bundle='${lang}'/>">
                                        <i class="fas fa-plus-circle"></i>
                                    </a>
                                </display:column>
                            </display:table>
                        </fmt:bundle>
                    </div>
                    <!-- /.card-body -->
                </div>
                <!-- /.card -->
                <input type="hidden" name="crudaction" id="crudaction"/>
                <input type="hidden" name="urlType" id="urlType" value="url_list"/>
            </form>
        </div>
    </div>
</div>
<script>
    function warningBeforeDelete(x) {
        if (x != null) {
            showAlertBeforeDelete(function () {
                var url = $(x).attr('sc-url');
                $('#formUrl').attr('action', url);
                $('#formUrl').attr('method', 'post');
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
</script>
</body>
</html>
