package com.ecommerce.ecommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecommerce.ecommerce.entities.User;
import com.ecommerce.ecommerce.repositories.UsuarioRepository;

@Service
public class UsuarioService {
	@Autowired
	UsuarioRepository usuarioRepository;
	
	
	public User guardarUsuario(User usuario) {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(4);
		usuario.setPassword(bCryptPasswordEncoder.encode(usuario.getPassword())); 
		usuario.setEnabled(true);
		return usuarioRepository.save(usuario);	
	}

}
