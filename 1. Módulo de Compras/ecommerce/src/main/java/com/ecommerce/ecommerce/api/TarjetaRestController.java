package com.ecommerce.ecommerce.api;

import java.util.List;

import com.ecommerce.ecommerce.banca.BancaSoapClient;
import com.ecommerce.ecommerce.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.ecommerce.entities.Tarjeta;
import com.ecommerce.ecommerce.services.TarjetaService;
import com.ecommerce.ecommerce.services.UsuarioService;

@RestController
@RequestMapping("/api/v1/tarjeta")
@CrossOrigin("*")
public class TarjetaRestController {

	@Autowired
	UsuarioService usuarioService;

	@Autowired
	@Qualifier("tarjetaService")
	TarjetaService tarjetaService;

	@Autowired
	BancaSoapClient banca;

	@PostMapping("/agregar")
	public ResponseEntity<Tarjeta> createTarjeta(@RequestBody Tarjeta tarjeta){
		String username = "";
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		}

		User u = usuarioService.traerUser(username);

		tarjeta.setUser(u);
		String validacion = banca.validar_tarjeta(tarjeta.getNumero(), tarjeta.getTipo(), u.getNombre(), u.getApellido(), Long.valueOf(u.getDni()));

		List<Tarjeta> listaTarjetas = tarjetaService.findByIdUser(u.getId());

		boolean duplicada = false;

		for(Tarjeta t : listaTarjetas){
			if (t.getTipo().equals(tarjeta.getTipo()) && t.getNumero().equals(tarjeta.getNumero())){
				duplicada = true;
				break;
			}
		}

		if (validacion.equals("1") && !duplicada){
			return new ResponseEntity<>(tarjetaService.guardarTarjeta(tarjeta), HttpStatus.OK);
		}
		else if(duplicada){
			//return new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);
			throw new RuntimeException("Error: Tarjeta duplicada");
		}
		else{
			//return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			throw new RuntimeException("Datos invalidos: La tarjeta ingresada no coincide con los datos personales del usuario logueado.");
		}


	}

	@GetMapping("/getTarjetasDeUsuario")
	public List<Tarjeta> getTarjetasDeUsuario() {
		String username = "";

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		}

		return this.tarjetaService.findByIdUser(this.usuarioService.traerUser(username).getId());
	}

	@GetMapping("/getTarjetaById")
	public ResponseEntity<Tarjeta> getTarjetaById(@RequestParam(name="idTarjeta") long idTarjeta) {
		Tarjeta tarjeta = tarjetaService.findById((int) idTarjeta);
		System.out.println(tarjeta);
		return new ResponseEntity<Tarjeta>(tarjeta, HttpStatus.OK);
	}

}
