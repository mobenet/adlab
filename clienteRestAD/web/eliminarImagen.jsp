<%-- 
    Document   : eliminarImagen
    Created on : 07-oct-2020, 16:51:19
    Author     : Samuel
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Eliminar imagen</title>
    </head>
    <body>
        <%
            HttpSession ses = request.getSession(false);
            if(ses.getAttribute("user")== null) response.sendRedirect("login.jsp");
        %>
        <p>Estás seguro que quieres eliminar tu imagen?</p>
        <form method="POST" action="eliminarImagen" >
            <input type="submit" name="Aceptar" value="Aceptar"/>
            <input type="submit" name="Cancelar" value="Cancelar"/>
        </form>

    </body>
</html>
