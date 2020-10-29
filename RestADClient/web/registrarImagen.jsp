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
        <%
            /* HttpSession ses = request.getSession(false);*/
            //   if (ses.getAttribute("user") == null) {
            //      response.sendRedirect("login.jsp");
            // }

        %>
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
                <input type="text" name="author" required><br><br>
                Fecha creación:
                <input type=date name="creation" required><br><br>
                Archivo:
                <input type="text" name="filename" required><br><br>
                <input  type="submit" value="Registrar"/>
            </form>
        </div>


        <br><br><a href="login.jsp">Vuelve al Login</a>
        <br><br><a href="menu.jsp">Vuelve al Menú</a>
        <script>
            /* let ses = window.sessionStorage;
             if (ses.getItem('user') !== null) {
             window.location.replace('menu.jsp');
             }*/
            const registerImg = document.forms['registerImg'];
            registerImg.onsubmit = async (e) => {
                e.preventDefault();
                //Validate!!
                const url = 'http://localhost:8080/RestAD/webresources/generic/register/';
                var data = new URLSearchParams();
                data.append('title', registerImg.elements['title'].value);
                data.append('description', registerImg.elements['description'].value);
                data.append('keywords', registerImg.elements['keywords'].value);
                data.append('author', registerImg.elements['author'].value);
                data.append('creation', registerImg.elements['creation'].value);
                data.append('filename', registerImg.elements['filename'].value);

                const response = await fetch(url, {
                    method: 'POST',
                    body: data.toString(),
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded'
                    }
                });
                const res = await response.text();
                const success = "Tu imagen ha sido registrada correctamente"
                if (response.ok) {
                    document.getElementById('success').innerHTML = succes; 
                } else
                    alert('Registro erroneo');
            };
        </script>
    </body>
</html>
