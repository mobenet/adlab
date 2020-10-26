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
import org.me.image.ImageWS_Service;

/**
 *
 * @author Samuel
 */
@WebServlet(name = "formRegistrarImagen", urlPatterns = {"/formRegistrarImagen"})
public class formRegistrarImagen extends HttpServlet {

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
        HttpSession ses = request.getSession(false);
        if (ses.getAttribute("user") == null) {
            response.sendRedirect("login.jsp");
        }
        List<String> authors = getUsers();
        try (PrintWriter out = response.getWriter()) {
            out.println("<h1>Inserta tu imagen en nuestra Base de datos</h1>"
                    + "<form method=\"POST\" action = \"registrarImagen\" enctype=\"multipart/form-data\">"
                    + "Titulo: <input type = \"text\" name = \"titulo\" required /> <br> <br>"
                    + "Descripción: <input type = \"text\" name = \"descripcion\" required /> <br> <br>"
                    + "Palabras clave: <input type=\"text\" name = \"clave\" required /> <br> <br>"
                    + "Autor: <select name=\"author\" required>");
            for(String autor:authors){
                out.println("<option value=\""+autor+"\">"+autor+"</option>");
            }
            out.println("</select> <br> <br>"
                    + "Fecha creación: <input type=\"text\" name = \"fechaC\" required /> <br> <br>"
                    + " Archivo: <input   type = \"file\" name = \"imagen\" /> <br> <br>"
                    + "<input type = \"submit\" name = \"submit\" value = \"Registrar\" /> </form> <br> <br>"
                    + "<a href = \"menu.jsp\"> Vuelve al Menú</a>");
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

    private java.util.List<java.lang.String> getUsers() {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        org.me.image.ImageWS port = service.getImageWSPort();
        return port.getUsers();
    }

}
