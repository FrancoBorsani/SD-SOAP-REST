package com.ecommerce.ecommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecommerce.ecommerce.entities.Perfil;
import com.ecommerce.ecommerce.entities.User;
import com.ecommerce.ecommerce.entities.UserRole;
import com.ecommerce.ecommerce.implementation.PerfilService;
import com.ecommerce.ecommerce.repositories.IPerfilRepository;
import com.ecommerce.ecommerce.repositories.IUserRoleRepository;
//import com.ecommerce.ecommerce.repositories.IUserRoleRepository;
import com.ecommerce.ecommerce.repositories.UsuarioRepository;

@Service
public class UsuarioService {
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	PerfilService perfilService;
	
	@Autowired
	IPerfilRepository perfilRepository;
	
	@Autowired
	IUserRoleRepository userRoleRepository;
	
	
	public User guardarUsuario(User usuario) {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(4);
		usuario.setPassword(bCryptPasswordEncoder.encode(usuario.getPassword())); 
		usuario.setEnabled(true);
		return usuarioRepository.save(usuario);	
	}
	
	
	
	public UserRole guardarRole(User usuario) {
		return userRoleRepository.save(new UserRole(usuarioRepository.findByIdUser(usuario.getId()),"ROLE_USER"));			
	}

	public Perfil guardarPerfil(User usuario) {
		return perfilService.addNewProfile(usuario);
	}
	
	
	public User traerUser(int id) {
		return usuarioRepository.findByIdUser(id);
	}
	
	public User traerUser(String username) {
		return usuarioRepository.findByUsername(username);
	}
	
	
	public User actualizarUsuario(User usuario) {
		return usuarioRepository.save(usuario);	
	}
	

}
