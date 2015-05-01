package pis.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import pis.data.Course;
import pis.data.Lector;

@Stateless
public class CourseManager {
	@PersistenceContext
    private EntityManager em;
	
	public void save(Course c)
    {
    	em.merge(c);
    }
	
    public void remove(Course c)
    {
    	em.remove(em.merge(c));
    }
    
    @SuppressWarnings("unchecked")
    public List<Course> findAll()
    {
    	return em.createQuery("SELECT c FROM Course c").getResultList();
    }
    
    @SuppressWarnings("unchecked")
    public List<Course> findAll(Lector lector)
    {
    	return em.createQuery("SELECT c FROM Course c WHERE c.lector = :lector")
    			.setParameter("lector", lector)
    			.getResultList();
    }

}
