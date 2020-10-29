<%-- 
    Document   : registroUsuarios
    Created on : 11-oct-2020, 21:44:02
    Author     : elchu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registro de Usuario</title>
    </head>
    <body>
        <%
            HttpSession ses = request.getSession(false);
            /*if (ses.getAttribute("user") != null) {
                response.sendRedirect("menu.jsp");
            }*/
        %>
        <h1>Registrate en el sistema</h1>
        <form id="registerForm">
            Usuario: <input type="text"     name="user"/><br>
            Contraseña: <input type="password" name="password"/><br>
            Confirma Contraseña: <input type="password" name="opassword"/><br>
            <input type="Submit"   value="Registrarse"/><br>
        </form><br><br>
        <a href="login.jsp">Volver</a>
        <script>
            const registerForm = document.forms['registerForm']
            registerForm.onsubmit = async (e) => {
                e.preventDefault()
                //validate
                const url = 'http://localhost:8080/RestAD/webresources/generic/register'
                var data = new URLSearchParams()
                data.append('user', registerForm.elements['user'].value)
                data.append('password', registerForm.elements['password'].value)
                const response = await fetch(url, {
                    method: 'POST',
                    body: data.toString(),
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded'
                    }
                });
                const res = await response.json()
                if (res.success) {
                    window.location.replace('menu.jsp')
                } else
                    alert('Registro erroneo')
            };
        </script>
    </body>
</html>
