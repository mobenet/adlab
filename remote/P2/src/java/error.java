import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/error"})
public class error extends HttpServlet {

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {       

            String error_type = request.getParameter("error");
            System.out.println(error_type);
            try (PrintWriter out = response.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println(" <link rel=\"stylesheet\" href=\"styles.css\">  ");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1 style = \"text-align:center\">Ha habido un error!</h1>");
                
                if(error_type.equals("invalid_user")) out.println("<h3 style=\"text-align: center\">El usuario es inválido");
                if(error_type.equals("invalid-format")) out.println("<h3 style=\"text-align: center\">El formato de imágen es inválido");
                
                if(error_type.equals("invalid_user"))out.println("<div style=\"padding-bottom:3%; padding-top:2%;\" class=\"Cnav-b\"><ul class=\"Cnav-ul\"><li class=\"Cnav-ul-li\"><a class=\"Cnav-ul-li-a\" href =\"login.jsp\"> Volver a la página de Login </a></li></ul></div>");
                if(error_type.equals("invalid-format"))out.println("<div style=\"padding-bottom:3%; padding-top:2%;\" class=\"Cnav-b\"><ul class=\"Cnav-ul\"><li class=\"Cnav-ul-li\"><a class=\"Cnav-ul-li-a\" href =\"menu.jsp\"> Volver al Menú </a></li></ul></div>");
               
                out.println("</body>");
                out.println("</html>");            
            }catch(Exception e) {
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
