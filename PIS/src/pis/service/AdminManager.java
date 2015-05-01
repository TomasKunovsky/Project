package pis.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import pis.data.Admin;

@Stateless
public class AdminManager {
    @PersistenceContext
    private EntityManager em;
    
    public Admin find(String login)
    {
    	return em.find(Admin.class, login);
    }
    
    @SuppressWarnings("unchecked")
    public List<Admin> findAll()
    {
    	return em.createQuery("SELECT a FROM Admin a").getResultList();
    }
    
    public void save(Admin a)
    {
    	em.merge(a);
    }
	
    public void remove(Admin a)
    {
    	em.remove(em.merge(a));
    }
}
