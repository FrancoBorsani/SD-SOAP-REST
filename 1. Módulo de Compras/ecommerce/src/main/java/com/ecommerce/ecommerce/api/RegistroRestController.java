package com.ecommerce.ecommerce.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.ecommerce.entities.User;
import com.ecommerce.ecommerce.entities.UserRole;
import com.ecommerce.ecommerce.implementation.PerfilService;
import com.ecommerce.ecommerce.implementation.UserRoleService;
import com.ecommerce.ecommerce.repositories.IUserRoleRepository;
//import com.ecommerce.ecommerce.entities.UserRole;
//import com.ecommerce.ecommerce.implementation.PerfilService;
import com.ecommerce.ecommerce.services.UsuarioService;


@RestController
@RequestMapping("api/v1/registro")
public class RegistroRestController {
	
	@Autowired
    UsuarioService usuarioService;
	
	@Autowired
	UserRoleService userRoleService;
	
	@Autowired
	IUserRoleRepository userRoleRepository;
	
	@Autowired
    PerfilService perfilService;

	@PostMapping("/create")
	public User createCliente(@RequestBody User user) {		
		
		User userReturn = usuarioService.guardarUsuario(user);
		
		userRoleService.saveUser(new UserRole(usuarioService.traerUser(user.getUsername()),"ROLE_USER"));			
		
		usuarioService.guardarPerfil(user);
		
		return userReturn;
	}
	
	
	@PostMapping("/updateProfile")
	public User updateProfile(@RequestBody User user) {
			return this.usuarioService.actualizarUsuario(user);
	}
	
}