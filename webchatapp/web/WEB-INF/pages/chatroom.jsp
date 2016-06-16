<%-- 
    Document   : chatroom_page
    Created on : Jun 15, 2016, 1:36:49 AM
    Author     : Cthulhu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP ChatRoom Page</title>
        
        <spring:url var="angularJs"
                    value="/res/js/angular/1.5.6/angular.min.js" />
        <spring:url var="jqueryJs"
                    value="/res/js/jquery/jquery-1.12.4.min.js" />
        <spring:url var="bootstrapJs"
                    value="/res/js/bootstrap/3.3.6/bootstrap.min.js" />
        <spring:url var="bootstrapCss"
                    value="/res/css/bootstrap.min.css" />
        <spring:url var="bootstrapThemeCss"
                    value="/res/css/bootstrap-theme.min.css" />
        <script src="${angularJs}"></script>
        <script src="${jqueryJs}"></script>
        <script src="${bootstrapJs}"></script>
        <link rel="stylesheet" type="text/css" href="${bootstrapCss}" />
        <link rel="stylesheet" type="text/css" href="${bootstrapThemeCss}" />
    </head>
    <body>
        <h1>Hello World!</h1>
    </body>
</html>
