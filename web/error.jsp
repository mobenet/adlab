<%-- 
    Document   : error
    Created on : 30-sep-2020, 20:01:13
    Author     : mo
--%>
<%@ page isErrorPage="true" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Error</h1>
        <p>${pageContext.errorData.throwable.cause}</p>
    </body>
</html>
