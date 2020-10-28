/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.me.image.client;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.WebServiceRef;
import org.me.image.Image;
import org.me.image.ImageWS_Service;

/**
 *
 * @author Samuel
 */
@WebServlet(name = "formModificarImagen", urlPatterns = {"/formModificarImagen"})
public class formModificarImagen extends HttpServlet {

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
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            HttpSession ses = request.getSession(false);
            String user = (String) ses.getAttribute("user");
            
            if (user == null) {
                response.sendRedirect("login.jsp");
            } else {
                try {
                    out.println("<table border=1>"
                            + "<tr>"
                            + "<th>Titulo</th>"
                            + "<th>Descripcion</th>"
                            + "<th>Palabras Clave</th>"
                            + "<th>Autor</th>"
                            + "<th>Fecha de creacion</th>"
                            + "<th>Fecha de subida</th>"
                            + "<th>Nombre del archivo</th>"
                            + "</tr>");
                    

                    int id = Integer.parseInt(ses.getAttribute("imageId").toString()); 
                  
                    Image img = searchbyId(id);

                    out.println("<tr><td>" + img.getTitle() + "</td>");
                    out.println("<td>" + img.getDescription() + "</td>");
                    out.println("<td>" + img.getKeywords() + "</td>");
                    out.println("<td>" + img.getAuthor() + "</td>");
                    out.println("<td>" + img.getCreationDate() + "</td>");
                    out.println("<td>" + img.getStorageDate() + "</td>");
                    out.println("<td><a href=image.jsp?name=" + img.getFileName() + "&id=" + id + ">" + img.getFileName() + "</a>");
                    
                    out.println(" </td> </tr>");
                    
                    out.println("</table><br><br>");//<a href=\"menu.jsp\"> Vuelve al menu</a>-->");
               
                    String autor = img.getAuthor();
                    String fStorage = img.getStorageDate(); 
                    String fileN = img.getFileName();
                    String title = img.getTitle();
                    String desc = img.getDescription();
                    String key = img.getKeywords(); 
                    String crea = img.getCreationDate();
                    ses.setAttribute("autor", autor);
                    ses.setAttribute("fStorage", fStorage);
                    ses.setAttribute("fileN", fileN);
                    ses.setAttribute("title", title);
                    ses.setAttribute("desc", desc);
                    ses.setAttribute("crea", crea);
                    ses.setAttribute("key", key);
                    out.println("<h1>Modifica tu imagen como desees</h1>"
                    + "<form method=\"POST\" action = \"modificarImagen\">"
                    + "Titulo: <input type = \"text\" name = \"titulo\"  /> <br> <br>"
                    + "Descripción: <input type = \"text\" name = \"descripcion\"  /> <br> <br>"
                    + "Palabras clave: <input type=\"text\" name = \"clave\"  /> <br> <br>"
                    + "Fecha creación: <input type=date name = \"fechaC\"  /> <br> <br>"
                    + "Autor: "+autor+"<br><br>"      
                    + "Fecha guardado: " + fStorage +" <br> <br>"
                    + "Nombre archivo: " + fileN + " <br><br>"
                    + "<input type = \"submit\" name = \"submit\" value = \"Modificar\" /> </form> <br> <br>"
                    + "<a href = \"menu.jsp\"> Vuelve al Menú</a>");
                    
                } catch (NumberFormatException e) {
                    System.err.println(e.getMessage());
                }
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
        processRequest(request, response);
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
        processRequest(request, response);
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

    private Image searchbyId(int id) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        org.me.image.ImageWS port = service.getImageWSPort();
        return port.searchbyId(id);
    }
}
