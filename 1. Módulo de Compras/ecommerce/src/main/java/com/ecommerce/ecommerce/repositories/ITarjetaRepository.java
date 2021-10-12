package com.ecommerce.ecommerce.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ecommerce.ecommerce.entities.Domicilio;
import com.ecommerce.ecommerce.entities.Tarjeta;
import com.ecommerce.ecommerce.entities.User;

@Repository("tarjetaRepository")
public interface ITarjetaRepository extends JpaRepository<Tarjeta, Serializable> {
	
	@Query(nativeQuery=true,value="select * from Tarjeta where numero=(:numero)")
	public User findByNumero(String numero);
	
	@Query(nativeQuery = true, value = "select * from Tarjeta where user_id=(:id)")
	public List<Tarjeta> findByIdUser(int id);
	
}