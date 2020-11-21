<%@page import="javax.servlet.annotation.WebServlet"%>


<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" session="false"%>


<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <link rel="stylesheet" href="styles.css">    
        <title>JSP Page</title>
    </head>
    
    <% 
        HttpSession sessionsa = request.getSession();
        /* Creating a new session*/
        sessionsa.setAttribute("user",null);
    %>
    
    <body >
      <h1 class="titleInici">Image Bulk Application</h1>
      <div class="loginbody">
        <form action="login" method="POST" enctype="" class="login-form" >
            <h1> Login </h1><br>
            <input class="form-input-material" type="Text" name="usuario" autocomplete="off" required placeholder="Usuario"/>
            <input class="form-input-material" type="password" name="password" autocomplete="off" required placeholder="Contraseña"/>
            <br>
            <button type="Submit" class="loginbutton" name="login" >Login</button>
            
        
        </form>
      </div>
    </body>
</html>
