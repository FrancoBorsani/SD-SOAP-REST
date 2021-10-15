package com.example.modulocorreo.dto;

import javax.validation.constraints.NotBlank;

public class CreateEnvioRequest {
	
    @NotBlank
    private String descripcion;

    @NotBlank
    private String dniDestinatario;

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

}
