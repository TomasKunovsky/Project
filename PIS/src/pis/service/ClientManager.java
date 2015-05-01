package pis.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import pis.data.Client;
import pis.data.Lector;

@Stateless
public class ClientManager {
    @PersistenceContext
    private EntityManager em;
    
    public Client find(String login)
    {
    	return em.find(Client.class, login);
    }
    
    public void save(Client c)
    {
    	em.merge(c);
    }
	
    public void remove(Client c)
    {
    	em.remove(em.merge(c));
    }
    
    @SuppressWarnings("unchecked")
    public List<Client> findAll()
    {
    	return em.createQuery("SELECT c FROM Client c").getResultList();
    }
}
