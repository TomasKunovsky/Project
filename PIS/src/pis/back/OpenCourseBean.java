package pis.back;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import pis.data.Course;
import pis.data.Lector;
import pis.data.OpenCourse;
import pis.service.CourseManager;
import pis.service.LectorManager;
import pis.service.OpenCourseManager;

@ManagedBean
@SessionScoped
public class OpenCourseBean {
	@EJB
	private OpenCourseManager openCourseMgr;
	@EJB
	private CourseManager courseMgr;
	@EJB
	private LectorManager lectorMgr;
	
	private OpenCourse openCourse;
	private Course course;
	
	public List<OpenCourse> getOpenCourses()
    {
		return openCourseMgr.findAll();
    }
	
	public String actionNew()
	{
		openCourse = new OpenCourse();
		return "course_select";
	}
	
	public String actionNewCourseSelected(Course course) {
		setCourse(course);
		return "new";
	}
	
	public String actionInsertNew()
    {
		course.getOpenCourses().add(openCourse);
		openCourse.setCourse(course);
        courseMgr.save(course);
        return "insert";
    }
	
	public String actionEdit(OpenCourse openCourse) {
		setOpenCourse(openCourse);		
		return "edit";
	}
	
	public String actionLectorSelect(OpenCourse openCourse) {
		setOpenCourse(openCourse);
		return "lector_select";
	}
	
	public String actionLectorSelected(Lector lector) {
		Lector lt = openCourse.getLector();
		if (lt == null) {
			openCourse.setLector(lector);
			lector.getOpenCourses().add(openCourse);
			courseMgr.save(openCourse.getCourse());
			lectorMgr.save(lector);
		} else {
			if (!lector.equals(lt)) {
				openCourse.setLector(lector);
				lt.getOpenCourses().remove(openCourse);
				lector.getOpenCourses().add(openCourse);
				courseMgr.save(openCourse.getCourse());
				lectorMgr.save(lt);
				lectorMgr.save(lector);
			}
		}
		
		return "list";
	}
	
	public String actionLectorSelected() {
		Lector lt = openCourse.getLector();
		if (lt != null) {
			openCourse.setLector(null);
			lt.getOpenCourses().remove(openCourse);
			lectorMgr.save(lt);
			courseMgr.save(openCourse.getCourse());
		} 
		
		return "list";
	}
	
	public String actionUpdate() {		
		courseMgr.save(openCourse.getCourse());
		return "update";
	}

	/*gettery a settery*/
	public OpenCourse getOpenCourse() {
		return openCourse;
	}

	public void setOpenCourse(OpenCourse openCourse) {
		this.openCourse = openCourse;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}


}
