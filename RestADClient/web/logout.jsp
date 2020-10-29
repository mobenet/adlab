<%-- 
    Document   : logout
    Created on : 11-oct-2020, 21:37:27
    Author     : elchu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>LogOut</title>
    </head>
    <body>
        <h1>Cerrar sesión</h1>
        ¿Seguro que quieres salir? <br>
        <button onclick="logout()">Confirmar</button><br><br>
        <a href="menu.jsp">Volver al menú principal</a>
    </body>
    <script>
        let ses = window.sessionStorage;
        const user = ses.getItem('user');
        if (user === null)
            window.location.replace('login.jsp');
        function logout(){
            ses.removeItem('user');
            window.location.replace('login.jsp');
        }
    </script>
</html>
