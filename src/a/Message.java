package com.chimichurris.server.models.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "message")
public class Message implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@SequenceGenerator(name = "message_id_gen", sequenceName = "message_id_seq", allocationSize = 1)
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
	

	public Message() {
		super();
	}

	public Message (Long id, String message, User sender, User receiver ) {
		this.id = id;
		this.message = message;
		this.sender = sender;
		this.receiver = receiver;
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
}