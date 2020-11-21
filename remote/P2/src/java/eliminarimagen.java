import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(urlPatterns = {"/eliminarimagen"})

public class eliminarimagen extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session.getAttribute("user") == null) response.sendRedirect("login.jsp");
        int id = Integer.parseInt(request.getParameter("product_no"));
        DAOImages.DeleteImage(id);
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println(" <link rel=\"stylesheet\" href=\"styles.css\">  ");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1 style = \"text-align:center\">La imagen ha sido eliminada</h1>"); 
            out.println("<div class=\"Cnav-b\"><ul class=\"Cnav-ul\"><li class=\"Cnav-ul-li\"><a class=\"Cnav-ul-li-a\" href =\"menu.jsp\"> Volver al Menú </a></li></ul></div>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
