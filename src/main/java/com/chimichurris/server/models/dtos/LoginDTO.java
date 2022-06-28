package com.chimichurris.server.models.dtos;

import javax.validation.constraints.NotBlank;

public class LoginDTO {
	@NotBlank
	private String identifier;

	private String email;
	
	@NotBlank
	private String password;

	public LoginDTO(String identifier, String password) {
		super();
		this.identifier = identifier;
		this.password = password;
	}

	public LoginDTO(String identifier, String password, String email) {
		super();
		this.email = email;
		this.identifier = identifier;
		this.password = password;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LoginDTO() {
		super();
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
