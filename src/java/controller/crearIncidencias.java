/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.Empleado;
import entities.Historial;
import entities.Incidencia;
import exception.exceptionJPA;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
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
public class crearIncidencias extends HttpServlet {

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
        Empleado em = (Empleado) request.getSession().getAttribute("empleado");
        String destino = request.getParameter("destino");
        Empleado dest = miEjb.buscarEmpleado(destino);
        String tipo = request.getParameter("tipo");
        String detalle = request.getParameter("detalle");
        int max = miEjb.maximoIdIncidencia();
        int max1=max+1;
        String fechahora = ZonedDateTime.now().toString();
        
        
        Incidencia c = new Incidencia(max1, fechahora, detalle, tipo, dest ,em);
        try {
            if(c.getTipo().equals("urgente")){
                Historial hist = new Historial(null, "U", ZonedDateTime.now().toString(), em);
                miEjb.crearEvento(hist);
            }
              miEjb.crearIncidencias(c);
            //Si el alta ha ido bien devolvemos msg ok
            request.setAttribute("status", "Creada inciden cia");    
            
            
        } catch (exceptionJPA ex) {
            //Devolvemos mensaje de la excepcion a la vista
            request.setAttribute("status", ex.getMessage());
        }
        //redirigimos a la vista final.jsp en este caso
        request.getRequestDispatcher("/final.jsp").forward(request, response);
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
