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


@WebServlet(urlPatterns = {"/DAOUser"})
public class DAOUser extends HttpServlet {

static boolean verify(String user, String password) 
        throws ServletException, IOException {
        String query;
        PreparedStatement statement;
        Connection connection = null;
        try {
            //Class.forName("org.apache.derby.jdbc.ClientDriver");
            connection = DriverManager.getConnection("jdbc:derby://localhost:1527/MARTIJUNCOSA;user=martijuncosa;password=martijuncosa");
            query = "select * from USUARIOS where ID_USUARIO = ? and PASSWORD = ?";
            System.out.println(query);
            statement = connection.prepareStatement(query);
            statement.setString(1,user);
            statement.setString(2,password);
            

            ResultSet rs = statement.executeQuery();
            if (rs.next()) return true;
            return false;
        }
        catch(Exception e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch(SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return false;
    }

    static void session(String user, HttpServletRequest request)throws ServletException, IOException {
        HttpSession sessionsa = request.getSession(false);
         
        /* Creating a new session*/
        sessionsa.setAttribute("user",user);
        try {
        }
        catch(Exception e) {
            System.err.println(e.getMessage());
        } 
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
