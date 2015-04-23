package pis.data;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.EAGER;

import java.util.Collection;
import java.util.Vector;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Lector extends User {
	private String qualification;
	@OneToMany(fetch = EAGER, cascade = ALL, mappedBy = "lector", orphanRemoval = false)
	private Collection<Lesson> lessons;
	@OneToMany(fetch = EAGER, cascade = ALL, mappedBy = "lector", orphanRemoval = false)
	private Collection<Course> courses;
	@OneToMany(fetch = EAGER, cascade = ALL, mappedBy = "lector", orphanRemoval = false)
	private Collection<OpenCourse> openCourses;
	
	public Lector() {
		lessons = new Vector<Lesson>();
		openCourses = new Vector<OpenCourse>();
		courses = new Vector<Course>();
	}
	
	public String getQualification() {
		return qualification;
	}
	public void setQualification(String qualification) {
		this.qualification = qualification;
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
	public Collection<OpenCourse> getOpenCourses() {
		return openCourses;
	}
	public void setOpenCourses(Collection<OpenCourse> openCourses) {
		this.openCourses = openCourses;
	}
	
	

}
