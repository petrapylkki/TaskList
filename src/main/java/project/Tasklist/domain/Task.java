package project.Tasklist.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Task {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id; 
	private String name;
	private String course;
	private String date;

	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="statusid")
	private Status status;
	
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="priorityid")
	private Priority priority;
		
	public Task(String name, String course, String date, Status status, Priority priority) {
		super();
		this.name = name;
		this.course = course;
		this.date = date;
		this.status = status;
		this.priority = priority;
	}
	
	public Task() {}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	@Override
	public String toString() {
		return "Task [id=" + id + ", name=" + name + ", course=" + course + ", date=" + date + "]";
	}
	
	
}
