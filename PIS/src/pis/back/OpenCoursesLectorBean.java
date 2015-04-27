package pis.back;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import pis.data.Lector;
import pis.data.OpenCourse;
import pis.service.OpenCourseManager;

@ManagedBean
@SessionScoped
public class OpenCoursesLectorBean {
	@EJB
	private OpenCourseManager openCourseMngr;
	
	private OpenCourse openCourse;
	private Lector lector;
	
	public String actionOpenCourses(Lector lector) {
		this.lector = lector;
		
		return "courses_list";
	}
	
	public String addLection(OpenCourse course) {
		openCourse = course;
		// TODO vytvor stranku
		
		return "add_lection";
	}
	
	public String changeCapacity(OpenCourse course) {
		openCourse = course;
		// TODO vytvor stranku
		
		return "change_capacity";
	}
	
	
	public List<OpenCourse> getOpenCourses() {
		return openCourseMngr.getOpenCourses(lector);
	}
}
