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
 * Umoznuje, aby klient poziadal o otvorenie kurzu.
 */
@ManagedBean
@SessionScoped
public class CoursesClientBean {
	@EJB
	private OpenCourseManager openCourseMngr;
	
	@EJB
	private LessonManager lessonMngr;
	
	private OpenCourse openCourse;
	private Lector lector;
	private Lesson lesson;
	
	// TODO
}
