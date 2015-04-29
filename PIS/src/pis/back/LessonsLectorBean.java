package pis.back;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import pis.data.Client;
import pis.data.Lector;
import pis.data.Lesson;
import pis.data.OpenCourse;
import pis.service.LessonManager;
import pis.service.OpenCourseManager;

/**
 * Umoznuje manipulaciu s lekciami, ktore podliehaju lektorovi.
 */
@ManagedBean
@SessionScoped
public class LessonsLectorBean {
	@EJB
	private LessonManager lessonMngr;
	
	private Lector lector;
	private Lesson lesson;
	private OpenCourse openCourse;
	private boolean justUpdate = true;
	
	public String actionLessons(Lector lector) {
		this.lector = lector;
		
		return "lesson_list";
	}
	
	public List<Lesson> getLessons() {
		return lessonMngr.findAll(lector);
	}
	
	public String actionEditLesson(Lesson lesson) {
		this.lesson = lesson;
		justUpdate = true;
		
		return "edit";
	}
	
	public String actionEditLessonPresence(Lesson lesson) {
		this.lesson = lesson;
		
		return "presence";
	}
	
	public String actionUpdate() {
		lessonMngr.save(lesson);
		
		return "update";
	}
	
	public String actionSetPresent(Client client, boolean present) {
		if (present) {
			lesson.getClients().add(client);
		} else {
			lesson.getClients().remove(client);
		}
		
		lessonMngr.save(lesson);
		
		return "update";
	}
	
	public String actionAddLesson(OpenCourse course, Lector lector) {
		openCourse = course;
		justUpdate = false;
		lesson = new Lesson();
		lesson.setLector(lector);
		lesson.setOpenCourse(openCourse);
		
		return "add_lesson";
	}
	
	public String actionEditLesson() {
		String ret = null;
		
		if (lesson.getEnd().before(lesson.getStart())) {
		    FacesMessage errorMessage = new FacesMessage("»as zaËiatku lekcie musÌ byù pred koncom.");
		    errorMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
		    FacesContext.getCurrentInstance().addMessage(null, errorMessage);
			ret = null;
		} else {
			lessonMngr.save(lesson);
			ret = justUpdate ? "update" : "create";			
		}
		
		return ret;
	}

	public Lesson getLesson() {
		return lesson;
	}

	public void setLesson(Lesson lesson) {
		this.lesson = lesson;
	}
}
