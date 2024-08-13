package com.JFS.Integration.student.model;



import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tasks")
public class Task {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	private String title;
    private String status;
    private Date dueDate;
    private Long projectId;
    
    public Task(Long id, String title, String status, Date dueDate, Long projectId) {
		super();
		this.id = id;
		this.title = title;
		this.status = status;
		this.dueDate = dueDate;
		this.projectId = projectId;
	}
    
    public Task () { }
    
    
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	public Long getProjectId() {
		return projectId;
	}
	@Override
	public String toString() {
		return "Task [id=" + id + ", title=" + title + ", status=" + status + ", dueDate=" + dueDate + ", projectId="
				+ projectId + "]";
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}
}
