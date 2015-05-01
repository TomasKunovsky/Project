package pis.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import pis.data.Lector;

@Stateless
public class LectorManager {
    @PersistenceContext
    private EntityManager em;
    
    public void save(Lector l)
    {
    	em.merge(l);
    }
	
    public void remove(Lector l)
    {
    	em.remove(em.merge(l));
    }
    
    @SuppressWarnings("unchecked")
    public List<Lector> findAll()
    {
    	return em.createQuery("SELECT l FROM Lector l").getResultList();
    }
    
    public Lector find(String login)
    {
    	return em.find(Lector.class, login);
    }
    
}
