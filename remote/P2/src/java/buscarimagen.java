import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(urlPatterns = {"/buscarimagen"})
public class buscarimagen extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        if (session.getAttribute("user") == null) response.sendRedirect("login.jsp");
        String title = request.getParameter("title");
        String author =  request.getParameter("author");
        String date = request.getParameter("date");
        String keywords = request.getParameter("keywords");
        String tipo = request.getParameter("tipo");
        boolean tipoo = false;
        if (tipo.equals("inclusivo")) tipoo = true;
        Vector<Vector<String>> returned = DAOImages.SearchImage(title,keywords,author, date,  tipoo);
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println(" <link rel=\"stylesheet\" href=\"styles.css\">  ");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1 style = \"text-align:center\">Los siguientes resultados coinciden con tu criterio de búsqueda</h1>");   
            out.println("<div style=\"padding-bottom:3%; padding-top:2%;\" class=\"Cnav-b\"><ul class=\"Cnav-ul\"><li class=\"Cnav-ul-li\"><a class=\"Cnav-ul-li-a\" href =\"menu.jsp\"> Volver al Menú </a></li></ul></div>");
            int cont = 0;
            while (cont < returned.size() ) {
                out.println("<div class=\"listaimatge\">");
                out.println("<h3>");
                out.println(returned.elementAt(cont).elementAt(1));
                out.println("</h3>");
                out.println("<h5>");
                out.println(returned.elementAt(cont).elementAt(2));
                out.println("</h5>");
                out.println("<br>");
                out.println("<img width=\"300\" height=\"300\" src=\"Images\\" + returned.elementAt(cont).elementAt(4) + "\">" );
                //filename
                out.println("<br>");
                if (session.getAttribute("user").equals(returned.elementAt(cont).elementAt(2))) {
                    out.println("<button  style = \" float:left \" onclick= \"location.href = 'eliminarimagen.jsp?id=" + returned.elementAt(cont).elementAt(0) + "' \" type=\"submit\"  >Eliminar</button>");
                    out.println("<button  style = \" float:left \" onclick= \"location.href = 'modificarimagen.jsp?id=" + returned.elementAt(cont).elementAt(0) + "' \" type=\"submit\"  >Modificar</button> <br><br><br><br>");
                }
                out.println("</div>");
                ++cont;
            }
            out.println(date);
            out.println("</body>");
            out.println("</html>");
        }
        catch(Exception e) {
            System.err.println(e.getMessage());
        }
    }
    

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
