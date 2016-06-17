<%-- 
    Document   : login_page
    Created on : Jun 15, 2016, 1:36:15 AM
    Author     : Cthulhu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Login Page</title>

        <script src="<spring:url value="/res/js/angular/1.5.6/angular.min.js" />"></script>
        <script src="<spring:url value="/res/js/jquery/jquery-1.12.4.min.js" />"></script>
        <script src="<spring:url value="/res/js/bootstrap/3.3.6/bootstrap.min.js" />"></script>
        <link rel="stylesheet" type="text/css"
              href="<spring:url value="/res/css/bootstrap.min.css" />" />
        <link rel="stylesheet" type="text/css"
              href="<spring:url value="/res/css/bootstrap-theme.min.css" />" />
        <link rel="stylesheet" type="text/css"
              href="<spring:url value="/res/css/font-awesome.min.css" />" />
        <script type="text/javascript">
            $(window).load(function () {
                $('#login-modal').modal({
                    backdrop: 'static',
                    keyboard: false
                });
                $('#login-modal').modal('show');
            });
        </script>
    </head>

    <body>
        <div class="modal fade" id="login-modal" tabindex="-1" role="dialog"
             aria-labelledby="loginModalLabel">
            <div class="modal-dialog" role="document">
                <form action="<spring:url value="/login" />" method="post"
                      class="form-horizontal">
                    <div class="modal-content">
                        <div class="modal-header">
                            <sec:authorize access="isAuthenticated()">
                                <h1>Hello, <sec:authentication property="principal.userName" />!</h1>
                            </sec:authorize>
                            <sec:authorize access="isAnonymous()">
                                <h1>Hello, Guest!</h1>
                            </sec:authorize>
                        </div>
                        <div class="modal-body">
                            <div class="login-card">
                                <div class="login-form">
                                    <c:if test="${param.error != null}">
                                        <div class="alert alert-danger">
                                            <p>Invalid username and password.</p>
                                        </div>
                                    </c:if>
                                    <c:if test="${param.logout != null}">
                                        <div class="alert alert-success">
                                            <p>You have been logged out successfully.</p>
                                        </div>
                                    </c:if>
                                    <sec:authorize access="isAuthenticated()">
                                        <h2>Whom do you want be?</h2>
                                    </sec:authorize>
                                    <sec:authorize access="isAnonymous()">
                                        <h2>What's your name?</h2>
                                    </sec:authorize>
                                    <div class="input-group input-sm">
                                        <label class="input-group-addon" for="username">
                                            <i class="fa fa-user"></i>
                                        </label>
                                        <input type="text" class="form-control"
                                               id="username" name="ssoId"
                                               placeholder="Enter Username" required>
                                    </div>
                                    <input type="hidden" class="form-control"
                                           value="ignored"
                                           id="password" name="password"
                                           placeholder="Enter Password" required>
                                    <input type="hidden" name="${_csrf.parameterName}"
                                           value="${_csrf.token}" />
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <input type="submit" value="Log in"
                                   class="btn btn-block btn-primary btn-default">
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>
