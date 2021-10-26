package com.ecommerce.ecommerce.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="perfil")
public class Perfil {
	
	@Id
	private int id;
	
	@Column(name = "username")
	private String username;

	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "apellido")
	private String apellido;
	
	@Column(name = "user_role")
	private String userRol;
	
	@Column(name = "dni")
	private String dni;
	
	@Column(name = "telefono")
	private String telefono;
	

	public Perfil(int id, String username, String userRol , String nombre, String apellido, String dni) {
		this.id = id;
		this.username = username;
		this.nombre = nombre;
		this.userRol = userRol;
		this.apellido = apellido;
		this.dni = dni;
	}


	public Perfil() {
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
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


	public String getUserRol() {
		return userRol;
	}


	public void setUserRol(String userRol) {
		this.userRol = userRol;
	}


	public String getDni() {
		return dni;
	}


	public void setDni(String dni) {
		this.dni = dni;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getTelefono() {
		return telefono;
	}


	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	
	
	
	
	
}
