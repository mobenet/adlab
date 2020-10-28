<%-- 
    Document   : registrarImagen
    Created on : 06-oct-2020, 18:56:16
    Author     : mo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registrar imagen</title>
    </head>
    <body>
        <%
            HttpSession ses = request.getSession(false);
            if (ses.getAttribute("user") == null) {
                response.sendRedirect("login.jsp");
            }
        %>
        <form method="POST" action="registrarImagen" enctype="multipart/form-data">
            <h1>Inserta tu imagen en nuestra Base de datos</h1>
            Titulo: 
            <input type="text" name="titulo" required ><br><br>
            Descripción: 
            <input type="text" name="descripcion" required><br><br>
            Palabras clave: 
            <input type="text" name="clave" required><br><br>
            Autor: 
            <input type="text" name="author" required><br><br>
            Fecha creación:
            <input type="text" name="fechaC" required><br><br>
            Archivo:
            <input type="file" name="imagen"><br><br>
            <input type="submit" name="submit" value="Registrar">
        </form>
        <br><br><a href="login.jsp">Vuelve al Login</a>
        <br><br><a href="menu.jsp">Vuelve al Menú</a>

    </body>
</html>
