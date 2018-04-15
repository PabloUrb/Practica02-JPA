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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.incidenciasEJB;

/**
 *
 * @author pablourbano
 */
public class cambiarContra extends HttpServlet {
    
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
        try {
                Empleado e = (Empleado) request.getSession().getAttribute("empleado");
                String passAnt = request.getParameter("actual");
                String pas1 = request.getParameter("contra1");
                String pas2 = request.getParameter("contra2");
                if (passAnt.equals(e.getPassword())) {
                    if (pas1.equals(pas2)) {
                        e.setPassword(pas1);
                        miEjb.cambiarContra(e);
                        request.setAttribute("ok", "1");
                        request.setAttribute("status", "Contraseña cambiada");
                        request.getRequestDispatcher("/final.jsp").forward(request, response);
                    } else {
                        throw new exceptionJPA("La contraseña y la confirmacion no coinciden");
                    }
                } else {
                    throw new exceptionJPA("Contraseña no coincide");
                }
            } catch (exceptionJPA ex) {
                request.setAttribute("ok", "0");
                request.setAttribute("status", ex.getMessage());
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
