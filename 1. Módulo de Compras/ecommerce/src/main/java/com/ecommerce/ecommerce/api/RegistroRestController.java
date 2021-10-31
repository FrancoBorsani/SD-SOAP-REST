package com.ecommerce.ecommerce.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

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