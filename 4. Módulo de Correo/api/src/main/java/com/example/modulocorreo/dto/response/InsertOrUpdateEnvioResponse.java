package com.example.modulocorreo.dto.response;

import com.example.modulocorreo.entities.Envio;

public class InsertOrUpdateEnvioResponse {
	
	private ApiMessageResponse apiResponse;
    private Envio envio;

    public InsertOrUpdateEnvioResponse(ApiMessageResponse apiResponse, Envio envio) {
        this.apiResponse = apiResponse;
        this.envio = envio;
    }

	public ApiMessageResponse getApiResponse() {
		return apiResponse;
	}

	public void setApiResponse(ApiMessageResponse apiResponse) {
		this.apiResponse = apiResponse;
	}

	public Envio getEnvio() {
		return envio;
	}

	public void setEnvio(Envio envio) {
		this.envio = envio;
	}

}
