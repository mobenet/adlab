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
        <%
            HttpSession ses = request.getSession(false);
            if (ses.getAttribute("user") == null) {
                response.sendRedirect("login.jsp");
            }
        %>
        <h1>Cerrar sesión</h1>
        <form action="logout" method="POST">
            ¿Seguro que quieres salir? <br>
            <input type="Submit" value="Salir"/><br><br>
        </form>
        <a href="menu.jsp">Volver al menú principal</a>

    </body>
</html>
