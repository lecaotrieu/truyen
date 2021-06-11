<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<c:url value="/manage-book" var="postBookUrl">
    <c:param name="urlType" value="url_edit"/>
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
            <%@include file="/common/web/menu.jsp"%>
        </div>
        <div class="col-9">
            <div class="page-header">
                <h3><i class="fas fa-book-open" style="margin-right: 10px;"></i><fmt:message key="label.book.add"
                                                                                             bundle="${lang}"/></h3>
            </div>
            <form action="${postBookUrl}" class="form-horizontal" method="post" id="formEdit" enctype="multipart/form-data">
                <div class="form-group row">
                    <label for="txtName" class="col-sm-2">Tên truyện</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" name="pojo.bookFullName" id="txtName"
                               value="${item.pojo.bookFullName}"
                               placeholder="<fmt:message key="label.book.name.placeholder" bundle="${lang}"/>">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="img" class="col-sm-2">Poster truyện</label>
                    <div class="col-sm-10">
                        <c:if test="${not empty item.pojo.poster}">
                            <c:set value="/repository/${item.pojo.poster}" var="imgUrl"/>
                        </c:if>
                        <c:if test="${empty item.pojo.poster}">
                            <c:set value="/repository/bookPoster\null\poster.jpg" var="imgUrl"/>
                        </c:if>
                        <img class="d-block"
                             src="${imgUrl}" alt="Card image cap" style="width: auto; height: 250px" id="img-poster">
                        <input type="file" name="img" id="img" onchange="readURL(this,'img-poster')">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="img" class="col-sm-2">Tác giả</label>
                    <div class="col-sm-10">
                        <select id="select-beast" placeholder="Pick a state..." name="pojo.authorDTO.authorName">
                            <option value=""></option>
                            <c:forEach items="${item.authorS}" var="author">
                                <option value="${author.authorId}"
                                        <c:if test="${author.authorId == item.pojo.authorDTO.authorId}">selected</c:if> >${author.authorName}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-sm-2">Thể loại</label>
                    <div class="col-sm-10 columns">
                        <c:forEach items="${item.categoryS}" var="category">
                            <div class="custom-control custom-checkbox float-left" style="width:120px; margin-right: 15px;">
                                <input name="checkList" type="checkbox" class="custom-control-input"
                                <c:forEach items="${item.pojo.categoryDTOS}" var="categoryChecked">
                                <c:if test="${category.categoryId == categoryChecked.categoryId}">
                                       checked
                                </c:if>
                                </c:forEach>
                                       id="checkbox_${category.categoryId}" value="${category.categoryId}">
                                <label class="custom-control-label"
                                       for="checkbox_${category.categoryId}">${category.categoryName}</label>
                            </div>
                        </c:forEach>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="txtBrief" class="col-sm-2">Nội dung</label>
                    <div class="col-sm-10">
                <textarea class="form-control" name="pojo.brief" id="txtBrief" rows="20"
                          placeholder="<fmt:message key="label.book.brief.placeholder" bundle="${lang}"/>">${item.pojo.brief}</textarea>
                    </div>
                </div>
                <input type="hidden" name="urlType" value="url_edit">
                <c:if test="${not empty item.pojo.bookId}">
                    <input type="hidden" name="pojo.bookId" value="${item.pojo.bookId}">
                </c:if>
                <input type="hidden" name="urlType" value="url_edit">
                <div class="row">
                    <div class="col-sm-12">
                        <button type="submit" class="btn btn-outline-primary float-right" value="Đăng truyện">
                            Đăng truyện
                        </button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<script>
    $(document).ready(function () {
        var editor = CKEDITOR.replace('txtDesc',{
            width: ['100%'], height: ['300px']
        });
        $('#select-beast').selectize({
            create: true,
            sortField: {
                field: 'text',
                direction: 'asc'
            },
            dropdownParent: 'body'
        });
        validateData();
    });
    function validateData() {
        $('#formEdit').validate({
            ignore: [],
            rules: [],
            messages: []
        });
        $('#txtName').rules("add",{
            required: true,
            messages: {
                required: '<fmt:message key="label.notempty" bundle="${lang}"/> '
            }
        });
    }
</script>
</body>
</html>