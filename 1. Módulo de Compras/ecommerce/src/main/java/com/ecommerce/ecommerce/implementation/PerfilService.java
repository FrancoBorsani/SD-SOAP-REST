package com.ecommerce.ecommerce.implementation;

import com.ecommerce.ecommerce.entities.Perfil;
import com.ecommerce.ecommerce.entities.User;
import com.ecommerce.ecommerce.repositories.IPerfilRepository;
import com.ecommerce.ecommerce.repositories.IUserRoleRepository;
import com.ecommerce.ecommerce.services.IPerfilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;

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
	public List<Perfil> getAll() {
		return perfilRepository.findAll();
	}
	
	@Override
	public Perfil findById(int id) {
		return perfilRepository.findById(id);
	}

	@Override
	public boolean remove(int id) {
		try {
			perfilRepository.deleteById(id);;
			return true;
		}catch(Exception e) {
			return false;
		}
	}

	@Override
	public Perfil addNewProfile(User usuario) {
		return perfilRepository.save(new Perfil(usuario.getId(), usuario.getNombre(),userRoleRepository.findByIdUser(usuario.getId()).getRole(),
				usuario.getApellido(), usuario.getDni(), usuario.getUsuario()));
	}

	@Override
	public Perfil updateProfile(Perfil newProfile) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	
}