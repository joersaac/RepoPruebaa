package com.chimichurris.server.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity(name = "projects")
public class Project implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@SequenceGenerator(name = "project_id_gen", sequenceName = "projects_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "project_id_gen")
	
	private Long id;
	
	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;
	
	@Column(name = "hour_type")
	private String hourType;

	@Column(name = "contact_email")
	private String contact_email;

	@Column(name = "quota")
	private int quota;

	@Column(name = "attendees")
	private int attendees;
	
	@Column(name="hours")
	private int hours;

	@JsonIgnore
	@OneToMany(mappedBy = "idProject", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Inscriptions> inscriptions;

	@JsonIgnore
	@OneToMany(mappedBy = "project", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Hour_Request> hour_requests;

	public Project() {
		super();
	}

	public Project (String name, String description, String hour_type, String contact_email,int quota,int attendees, int hours) {
		this.name = name;
		this.description = description;
		this.hourType = hour_type;
		this.contact_email = contact_email;
		this.quota = quota;
		this.attendees = attendees;
		this.hours = hours;
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

	public String getHour_Type() {
		return hourType;
	}

	public void setHour_Type(String hour_type) {
		this.hourType = hour_type;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContact_Email() {
		return contact_email;
	}

	public void setContact_Email(String contact_email) {
		this.contact_email = contact_email;
	}

	public int getQuota() {
		return quota;
	}

	public void setQuota(int quota) {
		this.quota = quota;
	}

	public int getAttendees() {
		return attendees;
	}

	public void setAttendees(int attendees) {
		this.attendees = attendees;
	}
	public int getHours() {
		return hours;
	}
	public void setHours(int hours) {
		this.hours = hours;
	
	}

	public String getHour_type() {
		return hourType;
	}

	public void setHour_type(String hour_type) {
		this.hourType = hour_type;
	}

	public String getContact_email() {
		return contact_email;
	}

	public void setContact_email(String contact_email) {
		this.contact_email = contact_email;
	}

	public List<Hour_Request> getHour_requests() {
		return hour_requests;
	}

	public void setHour_requests(List<Hour_Request> hour_requests) {
		this.hour_requests = hour_requests;
	}
}