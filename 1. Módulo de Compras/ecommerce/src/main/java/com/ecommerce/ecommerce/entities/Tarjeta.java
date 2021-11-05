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
@Table(name="tarjeta")
public class Tarjeta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="tipo", nullable=false, length=45)
	private String tipo;
	
	@Column(name="numero",nullable=false, length=45)
	private String numero;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id", nullable=false)
	private User user;
	
	public Tarjeta() {}
	
	public Tarjeta(int id, String tipo, String numero, User user) {
		this.id = id;
		this.tipo = tipo;
		this.numero = numero;
		this.user = user;
	}

	public Tarjeta(String tipo, String numero, User user) {
		super();
		this.tipo = tipo;
		this.numero = numero;
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
	
	

	
	
}
