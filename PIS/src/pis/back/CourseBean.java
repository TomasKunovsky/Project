package pis.back;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import pis.data.Course;
import pis.data.Lector;
import pis.service.CourseManager;



@ManagedBean
@SessionScoped
public class CourseBean {
	@EJB
	private CourseManager courseMgr;
	private Course course;
	
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
        courseMgr.save(course);
        return "insert";
    }
	
	public String actionNewCourseLectorSelect()
    {
        return "lector_select";
    }

	public String actionUpdate()
    {
        courseMgr.save(course);
        return "update";
    }
    
    public String actionEdit(Course course)
    {
    	setCourse(course);
    	return "edit";
    }
    
    public String actionDelete(Course course)
    {
    	courseMgr.remove(course);
    	return "delete";
    }

}
