<%-- 
    Document   : error
    Created on : 30-sep-2020, 20:01:13
    Author     : mo
--%>
<%@page import="java.io.PrintWriter"%>
<%@ page isErrorPage = "true"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Página Error</title>
    </head>
    <body>

        <%
            // Analyze the servlet exception
            Throwable throwable = (Throwable) request
                    .getAttribute("javax.servlet.error.exception");
            Integer statusCode = (Integer) request
                    .getAttribute("javax.servlet.error.status_code");
            String servletName = (String) request
                    .getAttribute("javax.servlet.error.servlet_name");
            if (servletName == null) {
                servletName = "Unknown";
            }
            String requestUri = (String) request
                    .getAttribute("javax.servlet.error.request_uri");
            System.err.println(requestUri);
            if (requestUri == null) {
                requestUri = "Unknown";
            }

            // Set response content type
            response.setContentType("text/html");

            PrintWriter outo = response.getWriter();
            outo.write("<html><head><title>Exception/errors</title></head><body>");
            outo.write("<h3>Oops!</h3><p>Un error o excepción ha sucedido</p>");
            if (statusCode != 500) {
                outo.write("<h3>Detalles del error</h3>");
                outo.write("<strong>Status Code</strong>: " + statusCode + "<br>");
                outo.write("<strong>Requested URI</strong>: " + requestUri);
            } else {
                outo.write("<h3>Detalles de la excepción</h3>");
                outo.write("<ul><li>Nombre Servlet: " + servletName + "</li>");
                outo.write("<li>Nombre Excepción: " + throwable.getClass().getName() + "</li>");
                outo.write("<li>Requested URI: " + requestUri + "</li>");
                outo.write("<li>Mensaje excepción: " + throwable.getMessage() + "</li>");
                outo.write("</ul>");
            }

            outo.write("<br><br>");
            outo.write("<a href=\"menu.jsp\">Vuelve al menú</a>");
            outo.write("</body></html>");

        %>  
        <!--- <pre> exception.printStackTrace(response.getWriter()); %> </pre>
        <!--<h1>Error. Usuari o password incorrectes</h1>-->
        <br><br><a href="login.jsp">Vuelve al Login</a>
    </body>
</html>
