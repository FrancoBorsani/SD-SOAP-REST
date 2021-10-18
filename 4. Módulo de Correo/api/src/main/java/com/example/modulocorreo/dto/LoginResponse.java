package com.example.modulocorreo.dto;

public class LoginResponse {

	private String jwt;
	private String username;

	public LoginResponse() {
		super();
	}

	public LoginResponse(String jwt, String username) {
		super();
		this.jwt = jwt;
		this.username = username;
	}

	public String getJwt() {
		return jwt;
	}

	public void setJwt(String jwt) {
		this.jwt = jwt;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "LoginResponse [jwt=" + jwt + ", username=" + username + "]";
	}

}
