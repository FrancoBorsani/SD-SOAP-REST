package com.ecommerce.ecommerce.entities;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name="user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="nombre", nullable=false, length=45)
	private String nombre;
	
	@Column(name="apellido",nullable=false, length=45)
	private String apellido;
	
	@Column(name="password", nullable=false, length=60)
	private String password;
	
	@Column(name="dni", nullable=false, length=60)
	private String dni;
	
	@Column(name="username", nullable=true, length=45)
	private String username;
	
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="user")
	private Set<UserRole> userRoles = new HashSet<UserRole>();
	
	@Column(name="enabled")
	private boolean enabled;
	

	public User() {}
	
	public User(String username, String nombre, String apellido, String password, String dni, boolean enabled) {
		this.username = username;
		this.nombre = nombre;
		this.apellido = apellido;
		this.password = password;
		this.dni = dni;
		this.enabled = true;

	}
	
	public User(String username, String nombre, String apellido, String password, String dni, boolean enabled, Set<UserRole> userRoles) {
		this.username = username;
		this.nombre = nombre;
		this.apellido = apellido;
		this.password = password;
		this.dni = dni;
		this.userRoles = userRoles;
		this.enabled = true;


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


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public Set<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	
	
}