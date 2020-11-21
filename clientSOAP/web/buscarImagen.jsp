<%-- 
    Document   : buscarImagen
    Created on : 07-oct-2020, 20:21:33
    Author     : elchu
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" session="false"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Buscar Imagen</title>
    </head>
    <body>
        <%
            HttpSession ses = request.getSession(false);
            if(ses.getAttribute("user")==null)response.sendRedirect("login.jsp");
        %>
        <h1>Búsqueda de imagenes</h1>
        <h2>Palabras clave a buscar (una por campo)</h2> 
        <br><br>
        <form method="POST" action="buscarImagen" enctype="multipart/form-data">
            Titulo: 
            <input type="text" value="" name="title"><br><br>
            Palabras clave: 
            <input type="text" name="keywords"><br><br>
            Autor: 
            <input type="text" name="author"><br><br>
            Fecha creación:
            <input type="text" name="cdate"><br><br>

            <input type="submit" name="Submit" value="Submit"> 
            <br><br>
            <a href="menu.jsp">Menu principal</a>
        </form>
    </body>
</html>
