<%-- 
    Document   : registroUsuarios
    Created on : 11-oct-2020, 21:44:02
    Author     : elchu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registro de Usuario</title>
    </head>
    <body>
        <%
            HttpSession ses = request.getSession(false);
            if (ses.getAttribute("user") != null) {
                response.sendRedirect("menu.jsp");
            }
        %>
        <h1>Registrate en el sistema</h1>
        <form action="registroUsuarios" method="POST">
            Usuario: <input type="text"     name="usuario"/><br>
            Contraseña: <input type="password" name="password"/><br>
            Confirma Contraseña: <input type="password" name="opassword"/><br>
            <input type="Submit"   value="Enviar"/><br>
        </form><br><br>
        <a href="login.jsp">Volver</a>
    </body>
</html>
