package pis.back;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import pis.data.Course;
import pis.data.Lector;
import pis.service.CourseManager;
import pis.service.LectorManager;



@ManagedBean
@SessionScoped
public class CourseBean {
	@EJB
	private CourseManager courseMgr;
	@EJB
	private LectorManager lectorMgr;
	private Course course;
	private Lector lector;
	
	public CourseBean() {
		course = new Course();
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}
	
	public List<Course> getCourses()
    {
		return courseMgr.findAll();
    }
	
	public String actionNew()
	{
		course = new Course();
		return "new";
	}
    
	public String actionInsertNew()
    {
        courseMgr.save(course);
        return "insert";
    }
	
	public String actionInsertNew(Lector lector)
    {
		lector.getCourses().add(course);
		course.setLector(lector);
		lectorMgr.save(lector);
        return "insert";
    }
	
	public String actionNewCourseLectorSelect()
    {
        return "lector_select";
    }
	
	public String actionEditCourseLectorSelect()
    {
        return "lector_select";
    }

	public String actionUpdate()
    {
        if ((course.getLector() == null && lector != null) ||  course.getLector().equals(lector) == false ) {
        	if (course.getLector() != null) {
        		course.getLector().getCourses().remove(course);
            	lectorMgr.save(course.getLector());
        	}
        	
        	if (lector != null) {
        		lector.getCourses().add(course);
        		lectorMgr.save(lector);
            }
        	
        	course.setLector(lector);
            courseMgr.save(course);
        }
        	
        return "update";
    }
    
    public String actionEdit(Course course)
    {
    	setCourse(course);
    	setLector(course.getLector());
    	return "edit";
    }
    
    public String actionDelete(Course course)
    {
    	if (course.getLector() != null) {
    		course.getLector().getCourses().remove(course);
    		lectorMgr.save(course.getLector());
    	}
    	
    	courseMgr.remove(course);
    	return "delete";
    }

	public Lector getLector() {
		return lector;
	}

	public void setLector(Lector lector) {
		this.lector = lector;
	}
	
	public String actionLectorEdit() {
		setLector(null);
		return "edit";
	}
	
	public String actionLectorEdit(Lector lector) {
		setLector(lector);
		return "edit";
	}
    
    

}
