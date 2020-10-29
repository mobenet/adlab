package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class registrarImagen_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>Registrar imagen</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        ");

            /* HttpSession ses = request.getSession(false);*/
            //   if (ses.getAttribute("user") == null) {
            //      response.sendRedirect("login.jsp");
            // }

            String reqUrl = "http://localhost:8080/RestAD/webresources/generic/";
        
      out.write("\n");
      out.write("        <form method=\"POST\" id=\"formRegImg\" action=\"http://localhost:8080/RestAD/webresources/generic/registerImage/\">\n");
      out.write("            <h1>Inserta tu imagen en nuestra Base de datos</h1>\n");
      out.write("            Titulo: \n");
      out.write("            <input type=\"text\" name=\"titulo\" placeholder=\"TITULO\" id=\"tit\" ><br><br>\n");
      out.write("            Descripción: \n");
      out.write("            <input type=\"text\" name=\"desc\" ><br><br>\n");
      out.write("            Palabras clave: \n");
      out.write("            <input type=\"text\" name=\"key\" ><br><br>\n");
      out.write("            Autor: \n");
      out.write("            <input type=\"text\" name=\"auth\" ><br><br>\n");
      out.write("            Fecha creación:\n");
      out.write("            <input type=date name=\"dataC\" ><br><br>\n");
      out.write("            Archivo:\n");
      out.write("            <input type=\"file\" name=\"imagen\"><br><br>\n");
      out.write("            <button  type=\"submit\" name=\"submit\">Registrar</button>\n");
      out.write("        </form>\n");
      out.write("\n");
      out.write("        <form action=\"http://localhost:8080/RestAD/webresources/generic/registerImage/\" method=\"POST\">\n");
      out.write("            <div>\n");
      out.write("                <label for=\"say\">What greeting do you want to say?</label>\n");
      out.write("                <input name=\"say\" id=\"say\" value=\"Hi\">\n");
      out.write("            </div>\n");
      out.write("            <div>\n");
      out.write("                <label for=\"to\">Who do you want to say it to?</label>\n");
      out.write("                <input name=\"to\" id=\"to\" value=\"Mom\">\n");
      out.write("            </div>\n");
      out.write("            <div>\n");
      out.write("                <button>Send my greetings</button>\n");
      out.write("            </div>\n");
      out.write("        </form>\n");
      out.write("\n");
      out.write("        <script>\n");
      out.write("            function regI() {\n");
      out.write("                document.getElementById(\"form\").action = \"http://localhost:8080/RestAD/webresources/generic/registerImage/\" + document.getElementById(\"tit\").value;\n");
      out.write("            }\n");
      out.write("        </script>    \n");
      out.write("        <br><br><a href=\"login.jsp\">Vuelve al Login</a>\n");
      out.write("        <br><br><a href=\"menu.jsp\">Vuelve al Menú</a>\n");
      out.write("\n");
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
