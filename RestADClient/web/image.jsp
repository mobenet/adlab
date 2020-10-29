<%-- 
    Document   : image
    Created on : 12-oct-2020, 18:32:47
    Author     : Samuel
--%>

<%@page import="ourpackage.selectImage"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>
            <%
            String name = request.getParameter("name");
            out.println(name);
            %>
        </title>
    </head>
    <body>
        <%
            HttpSession ses = request.getSession(false);
            if(ses.getAttribute("user") == null){
                response.sendRedirect("login.jsp");
            } else {
                out.println("<img src=images/"+selectImage.getImageName(Integer.parseInt(request.getParameter("id")),name));
            }
        %>
    </body>
</html>
