/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import Clases.Ciudad;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Clases.Provincia;
import Clases.Paquete;
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
public class CiudadJpaController implements Serializable {

    public CiudadJpaController() {
        this.emf = Persistence.createEntityManagerFactory("ProyectoPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Ciudad ciudad) {
        if (ciudad.getPaqueteCollection() == null) {
            ciudad.setPaqueteCollection(new ArrayList<Paquete>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Provincia idProvincia = ciudad.getIdProvincia();
            if (idProvincia != null) {
                idProvincia = em.getReference(idProvincia.getClass(), idProvincia.getIdProvincia());
                ciudad.setIdProvincia(idProvincia);
            }
            Collection<Paquete> attachedPaqueteCollection = new ArrayList<Paquete>();
            for (Paquete paqueteCollectionPaqueteToAttach : ciudad.getPaqueteCollection()) {
                paqueteCollectionPaqueteToAttach = em.getReference(paqueteCollectionPaqueteToAttach.getClass(), paqueteCollectionPaqueteToAttach.getIdPaquete());
                attachedPaqueteCollection.add(paqueteCollectionPaqueteToAttach);
            }
            ciudad.setPaqueteCollection(attachedPaqueteCollection);
            em.persist(ciudad);
            if (idProvincia != null) {
                idProvincia.getCiudadCollection().add(ciudad);
                idProvincia = em.merge(idProvincia);
            }
            for (Paquete paqueteCollectionPaquete : ciudad.getPaqueteCollection()) {
                Ciudad oldIdCiudadOfPaqueteCollectionPaquete = paqueteCollectionPaquete.getIdCiudad();
                paqueteCollectionPaquete.setIdCiudad(ciudad);
                paqueteCollectionPaquete = em.merge(paqueteCollectionPaquete);
                if (oldIdCiudadOfPaqueteCollectionPaquete != null) {
                    oldIdCiudadOfPaqueteCollectionPaquete.getPaqueteCollection().remove(paqueteCollectionPaquete);
                    oldIdCiudadOfPaqueteCollectionPaquete = em.merge(oldIdCiudadOfPaqueteCollectionPaquete);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Ciudad ciudad) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ciudad persistentCiudad = em.find(Ciudad.class, ciudad.getIdCiudad());
            Provincia idProvinciaOld = persistentCiudad.getIdProvincia();
            Provincia idProvinciaNew = ciudad.getIdProvincia();
            Collection<Paquete> paqueteCollectionOld = persistentCiudad.getPaqueteCollection();
            Collection<Paquete> paqueteCollectionNew = ciudad.getPaqueteCollection();
            List<String> illegalOrphanMessages = null;
            for (Paquete paqueteCollectionOldPaquete : paqueteCollectionOld) {
                if (!paqueteCollectionNew.contains(paqueteCollectionOldPaquete)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Paquete " + paqueteCollectionOldPaquete + " since its idCiudad field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idProvinciaNew != null) {
                idProvinciaNew = em.getReference(idProvinciaNew.getClass(), idProvinciaNew.getIdProvincia());
                ciudad.setIdProvincia(idProvinciaNew);
            }
            Collection<Paquete> attachedPaqueteCollectionNew = new ArrayList<Paquete>();
            for (Paquete paqueteCollectionNewPaqueteToAttach : paqueteCollectionNew) {
                paqueteCollectionNewPaqueteToAttach = em.getReference(paqueteCollectionNewPaqueteToAttach.getClass(), paqueteCollectionNewPaqueteToAttach.getIdPaquete());
                attachedPaqueteCollectionNew.add(paqueteCollectionNewPaqueteToAttach);
            }
            paqueteCollectionNew = attachedPaqueteCollectionNew;
            ciudad.setPaqueteCollection(paqueteCollectionNew);
            ciudad = em.merge(ciudad);
            if (idProvinciaOld != null && !idProvinciaOld.equals(idProvinciaNew)) {
                idProvinciaOld.getCiudadCollection().remove(ciudad);
                idProvinciaOld = em.merge(idProvinciaOld);
            }
            if (idProvinciaNew != null && !idProvinciaNew.equals(idProvinciaOld)) {
                idProvinciaNew.getCiudadCollection().add(ciudad);
                idProvinciaNew = em.merge(idProvinciaNew);
            }
            for (Paquete paqueteCollectionNewPaquete : paqueteCollectionNew) {
                if (!paqueteCollectionOld.contains(paqueteCollectionNewPaquete)) {
                    Ciudad oldIdCiudadOfPaqueteCollectionNewPaquete = paqueteCollectionNewPaquete.getIdCiudad();
                    paqueteCollectionNewPaquete.setIdCiudad(ciudad);
                    paqueteCollectionNewPaquete = em.merge(paqueteCollectionNewPaquete);
                    if (oldIdCiudadOfPaqueteCollectionNewPaquete != null && !oldIdCiudadOfPaqueteCollectionNewPaquete.equals(ciudad)) {
                        oldIdCiudadOfPaqueteCollectionNewPaquete.getPaqueteCollection().remove(paqueteCollectionNewPaquete);
                        oldIdCiudadOfPaqueteCollectionNewPaquete = em.merge(oldIdCiudadOfPaqueteCollectionNewPaquete);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = ciudad.getIdCiudad();
                if (findCiudad(id) == null) {
                    throw new NonexistentEntityException("The ciudad with id " + id + " no longer exists.");
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
            Ciudad ciudad;
            try {
                ciudad = em.getReference(Ciudad.class, id);
                ciudad.getIdCiudad();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ciudad with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Paquete> paqueteCollectionOrphanCheck = ciudad.getPaqueteCollection();
            for (Paquete paqueteCollectionOrphanCheckPaquete : paqueteCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Ciudad (" + ciudad + ") cannot be destroyed since the Paquete " + paqueteCollectionOrphanCheckPaquete + " in its paqueteCollection field has a non-nullable idCiudad field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Provincia idProvincia = ciudad.getIdProvincia();
            if (idProvincia != null) {
                idProvincia.getCiudadCollection().remove(ciudad);
                idProvincia = em.merge(idProvincia);
            }
            em.remove(ciudad);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Ciudad> findCiudadEntities() {
        return findCiudadEntities(true, -1, -1);
    }

    public List<Ciudad> findCiudadEntities(int maxResults, int firstResult) {
        return findCiudadEntities(false, maxResults, firstResult);
    }

    private List<Ciudad> findCiudadEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Ciudad.class));
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

    public Ciudad findCiudad(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Ciudad.class, id);
        } finally {
            em.close();
        }
    }

    public int getCiudadCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Ciudad> rt = cq.from(Ciudad.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
