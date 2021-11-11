package com.example.modulocorreo.services.implementation;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.modulocorreo.entities.Envio;
import com.example.modulocorreo.repositories.IEnvioRepository;
import com.example.modulocorreo.services.IEnvioService;

@Service("envioService")
public class EnvioService implements IEnvioService {

	@Autowired
	@Qualifier("envioRepository")
	private IEnvioRepository envioRepository;

	@Override
	public List<Envio> getAll() {
		return envioRepository.findAll();
	}

	@Override
	public Envio insertOrUpdate(Envio envio) {
		return envioRepository.save(envio);
	}

	@Override
	public boolean remove(int id) {
		try {
			envioRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Envio findById(int id) {
		return envioRepository.findById(id);
	}
	
	@Override
	public Envio findByCodigoDeSeguimiento(String codigoDeSeguimiento) {
		return envioRepository.findByCodigoDeSeguimiento(codigoDeSeguimiento);
	}
	
	@Override
	public List<Envio> searchEnvio(String keyword) {
		return envioRepository.searchEnvio(keyword);
	}

	@Override
	public List<Map<Integer, String>> cantidadDeEnviosPorEstado() {
		return envioRepository.cantidadDeEnviosPorEstado();
	}
	
}

