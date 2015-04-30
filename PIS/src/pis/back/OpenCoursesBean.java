package pis.back;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import pis.data.Lector;
import pis.data.Lesson;
import pis.data.OpenCourse;
import pis.service.LessonManager;
import pis.service.OpenCourseManager;

/**
 * Umoznuje zobrazenie otvorenych kurzov neregistrovanemu klientovi
 */
@ManagedBean
@SessionScoped
public class OpenCoursesBean {
	@EJB
	private OpenCourseManager openCourseMngr;
	
	private OpenCourse openCourse;
	
	public String actionOpenCourse(OpenCourse course) {
		this.openCourse = course;
		
		return "open_course";
	}

	public List<OpenCourse> getOpenCourses() {
		return openCourseMngr.getOpenCourses();
	}

	public OpenCourse getOpenCourse() {
		return openCourse;
	}

	public void setOpenCourse(OpenCourse openCourse) {
		this.openCourse = openCourse;
	}
}
