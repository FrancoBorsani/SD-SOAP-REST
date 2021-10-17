package com.ecommerce.ecommerce.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.ecommerce.entities.Perfil;
import com.ecommerce.ecommerce.entities.User;
import com.ecommerce.ecommerce.entities.UserRole;
import com.ecommerce.ecommerce.implementation.PerfilService;
import com.ecommerce.ecommerce.services.UsuarioService;


@RestController
@RequestMapping("api/registro")
public class RegistroRestController {
	
	@Autowired
    UsuarioService usuarioService;
	
	@Autowired
    PerfilService perfilService;

	@PostMapping("/create")
	public Perfil createCliente(@RequestBody User user) {
		this.usuarioService.guardarUsuario(user);
		createRole(user);
		return createPerfil(user);
	}
	
	public UserRole createRole(User user) {
		return this.usuarioService.guardarRole(user);
	}
	
	public Perfil createPerfil(User user) {
		return this.usuarioService.guardarPerfil(user);
	}
	

}