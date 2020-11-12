/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.me.image.client;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.xml.ws.WebServiceRef;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.xml.ws.soap.MTOMFeature;

import org.me.image.ImageWS_Service;
import org.me.image.Image;

/**
 *
 * @author mo
 */
@WebServlet(name = "registrarImagen", urlPatterns = {"/registrarImagen"})
@MultipartConfig
public class registrarImagen extends HttpServlet {

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
     * @throws java.sql.SQLException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {

        response.setContentType("text/html;charset=UTF-8");

        HttpSession ses = request.getSession(false);
        if (ses.getAttribute("user") == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        //create path components to save the file
        final Part filePart = request.getPart("imagen");
        final String fileName = getFileName(filePart);
        if (fileName == null || fileName.isEmpty()) {
            throw new FileNotFoundException("No has especificado el archivo a subir");
        }

        Image img = new Image();
        img.setTitle(request.getParameter("titulo"));
        img.setAuthor(request.getParameter("author"));
        img.setCreationDate(request.getParameter("fechaC"));
        img.setKeywords(request.getParameter("clave"));
        img.setDescription(request.getParameter("descripcion"));
        img.setFileName(fileName);
        byte[] imageBytes = readImageBytes(filePart);
        if(imageBytes == null){
            response.sendRedirect("error.jsp");
            System.err.println("No se ha podido leer la imagen correctamente");
        } else img.setBytes(imageBytes);
        registerImage(img);
        try (PrintWriter out = response.getWriter()) {
            out.println("<p>Se ha registrado la imagen exitosamente</p>");
            out.println("<a href=\"menu.jsp\">Vuelve al Menu</a>");
        }

    }

    private byte[] readImageBytes(Part imagePart) {

        byte[] imageBytes = new byte[(int) imagePart.getSize()];
        try (InputStream is = imagePart.getInputStream()) {
            is.read(imageBytes);
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return null;
        }
        return imageBytes;
    }

    private String getFileName(final Part part) {
        final String partHeader = part.getHeader("content-disposition");

        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(
                        content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
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
        } catch (SQLException ex) {
            Logger.getLogger(registrarImagen.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (SQLException ex) {
            Logger.getLogger(registrarImagen.class.getName()).log(Level.SEVERE, null, ex);
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

    private int registerImage(org.me.image.Image image) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        org.me.image.ImageWS port = service.getImageWSPort(new MTOMFeature(10240));
        return port.registrerImage(image);
    }
}
