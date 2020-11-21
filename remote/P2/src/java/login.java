import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(urlPatterns = {"/login"})
public class login extends HttpServlet {

  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        String user = request.getParameter("usuario");
        String Pwd = request.getParameter("password");
        //PrintWriter out = response.getWriter();
        boolean verify = DAOUser.verify(user,Pwd);
        if (verify) {
            DAOUser.session(user,request);
            response.sendRedirect("menu.jsp");
        }
        else {
            response.sendRedirect("error?error=invalid_user");
        }  
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
