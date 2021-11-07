package com.ecommerce.ecommerce.implementation;

import com.ecommerce.ecommerce.entities.Perfil;
import com.ecommerce.ecommerce.entities.User;
import com.ecommerce.ecommerce.repositories.IPerfilRepository;
import com.ecommerce.ecommerce.repositories.IUserRoleRepository;
import com.ecommerce.ecommerce.services.IPerfilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class PerfilService implements IPerfilService{

	@Autowired
	@Qualifier("perfilRepository")
	private IPerfilRepository perfilRepository;
	
	@Autowired
	@Qualifier("userRoleRepository")
	private IUserRoleRepository userRoleRepository;
		
	@Override
	public Perfil addNewProfile(User usuario) {
		return perfilRepository.save(new Perfil(usuario.getId(), usuario.getUsername(),userRoleRepository.findByIdUser(usuario.getId()).getRole(),
				usuario.getNombre(), usuario.getApellido() ,usuario.getDni()));
	}

}