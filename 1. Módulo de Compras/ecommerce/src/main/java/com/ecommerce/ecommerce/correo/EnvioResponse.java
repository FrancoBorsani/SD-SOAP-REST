package com.ecommerce.ecommerce.correo;

import java.time.LocalDateTime;

public class EnvioResponse {
	
	private int id;

	private String descripcion;

	private String estado;

	private String DNIDestinatario;

	private String codigoDeSeguimiento;
	
	private String idOrden;
	
	private String vendedor;

	private LocalDateTime createdAt;
	
	public EnvioResponse() {
		super();
	}

	public EnvioResponse(int id, String descripcion, String estado, String dNIDestinatario, String codigoDeSeguimiento,
			String idOrden, String vendedor, LocalDateTime createdAt) {
		super();
		this.id = id;
		this.descripcion = descripcion;
		this.estado = estado;
		DNIDestinatario = dNIDestinatario;
		this.codigoDeSeguimiento = codigoDeSeguimiento;
		this.idOrden = idOrden;
		this.vendedor = vendedor;
		this.createdAt = createdAt;
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

	@Override
	public String toString() {
		return "EnvioResponse [id=" + id + ", descripcion=" + descripcion + ", estado=" + estado + ", DNIDestinatario="
				+ DNIDestinatario + ", codigoDeSeguimiento=" + codigoDeSeguimiento + ", idOrden=" + idOrden
				+ ", vendedor=" + vendedor + ", createdAt=" + createdAt + "]";
	}

}
