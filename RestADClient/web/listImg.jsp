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
                    const authors = document.getElementsByClassName('autor');
                    Array.prototype.forEach.call(authors, function (author) {
                        const name = author.innerHTML;
                        if (name === user) {
                            const tdFilename = author.parentElement.lastChild;
                            const id = author.parentElement.firstChild.innerHTML;
                            tdFilename.innerHTML = tdFilename.innerHTML + "<br><button onclick=\"callMethod('Modificar',"+id+")\">Modificar</button>"
                                    +"<button onclick=\"callMethod('Eliminar',"+id+")\">Eliminar</button>";
                        }
                    });
                }
                function callMethod(method,id){
                    ses.setItem('imageId',id);
                    if(method === 'Modificar') window.location.href = 'modificarImagen.jsp';
                    else window.location.href = 'eliminarImagen.jsp';
                }
            }
        </script>
    </body>
</html>
