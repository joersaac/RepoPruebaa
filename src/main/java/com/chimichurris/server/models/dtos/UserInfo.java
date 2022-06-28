package com.chimichurris.server.models.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserInfo {
	@NotBlank
	@Size(min = 4, max = 32)
	private String username;

	@NotBlank
	private String name;

	@NotBlank
	@Pattern(regexp = "^[^@ \\t\\r\\n]+@[^@ \\t\\r\\n]+\\.[^@ \\t\\r\\n]+$")
	private String email;
	
	@NotBlank
	@Size(min = 8, max = 32)
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,32}$")
	private String password;

	@NotBlank
	@Size(min = 4, max = 32)
	private String rol;

	public UserInfo() {
		super();
	}


	public UserInfo(@NotBlank @Size(min = 4, max = 32) String username,
					@NotBlank String name,
					@NotBlank @Pattern(regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\\\.[A-Za-z0-9-]+)*(\\\\.[A-Za-z]{2,})$") String email,
					@NotBlank @Size(min = 8, max = 32) @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,32}$") String password,
					String rol) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
		this.rol = rol;
		this.name = name;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
