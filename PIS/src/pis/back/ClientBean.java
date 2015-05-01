package pis.back;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import pis.data.Client;
import pis.data.Course;
import pis.data.Lector;
import pis.data.Lesson;
import pis.data.Payment;
import pis.data.OpenCourse;
import pis.service.ClientManager;
import pis.service.CourseManager;
import pis.service.LessonManager;
import pis.service.OpenCourseManager;
import pis.service.PaymentManager;

@ManagedBean
@SessionScoped
public class ClientBean {
	@EJB
	private ClientManager clientMgr;
	
	@EJB
	private LessonManager lessonMgr;
	
	@EJB
	private OpenCourseManager openCourseMgr;
	
	@EJB
	private CourseManager courseMgr;
	
	@EJB
	private PaymentManager paymentMgr;
	
	private Client client;
	
	public ClientBean() {
		
	}
	
	public List<Client> getClients()
    {
		return clientMgr.findAll();
    }
	
	public String actionDelete(Client client)
    {
		safeRemove(client);
    	return "delete";
    }
	
	public String actionEdit(Client client)
    {
    	setClient(client);
    	return "edit";
    }
	
	public String actionUpdate()
    {
        clientMgr.save(client);
        return "update";
    }
	
	public String actionNew()
	{
		client = new Client();
		return "new";
	}
	
	public String actionInsertNew()
    {
        clientMgr.save(client);
        return "insert";
    }
	
	public void safeRemove(Client client) {
		/*vazba na lekce*/		
		for (Lesson lesson:client.getLessons()) {
			lesson.getClients().remove(client);
			lessonMgr.save(lesson);			
		}
		client.getLessons().clear();
		clientMgr.save(client);
		
		/*vazba na otevrene kurzy*/		
		for (OpenCourse openCourse:client.getOpenCourses()) {
			openCourse.getClients().remove(client);
			openCourseMgr.save(openCourse);			
		}
		client.getOpenCourses().clear();
		clientMgr.save(client);
		
		/*vazba na kurzy*/		
		for (Course course:client.getCourses()) {
			course.getClients().remove(client);
			courseMgr.save(course);			
		}
		client.getCourses().clear();
		clientMgr.save(client);
		
		/*vazba na platby*/		
		for (Payment payment:client.getPayments()) {
			payment.setClient(null);
			paymentMgr.save(payment);			
		}
		client.getPayments().clear();
		clientMgr.save(client);
		
		clientMgr.remove(client);
	}
	
	public String actionShowPayments(Client client) {
		setClient(client);
		return "payment_list";
	}

	/*gettery a settery*/
	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
	
	
	
	

}
