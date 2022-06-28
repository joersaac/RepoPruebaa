package com.chimichurris.server.models.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity(name = "messages")
public class Message implements Serializable {

	@Id
	@Column(name = "id")
	@SequenceGenerator(name = "message_id_gen", sequenceName = "messages_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "message_id_gen")
	
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "sender")
	private User sender;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "reciver")
	private User receiver;
	
	@Column(name = "message")
	private String message;

	@Column(name="time")
	private Timestamp time;

	@Column(name = "state")
	private boolean state;
	

	public Message() {
		super();
	}

	public Message (Long id, String message, User sender, User receiver , Timestamp time) {
		this.id = id;
		this.message = message;
		this.sender = sender;
		this.receiver = receiver;
		this.time = time;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public User getReceiver() {
		return receiver;
	}

	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}
}