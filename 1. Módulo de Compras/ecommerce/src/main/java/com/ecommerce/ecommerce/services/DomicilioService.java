package com.ecommerce.ecommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.ecommerce.entities.Domicilio;
//import com.ecommerce.ecommerce.entities.UserRole;
import com.ecommerce.ecommerce.repositories.IDomicilioRepository;

@Service
public class DomicilioService {
	
	@Autowired
	IDomicilioRepository domicilioRepository;
	
	public Domicilio guardarDomicilio(Domicilio domicilio) {
		return domicilioRepository.save(domicilio);			
	}

	public List<Domicilio> findByIdUser(int idUser) {
		return domicilioRepository.findByIdUser(idUser);
	}

}
