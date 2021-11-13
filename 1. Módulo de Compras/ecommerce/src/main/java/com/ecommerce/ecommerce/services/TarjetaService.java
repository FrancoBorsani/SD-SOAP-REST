package com.ecommerce.ecommerce.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.ecommerce.ecommerce.entities.Tarjeta;
import com.ecommerce.ecommerce.repositories.ITarjetaRepository;

@Service
public class TarjetaService {
	
	@Autowired
	ITarjetaRepository tarjetaRepository;
	
	public Tarjeta guardarTarjeta(Tarjeta tarjeta) {
		return tarjetaRepository.save(tarjeta);			
	}
	
	public List<Tarjeta> findByIdUser(int idUser) {
		return tarjetaRepository.findByIdUser(idUser);
	}

	public Optional<Tarjeta> findById(int idTarjeta) {
		return tarjetaRepository.findById(idTarjeta);
	}

}
