package com.ecommerce.ecommerce.api;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.ecommerce.entities.User;
import com.ecommerce.ecommerce.entities.UserRole;
import com.ecommerce.ecommerce.implementation.PerfilService;
import com.ecommerce.ecommerce.implementation.UserRoleService;
import com.ecommerce.ecommerce.repositories.IUserRoleRepository;
import com.ecommerce.ecommerce.security.UserResponse;
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
	
	
	@PostMapping("/update")
	public ResponseEntity<UserResponse> updateProfile(@RequestBody User user) {
			
		User userActualizado = this.usuarioService.actualizarUsuario(user);
		
        List<String> roles = userActualizado.getUserRoles().stream()
        		.map(item -> item.getRole())
        		.collect(Collectors.toList());
		
		return new ResponseEntity<UserResponse>(new UserResponse(userActualizado.getId(), userActualizado.getUsername(), userActualizado.getNombre(), userActualizado.getApellido(), userActualizado.getDni(), userActualizado.getEmail(), userActualizado.getTelefono(), roles), HttpStatus.ACCEPTED);
	}
	
}