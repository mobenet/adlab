<%-- 
    Document   : registrarImagen
    Created on : 06-oct-2020, 18:56:16
    Author     : mo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registrar imagen</title>
    </head>
    <body>
        <div id="success">
            <form id="registerImg">
                <h1>Inserta tu imagen en nuestra Base de datos</h1>
                Titulo: 
                <input type="text" name="title" required><br><br>
                Descripción: 
                <input type="text" name="description" required><br><br>
                Palabras clave: 
                <input type="text" name="keywords" required><br><br>
                Autor: 
                <select id="authors" name="author" required>
                </select><br><br>
                Fecha creación:
                <input type=date name="creation" required><br><br>
                Archivo:
                <input type="file" name="file" required><br><br>
                <input  type="submit" value="Registrar"/>
            </form>
        </div>
        <br><br><a href="menu.jsp">Vuelve al Menú</a>
        <script>
            let ses = window.sessionStorage;
            if (ses.getItem('user') === null) {
                window.location.replace('login.jsp');
            } else {
                const baseurl = 'http://localhost:8080/RestAD/webresources/generic/';
                window.onload = async() => {
                    let response = await fetch(baseurl+'getUsers');
                    let res = await response.json();
                    let selectAuth = document.getElementById('authors');
                    res.forEach(e => {
                        selectAuth.options.add(new Option(e,e));
                    });
                }

                const registerImg = document.forms['registerImg'];
                registerImg.onsubmit = async (e) => {
                    e.preventDefault();
                    const url = baseurl+'register/';
                    const data = new FormData(registerImg);
                    const filename = registerImg.file.files[0].name;
                    data.append('filename',filename);
                    const response = await fetch(url, {
                        method: 'POST',
                        body: data
                    });
                    const success = "Tu imagen ha sido registrada correctamente"
                    if (response.ok) {
                        document.getElementById('success').innerHTML = success;
                    } else {
                        ses.setItem('errorMessage',"Error interno del servidor: Error al intentar registrar la imagen");
                        window.location.replace('error.jsp')
                    }
                };
            }
        </script>
    </body>
</html>