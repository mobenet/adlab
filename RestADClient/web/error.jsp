<%-- 
    Document   : error
    Created on : 30-sep-2020, 20:01:13
    Author     : mo
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Página Error</title>
    </head>
    <body>
        <h3>Oops!</h3>
        <div id="message">
            <%
                Integer statusCode = (Integer) request
                        .getAttribute("javax.servlet.error.status_code");
                String servletName = (String) request
                        .getAttribute("javax.servlet.error.servlet_name");
                String requestUri = (String) request
                        .getAttribute("javax.servlet.error.request_uri");
                if (servletName == null) {
                    servletName = "Unknown";
                }
                if (requestUri == null) {
                    requestUri = "Unknown";
                }
                if (statusCode != null) {
                    switch (statusCode) {
                        case 404:
            %>
            El recurso que estas buscando no existe!<br>
            <strong>Requested URI: </strong>
            <%
                    out.print(requestUri);
                    break;
                case 403:
            %>
            El recurso al que estas intentando acceder esta prohibido<br>
            <strong>Requested URI: </strong>
            <%
                    out.print(requestUri);
                    break;
                default:
            %>
            <strong>Requested URI: </strong>

            <%
                            out.print(requestUri);
                    }
                }
            %>  
        </div>
        <br><br>
        <span id="return"></span>
        <script>
            const ses = window.sessionStorage;
            const err = ses.getItem('errorMessage');
            if (err !== null) {
                document.getElementById('message').innerHTML = err;
                ses.removeItem('errorMessage');
            }
            const ret = document.getElementById('return');
            if (ses.getItem('user') === null) {
                ret.innerHTML = '<a href="login.jsp">Vuelve al login</a>';
            } else
                ret.innerHTML = '<a href="menu.jsp">Vuelve al menu</a>';
        </script>
    </body>
</html>
