package com.ecommerce.ecommerce.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.ecommerce.entities.Tarjeta;
import com.ecommerce.ecommerce.services.TarjetaService;
import com.ecommerce.ecommerce.services.UsuarioService;

@RestController
@RequestMapping("api/v1/tarjeta")
@CrossOrigin("*")
public class TarjetaRestController {

	@Autowired
	UsuarioService usuarioService;

	@Autowired
	TarjetaService tarjetaService;

	@PostMapping("/agregar")
	public Tarjeta createTarjeta(@RequestBody Tarjeta tarjeta) {
		String username = "";
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		}

		tarjeta.setUser(usuarioService.traerUser(username));

		return tarjetaService.guardarTarjeta(tarjeta);
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
