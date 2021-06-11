<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="listBookUrl" value="/admin-home/book-management">
    <c:param name="urlType" value="url_list"/>
</c:url>
<html>
<head>
    <title><fmt:message key="label.book.manage" bundle="${lang}"/></title>
</head>
<body>
<div class="page-content">
    <div class="row">
        <div class="col-12">
            <form action="${listUserUrl}" method="get" id="formUrl">
                <div class="row" style="margin-bottom : 20px;">
                    <div class="col-9">
                        <c:if test="${not empty messageResponse}">
                            <div class="alert alert-${alert}" style="margin: 0;">
                                <button type="button" class="close" data-dismiss="alert">
                                    <i class="ace-icon fa fa-times"></i>
                                </button>
                                    ${messageResponse}
                            </div>
                        </c:if>
                    </div>
                    <div class="col-3">
                        <div class="table-btn-controls">
                            <div class="pull-right tableTools-container">
                                <div class="dt-buttons btn-overlap btn-group">
                                    <a flag="info" href="<c:url value="/admin-home/book-management?urlType=url_edit"/>"
                                       class="dt-button btn btn-white btn-primary btn-bold" title="<fmt:message key='label.book.add' bundle='${lang}'/>">
                                            <span>
                                                <i class="fa fa-plus-circle bigger-110 purple"></i>
                                            </span>
                                    </a>
                                    <button type="button"
                                            class="dt-button btn btn-white btn-primary btn-bold"
                                            id="deleteAll" disabled data-toggle="tooltip" onclick="warningBeforeDelete()"
                                            title="<fmt:message key='label.delete.all' bundle='${lang}'/> ">
                                                    <span>
                                                        <i class="fa fa-trash-o bigger-110 pink"></i>
                                                    </span>
                                    </button>
                                    <a flag="info" class="dt-button buttons-colvis btn btn-white btn-primary btn-bold"
                                       href="${importUrl}">
                                        <span>
                                            <i class="fa fa-file" aria-hidden="true"></i>
                                        </span>
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-12">
                        <div class="card">
                            <div class="card-header">
                                <h3 class="card-title">Danh sách user</h3>

                                <div class="card-tools">
                                    <div class="input-group input-group-sm" style="width: 400px;">
                                        <input type="text" name="pojo.bookFullName" class="form-control float-right"
                                               placeholder="Tìm kiếm" value="${items.pojo.bookFullName}">

                                        <div class="input-group-append">
                                            <button type="submit" class="btn btn-default"><i class="fas fa-search" id="btn-search"></i>
                                            </button>
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
                                        <display:column title="<div class='custom-control custom-checkbox'>
                                                        <input type='checkbox' id='checkAll' class='custom-control-input'>
                                                        <label class='custom-control-label' for='checkAll'></label>
                                                        </div>"
                                                        class="text-center chonCheckBox"
                                                        headerClass="text-center chonCheckBox">
                                            <div class="custom-control custom-checkbox">
                                                <input name="checkList" type="checkbox" class="custom-control-input"
                                                       id="checkbox_${tableList.bookId}" value="${tableList.bookId}">
                                                <label class="custom-control-label"
                                                       for="checkbox_${tableList.bookId}"></label>
                                            </div>
                                        </display:column>
                                        <display:column property="bookFullName" titleKey="label.book.fullName"
                                                        sortable="true" sortName="bookFullName" style="width: auto"/>
                                        <display:column property="brief" titleKey="label.book.brief" maxLength="50"/>
                                        <display:column property="categoryNames" titleKey="label.book.category" maxLength="30"/>
                                        <display:column property="createdDate" titleKey="label.createdDate"/>
                                        <display:column property="userDTO.name" titleKey="label.book.user"/>
                                        <display:column property="authorDTO.authorName" titleKey="label.author"/>
                                        <display:column titleKey="label.action" class="">
                                            <c:url value="/admin-home/book-management" var="deleteUrl">
                                                <c:param name="urlType" value="url_list"/>
                                                <c:param name="pojo.bookId" value="${tableList.bookId}"/>
                                            </c:url>
                                            <c:url value="/admin-home/book-management" var="updateBook">
                                                <c:param name="urlType" value="url_edit"/>
                                                <c:param name="pojo.bookId" value="${tableList.bookId}"/>
                                            </c:url>
                                            <a class="btn btn-sm btn-primary btn-edit" href="${updateBook}"
                                               data-toggle="tooltip" title="<fmt:message key='label.book.update' bundle='${lang}'/>">
                                                <i class="fa fa-pencil-square-o" aria-hidden="true"></i>
                                            </a>
                                            <a class="btn btn-sm btn-danger btn-cancel" data-toggle="tooltip"  sc-url="${deleteUrl}"
                                               title="<fmt:message key='label.user.delete' bundle='${lang}'/>" onclick="warningBeforeDelete(this)">
                                                <i class="fa fa-trash" aria-hidden="true"></i>
                                            </a>
                                        </display:column>
                                    </display:table>
                                </fmt:bundle>
                            </div>
                            <!-- /.card-body -->
                        </div>
                        <!-- /.card -->
                    </div>
                </div>

                <input type="hidden" name="crudaction" id="crudaction"/>
                <input type="hidden" name="urlType" id="urlType" value="url_list"/>
            </form>
        </div>
    </div>
</div>
<div id="edit"></div>
<script>
    function warningBeforeDelete(x) {
        if (x!=null){
            showAlertBeforeDelete(function () {
                var url = $(x).attr('sc-url');
                $('#formUrl').attr('action',url);
                $('#formUrl').attr('method','post');
                $('#crudaction').val('redirect_delete');
                $('#formUrl').submit();
            });
        } else {
            showAlertBeforeDelete(function () {
                $('#crudaction').val('redirect_delete');
                $('#formUrl').attr('method','post');
                $('#formUrl').submit();
            });
        }
    }
</script>
</body>
</html>
