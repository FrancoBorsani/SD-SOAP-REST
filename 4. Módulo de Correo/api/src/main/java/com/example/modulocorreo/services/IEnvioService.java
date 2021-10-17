package com.example.modulocorreo.services;

import java.util.List;

import com.example.modulocorreo.entities.Envio;

public interface IEnvioService {
	
	public List<Envio> getAll();
	
	public Envio findById(int id);
	
	public Envio findByCodigoDeSeguimiento(String codigoDeSeguimiento);
	
	public Envio insertOrUpdate(Envio Envio);
	
	public boolean remove(int id);

}
