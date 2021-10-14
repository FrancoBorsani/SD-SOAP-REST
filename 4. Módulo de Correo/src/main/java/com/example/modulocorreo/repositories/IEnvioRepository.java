package com.example.modulocorreo.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.modulocorreo.entities.Envio;

@Repository("envioRepository")
public interface IEnvioRepository extends JpaRepository<Envio, Serializable>{
	
	public abstract Envio findById(int id);
	
	public abstract Envio findByCodigoDeSeguimiento(String codigoDeSeguimiento);

}
