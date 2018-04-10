/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.Empleado;
import exception.exceptionJPA;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.incidenciasEJB;

/**
 *
 * @author pablourbano
 */
public class validarEmpleado extends HttpServlet {

    @EJB
    incidenciasEJB miEjb;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws exception.exceptionJPA
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws exceptionJPA, IOException, ServletException {

        //Recorremos lass variables del formulario
        String nombreusuario = request.getParameter("nombreUsuario");
        String password = request.getParameter("password");

        if (miEjb.validarEmpleado(nombreusuario, password) == true) {
            Empleado empleado = miEjb.buscarEmpleado(nombreusuario);
            if (empleado.getNombreusuario().equals("admin")) { 
                request.getSession(true).setAttribute("empleado", empleado);
                response.sendRedirect(request.getContextPath() + "/menuAdmin.jsp");
            } else {
                request.getSession(true).setAttribute("empleado", empleado);
                response.sendRedirect(request.getContextPath() + "/menuUsuario.jsp");
            }
        } else {
            request.getRequestDispatcher("/final.jsp").forward(request, response);
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
        } catch (exceptionJPA ex) {
            Logger.getLogger(validarEmpleado.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (exceptionJPA ex) {
            Logger.getLogger(validarEmpleado.class.getName()).log(Level.SEVERE, null, ex);
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

}
