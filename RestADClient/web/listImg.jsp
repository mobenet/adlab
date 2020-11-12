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
                    const imageArr = [];
                    for (let author of authors) {
                        const cols = author.parentElement.children;
                        const image = {
                            id: cols[0].innerHTML,
                            title: cols[1].innerHTML,
                            description: cols[2].innerHTML,
                            keywords: cols[3].innerHTML,
                            author: cols[4].innerHTML,
                            creation_date: cols[5].innerHTML,
                            storage_date: cols[6].innerHTML,
                            filename: cols[7].innerHTML
                        }
                        imageArr.append(image);
                        console.log(image);
                        if (image.author === user) {
                            cols[7].innerHTML = image.filename + "<br><button onclick=\"callMethod('Modificar',imageArr["+image.id+"])\">Modificar</button>"
                                    + "<button onclick=\"callMethod('Eliminar',imageArr["+image.id+"])\">Eliminar</button>";
                        }
                    }
                }
                function callMethod(method, img) {
                    ses.setItem('image', img);
                    if (method === 'Modificar')
                        window.location.href = 'modificarImagen.jsp';
                    else
                        window.location.href = 'eliminarImagen.jsp';
                }
            }
        </script>
    </body>
</html>
