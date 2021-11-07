package com.ecommerce.ecommerce.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.ecommerce.entities.Domicilio;
import com.ecommerce.ecommerce.entities.User;

@Repository("domicilioRepository")
public interface IDomicilioRepository extends CrudRepository<Domicilio, Long> {
	
	@Query(nativeQuery = true, value = "select * from Domicilio where user_id=(:id)")
	public List<Domicilio> findByIdUser(int id);
	
	
}