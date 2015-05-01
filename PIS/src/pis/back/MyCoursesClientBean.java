package pis.back;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import pis.data.Client;
import pis.data.Lector;
import pis.data.Lesson;
import pis.data.OpenCourse;
import pis.data.Payment;
import pis.service.ClientManager;
import pis.service.LessonManager;
import pis.service.OpenCourseManager;
import pis.service.PaymentManager;

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

	@EJB
	private PaymentManager paymentMngr;
	
	@EJB
	private ClientManager clientMngr;
	
	private OpenCourse openCourse;
	private Client client;
	private Payment payment;

	public String actionPayCourse(OpenCourse course) {
		this.setOpenCourse(course);
		payment = new Payment();
		
		return "pay";
	}
	
	public String actionSendPaymentInfo() {
		paymentMngr.addNew(payment);
		client.getPayments().add(payment);
		payment.setClient(client);
		payment.setDate(new Date());
		payment.setOpenCourse(openCourse);
		openCourse.getPayments().add(payment);
		openCourseMngr.save(openCourse);
		clientMngr.save(client);
		
		return "ok";
	}
	
	public boolean isPayed(OpenCourse course) {
		boolean ret = false;
		
		for (Payment p : client.getPayments()) {
			if (p.getOpenCourse() == course) {
				ret = true;
				break;
			}
		}
		
		return ret;
	}
	
	public List<Lesson> getCourseLessons() {
		List<Lesson> tmp =  lessonMngr.findAll(openCourse);
		return tmp;
	}
	
	public String actionMyCourses(Client client) {
		this.setClient(client);
		
		return "my_courses";
	}
	
	public String actionLessons(OpenCourse course) {
		this.setOpenCourse(course);
		
		return "lessons";
	}

	public OpenCourse getOpenCourse() {
		return openCourse;
	}

	public void setOpenCourse(OpenCourse openCourse) {
		this.openCourse = openCourse;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}
}
