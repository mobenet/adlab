/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restad;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author mo
 */
public class OurDao {

    static Connection connection = null;

    public static void startDB() throws ClassNotFoundException, SQLException {
        Class.forName("org.apache.derby.jdbc.ClientDriver");
        connection = DriverManager.getConnection("jdbc:derby://localhost:1527/pr2;user=pr2;password=pr2");
    }

    public static void stopDB() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    public static boolean loggin(String usuari, String psw) throws SQLException {
        String query = "select * from usuarios WHERE id_usuario = ? AND password = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, usuari);
        statement.setString(2, psw);
        ResultSet rs = statement.executeQuery();
        return rs.next();
    }

    public static void newuser(String usuario, String passw) throws SQLException {

        PreparedStatement statement;
        String query = "insert into usuarios values(?,?)";
        statement = connection.prepareStatement(query);
        statement.setString(1, usuario);
        statement.setString(2, passw);
        statement.executeUpdate();

    }

    public static int enregistrar(String titulo, String desc, String clave,
            String author, String fechaC, String fechaS, String fileName) throws SQLException {

        String query = "SELECT id from image";
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet rs = statement.executeQuery();

        int idI = 0;
        while (rs.next()) {
            idI = rs.getInt("ID");
        }

        idI++;
        query = "insert into IMAGE  values(?, ?, ?, ?, ?, ?, ?, ?)";
        statement = connection.prepareStatement(query);
        statement.setInt(1, idI);
        statement.setString(2, titulo);
        statement.setString(3, desc);
        statement.setString(4, clave);
        statement.setString(5, author);
        statement.setString(6, fechaC);
        statement.setString(7, fechaS);
        statement.setString(8, fileName);
        statement.executeUpdate();
        return idI;
    }

    public static void eliminar(int id) throws SQLException {

        PreparedStatement statement;
        String query;
        query = "delete from image where ID = ?";
        statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        statement.executeUpdate();
    }

    public static boolean enregistrarCanvi(String id, String title, String description,
            String keywords, String author, String creationDate, String filename) throws SQLException {
        String query;
        PreparedStatement st;
        try {
            query = "UPDATE IMAGE SET ";
            ArrayList<String> cols = new ArrayList();
            if (title != null) {
                query += " TITLE = ?,";
                cols.add(title);
            }
            if (description != null) {
                query += " DESCRIPTION = ?,";
                cols.add(description);
            }
            if (keywords != null) {
                query += " KEYWORDS = ?,";
                cols.add(keywords);
            }
            if (author != null) {
                query += " AUTHOR = ?,";
                cols.add(author);
            }
            if (creationDate != null) {
                query += " CREATION_DATE = ?,";
                cols.add(creationDate);
            }
            if (filename != null) {
                query += " FILENAME = ?,";
                cols.add(filename);
            }
            if (cols.isEmpty()) {
                return false;
            }
            query = query.substring(0, query.length() - 1) + " WHERE ID = ?";
            st = connection.prepareStatement(query);
            for (int i = 0; i < cols.size(); i++) {
                st.setString(i + 1, cols.get(i));
            }
            st.setInt(cols.size() + 1, Integer.parseInt(id));
            st.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
        return true;
    }

    public static ResultSet consultar(HashMap<String, String> consultas) throws SQLException {

        String query = "SELECT * FROM IMAGE WHERE ";
        for (String c : consultas.keySet()) {
            switch (c) {
                case "id":
                    query += "ID = ?";
                    PreparedStatement statement = connection.prepareStatement(query);
                    statement.setInt(1, Integer.parseInt(consultas.get("id")));
                    return statement.executeQuery();
                case "title":
                    query += "TITLE LIKE ? OR ";
                    break;
                case "descrpition":
                    query += "DESCRIPTION LIKE ? OR ";
                    break;
                case "keywords":
                    query += "KEYWORDS LIKE ? OR ";
                    break;
                case "author":
                    query += "AUTHOR LIKE ? OR ";
                    break;
                case "cdate":
                    query += "CREATION_DATE LIKE ? OR ";
                    break;
                case "filename":
                    query += "FILENAME LIKE ? OR ";
                    break;
                default:
                    return null;
            }
            query = query.substring(0, query.length() - 3);
        }
        PreparedStatement statement = connection.prepareStatement(query);
        int i = 1;
        for (String v : consultas.values()) {
            statement.setString(i, v);
            i++;
        }
        return statement.executeQuery();
    }

    public static List<Image> getAllImages() throws SQLException {

        if (connection == null) {
            return null; //No se ha iniciado la conexión
        }
        String query = "select * from image";
        ResultSet res = connection.prepareStatement(query).executeQuery();
        ArrayList<Image> list = new ArrayList<>();
        while (res.next()) {
            list.add(new Image(
                    res.getInt("ID"),
                    res.getString("TITLE"),
                    res.getString("AUTHOR"),
                    res.getString("DESCRIPTION"),
                    res.getString("KEYWORDS"),
                    res.getString("CREATION_DATE"),
                    res.getString("STORAGE_DATE"),
                    res.getString("FILENAME")
            ));
        }
        return list;
    }

    public static ResultSet getImage(int id) throws SQLException {

        if (connection == null) {
            return null; //No se ha iniciado la conexión
        }
        PreparedStatement st;
        String query = "select * from image where id =?";
        st = connection.prepareStatement(query);
        st.setInt(1, id);
        ResultSet res = st.executeQuery();
        return res;
    }

    public static List<String> getUsers() throws SQLException {
        if (connection == null) {
            return null;
        }
        ResultSet rs = connection
                .prepareStatement("select id_usuario from usuarios")
                .executeQuery();
        ArrayList<String> list = new ArrayList<>();
        while (rs.next()) {
            list.add(rs.getString("id_usuario"));
        }
        return list;
    }

    protected static boolean validateUsername(String username) {

        return username != null
                && !username.isEmpty()
                && !username.contains(" ");
    }

    protected static boolean validatePassword(String password) {

        return password != null
                && !password.isEmpty()
                && !password.contains(" ")
                && password.length() > 5;
    }
}
