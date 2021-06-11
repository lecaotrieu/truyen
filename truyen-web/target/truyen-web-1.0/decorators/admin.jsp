<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title><dec:title/></title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="<c:url value="/template/admin/plugins/fontawesome-free/css/all.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/template/admin/font-awesome/css/font-awesome.min.css"/>">
    <!-- themify-icons -->
    <link rel="stylesheet" href="<c:url value="/template/admin/themify-icons/css/themify-icons.css"/>">
    <!-- Ionicons -->
    <link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
    <!-- bootstrap -->
    <link rel="stylesheet" href="<c:url value="/template/admin/dist/css/custom-Input-file.css"/>">

    <link rel="stylesheet" href="<c:url value="/template/admin/dist/bootstrap/css/bootstrap.min.css"/>">
    <!-- Theme style -->
    <link rel="stylesheet" href="<c:url value="/template/admin/dist/css/adminlte.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/template/admin/dist/css/style.css"/>">
    <!-- overlayScrollbars -->
    <link rel="stylesheet"
          href="<c:url value="/template/admin/plugins/overlayScrollbars/css/OverlayScrollbars.min.css"/>">
    <!-- Google Font: Source Sans Pro -->
    <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700" rel="stylesheet">
       <!-- jQuery -->
    <script src="<c:url value="/template/admin/plugins/jquery/jquery.min.js"/>"></script>
    <script type='text/javascript'
            src='<c:url value="/template/admin/plugins/sweetalert2/sweetalert2.min.js"/>'></script>
    <link rel="stylesheet" href="<c:url value="/template/admin/plugins/sweetalert2/sweetalert2.min.css"/>">
    <!-- ckeditor -->
    <script type="text/javascript" src="<c:url value='/ckeditor/ckeditor.js' />"></script>
    <!-- selectize -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/selectize.js/0.12.6/js/standalone/selectize.min.js" integrity="sha256-+C0A5Ilqmu4QcSPxrlGpaZxJ04VjsRjKu+G82kl5UJk=" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/selectize.js/0.12.6/css/selectize.bootstrap3.min.css" integrity="sha256-ze/OEYGcFbPRmvCnrSeKbRTtjG4vGLHXgOqsyLFTRjg=" crossorigin="anonymous" />
    <!-- login -->
    <link rel="stylesheet" type="text/css" href="<c:url value="/template/account/css/util.css"/>">
    <link rel="stylesheet" type="text/css" href="<c:url value="/template/account/css/main.css"/>">
    <!--===============================================================================================-->
</head>
<body class="hold-transition sidebar-mini layout-fixed">
<div class="wrapper">
    <%@include file="/common/admin/header.jsp" %>
    <%@include file="/common/admin/menu.jsp" %>
    <div class="content-wrapper" style="min-height: 521px;">
        <!-- Content Header (Page header) -->
        <div class="content-header">
            <div class="container-fluid">
                <div class="row mb-2">
                    <div class="col-sm-6">
                        <h1 class="m-0 text-dark">Dashboard</h1>
                    </div><!-- /.col -->
                    <div class="col-sm-6">
                        <ol class="breadcrumb float-sm-right">
                            <li class="breadcrumb-item"><a href="#">Home</a></li>
                            <li class="breadcrumb-item active">Dashboard v1</li>
                        </ol>
                    </div><!-- /.col -->
                </div><!-- /.row -->
            </div><!-- /.container-fluid -->
        </div>
        <!-- Main content -->
        <section class="content justify-content-center">
            <dec:body/>
        </section>
        <!-- /.content-header -->
        <!-- Main content -->
        <!-- /.content -->
    </div>
    <%@include file="/common/admin/footer.jsp" %>
</div>

<!-- Bootstrap 4 -->
<script src="<c:url value="/template/admin/plugins/bootstrap/js/bootstrap.bundle.min.js"/>"></script>
<!-- overlayScrollbars -->
<script src="<c:url value="/template/admin/plugins/overlayScrollbars/js/jquery.overlayScrollbars.min.js"/>"></script>
<!-- Bootstrap core JavaScript -->
<script src="<c:url value="/template/admin/dist/bootstrap/js/bootstrap.bundle.min.js"/>"></script>
<!-- AdminLTE App -->
<script src="<c:url value="/template/admin/dist/js/adminlte.js"/>"></script>
<!-- AdminLTE dashboard demo (This is only for demo purposes) -->
<script src="<c:url value="/template/admin/dist/js/pages/dashboard.js"/>"></script>
<!-- AdminLTE for demo purposes -->
<script src="<c:url value="/template/admin/dist/js/demo.js"/>"></script>
<!-- global -->
<script src="<c:url value="/template/admin/dist/js/global_admin_script.js"/>"></script>
<script src="<c:url value="/template/admin/dist/js/custom-input-file.js"/>"></script>
<script src="<c:url value="/template/admin/dist/jquery.validate.min.js"/>"></script>
<!-- login -->
<!--===============================================================================================-->
<script src="<c:url value="/template/account/vendor/bootstrap/js/popper.js"/>"></script>
<script src="<c:url value="/template/account/vendor/select2/select2.min.js"/>"></script>
<!--===============================================================================================-->
<script src="<c:url value="/template/account/vendor/tilt/tilt.jquery.min.js"/>"></script>
<script src="<c:url value="/template/account/js/main.js"/>"></script>
<script type="text/javascript">

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
</body>
</html>
