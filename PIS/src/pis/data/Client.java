package pis.data;

import java.util.Collection;

import javax.persistence.Entity;

import static javax.persistence.FetchType.EAGER;

import javax.persistence.ManyToMany;

import static javax.persistence.CascadeType.ALL;

import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import java.util.Vector;


@Entity
public class Client extends User  {
	
	@ManyToMany(fetch = EAGER)
	@JoinTable(
		      name="client_open_course",
		      joinColumns={@JoinColumn(name="client_login", referencedColumnName="login")},
		      inverseJoinColumns={@JoinColumn(name="open_course_id", referencedColumnName="id")})
	private Collection<OpenCourse> openCourses;
	
	@ManyToMany(fetch = EAGER)
	@JoinTable(
		      name="client_lesson",
		      joinColumns={@JoinColumn(name="client_login", referencedColumnName="login")},
		      inverseJoinColumns={@JoinColumn(name="lesson_id", referencedColumnName="id")})
	private Collection<Lesson> lessons;
	
	@ManyToMany(fetch = EAGER)
	@JoinTable(
		      name="client_course",
		      joinColumns={@JoinColumn(name="client_login", referencedColumnName="login")},
		      inverseJoinColumns={@JoinColumn(name="course_id", referencedColumnName="id")})	
	private Collection<Course> courses;
	@OneToMany(mappedBy = "client", orphanRemoval = true, fetch = EAGER, cascade = ALL)
	private Collection<Payment> payments;
	public Client() {
		this.openCourses = new Vector<OpenCourse>();
		this.lessons = new Vector<Lesson>();
		this.courses = new Vector<Course>();
		this.payments = new Vector<Payment>();
	}
	public Collection<OpenCourse> getOpenCourses() {
		return openCourses;
	}
	public void setOpenCourses(Collection<OpenCourse> openCourses) {
		this.openCourses = openCourses;
	}
	public Collection<Lesson> getLessons() {
		return lessons;
	}
	public void setLessons(Collection<Lesson> lessons) {
		this.lessons = lessons;
	}
	public Collection<Course> getCourses() {
		return courses;
	}
	public void setCourses(Collection<Course> courses) {
		this.courses = courses;
	}
	public Collection<Payment> getPayments() {
		return payments;
	}
	public void setPayments(Collection<Payment> payments) {
		this.payments = payments;
	}
	



}
