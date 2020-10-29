<%-- 
    Document   : list
    Created on : 06-oct-2020, 20:37:51
    Author     : mo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listar imagenes</title>
    </head>
    <body>
        <div id="listaImagenes"></div>
        <a href="menu.jsp">Vuelve al menu</a>
        <script>
            let ses = window.sessionStorage;
            const user = ses.getItem('user');
            if (user === null)
                window.location.replace('login.jsp');
            else {
                window.onload = async() => {
                    const url = "http://localhost:8080/RestAD/webresources/generic/list";
                    const response = await fetch(url, {method: 'GET'});
                    let text = await response.text();
                    document.getElementById('listaImagenes').innerHTML = text;
                    document.getElementsByClassName('autor').forEach(e => {
                       const name = e.innerHTML;
                       if(name === user)
                    });
                }
            }
        </script>
    </body>
</html>
