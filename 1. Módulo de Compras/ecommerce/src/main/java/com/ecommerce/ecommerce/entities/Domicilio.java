package com.ecommerce.ecommerce.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="domicilio")
public class Domicilio {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="calle", nullable=false, length=45)
	private String calle;
	
	@Column(name="numero",nullable=false, length=45)
	private String numero;
	
	@Column(name="codigoPostal", nullable=false, length=60)
	private String codigoPostal;
	
	@Column(name="provincia", nullable=false, length=60)
	private String provincia;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id", nullable=false)
	private User user;
	
	
	
	public Domicilio() {}
	
	public Domicilio(int id, String calle, String numero, String codigoPostal, String provincia, User user) {
		this.id = id;
		this.calle = calle;
		this.numero = numero;
		this.codigoPostal = codigoPostal;
		this.provincia = provincia;
		this.user = user;
	}

	public Domicilio(String calle, String numero, String codigoPostal, String provincia, User user) {
		super();
		this.calle = calle;
		this.numero = numero;
		this.codigoPostal = codigoPostal;
		this.provincia = provincia;
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
	
}
