<%-- 
    Document   : modificarImagen
    Created on : 08-oct-2020, 0:24:42
    Author     : mo
--%>

<%@page import="java.net.URL"%>
<%@page import="org.me.image.ImageWS_Service"%>
<%@page import="java.util.List"%>
<%@page import="org.me.image.Image"%>
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
            } else {
                try {
        %>
        <table>
            <tr>
                <th>Titulo</th>
                <th>Descripcion</th>
                <th>Palabras Clave</th>
                <th>Autor</th>
                <th>Fecha de creacion</th>
                <th>Fecha de subida</th>
                <th>Nombre del archivo</th>
            </tr>
            <%
               org.me.image.ImageWS_Service service = new org.me.image.ImageWS_Service();
               org.me.image.ImageWS port = service.getImageWSPort();
               int id = Integer.parseInt(ses.getAttribute("imageid").toString()); 
               Image img = port.searchbyId(id);
               
            %>
            <tr>
                <td><%  out.println(img.getTitle());%></td>
                <td><%  out.println(img.getDescription());%></td>
                <td><%  out.println(img.getKeywords());%></td>
                <td><%
                    String autor = img.getAuthor();
                    out.println(autor);
                    %></td>
                <td><%  out.println(img.getCreationDate());%></td>
                <td><%  out.println(img.getStorageDate());%></td>
                <%
                    String filename = img.getFileName();
                %>
                <td>
                    <%
                        out.println("<a href=image.jsp?name=" + filename + "&id=" + id + ">" + filename + "</a>");
                    %>
                </td>
            </tr><%          
                            
                        } catch (Exception e) {
                            System.err.println(e.getMessage());
                        }
                    }
                }
            %>
        </table><br><br>
        
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
