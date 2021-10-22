package com.example.modulocorreo.dto.request;

import javax.validation.constraints.NotBlank;

public class CreateEnvioRequest {
	
    @NotBlank
    private String descripcion;

    @NotBlank
    private String dniDestinatario;
    
    @NotBlank
    private String vendedor;
    
    @NotBlank
    private String idOrden;

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDniDestinatario() {
		return dniDestinatario;
	}

	public void setDniDestinatario(String dniDestinatario) {
		this.dniDestinatario = dniDestinatario;
	}

	public String getVendedor() {
		return vendedor;
	}

	public void setVendedor(String vendedor) {
		this.vendedor = vendedor;
	}

	public String getIdOrden() {
		return idOrden;
	}

	public void setIdOrden(String idOrden) {
		this.idOrden = idOrden;
	}

	@Override
	public String toString() {
		return "CreateEnvioRequest [descripcion=" + descripcion + ", dniDestinatario=" + dniDestinatario + ", vendedor="
				+ vendedor + ", idOrden=" + idOrden + "]";
	}

}
