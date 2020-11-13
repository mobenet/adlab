/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restad;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author elchu
 */
@Path("generic")
public class GenericResource {

    private final HashSet<String> set = new HashSet();

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GenericResource
     */
    public GenericResource() {
    }

    /**
     * Retrieves representation of an instance of restad.GenericResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String getHtml() {
        //TODO return proper representation object

        //throw new UnsupportedOperationException();
        return "<html><head/><body><h1>Hello World!</h1></body></html>";

    }

    /**
     * POST method to register a new image
     *
     * @param title
     * @param description
     * @param keywords
     * @param author
     * @param crea_date
     * @param filename
     * @return
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    @Path("register")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_HTML)
    public String registerImage(@FormParam("title") String title,
            @FormParam("description") String description,
            @FormParam("keywords") String keywords,
            @FormParam("author") String author,
            @FormParam("creation") String crea_date,
            @FormParam("filename") String filename) throws ClassNotFoundException, SQLException {
        String storage_date = LocalDate.now().toString();
        OurDao.startDB();
        OurDao.enregistrar(title, description, keywords, author, crea_date, storage_date, filename);
        OurDao.stopDB();
        return "<html><h1>HelloBitch</h1></html>";
    }

    /**
     *
     * @param uploadedInputStream
     * @param filename
     * @return
     */
    /*  @POST
    @Path("/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public String uploadFile(@FormDataParam("file") InputStream uploadedInputStream,
                @FormDataParam("filename") String filename){
        return ""; 
    }*/
    /**
     * POST method to modify an existing image
     *
     * @param id
     * @param title
     * @param description
     * @param keywords
     * @param author
     * @param crea_date
     * @return
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    @Path("modify")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_HTML)
    public String modifyImage(@FormParam("id") String id,
            @FormParam("title") String title,
            @FormParam("description") String description,
            @FormParam("keywords") String keywords,
            @FormParam("author") String author,
            @FormParam("creation") String crea_date) throws ClassNotFoundException, SQLException {

        OurDao.startDB();
        OurDao.enregistrarCanvi(title, description, keywords, author, crea_date, id);
        OurDao.stopDB();
        return "";
    }

    /**
     * POST method to delete an existing image
     *
     * @param id
     * @return
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    @Path("delete")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_HTML)
    public String deleteImage(@FormParam("id") String id) throws ClassNotFoundException, SQLException {
        OurDao.startDB();
        OurDao.eliminar(id);
        OurDao.stopDB();
        return "";
    }

    /**
     * GET method to list images
     *
     * @return
     */
    @Path("list")
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String listImages() {
        String res = "";
        try {
            OurDao.startDB();
            List<Image> list = OurDao.getAllImages();
            OurDao.stopDB();
            res += "<table border=1>"
                    + "<tr>"
                    + "<th>Id</th>"
                    + "<th>Titulo</th>"
                    + "<th>Descripcion</th>"
                    + "<th>Palabras Clave</th>"
                    + "<th>Autor</th>"
                    + "<th>Fecha de creacion</th>"
                    + "<th>Fecha de subida</th>"
                    + "<th>Nombre del archivo</th>"
                    + "</tr>";

            for (Image img : list) {
                res += "<tr class=imageData><td>" + img.getId() + "</td>"
                        + "<td>" + img.getTitle() + "</td>"
                        + "<td>" + img.getDescription() + "</td>"
                        + "<td>" + img.getKeywords() + "</td>"
                        + "<td>" + img.getAuthor() + "</td>"
                        + "<td>" + img.getCreationDate() + "</td>"
                        + "<td>" + img.getStorageDate() + "</td>"
                        + "<td>" + img.getFileName() + "</td>";
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println(e.getMessage());
        }
        return res;
    }

    /**
     * GET method to search images by id
     *
     * @param id
     * @return
     */
    @Path("searchID/{id}")
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String searchByID(@PathParam("id") int id) {
        Image tmp = new Image();//cambiar
        try {
            //TODO write your implementation code here:
            HashMap<String, String> map = new HashMap<>();
            map.put("id", String.valueOf(id));
            OurDao.startDB();
            ResultSet res;
            res = OurDao.consultar(map);
            if (res == null) {
                return "Busqueda vacia";//prova
            }
            if (res.next()) {
                tmp.setId(res.getInt("ID"));
                tmp.setTitle(res.getString("TITLE"));
                tmp.setAuthor(res.getString("AUTHOR"));
                tmp.setDescription(res.getString("DESCRIPTION"));
                tmp.setKeywords(res.getString("KEYWORDS"));
                tmp.setCreationDate(res.getString("CREATION_DATE"));
                tmp.setStorageDate(res.getString("STORAGE_DATE"));
                tmp.setFileName(res.getString("FILENAME"));
            }
            OurDao.stopDB();

            //Leer imagen del sistema de ficheros
            String basepath = GenericResource.class
                    .getProtectionDomain()
                    .getCodeSource()
                    .getLocation()
                    .getPath();
            String projectName = "RestAD";
            basepath = basepath.substring(0, basepath.lastIndexOf(projectName));
            final String path = basepath + projectName + "/web/images/";
            File imageFile = new File(path + tmp.getImageName());
            FileInputStream fis = new FileInputStream(imageFile);
            byte[] imageBytes;
            try (BufferedInputStream inStream = new BufferedInputStream(fis)) {
                imageBytes = new byte[(int) imageFile.length()];
                inStream.read(imageBytes);
            }
            tmp.setBytes(imageBytes);

        } catch (ClassNotFoundException | IOException | SQLException ex) {
            System.err.println(ex.getMessage());
            return null;
        }
        return ImageToString(tmp);
    }

    /**
     * GET method to search images by title
     *
     * @param title1
     * @param author1
     * @param keywords1
     * @param cDate1
     * @return
     */
    @Path("searchcombi")
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String searchCombi(@QueryParam("title") String title1,
            @QueryParam("author") String author1,
            @QueryParam("keywords") String keywords1,
            @QueryParam("cdate") String cDate1) {
        //HashSet<String> set =new HashSet(); 
        String resultat = "<table border=1>\n"
                + "            <tr>\n"
                + "                <th>Titulo</th>\n"
                + "                <th>Descripcion</th>\n"
                + "                <th>Palabras Clave</th>\n"
                + "                <th>Autor</th>\n"
                + "                <th>Fecha de creacion</th>\n"
                + "                <th>Fecha de subida</th>\n"
                + "                <th>Nombre del archivo</th>\n"
                + "            </tr>";

        if ((!"".equals(title1)) & (!" ".equals(title1))) {
            String g = searchByTitle(title1);
        }
        if ((!"".equals(author1)) & (!" ".equals(author1))) {
            String g = searchByAuthor(author1);
        }
        if ((!"".equals(keywords1)) & (!" ".equals(keywords1))) {
            String g = searchByKeywords(keywords1);
        }
        if ((!"".equals(cDate1)) & (!" ".equals(cDate1))) {
            String g = searchByCreationDate(cDate1);
        }
        Iterator<String> i = set.iterator();
        while (i.hasNext()) {
            resultat += (i.next());
        }

        resultat += "</table>";
        set.clear();
        return resultat;
    }

    /**
     * GET method to search images by title
     *
     * @param title
     * @return
     */
    @Path("searchTitle/{title}")
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String searchByTitle(@PathParam("title") String title) {
        String resultat = "";
        try {
            HashMap<String, String> map = new HashMap<>();
            map.put("title", "%" + title + "%");
            OurDao.startDB();
            ResultSet rs;
            rs = OurDao.consultar(map);
            while (rs.next()) {
                set.add("<tr><td>" + rs.getString("TITLE") + "</td>"
                        + "<td>" + rs.getString("DESCRIPTION") + "</td>"
                        + "<td>" + rs.getString("KEYWORDS") + "</td>"
                        + "<td>" + rs.getString("AUTHOR") + "</td>"
                        + "<td>" + rs.getString("CREATION_DATE") + "</td>"
                        + "<td>" + rs.getString("STORAGE_DATE") + "</td>"
                        + "<td>" + rs.getString("FILENAME") + "</td></tr>");
            }
            OurDao.stopDB();

        } catch (ClassNotFoundException | SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return resultat;
    }

    /**
     * GET method to search images by creation date
     *
     * @param creaDate
     * @return
     */
    @Path("searchCreationDate/{date}")
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String searchByCreationDate(@PathParam("date") String creaDate) {
        String resultat = "";
        try {
            HashMap<String, String> map = new HashMap<>();
            map.put("cdate", "%" + creaDate + "%");
            OurDao.startDB();
            ResultSet rs;
            rs = OurDao.consultar(map);
            while (rs.next()) {
                set.add("<tr><td>" + rs.getString("TITLE") + "</td>"
                        + "<td>" + rs.getString("DESCRIPTION") + "</td>"
                        + "<td>" + rs.getString("KEYWORDS") + "</td>"
                        + "<td>" + rs.getString("AUTHOR") + "</td>"
                        + "<td>" + rs.getString("CREATION_DATE") + "</td>"
                        + "<td>" + rs.getString("STORAGE_DATE") + "</td>"
                        + "<td>" + rs.getString("FILENAME") + "</td></tr>");
            }
            OurDao.stopDB();

        } catch (ClassNotFoundException | SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return resultat;
    }

    /**
     * GET method to search images by author
     *
     * @param author
     * @return
     */
    @Path("searchAuthor/{author}")
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String searchByAuthor(@PathParam("author") String author) {
        String resultat = "";
        try {
            HashMap<String, String> map = new HashMap<>();
            map.put("author", "%" + author + "%");
            OurDao.startDB();
            ResultSet rs;
            rs = OurDao.consultar(map);
            while (rs.next()) {
                set.add("<tr><td>" + rs.getString("TITLE") + "</td>"
                        + "<td>" + rs.getString("DESCRIPTION") + "</td>"
                        + "<td>" + rs.getString("KEYWORDS") + "</td>"
                        + "<td>" + rs.getString("AUTHOR") + "</td>"
                        + "<td>" + rs.getString("CREATION_DATE") + "</td>"
                        + "<td>" + rs.getString("STORAGE_DATE") + "</td>"
                        + "<td>" + rs.getString("FILENAME") + "</td></tr>");

            }
            OurDao.stopDB();

        } catch (ClassNotFoundException | SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return resultat;
    }

    /**
     * GET method to search images by keyword
     *
     * @param keywords
     * @return
     */
    @Path("searchKeywords/{keywords}")
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String searchByKeywords(@PathParam("keywords") String keywords) {
        String resultat = "";
        try {
            HashMap<String, String> map = new HashMap<>();
            map.put("keywords", "%" + keywords + "%");
            OurDao.startDB();
            ResultSet rs;
            rs = OurDao.consultar(map);
            while (rs.next()) {
                set.add("<tr><td>" + rs.getString("TITLE") + "</td>"
                        + "<td>" + rs.getString("DESCRIPTION") + "</td>"
                        + "<td>" + rs.getString("KEYWORDS") + "</td>"
                        + "<td>" + rs.getString("AUTHOR") + "</td>"
                        + "<td>" + rs.getString("CREATION_DATE") + "</td>"
                        + "<td>" + rs.getString("STORAGE_DATE") + "</td>"
                        + "<td>" + rs.getString("FILENAME") + "</td></tr>");
            }
            OurDao.stopDB();

        } catch (ClassNotFoundException | SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return resultat;

    }

    /**
     * POST method to login an existing user
     *
     * @param user
     * @param password
     * @return
     */
    @Path("loginUser")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public String login(@FormParam("user") String user, @FormParam("password") String password) {
        boolean logged = false;
        try {
            OurDao.startDB();
            if (!OurDao.validatePassword(password) || !OurDao.validateUsername(user)) {
                throw new IllegalArgumentException("Contraseña o usuario con formato invalido");
            }
            logged = OurDao.loggin(user, password);
            OurDao.stopDB();
        } catch (ClassNotFoundException | IllegalArgumentException | SQLException e) {
            String msg = e.getMessage();
            System.err.println(msg);
            return "{\"success\": false, \"message\": \"" + msg + "\"}";
        }
        if (!logged) {
            return "{\"success\": false, \"message\": \"El nombre o el usuario no son correctos\"}";
        } else {
            return "{\"success\": true, \"message\": \"Se ha iniciado sesión con exito\"}";
        }
    }

    /**
     * POST method to register a new user
     *
     * @param user
     * @param password
     * @return
     */
    @Path("registerUser")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public String register(@FormParam("user") String user, @FormParam("password") String password) {
        try {
            OurDao.startDB();
            if (!OurDao.validatePassword(password) || !OurDao.validateUsername(user)) {
                throw new IllegalArgumentException("Contraseña o usuario con formato invalido");
            }
            OurDao.newuser(user, password);
            OurDao.stopDB();
        } catch (IllegalArgumentException e) {
            String msg = e.getMessage();
            System.err.println(msg);
            return "{\"success\": false, \"message\": \"" + msg + "\"}";
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return "{\"success\": false, \"message\": \"Este usuario ya está registrado\"}";
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
            return "{\"success\": false, \"message\": \"Error interno\"}";
        }

        return "{\"success\": true, \"message\": \"Se ha registrado el usuario con exito\"}";
    }

    /**
     * GET method to retrieve a list of all the users
     *
     * @return
     */
    @Path("getUsers")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getUsers() {
        String res = "[";
        try {
            OurDao.startDB();
            for (String user : OurDao.getUsers()) {
                res += "\"" + user + "\",";
            }
            OurDao.stopDB();
            if (!"[".equals(res)) {
                res = res.substring(0, res.length() - 1);
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getMessage());
        }
        res += "]";
        return res;
    }

    private static String ImageToString(Image img) {
        return "Image{"
                + "id=" + img.getId()
                + ", title=" + img.getTitle()
                + ", author=" + img.getAuthor()
                + ", description=" + img.getDescription()
                + ", keywords=" + img.getKeywords()
                + ", creationDate=" + img.getCreationDate()
                + ", storageDate=" + img.getStorageDate()
                + ", fileName=" + img.getFileName() + '}';
    }

    /**
     * PUT method for updating or creating an instance of GenericResource
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.TEXT_HTML)
    public void putHtml(String content) {
    }
}
