package pis.back;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import pis.data.Client;
import pis.data.Course;
import pis.data.Lector;
import pis.data.Lesson;
import pis.data.OpenCourse;
import pis.service.ClientManager;
import pis.service.CourseManager;
import pis.service.LessonManager;
import pis.service.OpenCourseManager;

/**
 * Umoznuje, aby klient poziadal o otvorenie kurzu.
 */
@ManagedBean
@SessionScoped
public class CoursesClientBean {
	private Course course;
	
	@EJB
	private CourseManager courseMngr;
	
	@EJB
	private ClientManager clientMngr;
	
	public String actionReopen(Course course, Client client) {
		this.setCourse(course);
		course.getClients().add(client);	
		courseMngr.save(course);
		
		return "reopen";
	}
	
	public List<Course> getCourses() {
		return courseMngr.findAll();
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}
}
