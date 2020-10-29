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
        <h1>Registrate en el sistema</h1>
        <form id="registerForm">
            Usuario: <input type="text" name="user" required/><br>
            Contraseña: <input type="password" name="password" required/><br>
            Repite la contraseña: <input type="password" name="opassword" required/><br>
            <input type="Submit"   value="Registrarse"/><br>
            <br><br>
        </form>
        <div id="errorMessage" style="color: red"></div>
        <a href="login.jsp">Volver</a>
        <script>
            let ses = window.sessionStorage;
            if(ses.getItem('user') !== null){
                window.location.replace('menu.jsp');
            }
            const registerForm = document.forms['registerForm'];
            registerForm.onsubmit = async (e) => {
                e.preventDefault();
                const pass = registerForm.elements['password'].value;
                if (registerForm.elements.opassword.value !== pass) {
                    document.getElementById('errorMessage').innerHTML = 'Las dos contraseñas deben coincidir';
                } else {
                    const url = 'http://localhost:8080/RestAD/webresources/generic/registerUser';
                    var data = new URLSearchParams();
                    const user = registerForm.elements['user'].value;
                    data.append('user', user);
                    data.append('password', registerForm.elements['password'].value);
                    const response = await fetch(url, {
                        method: 'POST',
                        body: data.toString(),
                        headers: {
                            'Content-Type': 'application/x-www-form-urlencoded'
                        }
                    });
                    const res = await response.json();
                    if (res.success) {
                        ses.setItem('user',user);
                        window.location.replace('menu.jsp');
                    } else
                        document.getElementById('errorMessage').innerHTML = res.message;
                }
            };
        </script>
    </body>
</html>
