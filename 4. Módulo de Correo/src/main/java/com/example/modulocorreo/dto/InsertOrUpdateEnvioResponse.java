package com.example.modulocorreo.dto;

import com.example.modulocorreo.entities.Envio;

public class InsertOrUpdateEnvioResponse {
	
	private ApiResponse apiResponse;
    private Envio envio;

    public InsertOrUpdateEnvioResponse(ApiResponse apiResponse, Envio envio) {
        this.apiResponse = apiResponse;
        this.envio = envio;
    }

	public ApiResponse getApiResponse() {
		return apiResponse;
	}

	public void setApiResponse(ApiResponse apiResponse) {
		this.apiResponse = apiResponse;
	}

	public Envio getEnvio() {
		return envio;
	}

	public void setEnvio(Envio envio) {
		this.envio = envio;
	}

}
