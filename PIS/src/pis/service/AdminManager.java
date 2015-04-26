package pis.service;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import pis.data.Admin;
import pis.data.Client;

@Stateless
public class AdminManager {
    @PersistenceContext
    private EntityManager em;
    
    public Admin find(String login)
    {
    	return em.find(Admin.class, login);
    }
}
