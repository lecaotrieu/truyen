<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="listBookUrl" value="/admin-home/category-management">
    <c:param name="urlType" value="url_list"/>
</c:url>
<c:url value="/ajax-category-edit" var="editCategory">
    <c:param name="urlType" value="url_edit"/>
</c:url>
<html>
<head>
    <title><fmt:message key="label.category.manage" bundle="${lang}"/></title>
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
                                    <a flag="info" onclick="insertOrUpdateCategory(null)"
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
                                <h3 class="card-title">Danh sách Thể Loại</h3>

                                <div class="card-tools">
                                    <div class="input-group input-group-sm" style="width: 400px;">
                                        <input type="text" name="pojo.categoryName" class="form-control float-right"
                                               placeholder="Tìm kiếm" value="${items.pojo.categoryName}">

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
                                                       id="checkbox_${tableList.categoryId}" value="${tableList.categoryId}">
                                                <label class="custom-control-label"
                                                       for="checkbox_${tableList.categoryId}"></label>
                                            </div>
                                        </display:column>
                                        <display:column property="categoryName" titleKey="label.category.name"
                                                        sortable="true" sortName="categoryName" style="width: auto"/>
                                        <display:column property="content" titleKey="label.content" maxLength="120"/>
                                        <display:column titleKey="label.action" class="">
                                            <c:url value="/admin-home/category-management" var="deleteUrl">
                                                <c:param name="urlType" value="url_list"/>
                                                <c:param name="pojo.categoryId" value="${tableList.categoryId}"/>
                                            </c:url>
                                            <c:url value="/ajax-category-edit" var="updateUrl">
                                                <c:param name="urlType" value="url_edit"/>
                                                <c:param name="pojo.categoryId" value="${tableList.categoryId}"/>
                                            </c:url>
                                            <a class="btn btn-sm btn-primary btn-edit" sc-url="${updateUrl}"
                                               data-toggle="tooltip" onclick="insertOrUpdateCategory(this)"
                                               title="<fmt:message key='label.category.edit.update' bundle='${lang}'/>">
                                                <i class="fa fa-pencil-square-o" aria-hidden="true"></i>
                                            </a>
                                            <a class="btn btn-sm btn-danger btn-cancel" data-toggle="tooltip"  sc-url="${deleteUrl}"
                                               title="<fmt:message key='label.category.delete' bundle='${lang}'/>" onclick="warningBeforeDelete(this)">
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
    function insertOrUpdateCategory(btn) {
        var editUrl = $(btn).attr('sc-url');
        if (typeof editUrl == 'undefined') {
            editUrl = '${editCategory}';
        }
        $('#edit').load(editUrl, "", function () {
            $('#edit-category').modal("toggle");
            addOrEditUser();
        });
    }

    function addOrEditUser() {
        $("#btnSave").click(function () {
            $('#editCategoryForm').submit();
        });
        $('#editCategoryForm').submit(function (e) {
            e.preventDefault();
            $('#crudactionEdit').val("insert_update");
            $.ajax({
                type: $(this).attr('method'),
                url: $(this).attr('action'),
                data: $(this).serialize(),
                dataType: 'html',
                success: function (res) {
                    if (res.trim() == "redirect_insert") {
                        $('#crudaction').val('redirect_insert');
                        $('#urlType').val('url_list');
                        $('#formUrl').submit();
                    } else if (res.trim() == "redirect_update") {
                        $('#crudaction').val('redirect_update');
                        $('#urlType').val('url_list');
                        $('#formUrl').submit();
                    } else if (res.trim() == "redirect_delete") {
                        $('#crudaction').val('redirect_delete');
                        $('#urlType').val('url_list');
                        $('#formUrl').submit();
                    } else if (res.trim() == "redirect_error") {
                        $('#crudaction').val('redirect_error');
                        $('#urlType').val('url_list');
                        $('#formUrl').submit();
                    }
                },
                error: function (res) {
                    console.log(res);
                }
            });
        });
    }

</script>
</body>
</html>
