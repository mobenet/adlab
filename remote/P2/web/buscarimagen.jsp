<%@page contentType="text/html" pageEncoding="UTF-8" session="false" %>
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
        <h1 class="titol">Buscar Imagen</h1>
        <form class="form" action="buscarimagen" method="GET" enctype="multipart/form-data">
            <input type="text" id="title" name="title" placeholder="Buscar por título:"> <br><br>
          
            <input type="text" id="author" name="author" placeholder="Buscar por autor:"> <br><br>

            <input type ="text" name="keywords" placeholder="Buscar por Keywords:"> <br><br>
            
            <input type ="date" name="date" > <br><br>
            
            <h4> Tipo de búsqueda:</h4> 
            <input type="radio"  name="tipo" value="exclusivo" checked > 
            <label for="Exclusivo"> Exclusiva </label>
            <input type="radio" name="tipo" value="inclusivo"> 
            <label for="Inclusivo"> Inclusiva </label> <br><br>

            <input class="botosubmit" type="submit" value="Buscar"> <br>
        </form>
       
        <div class="Cnav-b"><ul class="Cnav-ul"><li class="Cnav-ul-li"><a class="Cnav-ul-li-a" href ="menu.jsp"> Volver al Menú </a></li></ul></div>
    </body>
</html>