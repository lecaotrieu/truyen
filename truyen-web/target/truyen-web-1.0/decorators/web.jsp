<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Trang chủ</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Login V1</title>

    <link rel="stylesheet" type="text/css"
          href="<c:url value="/template/web/bootstrap-4.4.1-dist/css/bootstrap.min.css"/>">
    <!-- font awesome -->
    <link rel="stylesheet" href="<c:url value="/template/admin/font-awesome/css/font-awesome.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/template/web/fontawesome-free/css/all.min.css"/>">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <link rel="stylesheet" type="text/css"
          href="<c:url value="/template/web/fontawesome-free/css/fontawesome.min.css"/>">
    <script src="<c:url value="/template/web/fontawesome-free/js/fontawesome.min.js"/>"></script>
    <!-- sweetalert -->
    <script type='text/javascript'
            src='<c:url value="/template/web/sweetalert2/sweetalert2.min.js"/>'></script>
    <link rel="stylesheet" href="<c:url value="/template/web/sweetalert2/sweetalert2.min.css"/>">

    <link rel="stylesheet" type="text/css" href="<c:url value="/template/web/style.css"/>">
    <!-- selectize -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="<c:url value="/template/web/selectize.js-master/dist/js/standalone/selectize.min.js"/>"></script>
    <link rel="stylesheet"
          href="<c:url value="/template/web/selectize.js-master/dist/css/selectize.bootstrap3.css"/>"/>

    <link rel="icon" type="image/png" href="<c:url value="/template/account/images/icons/favicon.ico"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/template/account/vendor/animate/animate.css"/>">
    <link rel="stylesheet" type="text/css"
          href="<c:url value="/template/account/vendor/css-hamburgers/hamburgers.min.css"/>">
    <link rel="stylesheet" type="text/css" href="<c:url value="/template/account/vendor/select2/select2.min.css"/>">
    <link rel="stylesheet" type="text/css" href="<c:url value="/template/account/css/util.css"/>">
    <link rel="stylesheet" type="text/css" href="<c:url value="/template/account/css/main.css"/>">
    <!-- ckeditor -->
    <script type="text/javascript" src="<c:url value='/ckeditor/ckeditor.js' />"></script>
    <script src="<c:url value="/template/web/jquery.twbsPagination.js"/>"></script>
</head>
<body>
<%@include file="/common/web/header.jsp" %>
<div class="container" style="min-height: 900px;">
    <dec:body/>
</div>
<%@include file="/common/web/footer.jsp" %>

<!-- Bootstrap core JavaScript -->
<script src="<c:url value="/template/web/bootstrap-4.4.1-dist/js/bootstrap.bundle.min.js"/>"></script>
<script src="<c:url value="/template/account/vendor/bootstrap/js/popper.js"/>"></script>
<script src="<c:url value="/template/account/vendor/select2/select2.min.js"/>"></script>
<script src="<c:url value="/template/account/vendor/tilt/tilt.jquery.min.js"/>"></script>
<script>
    $('.js-tilt').tilt({
        scale: 1.1
    })
    function readURL(input, imageId) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();
            reader.onload = function (e) {
                $('#' + imageId).attr('src', reader.result);
            }
            reader.readAsDataURL(input.files[0]);
        }
    }
    function showAlertBeforeDelete(callback) {
        swal({
            title: "Xác nhận xóa",
            text: "Bạn có chắc muốn xóa những dữ liệu đã chọn !",
            type: "warning",
            showCancelButton: true,
            confirmButtonText: "Xác nhận",
            cancelButtonText: "Hủy bỏ",
            confirmButtonClass: "btn btn-success",
            cancelButtonClass: "btn btn-danger"
        }).then(function (isConfirm) {
            if (isConfirm) {
                callback();
            }
        })
    }

</script>
<script src="<c:url value="/template/account/js/main.js"/>"></script>

</body>
</html>
