package com.example.modulocorreo.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "envio")
public class Envio {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "descripcion")
	private String descripcion;

	@Column(name = "estado")
	private String estado;

	@Column(name = "DNIDestinatario")
	private String DNIDestinatario;

	@Column(name = "codigoDeSeguimiento")
	private String codigoDeSeguimiento;

	@Column(name = "createdat")
	@CreationTimestamp
	private LocalDateTime createdAt;

	@Column(name = "updatedat")
	@UpdateTimestamp
	private LocalDateTime updatedAt;

	public Envio() {
		super();
	}

	public Envio(String descripcion, String estado, String dNIDestinatario, String codigoDeSeguimiento) {
		super();
		this.descripcion = descripcion;
		this.estado = estado;
		this.DNIDestinatario = dNIDestinatario;
		this.codigoDeSeguimiento = codigoDeSeguimiento;
	}

	public Envio(int id, String descripcion, String estado, String dNIDestinatario, String codigoDeSeguimiento) {
		super();
		this.id = id;
		this.descripcion = descripcion;
		this.estado = estado;
		this.DNIDestinatario = dNIDestinatario;
		this.codigoDeSeguimiento = codigoDeSeguimiento;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getDNIDestinatario() {
		return DNIDestinatario;
	}

	public void setDNIDestinatario(String dNIDestinatario) {
		DNIDestinatario = dNIDestinatario;
	}

	public String getCodigoDeSeguimiento() {
		return codigoDeSeguimiento;
	}

	public void setCodigoDeSeguimiento(String codigoDeSeguimiento) {
		this.codigoDeSeguimiento = codigoDeSeguimiento;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Override
	public String toString() {
		return "Envio [id=" + id + ", descripcion=" + descripcion + ", estado=" + estado + ", DNIDestinatario="
				+ DNIDestinatario + ", codigoDeSeguimiento=" + codigoDeSeguimiento + ", createdAt=" + createdAt
				+ ", updatedAt=" + updatedAt + "]";
	}
	
}
