<%-- 
    Document   : index
    Created on : 30-sep-2020, 16:42:21
    Author     : mo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page errorPage="error.jsp"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <h1>Hola! Accede a tu cuenta</h1>
        <form id="loginForm">
            Usuario: <input type="text" name="user" required/><br>
            Contraseña: <input type="password" name="password" required/><br>
            <input type="Submit"   value="Acceder"/><br>
            <br><br>
        </form>
        <div id="errorMessage" style="color: red"></div>
        <a href="registroUsuarios.jsp">Registrate aqui</a><br><br>
        <script>
            let ses = window.sessionStorage;
            if (ses.getItem('user') !== null) {
                window.location.replace('menu.jsp');
            }
            const loginForm = document.forms['loginForm'];
            loginForm.onsubmit = async (e) => {
                e.preventDefault();
                const url = 'http://localhost:8080/RestAD/webresources/generic/login';
                var data = new URLSearchParams();
                const user = loginForm.elements['user'].value;
                data.append('user', user);
                data.append('password', loginForm.elements['password'].value);
                const response = await fetch(url, {
                    method: 'POST',
                    body: data.toString(),
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded'
                    }
                });
                const res = await response.json();
                if (res.success) {
                    ses.setItem('user', user);
                    window.location.replace('menu.jsp');
                } else
                    document.getElementById('errorMessage').innerHTML = res.message;
            };
        </script>
    </body>
</html>
