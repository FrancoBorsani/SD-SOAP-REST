package com.example.modulocorreo.dto.response;

public class LoginResponse {

	private String jwt;
	private String username;
	private String nombre;
	private String apellido;
	private String email;
	private String documento;

	public LoginResponse() {
		super();
	}

	public LoginResponse(String jwt, String username, String nombre, String apellido, String email, String documento) {
		super();
		this.jwt = jwt;
		this.username = username;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.documento = documento;
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

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	@Override
	public String toString() {
		return "LoginResponse [jwt=" + jwt + ", username=" + username + ", nombre=" + nombre + ", apellido=" + apellido
				+ ", email=" + email + ", documento=" + documento + "]";
	}

}
