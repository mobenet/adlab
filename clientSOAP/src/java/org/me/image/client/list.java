/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.me.image.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.WebServiceRef;
import org.me.image.Image;
import org.me.image.ImageBulkService_Service;

/**
 *
 * @author Samuel
 */
@WebServlet(name = "list", urlPatterns = {"/list"})
public class list extends HttpServlet {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/servidorSOAP/ImageBulkService.wsdl")
    private ImageBulkService_Service service;

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
                            + "<th>Nombre del archivo</th>"
                            + "</tr>");

                    List<Object> list = listImages();
                    for (Object o : list) {
                        Image img = (Image) o;
                        String filename = img.getFilename();
                        String autor = img.getAuth();
                        int id = img.getId();

                        out.println("<tr><td>" + img.getTitle() + "</td>");
                        out.println("<td>" + img.getDescription() + "</td>");
                        out.println("<td>" + img.getKwords() + "</td>");
                        out.println("<td>" + autor + "</td>");
                        out.println("<td><a href=showImage?id=" + id + ">" + filename + "</a>");
                        if (autor.equals(user)) {
                            out.println("<form action = selectImage method = \"POST\">"
                                    + "<input type = \"hidden\" value = \"" + filename + "\" name = \"name\" />"
                                    + "<input type = \"hidden\" value = " + id + " name = \"id\" />"
                                    + "<input type = \"submit\" value = \"Modificar\" name = \"action\" />"
                                    + "<input type = \"submit\" value = \"Eliminar\" name = \"action\" /> </form>");
                        }
                        out.println(" </td> </tr>");
                    }
                    out.println("</table><br><br><a href=\"menu.jsp\"> Vuelve al menu</a>");
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
        }
    }

    private java.util.List<java.lang.Object> listImages() {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        org.me.image.ImageBulkService port = service.getImageBulkServicePort();
        return port.listImage();
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

}
