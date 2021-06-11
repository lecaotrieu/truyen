<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="loginUrl" value="/account-login">
    <c:param name="urlType" value="login"/>
</c:url>
    <div class="d-flex justify-content-center align-self-center" >
        <div class="wrap-login100 login-content">
            <div class="login100-pic js-tilt" data-tilt>
                <img src="<c:url value="/template/account/images/12.jpg"/>" alt="IMG">
            </div>
            <form action="${loginUrl}" method="post" id="formLogin" class="login100-form validate-form">
		<span class="login100-form-title">
			<fmt:message key="label.login" bundle="${lang}"/>
		</span>

                <div class="wrap-input100 validate-input"
                     data-validate="<fmt:message key="label.notempty.userName" bundle="${lang}"/>">
                    <input class="input100" type="text" name="pojo.name"
                           placeholder="<fmt:message key="label.user.name" bundle="${lang}"/>">
                    <span class="focus-input100"></span>
                    <span class="symbol-input100">
                <i class="fa fa-user" aria-hidden="true"></i>
            </span>
                </div>

                <div class="wrap-input100 validate-input"
                     data-validate="<fmt:message key="label.notempty.password" bundle="${lang}"/>">
                    <input class="input100" type="password" name="pojo.password"
                           placeholder="<fmt:message key="label.user.password" bundle="${lang}"/>">
                    <span class="focus-input100"></span>
                    <span class="symbol-input100">
                <i class="fa fa-lock" aria-hidden="true"></i>
            </span>
                </div>
                <p id="messageRP"
                   style="color: red;font-family: Helvetica;font-weight: bold;font-size: 12px;text-align: right;padding-right: 18px;">${messageResponse}</p>
                <div class="container-login100-form-btn">
                    <button class="login100-form-btn">
                        <fmt:message key="label.login" bundle="${lang}"/>
                    </button>
                </div>

                <div class="text-center p-t-12">
						<span class="txt1">
							Forgot
						</span>
                    <a class="txt2" href="#">
                        Username / Password?
                    </a>
                </div>

                <div class="text-center p-t-136">
                    <a class="txt2" href="#">
                        Create your Account
                        <i class="fa fa-long-arrow-right m-l-5" aria-hidden="true"></i>
                    </a>
                </div>
            </form>
        </div>
    </div>
<script>
    $(document).ready(function () {
        var url = window.location.pathname;
        url = url + "?urlType=login";
        $('#formLogin').attr('action', url);
    })
</script>
