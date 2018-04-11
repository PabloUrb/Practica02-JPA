/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entities.Empleado;
import entities.Incidencia;
import exception.exceptionJPA;
import java.sql.ResultSet;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.validation.constraints.AssertFalse;

/**
 *
 * @author pablourbano
 */
@Stateless
public class incidenciasEJB {

    @PersistenceUnit
    EntityManagerFactory emf;

    public void altaEmpleado(Empleado e) throws exceptionJPA {
        EntityManager em = emf.createEntityManager();
        //Comprobamos si ya existe un empleado
        Empleado aux = em.find(Empleado.class, e.getNombreusuario());
        if (aux != null) {
            em.close();
            throw new exceptionJPA("Ya existe un empleado con este nombre");
        }
        em.persist(e);
        em.close();
    }

    public boolean validarEmpleado(String nombre, String pass) throws exceptionJPA {
        EntityManager em = emf.createEntityManager();
        //comprobamos que exista el empleado
        Empleado aux = em.find(Empleado.class, nombre);
        if (aux != null) {
            if (pass.equals(aux.getPassword())) {
                em.close();
                return true;
            }
        } else {
            throw new exceptionJPA("Este usuario no existe");

        }
        return false;
    }

    public Empleado buscarEmpleado(String nombre) {
        EntityManager em = emf.createEntityManager();
        Empleado e = em.find(Empleado.class, nombre);
        em.close();
        return e;
    }

    public List<Incidencia> incidenciasEnviadas(Empleado e) {
        Query q;
        q = emf.createEntityManager().createQuery("SELECT i FROM Incidencia i WHERE i.origen = :empleado");
        q.setParameter("empleado", e);
        return q.getResultList();
        
    }
    public List<Incidencia> incidenciasRecividas(Empleado e) {
        Query q;
        q = emf.createEntityManager().createQuery("SELECT i FROM Incidencia i WHERE i.destino = :empleado");
        q.setParameter("empleado", e);
        return q.getResultList();
        
    }
}
