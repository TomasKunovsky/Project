package pis.data;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import static javax.persistence.TemporalType.DATE;
import static javax.persistence.TemporalType.TIME;
import static javax.persistence.FetchType.EAGER;
import javax.persistence.ManyToOne;

@Entity
@Table(name = "lesson")
public class Lesson {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private long id;
	private int cost;
	private String description;
	@Temporal(DATE)
	private Date date;
	@Temporal(TIME)
	private Date start;
	@Temporal(TIME)
	private Date end;
	@ManyToOne(fetch = EAGER)
	private OpenCourse openCourse;
	@ManyToOne(fetch = EAGER)
	private Lector lector;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Date getStart() {
		return start;
	}
	public void setStart(Date start) {
		this.start = start;
	}
	public Date getEnd() {
		return end;
	}
	public void setEnd(Date end) {
		this.end = end;
	}
	public OpenCourse getOpenCourse() {
		return openCourse;
	}
	public void setOpenCourse(OpenCourse openCourse) {
		this.openCourse = openCourse;
	}
	public Lector getLector() {
		return lector;
	}
	public void setLector(Lector lector) {
		this.lector = lector;
	}
	
}
