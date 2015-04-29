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
 * Umoznuje manipulaciu s kurzmi, na ktore je prihlaseny klient
 */
@ManagedBean
@SessionScoped
public class MyCoursesClientBean {
	@EJB
	private OpenCourseManager openCourseMngr;
	
	@EJB
	private LessonManager lessonMngr;

	// TODO
}
