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
            <form id="deleteImg" >
                <input type="submit" name="Aceptar" value="Aceptar"/>
                <input type="submit" name="Cancelar" value="Cancelar"/>
            </form>
        </div>
        <script>
            let ses = window.sessionStorage;
            if (ses.getItem('user') === null) {
                window.location.replace('menu.jsp');
            }
            var id = '9';
            const exit = "Has elegido cancelar la operacion";
            const success = "Tu imagen ha sido eliminada correctamente";

            const deleteImg = document.forms['deleteImg'];
            deleteImg.onsubmit = async (e) => {
                e.preventDefault();
                //Validate!!
                const url = 'http://localhost:8080/RestAD/webresources/generic/delete/';
                var data = new URLSearchParams();
                if (deleteImg.elements['Aceptar'].value === 'Aceptar') {
                    data.append('id', id);
                    const response = await fetch(url, {
                        method: 'POST',
                        body: data.toString(),
                        headers: {
                            'Content-Type': 'application/x-www-form-urlencoded'
                        }
                    });
                    const res = await response.text();
                    if (response.ok)
                        document.getElementById('success').innerHTML = success;
                } else if (deleteImg.elements['Cancelar'].value === 'Cancelar')
                    document.getElementById('success').innerHTML = exit;



            };



        </script>
    </body>
</html>
