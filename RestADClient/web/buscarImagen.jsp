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
            <select id="authors" name="author"></select><br><br>
            
            Palabras clave: 
            <input type="text" name="keywords"/><br><br>

            Fecha creación:
            <input type="text" name="cdate"/><br><br>

            <input type="Submit"   value="Acceder"/><br>
        </form>                             
        <script>
            let ses = window.sessionStorage;
            const user = ses.getItem('user');
            const imageArr = [];
            if (user === null) window.location.replace('login.jsp');
            else {
                
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
                    let url = baseurl+'searchcombi?';
                    const queryString = new URLSearchParams(new FormData(formimage)).toString();
                    url+=queryString;
                    const response = await fetch(url, {
                        method: 'GET'
                    });
                    const res = await response.text();
                    document.getElementById("tablaimagen").innerHTML = res;
           
                    const dataList = document.getElementsByClassName('imgD');
                    for (let data of dataList) {
                        const cols = data.children;
                        const image = {
                            id: cols[0].innerHTML,
                            title: cols[1].innerHTML,
                            description: cols[2].innerHTML,
                            keywords: cols[3].innerHTML,
                            author: cols[4].innerHTML,
                            creation_date: cols[5].innerHTML,
                            storage_date: cols[6].innerHTML,
                            filename: cols[7].innerHTML
                        };
                        imageArr.push(image);
                        console.log(image);
                        if (image.author === user) {
                            const current = imageArr.length - 1;
                            cols[7].innerHTML = image.filename + "<br><button onclick=\"callMethod('Modificar',imageArr[" + current + "])\">Modificar</button>"
                                    + "<button onclick=\"callMethod('Eliminar',imageArr[" + current + "])\">Eliminar</button>";
                        }
                    }
                }
                    function callMethod(method, img) {
                        ses.setItem('image', JSON.stringify(img));
                        if (method === 'Modificar')
                            window.location.href = 'modificarImagen.jsp';
                        else
                            window.location.href = 'eliminarImagen.jsp';
                    }
                }
                
                
            
        </script>
        <div id="tablaimagen"></div>

        <br><br>
        <a href="menu.jsp">Volver al menu</a><br><br>
    </form>
</body>
</html>
