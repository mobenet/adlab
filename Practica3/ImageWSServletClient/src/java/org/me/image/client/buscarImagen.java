/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.me.image.client;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;
import javax.xml.ws.WebServiceRef;
import org.me.image.Image;
import org.me.image.ImageWS_Service;

/**
 *
 * @author elchu
 */
@WebServlet(name = "buscarImagen", urlPatterns = {"/buscarImagen"})
@MultipartConfig

public class buscarImagen extends HttpServlet {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/ImageWSApplication/ImageWS.wsdl")
    private ImageWS_Service service;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession ses = request.getSession(false);
        String user = (String) ses.getAttribute("user");
        if (user == null) {
            response.sendRedirect("login.jsp");
        } else {
            try (PrintWriter out = response.getWriter()) {
                String author = request.getParameter("author");
                String creationDate = request.getParameter("cdate");
                String keywords = request.getParameter("keywords");
                String title = request.getParameter("title");
                ArrayList<List<Object>> searchArray = new ArrayList<>();
                if (title != null) searchArray.add(searchbyTitle(title));
                if (keywords != null) searchArray.add(searchbyKeywords(keywords));
                if (author != null) searchArray.add(searchbyAuthor(author));
                if (creationDate != null)  searchArray.add(searchbyCreaDate(creationDate));
                List<Image> searchResult = combineSearch(searchArray);
                if (searchResult.isEmpty()) {
                    out.println("No hay resultados con las entradas correspondientes");
                    out.print("<br><br>");
                    String resp = "<a href=\"menu.jsp\">Menu principal</a>";
                    out.println(resp);
                    out.print("<br><br>");
                    resp = "<a href=\"buscarImagen.jsp\">Buscar una nueva imagen</a>";
                    out.println(resp);
                    out.print("<br><br>");
                } else {
                    out.println("Listado de imagenes: <br>");
                    out.println("<table>\n"
                            + "            <tr>\n"
                            + "                <th>Titulo</th>\n"
                            + "                <th>Descripcion</th>\n"
                            + "                <th>Palabras Clave</th>\n"
                            + "                <th>Autor</th>\n"
                            + "                <th>Fecha de creacion</th>\n"
                            + "                <th>Fecha de subida</th>\n"
                            + "                <th>Nombre del archivo</th>\n"
                            + "            </tr>");
                    for(Image img: searchResult){
                        out.println("<tr>");
                        out.println("<td>" + img.getTitle() + "</td>");
                        out.println("<td>" + img.getDescription() + "</td>");
                        out.println("<td>" + img.getKeywords() + "</td>");
                        String autor = img.getAuthor();
                        out.println("<td>" + autor + "</td>");
                        out.println("<td>" + img.getCreationDate() + "</td>");
                        out.println("<td>" + img.getStorageDate() + "</td>");
                        String filename = img.getFileName();
                        int id = img.getId();
                        out.println("<td><a href=showImage?id=" + id + ">" + filename + "</a>");
                        if (autor.equals(user)) {
                            out.println("<form action=selectImage method=\"POST\">"
                                    + "<input type=\"hidden\" value=\"" + filename + "\" name=\"name\"/>"
                                    + "<input type=\"hidden\" value=\"" + Integer.toString(id) + "\" name=\"id\"/>"
                                    + "<input type=\"submit\" value=\"Modificar\" name=\"action\"/>"
                                    + "<input type=\"submit\" value=\"Eliminar\" name=\"action\"/> </form> </td>");
                        }
                    }
                    out.println("</table>");
                    out.println("<a href=\"buscarImagen.jsp\">Hacer otra busqueda</a><br><br>");
                    out.println("<a href=\"menu.jsp\">Vuelve al menu</a>");
                }
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(buscarImagen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(buscarImagen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
    private List<Image> combineSearch(List<List<Object>> searchResult){
        if(searchResult.isEmpty())return Arrays.asList();
        ArrayList<Image> res = new ArrayList<>();
        searchResult.get(0).forEach(o -> {
            Image img = (Image)o;
            res.add(img);
        });
        searchResult.forEach(list -> {
            ArrayList<Image> tmp = new ArrayList<>();
            list.forEach(o -> {
                Image img = (Image)o;
                tmp.add(img);
            });
            intersect(res,tmp);
        });
        return res;
    }
    
    private void intersect(ArrayList<Image> acc, List<Image> other){
        acc.removeIf(e -> !other.stream().anyMatch(img -> (img.getId() == e.getId())));
    }

    private java.util.List<java.lang.Object> searchbyAuthor(java.lang.String author) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        org.me.image.ImageWS port = service.getImageWSPort();
        return port.searchbyAuthor(author);
    }

    private java.util.List<java.lang.Object> searchbyCreaDate(java.lang.String creaDate) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        org.me.image.ImageWS port = service.getImageWSPort();
        return port.searchbyCreaDate(creaDate);
    }

    private java.util.List<java.lang.Object> searchbyKeywords(java.lang.String keywords) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        org.me.image.ImageWS port = service.getImageWSPort();
        return port.searchbyKeywords(keywords);
    }

    private java.util.List<java.lang.Object> searchbyTitle(java.lang.String title) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        org.me.image.ImageWS port = service.getImageWSPort();
        return port.searchbyTitle(title);
    }

}
