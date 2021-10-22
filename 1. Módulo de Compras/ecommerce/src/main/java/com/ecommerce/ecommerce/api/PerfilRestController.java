/*package com.ecommerce.ecommerce.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.ecommerce.ecommerce.entities.Perfil;
import com.ecommerce.ecommerce.entities.User;
import com.ecommerce.ecommerce.entities.UserRole;
import com.ecommerce.ecommerce.implementation.PerfilService;
import com.ecommerce.ecommerce.services.UsuarioService;

import ch.qos.logback.core.status.Status;


@RestController
@RequestMapping("api/perfil")
public class PerfilRestController {
	
	@Autowired
    UsuarioService usuarioService;
	
	@Autowired
    PerfilService perfilService;
/*
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
	*/
/*
	@PostMapping("/updateProfile")
	public User updateProfile(@RequestBody Perfil editPerfil) {
		System.out.println("PERFILLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL");
		System.out.println(editPerfil);
			perfilService.updateProfile(editPerfil);
			User perfilUser = this.usuarioService.traerUser(editPerfil.getId());
			System.out.println(perfilUser);
			perfilUser.setNombre(editPerfil.getNombre());
			perfilUser.setApellido(editPerfil.getApellido());
			perfilUser.setDni(editPerfil.getDni());
			perfilUser.setTelefono(editPerfil.getTelefono());
			
			return this.usuarioService.actualizarUsuario(perfilUser);
	}
*/

//}