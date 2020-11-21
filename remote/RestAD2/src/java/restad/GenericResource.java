package restad;

import DAOpackage.DAOImages;
import DAOpackage.DAOUser;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import modelo.Image;

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
     * LOGIN
     * @param user
     * @param password
     * @return 
     */
    @Path("login")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_HTML)
    public String login (@FormParam("user") String user,
    @FormParam("password") String password){
        System.out.println(user);
        System.out.println(password);        
        boolean verify = false;
        try {
            verify = DAOUser.verify(user,password);
        } catch (IOException ex) {
            Logger.getLogger(GenericResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (verify) {
            return "<html style=\" height:100%;background-image: linear-gradient(to right top, #8e44ad 0%, #3498db 100%);font-family: Verdana\"><head></head><body><h1 style = \"text-align:center\">"
                        + "Te has logueado correctamente</h1> <nav style=\"text-align:center\"> <ul > <li class=\"Cnav-ul-li\"> <a class=\"Cnav-ul-li-a\" href =\"http://localhost:17560/ClientRestAD/menu.jsp\"> Ir al menu </a> </li> "
                        + " </ul> </nav> </body></html>";
        }
        else {
            return "<html style=\" height:100%;background-image: linear-gradient(to right top, #8e44ad 0%, #3498db 100%);font-family: Verdana\"><head></head><body><h1 style = \"text-align:center\">"
                        + "Usuario Incorrecto</h1> <nav style=\"text-align:center\"> <ul > <li class=\"Cnav-ul-li\"> <a class=\"Cnav-ul-li-a\" href =\"http://localhost:17560/ClientRestAD/login.jsp\"> Volver al Login </a> </li> "
                        + " </ul> </nav> </body></html>";
        }  
    }
    
    
    
    
    /**
     * POST method to register a new image
     * @param title
     * @param description
     * @param keywords
     * @param author
     * @param date
     * @param uploadedInputStream
     * @return
     */
    
    @Path("register")
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.TEXT_HTML)
    public String registerImage (
    @FormDataParam("title") String title,
    @FormDataParam("description") String description,
    @FormDataParam("keywords") String keywords,
    @FormDataParam("author") String author,
    @FormDataParam("date") String date,
    @FormDataParam("file") InputStream uploadedInputStream)
    {
        boolean a = false;
        try {
            byte[] rawData = toByteArray(uploadedInputStream);
            uploadedInputStream.read(rawData);
            try {
                 a = DAOImages.RegisterImage(title, description, keywords, author, date, "imatge", rawData);
            } catch (SQLException ex) {
                Logger.getLogger(GenericResource.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } catch (IOException ex) {
            Logger.getLogger(GenericResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(a){
            return "<html style=\" height:100%;background-image: linear-gradient(to right top, #8e44ad 0%, #3498db 100%);font-family: Verdana\"><head></head><body><h1 style = \"text-align:center\">"
                        + "La imagen se ha registrado correctamente</h1> <nav style=\"text-align:center\"> <ul > <li class=\"Cnav-ul-li\"> <a class=\"Cnav-ul-li-a\" href =\"http://localhost:17560/ClientRestAD/menu.jsp\"> Volver al menu </a> </li> "
                        + "<li class=\"Cnav-ul-li\"> <a class=\"Cnav-ul-li-a\" href = http://localhost:17560/ClientRestAD/registrarimagen.jsp> Registrar otra Imagen </a> </li> </ul> </nav> </body></html>";
            }
        else{
            return "<html style=\" height:100%;background-image: linear-gradient(to right top, #8e44ad 0%, #3498db 100%);font-family: Verdana\"><head></head><body><h1 style = \"text-align:center\">"
                        + "La imagen se ha registrado correctamente</h1></body></html>";
        }
    }
    
    
    
    
    
    /**
 * POST method to modify an existing image
     * @param id
 * @param title
 * @param description
 * @param keywords
 * @return
 */
    
    
 @Path("modify")
 @POST
 @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
 @Produces(MediaType.TEXT_HTML)
 public String modifyImage (
 @FormParam("id") String id,
 @FormParam("title") String title,
 @FormParam("description") String description,
 @FormParam("keywords") String keywords){
     boolean error = false;
     System.out.println(id);
        try {
            error = DAOImages.ModifyImage(Integer.parseInt(id), title, description, keywords);
        } catch (IOException ex) {
            Logger.getLogger(GenericResource.class.getName()).log(Level.SEVERE, null, ex);
        }
     if(error){
     return "<!DOCTYPE html> <html style=\" height:100%;background-image: linear-gradient(to right top, #8e44ad 0%, #3498db 100%);font-family: Verdana\"><head></head><body><h1 style = \"text-align:center\">La imagen se ha modificado correctamente</h1></body></html>"
             + "<nav style=\"text-align:center\"> <ul class=\"Cnav-ul\"> <li ><a href =\"http://localhost:17560/ClientRestAD/menu.jsp\"> Volver al menu </a> </li> </ul> </nav> </body> </html>";
     }
     else {
         return "<!DOCTYPE html> <html style=\\\" height:100%;background-image: linear-gradient(to right top, #8e44ad 0%, #3498db 100%);font-family: Verdana\\\"><head></head><body><h1 style = \\\"text-align:center\\\">Ha habido un error</h1></body></html>";
     }
 }
 
 
 
 
 
 
 
 
 /**
 * POST method to delete an existing image
     * @param id
 * @return
 */
 @Path("delete")
 @POST
 @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
 @Produces(MediaType.TEXT_HTML)
 public String deleteImage (@FormParam("id") String id){
        
            System.out.println(id);
        try {
            DAOImages.DeleteImage(Integer.parseInt(id));
        } catch (IOException ex) {
            Logger.getLogger(GenericResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    return "<!DOCTYPE html> <html style=\" height:100%;background-image: linear-gradient(to right top, #8e44ad 0%, #3498db 100%);font-family: Verdana\"><head></head><body><h1 style = \"text-align:center\">La imagen ha sido eliminada</h1>"
            + "<div style = \"text-align:center\"><ul><li><a href =\"http://localhost:17560/ClientRestAD/menu.jsp\"> Volver al Menú </a></li></ul></div></body></html>";
 }


  
 /**
 * GET method to list images
 * @return
 */
 @Path("list")
 @GET
 @Produces(MediaType.TEXT_HTML)
 public String listImages (){
        String returned = "<!DOCTYPE html> <html style=\" height:100%;background-image: linear-gradient(to right top, #8e44ad 0%, #3498db 100%);font-family: Verdana\"> <head> <link rel=\"stylesheet\" href=\"styles.css\"></head>"
             + "<body><h1 style = \"text-align:center\">Listado de imagenes</h1> "
             + "<div style=\"padding-bottom:3%; padding-top:2%;\" class=\"Cnav-b\"><ul class=\"Cnav-ul\"><li class=\"Cnav-ul-li\"><a class=\"Cnav-ul-li-a\" href =\"http://localhost:17560/ClientRestAD/menu.jsp\"> Volver al Menu </a></li></ul></div>";
        List<Image> images = new ArrayList();
        try {
            images = DAOImages.ListImage();
        } catch (IOException ex) {
            Logger.getLogger(GenericResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        int cont = 0;
        while (cont < images.size() ) {
            returned += "<div class=\"listaimatge\"> <h3> Titulo: " +images.get(cont).getTitle() + "</h3><br>"
             + "<div class=\"listaimatge\"> <h3> Autor: " +images.get(cont).getAuth() + "</h3><br>"
             + "<img  width=\"300\" height=\"300\"  src=\"http://localhost:17560/RestAD/Images/" + images.get(cont).getFilename()+ "\"> <br>"
             + "<button style = \" float:left \" onclick= \"location.href = 'http://localhost:17560/ClientRestAD/eliminarimagen.jsp?id=" + images.get(cont).getId()+ "' \" type=\"submit\"  >Eliminar</button>"
             + "<button style = \" float:left \" onclick= \"location.href = 'http://localhost:17560/ClientRestAD/modificarimagen.jsp?id=" + images.get(cont).getId() + "&title="+ images.get(cont).getTitle()+ "&description="+ images.get(cont).getDescription()+ "&keywords="+ images.get(cont).getKwords()+"' \" type=\"submit\"  >Modificar</button>"
             + "</div>";
             ++cont;
        }
        returned += "</body></html>";
        return returned;
 }
                
               /*  if (session.getAttribute("user").equals(images.get(cont).getAuth())) {
                     out.println("<button style = \" float:left \" onclick= \"location.href = 'eliminarimagen.jsp?id=" + images.get(cont).getId()+ "' \" type=\"submit\"  >Eliminar</button>");
                     out.println("<button style = \" float:left \" onclick= \"location.href = 'modificarimagen.jsp?id=" + images.get(cont).getId() + "&title="+ images.get(cont).getTitle()+ "&description="+ images.get(cont).getDescription()+ "&keywords="+ images.get(cont).getKwords()+"' \" type=\"submit\"  >Modificar</button>"); 
                 }*/
 
 
 
 
 
 /**
 * GET method to search images by id
 * @param id
 * @return
 */
 @Path("searchID/{id}")
 @GET
 @Produces(MediaType.TEXT_HTML)
 public String searchByID (@PathParam("id") String id){
    String returned = "<!DOCTYPE html> <html style=\" height:100%;background-image: linear-gradient(to right top, #8e44ad 0%, #3498db 100%);font-family: Verdana\"> <head> <link rel=\"stylesheet\" href=\"styles.css\"></head>"
    + "<body><h1 style = \"text-align:center\">Listado de imagenes</h1> "
    + "<div style=\"padding-bottom:3%; padding-top:2%;\" class=\"Cnav-b\"><ul class=\"Cnav-ul\"><li class=\"Cnav-ul-li\"><a class=\"Cnav-ul-li-a\" href =\"http://localhost:17560/ClientRestAD/menu.jsp\"> Volver al Menu </a></li></ul></div>";
    List<Image> images = new ArrayList();
    try {
        images = DAOImages.SearchImage(Integer.parseInt(id), "", "", "", "", true);
    } catch (IOException ex) {
        Logger.getLogger(GenericResource.class.getName()).log(Level.SEVERE, null, ex);
        }
    int cont = 0;
    while (cont < images.size() ) {
        returned += "<div class=\"listaimatge\"> <h3> Titulo: " +images.get(cont).getTitle() + "</h3><br>"
         + "<div class=\"listaimatge\"> <h3> Autor: " +images.get(cont).getAuth() + "</h3><br>"
         + "<img  width=\"300\" height=\"300\"  src=\"http://localhost:17560/RestAD/Images/" + images.get(cont).getFilename()+ "\"> <br>"
         + "<button style = \" float:left \" onclick= \"location.href = 'http://localhost:17560/ClientRestAD/eliminarimagen.jsp?id=" + images.get(cont).getId()+ "' \" type=\"submit\"  >Eliminar</button>"
         + "<button style = \" float:left \" onclick= \"location.href = 'http://localhost:17560/ClientRestAD/modificarimagen.jsp?id=" + images.get(cont).getId() + "&title="+ images.get(cont).getTitle()+ "&description="+ images.get(cont).getDescription()+ "&keywords="+ images.get(cont).getKwords()+"' \" type=\"submit\"  >Modificar</button>"
         + "</div>";
         ++cont;
    }
        returned += "</body></html>";
    return returned;
 }
 
 /**
 * GET method to search images by title
 * @param title
 * @return
 */
 @Path("searchTitle/{title}")
 @GET
 @Produces(MediaType.TEXT_HTML)
 public String searchByTitle (@PathParam("title") String title){
    String returned = "<!DOCTYPE html> <html style=\" height:100%;background-image: linear-gradient(to right top, #8e44ad 0%, #3498db 100%);font-family: Verdana\"> <head> <link rel=\"stylesheet\" href=\"styles.css\"></head>"
    + "<body><h1 style = \"text-align:center\">Listado de imagenes</h1> "
    + "<div style=\"padding-bottom:3%; padding-top:2%;\" class=\"Cnav-b\"><ul class=\"Cnav-ul\"><li class=\"Cnav-ul-li\"><a class=\"Cnav-ul-li-a\" href =\"http://localhost:17560/ClientRestAD/menu.jsp\"> Volver al Menu </a></li></ul></div>";
    List<Image> images = new ArrayList();
    try {
        images = DAOImages.SearchImage(-1, title, "", "", "", true);
    } catch (IOException ex) {
        Logger.getLogger(GenericResource.class.getName()).log(Level.SEVERE, null, ex);
        }
    int cont = 0;
    while (cont < images.size() ) {
        returned += "<div class=\"listaimatge\"> <h3> Titulo: " +images.get(cont).getTitle() + "</h3><br>"
         + "<div class=\"listaimatge\"> <h3> Autor: " +images.get(cont).getAuth() + "</h3><br>"
         + "<img  width=\"300\" height=\"300\"  src=\"http://localhost:17560/RestAD/Images/" + images.get(cont).getFilename()+ "\"> <br>"
         + "<button style = \" float:left \" onclick= \"location.href = 'http://localhost:17560/ClientRestAD/eliminarimagen.jsp?id=" + images.get(cont).getId()+ "' \" type=\"submit\"  >Eliminar</button>"
         + "<button style = \" float:left \" onclick= \"location.href = 'http://localhost:17560/ClientRestAD/modificarimagen.jsp?id=" + images.get(cont).getId() + "&title="+ images.get(cont).getTitle()+ "&description="+ images.get(cont).getDescription()+ "&keywords="+ images.get(cont).getKwords()+"' \" type=\"submit\"  >Modificar</button>"
         + "</div>";
         ++cont;
    }
        returned += "</body></html>";
    return returned;
 }
 
 
 /**
 * GET method to search images by creation date
 * @param date
 * @return
 */
 @Path("searchCreationDate/{date}")
 @GET
 @Produces(MediaType.TEXT_HTML)
 public String searchByCreationDate (@PathParam("date") String date){
    String returned = "<!DOCTYPE html> <html style=\" height:100%;background-image: linear-gradient(to right top, #8e44ad 0%, #3498db 100%);font-family: Verdana\"> <head> <link rel=\"stylesheet\" href=\"styles.css\"></head>"
    + "<body><h1 style = \"text-align:center\">Listado de imagenes</h1> "
    + "<div style=\"padding-bottom:3%; padding-top:2%;\" class=\"Cnav-b\"><ul class=\"Cnav-ul\"><li class=\"Cnav-ul-li\"><a class=\"Cnav-ul-li-a\" href =\"http://localhost:17560/ClientRestAD/menu.jsp\"> Volver al Menu </a></li></ul></div>";
    List<Image> images = new ArrayList();
    try {
        images = DAOImages.SearchImage(-1, "", "", "", date, true);
    } catch (IOException ex) {
        Logger.getLogger(GenericResource.class.getName()).log(Level.SEVERE, null, ex);
        }
    int cont = 0;
    while (cont < images.size() ) {
        returned += "<div class=\"listaimatge\"> <h3> Titulo: " +images.get(cont).getTitle() + "</h3><br>"
         + "<div class=\"listaimatge\"> <h3> Autor: " +images.get(cont).getAuth() + "</h3><br>"
         + "<img  width=\"300\" height=\"300\"  src=\"http://localhost:17560/RestAD/Images/" + images.get(cont).getFilename()+ "\"> <br>"
         + "<button style = \" float:left \" onclick= \"location.href = 'http://localhost:17560/ClientRestAD/eliminarimagen.jsp?id=" + images.get(cont).getId()+ "' \" type=\"submit\"  >Eliminar</button>"
         + "<button style = \" float:left \" onclick= \"location.href = 'http://localhost:17560/ClientRestAD/modificarimagen.jsp?id=" + images.get(cont).getId() + "&title="+ images.get(cont).getTitle()+ "&description="+ images.get(cont).getDescription()+ "&keywords="+ images.get(cont).getKwords()+"' \" type=\"submit\"  >Modificar</button>"
         + "</div>";
         ++cont;
    }
        returned += "</body></html>";
    return returned;
 }
 
 
 /**
 * GET method to search images by author
 * @param author
 * @return
 */
 @Path("searchAuthor/{author}")
 @GET
 @Produces(MediaType.TEXT_HTML)
 public String searchByAuthor (@PathParam("author") String author){
    String returned = "<!DOCTYPE html> <html style=\" height:100%;background-image: linear-gradient(to right top, #8e44ad 0%, #3498db 100%);font-family: Verdana\"> <head> <link rel=\"stylesheet\" href=\"styles.css\"></head>"
    + "<body><h1 style = \"text-align:center\">Listado de imagenes</h1> "
    + "<div style=\"padding-bottom:3%; padding-top:2%;\" class=\"Cnav-b\"><ul class=\"Cnav-ul\"><li class=\"Cnav-ul-li\"><a class=\"Cnav-ul-li-a\" href =\"http://localhost:17560/ClientRestAD/menu.jsp\"> Volver al Menu </a></li></ul></div>";
    List<Image> images = new ArrayList();
    try {
        images = DAOImages.SearchImage(-1, "", "", author, "", true);
    } catch (IOException ex) {
        Logger.getLogger(GenericResource.class.getName()).log(Level.SEVERE, null, ex);
        }
    int cont = 0;
    while (cont < images.size() ) {
        returned += "<div class=\"listaimatge\"> <h3> Titulo: " +images.get(cont).getTitle() + "</h3><br>"
         + "<div class=\"listaimatge\"> <h3> Autor: " +images.get(cont).getAuth() + "</h3><br>"
         + "<img  width=\"300\" height=\"300\"  src=\"http://localhost:17560/RestAD/Images/" + images.get(cont).getFilename()+ "\"> <br>"
         + "<button style = \" float:left \" onclick= \"location.href = 'http://localhost:17560/ClientRestAD/eliminarimagen.jsp?id=" + images.get(cont).getId()+ "' \" type=\"submit\"  >Eliminar</button>"
         + "<button style = \" float:left \" onclick= \"location.href = 'http://localhost:17560/ClientRestAD/modificarimagen.jsp?id=" + images.get(cont).getId() + "&title="+ images.get(cont).getTitle()+ "&description="+ images.get(cont).getDescription()+ "&keywords="+ images.get(cont).getKwords()+"' \" type=\"submit\"  >Modificar</button>"
         + "</div>";
         ++cont;
    }
        returned += "</body></html>";
    return returned;
 }
 
         
         /**
 * GET method to search images by keyword
 * @param keywords
 * @return
 */
 @Path("searchKeywords/{keywords}")
 @GET
 @Produces(MediaType.TEXT_HTML)
 public String searchByKeywords (@PathParam("keywords") String
 keywords){
    String returned = "<!DOCTYPE html> <html style=\" height:100%;background-image: linear-gradient(to right top, #8e44ad 0%, #3498db 100%);font-family: Verdana\"> <head> <link rel=\"stylesheet\" href=\"styles.css\"></head>"
    + "<body><h1 style = \"text-align:center\">Listado de imagenes</h1> "
    + "<div style=\"padding-bottom:3%; padding-top:2%;\" class=\"Cnav-b\"><ul class=\"Cnav-ul\"><li class=\"Cnav-ul-li\"><a class=\"Cnav-ul-li-a\" href =\"http://localhost:17560/ClientRestAD/menu.jsp\"> Volver al Menu </a></li></ul></div>";
    List<Image> images = new ArrayList();
    try {
        images = DAOImages.SearchImage(-1, "", keywords, "", "", true);
    } catch (IOException ex) {
        Logger.getLogger(GenericResource.class.getName()).log(Level.SEVERE, null, ex);
        }
    int cont = 0;
    while (cont < images.size() ) {
        returned += "<div class=\"listaimatge\"> <h3> Titulo: " +images.get(cont).getTitle() + "</h3><br>"
         + "<div class=\"listaimatge\"> <h3> Autor: " +images.get(cont).getAuth() + "</h3><br>"
         + "<img  width=\"300\" height=\"300\"  src=\"http://localhost:17560/RestAD/Images/" + images.get(cont).getFilename()+ "\"> <br>"
         + "<button style = \" float:left \" onclick= \"location.href = 'http://localhost:17560/ClientRestAD/eliminarimagen.jsp?id=" + images.get(cont).getId()+ "' \" type=\"submit\"  >Eliminar</button>"
         + "<button style = \" float:left \" onclick= \"location.href = 'http://localhost:17560/ClientRestAD/modificarimagen.jsp?id=" + images.get(cont).getId() + "&title="+ images.get(cont).getTitle()+ "&description="+ images.get(cont).getDescription()+ "&keywords="+ images.get(cont).getKwords()+"' \" type=\"submit\"  >Modificar</button>"
         + "</div>";
         ++cont;
    }
        returned += "</body></html>";
    return returned;
 }
 
 
 
 
          /**
 * GET method to search images by keyword
     * @param id
     * @param title
     * @param author
     * @param keywords
     * @param date
 * @return
 */
 @Path("searchCombined/{id}/{title}/{author}/{keywords}/{date}")
 @GET
 @Produces(MediaType.TEXT_HTML)
 public String searchCombined(@PathParam("id") String id, @PathParam("title") String title, @PathParam("author") String author, @PathParam("keywords") String keywords, @PathParam("date") String date ) {
    String returned = "<!DOCTYPE html> <html style=\" height:100%;background-image: linear-gradient(to right top, #8e44ad 0%, #3498db 100%);font-family: Verdana\"> <head> <link rel=\"stylesheet\" href=\"styles.css\"></head>"
    + "<body><h1 style = \"text-align:center\">Listado de imagenes</h1> "
    + "<div style=\"padding-bottom:3%; padding-top:2%;\" class=\"Cnav-b\"><ul class=\"Cnav-ul\"><li class=\"Cnav-ul-li\"><a class=\"Cnav-ul-li-a\" href =\"http://localhost:17560/ClientRestAD/menu.jsp\"> Volver al Menu </a></li></ul></div>";
    List<Image> images = new ArrayList();
    try {
        images = DAOImages.SearchImage(Integer.parseInt(id), title, keywords, author, date, true);
    } catch (IOException ex) {
        Logger.getLogger(GenericResource.class.getName()).log(Level.SEVERE, null, ex);
        }
    int cont = 0;
    while (cont < images.size() ) {
        returned += "<div class=\"listaimatge\"> <h3> Titulo: " +images.get(cont).getTitle() + "</h3><br>"
         + "<div class=\"listaimatge\"> <h3> Autor: " +images.get(cont).getAuth() + "</h3><br>"
         + "<img  width=\"300\" height=\"300\"  src=\"http://localhost:17560/RestAD/Images/" + images.get(cont).getFilename()+ "\"> <br>"
         + "<button style = \" float:left \" onclick= \"location.href = 'http://localhost:17560/ClientRestAD/eliminarimagen.jsp?id=" + images.get(cont).getId()+ "' \" type=\"submit\"  >Eliminar</button>"
         + "<button style = \" float:left \" onclick= \"location.href = 'http://localhost:17560/ClientRestAD/modificarimagen.jsp?id=" + images.get(cont).getId() + "&title="+ images.get(cont).getTitle()+ "&description="+ images.get(cont).getDescription()+ "&keywords="+ images.get(cont).getKwords()+"' \" type=\"submit\"  >Modificar</button>"
         + "</div>";
         ++cont;
    }
        returned += "</body></html>";
    return returned;
 }
 
 
 
 public static byte[] toByteArray(InputStream in) throws IOException {
 
        ByteArrayOutputStream os = new ByteArrayOutputStream();
 
        byte[] buffer = new byte[1024];
        int len;
 
        // read bytes from the input stream and store them in buffer
        while ((len = in.read(buffer)) != -1) {
            // write bytes from the buffer into output stream
            os.write(buffer, 0, len);
        }
 
        return os.toByteArray();
    }
 
}
