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
        <%
            HttpSession ses = request.getSession(false);
            if (ses.getAttribute("user") != null) {
                response.sendRedirect("menu.jsp");
            }
        %>
        <h1>Buscar imagenes</h1>
        
        <form id="titul">        
                   
            Titulo: 
            <input type="text" name="title"/><br><br>
           
            Autor: 
            <input type="text" name="author"/><br><br>
            Palabras clave: 
            <input type="text" name="keywords"/><br><br>
            
            Fecha creación:
            <input type="text" name="cdate"/><br><br>
            
            <input type="Submit"   value="Acceder"/><br>
        </form>                             
        <script>
            const formimage = document.forms['titul'];
            formimage.onsubmit = async (e) => {
                e.preventDefault();
                //Validate!!
               
                const url = 'http://localhost:8080/RestAD/webresources/generic/searchcombi/'+formimage.elements['title'].value +'/'+formimage.elements['author'].value;'/'+formimage.elements['keywords'].value+'/'+formimage.elements['cdate'].value;
                const response = await fetch(url, {
                    method: 'GET'
                });
                const res = await response.text();
                document.getElementById("tablaimagen").innerHTML = res;
            };
            
        </script>
        <div id="tablaimagen"></div>
        
            <br><br>
            <a href="menu.jsp">Volver al menu</a><br><br>
    </body>
</html>
