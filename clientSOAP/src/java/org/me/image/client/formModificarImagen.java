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
import org.me.image.ImageBulkService_Service;

/**
 *
 * @author Samuel
 */
@WebServlet(name = "formModificarImagen", urlPatterns = {"/formModificarImagen"})
public class formModificarImagen extends HttpServlet {

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

                    out.println("<h1>Modifica tu imagen como desees</h1>"
                    + "<form method=\"POST\" action = \"modificarImagen\">"
                    + "Titulo: <input type = \"text\" name = \"titulo\"  /> <br> <br>"
                    + "Descripción: <input type = \"text\" name = \"descripcion\"  /> <br> <br>"
                    + "Palabras clave: <input type=\"text\" name = \"clave\"  /> <br> <br>"
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
}
