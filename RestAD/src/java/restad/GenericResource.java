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
import java.util.ArrayList;
import java.util.HashMap;
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
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author elchu
 */
@Path("generic")
public class GenericResource {

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
     * @return
     */
    @Path("register")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_HTML)
    public String registerImage(@FormParam("title") String title,
            @FormParam("description") String description,
            @FormParam("keywords") String keywords,
            @FormParam("author") String author,
            @FormParam("creation") String crea_date) {
        return "";
    }

    /**
     * POST method to modify an existing image
     *
     * @param title
     * @param description
     * @param keywords
     * @param author
     * @param crea_date
     * @return
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
            @FormParam("creation") String crea_date) {
        return "";
    }

    /**
     * POST method to delete an existing image
     *
     * @param title
     * @param description
     * @param keywords
     * @param author
     * @param crea_date
     * @return
     */
    @Path("delete")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_HTML)
    public String deleteImage(@FormParam("id") String id) {
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
        return "";
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
     * @param title
     * @return
     */
    @Path("searchTitle/{title}")
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String searchByTitle(@PathParam("title") String title) {
        ArrayList<Image> lista = new ArrayList<>();
        try {
            HashMap<String, String> map = new HashMap<>();
            map.put("title", "%" + title + "%");
            OurDao.startDB();
            ResultSet res;
            res = OurDao.consultar(map);
            while (res.next()) {
                Image tmp = new Image();
                tmp.setId(res.getInt("ID"));
                tmp.setTitle(res.getString("TITLE"));
                tmp.setAuthor(res.getString("AUTHOR"));
                tmp.setDescription(res.getString("DESCRIPTION"));
                tmp.setKeywords(res.getString("KEYWORDS"));
                tmp.setCreationDate(res.getString("CREATION_DATE"));
                tmp.setStorageDate(res.getString("STORAGE_DATE"));
                tmp.setFileName(res.getString("FILENAME"));
                lista.add(tmp);
            }
            OurDao.stopDB();

        } catch (ClassNotFoundException | SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return title;
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
        ArrayList<Image> lista = new ArrayList<>();
        try {
            HashMap<String, String> map = new HashMap<>();
            map.put("cdate", "%" + creaDate + "%");
            OurDao.startDB();
            ResultSet res;
            res = OurDao.consultar(map);
            while (res.next()) {
                Image tmp = new Image();
                tmp.setId(res.getInt("ID"));
                tmp.setTitle(res.getString("TITLE"));
                tmp.setAuthor(res.getString("AUTHOR"));
                tmp.setDescription(res.getString("DESCRIPTION"));
                tmp.setKeywords(res.getString("KEYWORDS"));
                tmp.setCreationDate(res.getString("CREATION_DATE"));
                tmp.setStorageDate(res.getString("STORAGE_DATE"));
                tmp.setFileName(res.getString("FILENAME"));
                lista.add(tmp);
            }
            OurDao.stopDB();

        } catch (ClassNotFoundException | SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return "";
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
        ArrayList<Image> lista = new ArrayList<>();
        try {
            HashMap<String, String> map = new HashMap<>();
            map.put("author", "%" + author + "%");//controlar el sql ijection
            OurDao.startDB();
            ResultSet res;
            res = OurDao.consultar(map);
            while (res.next()) {
                Image tmp = new Image();
                tmp.setId(res.getInt("ID"));
                tmp.setTitle(res.getString("TITLE"));
                tmp.setAuthor(res.getString("AUTHOR"));
                tmp.setDescription(res.getString("DESCRIPTION"));
                tmp.setKeywords(res.getString("KEYWORDS"));
                tmp.setCreationDate(res.getString("CREATION_DATE"));
                tmp.setStorageDate(res.getString("STORAGE_DATE"));
                tmp.setFileName(res.getString("FILENAME"));
                lista.add(tmp);
            }
            OurDao.stopDB();

        } catch (ClassNotFoundException | SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return "";
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
        ArrayList<Image> lista = new ArrayList<>();
        try {
            HashMap<String, String> map = new HashMap<>();
            map.put("keywords", "%" + keywords + "%");//controlar el sql ijection
            OurDao.startDB();
            ResultSet res;
            res = OurDao.consultar(map);
            while (res.next()) {
                Image tmp = new Image();
                tmp.setId(res.getInt("ID"));
                tmp.setTitle(res.getString("TITLE"));
                tmp.setAuthor(res.getString("AUTHOR"));
                tmp.setDescription(res.getString("DESCRIPTION"));
                tmp.setKeywords(res.getString("KEYWORDS"));
                tmp.setCreationDate(res.getString("CREATION_DATE"));
                tmp.setStorageDate(res.getString("STORAGE_DATE"));
                tmp.setFileName(res.getString("FILENAME"));
                lista.add(tmp);
            }
            OurDao.stopDB();

        } catch (ClassNotFoundException | SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return "";

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
