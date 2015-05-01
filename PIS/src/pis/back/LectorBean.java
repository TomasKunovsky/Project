package pis.back;

import java.util.List;
import java.util.Vector;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import pis.data.Course;
import pis.data.Lector;
import pis.data.Lesson;
import pis.data.OpenCourse;
import pis.service.CourseManager;
import pis.service.LectorManager;
import pis.service.LessonManager;
import pis.service.OpenCourseManager;

@ManagedBean
@SessionScoped
public class LectorBean {
	@EJB
	private LectorManager lectorMgr;
	@EJB
	private CourseManager courseMgr;
	@EJB
	private OpenCourseManager openCourseMgr;
	@EJB
	private LessonManager lessonMgr;
	
	private Lector lector;
	private List<Course> courses;

	private List<OpenCourse> openCourses;
	
	public LectorBean() {
		lector = new Lector();
		courses = new Vector<Course>();
		openCourses = new Vector<OpenCourse>();
	}
	
	public List<Lector> getLectors()
    {
		return lectorMgr.findAll();
    }
	
	public String actionNew()
	{
		lector = new Lector();
		return "new";
	}
	
	public String actionInsertNew()
    {
        lectorMgr.save(lector);
        return "insert";
    }
	
	public String actionEdit(Lector lector)
    {
    	courses.clear();
    	openCourses.clear();
    	setLector(lector);
    	return "edit";
    }
	
    public String actionCourseDel(Course course)
    {
    	courses.add(course);
        lector.getCourses().remove(course);
        return "delete";
    }

    public String actionOpenCourseDel(OpenCourse openCourse)
    {
    	openCourses.add(openCourse);
        lector.getOpenCourses().remove(openCourse);
        return "delete";
    }
    
	public String actionUpdate()
    {
		for (Course course:courses) {
			course.setLector(null);
			courseMgr.save(course);
		}

		for (OpenCourse openCourse:openCourses) {
			openCourse.setLector(null);
			openCourseMgr.save(openCourse);
		}

        lectorMgr.save(lector);
        return "update";
    }
	
	public String actionDelete(Lector lector)
    {
    	safeRemove(lector);
    	return "delete";
    }
	
	public void safeRemove(Lector lector) {
		for (Course course:lector.getCourses()) {
			course.setLector(null);
			courseMgr.save(course);
		}
		lector.getCourses().clear();

		for (OpenCourse openCourse:lector.getOpenCourses()) {
			openCourse.setLector(null);
			openCourseMgr.save(openCourse);
		}
		lector.getOpenCourses().clear();
		
		for (Lesson lesson:lector.getLessons()) {
			lesson.setLector(null);
			lessonMgr.save(lesson);
		}
		lector.getLessons().clear();

    	lectorMgr.remove(lector); 
	}

	/*settery a gettery*/
	public Lector getLector() {
		return lector;
	}

	public void setLector(Lector lector) {
		this.lector = lector;
	}	

}
