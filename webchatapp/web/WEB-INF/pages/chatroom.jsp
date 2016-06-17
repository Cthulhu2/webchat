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
              href="<spring:url value="/res/css/font-awesome.min.css" />" />
    </head>
    <body>
        <h1>Hello, <sec:authentication property="principal.userName" />!</h1>
        <a class="btn btn-primary btn-lg"
           href="<spring:url value="/logout" />">
            Logout
        </a>
    </body>
</html>
