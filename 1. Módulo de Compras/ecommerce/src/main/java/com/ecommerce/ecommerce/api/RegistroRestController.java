package com.ecommerce.ecommerce.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.ecommerce.entities.Perfil;
import com.ecommerce.ecommerce.entities.User;
//import com.ecommerce.ecommerce.entities.UserRole;
//import com.ecommerce.ecommerce.implementation.PerfilService;
import com.ecommerce.ecommerce.services.UsuarioService;


@RestController
@RequestMapping("api/registro")
public class RegistroRestController {
	
	@Autowired
    UsuarioService usuarioService;
	
//	@Autowired
 //   PerfilService perfilService;

	@PostMapping("/create")
	public User createCliente(@RequestBody User user) {
		return this.usuarioService.guardarUsuario(user);
		//createRole(user);
		//return createPerfil(user);
	}
	/*
	public UserRole createRole(User user) {
		return this.usuarioService.guardarRole(user);
	}
	
	public Perfil createPerfil(User user) {
		return this.usuarioService.guardarPerfil(user);
	}

*/
	
	
	@PostMapping("/updateProfile")
	public User updateProfile(@RequestBody User user) {
			return this.usuarioService.actualizarUsuario(user);
			//User perfilUser = this.usuarioService.traerUser(editPerfil.getId());
			//System.out.println(perfilUser);
			//perfilUser.setNombre(editPerfil.getNombre());
			//perfilUser.setApellido(editPerfil.getApellido());
			//perfilUser.setDni(editPerfil.getDni());
			//perfilUser.setTelefono(editPerfil.getTelefono());
			
			//return this.usuarioService.actualizarUsuario(perfilUser);
	}
	
}