package pis.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import pis.data.OpenCourse;
import pis.data.Payment;

@Stateless
public class PaymentManager {
	@PersistenceContext
    private EntityManager em;
	
    public void save(Payment p)
    {
    	em.merge(p);
    }
	
    public void remove(Payment p)
    {
    	em.remove(em.merge(p));
    }
    
    @SuppressWarnings("unchecked")
    public List<Payment> findAll()
    {
    	return em.createQuery("SELECT p FROM Payment p ORDER BY p.date DESC").getResultList();
    }
    
    public void addNew(Payment payment) {
		em.persist(payment);		
	}

}
