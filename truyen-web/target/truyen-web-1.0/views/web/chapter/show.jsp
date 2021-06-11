<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<c:url value="/ajax-chapter-list" var="showListChapterUrl">
</c:url>
<!DOCTYPE html>
<html>
<head>
    <title><fmt:message key="label.book.add" bundle="${lang}"/></title>
    href="<c:url value="/template/web/bootstrap-4.4.1-dist/css/bootstrap.min.css"/>">
</head>
<body>
<div class="book-detail content">
    <div class="row chapter-detail-header">
        <div class="col-12">
            <div class="chapter-title mx-auto" style="width: fit-content;">
                <h3>Chương ${item.pojo.chapter}: ${item.pojo.title}</h3>
            </div>
        </div>
    </div>
    <div class="row chapter-content">
        <div class="col-12">
            <div class="chapter-content-detail">
                ${item.pojo.content}
            </div>
        </div>
    </div>
    <div class="row page-chapter">
        <div class="col-12">

        </div>
        <ul class="pager mx-auto" style="width: fit-content;">
            <c:if test="${item.pojo.chapter != 1}">
                <li class="previous btn" type="<fmt:message key="label.chapter.previous" bundle="${lang}"/>">
                    <c:url value="/doc-truyen" var="previous" >
                        <c:param name="urlType" value="url_edit"/>
                        <c:param name="bookName" value="${item.bookName}"/>
                        <c:param name="pojo.chapter" value="${item.pojo.chapter - 1}"/>
                    </c:url>
                    <a href="${previous}" class="btn btn-outline-success"><i class="fas fa-chevron-left"></i></a>
                </li>
            </c:if>
            <c:if test="${item.pojo.chapter == 1}">
                <li class="previous btn" title="<fmt:message key="label.chapter.previous" bundle="${lang}"/>">
                    <a class="btn btn-outline-success"><i class="fas fa-chevron-left"></i></a>
                </li>
            </c:if>
            <c:if test="${item.lastChapter == item.pojo.chapter}">
                <li class="next btn" title="<fmt:message key="label.chapter.next" bundle="${lang}"/>">
                    <a class="btn btn-outline-success"><i class="fas fa-chevron-right"></i></a>
                </li>
            </c:if>
            <c:if test="${item.lastChapter != item.pojo.chapter}">
                <li class="next btn" title="<fmt:message key="label.chapter.next" bundle="${lang}"/>">
                    <c:url value="/doc-truyen" var="next" >
                        <c:param name="urlType" value="url_edit"/>
                        <c:param name="bookName" value="${item.bookName}"/>
                        <c:param name="pojo.chapter" value="${item.pojo.chapter + 1}"/>
                    </c:url>
                    <a href="${next}" class="btn btn-outline-success"><i class="fas fa-chevron-right"></i></a>
                </li>
            </c:if>

        </ul>
    </div>
</div>
</body>
</html>