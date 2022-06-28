package com.chimichurris.server.models.dtos;

import com.chimichurris.server.models.entities.User;

public class TokenDTO {
	private String token;

	private UserDTO user;

	public TokenDTO(String token, UserDTO user) {
		super();
		this.token = token;
		this.user = user;
	}

	public TokenDTO() {
		super();
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}
}
