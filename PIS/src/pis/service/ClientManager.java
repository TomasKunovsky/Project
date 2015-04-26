package pis.service;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import pis.data.Client;

@Stateless
public class ClientManager {
    @PersistenceContext
    private EntityManager em;
    
    public Client find(String login)
    {
    	return em.find(Client.class, login);
    }
}
