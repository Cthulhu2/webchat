<%-- 
    Document   : chatroom_page
    Created on : Jun 15, 2016, 1:36:49 AM
    Author     : Cthulhu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP ChatRoom Page</title>

        <script src="<spring:url value="/res/js/angular/1.5.6/angular.min.js" />"></script>
        <script src="<spring:url value="/res/js/jquery/jquery-1.12.4.min.js" />"></script>
        <script src="<spring:url value="/res/js/bootstrap/3.3.6/bootstrap.min.js" />"></script>
        <link rel="stylesheet" type="text/css"
              href="<spring:url value="/res/css/bootstrap.min.css" />" />
        <link rel="stylesheet" type="text/css"
              href="<spring:url value="/res/css/bootstrap-theme.min.css" />" />
        <link rel="stylesheet" type="text/css"
              href="<spring:url value="/res/css/chatroom.css" />" />
    </head>
    <body>
        <div class="container">
            <div class="row flex-row">
                <div class="col-md-3">
                    <div class="panel panel-primary flex-col">
                        <div class="panel-heading">
                            <h3 class="panel-title">
                                <spring:message code="chatroom.lbl.title" />
                                <a class="pull-right" href="<spring:url value="/logout" />">
                                    <spring:message code="chatroom.btn.logout" />
                                </a>
                            </h3>
                        </div>
                        <div class="panel-body flex-grow">
                            <div class="pre-scrollable chat-room">
                                <samp></samp>
                            </div>
                        </div>
                        <div class="panel-footer">
                            <div class="row">
                                <button class="col-md-1 col-md-push-11 btn btn-primary btn-lg">
                                    <spring:message code="chatroom.btn.send" />
                                </button>
                                <textarea class="col-md-11 col-md-pull-1 panel-body"
                                          placeholder="<spring:message code="chatroom.hint.message" />"
                                          rows="1"></textarea>  
                            </div>
                        </div>
                    </div>
                </div>
            </div><!--/row-->
        </div><!--/container-->
    </body>
</html>
