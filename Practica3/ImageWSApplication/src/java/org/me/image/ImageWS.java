/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.me.image;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.xml.ws.soap.MTOM;

/**
 *
 * @author Samuel
 */
@MTOM(threshold = 2048)
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
            return -1;
        }
        image.setStorageDate(LocalDate.now().toString());
        try {

            //BBDD
            OurDao.startDB();
            image.setId(
                    OurDao.enregistrar(
                            image.getTitle(),
                            image.getDescription(),
                            image.getKeywords(),
                            image.getAuthor(),
                            image.getCreationDate(),
                            image.getStorageDate(),
                            image.getFileName()
                    )
            );
            OurDao.stopDB();

            //Escribe imagen en el directorio web/images
            String basepath = ImageWS.class
                    .getProtectionDomain()
                    .getCodeSource()
                    .getLocation()
                    .getPath();
            String projectName = "ImageWSApplication";
            basepath = basepath.substring(0, basepath.lastIndexOf(projectName));
            final String path = basepath + projectName + "/web/images/";
            
            File newdir = new File(path);
            if(!newdir.exists())newdir.mkdir();

            try (BufferedOutputStream outStream = new BufferedOutputStream(new FileOutputStream(path + image.getImageName()))) {
                outStream.write(image.getBytes());
            }

        } catch (IOException | ClassNotFoundException | SQLException e) {
            System.err.println(e.getMessage());
        }
        return image.getId();
    }

    /**
     * Web service operation
     *
     * @param image
     * @return
     */
    @WebMethod(operationName = "ModifyImage")
    public int ModifyImage(@WebParam(name = "image") Image image
    ) {
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
        } catch (ClassNotFoundException | SQLException e) {
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
    public int DeleteImage(@WebParam(name = "image") Image image
    ) {
        try {
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
        //TODO write your implementation code here:
        return null;
    }

    /**
     * Web service operation
     *
     * @param title
     * @return
     */
    @WebMethod(operationName = "SearchbyTitle")
    public List SearchbyTitle(@WebParam(name = "title") String title
    ) {
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
    public List SearchbyCreaDate(@WebParam(name = "creaDate") String creaDate
    ) {
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
    public List SearchbyAuthor(@WebParam(name = "author") String author
    ) {
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
    public List SearchbyKeywords(@WebParam(name = "keywords") String keywords
    ) {
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
    public boolean registerUser(@WebParam(name = "username") String username,
            @WebParam(name = "password") String password
    ) {
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
    public boolean loginUser(@WebParam(name = "username") String username,
            @WebParam(name = "password") String password
    ) {
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
     *
     * @return
     */
    @WebMethod(operationName = "getUsers")
    public List<String> getUsers() {
        List<String> list = null;
        try {
            OurDao.startDB();
            list = OurDao.getUsers();
            OurDao.stopDB();
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getMessage());
        }
        return list;
    }
}
