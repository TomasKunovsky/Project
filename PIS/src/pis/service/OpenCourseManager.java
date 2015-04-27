package pis.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import pis.data.Lector;
import pis.data.OpenCourse;

@Stateless
public class OpenCourseManager {
    @PersistenceContext
    private EntityManager em;
    
    @SuppressWarnings("unchecked")
    public List<OpenCourse> getOpenCourses(Lector lector) {
    	
    	return em.createQuery("SELECT c FROM OpenCourse c WHERE c.lector = :lec")
    			.setParameter("lec", lector)
    			.getResultList();
    }

}
