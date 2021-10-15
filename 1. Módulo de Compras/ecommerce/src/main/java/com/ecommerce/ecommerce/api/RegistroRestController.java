package com.ecommerce.ecommerce.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.ecommerce.entities.User;
import com.ecommerce.ecommerce.services.UsuarioService;


@RestController
@RequestMapping("api/registro")
public class RegistroRestController {
	
	@Autowired
    UsuarioService usuarioService;

	@PostMapping("/create")
	public User createCliente(@RequestBody User user) {
		return this.usuarioService.guardarUsuario(user);
	}

}