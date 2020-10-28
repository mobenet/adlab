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
 * @author mo
 */
@WebServlet(name = "modificarImagen", urlPatterns = {"/modificarImagen"})
public class modificarImagen extends HttpServlet {

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
        HttpSession ses = request.getSession(false);
        if(ses.getAttribute("user") == null) response.sendRedirect("login.jsp");
        else {
            String autor = (String) ses.getAttribute("autor");
            String fStorage = (String) ses.getAttribute("fStorage");
            int id = Integer.parseInt(ses.getAttribute("imageId").toString()); 

            response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                Image img = new Image();
                img.setId(id);
                img.setTitle(request.getParameter("titulo"));
                img.setDescription(request.getParameter("descripcion"));
                img.setKeywords(request.getParameter("clave"));
                img.setAuthor(autor);
                img.setStorageDate(fStorage);
                img.setCreationDate(request.getParameter("fechaC"));
                img.setFileName(request.getParameter("filename"));
                
                int idI = modifyImage(img);
                out.println("<p>Se ha modificado la imagen exitosamente</p>");
                out.println("<a href=\"menu.jsp\">Vuelve al Menu</a>");
            } catch(Exception e){
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

    private int modifyImage(org.me.image.Image image) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        org.me.image.ImageWS port = service.getImageWSPort();
        return port.modifyImage(image);
    }

}