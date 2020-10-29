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

        <table border="1">
            <tr>
                <th>Titulo</th>
                <th>Descripcion</th>
                <th>Palabras Clave</th>
                <th>Autor</th>
                <th>Fecha de creacion</th>
                <th>Nombre del archivo</th>
            </tr>

        </table><br><br>
        <h2>Escribe todos los valores, tanto los que quieras modificar com los que no</h2>  
        <form method="POST" action="http://localhost:8080/RestAD/webresources/generic/register/">
            Titulo: 
            <input type="text" name="title" ><br><br>
            Descripción: 
            <input type="text" name="description" ><br><br>
            Palabras clave: 
            <input type="text" name="keywords" ><br><br>
            Fecha creación:
            <input type=date name="creadate" ><br><br>
            Autor:  
            Fecha guardado: <p> ${fechaS} </p>
            Nombre Archivo: <p> {{fileN}}</p>
            <input type="submit" name="submit" value="Modifcar">
        </form>
        <br><br><a href="menu.jsp">Vuelve al Menú</a>
    </body>
    <script>
        
    </script>
</html>
