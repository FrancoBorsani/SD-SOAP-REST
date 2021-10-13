package com.example.modulocorreo.endpoints;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import io.spring.modulo_de_correo.GetEnvioRequest;
import io.spring.modulo_de_correo.GetEnvioResponse;

import io.spring.modulo_de_correo.CreateEnvioRequest;
import io.spring.modulo_de_correo.CreateEnvioResponse;
import io.spring.modulo_de_correo.Envio;
import io.spring.modulo_de_correo.GetAllEnviosResponse;
import io.spring.modulo_de_correo.ResponseStatus;
import io.spring.modulo_de_correo.UpdateEnvioResponse;
import io.spring.modulo_de_correo.UpdateEnvioRequest;

@Endpoint
public class EnvioEndpoint {
	private static final String NAMESPACE_URI = "http://spring.io/modulo-de-correo";

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getEnvioRequest")
	@ResponsePayload
	public GetEnvioResponse getEnvio(@RequestPayload GetEnvioRequest request) {

		GetEnvioResponse response = new GetEnvioResponse();

		Envio envio = new Envio();

		envio.setCodigoDeSeguimiento("12");
		envio.setDescripcion("Descripcion");
		envio.setDNIDestinatario("42200255");
		envio.setEstado("En preparación");
		envio.setId(1);

		response.setEnvio(envio);

		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "createEnvioRequest")
	@ResponsePayload
	public CreateEnvioResponse createEnvio(@RequestPayload CreateEnvioRequest request) {

		CreateEnvioResponse response = new CreateEnvioResponse();
		ResponseStatus responseStatus = new ResponseStatus();

		responseStatus.setCode("0");
		responseStatus.setMessage("Created successfully.");

		response.setResponseStatus(responseStatus);

		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateEnvioRequest")
	@ResponsePayload
	public UpdateEnvioResponse updateEnvio(@RequestPayload UpdateEnvioRequest request) {

		UpdateEnvioResponse response = new UpdateEnvioResponse();
		ResponseStatus responseStatus = new ResponseStatus();

		responseStatus.setCode("0");
		responseStatus.setMessage("Updated successfully.");

		response.setResponseStatus(responseStatus);

		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllEnviosRequest")
	@ResponsePayload
	public GetAllEnviosResponse getAllEnvios() {

		GetAllEnviosResponse response = new GetAllEnviosResponse();

		return response;
	}

}
