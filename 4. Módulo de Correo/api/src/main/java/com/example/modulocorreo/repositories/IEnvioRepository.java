package com.example.modulocorreo.repositories;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.modulocorreo.entities.Envio;

@Repository("envioRepository")
public interface IEnvioRepository extends JpaRepository<Envio, Serializable>{
	
	public abstract Envio findById(int id);
	
	public abstract Envio findByCodigoDeSeguimiento(String codigoDeSeguimiento);
	
    @Query("SELECT e FROM Envio e WHERE CONCAT(e.DNIDestinatario, ' ', e.estado, ' ', e.codigoDeSeguimiento) LIKE %?1%")
    public abstract List<Envio> searchEnvio(String keyword);
	
    @Query("SELECT COUNT(*) AS cantidad, e.estado as estado FROM Envio e GROUP BY e.estado")
    public abstract List<Map<Integer, String>> cantidadDeEnviosPorEstado();

}
