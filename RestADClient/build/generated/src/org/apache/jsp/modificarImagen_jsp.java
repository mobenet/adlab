package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class modificarImagen_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>Modificar Imagen</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("         ");
 
       //     HttpSession ses = request.getSession(false);
       //     String user = (String) ses.getAttribute("user");
       //     if(user == null) {
       //         response.sendRedirect("login.jsp");
       //     } else {
        
      out.write("\n");
      out.write("        <table border=\"1\">\n");
      out.write("            <tr>\n");
      out.write("                <th>Titulo</th>\n");
      out.write("                <th>Descripcion</th>\n");
      out.write("                <th>Palabras Clave</th>\n");
      out.write("                <th>Autor</th>\n");
      out.write("                <th>Fecha de creacion</th>\n");
      out.write("                <th>Nombre del archivo</th>\n");
      out.write("            </tr>\n");
      out.write("            ");
 
     
        String fechaS = "2020/20/20"; 
        String author = "hola";
        String fileN = "nombreArchivo";
            
      out.write("\n");
      out.write("       \n");
      out.write("        </table><br><br>\n");
      out.write("        <h2>Escribe todos los valores, tanto los que quieras modificar com los que no</h2>  \n");
      out.write("        <form method=\"POST\" action=\"http://localhost:8080/RestAD/webresources/generic/register/\">\n");
      out.write("            Titulo: \n");
      out.write("            <input type=\"text\" name=\"title\" ><br><br>\n");
      out.write("            Descripción: \n");
      out.write("            <input type=\"text\" name=\"description\" ><br><br>\n");
      out.write("            Palabras clave: \n");
      out.write("            <input type=\"text\" name=\"keywords\" ><br><br>\n");
      out.write("            Fecha creación:\n");
      out.write("            <input type=date name=\"creadate\" ><br><br>\n");
      out.write("            Autor: <p> {{ author }} </p>\n");
      out.write("            Fecha guardado: <p> {{ fechaS }} </p>\n");
      out.write("            Nombre Archivo: <p> {{fileN}}</p>\n");
      out.write("            <input type=\"submit\" name=\"submit\" value=\"Modifcar\">\n");
      out.write("        </form>\n");
      out.write("        <br><br><a href=\"menu.jsp\">Vuelve al Menú</a>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
