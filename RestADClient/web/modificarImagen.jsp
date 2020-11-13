<%-- 
    Document   : modificarImagen
    Created on : 08-oct-2020, 0:24:42
    Author     : mo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Modificar imagen</title>
    </head>
    <body>

        <div id="success">

            <form id="modifyImg">
                <h1>Modifica tu imagen como deseess</h1>
                Titulo: 
                <input type="text" name="title" ><br><br>
                Descripción: 
                <input type="text" name="description" ><br><br>
                Palabras clave: 
                <input type="text" name="keywords" ><br><br>
                Autor: 
                <select id="authors" name="author" required>
                </select><br><br>
                Fecha creación:
                <input type=date name="creation" ><br><br>
                Fecha de guardado: <span id="storageDate"></span><br><br>
                Nombre del Archivo: <span id="fileName"></span><br><br>
                <input  type="submit" value="Modificar"/>
            </form>
        </div>
        <br><br><a href="menu.jsp">Vuelve al Menú</a>
        <script>
            let ses = window.sessionStorage;
            if (ses.getItem('user') === null) {
                window.location.replace('menu.jsp');
            } else {
                const baseurl = 'http://localhost:8080/RestAD/webresources/generic/';
                const modifyImg = document.getElementById('modifyImg');
                const image = JSON.parse(ses.getItem('image'));
                window.onload = async() => {
                    let response = await fetch(baseurl + 'getUsers');
                    let res = await response.json();
                    res.forEach(e => {
                        modifyImg.author.options.add(new Option(e, e));
                    });
                    modifyImg.author.value = image.author;
                }
                modifyImg.title.value = image.title;
                modifyImg.description.value = image.description;
                modifyImg.keywords.value = image.keywords;
                modifyImg.creation.value = image.creation_date;
                document.getElementById("storageDate").innerHTML = image.storage_date;
                document.getElementById("fileName").innerHTML = image.filename;
                modifyImg.onsubmit = async (e) => {

                    e.preventDefault();
                    let data = new URLSearchParams();
                    data.append('id', image.id);
                    data.append('title', modifyImg.title.value);
                    data.append('description', modifyImg.description.value);
                    data.append('keywords', modifyImg.keywords.value);
                    data.append('author', modifyImg.author.value);
                    data.append('creation', modifyImg.creation.value);

                    const response = await fetch(baseurl + 'modify', {
                        method: 'POST',
                        body: data.toString(),
                        headers: {
                            'Content-Type': 'application/x-www-form-urlencoded'
                        }
                    });
                    const res = await response.text();
                    const success = "Tu imagen ha sido modificada correctamente"
                    if (response.ok) {
                        document.getElementById('success').innerHTML = success;
                    } else window.location.replace('error.jsp');
                };
            }

        </script>
    </body>
</html>
