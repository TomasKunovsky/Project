package pis.service;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import pis.data.Admin;
import pis.data.Client;
import pis.data.Payment;

@Stateless
public class PaymentManager {
    @PersistenceContext
    private EntityManager em;
    
    public Payment save(Payment payment)
    {
    	return em.merge(payment);
    }

	public void addNew(Payment payment) {
		em.persist(payment);		
	}
}
