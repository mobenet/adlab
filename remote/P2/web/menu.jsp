<%@page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" >
        <link rel="stylesheet" href="styles.css">    
    </head>
    <body>
    <%HttpSession sessionsa = request.getSession(false);
    if ((String) sessionsa.getAttribute("user") == null) response.sendRedirect("login.jsp");
    %> 

    <nav class="Cnav-a">
        <ul class="Cnav-ul">
            <li class="Cnav-ul-li">
                <a class="Cnav-ul-li-a" href ="registrarimagen.jsp"> Registrar Imagen </a>
            </li>
            <li class="Cnav-ul-li">
                <a class="Cnav-ul-li-a" href ="listarimagen"> Listar Imágenes </a>
            </li>
            <li class="Cnav-ul-li">
                <a class="Cnav-ul-li-a" href ="buscarimagen.jsp"> Buscar Imagen </a>
            </li>
        </ul>
    </nav>
    </body>
</html>