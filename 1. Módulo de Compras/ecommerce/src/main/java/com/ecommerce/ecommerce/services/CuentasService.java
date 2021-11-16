package com.ecommerce.ecommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.ecommerce.entities.Cuentas;
import com.ecommerce.ecommerce.entities.User;
import com.ecommerce.ecommerce.repositories.CuentasRepository;

@Service
public class CuentasService {
	@Autowired
	CuentasRepository cuentasRepository;
	
	public Cuentas traerCuentaPorUser(User usuario) {
		return cuentasRepository.findByUser(usuario.getId());	
	}
}