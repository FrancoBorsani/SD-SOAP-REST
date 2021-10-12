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
		return perfilRepository.save(new Perfil(usuario.getId(), usuario.getUsername(),userRoleRepository.findByIdUser(usuario.getId()).getRole(),
				usuario.getNombre(), usuario.getApellido() ,usuario.getDni()));
	}

	@Override
	public Perfil updateProfile(Perfil newProfile)throws IOException {
		Perfil oldProfile = perfilRepository.getById(newProfile.getId());
		newProfile.setUsername(oldProfile.getUsername());
		newProfile.setUserRol(oldProfile.getUserRol());
		return perfilRepository.save(newProfile);
	}
	
	@Override
	public Perfil updateProfile(Perfil profileToModif, String username, MultipartFile imagen , String aboutMe )throws IOException {
		remove(profileToModif.getId());
		//modifico el perfil con el nuevo username
		profileToModif.setUsername(username);
		String nuevaImagen = "id="+String.valueOf(profileToModif.getId())+"_"+ imagen.getOriginalFilename();//agregamos la ruta de la imagen con el id del usuario para poder diferenciarla de las imagenes de otros usuarios
		
		remove(profileToModif.getId());
		return perfilRepository.save(profileToModif); 
	}


}