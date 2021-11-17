package com.ecommerce.ecommerce.correo;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.client.RestTemplate;

public class CorreoRestClient {
	
	private static final String GET_ENVIO_BY_CODIGO_ENVIO_API = "http://localhost:8083/api/v1/envios/codigo/{codigoDeSeguimiento}";
	
	private static final String CREATE_ENVIO_API = "http://localhost:8083/api/v1/envios/create";

	static RestTemplate restTemplate = new RestTemplate();

	public static EnvioResponse callGetEnvioByCodigoAPI(String codigoDeSeguimiento) {
		
		Map<String, String> param = new HashMap<>();
		
		param.put("codigoDeSeguimiento", codigoDeSeguimiento);
		
		EnvioResponse response = restTemplate.getForObject(GET_ENVIO_BY_CODIGO_ENVIO_API, EnvioResponse.class, param);
				
		return response;
		
	}
	
	public static EnvioResponse callCreateEnvioAPI(String descripcion, String dniDestinatario, String idOrden, String vendedor) {
				
		CreateEnvioRequest request = new CreateEnvioRequest(descripcion, dniDestinatario, idOrden, vendedor);
				
		EnvioResponse response = restTemplate.postForObject(CREATE_ENVIO_API, request, EnvioResponse.class);
	
		return response;
	}
	
}
