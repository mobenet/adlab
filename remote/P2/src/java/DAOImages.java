import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import static java.sql.DriverManager.println;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;


@WebServlet(urlPatterns = {"/DAOImages"})
public class DAOImages extends HttpServlet {

    
static Vector<Vector<String>> ListImage() throws ServletException, IOException {

    OutputStream out = null;
    InputStream filecontent = null;
    String query;
    PreparedStatement statement;
    Connection connection = null;
    try {
        connection = DriverManager.getConnection("jdbc:derby://localhost:1527/MARTIJUNCOSA;user=martijuncosa;password=martijuncosa");
        query = "SELECT * FROM IMAGE";
        statement = connection.prepareStatement(query);
        System.out.println(query);
        ResultSet rs =  statement.executeQuery();
        Vector<Vector<String>> returned = new Vector<Vector<String>>();
        int pos = 0;
        while (rs.next()) {
            Vector<String> tmp = new Vector<String>();
            tmp.insertElementAt(Integer.toString(rs.getInt("ID")),0);
            tmp.insertElementAt(rs.getString("TITLE"),1);
            tmp.insertElementAt(rs.getString("AUTHOR"),2);
            tmp.insertElementAt(rs.getString("DESCRIPTION"), 3);
            tmp.insertElementAt(rs.getString("FILENAME"),4);
            tmp.insertElementAt(rs.getString("KEYWORDS"),5);
            returned.insertElementAt(tmp, pos);
            ++pos;
        }
        return returned; 
    } catch(Exception e) {
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
    return null;
}
        

   
static Vector<Vector<String>> SearchImage(String title,  String keywords, String author, String date,  boolean tipo )
throws ServletException, IOException {
    String query;
    //PreparedStatement statement;
    Statement statement;
    Connection connection = null;
        try {
            query = "SELECT * FROM IMAGE WHERE ";
            if (title != "") query += "TITLE LIKE '%" +title+ "%' ";
            if (keywords != "") {
                if ( title != ""){
                    if (tipo == false) query += "OR ";
                    else query += "AND ";
                }
                query += "KEYWORDS LIKE '%" +keywords+ "%' ";
            }
            if (author != "") {
                if ( title != "" || keywords != ""){
                    if (tipo == false) query += "OR ";
                    else query += "AND ";
                }
                query += "AUTHOR LIKE '%" +author+ "%' ";
            }
            if (date != "") {
                if (title != "" || keywords != "" || author != "") {
                    if (tipo == false) query += "OR ";
                    else query += "AND ";
                }
                query += "CREATION_DATE LIKE '%" +date+ "%' ";
            }  
        //Class.forName("org.apache.derby.jdbc.ClientDriver");
        connection = DriverManager.getConnection("jdbc:derby://localhost:1527/MARTIJUNCOSA;user=martijuncosa;password=martijuncosa");
        statement = connection.createStatement();
        
        
        /* USADO CON EL PREPARED STATEMENT, LO HEMOS SACADO PARA USAR LIKE EN LA QUERY
        int cont = 1;
        if (title != "") {
            statement.setString(cont,title);
            ++cont;
        }
        if (keywords != "") {
            statement.setString(cont,keywords);
            ++cont;
        }
        if (author != "") {
            statement.setString(cont,author);
            ++cont;
        }
         if (date != ""){
            statement.setString(cont,date);
            ++cont;
        }
        ^*/
        System.out.println(query);
        ResultSet rs =  statement.executeQuery(query);
        Vector<Vector<String>> returned = new Vector<Vector<String>>();
        int pos = 0;
        while (rs.next()) {
            Vector<String> tmp = new Vector<String>();
            tmp.insertElementAt(Integer.toString(rs.getInt("ID")),0);
            tmp.insertElementAt(rs.getString("TITLE"),1);
            tmp.insertElementAt(rs.getString("AUTHOR"),2);
            tmp.insertElementAt(rs.getString("DESCRIPTION"), 3);
            tmp.insertElementAt(rs.getString("FILENAME"),4);
            tmp.insertElementAt(rs.getString("KEYWORDS"),5);

            returned.insertElementAt(tmp, pos);
            ++pos;
        }
        return returned;
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
    return null;
}
   
   
   
     static boolean ModifyImage(int id,String title, String description, String keywords) throws ServletException, IOException{
    String query;
    PreparedStatement statement;
    Connection connection = null;
        try {
            //Class.forName("org.apache.derby.jdbc.ClientDriver");
            connection = DriverManager.getConnection("jdbc:derby://localhost:1527/MARTIJUNCOSA;user=martijuncosa;password=martijuncosa");
            query = "UPDATE IMAGE SET ";
            if (title != "") query += "TITLE = ? ";
            if (keywords != ""){
                if (title!= "") query += ",";
                    query += " KEYWORDS=?";
            }
            if (description != ""){
                if (title != "" && keywords != ""  || keywords != "") query += ",";
                    query += " DESCRIPTION=?";
            }
            query += " WHERE ID=?";
            statement = connection.prepareStatement(query);
            int cont = 1;
            if (title != "") {
                statement.setString(cont,title);
                ++cont;
            }

            if (keywords != "") {
                statement.setString(cont,keywords);
                ++cont;
            }
            if (description != "") {
                statement.setString(cont,description);
                ++cont;
            }
            statement.setInt(cont, id);
            System.out.println(query);
            int result = statement.executeUpdate();
            if (result == 1 ) return true;
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
    return true;
}    
   
   
   
     static boolean DeleteImage(int id) throws ServletException, IOException {
    String query;
    PreparedStatement statement;
    Connection connection = null;
        try {
        //Class.forName("org.apache.derby.jdbc.ClientDriver");
        connection = DriverManager.getConnection("jdbc:derby://localhost:1527/MARTIJUNCOSA;user=martijuncosa;password=martijuncosa");
        query = "SELECT FILENAME FROM IMAGE WHERE ID = ?";
        statement = connection.prepareStatement(query);
        statement.setInt(1,id);
        ResultSet rs =  statement.executeQuery();
        if(rs.next()){
            File file = new File("D:\\Users\\Samuel\\Documents\\Universidad\\AD\\adlab\\remote\\P2\\web\\Images", rs.getString("FILENAME"));
            if (!file.isDirectory()) file.delete();
            query = "DELETE  FROM IMAGE WHERE ID = ?";
            System.out.println(query);
            statement = connection.prepareStatement(query);
            statement.setInt(1,id);
            int result = statement.executeUpdate();
            if (result == 1 ) return true;
                return false;
        }
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
    return true;
}
   
   
static boolean RegisterImage(String title, String description, String keywords, String author, String date, String filename, Part file) throws ServletException, IOException {
    OutputStream out = null;
    InputStream filecontent = null;
    String query;
    PreparedStatement statement;
    Connection connection = null;
    try {
        out = new FileOutputStream(new File("D:\\Users\\Samuel\\Documents\\Universidad\\AD\\adlab\\remote\\P2\\web\\Images" + File.separator + filename));
        filecontent = file.getInputStream();
        int read = 0;
        final byte[] bytes = new byte[1024];
        while ((read = filecontent.read(bytes)) != -1) {
            out.write(bytes, 0, read);
        }
        try {
            //Class.forName("org.apache.derby.jdbc.ClientDriver");
            connection = DriverManager.getConnection("jdbc:derby://localhost:1527/MARTIJUNCOSA;user=martijuncosa;password=martijuncosa");
            query = "INSERT INTO IMAGE (ID,TITLE,DESCRIPTION,KEYWORDS,AUTHOR,CREATION_DATE,STORAGE_DATE,FILENAME) VALUES (default,?,?,?,?,?,?,?)";
            System.out.println(query);
            statement = connection.prepareStatement(query);
            statement.setString(1,title);
            statement.setString(2,description);
            statement.setString(3,keywords);
            statement.setString(4,author);
            statement.setString(5,date);
            statement.setString(7,filename);
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            LocalDateTime now = LocalDateTime.now();
            statement.setString(6,dtf.format(now));
            int result = statement.executeUpdate();
            if (result == 1 ) return true;
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
    } catch (FileNotFoundException fne) {
        println("You either did not specify a file to upload or are "
                + "trying to upload a file to a protected or nonexistent "
                + "location.");
        println("<br/> ERROR: " + fne.getMessage());
    } finally {
        if (out != null) {
            out.close();
        }
        if (filecontent != null) {
            filecontent.close();
        }
    }println("funciona");
    return true;
}
}