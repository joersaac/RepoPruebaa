package com.chimichurris.server.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity(name = "user")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@SequenceGenerator(name = "user_id_gen", sequenceName = "user_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_gen")
	//TODO: Settear el tamaño de salto a 1
	private Long id;
	
	@Column(name = "username")
	private String username;

	@Column(name = "name")
	private String name;
	
	@Column(name = "email")
	private String email;

	@Column(name = "password")
	private String password;

	@Column(name = "rol")
	private String rol;

	@Column(name = "img")
	private String img;

	@Column(name="internal_hours")
	private int int_hour;

	@Column(name="external_hours")
	private int ext_hour;

	@Column(name = "unreadmessages")
	private int unreadmessages;

	@JsonIgnore
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Token> tokens;

	@JsonIgnore
	@OneToMany(mappedBy = "idStudent", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Inscriptions> inscriptions;

	@JsonIgnore
	@OneToMany(mappedBy = "student", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Hour_Request> hour_requests;

	@JsonIgnore
	@OneToMany(mappedBy = "sender", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Message> send_Messages;

	@JsonIgnore
	@OneToMany(mappedBy = "receiver", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Message> receive_Messages;

	public User() {
		super();
	}

	public User(long id,String username, String name, String email, String password, String rol, String img, int int_hour, int ext_hour) {
		this.id = id;
		this.username = username;
		this.name = name;
		this.email = email;
		this.password = password;
		this.rol = rol;
		this.img = img;
		this.int_hour = int_hour;
		this.ext_hour = ext_hour;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getInt_hour() {
		return int_hour;
	}

	public void setInt_hour(int int_hour) {
		this.int_hour = int_hour;
	}

	public int getExt_hour() {
		return ext_hour;
	}

	public void setExt_hour(int ext_hour) {
		this.ext_hour = ext_hour;
	}

	public List<Token> getTokens() {
		return tokens;
	}

	public void setTokens(List<Token> tokens) {
		this.tokens = tokens;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public List<Message> getSend_Messages() {
		return send_Messages;
	}

	public void setSend_Messages(List<Message> send_Messages) {
		this.send_Messages = send_Messages;
	}

	public List<Message> getReceive_Messages() {
		return receive_Messages;
	}

	public void setReceive_Messages(List<Message> receive_Messages) {
		this.receive_Messages = receive_Messages;
	}

	public int getUnreadmessages() {
		return unreadmessages;
	}

	public void setUnreadmessages(int unreadmessages) {
		this.unreadmessages = unreadmessages;
	}
}
