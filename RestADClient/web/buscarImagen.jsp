<%-- 
    Document   : index
    Author     : elchu
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
        <h1>Buscar imagenes</h1>

        <form id="titul">        

            Titulo: 
            <input type="text" name="title"/><br><br>

            Autor: 
            <select id="authors" name="author">
            </select>
            <br><br>
            Palabras clave: 
            <input type="text" name="keywords"/><br><br>

            Fecha creación:
            <input type="text" name="cdate"/><br><br>

            <input type="Submit"   value="Acceder"/><br>
        </form>                             
        <script>
            let ses = window.sessionStorage;
            if (ses.getItem('user') === null) {
                window.location.replace('login.jsp');
            } else {
                const baseurl = 'http://localhost:8080/RestAD/webresources/generic/';

                window.onload = async() => {
                    let response = await fetch(baseurl + 'getUsers');
                    let res = await response.json();
                    let selectAuth = document.getElementById('authors');
                    res.forEach(e => {
                        selectAuth.options.add(new Option(e, e));
                    });
                }
                const formimage = document.forms['titul'];
                formimage.onsubmit = async (e) => {
                    e.preventDefault();
                    //Validate!!
                    let url = baseurl+'searchcombi?'
                    const queryString = new URLSearchParams(new FormData(formimage)).toString();
                    url+=queryString;
                    const response = await fetch(url, {
                        method: 'GET'
                    });
                    const res = await response.text();
                    document.getElementById("tablaimagen").innerHTML = res;
                };
            }
        </script>
        <div id="tablaimagen"></div>

        <br><br>
        <a href="menu.jsp">Volver al menu</a><br><br>
    </form>
</body>
</html>
