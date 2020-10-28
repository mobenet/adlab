<%-- 
    Document   : modificarImagen
    Created on : 08-oct-2020, 0:24:42
    Author     : mo
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="ourpackage.OurDao"%>
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
        %>
        <table>
            <tr>
                <th>Titulo</th>
                <th>Descripcion</th>
                <th>Palabras Clave</th>
                <th>Autor</th>
                <th>Fecha de creacion</th>
                <th>Nombre del archivo</th>
            </tr>
            <% 
                try{
                    OurDao.startDB();
                    int id = Integer.parseInt(ses.getAttribute("imageId").toString());
                    ResultSet rs = OurDao.getImage(id);
                    while(rs.next()) {
            %>
            <tr>
                <td><%  String titulo = rs.getString("TITLE");
                        out.println(titulo);%></td>
                <td><%  String descr= rs.getString("DESCRIPTION");
                        out.println(rs.getString("DESCRIPTION"));%></td>
                <td><%  String key = rs.getString("KEYWORDS");
                        out.println(key);%></td>
                <td><%
                        String autor = rs.getString("AUTHOR");
                        out.println(autor);
                    %></td>
                <td><%  String crea_date = rs.getString("CREATION_DATE");
                        out.println(crea_date);%></td>
                
                <%
                        String filename = rs.getString("FILENAME");
                %>
                <td>
                <%
                        out.println("<a href=image.jsp?name="+filename+"&id="+id+">"+filename+"</a>");
                %>
                
                </td>
            </tr>
            <%  }
                    OurDao.stopDB();
                } catch(Exception e){
                    System.err.println(e.getMessage());
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
