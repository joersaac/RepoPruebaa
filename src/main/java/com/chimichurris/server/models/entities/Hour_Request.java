package com.chimichurris.server.models.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "hour_request")
public class Hour_Request implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@SequenceGenerator(name = "hour_request_id_gen", sequenceName = "hour_request_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hour_request_id_gen")
	
	private Long id;
	
	@Column(name="hours")
	private int hours;

	@Column(name="description")
	private String description;
	
	@Column(name = "status")
	private String status;
	
	@ManyToOne
	@JoinColumn(name = "id_student")
	private User student;
	
	@ManyToOne
	@JoinColumn(name = "id_project")
	private Project project;

	public Hour_Request() {
		super();
	}

	public Hour_Request (Long id, int hours, String description, String status, User id_student, Project id_project ) {
		this.id = id;
		this.hours = hours;
		this.description = description;
		this.status = status;
		this.student = id_student;
		this.project = id_project;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getHours() {
		return hours;
	}

	public void setHours(int hours) {
		this.hours = hours;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public User getStudent() {
		return student;
	}

	public void setStudent(User student) {
		this.student = student;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}
}
