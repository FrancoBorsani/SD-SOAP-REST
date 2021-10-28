package com.example.modulocorreo.controllers;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.modulocorreo.dto.request.CreateEnvioRequest;
import com.example.modulocorreo.dto.response.ApiMessageResponse;
import com.example.modulocorreo.dto.response.InsertOrUpdateEnvioResponse;
import com.example.modulocorreo.entities.Envio;
import com.example.modulocorreo.services.IEnvioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/api/v1/envios")
@CrossOrigin("*")
@Validated
public class EnvioRestController {

	@Autowired
	@Qualifier("envioService")
	private IEnvioService envioService;

	@Operation(summary = "Listar todos los envios.")
	@ApiResponses(value = @ApiResponse(responseCode = "200", content = {
			@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Envio.class))) }))
	@GetMapping("")
	public ResponseEntity<List<Envio>> getAllEnvios() {
		return ResponseEntity.ok(envioService.getAll());
	}

	@Operation(summary = "Crear un nuevo envio.")
	@ApiResponses(value = @ApiResponse(responseCode = "201", description = "Envio creado correctamente.", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = Envio.class)) }))
	@PostMapping("")
	public ResponseEntity<?> createEnvio(@Valid @RequestBody CreateEnvioRequest createEnvioRequest) {

		Envio envio = envioService.insertOrUpdate(new Envio(createEnvioRequest.getDescripcion(),
				Envio.ESTADO_EN_PREPARACION, createEnvioRequest.getDniDestinatario(), UUID.randomUUID().toString(),
				createEnvioRequest.getVendedor(), createEnvioRequest.getIdOrden()));

		return ResponseEntity.status(HttpStatus.CREATED).body(
				new InsertOrUpdateEnvioResponse(new ApiMessageResponse(true, "Envio creado correctamente."), envio));
	}

	@Operation(summary = "Traer envio por ID.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Envio encontrado.", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Envio.class)) }),
			@ApiResponse(responseCode = "404", description = "Envio no encontrado", content = @Content) })
	@GetMapping("/{id}")
	public ResponseEntity<?> getEnvio(@PathVariable("id") int idEnvio) {

		Envio envio = envioService.findById(idEnvio);

		if (envio == null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ApiMessageResponse(false, "Envio no encontrado."));

		return ResponseEntity.ok(envio);
	}

	@Operation(summary = "Traer envio por codigo de seguimiento.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Envio encontrado.", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Envio.class)) }),
			@ApiResponse(responseCode = "404", description = "Envio no encontrado", content = @Content) })
	@GetMapping("/codigo/{codigoDeSeguimiento}")
	public ResponseEntity<?> getEnvioByCodigoDeSeguimiento(
			@PathVariable("codigoDeSeguimiento") String codigoDeSeguimiento) {

		Envio envio = envioService.findByCodigoDeSeguimiento(codigoDeSeguimiento);

		if (envio == null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ApiMessageResponse(false, "Envio no encontrado."));

		return ResponseEntity.ok(envio);
	}

	@Operation(summary = "Actualizar estado de envio en curso.")
	@PutMapping("/{id}")
	public ResponseEntity<?> updateEstadoDeEnvio(@PathVariable("id") int idEnvio,
			@RequestParam(name = "estado") String estado) {

		Envio envio = envioService.findById(idEnvio);

		if (envio == null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ApiMessageResponse(false, "Envio no encontrado."));

		envio.setEstado(estado);

		Envio envioActualizado = envioService.insertOrUpdate(envio);

		return ResponseEntity.status(HttpStatus.CREATED).body((new InsertOrUpdateEnvioResponse(
				new ApiMessageResponse(true, "Envio del envio actualizado correctamente."), envioActualizado)));
	}

}
