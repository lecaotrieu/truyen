<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<c:url value="/ajax-user-edit" var="editUserUrl">
    <c:param name="urlType" value="url_edit"/>
</c:url>
<c:url value="/ajax-user-edit" var="changeAvatarUrl">
    <c:param name="urlType" value="url_avatar"/>
    <c:param name="pojo.userId" value="${item.pojo.userId}"/>
</c:url>
<c:choose>
    <c:when test="${not empty messageResponse}">
        ${messageResponse}
    </c:when>
    <c:otherwise>
        <div class="modal fade" id="edit-user"edit-user style="display: none; padding-right: 17px;" aria-modal="true">
            <div class="modal-dialog modal-dialog-scrollable">
                <div class="modal-content">
                    <div class="modal-header">
                        <c:if test="${not empty item.pojo.userId}">
                            <h3 class="modal-title"><fmt:message key="label.user.edit.update" bundle="${lang}"/></h3>
                        </c:if>
                        <c:if test="${empty item.pojo.userId}">
                            <h3 class="modal-title"><fmt:message key="label.user.edit.insert" bundle="${lang}"/></h3>
                        </c:if>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>

                    <div class="modal-body">
                        <!-- form start -->
                        <form method="POST" action="${editUserUrl}" id="editUserForm">
                            <c:if test="${not empty item.pojo.userId}">
                                <div class="form-group">
                                    <div class="mx-auto d-block row">
                                        <div class=" mx-auto imageCircle">
                                            <c:if test="${not empty item.pojo.avatar}">
                                                <c:set value="/repository/${item.pojo.avatar}"  var="imgUrl"/>
                                            </c:if>
                                            <img class="d-block rounded-circle"
                                                 src="${imgUrl} " alt="Card image cap" style="width: 150px; height: 150px" id="avatarUser">
                                        </div>
                                        <button type="button" href="#edit-avatar-user" data-toggle="modal" data-dismiss="modal" class="d-block mx-auto btn btn-outline-info">
                                            <fmt:message key="label.user.edit.avatar.change" bundle="${lang}"/>
                                        </button>
                                    </div>
                                </div>
                            </c:if>

                            <div class="form-group">
                                <label class=" form-control-label"><fmt:message key="label.user.name"
                                                                                bundle="${lang}"/></label>
                                <input type="text" name="pojo.name" placeholder="Name" class="form-control" value="${item.pojo.name}">
                            </div>
                            <div class="form-group">
                                <label class=" form-control-label"><fmt:message key="label.user.fullName"
                                                                                bundle="${lang}"/></label>
                                <input type="text" name="pojo.fullName" placeholder="Full name" class="form-control" value="${item.pojo.fullName}">
                            </div>
                            <div class="form-group">
                                <label class=" form-control-label"><fmt:message key="label.user.password"
                                                                                bundle="${lang}"/></label>
                                <input type="password" name="pojo.password" placeholder="Password" class="form-control" value="${item.pojo.password}">
                            </div>
                            <div class="form-group">
                                <label class=" form-control-label"><fmt:message key="label.user.email"
                                                                                bundle="${lang}"/></label>
                                <input type="email" name="pojo.email" placeholder="Enter Email" class="form-control" value="${item.pojo.email}">
                            </div>
                            <div class="form-group">
                                <label class=" form-control-label"><fmt:message key="label.user.address"
                                                                                bundle="${lang}"/></label>
                                <input type="text" name="pojo.address" placeholder="Address" class="form-control" value="${item.pojo.address}">
                            </div>
                            <div class="form-group">
                                <label class=" form-control-label"><fmt:message key="label.user.number"
                                                                                bundle="${lang}"/></label>
                                <input type="text" name="pojo.number" placeholder="Phone number" class="form-control" value="${item.pojo.number}">
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
                                <label class=" form-control-label"><fmt:message key="label.user.roleName"
                                                                                bundle="${lang}"/></label>
                                <select name="roleId" class="custom-select">
                                    <c:choose>
                                        <c:when test="${not empty item.pojo.userId}">
                                            <option value="${item.pojo.roleDTO.roleId}">${item.pojo.roleDTO.name}</option>
                                            <c:forEach items="${item.roles}" var="itemRole">
                                                <c:if test="${item.pojo.roleDTO.roleId != itemRole.roleId}">
                                                    <option value="${itemRole.roleId}">${itemRole.name}</option>
                                                </c:if>
                                            </c:forEach>
                                        </c:when>
                                        <c:otherwise>
                                            <option><fmt:message key="label.option.role" bundle="${lang}"/></option>
                                            <c:forEach items="${item.roles}" var="itemRole">
                                                <option value="${itemRole.roleId}">${itemRole.name}</option>
                                            </c:forEach>
                                        </c:otherwise>
                                    </c:choose>
                                </select>
                            </div>
                            <c:if test="${not empty item.pojo.userId}">
                                <input type="hidden" name="pojo.userId" value="${item.pojo.userId}" id="userid"/>
                            </c:if>
                            <input type="hidden" name="pojo.avatar" value="${item.pojo.avatar}" id="avatar"/>
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
        <div class="modal fade" id="edit-avatar-user" style="display: none; padding-right: 17px;" aria-modal="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h3 class="modal-title"><fmt:message key="label.user.edit.avatar.change" bundle="${lang}"/></h3>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>

                    <div class="modal-body">
                        <!-- form start -->
                        <form method="POST" action="${changeAvatarUrl}" enctype="multipart/form-data">
                            <div class="form-group">
                                <div class="mx-auto d-block row">
                                    <div class=" mx-auto imgCircle">
                                            <img class="d-block rounded-circle"
                                                 src="${imgUrl}" alt="Card image cap" style="width: 300px; height: 300px" id="img-avatar">
                                        <div class="input-file-container mx-auto">
                                            <input class="input-file" name="file" onchange="readURL(this,'img-avatar')" id="my-file" type="file">
                                            <label tabindex="0" for="my-file" class="d-flex justify-content-center rounded-circle input-file-trigger">
                                                <i class="fa fa-camera"></i>
                                            </label>
                                        </div>
                                        <p class="file-return"></p>
                                    </div>
                                </div>
                            </div>
                            <button type="button" href="#edit-user" data-toggle="modal" data-dismiss="modal" class="btn btn-outline-info">
                                <fmt:message key="label.back" bundle="${lang}"/>
                            </button>
                            <button type="submit" class="btn btn-outline-primary float-right"><fmt:message key="label.user.save.change" bundle="${lang}"/></button>
                        </form>
                    </div>
                </div>
                <!-- /.modal-content -->
            </div>
            <!-- /.modal-dialog -->
        </div>
    </c:otherwise>
</c:choose>

