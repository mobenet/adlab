<%-- 
    Document   : image
    Created on : 12-oct-2020, 18:32:47
    Author     : Samuel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>
        </title>
    </head>
    <body>
        <img id="showImg"/>
        <script>
            const ses = window.sessionStorage;
            if (ses.getItem('user') === null) {
                window.location.replace('login.jsp');
            } else {
                const params = new URLSearchParams(window.location.search);
                document.title = params.get('name');
                showImg.src = 'http://localhost:8080/RestAD/webresources/generic/download/' + params.get('id');
            }
        </script>
    </body>
</html>
