<%-- 
    Document   : modificarImagen
    Created on : 08-oct-2020, 0:24:42
    Author     : mo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Modificar Imagen</title>
    </head>
    <body>
         <% 
            HttpSession ses = request.getSession(false);
            String user = (String) ses.getAttribute("user");
            if(user == null) {
                response.sendRedirect("login.jsp");
            }
        %>
        
        <h2>Escribe todos los valores, tanto los que quieras modificar com los que no</h2>  
        <form method="POST" action="modificarImagen">
            Titulo: 
            <input type="text" name="titulo" required><br><br>
            Descripción: 
            <input type="text" name="descripcion" required><br><br>
            Palabras clave: 
            <input type="text" name="clave" required><br><br>
            Fecha creación:
            <input type="text" name="fechaC" required><br><br>
            Nombre archivo:
            <input type="text" name="fileN" required><br><br>
            <input type="submit" name="submit" value="Modifcar">
        </form>
        <br><br><a href="menu.jsp">Vuelve al Menú</a>
    </body>
</html>
