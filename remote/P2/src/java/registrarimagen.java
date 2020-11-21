import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import static java.sql.DriverManager.println;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import static jdk.nashorn.internal.objects.NativeError.getFileName;


@WebServlet(urlPatterns = {"/registrarimagen"})
@MultipartConfig
public class registrarimagen extends HttpServlet {
private final static Logger LOGGER = 
            Logger.getLogger(registrarimagen.class.getCanonicalName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      
    }

    
    private String getFileName(final Part part) {
        final String partHeader = part.getHeader("content-disposition");
        LOGGER.log(Level.INFO, "Part Header = {0}", partHeader);
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
    return null;
    }
    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        //HTTP session
        HttpSession session = request.getSession(false);
        if (session.getAttribute("user") == null) response.sendRedirect("login.jsp");
        else {
            String title = request.getParameter("title");
            String description = request.getParameter("description");
            String keywords = request.getParameter("keywords");
            String author = request.getParameter("author");
            String creationdate = request.getParameter("date");

            final Part filePart = request.getPart("image");
            PrintWriter out = response.getWriter();
            String type = filePart.getContentType();
            if (!type.equals("image/jpeg")){
               response.sendRedirect("error?error=invalid-format");
               return;
            }
            final String fileName = getFileName(filePart);
            boolean a;
            a = DAOImages.RegisterImage(title,description,keywords,author,creationdate,fileName,filePart);
            if (a) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println(" <link rel=\"stylesheet\" href=\"styles.css\">  ");
                out.println("<h1 style = \"text-align:center\">La imagen se ha registrado correctamente</h1>");   
                out.println("</head>");
                out.println("<nav class=\"Cnav-a\"> <ul class=\"Cnav-ul\"> <li class=\"Cnav-ul-li\">");
                out.println("<a class=\"Cnav-ul-li-a\" href =\"menu.jsp\"> Volver al menú </a> </li>");
                out.println("<li class=\"Cnav-ul-li\"> <a class=\"Cnav-ul-li-a\" href = registrarimagen.jsp> Registrar otra Imagen </a> </li>");
                out.println(" </ul> </nav> </body> </html>");
            }
            
            else response.sendRedirect("error.jsp?error=non_accessible");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
