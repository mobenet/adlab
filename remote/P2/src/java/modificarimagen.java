import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(urlPatterns = {"/modificarimagen"})
public class modificarimagen extends HttpServlet {
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        if (session.getAttribute("user") == null) response.sendRedirect("login.jsp");
        
        PrintWriter out = response.getWriter();
        int id = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");
        System.out.println(title);
        String description = request.getParameter("description");
        String keywords = request.getParameter("keywords");
        boolean a = DAOImages.ModifyImage(id, title, description, keywords);
        if(a){
            if (a) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println(" <link rel=\"stylesheet\" href=\"styles.css\">  ");
                out.println("<h1 style = \"text-align:center\">La imagen se ha modificado correctamente</h1>");   
                out.println("</head>");
                out.println("<nav class=\"Cnav-a\"> <ul class=\"Cnav-ul\"> <li class=\"Cnav-ul-li\">");
                out.println("<a class=\"Cnav-ul-li-a\" href =\"menu.jsp\"> Volver al menú </a> </li>");
                out.println(" </ul> </nav> </body> </html>");
            }
            
            else response.sendRedirect("error.jsp?error=not_modified_correct");            
        }
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
