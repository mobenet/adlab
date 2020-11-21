<%@page contentType="text/html" pageEncoding="UTF-8" session="false"%>
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
       String aux = "<input type=\"hidden\" name=\"product_no\" value=\""+ request.getParameter("id") +"\" />";
    %>
        <h1 class="titol">¿Seguro que quieres eliminar la imagen?</h1> <br>
        <form class="form" action="eliminarimagen" method="POST">
            <h3 style="text-align: center">No podrás deshacer esta acción</h3>
            <input class="botosubmit" style="margin-top:100px" type="submit" value="Eliminar">
            <% out.write(aux); %>
        </form>
        
        <div class="Cnav-b"><ul class="Cnav-ul"><li class="Cnav-ul-li"><a class="Cnav-ul-li-a" href ="menu.jsp"> Volver al Menú </a></li></ul></div>
    </body>
</html>
