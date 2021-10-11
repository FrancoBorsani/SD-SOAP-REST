package com.ecommerce.ecommerce.entities;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
	
	@Column(name="usuario", nullable=false, length=45)
	private String usuario;
	
	@Column(name="password", nullable=false, length=60)
	private String password;
	
	@Column(name="dni", nullable=false, length=60)
	private String dni;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="user")
	private Set<UserRole> userRoles = new HashSet<UserRole>();
	

	public User() {}
	
	public User(String nombre, String apellido, String usuario, String password, String dni) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.usuario = usuario;
		this.password = password;
		this.dni = dni;
	}
	
	public User(String nombre, String apellido, String usuario, String password, String dni, Set<UserRole> userRoles) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.usuario = usuario;
		this.password = password;
		this.dni = dni;
		this.userRoles = userRoles;

	}

	
	
	
}