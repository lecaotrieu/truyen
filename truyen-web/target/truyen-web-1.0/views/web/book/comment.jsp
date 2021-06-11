<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<div class="comment-list col-12">
    <c:forEach items="${items.listResult}" var="comment">
        <div class="row">
            <c:set value="/repository/${comment.userDTO.avatar}" var="imgUrl"/>
            <div class="col-1 user-img">
                <img src="${imgUrl}" style="height: 50px;width: 50px;" class="" alt="User Image">
            </div>
            <div class="col-11 user-comment">
                <div class="row">
                    <a href="#" class="col-12">${comment.userDTO.fullName}</a>
                    <p class="col-12">${comment.content}</p>
                </div>
            </div>
        </div>
    </c:forEach>
</div>

