/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entities.Empleado;
import exception.exceptionJPA;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

/**
 *
 * @author pablourbano
 */
@Stateless
public class incidenciasEJB {
    @PersistenceUnit EntityManagerFactory emf;
    
    public void altaEmpleado(Empleado c) throws exceptionJPA{
        EntityManager em = emf.createEntityManager();
        //Comprobamos si ya existe un cocinero
        Empleado aux = em.find(Empleado.class, c.getNombreusuario());
        if(aux != null){
            em.close();
            throw new exceptionJPA("Ya existe un empleado con este nombre");
        }
        em.persist(c);
        em.close();
    }
}
