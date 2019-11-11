package project.Tasklist.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Status {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long statusid; 
	private String name;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "status")
	private List<Task> tasks;
	
	public Status() {}
	
	public Status(String name) {
		super();
		this.name = name;
	}
	
	public Long getId() {
		return statusid;
	}
	public void setId(Long id) {
		this.statusid = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Task> getTasks() {
		return tasks;
	}
	public void setTasks(List<Task> books) {
		this.tasks = books;
	}

	@Override
	public String toString() {
		return "Status [id=" + statusid + ", name=" + name + "]";
	}
}
