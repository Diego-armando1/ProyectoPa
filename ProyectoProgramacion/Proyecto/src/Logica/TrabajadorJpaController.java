/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Clases.Historial;
import Clases.Trabajador;
import Logica.exceptions.IllegalOrphanException;
import Logica.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author hp
 */
public class TrabajadorJpaController implements Serializable {

    public TrabajadorJpaController() {
        this.emf = Persistence.createEntityManagerFactory("ProyectoPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Trabajador trabajador) {
        if (trabajador.getHistorialCollection() == null) {
            trabajador.setHistorialCollection(new ArrayList<Historial>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Historial> attachedHistorialCollection = new ArrayList<Historial>();
            for (Historial historialCollectionHistorialToAttach : trabajador.getHistorialCollection()) {
                historialCollectionHistorialToAttach = em.getReference(historialCollectionHistorialToAttach.getClass(), historialCollectionHistorialToAttach.getIdHistorial());
                attachedHistorialCollection.add(historialCollectionHistorialToAttach);
            }
            trabajador.setHistorialCollection(attachedHistorialCollection);
            em.persist(trabajador);
            for (Historial historialCollectionHistorial : trabajador.getHistorialCollection()) {
                Trabajador oldIdTrabajadorOfHistorialCollectionHistorial = historialCollectionHistorial.getIdTrabajador();
                historialCollectionHistorial.setIdTrabajador(trabajador);
                historialCollectionHistorial = em.merge(historialCollectionHistorial);
                if (oldIdTrabajadorOfHistorialCollectionHistorial != null) {
                    oldIdTrabajadorOfHistorialCollectionHistorial.getHistorialCollection().remove(historialCollectionHistorial);
                    oldIdTrabajadorOfHistorialCollectionHistorial = em.merge(oldIdTrabajadorOfHistorialCollectionHistorial);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Trabajador trabajador) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Trabajador persistentTrabajador = em.find(Trabajador.class, trabajador.getIdTrabajador());
            Collection<Historial> historialCollectionOld = persistentTrabajador.getHistorialCollection();
            Collection<Historial> historialCollectionNew = trabajador.getHistorialCollection();
            List<String> illegalOrphanMessages = null;
            for (Historial historialCollectionOldHistorial : historialCollectionOld) {
                if (!historialCollectionNew.contains(historialCollectionOldHistorial)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Historial " + historialCollectionOldHistorial + " since its idTrabajador field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Historial> attachedHistorialCollectionNew = new ArrayList<Historial>();
            for (Historial historialCollectionNewHistorialToAttach : historialCollectionNew) {
                historialCollectionNewHistorialToAttach = em.getReference(historialCollectionNewHistorialToAttach.getClass(), historialCollectionNewHistorialToAttach.getIdHistorial());
                attachedHistorialCollectionNew.add(historialCollectionNewHistorialToAttach);
            }
            historialCollectionNew = attachedHistorialCollectionNew;
            trabajador.setHistorialCollection(historialCollectionNew);
            trabajador = em.merge(trabajador);
            for (Historial historialCollectionNewHistorial : historialCollectionNew) {
                if (!historialCollectionOld.contains(historialCollectionNewHistorial)) {
                    Trabajador oldIdTrabajadorOfHistorialCollectionNewHistorial = historialCollectionNewHistorial.getIdTrabajador();
                    historialCollectionNewHistorial.setIdTrabajador(trabajador);
                    historialCollectionNewHistorial = em.merge(historialCollectionNewHistorial);
                    if (oldIdTrabajadorOfHistorialCollectionNewHistorial != null && !oldIdTrabajadorOfHistorialCollectionNewHistorial.equals(trabajador)) {
                        oldIdTrabajadorOfHistorialCollectionNewHistorial.getHistorialCollection().remove(historialCollectionNewHistorial);
                        oldIdTrabajadorOfHistorialCollectionNewHistorial = em.merge(oldIdTrabajadorOfHistorialCollectionNewHistorial);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = trabajador.getIdTrabajador();
                if (findTrabajador(id) == null) {
                    throw new NonexistentEntityException("The trabajador with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Trabajador trabajador;
            try {
                trabajador = em.getReference(Trabajador.class, id);
                trabajador.getIdTrabajador();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The trabajador with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Historial> historialCollectionOrphanCheck = trabajador.getHistorialCollection();
            for (Historial historialCollectionOrphanCheckHistorial : historialCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Trabajador (" + trabajador + ") cannot be destroyed since the Historial " + historialCollectionOrphanCheckHistorial + " in its historialCollection field has a non-nullable idTrabajador field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(trabajador);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Trabajador> findTrabajadorEntities() {
        return findTrabajadorEntities(true, -1, -1);
    }

    public List<Trabajador> findTrabajadorEntities(int maxResults, int firstResult) {
        return findTrabajadorEntities(false, maxResults, firstResult);
    }

    private List<Trabajador> findTrabajadorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Trabajador.class));
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

    public Trabajador findTrabajador(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Trabajador.class, id);
        } finally {
            em.close();
        }
    }

    public int getTrabajadorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Trabajador> rt = cq.from(Trabajador.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
