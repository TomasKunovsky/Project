package pis.back;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import pis.data.Client;
import pis.data.Lector;
import pis.data.Lesson;
import pis.data.OpenCourse;
import pis.service.LessonManager;
import pis.service.OpenCourseManager;

/**
 * Umoznuje prihlasovanie klienta na otvorene kurzy klientom.
 */
@ManagedBean
@SessionScoped
public class OpenCoursesClientBean {
	@EJB
	private OpenCourseManager openCourseMngr;
	
	@EJB
	private LessonManager lessonMngr;
	
	private OpenCourse openCourse;
	private Client client;
	
	public List<OpenCourse> getOpenCourses() {
		return openCourseMngr.getOpenCourses();
	}
	
	public String actionStartRegistation(OpenCourse course, Client client) {
		this.setOpenCourse(course);
		this.setClient(client);
		
		return "register";
	}
	
	public String actionRegister() {
		openCourse.getClients().add(client);
		openCourseMngr.save(openCourse);

		return "ok";
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public OpenCourse getOpenCourse() {
		return openCourse;
	}

	public void setOpenCourse(OpenCourse openCourse) {
		this.openCourse = openCourse;
	}
}
