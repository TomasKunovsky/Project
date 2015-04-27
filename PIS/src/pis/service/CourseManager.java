package pis.service;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pis.data.Course;

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

}
