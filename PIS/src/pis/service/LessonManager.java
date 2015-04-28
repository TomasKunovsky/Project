package pis.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import pis.data.Lector;
import pis.data.Lesson;

@Stateless
public class LessonManager {
	@PersistenceContext
    private EntityManager em;
	
	public void save(Lesson l)
    {
    	em.merge(l);
    }
	
    public void remove(Lesson l)
    {
    	em.remove(em.merge(l));
    }
    
    @SuppressWarnings("unchecked")
    public List<Lesson> findAll(Lector lector)
    {
    	return em.createQuery("SELECT lesson FROM Lesson lesson WHERE lesson.lector = :lector")
    			.setParameter("lector", lector)
    			.getResultList();
    }
}
