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
import Clases.Ciudad;
import Clases.Provincia;
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
public class ProvinciaJpaController implements Serializable {

    public ProvinciaJpaController() {
        this.emf = Persistence.createEntityManagerFactory("ProyectoPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Provincia provincia) {
        if (provincia.getCiudadCollection() == null) {
            provincia.setCiudadCollection(new ArrayList<Ciudad>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Ciudad> attachedCiudadCollection = new ArrayList<Ciudad>();
            for (Ciudad ciudadCollectionCiudadToAttach : provincia.getCiudadCollection()) {
                ciudadCollectionCiudadToAttach = em.getReference(ciudadCollectionCiudadToAttach.getClass(), ciudadCollectionCiudadToAttach.getIdCiudad());
                attachedCiudadCollection.add(ciudadCollectionCiudadToAttach);
            }
            provincia.setCiudadCollection(attachedCiudadCollection);
            em.persist(provincia);
            for (Ciudad ciudadCollectionCiudad : provincia.getCiudadCollection()) {
                Provincia oldIdProvinciaOfCiudadCollectionCiudad = ciudadCollectionCiudad.getIdProvincia();
                ciudadCollectionCiudad.setIdProvincia(provincia);
                ciudadCollectionCiudad = em.merge(ciudadCollectionCiudad);
                if (oldIdProvinciaOfCiudadCollectionCiudad != null) {
                    oldIdProvinciaOfCiudadCollectionCiudad.getCiudadCollection().remove(ciudadCollectionCiudad);
                    oldIdProvinciaOfCiudadCollectionCiudad = em.merge(oldIdProvinciaOfCiudadCollectionCiudad);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Provincia provincia) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Provincia persistentProvincia = em.find(Provincia.class, provincia.getIdProvincia());
            Collection<Ciudad> ciudadCollectionOld = persistentProvincia.getCiudadCollection();
            Collection<Ciudad> ciudadCollectionNew = provincia.getCiudadCollection();
            List<String> illegalOrphanMessages = null;
            for (Ciudad ciudadCollectionOldCiudad : ciudadCollectionOld) {
                if (!ciudadCollectionNew.contains(ciudadCollectionOldCiudad)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Ciudad " + ciudadCollectionOldCiudad + " since its idProvincia field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Ciudad> attachedCiudadCollectionNew = new ArrayList<Ciudad>();
            for (Ciudad ciudadCollectionNewCiudadToAttach : ciudadCollectionNew) {
                ciudadCollectionNewCiudadToAttach = em.getReference(ciudadCollectionNewCiudadToAttach.getClass(), ciudadCollectionNewCiudadToAttach.getIdCiudad());
                attachedCiudadCollectionNew.add(ciudadCollectionNewCiudadToAttach);
            }
            ciudadCollectionNew = attachedCiudadCollectionNew;
            provincia.setCiudadCollection(ciudadCollectionNew);
            provincia = em.merge(provincia);
            for (Ciudad ciudadCollectionNewCiudad : ciudadCollectionNew) {
                if (!ciudadCollectionOld.contains(ciudadCollectionNewCiudad)) {
                    Provincia oldIdProvinciaOfCiudadCollectionNewCiudad = ciudadCollectionNewCiudad.getIdProvincia();
                    ciudadCollectionNewCiudad.setIdProvincia(provincia);
                    ciudadCollectionNewCiudad = em.merge(ciudadCollectionNewCiudad);
                    if (oldIdProvinciaOfCiudadCollectionNewCiudad != null && !oldIdProvinciaOfCiudadCollectionNewCiudad.equals(provincia)) {
                        oldIdProvinciaOfCiudadCollectionNewCiudad.getCiudadCollection().remove(ciudadCollectionNewCiudad);
                        oldIdProvinciaOfCiudadCollectionNewCiudad = em.merge(oldIdProvinciaOfCiudadCollectionNewCiudad);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = provincia.getIdProvincia();
                if (findProvincia(id) == null) {
                    throw new NonexistentEntityException("The provincia with id " + id + " no longer exists.");
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
            Provincia provincia;
            try {
                provincia = em.getReference(Provincia.class, id);
                provincia.getIdProvincia();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The provincia with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Ciudad> ciudadCollectionOrphanCheck = provincia.getCiudadCollection();
            for (Ciudad ciudadCollectionOrphanCheckCiudad : ciudadCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Provincia (" + provincia + ") cannot be destroyed since the Ciudad " + ciudadCollectionOrphanCheckCiudad + " in its ciudadCollection field has a non-nullable idProvincia field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(provincia);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Provincia> findProvinciaEntities() {
        return findProvinciaEntities(true, -1, -1);
    }

    public List<Provincia> findProvinciaEntities(int maxResults, int firstResult) {
        return findProvinciaEntities(false, maxResults, firstResult);
    }

    private List<Provincia> findProvinciaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Provincia.class));
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

    public Provincia findProvincia(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Provincia.class, id);
        } finally {
            em.close();
        }
    }

    public int getProvinciaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Provincia> rt = cq.from(Provincia.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
