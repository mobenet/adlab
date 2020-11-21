<%@page contentType="text/html"  pageEncoding="UTF-8" session="false" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <link rel="stylesheet" href="styles.css">    
    </head>
    <body> 
    <%HttpSession sessionsa = request.getSession(false);
    if ((String) sessionsa.getAttribute("user") == null) response.sendRedirect("login.jsp");
    %>
    <%
       String aux = "<input type=\"hidden\" name=\"id\" value=\""+ request.getParameter("id") +"\" />";
       String title = request.getParameter("title");
       String description = request.getParameter("description");
       String keywords = request.getParameter("keywords");
    %>
    
        <h1 class="titol">Modificar imagen </h1>
        <form class="form" action="modificarimagen" method="POST">
            
            <label for="title"> Título: </label>
            <input type="text"  style="margin-top: 50px" name="title" value="<%=title%>"> <br><br> 
            
            <label for="description"> Descripción: </label>
            <textarea rows="4" cols="37" name="description"><%=description%></textarea> <br><br>
            
            <label for="keywords"> Keywords: </label>
            <input type ="text" name="keywords" value="<%=keywords%>"> <br><br>
           <% out.write(aux); %>
            <input class="botosubmit" type="submit" value="Modificar"> 
        </form>
        <div class="Cnav-b"><ul class="Cnav-ul"><li class="Cnav-ul-li"><a class="Cnav-ul-li-a" href ="menu.jsp"> Volver al Menú </a></li></ul></div>
    </body>
</html>
