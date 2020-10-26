package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.net.URL;
import org.me.image.ImageWS_Service;
import java.util.List;
import org.me.image.Image;

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
      out.write("\n");
      out.write("\n");
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
 
            HttpSession ses = request.getSession(false);
            String user = (String) ses.getAttribute("user");
            if(user == null) {
                response.sendRedirect("login.jsp");
            } else {
                try {
        
      out.write("\n");
      out.write("        <table>\n");
      out.write("            <tr>\n");
      out.write("                <th>Titulo</th>\n");
      out.write("                <th>Descripcion</th>\n");
      out.write("                <th>Palabras Clave</th>\n");
      out.write("                <th>Autor</th>\n");
      out.write("                <th>Fecha de creacion</th>\n");
      out.write("                <th>Fecha de subida</th>\n");
      out.write("                <th>Nombre del archivo</th>\n");
      out.write("            </tr>\n");
      out.write("            ");

               org.me.image.ImageWS_Service service = new org.me.image.ImageWS_Service();
               org.me.image.ImageWS port = service.getImageWSPort();
               int id = Integer.parseInt(ses.getAttribute("imageid").toString()); 
               Image img = port.searchbyId(id);
               
            
      out.write("\n");
      out.write("            <tr>\n");
      out.write("                <td>");
  out.println(img.getTitle());
      out.write("</td>\n");
      out.write("                <td>");
  out.println(img.getDescription());
      out.write("</td>\n");
      out.write("                <td>");
  out.println(img.getKeywords());
      out.write("</td>\n");
      out.write("                <td>");

                    String autor = img.getAuthor();
                    out.println(autor);
                    
      out.write("</td>\n");
      out.write("                <td>");
  out.println(img.getCreationDate());
      out.write("</td>\n");
      out.write("                <td>");
  out.println(img.getStorageDate());
      out.write("</td>\n");
      out.write("                ");

                    String filename = img.getFileName();
                
      out.write("\n");
      out.write("                <td>\n");
      out.write("                    ");

                        out.println("<a href=image.jsp?name=" + filename + "&id=" + id + ">" + filename + "</a>");
                    
      out.write("\n");
      out.write("                </td>\n");
      out.write("            </tr>");
          
                            
                        } catch (Exception e) {
                            System.err.println(e.getMessage());
                        }
                    }
                }
            
      out.write("\n");
      out.write("        </table><br><br>\n");
      out.write("        \n");
      out.write("        <h2>Escribe todos los valores, tanto los que quieras modificar com los que no</h2>  \n");
      out.write("        <form method=\"POST\" action=\"modificarImagen\">\n");
      out.write("            Titulo: \n");
      out.write("            <input type=\"text\" name=\"titulo\" required><br><br>\n");
      out.write("            Descripción: \n");
      out.write("            <input type=\"text\" name=\"descripcion\" required><br><br>\n");
      out.write("            Palabras clave: \n");
      out.write("            <input type=\"text\" name=\"clave\" required><br><br>\n");
      out.write("            Fecha creación:\n");
      out.write("            <input type=\"text\" name=\"fechaC\" required><br><br>\n");
      out.write("            Nombre archivo:\n");
      out.write("            <input type=\"text\" name=\"fileN\" required><br><br>\n");
      out.write("            <input type=\"submit\" name=\"submit\" value=\"Modifcar\">\n");
      out.write("        </form>\n");
      out.write("        <br><br><a href=\"menu.jsp\">Vuelve al Menú</a>\n");
      out.write("   \n");
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
