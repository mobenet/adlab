<%-- 
    Document   : eliminarImagen
    Created on : 07-oct-2020, 16:51:19
    Author     : Samuel
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Eliminar imagen</title>
    </head>
    <body>
        <div id="success">
            <p>Estás seguro que quieres eliminar tu imagen?</p>
            <button id="acc" onclick="eliminar()">Aceptar</button>
            <button id="canc" onclick="cancelar()">Cancelar</button>
        </div>
        <br><br><a href="menu.jsp">Vuelve al Menú</a>

        <script>
            let ses = window.sessionStorage;
            if (ses.getItem('user') === null) {
                window.location.replace('login.jsp');
            }
            const image = JSON.parse(ses.getItem('image'));
            const exit = "Has elegido cancelar la operacion";
            const success = "Tu imagen ha sido eliminada correctamente";
            const message = document.getElementById('success');

            async function eliminar() {

                const url = 'http://localhost:8080/RestAD/webresources/generic/delete/';
                let data = new URLSearchParams();

                data.append('id', image.id);
                const response = await fetch(url, {
                    method: 'POST',
                    body: data.toString(),
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded'
                    }
                });
                const res = await response.text();
                if (response.ok)
                    message.innerHTML = success;
                else {
                    ses.setItem('errorMessage','Error interno del servidor: Error al intentar eliminar la imagen');
                    window.location.replace('error.jsp');
                }

            }

            function cancelar() {
                message.innerHTML = exit;
            }
        </script>
    </body>
</html>