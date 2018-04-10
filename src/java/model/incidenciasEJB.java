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
    
    public void altaEmpleado(Empleado e) throws exceptionJPA{
        EntityManager em = emf.createEntityManager();
        //Comprobamos si ya existe un empleado
        Empleado aux = em.find(Empleado.class, e.getNombreusuario());
        if(aux != null){
            em.close();
            throw new exceptionJPA("Ya existe un empleado con este nombre");
        }
        em.persist(e);
        em.close();
    }
    public boolean validarEmpleado(String nombre, String pass) throws exceptionJPA{
        EntityManager em = emf.createEntityManager();
        //comprobamos que exista el empleado
        Empleado aux = em.find(Empleado.class, nombre);
        if(aux!=null){
            if(pass.equals(aux.getPassword())){
                em.close();
                return true;
            }
        }else{
           throw new exceptionJPA("Este usuario no existe");
           
        }
        return false; 
    }
    public Empleado buscarEmpleado(String nombre){
        EntityManager em = emf.createEntityManager();
        Empleado e = em.find(Empleado.class, nombre);
        em.close();
        return e;
    }
}
