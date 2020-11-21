package BaseDeDatos;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.DriverManager;
import static java.sql.DriverManager.println;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import modelo.Image;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Efren
 */
public class DAOImages {
    
public static String ValidFilename(String filename)
     {
          File f = new File("D:\\Users\\Samuel\\Documents\\Universidad\\AD\\adlab\\remote\\servidorSOAP\\web\\Images" + File.separator + filename);
          if(!f.exists()) { 
              return filename;
          }
          else return ValidFilename(filename+".copia.jpeg");
     }
public static List<Image> ListImage() throws ServletException, IOException {

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
        List<Image> returned = new ArrayList<>();
        
        int pos = 0;
        while (rs.next()) {
            Image tmp = new Image();
            tmp.setTitle(rs.getString("TITLE"));
            tmp.setId(rs.getInt("ID"));
            tmp.setAuth(rs.getString("AUTHOR"));
            tmp.setDescription(rs.getString("DESCRIPTION"));
            tmp.setFilename(rs.getString("FILENAME"));
            tmp.setKwords(rs.getString("KEYWORDS"));
            
            File myObj = new File("D:\\Users\\Samuel\\Documents\\Universidad\\AD\\adlab\\remote\\servidorSOAP\\web\\Images" + File.separator + rs.getString("FILENAME"));
            byte[] data = Files.readAllBytes(myObj.toPath());
            tmp.setRawData(data);
            returned.add(pos,tmp);
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
        
public static List<Image> SearchImage(int id ,String title,  String keywords, String author, String date,  boolean tipo )
throws ServletException, IOException {
    String query;
    //PreparedStatement statement;
    PreparedStatement statement;
    if(keywords == null) keywords = "";
    if(date == null) date = "";
    if(author == null) author = "";
    if(title == null) title = "";
    
    Connection connection = null;
        try {
            query = "SELECT * FROM IMAGE WHERE ";
            if (title != "") query += "TITLE LIKE ? ";
            if (!keywords.equals("")) {
                if ( title != ""){
                    if (tipo == false) query += "OR ";
                    else query += "AND ";
                }
                query += "KEYWORDS LIKE ? ";
            }
            if (!author.equals("")) {
                if ( title != "" || keywords != ""){
                    if (tipo == false) query += "OR ";
                    else query += "AND ";
                }
                query += "AUTHOR LIKE ? ";
            }
            if (!date.equals("")) {
                if (title != "" || keywords != "" || author != "") {
                    if (tipo == false) query += "OR ";
                    else query += "AND ";
                }
                query += "CREATION_DATE LIKE ? ";
            }
            if (id != -1) {
                if (title != "" || keywords != "" || author != "" || date != "") {
                    if (tipo == false) query += "OR ";
                    else query += "AND ";
                }
                query += "ID = " +"?"+ " ";
            }  
        //Class.forName("org.apache.derby.jdbc.ClientDriver");
       connection = DriverManager.getConnection("jdbc:derby://localhost:1527/MARTIJUNCOSA;user=martijuncosa;password=martijuncosa");
       
       System.out.println(query);
       statement = connection.prepareStatement(query);
       
        int cont = 1;
        if (!title.equals("")) {
            statement.setString(cont,"%"+title+"%");
            ++cont;
        }
        if (!keywords.equals("")) {
            statement.setString(cont,"%"+keywords+"%");
            ++cont;
        }
        if (!author.equals("")) {
            statement.setString(cont,"%"+author+"%");
            ++cont;
        }
         if (!date.equals("")){
            statement.setString(cont,"%"+date+"%");
            ++cont;
        }
        ResultSet rs =  statement.executeQuery();
        List<Image> returned = new ArrayList<>();
        
        int pos = 0;
        while (rs.next()) {
            System.out.println(rs.getInt("ID"));
            Image tmp = new Image();
            tmp.setTitle(rs.getString("TITLE"));
            tmp.setId(rs.getInt("ID"));
            tmp.setAuth(rs.getString("AUTHOR"));
            tmp.setDescription(rs.getString("DESCRIPTION"));
            tmp.setFilename(rs.getString("FILENAME"));
            tmp.setKwords(rs.getString("KEYWORDS"));
            
            File myObj = new File("D:\\Users\\Samuel\\Documents\\Universidad\\AD\\adlab\\remote\\servidorSOAP\\web\\Images" + File.separator + rs.getString("FILENAME"));
            byte[] data = Files.readAllBytes(myObj.toPath());
            tmp.setRawData(data);
            returned.add(pos,tmp);
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
   
   
   
public  static boolean ModifyImage(int id,String title, String description, String keywords) throws ServletException, IOException{
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
            System.err.println(query+" "+title+" "+id+" "+description+" "+keywords);
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
   
   
   
public static boolean DeleteImage(int id) throws ServletException, IOException {
    String query;
    PreparedStatement statement;
    Connection connection = null;
        try {
        //Class.forName("org.apache.derby.jdbc.ClientDriver");
        connection = DriverManager.getConnection("jdbc:derby://localhost:1527/MARTIJUNCOSA;user=martijuncosa;password=martijuncosa");
        System.out.println(id);
        query = "SELECT FILENAME FROM IMAGE WHERE ID = ?";
        statement = connection.prepareStatement(query);
        statement.setInt(1,id);
        ResultSet rs =  statement.executeQuery();
        if(rs.next()){
            query = "DELETE  FROM IMAGE WHERE ID = ?";
            System.out.println(query);
            
            statement = connection.prepareStatement(query);
            statement.setInt(1,id);
            int result = statement.executeUpdate();
            if (result == 1 ){
                System.out.println("Esborrat");
                File file = new File("D:\\Users\\Samuel\\Documents\\Universidad\\AD\\adlab\\remote\\servidorSOAP\\web\\Images", rs.getString("FILENAME"));
                if (!file.isDirectory()) file.delete();
                return true;
            }
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
   
   
     
public static boolean RegisterImage(String title, String description, String keywords, String author, String date, String filename, byte[] file) throws ServletException, IOException, SQLException {
    FileOutputStream out = null;
    InputStream filecontent = null;
    String query;
    PreparedStatement statement;
    Connection connection = null;
    System.out.println("Chivatooo");
    try {
        String ValidFilename = ValidFilename(filename);
        File f = new File("D:\\Users\\Samuel\\Documents\\Universidad\\AD\\adlab\\remote\\servidorSOAP\\web\\Images" + File.separator + ValidFilename);
        out = new FileOutputStream(f); 
        System.out.println("Chivatooo2");
        out.write(file);
        System.out.println("Chivatooo3");
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
            statement.setString(7,ValidFilename);
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
    }
    return true;
}

}
