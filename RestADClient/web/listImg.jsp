<%-- 
    Document   : list
    Created on : 06-oct-2020, 20:37:51
    Author     : mo
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="ourpackage.OurDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listar imagenes</title>
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
                <th>Fecha de subida</th>
                <th>Nombre del archivo</th>
            </tr>
            <% 
                try{
                    OurDao.startDB();
                    ResultSet rs = OurDao.getAllImages();
                    while(rs.next()) {
            %>
            <tr>
                <td><%  out.println(rs.getString("TITLE"));%></td>
                <td><%  out.println(rs.getString("DESCRIPTION"));%></td>
                <td><%  out.println(rs.getString("KEYWORDS"));%></td>
                <td><%
                        String autor = rs.getString("AUTHOR");
                        out.println(autor);
                    %></td>
                <td><%  out.println(rs.getString("CREATION_DATE"));%></td>
                <td><%  out.println(rs.getString("STORAGE_DATE"));%></td>
                <%
                        String filename = rs.getString("FILENAME");
                        int id = rs.getInt("ID");%>
                <td>
                    <%
                            out.println("<a href=image.jsp?name="+filename+"&id="+id+">"+filename+"</a>");
                            if(autor.equals(user)){
                    %>
                    <form action=selectImage method="POST">
                        <input type="hidden" value="<%out.print(filename);%>" name="name"/>
                        <input type="hidden" value="<%out.print(id);%>" name="id"/>
                        <input type="submit" value="Modificar" name="action"/>
                        <input type="submit" value="Eliminar" name="action"/>
                    </form>
                </td>
            </tr>
            <%          }
                    }
                    OurDao.stopDB();
                } catch(Exception e){
                    System.err.println(e.getMessage());
                }
            }
            %>
        </table><br><br>
        <a href="menu.jsp">Vuelve al menu</a>
    </body>
</html>
