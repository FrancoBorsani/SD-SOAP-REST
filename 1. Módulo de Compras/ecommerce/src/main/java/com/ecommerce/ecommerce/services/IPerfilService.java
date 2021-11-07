package com.ecommerce.ecommerce.services;

import com.ecommerce.ecommerce.entities.Perfil;
import com.ecommerce.ecommerce.entities.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IPerfilService {

	public Perfil addNewProfile(User usuario);

}