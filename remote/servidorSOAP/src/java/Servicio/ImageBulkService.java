/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicio;

import BaseDeDatos.DAOImages;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.servlet.ServletException;
import modelo.Image;

/**
 *
 * @author Efren
 */
@WebService(serviceName = "ImageBulkService")
public class ImageBulkService {

     


    /**
     * Web service operation
     */
    @WebMethod(operationName = "RegisterImage")
    public int RegisterImage(@WebParam(name = "image") Image image) {
        
        int id = image.getId();
        String auth = image.getAuth();
        String Kwords = image.getKwords();
        String C_date = image.getC_date();
        String filename = image.getFilename();
        String title = image.getTitle();
        String description = image.getDescription();
        byte[] rawdata = image.getRawData();
        try {
            try {
                DAOImages.RegisterImage(title, description, Kwords, auth, C_date, filename, rawdata);
            } catch (SQLException ex) {
                
                Logger.getLogger(ImageBulkService.class.getName()).log(Level.SEVERE, null, ex);
                return -1;
            }
        } catch (ServletException ex) {
            Logger.getLogger(ImageBulkService.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        } catch (IOException ex) {
            Logger.getLogger(ImageBulkService.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
        
        return 0;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "DeleteImage")
    public int DeleteImage(@WebParam(name = "image") Image image) {
        //TODO write your implementation code here:
        int id = image.getId();
        try {
            DAOImages.DeleteImage(id);
        } catch (ServletException ex) {
            Logger.getLogger(ImageBulkService.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        } catch (IOException ex) {
            Logger.getLogger(ImageBulkService.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
        return 0;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "ModifyImage")
    public int ModifyImage(@WebParam(name = "image") Image image) {
         int id = image.getId();
        String Kwords = image.getKwords();
        String title = image.getTitle();
        String description = image.getDescription();
        try {
            DAOImages.ModifyImage(id, title, description, Kwords);
        } catch (ServletException ex) {
            Logger.getLogger(ImageBulkService.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        } catch (IOException ex) {
            Logger.getLogger(ImageBulkService.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
        return 0;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "ListImage")
    public List ListImage() {
        //TODO write your implementation code here:
        List<Image> returnlist = new ArrayList<>();
        try {
            returnlist = DAOImages.ListImage();
        } catch (ServletException ex) {
            Logger.getLogger(ImageBulkService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ImageBulkService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return returnlist;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "SearchbyTitle")
    public List SearchbyTitle(@WebParam(name = "Title") String Title) {
        List<Image> returnvalue = new ArrayList<>();
        try {
            returnvalue = DAOImages.SearchImage(-1,Title,"", "", "", true);
        } catch (ServletException ex) {
            Logger.getLogger(ImageBulkService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ImageBulkService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return returnvalue;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "SearchbyId")
    public Image SearchbyId(@WebParam(name = "id") int id) {
       List<Image> returnvalue = new ArrayList<>();
        try {
            returnvalue = DAOImages.SearchImage(id,"","", "", "", true);
            
        } catch (ServletException ex) {
            Logger.getLogger(ImageBulkService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ImageBulkService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return returnvalue.get(0);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "SearchbyCreaDate")
    public List SearchbyCreaDate(@WebParam(name = "CreaDate") String CreaDate) {
       List<Image> returnvalue = new ArrayList<>();
        try {
            returnvalue = DAOImages.SearchImage(-1,"","", "", CreaDate, true);
        } catch (ServletException ex) {
            Logger.getLogger(ImageBulkService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ImageBulkService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return returnvalue;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "SearchbyAuthor")
    public List SearchbyAuthor(@WebParam(name = "author") String author) {
        List<Image> returnvalue = new ArrayList<>();
        try {
            
            returnvalue = DAOImages.SearchImage(-1,"","", author, "", true);
           
        } catch (ServletException ex) {
            Logger.getLogger(ImageBulkService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
         
            Logger.getLogger(ImageBulkService.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("1");
        return returnvalue;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "SearchbyKeywords")
    public List SearchbyKeywords(@WebParam(name = "keywords") String keywords) {
        List<Image> returnvalue = new ArrayList<>();
        try {
            returnvalue = DAOImages.SearchImage(-1,"",keywords, "", "", true);
        } catch (ServletException ex) {
            Logger.getLogger(ImageBulkService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ImageBulkService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return returnvalue;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "Search")
    public List Search(@WebParam(name = "Title") String Title, @WebParam(name = "Author") String Author, @WebParam(name = "Kwords") String Kwords, @WebParam(name = "Type") boolean Type) {
        List<Image> returnvalue = new ArrayList<>();
        try {
            returnvalue = DAOImages.SearchImage(-1,Title,Kwords, Author, "", Type);
        } catch (ServletException ex) {
            Logger.getLogger(ImageBulkService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ImageBulkService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return returnvalue;
    }
}
