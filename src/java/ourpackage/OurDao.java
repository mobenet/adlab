/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ourpackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author mo
 */
   
public class OurDao {
    
    static Connection connection = null; 
    
    
    public static void startDB () throws ClassNotFoundException, SQLException{
        Class.forName("org.apache.derby.jdbc.ClientDriver");
        connection = DriverManager.getConnection("jdbc:derby://localhost:1527/pr2;user=pr2;password=pr2");
       }
        
    
    public static void stopDB () throws SQLException{
        if(connection != null) {
            connection.close();
        }
    }
    
    public static void enregistrar(int idI, String titulo, String desc, String clave, 
            String author, String fechaC, String fechaS, String fileName) throws SQLException{
        
        PreparedStatement statement; 
        String query;
        
        query = "insert into IMAGE  values(?, ?, ?, ?, ?, ?, ?, ?)";
        statement = connection.prepareStatement(query);
        statement.setInt(1, idI+1);
        statement.setString(2, titulo);
        statement.setString(3, desc);
        statement.setString(4, clave);
        statement.setString(5, author);
        statement.setString(6,fechaC);
        statement.setString(7, fechaS);
        statement.setString(8, fileName);
        statement.executeUpdate();
    }
    
    public static int esborrar(){
        return 0;
    }
    
    public static void consultar(){
        
    }
    
    
    //get last id in the DB
    public static int getIdi() throws SQLException{
        int id=0; 
        PreparedStatement statement; 
        ResultSet rs;
        
        String query = "SELECT * from image";
        statement = connection.prepareStatement(query);
        rs = statement.executeQuery();
        //rs.last();
        //int newId = rs.getInt("id") + 1; // Ids comenzando por 1?
        while (rs.next()){
            id = rs.getInt("ID"); 
            System.out.println(id);
        }

        return id;
    }
    
    public static ResultSet getAllImages() throws SQLException {
        
        if(connection == null) return null; //No se ha iniciado la conexión
        String query = "select * from image";
        ResultSet res = connection.prepareStatement(query).executeQuery();
        return res;
    }
}
