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
            <table border="1">
                <tr>
                    <th>Titulo</th>
                    <th>Descripcion</th>
                    <th>Palabras Clave</th>
                    <th>Autor</th>
                    <th>Fecha de creacion</th>
                    <th>Nombre del archivo</th>
                </tr>
            </table><br><br>

            <form id="modifyImg">
                <h1>Modifica tu imagen como deseess</h1>
                Titulo: 
                <input type="text" name="title" ><br><br>
                Descripción: 
                <input type="text" name="description" ><br><br>
                Palabras clave: 
                <input type="text" name="keywords" ><br><br>
                Autor: 
                <input type="text" name="author" value="author"> <br><br>

                Fecha creación:
                <input type=date name="creation" ><br><br>
                Fecha de guardado: 
                Nombre del Archivo:          
                <input  type="submit" value="Modificar"/>
            </form>
        </div>
        <br><br><a href="menu.jsp">Vuelve al Menú</a>
        <script>
            let ses = window.sessionStorage;
            if (ses.getItem('user') === null) {
                window.location.replace('menu.jsp');
            }
            const image = JSON.parse(ses.getItem('image'));
            const modifyImg = document.forms['modifyImg'];
            modifyImg.title.value = image.title;
            modifyImg.description.value = image.description;
            modifyImg.keywords.value = image.keywords;
            modifyImg.creation.value = image.creation_date;
            modifyImg.onsubmit = async (e) => {
                e.preventDefault();
                //Validate!!
                const url = 'http://localhost:8080/RestAD/webresources/generic/modify/';
                var data = new URLSearchParams();
                data.append('id', image.id);
                data.append('title', modifyImg.title.value);
                data.append('description', modifyImg.description.value);
                data.append('keywords', modifyImg.keywords.value);
                data.append('author', modifyImg.author.value);
                data.append('creation', modifyImg.creation.value);


                //data.append('filename', modifyImg.elements['filename'].value);

                const response = await fetch(url, {
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
                } else
                    alert('Modificacion erronea');
            };
        </script>
    </body>
</html>
