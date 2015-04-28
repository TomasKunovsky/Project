package pis.back;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import pis.data.Course;
import pis.data.Lector;
import pis.service.CourseManager;

/**
 * Umoznuje manipulaciu s kurzmi, ktore podliehaju lektorovi.
 */
@ManagedBean
@SessionScoped
public class CoursesLectorBean {
	@EJB
	private CourseManager courseMngr;
	
	private Course course;
	private Lector lector;
	
	public String actionCourses(Lector lector) {
		this.lector = lector;
		
		return "courses_list";
	}
	
	public List<Course> getCourses() {
		return courseMngr.findAll(lector);
	}
	
	public String actionUpdate() {
		courseMngr.save(course);
		
		return "edit";
	}
	
	public String actionEdit(Course course) {
		this.course = course;
		
		return "edit";
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}
}
