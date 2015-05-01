package pis.back;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import pis.data.Client;
import pis.data.Course;
import pis.data.Lector;
import pis.data.Lesson;
import pis.data.OpenCourse;
import pis.data.Payment;
import pis.service.ClientManager;
import pis.service.CourseManager;
import pis.service.LectorManager;
import pis.service.LessonManager;
import pis.service.OpenCourseManager;
import pis.service.PaymentManager;



@ManagedBean
@SessionScoped
public class CourseBean {
	@EJB
	private CourseManager courseMgr;
	@EJB
	private LectorManager lectorMgr;
	@EJB
	private OpenCourseManager openCourseMgr;
	@EJB
	private LessonManager lessonMgr;
	@EJB
	private ClientManager clientMgr;
	@EJB
	private PaymentManager paymentMgr;
	
	private Course course;
	private OpenCourse openCourse;
	
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
	
	public String actionEdit(Course course)
    {
    	setCourse(course);
    	return "edit";
    }
	
	public String actionDelete(Course course)
    {
		safeRemove(course);
    	return "delete";
    }
		
	public String actionUpdate() {
		courseMgr.save(course);
		return "update";
	}
	
	public String actionLectorSelect(Course course) {
		setCourse(course);
		return "lector_select";
	}
		
	public String actionLectorSelected(Lector lector) {
		Lector lt = course.getLector();
		if (lt == null) {
			course.setLector(lector);
			lector.getCourses().add(course);
			courseMgr.save(course);
			lectorMgr.save(lector);
		} else {
			if (!lector.equals(lt)) {
				course.setLector(lector);
				lt.getCourses().remove(course);
				lector.getCourses().add(course);
				courseMgr.save(course);
				lectorMgr.save(lt);
				lectorMgr.save(lector);
			}
		}
		
		return "list";
	}
	
	public String actionLectorSelected() {
		Lector lt = course.getLector();
		if (lt != null) {
			course.setLector(null);
			lt.getCourses().remove(course);
			lectorMgr.save(lt);
			courseMgr.save(course);
		} 
		
		return "list";
	}
	
	public void safeRemove(Course course) {
		/*vazba na lektora*/
		Lector lt = course.getLector();
    	if (lt != null) {
    		course.setLector(null);
    		courseMgr.save(course);
    		lt.getCourses().remove(course);
    		lectorMgr.save(lt);    		
    	}
    	
    	/*vazba na klienty*/
    	for (Client client:course.getClients()) {
    		client.getCourses().remove(course);
    		clientMgr.save(client);
    	}
    	course.getClients().clear();
    	courseMgr.save(course);
    	
    	/*vazba na otevrene kurzy*/
    	List<OpenCourse> copyOpenCourses = new ArrayList<OpenCourse>(course.getOpenCourses());
    	for (OpenCourse openCourse:copyOpenCourses) {
    		safeRemove(openCourse);
    	}
    	course.getOpenCourses().clear();
    	courseMgr.remove(course);
	}
	
	public void safeRemove(OpenCourse openCourse) {
		/*vazba na lektora*/
		Lector lector = openCourse.getLector();
		if (lector != null) {
			openCourse.setLector(null);
			openCourseMgr.save(openCourse);
			lector.getOpenCourses().remove(openCourse);
			lectorMgr.save(lector);
		}
				
		/*vazba na klienty*/		
		for (Client client:openCourse.getClients()) {
			client.getOpenCourses().remove(openCourse);
			clientMgr.save(client);			
		}
		openCourse.getClients().clear();
		openCourseMgr.save(openCourse);
		
		/*vazba na kurz*/
		Course course = openCourse.getCourse();
		openCourse.setCourse(null);
		openCourseMgr.save(openCourse);
		course.getOpenCourses().remove(openCourse);
		courseMgr.save(course);
		
		/*vazba na platby*/
		for (Payment payment:openCourse.getPayments()) {
			payment.setOpenCourse(null);
			paymentMgr.save(payment);	
		}
		openCourse.getPayments().clear();
		openCourseMgr.save(openCourse);
		
		/*vazba na lekce*/
		List<Lesson> copyLessons = new ArrayList<Lesson>(openCourse.getLessons());
		for (Lesson lesson:copyLessons) {
			safeRemove(lesson);
		}
		openCourse.getLessons().clear();
		openCourseMgr.save(openCourse);
		openCourseMgr.remove(openCourse);
	}
	
	public void safeRemove(Lesson lesson) {
		/*vazba na lektora*/
		Lector lector = lesson.getLector();
		if (lector != null) {
			lesson.setLector(null);
			lessonMgr.save(lesson);
			lector.getLessons().remove(lesson);
			lectorMgr.save(lector);
		}
		/*vazba na otevreny kurz*/
		OpenCourse openCourse = lesson.getOpenCourse();
		lesson.setOpenCourse(null);
		lessonMgr.save(lesson);
		openCourse.getLessons().remove(lesson);
		openCourseMgr.save(openCourse);
		/*vazba na klienty*/		
		for (Client client:lesson.getClients()) {
			client.getLessons().remove(lesson);
			clientMgr.save(client);			
		}
		lesson.getClients().clear();
		lessonMgr.save(lesson);
		lessonMgr.remove(lesson);
	}
	
	public String actionOpenCourseDel(OpenCourse openCourse) {
		safeRemove(openCourse);
		
		return "delete";
	}
	
	/*settery a gettery*/
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public OpenCourse getOpenCourse() {
		return openCourse;
	}
	public void setOpenCourse(OpenCourse openCourse) {
		this.openCourse = openCourse;
	}
	

	
    

}
