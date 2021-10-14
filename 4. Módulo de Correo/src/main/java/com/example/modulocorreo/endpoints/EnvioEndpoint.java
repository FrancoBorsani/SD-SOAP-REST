package com.example.modulocorreo.endpoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.example.modulocorreo.services.IEnvioService;

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

	@Autowired
	@Qualifier("envioService")
	private IEnvioService envioService;

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getEnvioRequest")
	@ResponsePayload
	public GetEnvioResponse getEnvio(@RequestPayload GetEnvioRequest request) {

		GetEnvioResponse response = new GetEnvioResponse();

		com.example.modulocorreo.entities.Envio envio = envioService
				.findByCodigoDeSeguimiento(request.getCodigoDeSeguimiento());

		if (envio != null) {
			response.setEnvio(entityToModel(envio));
		}

		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "createEnvioRequest")
	@ResponsePayload
	public CreateEnvioResponse createEnvio(@RequestPayload CreateEnvioRequest request) {

		CreateEnvioResponse response = new CreateEnvioResponse();
		ResponseStatus responseStatus = new ResponseStatus();

		Envio envio = entityToModel(envioService.insertOrUpdate(new com.example.modulocorreo.entities.Envio(
				request.getDescripcion(), "En preparación", request.getDniDestinatario(), "558")));

		response.setEnvio(envio);
		
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
		
		com.example.modulocorreo.entities.Envio envio = envioService
				.findByCodigoDeSeguimiento(request.getCodigoDeSeguimiento());
		
		envioService.insertOrUpdate(envio);
		
		if (envio == null) {
			
			responseStatus.setCode("100");
			responseStatus.setMessage("Envio not found.");
	
		} else {
			envio.setEstado(request.getEstado());
			
			envioService.insertOrUpdate(envio);
			
			response.setEnvio(entityToModel(envio));

			responseStatus.setCode("0");
			responseStatus.setMessage("Updated successfully.");
		}

		response.setResponseStatus(responseStatus);

		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllEnviosRequest")
	@ResponsePayload
	public GetAllEnviosResponse getAllEnvios() {

		GetAllEnviosResponse response = new GetAllEnviosResponse();
		
		for (com.example.modulocorreo.entities.Envio e : envioService.getAll()) {
			response.getEnvios().add(entityToModel(e));
		}

		return response;
	}

	public Envio entityToModel(com.example.modulocorreo.entities.Envio envio) {

		Envio e = new Envio();

		e.setId(envio.getId());
		e.setCodigoDeSeguimiento(envio.getCodigoDeSeguimiento());
		e.setDescripcion(envio.getDescripcion());
		e.setDNIDestinatario(envio.getDNIDestinatario());
		e.setEstado(envio.getEstado());
		e.setFecha(envio.getCreatedAt().toString());

		return e;
	}

}
