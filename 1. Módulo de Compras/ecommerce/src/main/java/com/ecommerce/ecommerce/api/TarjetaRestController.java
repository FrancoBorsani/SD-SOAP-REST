package com.ecommerce.ecommerce.api;

import java.util.List;

import com.ecommerce.ecommerce.banca.BancaSoapClient;
import com.ecommerce.ecommerce.entities.User;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
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
		String validacion = banca.validar_tarjeta(Long.valueOf(tarjeta.getNumero()), u.getNombre(), u.getApellido(), Long.valueOf(u.getDni()));

		if (validacion.equals("1")){
			return new ResponseEntity<>(tarjetaService.guardarTarjeta(tarjeta), HttpStatus.OK);
		}
		else{
			throw new RuntimeException("La tarjeta no le pertenece.");
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

}
