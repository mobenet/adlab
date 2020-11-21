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
        <h1 class="titol">Registrar Imagen</h1>
        <form class="form" action="registrarimagen" method="POST" enctype="multipart/form-data">
            <input type="text" name="title" placeholder="Título" required> <br><br>
            <textarea rows="4" cols="37" name="description" placeholder="Descripción"></textarea> <br><br>
            <input type="text" name="author" placeholder="Autor" required> <br><br>
            <input type ="text" name="keywords" placeholder="Keywords"> <br><br>
            
            <label for="date"> Fecha de creación: </label> 
            <input type ="date" name="date"> <br><br>
            
            <label for="image"> Imagen: </label> 
            <input type="file" name="image" required accept="image/x-png, image/gif, image/jpeg, image/jpg, image/pnj"/> <br><br>
            
            <input class="botosubmit" type="submit" value="Registrar"> <br>
        </form>
        <div class="Cnav-b"><ul class="Cnav-ul"><li class="Cnav-ul-li"><a class="Cnav-ul-li-a" href ="menu.jsp"> Volver al Menú </a></li></ul></div>
    </body>
</html>
