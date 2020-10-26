/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.me.image;

import java.io.IOException;
import static java.lang.System.out;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Samuel
 */
@WebService(serviceName = "ImageWS")
public class ImageWS {

    /**
     * Web service operation
     *
     * @param image
     * @return
     */
    @WebMethod(operationName = "RegistrerImage")
    public int RegistrerImage(@WebParam(name = "image") Image image) {
        if (image.getFileName() == null || image.getFileName().isEmpty()) {
            //throw new FileNotFoundException();
        }

        String basepath = ImageWS.class
                .getProtectionDomain()
                .getCodeSource()
                .getLocation()
                .getPath();
        //CAMBIAR POR CARPETA CORRECTA
        basepath = basepath.substring(0, basepath.lastIndexOf("adlab"));
        final String path = basepath + "adlab/web/images";
        //OutputStream outta = null;
        //InputStream filecontent = null;
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/mm/dd");
        String fechaS = dateFormat.format(date);

        try {

            OurDao.startDB();

            image.setId(OurDao.enregistrar(image.getTitle(), image.getDescription(), image.getKeywords(), image.getAuthor(), image.getCreationDate(), fechaS, image.getFileName()));

            /*outta = new FileOutputStream(new File(path + File.separator + selectImage.getImageName(id, fileName)));
            /filecontent = filePart.getInputStream();

            int read = 0;
            final byte[] bytes = new byte[1024];

            while ((read = filecontent.read(bytes)) != -1) {
                outta.write(bytes, 0, read);
            }
             */
            OurDao.stopDB();

        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getMessage());
        }
        /*finally {

            if (outta != null) {
                outta.close();
            }
            if (filecontent != null) {
                filecontent.close();

            }
            if (out != null) {
                out.close();

            }
        }*/
        return image.getId();
    }

    /*
    private String getFileName(final Part part) {
        final String partHeader = part.getHeader("content-disposition");

        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(
                        content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }*/
    /**
     * Web service operation
     *
     * @param image
     * @return
     */
    @WebMethod(operationName = "ModifyImage")
    public int ModifyImage(@WebParam(name = "image") Image image) {
        try {
            OurDao.startDB();
            String title = image.getTitle();
            String desc = image.getDescription();
            String key = image.getKeywords();
            String data = image.getCreationDate();
            String filename = image.getFileName();
            int id = image.getId();
            boolean ok = OurDao.enregistrarCanvi(title, desc, key, data, filename, id);
            OurDao.stopDB();
        } catch (ClassNotFoundException | SQLException e){
            System.err.println(e.getMessage());
        }
        return 0;
    }

    /**
     * Web service operation
     *
     * @param image
     * @return
     */
    @WebMethod(operationName = "DeleteImage")
    public int DeleteImage(@WebParam(name = "image") Image image) {
        try{
            OurDao.startDB();
            int id = image.getId(); 
            boolean res = OurDao.eliminar(id);
            OurDao.stopDB();
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getMessage());
        }
        return image.getId();
    }

    /**
     * Web service operation
     *
     * @return
     */
    @WebMethod(operationName = "ListImages")
    public List ListImages() {

        List<Image> list = null;
        try {
            OurDao.startDB();
            list = OurDao.getAllImages();
            OurDao.stopDB();
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getMessage());
        }
        return list;
    }

    /**
     * Web service operation
     *
     * @param id
     * @return
     */
   @WebMethod(operationName = "SearchbyId")
    public Image SearchbyId(@WebParam(name = "id") int id
    ) {
        Image tmp = new Image();//cambiar
        try {
            //TODO write your implementation code here:
            HashMap<String, String> map = new HashMap<>();
            map.put("id", String.valueOf(id));//controlar el sql injection
            OurDao.startDB(); // els stopDB shan de posar? 
            ResultSet res;
            res = OurDao.consultar(map);
            if(res==null)return null;
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

           /* //Leer imagen del sistema de ficheros
            String basepath = ImageWS.class
                    .getProtectionDomain()
                    .getCodeSource()
                    .getLocation()
                    .getPath();
            String projectName = "ImageWSApplication";
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

        */} catch (ClassNotFoundException | SQLException ex) {
            System.err.println(ex.getMessage());
            return null;
        }
        //q pasa si la imagen es vacia
        
        return tmp;
    }

    /**
     * Web service operation
     *
     * @param title
     * @return
     */
    @WebMethod(operationName = "SearchbyTitle")
    public List SearchbyTitle(@WebParam(name = "title") String title) {
        //TODO write your implementation code here:
        return null;
    }

    /**
     * Web service operation
     *
     * @param creaDate
     * @return
     */
    @WebMethod(operationName = "SearchbyCreaDate")
    public List SearchbyCreaDate(@WebParam(name = "creaDate") String creaDate) {
        //TODO write your implementation code here:
        return null;
    }

    /**
     * Web service operation
     *
     * @param author
     * @return
     */
    @WebMethod(operationName = "SearchbyAuthor")
    public List SearchbyAuthor(@WebParam(name = "author") String author) {
        //TODO write your implementation code here:
        return null;
    }

    /**
     * Web service operation
     *
     * @param keywords
     * @return
     */
    @WebMethod(operationName = "SearchbyKeywords")
    public List SearchbyKeywords(@WebParam(name = "keywords") String keywords) {
        //TODO write your implementation code here:
        return null;
    }

    /**
     * Web service operation
     *
     * @param username
     * @param password
     * @return
     */
    @WebMethod(operationName = "registerUser")
    public boolean registerUser(@WebParam(name = "username") String username, @WebParam(name = "password") String password) {
        try {
            OurDao.startDB();
            if (!OurDao.validatePassword(password) || !OurDao.validateUsername(username)) {
                throw new IllegalArgumentException("Contraseña o usuario con formato invalido");
            }
            OurDao.newuser(username, password);
            OurDao.stopDB();
        } catch (ClassNotFoundException | IllegalArgumentException | SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * Web service operation
     *
     * @param username
     * @param password
     * @return
     */
    @WebMethod(operationName = "loginUser")
    public boolean loginUser(@WebParam(name = "username") String username, @WebParam(name = "password") String password) {
        boolean logged = false;
        try {
            OurDao.startDB();
            if (!OurDao.validatePassword(password) || !OurDao.validateUsername(username)) {
                throw new IllegalArgumentException("Contraseña o usuario con formato invalido");
            }
            logged = OurDao.loggin(username, password);
            OurDao.stopDB();
        } catch (ClassNotFoundException | IllegalArgumentException | SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
        return logged;
    }

    /**
     * Web service operation
     * @return 
     */
    @WebMethod(operationName = "getUsers")
    public List<String> getUsers() {
        List<String> list = null;
        try{
            OurDao.startDB();
            list = OurDao.getUsers();
            OurDao.stopDB();
        } catch(ClassNotFoundException | SQLException e){
            System.err.println(e.getMessage());
        }
        return list;
    }
}
