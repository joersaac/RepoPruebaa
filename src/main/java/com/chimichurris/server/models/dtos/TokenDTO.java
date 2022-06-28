package com.chimichurris.server.models.dtos;

import com.chimichurris.server.models.entities.User;

public class TokenDTO {
	private String token;

	private User user;

	public TokenDTO(String token, User user) {
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
