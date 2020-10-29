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

        
      out.write("\n");
      out.write("        <form id=\"registerImg\">\n");
      out.write("            <h1>Inserta tu imagen en nuestra Base de datos</h1>\n");
      out.write("            Titulo: \n");
      out.write("            <input type=\"text\" name=\"title\" required><br><br>\n");
      out.write("            Descripción: \n");
      out.write("            <input type=\"text\" name=\"description\" required><br><br>\n");
      out.write("            Palabras clave: \n");
      out.write("            <input type=\"text\" name=\"keywords\" required><br><br>\n");
      out.write("            Autor: \n");
      out.write("            <input type=\"text\" name=\"author\" required><br><br>\n");
      out.write("            Fecha creación:\n");
      out.write("            <input type=date name=\"creation\" required><br><br>\n");
      out.write("            Archivo:\n");
      out.write("            <input type=\"text\" name=\"filename\" required><br><br>-->\n");
      out.write("            <button  type=\"submit\" name=\"submit\">Registrar</button>\n");
      out.write("        </form>\n");
      out.write("\n");
      out.write("\n");
      out.write("        <br><br><a href=\"login.jsp\">Vuelve al Login</a>\n");
      out.write("        <br><br><a href=\"menu.jsp\">Vuelve al Menú</a>\n");
      out.write("        <script>\n");
      out.write("            const registerImg = document.forms['registerImg'];\n");
      out.write("            registerImg.onsubmit = async (e) => {\n");
      out.write("                e.preventDefault();\n");
      out.write("                //Validate!!\n");
      out.write("                const url = 'http://localhost:8080/RestAD/webresources/generic/register';\n");
      out.write("                var data = new URLSearchParams();\n");
      out.write("                data.append('title', registerImg.elements['title'].value);\n");
      out.write("                data.append('description', registerImg.elements['description'].value);\n");
      out.write("                data.append('keywords', registerImg.elements['keywords'].value);\n");
      out.write("                data.append('author', registerImg.elements['author'].value);\n");
      out.write("                data.append('creation', registerImg.elements['creation'].value);\n");
      out.write("                data.append('filename', registerImg.elements['filename'].value);\n");
      out.write("                \n");
      out.write("                const response = await fetch(url, {\n");
      out.write("                    method: 'POST',\n");
      out.write("                    body: data.toString(),\n");
      out.write("                    headers: {\n");
      out.write("                         'Content-Type': 'application/x-www-form-urlencoded'\n");
      out.write("                    }\n");
      out.write("                });\n");
      out.write("                const res = await response.text();\n");
      out.write("                \n");
      out.write("                if (response.ok) {\n");
      out.write("                    window.location.replace('menu.jsp');\n");
      out.write("                } else\n");
      out.write("                    alert('Registro erroneo');\n");
      out.write("            };\n");
      out.write("        </script>\n");
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
