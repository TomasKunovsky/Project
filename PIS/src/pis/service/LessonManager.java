package pis.service;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
}
