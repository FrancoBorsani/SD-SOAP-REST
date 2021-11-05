package com.ecommerce.ecommerce.security;

import java.util.List;


public class UserResponse {
	
	private long id;

	private String username;

	private String nombre;

	private String apellido;

	private String dni;

	private String email;

	private String telefono;

	private List<String> roles;

	public UserResponse() {
		super();
	}

	public UserResponse(long id, String username, String nombre, String apellido, String dni, String email,
			String telefono, List<String> roles) {
		super();
		this.id = id;
		this.username = username;
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.email = email;
		this.telefono = telefono;
		this.roles = roles;
	}
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

}
