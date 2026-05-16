/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import Clases.Cliente;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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
public class ClienteJpaController implements Serializable {

    public ClienteJpaController() {
        this.emf = Persistence.createEntityManagerFactory("ProyectoPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cliente cliente) {
        if (cliente.getPaqueteCollection() == null) {
            cliente.setPaqueteCollection(new ArrayList<Paquete>());
        }
        if (cliente.getPaqueteCollection1() == null) {
            cliente.setPaqueteCollection1(new ArrayList<Paquete>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Paquete> attachedPaqueteCollection = new ArrayList<Paquete>();
            for (Paquete paqueteCollectionPaqueteToAttach : cliente.getPaqueteCollection()) {
                paqueteCollectionPaqueteToAttach = em.getReference(paqueteCollectionPaqueteToAttach.getClass(), paqueteCollectionPaqueteToAttach.getIdPaquete());
                attachedPaqueteCollection.add(paqueteCollectionPaqueteToAttach);
            }
            cliente.setPaqueteCollection(attachedPaqueteCollection);
            Collection<Paquete> attachedPaqueteCollection1 = new ArrayList<Paquete>();
            for (Paquete paqueteCollection1PaqueteToAttach : cliente.getPaqueteCollection1()) {
                paqueteCollection1PaqueteToAttach = em.getReference(paqueteCollection1PaqueteToAttach.getClass(), paqueteCollection1PaqueteToAttach.getIdPaquete());
                attachedPaqueteCollection1.add(paqueteCollection1PaqueteToAttach);
            }
            cliente.setPaqueteCollection1(attachedPaqueteCollection1);
            em.persist(cliente);
            for (Paquete paqueteCollectionPaquete : cliente.getPaqueteCollection()) {
                Cliente oldIdRemitenteOfPaqueteCollectionPaquete = paqueteCollectionPaquete.getIdRemitente();
                paqueteCollectionPaquete.setIdRemitente(cliente);
                paqueteCollectionPaquete = em.merge(paqueteCollectionPaquete);
                if (oldIdRemitenteOfPaqueteCollectionPaquete != null) {
                    oldIdRemitenteOfPaqueteCollectionPaquete.getPaqueteCollection().remove(paqueteCollectionPaquete);
                    oldIdRemitenteOfPaqueteCollectionPaquete = em.merge(oldIdRemitenteOfPaqueteCollectionPaquete);
                }
            }
            for (Paquete paqueteCollection1Paquete : cliente.getPaqueteCollection1()) {
                Cliente oldIdDestinatarioOfPaqueteCollection1Paquete = paqueteCollection1Paquete.getIdDestinatario();
                paqueteCollection1Paquete.setIdDestinatario(cliente);
                paqueteCollection1Paquete = em.merge(paqueteCollection1Paquete);
                if (oldIdDestinatarioOfPaqueteCollection1Paquete != null) {
                    oldIdDestinatarioOfPaqueteCollection1Paquete.getPaqueteCollection1().remove(paqueteCollection1Paquete);
                    oldIdDestinatarioOfPaqueteCollection1Paquete = em.merge(oldIdDestinatarioOfPaqueteCollection1Paquete);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cliente cliente) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente persistentCliente = em.find(Cliente.class, cliente.getIdCliente());
            Collection<Paquete> paqueteCollectionOld = persistentCliente.getPaqueteCollection();
            Collection<Paquete> paqueteCollectionNew = cliente.getPaqueteCollection();
            Collection<Paquete> paqueteCollection1Old = persistentCliente.getPaqueteCollection1();
            Collection<Paquete> paqueteCollection1New = cliente.getPaqueteCollection1();
            List<String> illegalOrphanMessages = null;
            for (Paquete paqueteCollectionOldPaquete : paqueteCollectionOld) {
                if (!paqueteCollectionNew.contains(paqueteCollectionOldPaquete)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Paquete " + paqueteCollectionOldPaquete + " since its idRemitente field is not nullable.");
                }
            }
            for (Paquete paqueteCollection1OldPaquete : paqueteCollection1Old) {
                if (!paqueteCollection1New.contains(paqueteCollection1OldPaquete)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Paquete " + paqueteCollection1OldPaquete + " since its idDestinatario field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Paquete> attachedPaqueteCollectionNew = new ArrayList<Paquete>();
            for (Paquete paqueteCollectionNewPaqueteToAttach : paqueteCollectionNew) {
                paqueteCollectionNewPaqueteToAttach = em.getReference(paqueteCollectionNewPaqueteToAttach.getClass(), paqueteCollectionNewPaqueteToAttach.getIdPaquete());
                attachedPaqueteCollectionNew.add(paqueteCollectionNewPaqueteToAttach);
            }
            paqueteCollectionNew = attachedPaqueteCollectionNew;
            cliente.setPaqueteCollection(paqueteCollectionNew);
            Collection<Paquete> attachedPaqueteCollection1New = new ArrayList<Paquete>();
            for (Paquete paqueteCollection1NewPaqueteToAttach : paqueteCollection1New) {
                paqueteCollection1NewPaqueteToAttach = em.getReference(paqueteCollection1NewPaqueteToAttach.getClass(), paqueteCollection1NewPaqueteToAttach.getIdPaquete());
                attachedPaqueteCollection1New.add(paqueteCollection1NewPaqueteToAttach);
            }
            paqueteCollection1New = attachedPaqueteCollection1New;
            cliente.setPaqueteCollection1(paqueteCollection1New);
            cliente = em.merge(cliente);
            for (Paquete paqueteCollectionNewPaquete : paqueteCollectionNew) {
                if (!paqueteCollectionOld.contains(paqueteCollectionNewPaquete)) {
                    Cliente oldIdRemitenteOfPaqueteCollectionNewPaquete = paqueteCollectionNewPaquete.getIdRemitente();
                    paqueteCollectionNewPaquete.setIdRemitente(cliente);
                    paqueteCollectionNewPaquete = em.merge(paqueteCollectionNewPaquete);
                    if (oldIdRemitenteOfPaqueteCollectionNewPaquete != null && !oldIdRemitenteOfPaqueteCollectionNewPaquete.equals(cliente)) {
                        oldIdRemitenteOfPaqueteCollectionNewPaquete.getPaqueteCollection().remove(paqueteCollectionNewPaquete);
                        oldIdRemitenteOfPaqueteCollectionNewPaquete = em.merge(oldIdRemitenteOfPaqueteCollectionNewPaquete);
                    }
                }
            }
            for (Paquete paqueteCollection1NewPaquete : paqueteCollection1New) {
                if (!paqueteCollection1Old.contains(paqueteCollection1NewPaquete)) {
                    Cliente oldIdDestinatarioOfPaqueteCollection1NewPaquete = paqueteCollection1NewPaquete.getIdDestinatario();
                    paqueteCollection1NewPaquete.setIdDestinatario(cliente);
                    paqueteCollection1NewPaquete = em.merge(paqueteCollection1NewPaquete);
                    if (oldIdDestinatarioOfPaqueteCollection1NewPaquete != null && !oldIdDestinatarioOfPaqueteCollection1NewPaquete.equals(cliente)) {
                        oldIdDestinatarioOfPaqueteCollection1NewPaquete.getPaqueteCollection1().remove(paqueteCollection1NewPaquete);
                        oldIdDestinatarioOfPaqueteCollection1NewPaquete = em.merge(oldIdDestinatarioOfPaqueteCollection1NewPaquete);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = cliente.getIdCliente();
                if (findCliente(id) == null) {
                    throw new NonexistentEntityException("The cliente with id " + id + " no longer exists.");
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
            Cliente cliente;
            try {
                cliente = em.getReference(Cliente.class, id);
                cliente.getIdCliente();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cliente with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Paquete> paqueteCollectionOrphanCheck = cliente.getPaqueteCollection();
            for (Paquete paqueteCollectionOrphanCheckPaquete : paqueteCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Cliente (" + cliente + ") cannot be destroyed since the Paquete " + paqueteCollectionOrphanCheckPaquete + " in its paqueteCollection field has a non-nullable idRemitente field.");
            }
            Collection<Paquete> paqueteCollection1OrphanCheck = cliente.getPaqueteCollection1();
            for (Paquete paqueteCollection1OrphanCheckPaquete : paqueteCollection1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Cliente (" + cliente + ") cannot be destroyed since the Paquete " + paqueteCollection1OrphanCheckPaquete + " in its paqueteCollection1 field has a non-nullable idDestinatario field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(cliente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cliente> findClienteEntities() {
        return findClienteEntities(true, -1, -1);
    }

    public List<Cliente> findClienteEntities(int maxResults, int firstResult) {
        return findClienteEntities(false, maxResults, firstResult);
    }

    private List<Cliente> findClienteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cliente.class));
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

    public Cliente findCliente(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cliente.class, id);
        } finally {
            em.close();
        }
    }

    public int getClienteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cliente> rt = cq.from(Cliente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
