package pis.data;

import java.util.Collection;
import java.util.Vector;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.OneToMany;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.CascadeType.ALL;

@Entity
@Table(name = "course")
public class Course {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private long id;
	private int price;
	private String description;
	private String name;
	@OneToMany(mappedBy = "course", orphanRemoval = true, fetch = EAGER, cascade = ALL)
	private Collection<OpenCourse> openCourses;
	@ManyToOne(fetch = EAGER)
	private Lector lector;
	@ManyToMany(mappedBy="courses", fetch = EAGER, cascade = ALL)
	private Collection<Client> clients;
	
	public Course() {
		openCourses = new Vector<OpenCourse>();
		clients = new Vector<Client>(); 
	}
	
	public Collection<OpenCourse> getOpenCourses() {
		return openCourses;
	}
	public void setOpenCourses(Collection<OpenCourse> openCourses) {
		this.openCourses = openCourses;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	
	public boolean equals(Object other)
    {
        if (other instanceof Course)
            return ((Course) other).getId() == id;
        else
            return false;
    }
	
	
	
}
