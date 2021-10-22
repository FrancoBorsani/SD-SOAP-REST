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

	@Column(name = "descripcion", nullable=false)
	private String descripcion;

	@Column(name = "estado", nullable=false)
	private String estado;

	@Column(name = "DNIDestinatario", nullable=false)
	private String DNIDestinatario;

	@Column(name = "codigoDeSeguimiento", nullable=false)
	private String codigoDeSeguimiento;
	
	@Column(name = "idOrden", nullable=false)
	private String idOrden;
	
	@Column(name = "vendedor", nullable=false)
	private String vendedor;

	@Column(name = "createdat")
	@CreationTimestamp
	private LocalDateTime createdAt;

	@Column(name = "updatedat")
	@UpdateTimestamp
	private LocalDateTime updatedAt;
	
	public final static String ESTADO_EN_PREPARACION = "En preparación";

	public Envio() {
		super();
	}

	public Envio(String descripcion, String estado, String dNIDestinatario, String codigoDeSeguimiento, String idOrden,
			String vendedor) {
		super();
		this.descripcion = descripcion;
		this.estado = estado;
		DNIDestinatario = dNIDestinatario;
		this.codigoDeSeguimiento = codigoDeSeguimiento;
		this.idOrden = idOrden;
		this.vendedor = vendedor;
	}

	public Envio(int id, String descripcion, String estado, String dNIDestinatario, String codigoDeSeguimiento,
			String idOrden, String vendedor) {
		super();
		this.id = id;
		this.descripcion = descripcion;
		this.estado = estado;
		DNIDestinatario = dNIDestinatario;
		this.codigoDeSeguimiento = codigoDeSeguimiento;
		this.idOrden = idOrden;
		this.vendedor = vendedor;
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

	public String getIdOrden() {
		return idOrden;
	}

	public void setIdOrden(String idOrden) {
		this.idOrden = idOrden;
	}

	public String getVendedor() {
		return vendedor;
	}

	public void setVendedor(String vendedor) {
		this.vendedor = vendedor;
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
				+ DNIDestinatario + ", codigoDeSeguimiento=" + codigoDeSeguimiento + ", idOrden=" + idOrden
				+ ", vendedor=" + vendedor + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}
	
}
