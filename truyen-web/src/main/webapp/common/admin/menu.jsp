<c:url value="/admin-home/user-management" var="userManagement">
    <c:param name="urlType" value="url_list"/>
</c:url>
<c:url value="/admin-home/book-management" var="bookManagement">
    <c:param name="urlType" value="url_list"/>
</c:url>
<c:url value="/admin-home/category-management" var="categoryManagement">
    <c:param name="urlType" value="url_list"/>
</c:url>
<c:url value="/admin-home/author-management" var="authorManagement">
    <c:param name="urlType" value="url_list"/>
</c:url>
<c:url value="/account-login" var="logoutUrl">
    <c:param name="urlType" value="logout"/>
</c:url>
<c:url value="/admin-account-login" var="loginUrl">
    <c:param name="urlType" value="login"/>
</c:url>
<aside class="main-sidebar sidebar-dark-primary elevation-4">
    <!-- Brand Logo -->
    <a href="index3.html" class="brand-link">
        <img src="<c:url value="/template/admin/dist/img/AdminLTELogo.png"/>" alt="AdminLTE Logo" class="brand-image img-circle elevation-3" style="opacity: .8">
        <span class="brand-text font-weight-light">AdminLTE 3</span>
    </a>

    <!-- Sidebar -->
    <div class="sidebar os-host os-theme-light os-host-overflow os-host-overflow-y os-host-resize-disabled os-host-scrollbar-horizontal-hidden os-host-transition"><div class="os-resize-observer-host"><div class="os-resize-observer observed" style="left: 0px; right: auto;"></div></div><div class="os-size-auto-observer" style="height: calc(100% + 1px); float: left;"><div class="os-resize-observer observed"></div></div><div class="os-content-glue" style="margin: 0px -8px; width: 249px; height: 570px;"></div><div class="os-padding"><div class="os-viewport os-viewport-native-scrollbars-invisible" style="overflow-y: scroll;"><div class="os-content" style="padding: 0px 8px; height: 100%; width: 100%;">
        <!-- Sidebar user panel (optional) -->
        <div class="user-panel mt-3 pb-3 mb-3 d-flex">
            <c:if test="${not empty login_user}">
                <c:set value="/repository/${login_user.avatar}"  var="imgUrl"/>
                <div class="image">
                    <img src="${imgUrl}" style="height: 30px;width: 30px;" class="img-circle elevation-2" alt="User Image">
                </div>
                <div class="info">
                    <a href="#" class="">${login_user.fullName}</a>
                </div>
                <a class="btn btn-outline-info" href="${logoutUrl}" role="button"><fmt:message key="label.logout" bundle="${lang}"/></a>
            </c:if>
           <c:if test="${empty login_user}">
               <div class="image">
               </div>
               <div class="info">
                   <a class="btn btn-outline-dark" href="${loginUrl}">
                       <fmt:message key="label.login" bundle="${lang}"/>
                   </a>
               </div>
            </ul>
           </c:if>
        </div>

        <!-- Sidebar Menu -->
        <nav class="mt-2">
            <ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu" data-accordion="false">
                <!-- Add icons to the links using the .nav-icon class
                     with font-awesome or any other icon font library -->
                <li class="nav-item has-treeview menu-open">
                    <a href="#" class="nav-link active">
                        <i class="nav-icon fas fa-tachometer-alt"></i>
                        <p>
                            <fmt:message key="label.dashboard" bundle="${lang}"/>
                            <i class="right fas fa-angle-left"></i>
                        </p>
                    </a>
                    <ul class="nav nav-treeview">
                        <li class="nav-item">
                            <a href="${userManagement}" class="nav-link">
                                <i class="fa fa-users nav-icon"></i>
                                <p><fmt:message key="label.user.manage" bundle="${lang}"/></p>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="${bookManagement}" class="nav-link">
                                <i class="fa fa-book nav-icon"></i>
                                <p><fmt:message key="label.book.manage" bundle="${lang}"/></p>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="${categoryManagement}" class="nav-link">
                                <i class="far fa-circle nav-icon"></i>
                                <p><fmt:message key="label.category.manage" bundle="${lang}"/></p>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="${authorManagement}" class="nav-link">
                                <i class="far fa-circle nav-icon"></i>
                                <p><fmt:message key="label.author.manage" bundle="${lang}"/></p>
                            </a>
                        </li>
                    </ul>
                </li>
                <li class="nav-item has-treeview menu-open">
                    <a href="" class="nav-link">
                        <i class="nav-icon fas fa-copy"></i>
                        <p>
                            Layout Options
                            <i class="fas fa-angle-left right"></i>
                            <span class="badge badge-info right">6</span>
                        </p>
                    </a>
                    <ul class="nav nav-treeview" style="display: block;">
                        <li class="nav-item">
                            <a href="pages/layout/collapsed-sidebar.html" class="nav-link">
                                <i class="far fa-circle nav-icon"></i>
                                <p>Collapsed Sidebar</p>
                            </a>
                        </li>
                    </ul>
                </li>
                <li class="nav-header">EXAMPLES</li>
                <li class="nav-item has-treeview menu-open">
                    <a href="#" class="nav-link">
                        <i class="nav-icon far fa-plus-square"></i>
                        <p>
                            Extras
                            <i class="fas fa-angle-left right"></i>
                        </p>
                    </a>
                    <ul class="nav nav-treeview" style="display: block;">
                        <li class="nav-item">
                            <a href="pages/examples/login.html" class="nav-link">
                                <i class="far fa-circle nav-icon"></i>
                                <p>Login</p>
                            </a>
                        </li>
                    </ul>
                </li>
            </ul>
        </nav>
        <!-- /.sidebar-menu -->
    </div></div></div><div class="os-scrollbar os-scrollbar-horizontal os-scrollbar-unusable os-scrollbar-auto-hidden"><div class="os-scrollbar-track"><div class="os-scrollbar-handle" style="width: 100%; transform: translate(0px, 0px);"></div></div></div><div class="os-scrollbar os-scrollbar-vertical os-scrollbar-auto-hidden"><div class="os-scrollbar-track"><div class="os-scrollbar-handle" style="height: 45.3895%; transform: translate(0px, 0px);"></div></div></div><div class="os-scrollbar-corner"></div></div>
    <!-- /.sidebar -->
</aside>
<!-- Left Panel -->
