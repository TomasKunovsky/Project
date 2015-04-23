package pis.data;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.IDENTITY;
import static javax.persistence.TemporalType.DATE;

@Entity
@Table(name = "payment")
public class Payment {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private long id;
	private int amount;
	@Temporal(DATE)
	private Date date;
	@ManyToOne(fetch = EAGER) 
	private OpenCourse openCourse;
	@ManyToOne(fetch = EAGER) 
	private Client client;
	private String accountNumber;
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
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
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	
	

}
