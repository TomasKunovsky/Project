package pis.back;

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
import pis.service.OpenCourseManager;
import pis.service.PaymentManager;

@ManagedBean
@SessionScoped
public class PaymentBean {
	@EJB
	private PaymentManager paymentMgr;
	@EJB
	private ClientManager clientMgr;
	@EJB
	private OpenCourseManager openCourseMgr;
	private Payment payment;
	
	public String actionDelete(Payment payment)
    {
    	safeRemove(payment);
    	return "delete";
    }
	
	public String actionUpdate()
    {
        paymentMgr.save(payment);
        return "update";
    }
	
	public List<Payment> getPayments()
    {
		return paymentMgr.findAll();
    }
	
	public String actionEdit(Payment payment) {
		setPayment(payment);
		
		return "edit";
	}
	
	public void safeRemove(Payment payment) {
		/*vazba na klienta*/
		Client ct = payment.getClient();
    	if (ct != null) {
    		payment.setClient(null);
    		paymentMgr.save(payment);
    		ct.getPayments().remove(payment);
    		clientMgr.save(ct);    		
    	}
    	/*vazba na otevreny kurz*/
		OpenCourse oct = payment.getOpenCourse();
    	if (oct != null) {
    		payment.setOpenCourse(null);
    		paymentMgr.save(payment);
    		oct.getPayments().remove(payment);
    		openCourseMgr.save(oct);    		
    	}
    	paymentMgr.remove(payment);
	}

	/*gettery a settery*/
	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	
	

}
