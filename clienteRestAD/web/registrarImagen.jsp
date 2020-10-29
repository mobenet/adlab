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
            /* HttpSession ses = request.getSession(false);*/
            //   if (ses.getAttribute("user") == null) {
            //      response.sendRedirect("login.jsp");
            // }

            String reqUrl = "http://localhost:8080/RestAD/webresources/generic/";
        %>
        <form method="POST" action="http://localhost:8080/RestAD/webresources/generic/register/">
            <h1>Inserta tu imagen en nuestra Base de datos</h1>
            Titulo: 
            <input type="text" name="title" ><br><br>
            Descripción: 
            <input type="text" name="description" ><br><br>
            Palabras clave: 
            <input type="text" name="keywords" ><br><br>
            Autor: 
            <input type="text" name="author" ><br><br>
            Fecha creación:
            <input type=date name="creation" ><br><br>
            Archivo:
            <input type="file" name="imagen"><br><br>
            <button  type="submit" name="submit">Registrar</button>
        </form>

  
        <br><br><a href="login.jsp">Vuelve al Login</a>
        <br><br><a href="menu.jsp">Vuelve al Menú</a>

    </body>
</html>
