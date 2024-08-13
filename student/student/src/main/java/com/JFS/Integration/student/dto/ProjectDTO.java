package com.JFS.Integration.student.dto;

public class ProjectDTO {
	private Long id;
    private String name;
    private String description;
    
     public ProjectDTO() {}
	
	public ProjectDTO (String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}
	public ProjectDTO(Long id, String name, String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
	}

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", description=" + description + "]";
	}
}
