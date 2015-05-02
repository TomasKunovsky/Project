package pis.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import pis.back.LectorBean;
import pis.data.Course;
import pis.data.Lector;
import pis.data.OpenCourse;

@Stateless
public class OpenCourseManager {
	@PersistenceContext
    private EntityManager em;
	
	public void save(OpenCourse oc)
    {
    	em.merge(oc);
    }
	
    public void remove(OpenCourse oc)
    {
    	em.remove(em.merge(oc));
    }
    
    @SuppressWarnings("unchecked")
    public List<OpenCourse> findAll()
    {
    	return em.createQuery("SELECT c FROM OpenCourse c").getResultList();
    }
    
    @SuppressWarnings("unchecked")
    public List<OpenCourse> getOpenCourses(Lector lector) {
    	return em.createQuery("SELECT c FROM OpenCourse c WHERE c.lector = :lec")
    			.setParameter("lec", lector)
    			.getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<OpenCourse> getOpenCourses() {
    	return findAll();
    }

	public OpenCourse refresh(OpenCourse openCourse) {
		OpenCourse oc = em.merge(openCourse);
		em.refresh(oc);
		
		return oc;
	}
}
