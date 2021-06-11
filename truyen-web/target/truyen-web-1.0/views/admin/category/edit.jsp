<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<c:url value="/ajax-category-edit" var="editCategoryUrl">
    <c:param name="urlType" value="url_edit"/>
</c:url>
<c:choose>
    <c:when test="${not empty messageResponse}">
        ${messageResponse}
    </c:when>
    <c:otherwise>
        <div class="modal fade" id="edit-category" style="display: none; padding-right: 17px;" aria-modal="true">
            <div class="modal-dialog modal-dialog-scrollable">
                <div class="modal-content">
                    <div class="modal-header">
                        <c:if test="${not empty item.pojo.categoryId}">
                            <h3 class="modal-title"><fmt:message key="label.category.edit.update"
                                                                 bundle="${lang}"/></h3>
                        </c:if>
                        <c:if test="${empty item.pojo.categoryId}">
                            <h3 class="modal-title"><fmt:message key="label.category.edit.insert"
                                                                 bundle="${lang}"/></h3>
                        </c:if>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>

                    <div class="modal-body">
                        <!-- form start -->
                        <form method="POST" action="${editCategoryUrl}" id="editCategoryForm">
                            <div class="form-group">
                                <label class=" form-control-label"><fmt:message key="label.category.name"
                                                                                bundle="${lang}"/></label>
                                <input type="text" name="pojo.categoryName" placeholder="<fmt:message key="label.category.name"
                                                bundle="${lang}"/>" class="form-control" value="${item.pojo.categoryName}">
                            </div>
                            <div class="form-group">
                                <label class=" form-control-label"><fmt:message key="label.content"
                                                                                bundle="${lang}"/></label>
                                <textarea class="form-control" name="pojo.content" id="txtDesc" rows="8"
                                          placeholder="<fmt:message key="label.content" bundle="${lang}"/>">${item.pojo.content}</textarea>
                            </div>
                            <c:if test="${not empty item.pojo.categoryId}">
                                <input type="hidden" name="pojo.categoryId" value="${item.pojo.categoryId}"/>
                            </c:if>
                            <input type="hidden" name="crudaction" id="crudactionEdit"/>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                        <button type="button" class="btn btn-outline-primary" id="btnSave">Save changes</button>
                    </div>
                </div>
                <!-- /.modal-content -->
            </div>
            <!-- /.modal-dialog -->
        </div>
    </c:otherwise>
</c:choose>

