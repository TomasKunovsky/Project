package pis.back;

import java.util.Calendar;
import java.util.Date;
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
import pis.service.ClientManager;
import pis.service.LectorManager;
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

	@EJB
	private ClientManager clientMngr;
	
	@EJB
	private OpenCourseManager openCourseMngr;
	
	@EJB
	private LectorManager lectorMngr;
	
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
	
	public boolean lessonHasEnded(Lesson lesson) {
		boolean ret = false;
		Date now = new Date();
		
		if (lesson.getDate().getTime() < now.getTime()) {
			ret = true;
		}
		
		return ret;
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
			client.getLessons().add(lesson);
		} else {
			lesson.getClients().remove(client);
			client.getLessons().remove(lesson);
		}
		
		lessonMngr.save(lesson);
		clientMngr.save(client);
		
		return "update";
	}
	
	public String actionAddLesson(OpenCourse course, Lector lector) {
		this.openCourse = course;
		this.lector = lector;
		justUpdate = false;
		
		lesson = new Lesson();
		lesson.setLector(lector);
		lesson.setOpenCourse(openCourse);
		
		return "add_lesson";
	}
	
	public String actionEditLesson() {
		String ret = null;
		
		if (lesson.getEnd().before(lesson.getStart())) {
		    FacesMessage errorMessage = new FacesMessage("Èas zaèiatku lekcie musí by pred koncom.");
		    errorMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
		    FacesContext.getCurrentInstance().addMessage(null, errorMessage);
			ret = null;
		} else {	
            lessonMngr.save(lesson);

            lector = lectorMngr.refresh(lector);
            openCourse = openCourseMngr.refresh(lesson.getOpenCourse());

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
