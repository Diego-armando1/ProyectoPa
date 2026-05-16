/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import Clases.Historial;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Clases.Paquete;
import Clases.Trabajador;
import Logica.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author hp
 */
public class HistorialJpaController implements Serializable {

    public HistorialJpaController() {
        this.emf = Persistence.createEntityManagerFactory("ProyectoPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Historial historial) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Paquete idPaquete = historial.getIdPaquete();
            if (idPaquete != null) {
                idPaquete = em.getReference(idPaquete.getClass(), idPaquete.getIdPaquete());
                historial.setIdPaquete(idPaquete);
            }
            Trabajador idTrabajador = historial.getIdTrabajador();
            if (idTrabajador != null) {
                idTrabajador = em.getReference(idTrabajador.getClass(), idTrabajador.getIdTrabajador());
                historial.setIdTrabajador(idTrabajador);
            }
            em.persist(historial);
            if (idPaquete != null) {
                idPaquete.getHistorialCollection().add(historial);
                idPaquete = em.merge(idPaquete);
            }
            if (idTrabajador != null) {
                idTrabajador.getHistorialCollection().add(historial);
                idTrabajador = em.merge(idTrabajador);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Historial historial) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Historial persistentHistorial = em.find(Historial.class, historial.getIdHistorial());
            Paquete idPaqueteOld = persistentHistorial.getIdPaquete();
            Paquete idPaqueteNew = historial.getIdPaquete();
            Trabajador idTrabajadorOld = persistentHistorial.getIdTrabajador();
            Trabajador idTrabajadorNew = historial.getIdTrabajador();
            if (idPaqueteNew != null) {
                idPaqueteNew = em.getReference(idPaqueteNew.getClass(), idPaqueteNew.getIdPaquete());
                historial.setIdPaquete(idPaqueteNew);
            }
            if (idTrabajadorNew != null) {
                idTrabajadorNew = em.getReference(idTrabajadorNew.getClass(), idTrabajadorNew.getIdTrabajador());
                historial.setIdTrabajador(idTrabajadorNew);
            }
            historial = em.merge(historial);
            if (idPaqueteOld != null && !idPaqueteOld.equals(idPaqueteNew)) {
                idPaqueteOld.getHistorialCollection().remove(historial);
                idPaqueteOld = em.merge(idPaqueteOld);
            }
            if (idPaqueteNew != null && !idPaqueteNew.equals(idPaqueteOld)) {
                idPaqueteNew.getHistorialCollection().add(historial);
                idPaqueteNew = em.merge(idPaqueteNew);
            }
            if (idTrabajadorOld != null && !idTrabajadorOld.equals(idTrabajadorNew)) {
                idTrabajadorOld.getHistorialCollection().remove(historial);
                idTrabajadorOld = em.merge(idTrabajadorOld);
            }
            if (idTrabajadorNew != null && !idTrabajadorNew.equals(idTrabajadorOld)) {
                idTrabajadorNew.getHistorialCollection().add(historial);
                idTrabajadorNew = em.merge(idTrabajadorNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = historial.getIdHistorial();
                if (findHistorial(id) == null) {
                    throw new NonexistentEntityException("The historial with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Historial historial;
            try {
                historial = em.getReference(Historial.class, id);
                historial.getIdHistorial();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The historial with id " + id + " no longer exists.", enfe);
            }
            Paquete idPaquete = historial.getIdPaquete();
            if (idPaquete != null) {
                idPaquete.getHistorialCollection().remove(historial);
                idPaquete = em.merge(idPaquete);
            }
            Trabajador idTrabajador = historial.getIdTrabajador();
            if (idTrabajador != null) {
                idTrabajador.getHistorialCollection().remove(historial);
                idTrabajador = em.merge(idTrabajador);
            }
            em.remove(historial);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Historial> findHistorialEntities() {
        return findHistorialEntities(true, -1, -1);
    }

    public List<Historial> findHistorialEntities(int maxResults, int firstResult) {
        return findHistorialEntities(false, maxResults, firstResult);
    }

    private List<Historial> findHistorialEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Historial.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Historial findHistorial(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Historial.class, id);
        } finally {
            em.close();
        }
    }

    public int getHistorialCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Historial> rt = cq.from(Historial.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
