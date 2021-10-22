package com.ecommerce.ecommerce.security;

public class LoginResponse {

	private String jwt;
	private String username;
	private String nombre;
	private String apellido;
	private String email;

	public LoginResponse() {
		super();
	}

	public LoginResponse(String jwt, String username, String nombre, String apellido, String email) {
		super();
		this.jwt = jwt;
		this.username = username;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
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
	

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "LoginResponse [jwt=" + jwt + ", username=" + username + ", nombre=" + nombre + ", apellido=" + apellido
				+ ", email=" + email + "]";
	}

}
