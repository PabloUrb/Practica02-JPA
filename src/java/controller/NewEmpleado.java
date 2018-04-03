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
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.incidenciasEJB;

/**
 *
 * @author pablourbano
 */
@WebServlet(name = "NewEmpleado2", urlPatterns = {"/NewEmpleado2"})
public class NewEmpleado extends HttpServlet {

    @EJB incidenciasEJB miEjb;
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
        //Recorremos lass variables del formulario
        String nombreusuario = request.getParameter("nombreUsuario");
        String nombrecompleto = request.getParameter("nombreCompleto");
        String password = request.getParameter("password");
        String password2 = request.getParameter("password2");
        String telefono = request.getParameter("telefono");
        String ciudad = request.getParameter("ciudad");
        
        
        Empleado c = new Empleado(nombreusuario, password, nombrecompleto, telefono, ciudad);
        try {
            if(!password.equals(password2)){
                throw new exceptionJPA("Las contraseñas no coinciden");
            }else{
              miEjb.altaEmpleado(c);
            //Si el alta ha ido bien devolvemos msg ok
            request.setAttribute("status", "Empleado dado de alta");    
            }
            
        } catch (exceptionJPA ex) {
            //Devolvemos mensaje de la excepcion a la vista
            request.setAttribute("status", ex.getMessage());
        }
        //redirigimos a la vista final.jsp en este caso
        request.getRequestDispatcher("/final.jsp").forward(request,response);
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
