package pis.data;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Collection;
import java.util.Date;
import java.util.Vector;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import static javax.persistence.FetchType.EAGER;

import javax.persistence.Temporal;

import static javax.persistence.TemporalType.DATE;

import javax.persistence.OneToMany;

import static javax.persistence.CascadeType.ALL;

import javax.persistence.ManyToMany;

@Entity
@Table(name = "open_course")
public class OpenCourse {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private long id;
	private String place;
	private int capacity;
	@Temporal(DATE)
	private Date startDate;
	@Temporal(DATE)
	private Date endDate;
	@ManyToOne(fetch = EAGER) 
	private Course course;
	@OneToMany(fetch = EAGER, cascade = ALL, mappedBy = "openCourse", orphanRemoval = true)
	private Collection<Lesson> lessons;
	@ManyToOne(fetch = EAGER)
	private Lector lector;
	@ManyToMany(mappedBy="openCourses", fetch = EAGER, cascade = ALL)
	private Collection<Client> clients;
	@OneToMany(mappedBy = "openCourse", orphanRemoval = true, fetch = EAGER, cascade = ALL)
	private Collection<Payment> payments;
	
	public OpenCourse() {
		this.lessons = new Vector<Lesson>();
		this.clients = new Vector<Client>();
		this.payments = new Vector<Payment>();
	}
	
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Collection<Lesson> getLessons() {
		return lessons;
	}

	public void setLessons(Collection<Lesson> lessons) {
		this.lessons = lessons;
	}

	public Lector getLector() {
		return lector;
	}

	public void setLector(Lector lector) {
		this.lector = lector;
	}

	public Collection<Client> getClients() {
		return clients;
	}

	public void setClients(Collection<Client> clients) {
		this.clients = clients;
	}

	public Collection<Payment> getPayments() {
		return payments;
	}

	public void setPayments(Collection<Payment> payments) {
		this.payments = payments;
	}
	
	
	
}
