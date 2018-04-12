/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entities.Empleado;
import entities.Historial;
import entities.Incidencia;
import exception.exceptionJPA;
import static java.lang.Math.log;
import static java.lang.StrictMath.log;
import java.sql.ResultSet;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.validation.ConstraintViolationException;
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
            em.close();
            throw new exceptionJPA("Este usuario no existe");

        }
        em.close();
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

    public List<Incidencia> listadoIncidencias() {
        Query q;
        q = emf.createEntityManager().createNamedQuery("Incidencia.findAll");
        return q.getResultList();

    }
    public List<Empleado> listadoEmpleados() {
        Query q;
        q = emf.createEntityManager().createNamedQuery("Empleado.findAll");
        return q.getResultList();

    }

    public void crearIncidencias(Incidencia e) throws exceptionJPA {
        EntityManager em = emf.createEntityManager();
        //Comprobamos si ya existe un empleado
        if (e.getDestino() != null && e.getDetalle() != null && e.getFechahora() != null && e.getIdincidencia() != null && e.getOrigen() != null && e.getTipo() != null) {
            em.persist(e);
            em.close();
        } else {
            System.out.println(e.getDestino());
            em.close();
        }
    }

    public Integer maximoIdIncidencia() {
        Integer a;
        Query q;
        q = emf.createEntityManager().createQuery("SELECT max(i.idincidencia) FROM Incidencia i");
        
        if (q.getSingleResult() == null) {
            a = 0;
            return a;
        } else {
            a= (int) q.getSingleResult();
            return a;
        }
    }
    public void crearEvento(Historial h) throws exceptionJPA{
        EntityManager em = emf.createEntityManager();
        //Comprovamos si existe empleado
        Empleado aux = em.find(Empleado.class, h.getEmpleado().getNombreusuario());
        if (aux == null) {
            em.close();
            throw new exceptionJPA("El empleado no existe");
        }
        em.persist(h);
        em.close(); 
    }
}
