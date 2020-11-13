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
            const imageArr = [];
            if (user === null)
                window.location.replace('login.jsp');
            else {
                window.onload = async() => {

                    const url = "http://localhost:8080/RestAD/webresources/generic/list";
                    const response = await fetch(url, {method: 'GET'});
                    document.getElementById('listaImagenes').innerHTML = await response.text();
                    const dataList = document.getElementsByClassName('imageData');

                    for (let data of dataList) {
                        const cols = data.children;
                        const image = {
                            id: cols[0].innerHTML,
                            title: cols[1].innerHTML,
                            description: cols[2].innerHTML,
                            keywords: cols[3].innerHTML,
                            author: cols[4].innerHTML,
                            creation_date: cols[5].innerHTML,
                            storage_date: cols[6].innerHTML,
                            filename: cols[7].innerHTML
                        };
                        imageArr.push(image);
                        cols[7].innerHTML = '<a href=image.jsp?id='+image.id+'&name='+image.filename+'>'+image.filename+'</a>';
                        if (image.author === user) {
                            const current = imageArr.length - 1;
                            cols[7].innerHTML += "<br><button onclick=\"callMethod('Modificar',imageArr[" + current + "])\">Modificar</button>"
                                    + "<button onclick=\"callMethod('Eliminar',imageArr[" + current + "])\">Eliminar</button>";
                        }
                    }
                }
                function callMethod(method, img) {
                    ses.setItem('image', JSON.stringify(img));
                    if (method === 'Modificar')
                        window.location.href = 'modificarImagen.jsp';
                    else
                        window.location.href = 'eliminarImagen.jsp';
                }
            }
        </script>
    </body>
</html>
