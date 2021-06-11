<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<c:url value="/ajax-author-edit" var="editAuthorUrl">
    <c:param name="urlType" value="url_edit"/>
</c:url>
<c:choose>
    <c:when test="${not empty messageResponse}">
        ${messageResponse}
    </c:when>
    <c:otherwise>
        <div class="modal fade" id="edit-author" style="display: none; padding-right: 17px;" aria-modal="true">
            <div class="modal-dialog modal-dialog-scrollable">
                <div class="modal-content">
                    <div class="modal-header">
                        <c:if test="${not empty item.pojo.authorId}">
                            <h3 class="modal-title"><fmt:message key="label.author.edit.update"
                                                                 bundle="${lang}"/></h3>
                        </c:if>
                        <c:if test="${empty item.pojo.authorId}">
                            <h3 class="modal-title"><fmt:message key="label.author.edit.insert"
                                                                 bundle="${lang}"/></h3>
                        </c:if>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>

                    <div class="modal-body">
                        <!-- form start -->
                        <form method="POST" action="${editAuthorUrl}" id="editAuthorForm">
                            <div class="form-group">
                                <label class=" form-control-label"><fmt:message key="label.author.name"
                                                                                bundle="${lang}"/></label>
                                <input type="text" name="pojo.authorName" placeholder="<fmt:message key="label.author.name"
                                                bundle="${lang}"/>" class="form-control" value="${item.pojo.authorName}">
                            </div>
                            <div class="form-group">
                                <label class=" form-control-label"><fmt:message key="label.sex"
                                                                                bundle="${lang}"/></label>
                                <select name="pojo.sex" class="custom-select">
                                    <option value="0" <c:if test="${item.pojo.sex=='0'}">selected</c:if>>khác</option>
                                    <option value="1" <c:if test="${item.pojo.sex=='1'}">selected</c:if>>Nam</option>
                                    <option value="2" <c:if test="${item.pojo.sex=='2'}">selected</c:if>>Nữ</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label class=" form-control-label"><fmt:message key="label.content"
                                                                                bundle="${lang}"/></label>
                                <textarea class="form-control" name="pojo.content" id="txtDesc" rows="8"
                                          placeholder="<fmt:message key="label.content" bundle="${lang}"/>">${item.pojo.content}</textarea>
                            </div>
                            <c:if test="${not empty item.pojo.authorId}">
                                <input type="hidden" name="pojo.authorId" value="${item.pojo.authorId}"/>
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

