package pis.back;

import java.util.List;
import java.util.Vector;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import pis.data.Course;
import pis.data.Lector;
import pis.data.OpenCourse;
import pis.service.CourseManager;
import pis.service.LectorManager;

@ManagedBean
@SessionScoped
public class LectorBean {
	@EJB
	private LectorManager lectorMgr;
	@EJB
	private CourseManager courseMgr;
	private Lector lector;
	private Course course;
	private List<Course> courses;
	
	public LectorBean() {
		lector = new Lector();
		course = new Course();
		courses = new Vector<Course>();
	}
	
	public Lector getLector() {
		return lector;
	}

	public void setLector(Lector lector) {
		this.lector = lector;
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

	public String actionUpdate()
    {
		for (Course course:courses) {
			course.setLector(null);
			courseMgr.save(course);
		}
        lectorMgr.save(lector);
        return "update";
    }
    
    public String actionEdit(Lector lector)
    {
    	courses.clear();
    	setLector(lector);
    	return "edit";
    }
    
    public String actionDelete(Lector lector)
    {
    	for (Course course:lector.getCourses()) {
    		course.setLector(null);
    		courseMgr.save(course);
    	}
    	lectorMgr.remove(lector);
    	return "delete";
    }
    
    public String actionCourseDel(Course course)
    {
    	courses.add(course);
        lector.getCourses().remove(course);
        return "delete";
    }
    
    public String actionOpenCourseDel(OpenCourse openCourse)
    {
        lector.getOpenCourses().remove(openCourse);
        return "delete";
    }

}
