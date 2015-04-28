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
 * Umoznuje manipulaciu s otvorenymi kurzmi, ktore podliehaju lektorovi.
 */
@ManagedBean
@SessionScoped
public class OpenCoursesLectorBean {
	@EJB
	private OpenCourseManager openCourseMngr;
	
	@EJB
	private LessonManager lessonMngr;
	
	private OpenCourse openCourse;
	private Lector lector;
	private Lesson lesson;
	
	public String actionOpenCourses(Lector lector) {
		this.lector = lector;
		
		return "courses_list";
	}
	
	
	public String changeCapacity(OpenCourse course) {
		openCourse = course;
		
		return "change_capacity";
	}
	
	public String changePlace(OpenCourse course) {
		openCourse = course;
		
		return "change_place";
	}
	
	public List<OpenCourse> getOpenCourses() {
		return openCourseMngr.getOpenCourses(lector);
	}
	
	public String actionUpdate() {
		openCourseMngr.save(openCourse);
		
		return "update";
	}

	public OpenCourse getOpenCourse() {
		return openCourse;
	}

	public void setOpenCourse(OpenCourse openCourse) {
		this.openCourse = openCourse;
	}
}
