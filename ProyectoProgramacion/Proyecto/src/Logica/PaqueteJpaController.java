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
import Clases.Cliente;
import Clases.Historial;
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
public class PaqueteJpaController implements Serializable {

    public PaqueteJpaController() {
        this.emf = Persistence.createEntityManagerFactory("ProyectoPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Paquete paquete) {
        if (paquete.getHistorialCollection() == null) {
            paquete.setHistorialCollection(new ArrayList<Historial>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ciudad idCiudad = paquete.getIdCiudad();
            if (idCiudad != null) {
                idCiudad = em.getReference(idCiudad.getClass(), idCiudad.getIdCiudad());
                paquete.setIdCiudad(idCiudad);
            }
            Cliente idRemitente = paquete.getIdRemitente();
            if (idRemitente != null) {
                idRemitente = em.getReference(idRemitente.getClass(), idRemitente.getIdCliente());
                paquete.setIdRemitente(idRemitente);
            }
            Cliente idDestinatario = paquete.getIdDestinatario();
            if (idDestinatario != null) {
                idDestinatario = em.getReference(idDestinatario.getClass(), idDestinatario.getIdCliente());
                paquete.setIdDestinatario(idDestinatario);
            }
            Collection<Historial> attachedHistorialCollection = new ArrayList<Historial>();
            for (Historial historialCollectionHistorialToAttach : paquete.getHistorialCollection()) {
                historialCollectionHistorialToAttach = em.getReference(historialCollectionHistorialToAttach.getClass(), historialCollectionHistorialToAttach.getIdHistorial());
                attachedHistorialCollection.add(historialCollectionHistorialToAttach);
            }
            paquete.setHistorialCollection(attachedHistorialCollection);
            em.persist(paquete);
            if (idCiudad != null) {
                idCiudad.getPaqueteCollection().add(paquete);
                idCiudad = em.merge(idCiudad);
            }
            if (idRemitente != null) {
                idRemitente.getPaqueteCollection().add(paquete);
                idRemitente = em.merge(idRemitente);
            }
            if (idDestinatario != null) {
                idDestinatario.getPaqueteCollection().add(paquete);
                idDestinatario = em.merge(idDestinatario);
            }
            for (Historial historialCollectionHistorial : paquete.getHistorialCollection()) {
                Paquete oldIdPaqueteOfHistorialCollectionHistorial = historialCollectionHistorial.getIdPaquete();
                historialCollectionHistorial.setIdPaquete(paquete);
                historialCollectionHistorial = em.merge(historialCollectionHistorial);
                if (oldIdPaqueteOfHistorialCollectionHistorial != null) {
                    oldIdPaqueteOfHistorialCollectionHistorial.getHistorialCollection().remove(historialCollectionHistorial);
                    oldIdPaqueteOfHistorialCollectionHistorial = em.merge(oldIdPaqueteOfHistorialCollectionHistorial);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Paquete paquete) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Paquete persistentPaquete = em.find(Paquete.class, paquete.getIdPaquete());
            Ciudad idCiudadOld = persistentPaquete.getIdCiudad();
            Ciudad idCiudadNew = paquete.getIdCiudad();
            Cliente idRemitenteOld = persistentPaquete.getIdRemitente();
            Cliente idRemitenteNew = paquete.getIdRemitente();
            Cliente idDestinatarioOld = persistentPaquete.getIdDestinatario();
            Cliente idDestinatarioNew = paquete.getIdDestinatario();
            Collection<Historial> historialCollectionOld = persistentPaquete.getHistorialCollection();
            Collection<Historial> historialCollectionNew = paquete.getHistorialCollection();
            List<String> illegalOrphanMessages = null;
            for (Historial historialCollectionOldHistorial : historialCollectionOld) {
                if (!historialCollectionNew.contains(historialCollectionOldHistorial)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Historial " + historialCollectionOldHistorial + " since its idPaquete field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idCiudadNew != null) {
                idCiudadNew = em.getReference(idCiudadNew.getClass(), idCiudadNew.getIdCiudad());
                paquete.setIdCiudad(idCiudadNew);
            }
            if (idRemitenteNew != null) {
                idRemitenteNew = em.getReference(idRemitenteNew.getClass(), idRemitenteNew.getIdCliente());
                paquete.setIdRemitente(idRemitenteNew);
            }
            if (idDestinatarioNew != null) {
                idDestinatarioNew = em.getReference(idDestinatarioNew.getClass(), idDestinatarioNew.getIdCliente());
                paquete.setIdDestinatario(idDestinatarioNew);
            }
            Collection<Historial> attachedHistorialCollectionNew = new ArrayList<Historial>();
            for (Historial historialCollectionNewHistorialToAttach : historialCollectionNew) {
                historialCollectionNewHistorialToAttach = em.getReference(historialCollectionNewHistorialToAttach.getClass(), historialCollectionNewHistorialToAttach.getIdHistorial());
                attachedHistorialCollectionNew.add(historialCollectionNewHistorialToAttach);
            }
            historialCollectionNew = attachedHistorialCollectionNew;
            paquete.setHistorialCollection(historialCollectionNew);
            paquete = em.merge(paquete);
            if (idCiudadOld != null && !idCiudadOld.equals(idCiudadNew)) {
                idCiudadOld.getPaqueteCollection().remove(paquete);
                idCiudadOld = em.merge(idCiudadOld);
            }
            if (idCiudadNew != null && !idCiudadNew.equals(idCiudadOld)) {
                idCiudadNew.getPaqueteCollection().add(paquete);
                idCiudadNew = em.merge(idCiudadNew);
            }
            if (idRemitenteOld != null && !idRemitenteOld.equals(idRemitenteNew)) {
                idRemitenteOld.getPaqueteCollection().remove(paquete);
                idRemitenteOld = em.merge(idRemitenteOld);
            }
            if (idRemitenteNew != null && !idRemitenteNew.equals(idRemitenteOld)) {
                idRemitenteNew.getPaqueteCollection().add(paquete);
                idRemitenteNew = em.merge(idRemitenteNew);
            }
            if (idDestinatarioOld != null && !idDestinatarioOld.equals(idDestinatarioNew)) {
                idDestinatarioOld.getPaqueteCollection().remove(paquete);
                idDestinatarioOld = em.merge(idDestinatarioOld);
            }
            if (idDestinatarioNew != null && !idDestinatarioNew.equals(idDestinatarioOld)) {
                idDestinatarioNew.getPaqueteCollection().add(paquete);
                idDestinatarioNew = em.merge(idDestinatarioNew);
            }
            for (Historial historialCollectionNewHistorial : historialCollectionNew) {
                if (!historialCollectionOld.contains(historialCollectionNewHistorial)) {
                    Paquete oldIdPaqueteOfHistorialCollectionNewHistorial = historialCollectionNewHistorial.getIdPaquete();
                    historialCollectionNewHistorial.setIdPaquete(paquete);
                    historialCollectionNewHistorial = em.merge(historialCollectionNewHistorial);
                    if (oldIdPaqueteOfHistorialCollectionNewHistorial != null && !oldIdPaqueteOfHistorialCollectionNewHistorial.equals(paquete)) {
                        oldIdPaqueteOfHistorialCollectionNewHistorial.getHistorialCollection().remove(historialCollectionNewHistorial);
                        oldIdPaqueteOfHistorialCollectionNewHistorial = em.merge(oldIdPaqueteOfHistorialCollectionNewHistorial);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = paquete.getIdPaquete();
                if (findPaquete(id) == null) {
                    throw new NonexistentEntityException("The paquete with id " + id + " no longer exists.");
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
            Paquete paquete;
            try {
                paquete = em.getReference(Paquete.class, id);
                paquete.getIdPaquete();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The paquete with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Historial> historialCollectionOrphanCheck = paquete.getHistorialCollection();
            for (Historial historialCollectionOrphanCheckHistorial : historialCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Paquete (" + paquete + ") cannot be destroyed since the Historial " + historialCollectionOrphanCheckHistorial + " in its historialCollection field has a non-nullable idPaquete field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Ciudad idCiudad = paquete.getIdCiudad();
            if (idCiudad != null) {
                idCiudad.getPaqueteCollection().remove(paquete);
                idCiudad = em.merge(idCiudad);
            }
            Cliente idRemitente = paquete.getIdRemitente();
            if (idRemitente != null) {
                idRemitente.getPaqueteCollection().remove(paquete);
                idRemitente = em.merge(idRemitente);
            }
            Cliente idDestinatario = paquete.getIdDestinatario();
            if (idDestinatario != null) {
                idDestinatario.getPaqueteCollection().remove(paquete);
                idDestinatario = em.merge(idDestinatario);
            }
            em.remove(paquete);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Paquete> findPaqueteEntities() {
        return findPaqueteEntities(true, -1, -1);
    }

    public List<Paquete> findPaqueteEntities(int maxResults, int firstResult) {
        return findPaqueteEntities(false, maxResults, firstResult);
    }

    private List<Paquete> findPaqueteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Paquete.class));
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

    public Paquete findPaquete(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Paquete.class, id);
        } finally {
            em.close();
        }
    }

    public int getPaqueteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Paquete> rt = cq.from(Paquete.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
